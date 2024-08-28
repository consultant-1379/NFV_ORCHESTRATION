
package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::StructuredPushSupplier
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/StructuredPushSupplier:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface StructuredPushSupplier : com.ericsson.irp.CosNotifyComm.NotifySubscribe {
  ...
};
 * </pre>
 */
public class StructuredPushSupplierPOATie extends StructuredPushSupplierPOA {
  private com.ericsson.irp.CosNotifyComm.StructuredPushSupplierOperations _delegate;
  private org.omg.PortableServer.POA _poa;

  public StructuredPushSupplierPOATie (final com.ericsson.irp.CosNotifyComm.StructuredPushSupplierOperations _delegate) {
    this._delegate = _delegate;
  }

  public StructuredPushSupplierPOATie (final com.ericsson.irp.CosNotifyComm.StructuredPushSupplierOperations _delegate, 
                              final org.omg.PortableServer.POA _poa) {
    this._delegate = _delegate;
    this._poa = _poa;
  }

  public com.ericsson.irp.CosNotifyComm.StructuredPushSupplierOperations _delegate () {
    return this._delegate;
  }

  public void _delegate (final com.ericsson.irp.CosNotifyComm.StructuredPushSupplierOperations the_delegate) {
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
   *   void disconnect_structured_push_supplier ();
   * </pre>
   */
  public void disconnect_structured_push_supplier () {
    this._delegate.disconnect_structured_push_supplier();
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
