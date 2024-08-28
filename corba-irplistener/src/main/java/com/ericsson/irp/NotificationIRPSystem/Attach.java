package com.ericsson.irp.NotificationIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPSystem::Attach
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPSystem/Attach:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception Attach {
  ...
};
 * </pre>
 */
public final class Attach extends org.omg.CORBA.UserException {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = 7906798628697907204L;
public java.lang.String reason;

  public Attach () {
    super(com.ericsson.irp.NotificationIRPSystem.AttachHelper.id());
  }

  public Attach (java.lang.String reason) {
    this();
    this.reason = reason;
  }

  public Attach (java.lang.String _reason, java.lang.String reason) {
    super(com.ericsson.irp.NotificationIRPSystem.AttachHelper.id() + ' ' + _reason);
    this.reason = reason;
  }

}
