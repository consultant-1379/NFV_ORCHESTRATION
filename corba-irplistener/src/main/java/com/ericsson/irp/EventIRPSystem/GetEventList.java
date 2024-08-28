package com.ericsson.irp.EventIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "EventIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::EventIRPSystem::GetEventList
 * <li> <b>Repository Id</b> IDL:EventIRPSystem/GetEventList:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception GetEventList {
  ...
};
 * </pre>
 */
public final class GetEventList extends org.omg.CORBA.UserException {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = -414698733049443292L;
public java.lang.String reason;

  public GetEventList () {
    super(com.ericsson.irp.EventIRPSystem.GetEventListHelper.id());
  }

  public GetEventList (java.lang.String reason) {
    this();
    this.reason = reason;
  }

  public GetEventList (java.lang.String _reason, java.lang.String reason) {
    super(com.ericsson.irp.EventIRPSystem.GetEventListHelper.id() + ' ' + _reason);
    this.reason = reason;
  }

}
