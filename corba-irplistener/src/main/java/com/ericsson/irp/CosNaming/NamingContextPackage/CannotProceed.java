package com.ericsson.irp.CosNaming.NamingContextPackage;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNaming.idl"
 * <li> <b>IDL Name</b>      ::CosNaming::NamingContext::CannotProceed
 * <li> <b>Repository Id</b> IDL:omg.org/CosNaming/NamingContext/CannotProceed:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception CannotProceed {
  ...
};
 * </pre>
 */
public final class CannotProceed extends org.omg.CORBA.UserException {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = 3562886907426637108L;

public com.ericsson.irp.CosNaming.NamingContext cxt;
  
  public com.ericsson.irp.CosNaming.NameComponent[] rest_of_name;

  public CannotProceed () {
    super(com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceedHelper.id());
  }

  public CannotProceed (com.ericsson.irp.CosNaming.NamingContext cxt,
                        com.ericsson.irp.CosNaming.NameComponent[] rest_of_name) {
    this();
    this.cxt = cxt;
    this.rest_of_name = rest_of_name;
  }

  public CannotProceed (java.lang.String _reason, com.ericsson.irp.CosNaming.NamingContext cxt, 
                                                  com.ericsson.irp.CosNaming.NameComponent[] rest_of_name) {
    super(com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceedHelper.id() + ' ' + _reason);
    this.cxt = cxt;
    this.rest_of_name = rest_of_name;
  }

}
