package com.ericsson.irp.EventIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "EventIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::EventIRPSystem::InvalidParameter
 * <li> <b>Repository Id</b> IDL:EventIRPSystem/InvalidParameter:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception InvalidParameter {
  ...
};
 * </pre>
 */
public final class InvalidParameter extends org.omg.CORBA.UserException {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = -439300493826392674L;
public java.lang.String parameter;

  public InvalidParameter () {
    super(com.ericsson.irp.EventIRPSystem.InvalidParameterHelper.id());
  }

  public InvalidParameter (java.lang.String parameter) {
    this();
    this.parameter = parameter;
  }

  public InvalidParameter (java.lang.String _reason, java.lang.String parameter) {
    super(com.ericsson.irp.EventIRPSystem.InvalidParameterHelper.id() + ' ' + _reason);
    this.parameter = parameter;
  }

}
