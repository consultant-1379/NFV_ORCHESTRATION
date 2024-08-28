package com.ericsson.irp.AlarmIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPSystem::ParameterNotSupported
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPSystem/ParameterNotSupported:1.0
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
	private static final long serialVersionUID = 1952041156893255716L;
public java.lang.String parameter;

  public ParameterNotSupported () {
    super(com.ericsson.irp.AlarmIRPSystem.ParameterNotSupportedHelper.id());
  }

  public ParameterNotSupported (java.lang.String parameter) {
    this();
    this.parameter = parameter;
  }

  public ParameterNotSupported (java.lang.String _reason, java.lang.String parameter) {
    super(com.ericsson.irp.AlarmIRPSystem.ParameterNotSupportedHelper.id() + ' ' + _reason);
    this.parameter = parameter;
  }

}
