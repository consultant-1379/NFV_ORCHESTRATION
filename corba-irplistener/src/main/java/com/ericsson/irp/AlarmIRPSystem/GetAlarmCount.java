package com.ericsson.irp.AlarmIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPSystem::GetAlarmCount
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPSystem/GetAlarmCount:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception GetAlarmCount {
  ...
};
 * </pre>
 */
public final class GetAlarmCount extends org.omg.CORBA.UserException {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = 6744633447505485691L;
public java.lang.String reason;

  public GetAlarmCount () {
    super(com.ericsson.irp.AlarmIRPSystem.GetAlarmCountHelper.id());
  }

  public GetAlarmCount (java.lang.String reason) {
    this();
    this.reason = reason;
  }

  public GetAlarmCount (java.lang.String _reason, java.lang.String reason) {
    super(com.ericsson.irp.AlarmIRPSystem.GetAlarmCountHelper.id() + ' ' + _reason);
    this.reason = reason;
  }

}
