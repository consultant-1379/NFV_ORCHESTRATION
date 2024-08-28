
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
public class EventInformationIteratorPOATie extends EventInformationIteratorPOA {
  private com.ericsson.irp.EventIRPSystem.EventInformationIteratorOperations _delegate;
  private org.omg.PortableServer.POA _poa;

  public EventInformationIteratorPOATie (final com.ericsson.irp.EventIRPSystem.EventInformationIteratorOperations _delegate) {
    this._delegate = _delegate;
  }

  public EventInformationIteratorPOATie (final com.ericsson.irp.EventIRPSystem.EventInformationIteratorOperations _delegate, 
                              final org.omg.PortableServer.POA _poa) {
    this._delegate = _delegate;
    this._poa = _poa;
  }

  public com.ericsson.irp.EventIRPSystem.EventInformationIteratorOperations _delegate () {
    return this._delegate;
  }

  public void _delegate (final com.ericsson.irp.EventIRPSystem.EventInformationIteratorOperations the_delegate) {
    this._delegate = the_delegate;
  }

  public org.omg.PortableServer.POA _default_POA () {
    if (_poa != null) {
      return _poa;
    } 
    else {
      return super._default_POA();
    }
  }

  /**
   * <pre>
   *   void destroy ();
   * </pre>
   */
  public void destroy () {
    this._delegate.destroy();
  }

  /**
   * <pre>
   *   boolean next_eventInformations (in unsigned long how_many,
                                  out com.ericsson.irp.EventIRPConstDefs.EventInformationSeq event_informations)
    raises (com.ericsson.irp.EventIRPSystem.NextEventInformations,
            com.ericsson.irp.EventIRPSystem.InvalidParameter);
   * </pre>
   */
  public boolean next_eventInformations (int how_many, 
                                         org.omg.CosNotification.EventBatchHolder event_informations) throws  com.ericsson.irp.EventIRPSystem.NextEventInformations, 
                                                                                                              com.ericsson.irp.EventIRPSystem.InvalidParameter {
    return this._delegate.next_eventInformations(how_many, event_informations);
  }

}
