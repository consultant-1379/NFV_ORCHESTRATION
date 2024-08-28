package com.ericsson.irp.NotificationIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPSystem::GetSubscriptionIds
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPSystem/GetSubscriptionIds:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception GetSubscriptionIds {
  ...
};
 * </pre>
 */
public final class GetSubscriptionIds extends org.omg.CORBA.UserException {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = -251880274535874618L;
public java.lang.String reason;

  public GetSubscriptionIds () {
    super(com.ericsson.irp.NotificationIRPSystem.GetSubscriptionIdsHelper.id());
  }

  public GetSubscriptionIds (java.lang.String reason) {
    this();
    this.reason = reason;
  }

  public GetSubscriptionIds (java.lang.String _reason, java.lang.String reason) {
    super(com.ericsson.irp.NotificationIRPSystem.GetSubscriptionIdsHelper.id() + ' ' + _reason);
    this.reason = reason;
  }

}
