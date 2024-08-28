
package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::StructuredPullSupplier
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/StructuredPullSupplier:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface StructuredPullSupplier : com.ericsson.irp.CosNotifyComm.NotifySubscribe {
  ...
};
 * </pre>
 */
public class StructuredPullSupplierPOATie extends StructuredPullSupplierPOA {
  private com.ericsson.irp.CosNotifyComm.StructuredPullSupplierOperations _delegate;
  private org.omg.PortableServer.POA _poa;

  public StructuredPullSupplierPOATie (final com.ericsson.irp.CosNotifyComm.StructuredPullSupplierOperations _delegate) {
    this._delegate = _delegate;
  }

  public StructuredPullSupplierPOATie (final com.ericsson.irp.CosNotifyComm.StructuredPullSupplierOperations _delegate, 
                              final org.omg.PortableServer.POA _poa) {
    this._delegate = _delegate;
    this._poa = _poa;
  }

  public com.ericsson.irp.CosNotifyComm.StructuredPullSupplierOperations _delegate () {
    return this._delegate;
  }

  public void _delegate (final com.ericsson.irp.CosNotifyComm.StructuredPullSupplierOperations the_delegate) {
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
   *   void disconnect_structured_pull_supplier ();
   * </pre>
   */
  public void disconnect_structured_pull_supplier () {
    this._delegate.disconnect_structured_pull_supplier();
  }

  /**
   * <pre>
   *   org.omg.CosNotification.StructuredEvent try_pull_structured_event (out boolean has_event)
    raises (com.ericsson.irp.CosEventComm.Disconnected);
   * </pre>
   */
  public org.omg.CosNotification.StructuredEvent try_pull_structured_event (org.omg.CORBA.BooleanHolder has_event) throws  com.ericsson.irp.CosEventComm.Disconnected {
    return this._delegate.try_pull_structured_event(has_event);
  }

  /**
   * <pre>
   *   org.omg.CosNotification.StructuredEvent pull_structured_event ()
    raises (com.ericsson.irp.CosEventComm.Disconnected);
   * </pre>
   */
  public org.omg.CosNotification.StructuredEvent pull_structured_event () throws  com.ericsson.irp.CosEventComm.Disconnected {
    return this._delegate.pull_structured_event();
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
