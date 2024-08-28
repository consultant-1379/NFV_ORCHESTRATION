package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::ConstraintNotFound
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/ConstraintNotFound:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception ConstraintNotFound {
  ...
};
 * </pre>
 */
public final class ConstraintNotFound extends org.omg.CORBA.UserException {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = 8069182767013202167L;
public int id;

  public ConstraintNotFound () {
    super(com.ericsson.irp.CosNotifyFilter.ConstraintNotFoundHelper.id());
  }

  public ConstraintNotFound (int id) {
    this();
    this.id = id;
  }

  public ConstraintNotFound (java.lang.String _reason, int id) {
    super(com.ericsson.irp.CosNotifyFilter.ConstraintNotFoundHelper.id() + ' ' + _reason);
    this.id = id;
  }

}
