package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::DuplicateConstraintID
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/DuplicateConstraintID:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception DuplicateConstraintID {
  ...
};
 * </pre>
 */
public final class DuplicateConstraintID extends org.omg.CORBA.UserException {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = 6080986722467604449L;
public int id;

  public DuplicateConstraintID () {
    super(com.ericsson.irp.CosNotifyFilter.DuplicateConstraintIDHelper.id());
  }

  public DuplicateConstraintID (int id) {
    this();
    this.id = id;
  }

  public DuplicateConstraintID (java.lang.String _reason, int id) {
    super(com.ericsson.irp.CosNotifyFilter.DuplicateConstraintIDHelper.id() + ' ' + _reason);
    this.id = id;
  }

}
