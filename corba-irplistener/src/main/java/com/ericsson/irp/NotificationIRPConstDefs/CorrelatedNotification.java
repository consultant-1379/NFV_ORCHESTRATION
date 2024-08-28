package com.ericsson.irp.NotificationIRPConstDefs;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPConstDefs.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPConstDefs::CorrelatedNotification
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPConstDefs/CorrelatedNotification:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * struct CorrelatedNotification {
  ...
};
 * </pre>
 */
public final class CorrelatedNotification implements org.omg.CORBA.portable.IDLEntity {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = 2779446501186608058L;

public java.lang.String source;
  
  public int[] notifIDSet;

  public CorrelatedNotification () {
    source = "";
  }

  public CorrelatedNotification (final java.lang.String source, 
                                 final int[] notifIDSet) {
    this.source = source;
    this.notifIDSet = notifIDSet;
  }

}
