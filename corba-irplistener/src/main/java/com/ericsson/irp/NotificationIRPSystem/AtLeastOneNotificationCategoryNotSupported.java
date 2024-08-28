package com.ericsson.irp.NotificationIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPSystem::AtLeastOneNotificationCategoryNotSupported
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPSystem/AtLeastOneNotificationCategoryNotSupported:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception AtLeastOneNotificationCategoryNotSupported {
  ...
};
 * </pre>
 */
public final class AtLeastOneNotificationCategoryNotSupported extends org.omg.CORBA.UserException {

  /**
	 * 
	 */
	private static final long serialVersionUID = -2333219259751544406L;

public AtLeastOneNotificationCategoryNotSupported () {
    super(com.ericsson.irp.NotificationIRPSystem.AtLeastOneNotificationCategoryNotSupportedHelper.id());
  }

  public AtLeastOneNotificationCategoryNotSupported (java.lang.String _reason) {
    super(com.ericsson.irp.NotificationIRPSystem.AtLeastOneNotificationCategoryNotSupportedHelper.id() + ' ' + _reason);
    
  }

}
