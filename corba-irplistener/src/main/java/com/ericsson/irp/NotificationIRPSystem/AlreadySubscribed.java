package com.ericsson.irp.NotificationIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPSystem::AlreadySubscribed
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPSystem/AlreadySubscribed:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception AlreadySubscribed {
  ...
};
 * </pre>
 */
public final class AlreadySubscribed extends org.omg.CORBA.UserException {

  /**
	 * 
	 */
	private static final long serialVersionUID = 6562564334284130984L;

public AlreadySubscribed () {
    super(com.ericsson.irp.NotificationIRPSystem.AlreadySubscribedHelper.id());
  }

  public AlreadySubscribed (java.lang.String _reason) {
    super(com.ericsson.irp.NotificationIRPSystem.AlreadySubscribedHelper.id() + ' ' + _reason);
    
  }

}
