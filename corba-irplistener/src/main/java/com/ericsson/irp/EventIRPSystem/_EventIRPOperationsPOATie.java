
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
public class _EventIRPOperationsPOATie extends _EventIRPOperationsPOA {
  private com.ericsson.irp.EventIRPSystem._EventIRPOperationsOperations _delegate;
  private org.omg.PortableServer.POA _poa;

  public _EventIRPOperationsPOATie (final com.ericsson.irp.EventIRPSystem._EventIRPOperationsOperations _delegate) {
    this._delegate = _delegate;
  }

  public _EventIRPOperationsPOATie (final com.ericsson.irp.EventIRPSystem._EventIRPOperationsOperations _delegate, 
                              final org.omg.PortableServer.POA _poa) {
    this._delegate = _delegate;
    this._poa = _poa;
  }

  public com.ericsson.irp.EventIRPSystem._EventIRPOperationsOperations _delegate () {
    return this._delegate;
  }

  public void _delegate (final com.ericsson.irp.EventIRPSystem._EventIRPOperationsOperations the_delegate) {
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
   *   com.ericsson.irp.EventIRPConstDefs.VersionNumberSet get_event_IRP_version ()
    raises (com.ericsson.irp.EventIRPSystem.GetEventIRPVersion);
   * </pre>
   */
  public java.lang.String[] get_event_IRP_version () throws  com.ericsson.irp.EventIRPSystem.GetEventIRPVersion {
    return this._delegate.get_event_IRP_version();
  }

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
                                                                   com.ericsson.irp.EventIRPSystem.EventInformationIteratorHolder iter) throws  com.ericsson.irp.EventIRPSystem.OperationNotSupported, 
                                                                                                                                                com.ericsson.irp.EventIRPSystem.ParameterNotSupported, 
                                                                                                                                                com.ericsson.irp.EventIRPSystem.InvalidParameter, 
                                                                                                                                                com.ericsson.irp.EventIRPSystem.GetEventList {
    return this._delegate.get_event_list(filter, flag, iter);
  }

}
