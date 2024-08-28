package com.ericsson.oss.nfe.alarmirp.corba;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.omg.CosNotification.*;
import org.omg.TimeBase.UtcT;
import org.omg.TimeBase.UtcTHelper;

import com.ericsson.irp.AlarmIRPConstDefs.*;
//import com.ericsson.irp.NotificationIRPConstDefs.NV_REASON;
import com.ericsson.irp.NotificationIRPConstDefs.*;

public class NotificationTranslator {
	
    private static DateFormat dateFormatter;
    private static final long   GREGORIAN_EPOCH_MILLIS = 12219292800000L;
    
    private String latestNotificationId_ = null;
    private String latestAckState_ = null;
    private String alarmID = null;

    static {
      // Set GMT time zone.
      dateFormatter = new SimpleDateFormat("yyyyMMdd HH:mm:ss z");
      dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    
    public String getLatestNotificationId() {
        return latestNotificationId_;
    }

    public String getLatestAckState() {
        return latestAckState_;
    }

    /**
     * Utility method to make it possible to trace all incoming events.
     *
     * @param the StructuredEvent to stringify.
     * @return A nice string.
     */
    public String translateEvent(StructuredEvent notification) {
        StringBuffer buffer = new StringBuffer(256);
        String value = "";
        UtcT utcTime = null;
        
        latestNotificationId_ = null;
        latestAckState_ = null;

        if (notification == null) {
            return "Structured Event was null (" + getCurrentDateTime() + ")";
        }

        FixedEventHeader fixedHeader = notification.header.fixed_header;

        Property pf[] = notification.filterable_data;

        buffer.append("\n*** START #");
        buffer.append(" *** (");
        buffer.append(getCurrentDateTime());

        buffer.append(")\n<FIXED_HEADER> EXTENDED_EVENT_TYPE : ");
        buffer.append(fixedHeader.event_name);
        if (fixedHeader.event_name.equals(NOTIFY_FM_NEW_ALARM.value)) {
            buffer.append(" (NOTIFY_FM_NEW_ALARM");
        } else if (fixedHeader.event_name.equals(
                NOTIFY_FM_ACK_STATE_CHANGED.value)) {
            buffer.append(" (NOTIFY_FM_ACK_STATE_CHANGED");
        } else if (fixedHeader.event_name.equals(
                NOTIFY_FM_ALARM_LIST_REBUILT.value)) {
            buffer.append(" (NOTIFY_FM_ALARM_LIST_REBUILT");
        } else if (fixedHeader.event_name.equals(NOTIFY_FM_CHANGED_ALARM.value)) {
            buffer.append(" (NOTIFY_FM_CHANGED_ALARM");
        } else if (fixedHeader.event_name.equals(NOTIFY_FM_CLEARED_ALARM.value)) {
            buffer.append(" (NOTIFY_FM_CLEARED_ALARM");
        } else {
            buffer.append("...");
        }

        buffer.append("\n<FIXED_HEADER> EVENT_TYPE : ");
        buffer.append(fixedHeader.event_type.type_name);
        if (fixedHeader.event_type.type_name.equals(
                ET_COMMUNICATIONS_ALARM.value)) {
            buffer.append(" (ET_COMMUNICATIONS_ALARM)");
        } else if (fixedHeader.event_type.type_name.equals(
                ET_ENVIRONMENTAL_ALARM.value)) {
            buffer.append(" (ET_ENVIRONMENTAL_ALARM)");
        } else if (fixedHeader.event_type.type_name.equals(
                ET_EQUIPMENT_ALARM.value)) {
            buffer.append(" (ET_EQUIPMENT_ALARM)");
        } else if (fixedHeader.event_type.type_name.equals(
                ET_PROCESSING_ERROR_ALARM.value)) {
            buffer.append(" (ET_PROCESSING_ERROR_ALARM)");
        } else if (fixedHeader.event_type.type_name.equals(
                ET_QUALITY_OF_SERVICE_ALARM.value)) {
            buffer.append(" (ET_QUALITY_OF_SERVICE_ALARM)");
        } else {
            buffer.append("...");
        }

        for (int i = 0; i < pf.length; i++) {
            value = pf[i].value.toString();
            if (pf[i].name.equals(NV_NOTIFICATION_ID.value)) {
                buffer.append("\nNV_NOTIFICATION_ID");
                if (value.startsWith("\"") && value.endsWith("\"")) {
					latestNotificationId_ = value.substring(1, value.length()-1);
                } else {
					latestNotificationId_ = value;
                }
            } else if (pf[i].name.equals(NV_CORRELATED_NOTIFICATIONS.value)) {
                buffer.append("\nNV_CORRELATED_NOTIFICATIONS");
            } else if (pf[i].name.equals(NV_EVENT_TIME.value)) {
                buffer.append("\nNV_EVENT_TIME");
                try {
                    utcTime = UtcTHelper.extract(pf[i].value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                value = decodeTime(utcTime);
            } else if (pf[i].name.equals(NV_SYSTEM_DN.value)) {
                buffer.append("\nNV_SYSTEM_DN");
            } else if (pf[i].name.equals(NV_MANAGED_OBJECT_CLASS.value)) {
                buffer.append("\nNV_MANAGED_OBJECT_CLASS");
            } else if (pf[i].name.equals(NV_MANAGED_OBJECT_INSTANCE.value)) {
                buffer.append("\nNV_MANAGED_OBJECT_INSTANCE");
            } else if (pf[i].name.equals(NV_PROBABLE_CAUSE.value)) {
                buffer.append("\nNV_PROBABLE_CAUSE");
            } else if (pf[i].name.equals(NV_PERCEIVED_SEVERITY.value)) {
                buffer.append("\nNV_PERCEIVED_SEVERITY");
            } else if (pf[i].name.equals(NV_SPECIFIC_PROBLEM.value)) {
                buffer.append("\nNV_SPECIFIC_PROBLEM");
            } else if (pf[i].name.equals(NV_ADDITIONAL_TEXT.value)) {
                buffer.append("\nNV_ADDITIONAL_TEXT");
                value = replace(value, "\\n", "\n");                
            } else if (pf[i].name.equals(NV_ALARM_ID.value)) {
                buffer.append("\nNV_ALARM_ID");
                setAlarmID(value);
            } else if (pf[i].name.equals(NV_ACK_USER_ID.value)) {
                buffer.append("\nNV_ACK_USER_ID");
            } else if (pf[i].name.equals(NV_ACK_TIME.value)) {
                buffer.append("\nNV_ACK_TIME");
                try {
                    utcTime = UtcTHelper.extract(pf[i].value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                value = decodeTime(utcTime);
            } else if (pf[i].name.equals(NV_ACK_SYSTEM_ID.value)) {
                buffer.append("\nNV_ACK_SYSTEM_ID");
            } else if (pf[i].name.equals(NV_ACK_STATE.value)) {
                buffer.append("\nNV_ACK_STATE");
                latestAckState_ = value;
            } else if (pf[i].name.equals(NV_BACKED_UP_STATUS.value)) {
                buffer.append("\nNV_BACKED_UP_STATUS");
            } else if (pf[i].name.equals(NV_BACK_UP_OBJECT.value)) {
                buffer.append("\nNV_BACK_UP_OBJECT");
            } else if (pf[i].name.equals(NV_THRESHOLD_INFO.value)) {
                buffer.append("\nNV_THRESHOLD_INFO");
            } else if (pf[i].name.equals(NV_TREND_INDICATION.value)) {
                buffer.append("\nNV_TREND_INDICATION");
            } else if (pf[i].name.equals(NV_STATE_CHANGE_DEFINITION.value)) {
                buffer.append("\nNV_STATE_CHANGE_DEFINITIONS");
            } else if (pf[i].name.equals(NV_MONITORED_ATTRIBUTES.value)) {
                buffer.append("\nNV_MONITORED_ATTRIBUTES");
            } else if (pf[i].name.equals(NV_PROPOSED_REPAIR_ACTIONS.value)) {
                buffer.append("\nNV_PROPOSED_REPAIRED_ACTIONS");
            } /*else if (pf[i].name.equals(NV_REASON.value)) {
                buffer.append("\nNV_REASON");
            } */else {
                buffer.append("\n*** Not defined ***");
            }
            buffer.append(" $").append(pf[i].name).append(" : ");
            buffer.append(value);
            if ("h".equals(pf[i].name)) {
                if ("1".equals(value)) {
                    buffer.append(" (INDETERMINATE)");
                } else if ("2".equals(value)) {
                    buffer.append(" (CRITICAL)");
                } else if ("3".equals(value)) {
                    buffer.append(" (MAJOR)");
                } else if ("4".equals(value)) {
                    buffer.append(" (MINOR)");
                } else if ("5".equals(value)) {
                    buffer.append(" (WARNING)");
                } else if ("6".equals(value)) {
                    buffer.append(" (CLEARED)");
                } else {
                    buffer.append(" <unknown>)");
                }
            } else if ("o".equals(pf[i].name)) {
                if ("1".equals(value)) {
                    buffer.append(" (ACKNOWLEDGED)");
                } else if ("2".equals(value)) {
                    buffer.append(" (UNACKNOWLEDGED)");
                } else {
                    buffer.append(" <unknown>)");
                }
            }
        }
        buffer.append("\n*** END ***\n");

        // Clean up
        pf = null;
        fixedHeader = null;
        notification = null;

        return buffer.toString();
    }

    /**
     * Decode UtcT time to a readable string.
     * @param utcTime time in UtcT format
     * @return string representation of the time
     */
    public static String decodeTime(UtcT utcTime) {

        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));

        cal.setTime(new Date((utcTime.time / 10000) - GREGORIAN_EPOCH_MILLIS));

        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        return timeFormat.format(cal.getTime());
    }

    /**
     * Replace in string a substring with a new substring
     *
     * @param aInput is the original String which may contain substring aOldPattern.
     * @param aOldPattern is the substring which is to be replaced
     * @param aNewPattern is the replacement for aOldPattern
     */
    public static String replace(
            final String aInput,
            final String aOldPattern,
            final String aNewPattern) {
    
        final StringBuffer result = new StringBuffer(256);
        // startIdx and idxOld delimit various chunks of aInput; these
        // chunks always end where aOldPattern begins
        int startIdx = 0;
        int idxOld = 0;

        while ((idxOld = aInput.indexOf(aOldPattern, startIdx)) >= 0) {
            // grab a part of aInput which does not include aOldPattern
            result.append(aInput.substring(startIdx, idxOld));
            // add aNewPattern to take place of aOldPattern
            result.append(aNewPattern);
    
            // reset the startIdx to just after the current match, to see
            // if there are any further matches
            startIdx = idxOld + aOldPattern.length();
        }
        // the final chunk will go to the end of aInput
        result.append(aInput.substring(startIdx));
        return result.toString();
    }
    
    protected synchronized static String getCurrentDateTime() {
        return dateFormatter.format(new Date());
    }

	/**
	 * @return the alarmID
	 */
	public String getAlarmID() {
		return alarmID;
	}

	/**
	 * @param alarmID the alarmID to set
	 */
	public void setAlarmID(String alarmID) {
		if (alarmID.startsWith("\"") && alarmID.endsWith("\"")) 
			this.alarmID = alarmID.substring(1, alarmID.length() - 1);;
	}
	
	public static String extracAlarmId(String alarmID) {
		if (alarmID.startsWith("\"") && alarmID.endsWith("\"")) 
			return alarmID.substring(1, alarmID.length() - 1);;
		return alarmID;
	}
}

