package com.ericsson.irp.EventIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "EventIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::EventIRPSystem::NextEventInformations
 * <li> <b>Repository Id</b> IDL:EventIRPSystem/NextEventInformations:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception NextEventInformations {
  ...
};
 * </pre>
 */
public final class NextEventInformations extends org.omg.CORBA.UserException {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = 3985790813285217865L;
public java.lang.String reason;

  public NextEventInformations () {
    super(com.ericsson.irp.EventIRPSystem.NextEventInformationsHelper.id());
  }

  public NextEventInformations (java.lang.String reason) {
    this();
    this.reason = reason;
  }

  public NextEventInformations (java.lang.String _reason, java.lang.String reason) {
    super(com.ericsson.irp.EventIRPSystem.NextEventInformationsHelper.id() + ' ' + _reason);
    this.reason = reason;
  }

}
