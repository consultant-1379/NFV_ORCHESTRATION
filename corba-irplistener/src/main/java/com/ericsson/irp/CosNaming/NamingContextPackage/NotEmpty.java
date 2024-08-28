package com.ericsson.irp.CosNaming.NamingContextPackage;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNaming.idl"
 * <li> <b>IDL Name</b>      ::CosNaming::NamingContext::NotEmpty
 * <li> <b>Repository Id</b> IDL:omg.org/CosNaming/NamingContext/NotEmpty:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception NotEmpty {
  ...
};
 * </pre>
 */
public final class NotEmpty extends org.omg.CORBA.UserException {

  /**
	 * 
	 */
	private static final long serialVersionUID = -1326422168440289990L;

public NotEmpty () {
    super(com.ericsson.irp.CosNaming.NamingContextPackage.NotEmptyHelper.id());
  }

  public NotEmpty (java.lang.String _reason) {
    super(com.ericsson.irp.CosNaming.NamingContextPackage.NotEmptyHelper.id() + ' ' + _reason);
    
  }

}
