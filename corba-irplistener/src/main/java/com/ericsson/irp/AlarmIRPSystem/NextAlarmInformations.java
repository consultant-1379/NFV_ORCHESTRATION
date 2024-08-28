package com.ericsson.irp.AlarmIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPSystem::NextAlarmInformations
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPSystem/NextAlarmInformations:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception NextAlarmInformations {
  ...
};
 * </pre>
 */
public final class NextAlarmInformations extends org.omg.CORBA.UserException {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = -6378334124619285220L;
public java.lang.String reason;

  public NextAlarmInformations () {
    super(com.ericsson.irp.AlarmIRPSystem.NextAlarmInformationsHelper.id());
  }

  public NextAlarmInformations (java.lang.String reason) {
    this();
    this.reason = reason;
  }

  public NextAlarmInformations (java.lang.String _reason, java.lang.String reason) {
    super(com.ericsson.irp.AlarmIRPSystem.NextAlarmInformationsHelper.id() + ' ' + _reason);
    this.reason = reason;
  }

}
