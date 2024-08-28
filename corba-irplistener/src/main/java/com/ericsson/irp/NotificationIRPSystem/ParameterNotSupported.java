package com.ericsson.irp.NotificationIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPSystem::ParameterNotSupported
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPSystem/ParameterNotSupported:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception ParameterNotSupported {
  ...
};
 * </pre>
 */
public final class ParameterNotSupported extends org.omg.CORBA.UserException {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = -3728492139335583164L;
public java.lang.String parameter;

  public ParameterNotSupported () {
    super(com.ericsson.irp.NotificationIRPSystem.ParameterNotSupportedHelper.id());
  }

  public ParameterNotSupported (java.lang.String parameter) {
    this();
    this.parameter = parameter;
  }

  public ParameterNotSupported (java.lang.String _reason, java.lang.String parameter) {
    super(com.ericsson.irp.NotificationIRPSystem.ParameterNotSupportedHelper.id() + ' ' + _reason);
    this.parameter = parameter;
  }

}
