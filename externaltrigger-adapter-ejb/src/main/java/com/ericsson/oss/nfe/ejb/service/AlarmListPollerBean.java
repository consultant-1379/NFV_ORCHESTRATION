package com.ericsson.oss.nfe.ejb.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.ejb.ProcessInstanceProgressServiceLocal;
import com.ericsson.oss.nfe.persistence.entity.ProcessTriggerEventEntity;
import com.ericsson.oss.nfe.poc.utils.FileUtils;

@Stateless
public class AlarmListPollerBean {

	@Inject
	private ProcessInstanceProgressServiceLocal processInstanceProgressServiceLocal;
	
	/*private static final String getAlarmsURL = "https://apache.vts.com/alarmcontroldisplayservice/alarmMonitoring/alarmoperations/getalarmlist/eventPoIdList?limit=50&recordLimit=50&filters=CLEARED&latest=true";
	
	private static final String enmLoginURL = "https://apache.vts.com/login";
	
	private static final String enmLogOut = "https://apache.vts.com/logout";
	
	private static final String getAllCollections = "https://apache.vts.com/topologyCollections/staticCollections";
	
	private static final String vEPG_COLLECTION_NAME="vEPG-Collection";
	*/
	private static final String ENM_FMALARM="ENMFMAlarm";
	
