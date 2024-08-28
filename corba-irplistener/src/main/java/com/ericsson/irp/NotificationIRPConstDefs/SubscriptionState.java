package com.ericsson.irp.NotificationIRPConstDefs;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPConstDefs.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPConstDefs::SubscriptionState
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPConstDefs/SubscriptionState:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * enum SubscriptionState {Inactive, Active, DontKnow};
 * </pre>
 */
public final class SubscriptionState implements org.omg.CORBA.portable.IDLEntity {
  /**
	 * 
	 */
	private static final long serialVersionUID = -3373684715312156337L;


private /* [JDK 1.1.x BUG] final */ int __value;

  
  public static final int _Inactive = 0;
  
  public static final int _Active = 1;
  
  public static final int _DontKnow = 2;

  
  public static final SubscriptionState Inactive = new SubscriptionState(_Inactive);
  
  public static final SubscriptionState Active = new SubscriptionState(_Active);
  
  public static final SubscriptionState DontKnow = new SubscriptionState(_DontKnow);

  protected SubscriptionState (final int _vis_value) {
    this.__value = _vis_value;
  }

  public int value () {
    return __value;
  }

  public static SubscriptionState from_int (final int _vis_value) {
    switch (_vis_value) {
      case 0: return Inactive;
      case 1: return Active;
      case 2: return DontKnow;
      default: throw new org.omg.CORBA.BAD_PARAM();
    }
  }

}
