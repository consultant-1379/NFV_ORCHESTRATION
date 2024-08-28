package com.ericsson.oss.nfe;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.json.simple.JSONObject;
import org.junit.Test;

import com.ericsson.oss.nfe.persistence.ProcessTriggerDAO;
import com.ericsson.oss.nfe.poc.utils.Utils;

public class ProcessTriggerDAOTest {

	 private static EntityManager entityManager = null;

	 private ProcessTriggerDAO processTriggerDAO;
	 
	   /* @BeforeClass
	    public static void setUpClass() throws Exception {
	        if (entityManager == null) {
	        	entityManager = (EntityManager) Persistence.createEntityManagerFactory("testPU").createEntityManager();
	        }
	        
	        try {
	    		Class.forName("net.sourceforge.jtds.jdbc.Driver");
	    	} catch (ClassNotFoundException e) {
	    		System.out.println("Where is your SYbase JTDS JDBC Driver?");
	    		e.printStackTrace();
	    		return;
	    	}
	     
	    	System.out.println("SYbase JTDS Driver Registered!");
	    	Connection connection = null;
	     
	    	try {
	    		connection = DriverManager
	    		.getConnection("jdbc:jtds:sybase://10.45.19.163:5025/fmadb_1_1 ","sa", "sybase11");
	     
	    	} catch (SQLException e) {
	    		System.out.println("Connection Failed! Check output console");
	    		e.printStackTrace();
	    		return;
	    	}
	     
	    	if (connection != null) {
	    		System.out.println("You made it, take control your database now!");
	    		releaseConnection(connection);
	    	} else {
	    		System.out.println("Failed to make connection!");
	    	}
	    }

	@Ignore @Test
	public void test() {
		System.out.println("ProcessTriggerDAOTest : "+processTriggerDAO);
		
 		
		System.out.println("entityManager : "+entityManager);

			 
			 entityManager.getTransaction().begin();
		
			 ProcessTriggerDefinitionEntity trigger = new ProcessTriggerDefinitionEntity();
			 
			 trigger.setTriggerName("testTrigger");
			  trigger.setTriggerType("testTriggerTyp");
			  
			 entityManager.persist(trigger);
			  
			 
			 entityManager.getTransaction().commit();
			 
			 entityManager.close();
			 
			 System.out.println(" trigger : "+trigger);
	}
	
	 @Test
		public void testAlarmPull() {
			 
		 System.out.println("test done !!");
		}
	 
	 
	 private static void releaseConnection(Connection conn)
	 {
		 try {
	    		 if(conn!=null)
	    			 conn.close();
	    	} catch (SQLException e) {
	    		System.out.println("releaseConnection failed");
	    		e.printStackTrace(); 
	    	}
	 }*/
	 
	 @Test
		public void testJSON() {
			System.out.println("ProcessTriggerDAOTest : testJSON");
			JSONObject json = new JSONObject();
			json.put("a","avalue");
			json.put("b","bvalue");
			json.put("c","cvalue");
			json.put("d","dvalue");
	 		
			
			System.out.println(Utils.extractMOname("SubNetwork=ONRM_ROOT_MO,ManagedElement=vEPG-10-51-215-137"));
			  
		}
}
