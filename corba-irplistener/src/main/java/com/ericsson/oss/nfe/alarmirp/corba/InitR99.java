/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2012
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.oss.nfe.alarmirp.corba;

import org.omg.CORBA.BooleanHolder;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CosNotification.StructuredEvent;

import com.ericsson.irp.AlarmIRPConstDefs.AlarmInformationIdSeqHolder;
import com.ericsson.irp.AlarmIRPSystem.*;
import com.ericsson.irp.CommonIRPConstDefs.Signal;
import com.ericsson.irp.EventIRPConstDefs.NOTIFY_EVENT;
import com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperations;
import com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperationsHelper;

/**
 * Sequence Push Consumer for Alarm IRP release 99 structured events. It also
 * subscribe for Event IRP structured events.
 */
public class InitR99 extends BasePOA {

	private String subId = "";
	private org.omg.CORBA.Object obj = null;
	private _NotificationIRPOperations neOp = null;
	private _AlarmIRPOperations aOp = null;
	private org.omg.CORBA.Object corbaObjectNotif = null;
	private org.omg.CORBA.Object corbaObjectAlarm = null;
	protected boolean onceAttached = false;
	protected boolean isConnected = false;
	private ORBUtil orbUtil;

	public InitR99(String filter, ORBUtil orbUtil) {
		super(filter);
		this.orbUtil = orbUtil;
	}

	public boolean attach(String notifPath) {
		String[] cats = new String[1];

		if (!this.onceAttached) {
			try {
				// Resolving NotificationIRP

				System.out.println("Resolving NotifIRP : " + notifPath);
				corbaObjectNotif = NameServiceHelper.resolve(orbUtil.getOrb(),
						notifPath);
				System.out.println("corbaObjectNotif=" + corbaObjectNotif);
				System.out.println("class: "
						+ corbaObjectNotif.getClass().getName());
				neOp = _NotificationIRPOperationsHelper
						.narrow(corbaObjectNotif);
				if (neOp != null) {
					System.out.println("Found NotifIRP Interface.");
				} else {
					System.out
							.println("NotifIRP Interface not found, exiting.. ");
					return false;
				}

				// Activating POA
				System.out.println("Activating POA.");
				rootPOA = orbUtil.getRootPOA();

				obj = getNewPOAObject();
				this.onceAttached = true;
			} catch (Exception e) {
				System.out.println("Exception = " + e);
				e.printStackTrace();
				this.onceAttached = false;
				return false;
			}
		}

		// Attaching to NS
		if (this.onceAttached) {
			System.out.println("Attaching to NS. NeObj:" + neOp.toString());
		} else {
			System.out.println("Re-attaching to NS. NeObj:" + neOp.toString());
		}

		cats[0] = "1f1"; // Alarm IRP
		//cats[1] = "1z1"; // Event IRP
		System.out.println("category[0]=" + cats[0] + ", category[1]="
				+ cats[0]);
		System.out.println("filter=" + this.filterString);
		
		System.out.println("obj : "+obj);
		try {
			subId = neOp.attach_push(obj, 0, cats, this.filterString);
			System.out.println("First attempt at attach_push()");
			System.out.println("Subscription id = " + subId);
			isConnected = true;
			return true;
		} catch (Exception e) {
			try {
				obj = getNewPOAObject();
				subId = neOp.attach_push(obj, 0, cats, this.filterString);
				System.out.println("Second attempt at attach_push()");
				System.out.println("Subscription id = " + subId);
				isConnected = true;
				return true;
			} catch (Exception e1) {
				e.printStackTrace();
				System.out
						.println("Exception occured while performing attach_push()"
								+ e1.getMessage());
				isConnected = false;
				return false;
			}
		}
	}

	private org.omg.CORBA.Object getNewPOAObject() throws Exception {
		rootPOA.the_POAManager().activate();
		rootPOA.activate_object(this);
		return rootPOA.servant_to_reference(this);
	}

	public synchronized void disconnect_structured_push_consumer() {

		if (isConnected) {
			System.out.println("Detaching " + subId
					+ " from Notification Service.");
			try {
				neOp.detach(obj, subId);
			} catch (Exception e) {
				System.out.println("Disconnection failed. Exception: "
						+ e.getMessage());
			}
			System.out.println("Disconnected from Notification Service.");
			isConnected = false;
		} else
			System.out.println("Not connected to Notification Service.");
	}

	private _AlarmIRPOperations getAlarm_IOR(String alarmPath) {

		// Resolving AlarmIRP

		System.out.println("Resolving AlarmIRP : " + alarmPath);
		try {
			//System.out.println("orb is----"+orbUtil.getOrb());
			corbaObjectAlarm = NameServiceHelper.resolve(orbUtil.getOrb(),
					alarmPath);
			//System.out.println("corbaobjectalarm is----"+corbaObjectAlarm);
			aOp = _AlarmIRPOperationsHelper.narrow(corbaObjectAlarm);
			
		} catch (NotFound | CannotProceed | InvalidName e) {
			e.printStackTrace();
		}
		if (aOp != null) {
			System.out.println("Found AlarmIRP Interface...");
		} else {
			System.out.println("AlarmIRP Interface not found, exiting.. ");
			return null;
		}

		return aOp;
	}

	public void synchronize(String alarmPath, String filter) {
		BooleanHolder flag = new BooleanHolder();
		AlarmInformationIteratorHolder iter = new AlarmInformationIteratorHolder();
		try {
			//System.out.println("Filter "+filter);
			StructuredEvent[] sEvent=getAlarm_IOR(alarmPath).get_alarm_list(filter, flag, iter);
			String tmpString = "";
            
			for(int i=0;i<sEvent.length;i++){
			//tmpString=tr.translateEvent(sEvent[i]);
			System.out.println(tmpString);

			if (sEvent[i].header.fixed_header.event_name.equals(NOTIFY_EVENT.value)) {
				System.out.print("Event");
			} else {
				System.out.print("Alarm");
			}
		}
			
		} catch (GetAlarmList | ParameterNotSupported | InvalidParameter e) {
			e.printStackTrace();
		}

	}

	public boolean acknowledge(boolean ack, String alarmPath,
			String[] id, String user) {
		Signal operationStatus;

		AlarmInformationIdSeqHolder bad_alarm_information_id_list = new AlarmInformationIdSeqHolder();
		try {
			
			if(ack){
				
				
				_AlarmIRPOperations operations = getAlarm_IOR(alarmPath);
				
				operationStatus=operations.acknowledge_alarms(id, user,
						"", bad_alarm_information_id_list);
				if(Signal.Failure.value()==operationStatus.value()){
					
                     System.out.println("Ack operation is failed returning Signal FAILURE ");						
					
					
				}else{
					System.out.println("Ack Operation is performed on all Ids successfully with Signal OK");
				}
			}else{
				
				
				operationStatus=getAlarm_IOR(alarmPath).unacknowledge_alarms(id, user,
						"", bad_alarm_information_id_list);
				
				if(Signal.Failure.value()==operationStatus.value()){
					
					System.out.println("UNAck operation is failed returning Signal FAILURE ");
				}else{
					System.out.println("UNAck Operation is performed on all Ids successfully with Signal OK");
				}
			}
			
		} catch (AcknowledgeAlarms | ParameterNotSupported | InvalidParameter | UnacknowledgeAlarms | OperationNotSupported e) {
			e.printStackTrace();
		}

		return true;
	}

}
