package com.ericsson.irp.NotificationIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPSystem::DetachException
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPSystem/DetachException:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception DetachException {
  ...
};
 * </pre>
 */
public final class DetachException extends org.omg.CORBA.UserException {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = -6683433783800881331L;
public java.lang.String reason;

  public DetachException () {
    super(com.ericsson.irp.NotificationIRPSystem.DetachExceptionHelper.id());
  }

  public DetachException (java.lang.String reason) {
    this();
    this.reason = reason;
  }

  public DetachException (java.lang.String _reason, java.lang.String reason) {
    super(com.ericsson.irp.NotificationIRPSystem.DetachExceptionHelper.id() + ' ' + _reason);
    this.reason = reason;
  }

}