	@Resource(mappedName = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(mappedName = "java:/queue/wfsTiggerEventQueue")
	private Queue wfsTriggerQueue;
	
	
	@Inject
	private PropFileConfigurationBean configurationBean;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@PostConstruct
	void onServiceStart() {
		logger.debug(" -----------------------~ AlarmListPollerBean Starting a poll run ~------------------------------------");

	}

	@Schedule(second="10", minute = "*", hour = "*", info = "every 30 secondds", persistent = false)
	public void doWork() {

		logger.debug("timer bean called at  " + new Date());

		handlePolling(); 

	}
	
	private void handlePolling()
	{
		
		try {
			logger.debug("handlePolling started  ");
			
			//First login to ENM
			String enmCookie = this.loginToENM();
			
			if(enmCookie==null)
				return;
			
			logger.debug("loginToENM succeed with cookie  "+enmCookie);
			
			//Get all the MO's for which you need to fetch alarms
			String moIds = getAllNodes(enmCookie);
			
			logger.debug("getAllNodes succeed with MOList  "+moIds);
			
			//Get all active alarms for the MO list obtained
			Map<String,JSONObject> activeAlarmMap = this.getAllActivAlarms(moIds, enmCookie);
			
			logger.debug("getAllActivAlarms succeed with activeAlarmMap  "+activeAlarmMap.keySet());
			
			//Filter the alarm map from already triggered alarms
			activeAlarmMap = this.filterAlreadyTriggeredAlarms(activeAlarmMap);
 
			logger.trace("getAllActivAlarms filtered and result is"+activeAlarmMap);
						
			logger.info("Sending total of :"+activeAlarmMap.size() +" alarms on the JMS downstream");
			
			for(Map.Entry<String, JSONObject> alarmJson:activeAlarmMap.entrySet()){				
				
				JSONObject  triggerJSON= new JSONObject();
				triggerJSON.put("triggerName", ENM_FMALARM);
				triggerJSON.put("triggerType", ENM_FMALARM);
				triggerJSON.put("filterString",alarmJson.getValue().get("specificProblem"));
				triggerJSON.put("externaleventid",alarmJson.getKey());
				triggerJSON.put("alarmParams",alarmJson.getValue());

				logger.info("sendJMSMessage  for  externaleventid :  "+alarmJson.getKey());
				
				System.out.println("sendJMSMessage  for  externaleventid :  "+alarmJson.getKey());
				
				this.sendJMSMessage(triggerJSON.toJSONString());
			}
					
			
			//Finally logout from ENM
			//logoutENM(enmCookie);
			
			logger.debug("handlePolling ended  ");
		} catch (Throwable allExp) {
			 logger.error("Some error in time run, but suppressed :"+allExp);
		}
		
	}

	private String loginToENM() {
		
		logger.debug("loginToENM called  ");
		
		String cookie = null;
		String userName = configurationBean.enmUserName;
		String password = configurationBean.enmUserPassword;
		
		Protocol myhttps = new Protocol("https", (ProtocolSocketFactory) new EasySSLProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", myhttps);

		logger.debug("loginToENM with endpoint :  "+configurationBean.enmLoginURL);
		
		
		final PostMethod post = new PostMethod(configurationBean.enmLoginURL);
		final HttpClient client = new HttpClient();

		// set headers
		post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		post.addRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		post.addRequestHeader("User-Agent", "Mozilla/5.0");

		post.addRequestHeader("Accept-Language", "en-GB,en-US;q=0.8,en;q=0.6");
		post.addRequestHeader("Cache-Control", "max-age=0");
		post.addRequestHeader("Accept-Encoding", "gzip, deflate");
		post.addRequestHeader("Connection", "keep-alive");

		// set login credentials for POST request
		NameValuePair params[] = new NameValuePair[] { new NameValuePair("IDToken1", userName),
				new NameValuePair("IDToken2", password) };
		post.setRequestBody(EncodingUtil.formUrlEncode(params, "UTF-8"));

		try {
			client.executeMethod(post);
			
			logger.debug("loginToENM with post call returned with :  "+post.getStatusCode());
			
			
			ArrayList<String> setCookies = new ArrayList<String>();

			Header[] responseHeaders = post.getResponseHeaders("Set-Cookie");

			
			for (int i = 0; i < responseHeaders.length; i++) {		 
				String sessionCookie = responseHeaders[i].getValue().split(";")[0];
				setCookies.add(sessionCookie);

				if (sessionCookie.contains("iPlanetDirectoryPro")) {
					cookie = sessionCookie;
					logger.debug("iPlanetDirectoryPro session cookie - " + cookie);
				}
			}
		} catch (Exception e) {
			logger.error("Error login to ENM",e);
		} finally{
			releaseHTTPConnection(post);
		}

		logger.debug("loginToENM ended returning : ",cookie); 
		return cookie;
	}

	
	private void logoutENM(String enmSessionCookie)
	{
		logger.debug("logoutENM called  ");
		
		final HttpClient client = new HttpClient();
		GetMethod get = null;
		try {
			get = new GetMethod(configurationBean.enmLogOut);
			get.addRequestHeader("Cookie", enmSessionCookie);
			get.addRequestHeader("Accept", "application/json");

			client.executeMethod(get);
  
		} catch (Exception e) {
			logger.error("Error logoutENM, but ignored",e); 
		} 
		finally{
			releaseHTTPConnection(get);
		}
		logger.debug("logoutENM ended  ");
	}
	
	private  Map<String,JSONObject> filterAlreadyTriggeredAlarms(Map<String,JSONObject> allActiveAlarmsMap)
	{
		logger.debug("filterAlreadyTriggeredAlarms started  ");
		//Get all the triggered Events for the EventType(FMAlarm-ENM)
		List<ProcessTriggerEventEntity> triggeredFMAlarms=  processInstanceProgressServiceLocal.getAllTriggerEvents(ENM_FMALARM, null);
		
		logger.debug("triggeredFMAlarms returned :  "+(triggeredFMAlarms!=null ? triggeredFMAlarms.size():0));
		
		//Get rid off the activeAlarms which have been used as trigger
		if(triggeredFMAlarms!=null && !triggeredFMAlarms.isEmpty())
		{
			
			for(ProcessTriggerEventEntity triggAlarm:triggeredFMAlarms){
				if(allActiveAlarmsMap.containsKey(triggAlarm.getExternalEventId()))
					allActiveAlarmsMap.remove(triggAlarm.getExternalEventId());
			}
			
		}
		logger.debug("filterAlreadyTriggeredAlarms ended  ");
		return allActiveAlarmsMap;
	}
	
	
	private String getAllNodes(String enmSessionCookie)
	{
		logger.debug("getAllNodes called  ");
		
		StringBuilder nodeNames = new StringBuilder();
		
		String result = "";
		GetMethod get = null;
		final HttpClient client = new HttpClient();
		try {
			get = new GetMethod(configurationBean.getAllCollectionsURL);
			get.addRequestHeader("Cookie", enmSessionCookie);
			get.addRequestHeader("Accept", "application/json");

			client.executeMethod(get);
 
			result = FileUtils.loadStreamAsString(get.getResponseBodyAsStream());			
			
			JSONArray staticCollections = (JSONArray) JSONValue.parse(result);

			logger.trace("get all collections response: " + staticCollections);
			
			String vEPGCollectionId = null;
			if(staticCollections!=null && staticCollections.size()>0)
			{
				 
				for(int index = 0;index<staticCollections.size();index++)
				{
					
					JSONObject collection = (JSONObject)staticCollections.get(index);
					if(collection != null && configurationBean.vEPG_COLLECTION_NAME.equalsIgnoreCase(collection.get("name").toString()))
					{
						vEPGCollectionId = collection.get("poId").toString();
						break;
					}
					
				}  
			}
			
			logger.debug("Got the collection id for vEPGCollection --> "+vEPGCollectionId);
			
			//Get all the Nodes in the collection if the collection ID is not null 
			// i,e you found the vepg collection id
			if(vEPGCollectionId!=null)
			{
				get = new GetMethod(configurationBean.getAllCollectionsURL+"/"+vEPGCollectionId);
				get.addRequestHeader("Cookie", enmSessionCookie);
				get.addRequestHeader("Accept", "application/json");

				client.executeMethod(get);
	 
				result = FileUtils.loadStreamAsString(get.getResponseBodyAsStream());			
				
				JSONObject vepgCollectionJson = (JSONObject) JSONValue.parse(result);

				logger.trace("Get vEPG Collection is response: " + vepgCollectionJson);
				
				//If the PO list is present and not empty extract the mo ids
				if(vepgCollectionJson!=null && vepgCollectionJson.containsKey("poList") &&
							!((JSONArray)vepgCollectionJson.get("poList")).isEmpty())
				{
					JSONArray molist = (JSONArray)vepgCollectionJson.get("poList");
					for(int index = 0;index<molist.size();index++)
					{
						JSONObject moJsonObj = (JSONObject)molist.get(index);
						if(moJsonObj!=null && moJsonObj.get("moName")!=null)
							nodeNames.append(moJsonObj.get("moName").toString()).append(";");
					}
				}
				
			}
			
		}catch(Exception e){ logger.error("Error getting getAllNodes, but ignored",e);}
		finally{
			releaseHTTPConnection(get);
		}
		
		logger.debug("getAllNodes ended  ");
		return nodeNames.toString();
		
		
	}
	
	
	private Map<String,JSONObject> getAllActivAlarms(String moIds, String enmSessionCookie) {
		

		Map<String,JSONObject> alarmMap = new HashMap<String, JSONObject>();
		
		String result = "";
		String getURLWithNodes = configurationBean.getAlarmsURL;
		GetMethod get = null;
		final HttpClient client = new HttpClient();
		try {
			
			if(moIds!=null && !moIds.isEmpty())
				getURLWithNodes = configurationBean.getAlarmsURL+"&nodes="+moIds;
			
			logger.debug("getAllActivAlarms with URL  "+getURLWithNodes);
			
			get = new GetMethod(getURLWithNodes);
			get.addRequestHeader("Cookie", enmSessionCookie);
			get.addRequestHeader("Accept", "application/json");

			
			
			client.executeMethod(get);
 
			result = FileUtils.loadStreamAsString(get.getResponseBodyAsStream());
			
			
			JSONArray alarmList = (JSONArray) JSONValue.parse(result);

			logger.trace("GET response: " + alarmList);
			
			if(alarmList!=null && alarmList.size()>1 && alarmList.get(2)!=null)
			{
				JSONArray alarmJSON = (JSONArray)((JSONObject)alarmList.get(2)).get("recordList");
				
				for(int index = 0;index<alarmJSON.size();index++)
				{
					
					JSONObject alarmJsonObj = (JSONObject)alarmJSON.get(index);
					if(alarmJsonObj != null && alarmJsonObj.containsKey("eventPoIdAsString"))
						alarmMap.put(alarmJsonObj.get("eventPoIdAsString").toString(), alarmJsonObj);					
					
				}  
			}
			
		} catch (Exception e) {
			logger.error("Error getting getAllActivAlarms, but ignored",e); 
		}
		finally{
			releaseHTTPConnection(get);
		}

		logger.trace("alarm id list is : "+alarmMap.keySet());
		return alarmMap;
	}
	
	
	public String sendJMSMessage(String messageString) 
	{
		logger.debug("Send message...");
		
		Connection connection = null;
		try {
			connection = connectionFactory.createConnection();
			final Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			
			final MessageProducer messageProducer = session
					.createProducer(wfsTriggerQueue);
			
			connection.start();
			 
			final Message message = session.createTextMessage(messageString);
			messageProducer.send(message);
			logger.debug("Send message success");

		} catch (JMSException e) {
			logger.error("Failed to send work flow event via JMS.", e);
		} finally {
			try {
				
				if(connection!=null)
					connection.close();
				
			} catch (JMSException e) {
				logger.error("Failed to close JMS Connection", e);
			}
		}
		return "ok";
	}
	private static void releaseHTTPConnection(HttpMethodBase httpMethod)
	{	
		try {
			if(httpMethod!=null)
				httpMethod.releaseConnection();
			
		} catch (Exception igonre){}
	}
	
	public static void main(String[] args) {
		
		 AlarmListPollerBean that = new AlarmListPollerBean();
		String cookie = that.loginToENM();
		
		System.out.println("Alarm List  : " +that.getAllNodes(cookie));
		System.out.println("Alarm List  : " +that.getAllActivAlarms(that.getAllNodes(cookie), cookie));
	}
	

}
