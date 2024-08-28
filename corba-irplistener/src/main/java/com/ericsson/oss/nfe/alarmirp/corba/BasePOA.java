package com.ericsson.oss.nfe.alarmirp.corba;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.omg.CORBA.ORB;
import org.omg.CosNotification.EventType;
import org.omg.CosNotification.FixedEventHeader;
import org.omg.CosNotification.Property;
import org.omg.CosNotification.StructuredEvent;
import org.omg.CosNotifyComm.StructuredPushConsumerPOA;
import org.omg.PortableServer.POA;
import org.omg.TimeBase.UtcT;
import org.omg.TimeBase.UtcTHelper;

import com.ericsson.irp.NotificationIRPConstDefs.NV_ACK_SYSTEM_ID;
import com.ericsson.irp.NotificationIRPConstDefs.NV_ACK_TIME;
import com.ericsson.irp.NotificationIRPConstDefs.NV_ACK_USER_ID;
import com.ericsson.irp.NotificationIRPConstDefs.NV_ADDITIONAL_TEXT;
import com.ericsson.irp.NotificationIRPConstDefs.NV_ALARM_ID;
import com.ericsson.irp.NotificationIRPConstDefs.NV_BACKED_UP_STATUS;
import com.ericsson.irp.NotificationIRPConstDefs.NV_BACK_UP_OBJECT;
import com.ericsson.irp.NotificationIRPConstDefs.NV_EVENT_TIME;
import com.ericsson.irp.NotificationIRPConstDefs.NV_MANAGED_OBJECT_CLASS;
import com.ericsson.irp.NotificationIRPConstDefs.NV_MANAGED_OBJECT_INSTANCE;
import com.ericsson.irp.NotificationIRPConstDefs.NV_MONITORED_ATTRIBUTES;
import com.ericsson.irp.NotificationIRPConstDefs.NV_PERCEIVED_SEVERITY;
import com.ericsson.irp.NotificationIRPConstDefs.NV_PROBABLE_CAUSE;
import com.ericsson.irp.NotificationIRPConstDefs.NV_PROPOSED_REPAIR_ACTIONS;
import com.ericsson.irp.NotificationIRPConstDefs.NV_SPECIFIC_PROBLEM;
import com.ericsson.irp.NotificationIRPConstDefs.NV_STATE_CHANGE_DEFINITION;
import com.ericsson.irp.NotificationIRPConstDefs.NV_SYSTEM_DN;
import com.ericsson.irp.NotificationIRPConstDefs.NV_THRESHOLD_INFO;
import com.ericsson.irp.NotificationIRPConstDefs.NV_TREND_INDICATION;
import com.ericsson.oss.nfe.alarmirp.ConfigUtils;

public abstract class BasePOA extends StructuredPushConsumerPOA {
	 

	protected ORB orb = null;
	protected POA rootPOA, myPOA = null;
	ORBUtil util = new ORBUtil();
	JMSUtil jmsutil;

	protected String filterString = null;

	public BasePOA(String attachFilterString) {

		filterString = attachFilterString;
		util.initORB();
		orb = util.getOrb();
		jmsutil = jmsutil.init(ConfigUtils.instance.getConfigAsMap());
	}

	public abstract boolean attach(String notifPath);

	public abstract void synchronize(String alarmIRPpath, String filter);

	public abstract boolean acknowledge(boolean unAckFrame, String aPath,
			String[] id, String user);

