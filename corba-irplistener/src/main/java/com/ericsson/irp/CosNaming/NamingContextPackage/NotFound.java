package com.ericsson.irp.CosNaming.NamingContextPackage;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNaming.idl"
 * <li> <b>IDL Name</b>      ::CosNaming::NamingContext::NotFound
 * <li> <b>Repository Id</b> IDL:omg.org/CosNaming/NamingContext/NotFound:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception NotFound {
  ...
};
 * </pre>
 */
public final class NotFound extends org.omg.CORBA.UserException {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = 4856706035606263743L;

public com.ericsson.irp.CosNaming.NamingContextPackage.NotFoundReason why;
  
  public com.ericsson.irp.CosNaming.NameComponent[] rest_of_name;

  public NotFound () {
    super(com.ericsson.irp.CosNaming.NamingContextPackage.NotFoundHelper.id());
  }

  public NotFound (com.ericsson.irp.CosNaming.NamingContextPackage.NotFoundReason why,
                   com.ericsson.irp.CosNaming.NameComponent[] rest_of_name) {
    this();
    this.why = why;
    this.rest_of_name = rest_of_name;
  }

  public NotFound (java.lang.String _reason, com.ericsson.irp.CosNaming.NamingContextPackage.NotFoundReason why, 
                                             com.ericsson.irp.CosNaming.NameComponent[] rest_of_name) {
    super(com.ericsson.irp.CosNaming.NamingContextPackage.NotFoundHelper.id() + ' ' + _reason);
    this.why = why;
    this.rest_of_name = rest_of_name;
  }

}
