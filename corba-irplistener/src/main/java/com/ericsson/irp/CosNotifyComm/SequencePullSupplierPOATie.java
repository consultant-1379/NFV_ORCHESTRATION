
package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::SequencePullSupplier
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/SequencePullSupplier:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface SequencePullSupplier : com.ericsson.irp.CosNotifyComm.NotifySubscribe {
  ...
};
 * </pre>
 */
public class SequencePullSupplierPOATie extends SequencePullSupplierPOA {
  private com.ericsson.irp.CosNotifyComm.SequencePullSupplierOperations _delegate;
  private org.omg.PortableServer.POA _poa;

  public SequencePullSupplierPOATie (final com.ericsson.irp.CosNotifyComm.SequencePullSupplierOperations _delegate) {
    this._delegate = _delegate;
  }

  public SequencePullSupplierPOATie (final com.ericsson.irp.CosNotifyComm.SequencePullSupplierOperations _delegate, 
                              final org.omg.PortableServer.POA _poa) {
    this._delegate = _delegate;
    this._poa = _poa;
  }

  public com.ericsson.irp.CosNotifyComm.SequencePullSupplierOperations _delegate () {
    return this._delegate;
  }

  public void _delegate (final com.ericsson.irp.CosNotifyComm.SequencePullSupplierOperations the_delegate) {
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
   *   void disconnect_sequence_pull_supplier ();
   * </pre>
   */
  public void disconnect_sequence_pull_supplier () {
    this._delegate.disconnect_sequence_pull_supplier();
  }

  /**
   * <pre>
   *   org.omg.CosNotification.EventBatch try_pull_structured_events (in long max_number,
                                                                 out boolean has_event)
    raises (com.ericsson.irp.CosEventComm.Disconnected);
   * </pre>
   */
  public org.omg.CosNotification.StructuredEvent[] try_pull_structured_events (int max_number, 
                                                                               org.omg.CORBA.BooleanHolder has_event) throws  com.ericsson.irp.CosEventComm.Disconnected {
    return this._delegate.try_pull_structured_events(max_number, has_event);
  }

  /**
   * <pre>
   *   org.omg.CosNotification.EventBatch pull_structured_events (in long max_number)
    raises (com.ericsson.irp.CosEventComm.Disconnected);
   * </pre>
   */
  public org.omg.CosNotification.StructuredEvent[] pull_structured_events (int max_number) throws  com.ericsson.irp.CosEventComm.Disconnected {
    return this._delegate.pull_structured_events(max_number);
  }

  /**
   * <pre>
   *   void subscription_change (in org.omg.CosNotification.EventTypeSeq added,
                            in org.omg.CosNotification.EventTypeSeq removed)
    raises (com.ericsson.irp.CosNotifyComm.InvalidEventType);
   * </pre>
   */
  public void subscription_change (org.omg.CosNotification.EventType[] added, 
                                   org.omg.CosNotification.EventType[] removed) throws  com.ericsson.irp.CosNotifyComm.InvalidEventType {
    this._delegate.subscription_change(added, removed);
  }

}
