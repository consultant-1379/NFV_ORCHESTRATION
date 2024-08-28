package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::MappingConstraintInfo
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/MappingConstraintInfo:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * struct MappingConstraintInfo {
  ...
};
 * </pre>
 */
public final class MappingConstraintInfo implements org.omg.CORBA.portable.IDLEntity {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = -2612121017380516090L;

public com.ericsson.irp.CosNotifyFilter.ConstraintExp constraint_expression;
  
  public int constraint_id;
  
  public org.omg.CORBA.Any value;

  public MappingConstraintInfo () {
  }

  public MappingConstraintInfo (final com.ericsson.irp.CosNotifyFilter.ConstraintExp constraint_expression, 
                                final int constraint_id, 
                                final org.omg.CORBA.Any value) {
    this.constraint_expression = constraint_expression;
    this.constraint_id = constraint_id;
    this.value = value;
  }

}
