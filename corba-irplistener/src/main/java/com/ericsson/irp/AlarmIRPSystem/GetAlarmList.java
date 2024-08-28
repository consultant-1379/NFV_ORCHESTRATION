package com.ericsson.irp.AlarmIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPSystem::GetAlarmList
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPSystem/GetAlarmList:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception GetAlarmList {
  ...
};
 * </pre>
 */
public final class GetAlarmList extends org.omg.CORBA.UserException {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = -5977922062371980809L;
public java.lang.String reason;

  public GetAlarmList () {
    super(com.ericsson.irp.AlarmIRPSystem.GetAlarmListHelper.id());
  }

  public GetAlarmList (java.lang.String reason) {
    this();
    this.reason = reason;
  }

  public GetAlarmList (java.lang.String _reason, java.lang.String reason) {
    super(com.ericsson.irp.AlarmIRPSystem.GetAlarmListHelper.id() + ' ' + _reason);
    this.reason = reason;
  }

}
