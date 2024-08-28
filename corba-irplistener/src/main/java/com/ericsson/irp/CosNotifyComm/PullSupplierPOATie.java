
package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::PullSupplier
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/PullSupplier:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface PullSupplier : com.ericsson.irp.CosNotifyComm.NotifySubscribe,
                         com.ericsson.irp.CosEventComm.PullSupplier {
  ...
};
 * </pre>
 */
public class PullSupplierPOATie extends PullSupplierPOA {
  private com.ericsson.irp.CosNotifyComm.PullSupplierOperations _delegate;
  private org.omg.PortableServer.POA _poa;

  public PullSupplierPOATie (final com.ericsson.irp.CosNotifyComm.PullSupplierOperations _delegate) {
    this._delegate = _delegate;
  }

  public PullSupplierPOATie (final com.ericsson.irp.CosNotifyComm.PullSupplierOperations _delegate, 
                              final org.omg.PortableServer.POA _poa) {
    this._delegate = _delegate;
    this._poa = _poa;
  }

  public com.ericsson.irp.CosNotifyComm.PullSupplierOperations _delegate () {
    return this._delegate;
  }

  public void _delegate (final com.ericsson.irp.CosNotifyComm.PullSupplierOperations the_delegate) {
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
   *   void disconnect_pull_supplier ();
   * </pre>
   */
  public void disconnect_pull_supplier () {
    this._delegate.disconnect_pull_supplier();
  }

  /**
   * <pre>
   *   any try_pull (out boolean has_event)
    raises (com.ericsson.irp.CosEventComm.Disconnected);
   * </pre>
   */
  public org.omg.CORBA.Any try_pull (org.omg.CORBA.BooleanHolder has_event) throws  com.ericsson.irp.CosEventComm.Disconnected {
    return this._delegate.try_pull(has_event);
  }

  /**
   * <pre>
   *   any pull ()
    raises (com.ericsson.irp.CosEventComm.Disconnected);
   * </pre>
   */
  public org.omg.CORBA.Any pull () throws  com.ericsson.irp.CosEventComm.Disconnected {
    return this._delegate.pull();
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
