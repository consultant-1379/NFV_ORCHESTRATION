
package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::SequencePushSupplier
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/SequencePushSupplier:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface SequencePushSupplier : com.ericsson.irp.CosNotifyComm.NotifySubscribe {
  ...
};
 * </pre>
 */
public class SequencePushSupplierPOATie extends SequencePushSupplierPOA {
  private com.ericsson.irp.CosNotifyComm.SequencePushSupplierOperations _delegate;
  private org.omg.PortableServer.POA _poa;

  public SequencePushSupplierPOATie (final com.ericsson.irp.CosNotifyComm.SequencePushSupplierOperations _delegate) {
    this._delegate = _delegate;
  }

  public SequencePushSupplierPOATie (final com.ericsson.irp.CosNotifyComm.SequencePushSupplierOperations _delegate, 
                              final org.omg.PortableServer.POA _poa) {
    this._delegate = _delegate;
    this._poa = _poa;
  }

  public com.ericsson.irp.CosNotifyComm.SequencePushSupplierOperations _delegate () {
    return this._delegate;
  }

  public void _delegate (final com.ericsson.irp.CosNotifyComm.SequencePushSupplierOperations the_delegate) {
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
   *   void disconnect_sequence_push_supplier ();
   * </pre>
   */
  public void disconnect_sequence_push_supplier () {
    this._delegate.disconnect_sequence_push_supplier();
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
