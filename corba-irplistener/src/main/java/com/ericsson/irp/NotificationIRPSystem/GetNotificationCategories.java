package com.ericsson.irp.NotificationIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPSystem::GetNotificationCategories
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPSystem/GetNotificationCategories:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception GetNotificationCategories {
  ...
};
 * </pre>
 */
public final class GetNotificationCategories extends org.omg.CORBA.UserException {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = 7001131429529411819L;
public java.lang.String reason;

  public GetNotificationCategories () {
    super(com.ericsson.irp.NotificationIRPSystem.GetNotificationCategoriesHelper.id());
  }

  public GetNotificationCategories (java.lang.String reason) {
    this();
    this.reason = reason;
  }

  public GetNotificationCategories (java.lang.String _reason, java.lang.String reason) {
    super(com.ericsson.irp.NotificationIRPSystem.GetNotificationCategoriesHelper.id() + ' ' + _reason);
    this.reason = reason;
  }

}
