package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::MappingConstraintPair
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/MappingConstraintPair:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * struct MappingConstraintPair {
  ...
};
 * </pre>
 */
public final class MappingConstraintPair implements org.omg.CORBA.portable.IDLEntity {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = -3976851543653733351L;

public com.ericsson.irp.CosNotifyFilter.ConstraintExp constraint_expression;
  
  public org.omg.CORBA.Any result_to_set;

  public MappingConstraintPair () {
  }

  public MappingConstraintPair (final com.ericsson.irp.CosNotifyFilter.ConstraintExp constraint_expression, 
                                final org.omg.CORBA.Any result_to_set) {
    this.constraint_expression = constraint_expression;
    this.result_to_set = result_to_set;
  }

}
