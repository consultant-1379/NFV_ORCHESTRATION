package com.ericsson.irp.NotificationIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPSystem::GetSubscriptionStatus
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPSystem/GetSubscriptionStatus:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception GetSubscriptionStatus {
  ...
};
 * </pre>
 */
public final class GetSubscriptionStatus extends org.omg.CORBA.UserException {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = -5033485805598993477L;
public java.lang.String reason;

  public GetSubscriptionStatus () {
    super(com.ericsson.irp.NotificationIRPSystem.GetSubscriptionStatusHelper.id());
  }

  public GetSubscriptionStatus (java.lang.String reason) {
    this();
    this.reason = reason;
  }

  public GetSubscriptionStatus (java.lang.String _reason, java.lang.String reason) {
    super(com.ericsson.irp.NotificationIRPSystem.GetSubscriptionStatusHelper.id() + ' ' + _reason);
    this.reason = reason;
  }

}
