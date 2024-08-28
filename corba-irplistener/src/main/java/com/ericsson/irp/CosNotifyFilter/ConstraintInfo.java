package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::ConstraintInfo
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/ConstraintInfo:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * struct ConstraintInfo {
  ...
};
 * </pre>
 */
public final class ConstraintInfo implements org.omg.CORBA.portable.IDLEntity {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = -2865547974713925936L;

public com.ericsson.irp.CosNotifyFilter.ConstraintExp constraint_expression;
  
  public int constraint_id;

  public ConstraintInfo () {
  }

  public ConstraintInfo (final com.ericsson.irp.CosNotifyFilter.ConstraintExp constraint_expression, 
                         final int constraint_id) {
    this.constraint_expression = constraint_expression;
    this.constraint_id = constraint_id;
  }

}
