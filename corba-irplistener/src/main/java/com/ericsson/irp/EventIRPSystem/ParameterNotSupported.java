package com.ericsson.irp.EventIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "EventIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::EventIRPSystem::ParameterNotSupported
 * <li> <b>Repository Id</b> IDL:EventIRPSystem/ParameterNotSupported:1.0
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
	private static final long serialVersionUID = 7175784714070695832L;
public java.lang.String parameter;

  public ParameterNotSupported () {
    super(com.ericsson.irp.EventIRPSystem.ParameterNotSupportedHelper.id());
  }

  public ParameterNotSupported (java.lang.String parameter) {
    this();
    this.parameter = parameter;
  }

  public ParameterNotSupported (java.lang.String _reason, java.lang.String parameter) {
    super(com.ericsson.irp.EventIRPSystem.ParameterNotSupportedHelper.id() + ' ' + _reason);
    this.parameter = parameter;
  }

}
