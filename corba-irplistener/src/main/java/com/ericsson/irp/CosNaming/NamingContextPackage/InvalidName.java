package com.ericsson.irp.CosNaming.NamingContextPackage;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNaming.idl"
 * <li> <b>IDL Name</b>      ::CosNaming::NamingContext::InvalidName
 * <li> <b>Repository Id</b> IDL:omg.org/CosNaming/NamingContext/InvalidName:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception InvalidName {
  ...
};
 * </pre>
 */
public final class InvalidName extends org.omg.CORBA.UserException {

  /**
	 * 
	 */
	private static final long serialVersionUID = -5705705362504182101L;

public InvalidName () {
    super(com.ericsson.irp.CosNaming.NamingContextPackage.InvalidNameHelper.id());
  }

  public InvalidName (java.lang.String _reason) {
    super(com.ericsson.irp.CosNaming.NamingContextPackage.InvalidNameHelper.id() + ' ' + _reason);
    
  }

}
