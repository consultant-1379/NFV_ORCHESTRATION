package com.ericsson.irp.NotificationIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPSystem::InvalidParameter
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPSystem/InvalidParameter:1.0
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
	private static final long serialVersionUID = -75579351215886774L;
public java.lang.String parameter;

  public InvalidParameter () {
    super(com.ericsson.irp.NotificationIRPSystem.InvalidParameterHelper.id());
  }

  public InvalidParameter (java.lang.String parameter) {
    this();
    this.parameter = parameter;
  }

  public InvalidParameter (java.lang.String _reason, java.lang.String parameter) {
    super(com.ericsson.irp.NotificationIRPSystem.InvalidParameterHelper.id() + ' ' + _reason);
    this.parameter = parameter;
  }

}
