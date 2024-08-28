package com.ericsson.irp.EventIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "EventIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::EventIRPSystem::EventInformationIterator
 * <li> <b>Repository Id</b> IDL:EventIRPSystem/EventInformationIterator:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface EventInformationIterator {
  ...
};
 * </pre>
 */
public interface EventInformationIteratorOperations {
  /**
   * <pre>
   *   void destroy ();
   * </pre>
   */
  public void destroy ();

  /**
   * <pre>
   *   boolean next_eventInformations (in unsigned long how_many,
                                  out com.ericsson.irp.EventIRPConstDefs.EventInformationSeq event_informations)
    raises (com.ericsson.irp.EventIRPSystem.NextEventInformations,
            com.ericsson.irp.EventIRPSystem.InvalidParameter);
   * </pre>
   */
  public boolean next_eventInformations (int how_many, 
                                         org.omg.CosNotification.EventBatchHolder event_informations) throws com.ericsson.irp.EventIRPSystem.NextEventInformations, 
                                                                                                             com.ericsson.irp.EventIRPSystem.InvalidParameter;

}