	public void push_structured_event(StructuredEvent se) {

		String jsonNotification = buildJSONNotification(se);
		
		System.out.println(" push_structured_event called back and object built is "+jsonNotification);
		try {
			jmsutil.sendMessage(ConfigUtils.instance.
					getProperty("triggerNotificationJMSQueue"),
					jsonNotification);
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	public void offer_change(EventType[] et, EventType[] et2) {
	}

	private String buildJSONNotification(StructuredEvent notification) {

		// StringBuffer buffer = new StringBuffer(256);
		String value = "";
		UtcT utcTime = null;
		Map alarmParams = new HashMap<String, String>();

		String alarmaName ="";

		/*
		 * if (notification == null) { return "Structured Event was null (" +
		 * new Date() + ")"; }
		 */
		FixedEventHeader fixedHeader;
		Property pf[];
		try {
			fixedHeader = notification.header.fixed_header;

			pf = notification.filterable_data;

			alarmParams.put("EVENT_NAME", fixedHeader.event_name);
			alarmaName = fixedHeader.event_name;
			alarmParams.put("EVENT_TYPE", fixedHeader.event_type.type_name);

			for (int i = 0; i < pf.length; i++) {
				value = pf[i].value.toString();

				if (pf[i].name.equals(NV_EVENT_TIME.value)) {
					try {
						utcTime = UtcTHelper.extract(pf[i].value);
						value = NotificationTranslator.decodeTime(utcTime);
					} catch (Exception e) {
						e.printStackTrace();
					}
					alarmParams.put("EVENT_TIME", value);

				} else if (pf[i].name.equals(NV_SYSTEM_DN.value)) {

					alarmParams.put("NV_SYSTEM_DN", value);

				} else if (pf[i].name.equals(NV_MANAGED_OBJECT_CLASS.value)) {
					alarmParams.put("MANAGED_OBJECT_CLASS", value);

				} else if (pf[i].name.equals(NV_MANAGED_OBJECT_INSTANCE.value)) {
					alarmParams.put("MANAGED_OBJECT_INSTANCE", value);

				} else if (pf[i].name.equals(NV_PROBABLE_CAUSE.value)) {
					alarmParams.put("PROBABLE_CAUSE", value);

				} else if (pf[i].name.equals(NV_PERCEIVED_SEVERITY.value)) {
					alarmParams.put("PERCEIVED_SEVERITY", value);

				} else if (pf[i].name.equals(NV_SPECIFIC_PROBLEM.value)) {
					alarmParams.put("SPECIFIC_PROBLEM", value);

				} else if (pf[i].name.equals(NV_ADDITIONAL_TEXT.value)) {
					value = NotificationTranslator.replace(value, "\\n", "\n");
					alarmParams.put("ADDITIONAL_TEXT", value);

				} else if (pf[i].name.equals(NV_ALARM_ID.value)) {

					alarmParams.put("ALARM_ID",
							NotificationTranslator.extracAlarmId(value));

				} else if (pf[i].name.equals(NV_ACK_USER_ID.value)) {

					alarmParams.put("ACK_USER_ID", value);

				} else if (pf[i].name.equals(NV_ACK_TIME.value)) {
					try {
						utcTime = UtcTHelper.extract(pf[i].value);
					} catch (Exception e) {
						e.printStackTrace();
					}
					value = NotificationTranslator.decodeTime(utcTime);
					alarmParams.put("ACK_TIME", value);

				} else if (pf[i].name.equals(NV_ACK_SYSTEM_ID.value)) {
					alarmParams.put("ACK_SYSTEM_ID", value);
				} else if (pf[i].name.equals(NV_BACKED_UP_STATUS.value)) {
					alarmParams.put("BACKED_UP_STATUS", value);
				} else if (pf[i].name.equals(NV_BACK_UP_OBJECT.value)) {
					alarmParams.put("BACK_UP_OBJECT", value);
				} else if (pf[i].name.equals(NV_THRESHOLD_INFO.value)) {
					alarmParams.put(pf[i].name, value);
				} else if (pf[i].name.equals(NV_TREND_INDICATION.value)) {
					alarmParams.put(pf[i].name, value);
				} else if (pf[i].name.equals(NV_STATE_CHANGE_DEFINITION.value)) {
					alarmParams.put(pf[i].name, value);
				} else if (pf[i].name.equals(NV_MONITORED_ATTRIBUTES.value)) {
					alarmParams.put(pf[i].name, value);
				} else if (pf[i].name.equals(NV_PROPOSED_REPAIR_ACTIONS.value)) {
					alarmParams.put(pf[i].name, value);
				}

				if ("h".equals(pf[i].name)) {
					if ("1".equals(value)) {
						alarmParams.put("CRITICALITY", "INDETERMINATE");
					} else if ("2".equals(value)) {
						alarmParams.put("CRITICALITY", "CRITICAL");
					} else if ("3".equals(value)) {
						alarmParams.put("CRITICALITY", "MAJOR");
					} else if ("4".equals(value)) {
						alarmParams.put("CRITICALITY", "MINOR");
					} else if ("5".equals(value)) {
						alarmParams.put("CRITICALITY", "WARNING");
					} else if ("6".equals(value)) {
						alarmParams.put("CRITICALITY", "CLEARED");
					} else {
						alarmParams.put("CRITICALITY", "<unknown>");

					}
				} else if ("o".equals(pf[i].name)) {
					if ("1".equals(value)) {
						alarmParams.put("STATE", "ACKNOWLEDGED");
					} else if ("2".equals(value)) {
						alarmParams.put("STATE", "UNACKNOWLEDGED");
					} else {
						alarmParams.put("STATE", "<unknown>");

					}
				}
			}
		} catch (Exception ignore) {
		}

		// Clean up
		pf = null;
		fixedHeader = null;
		notification = null;

		JSONObject triggerJson = new JSONObject();
		triggerJson.put("triggerName", alarmaName);
		triggerJson.put("triggerType", "FMAlarm");
		triggerJson.put("filterString", this.filterString);
		triggerJson.put("externaleventid", alarmParams.get("ALARM_ID"));
		JSONObject alarmParamsJsonObj = new JSONObject(alarmParams);
		triggerJson.put("alarmParams", alarmParamsJsonObj);

		return triggerJson.toJSONString();
	}
}
