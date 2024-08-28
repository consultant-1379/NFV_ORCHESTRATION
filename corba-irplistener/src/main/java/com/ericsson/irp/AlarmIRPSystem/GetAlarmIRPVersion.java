package com.ericsson.irp.AlarmIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPSystem::GetAlarmIRPVersion
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPSystem/GetAlarmIRPVersion:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception GetAlarmIRPVersion {
  ...
};
 * </pre>
 */
public final class GetAlarmIRPVersion extends org.omg.CORBA.UserException {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = 1314591601292929902L;
public java.lang.String reason;

  public GetAlarmIRPVersion () {
    super(com.ericsson.irp.AlarmIRPSystem.GetAlarmIRPVersionHelper.id());
  }

  public GetAlarmIRPVersion (java.lang.String reason) {
    this();
    this.reason = reason;
  }

  public GetAlarmIRPVersion (java.lang.String _reason, java.lang.String reason) {
    super(com.ericsson.irp.AlarmIRPSystem.GetAlarmIRPVersionHelper.id() + ' ' + _reason);
    this.reason = reason;
  }

}
