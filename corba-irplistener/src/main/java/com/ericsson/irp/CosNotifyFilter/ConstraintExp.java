package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::ConstraintExp
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/ConstraintExp:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * struct ConstraintExp {
  ...
};
 * </pre>
 */
public final class ConstraintExp implements org.omg.CORBA.portable.IDLEntity {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = 5881276183079882113L;

public org.omg.CosNotification.EventType[] event_types;
  
  public java.lang.String constraint_expr;

  public ConstraintExp () {
    constraint_expr = "";
  }

  public ConstraintExp (final org.omg.CosNotification.EventType[] event_types, 
                        final java.lang.String constraint_expr) {
    this.event_types = event_types;
    this.constraint_expr = constraint_expr;
  }

}
