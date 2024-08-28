package com.ericsson.irp.NotificationIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPSystem::ChangeSubscriptionFilter
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPSystem/ChangeSubscriptionFilter:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception ChangeSubscriptionFilter {
  ...
};
 * </pre>
 */
public final class ChangeSubscriptionFilter extends org.omg.CORBA.UserException {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = -8837810228386082952L;
public java.lang.String reason;

  public ChangeSubscriptionFilter () {
    super(com.ericsson.irp.NotificationIRPSystem.ChangeSubscriptionFilterHelper.id());
  }

  public ChangeSubscriptionFilter (java.lang.String reason) {
    this();
    this.reason = reason;
  }

  public ChangeSubscriptionFilter (java.lang.String _reason, java.lang.String reason) {
    super(com.ericsson.irp.NotificationIRPSystem.ChangeSubscriptionFilterHelper.id() + ' ' + _reason);
    this.reason = reason;
  }

}
