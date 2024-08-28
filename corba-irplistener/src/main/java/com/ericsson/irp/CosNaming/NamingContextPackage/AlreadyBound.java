package com.ericsson.irp.CosNaming.NamingContextPackage;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNaming.idl"
 * <li> <b>IDL Name</b>      ::CosNaming::NamingContext::AlreadyBound
 * <li> <b>Repository Id</b> IDL:omg.org/CosNaming/NamingContext/AlreadyBound:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception AlreadyBound {
  ...
};
 * </pre>
 */
public final class AlreadyBound extends org.omg.CORBA.UserException {

  /**
	 * 
	 */
	private static final long serialVersionUID = 5276432767003635357L;

public AlreadyBound () {
    super(com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBoundHelper.id());
  }

  public AlreadyBound (java.lang.String _reason) {
    super(com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBoundHelper.id() + ' ' + _reason);
    
  }

}
