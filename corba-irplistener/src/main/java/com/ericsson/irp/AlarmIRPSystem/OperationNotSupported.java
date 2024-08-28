package com.ericsson.irp.AlarmIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPSystem::OperationNotSupported
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPSystem/OperationNotSupported:1.0
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
	private static final long serialVersionUID = -63976434619957621L;

public OperationNotSupported () {
    super(com.ericsson.irp.AlarmIRPSystem.OperationNotSupportedHelper.id());
  }

  public OperationNotSupported (java.lang.String _reason) {
    super(com.ericsson.irp.AlarmIRPSystem.OperationNotSupportedHelper.id() + ' ' + _reason);
    
  }

}
