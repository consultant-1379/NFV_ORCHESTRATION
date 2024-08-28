package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::InvalidEventType
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/InvalidEventType:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception InvalidEventType {
  ...
};
 * </pre>
 */
public final class InvalidEventType extends org.omg.CORBA.UserException {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = 939712234745663975L;
public org.omg.CosNotification.EventType type;

  public InvalidEventType () {
    super(com.ericsson.irp.CosNotifyComm.InvalidEventTypeHelper.id());
  }

  public InvalidEventType (org.omg.CosNotification.EventType type) {
    this();
    this.type = type;
  }

  public InvalidEventType (java.lang.String _reason, org.omg.CosNotification.EventType type) {
    super(com.ericsson.irp.CosNotifyComm.InvalidEventTypeHelper.id() + ' ' + _reason);
    this.type = type;
  }

}
