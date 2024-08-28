package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::InvalidValue
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/InvalidValue:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception InvalidValue {
  ...
};
 * </pre>
 */
public final class InvalidValue extends org.omg.CORBA.UserException {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = -3436496197463981315L;

public com.ericsson.irp.CosNotifyFilter.ConstraintExp constr;
  
  public org.omg.CORBA.Any value;

  public InvalidValue () {
    super(com.ericsson.irp.CosNotifyFilter.InvalidValueHelper.id());
  }

  public InvalidValue (com.ericsson.irp.CosNotifyFilter.ConstraintExp constr,
                       org.omg.CORBA.Any value) {
    this();
    this.constr = constr;
    this.value = value;
  }

  public InvalidValue (java.lang.String _reason, com.ericsson.irp.CosNotifyFilter.ConstraintExp constr, 
                                                 org.omg.CORBA.Any value) {
    super(com.ericsson.irp.CosNotifyFilter.InvalidValueHelper.id() + ' ' + _reason);
    this.constr = constr;
    this.value = value;
  }

}
