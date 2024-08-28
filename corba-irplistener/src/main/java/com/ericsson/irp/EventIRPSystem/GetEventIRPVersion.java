package com.ericsson.irp.EventIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "EventIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::EventIRPSystem::GetEventIRPVersion
 * <li> <b>Repository Id</b> IDL:EventIRPSystem/GetEventIRPVersion:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception GetEventIRPVersion {
  ...
};
 * </pre>
 */
public final class GetEventIRPVersion extends org.omg.CORBA.UserException {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = -743708348117199675L;
public java.lang.String reason;

  public GetEventIRPVersion () {
    super(com.ericsson.irp.EventIRPSystem.GetEventIRPVersionHelper.id());
  }

  public GetEventIRPVersion (java.lang.String reason) {
    this();
    this.reason = reason;
  }

  public GetEventIRPVersion (java.lang.String _reason, java.lang.String reason) {
    super(com.ericsson.irp.EventIRPSystem.GetEventIRPVersionHelper.id() + ' ' + _reason);
    this.reason = reason;
  }

}
