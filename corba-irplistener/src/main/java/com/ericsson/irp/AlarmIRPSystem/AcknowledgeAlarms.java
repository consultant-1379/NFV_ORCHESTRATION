package com.ericsson.irp.AlarmIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPSystem::AcknowledgeAlarms
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPSystem/AcknowledgeAlarms:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception AcknowledgeAlarms {
  ...
};
 * </pre>
 */
public final class AcknowledgeAlarms extends org.omg.CORBA.UserException {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = 1752367901677092265L;
public java.lang.String reason;

  public AcknowledgeAlarms () {
    super(com.ericsson.irp.AlarmIRPSystem.AcknowledgeAlarmsHelper.id());
  }

  public AcknowledgeAlarms (java.lang.String reason) {
    this();
    this.reason = reason;
  }

  public AcknowledgeAlarms (java.lang.String _reason, java.lang.String reason) {
    super(com.ericsson.irp.AlarmIRPSystem.AcknowledgeAlarmsHelper.id() + ' ' + _reason);
    this.reason = reason;
  }

}
