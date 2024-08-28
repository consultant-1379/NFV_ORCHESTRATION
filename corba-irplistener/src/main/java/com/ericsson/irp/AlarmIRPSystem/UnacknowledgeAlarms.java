package com.ericsson.irp.AlarmIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPSystem::UnacknowledgeAlarms
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPSystem/UnacknowledgeAlarms:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception UnacknowledgeAlarms {
  ...
};
 * </pre>
 */
public final class UnacknowledgeAlarms extends org.omg.CORBA.UserException {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = -5063280456981987353L;
public java.lang.String reason;

  public UnacknowledgeAlarms () {
    super(com.ericsson.irp.AlarmIRPSystem.UnacknowledgeAlarmsHelper.id());
  }

  public UnacknowledgeAlarms (java.lang.String reason) {
    this();
    this.reason = reason;
  }

  public UnacknowledgeAlarms (java.lang.String _reason, java.lang.String reason) {
    super(com.ericsson.irp.AlarmIRPSystem.UnacknowledgeAlarmsHelper.id() + ' ' + _reason);
    this.reason = reason;
  }

}
