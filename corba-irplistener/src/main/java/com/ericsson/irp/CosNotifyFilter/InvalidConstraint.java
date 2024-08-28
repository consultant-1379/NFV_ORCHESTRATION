package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::InvalidConstraint
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/InvalidConstraint:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception InvalidConstraint {
  ...
};
 * </pre>
 */
public final class InvalidConstraint extends org.omg.CORBA.UserException {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = -8660658111356305564L;
public com.ericsson.irp.CosNotifyFilter.ConstraintExp constr;

  public InvalidConstraint () {
    super(com.ericsson.irp.CosNotifyFilter.InvalidConstraintHelper.id());
  }

  public InvalidConstraint (com.ericsson.irp.CosNotifyFilter.ConstraintExp constr) {
    this();
    this.constr = constr;
  }

  public InvalidConstraint (java.lang.String _reason, com.ericsson.irp.CosNotifyFilter.ConstraintExp constr) {
    super(com.ericsson.irp.CosNotifyFilter.InvalidConstraintHelper.id() + ' ' + _reason);
    this.constr = constr;
  }

}
