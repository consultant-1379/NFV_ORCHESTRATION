package com.ericsson.irp.EventIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "EventIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::EventIRPSystem::EventIRPOperations
 * <li> <b>Repository Id</b> IDL:EventIRPSystem/EventIRPOperations:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface _EventIRPOperations {
  ...
};
 * </pre>
 */
public interface _EventIRPOperationsOperations {
  /**
   * <pre>
   *   com.ericsson.irp.EventIRPConstDefs.VersionNumberSet get_event_IRP_version ()
    raises (com.ericsson.irp.EventIRPSystem.GetEventIRPVersion);
   * </pre>
   */
  public java.lang.String[] get_event_IRP_version () throws com.ericsson.irp.EventIRPSystem.GetEventIRPVersion;

  /**
   * <pre>
   *   com.ericsson.irp.EventIRPConstDefs.EventInformationSeq get_event_list (in string filter,
                                                                         out boolean flag,
                                                                         out com.ericsson.irp.EventIRPSystem.EventInformationIterator iter)
    raises (com.ericsson.irp.EventIRPSystem.OperationNotSupported,
            com.ericsson.irp.EventIRPSystem.ParameterNotSupported,
            com.ericsson.irp.EventIRPSystem.InvalidParameter,
            com.ericsson.irp.EventIRPSystem.GetEventList);
   * </pre>
   */
  public org.omg.CosNotification.StructuredEvent[] get_event_list (java.lang.String filter, 
                                                                   org.omg.CORBA.BooleanHolder flag, 
                                                                   com.ericsson.irp.EventIRPSystem.EventInformationIteratorHolder iter) throws com.ericsson.irp.EventIRPSystem.OperationNotSupported, 
                                                                                                                                               com.ericsson.irp.EventIRPSystem.ParameterNotSupported, 
                                                                                                                                               com.ericsson.irp.EventIRPSystem.InvalidParameter, 
                                                                                                                                               com.ericsson.irp.EventIRPSystem.GetEventList;

}
