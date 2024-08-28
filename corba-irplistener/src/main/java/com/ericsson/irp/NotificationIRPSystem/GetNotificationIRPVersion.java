package com.ericsson.irp.NotificationIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPSystem::GetNotificationIRPVersion
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPSystem/GetNotificationIRPVersion:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception GetNotificationIRPVersion {
  ...
};
 * </pre>
 */
public final class GetNotificationIRPVersion extends org.omg.CORBA.UserException {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = 732945154403036671L;
public java.lang.String reason;

  public GetNotificationIRPVersion () {
    super(com.ericsson.irp.NotificationIRPSystem.GetNotificationIRPVersionHelper.id());
  }

  public GetNotificationIRPVersion (java.lang.String reason) {
    this();
    this.reason = reason;
  }

  public GetNotificationIRPVersion (java.lang.String _reason, java.lang.String reason) {
    super(com.ericsson.irp.NotificationIRPSystem.GetNotificationIRPVersionHelper.id() + ' ' + _reason);
    this.reason = reason;
  }

}
