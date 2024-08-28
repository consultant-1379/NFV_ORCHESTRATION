package com.ericsson.irp.NotificationIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPSystem::OperationNotSupported
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPSystem/OperationNotSupported:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception OperationNotSupported {
  ...
};
 * </pre>
 */
public final class OperationNotSupported extends org.omg.CORBA.UserException {

  /**
	 * 
	 */
	private static final long serialVersionUID = 7041867945411337453L;

public OperationNotSupported () {
    super(com.ericsson.irp.NotificationIRPSystem.OperationNotSupportedHelper.id());
  }

  public OperationNotSupported (java.lang.String _reason) {
    super(com.ericsson.irp.NotificationIRPSystem.OperationNotSupportedHelper.id() + ' ' + _reason);
    
  }

}
