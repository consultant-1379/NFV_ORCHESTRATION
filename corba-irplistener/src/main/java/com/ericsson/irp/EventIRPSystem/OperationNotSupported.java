package com.ericsson.irp.EventIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "EventIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::EventIRPSystem::OperationNotSupported
 * <li> <b>Repository Id</b> IDL:EventIRPSystem/OperationNotSupported:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception OperationNotSupported {
  ...
};
 * </pre>
 */
public final class OperationNotSupported extends org.omg.CORBA.UserException {

  /**
	 * 
	 */
	private static final long serialVersionUID = -6147177133146291577L;

public OperationNotSupported () {
    super(com.ericsson.irp.EventIRPSystem.OperationNotSupportedHelper.id());
  }

  public OperationNotSupported (java.lang.String _reason) {
    super(com.ericsson.irp.EventIRPSystem.OperationNotSupportedHelper.id() + ' ' + _reason);
    
  }

}
