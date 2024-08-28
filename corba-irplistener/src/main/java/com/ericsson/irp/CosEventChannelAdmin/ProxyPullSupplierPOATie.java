
package com.ericsson.irp.CosEventChannelAdmin;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventChannelAdmin.idl"
 * <li> <b>IDL Name</b>      ::CosEventChannelAdmin::ProxyPullSupplier
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventChannelAdmin/ProxyPullSupplier:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface ProxyPullSupplier : com.ericsson.irp.CosEventComm.PullSupplier {
  ...
};
 * </pre>
 */
public class ProxyPullSupplierPOATie extends ProxyPullSupplierPOA {
  private com.ericsson.irp.CosEventChannelAdmin.ProxyPullSupplierOperations _delegate;
  private org.omg.PortableServer.POA _poa;

  public ProxyPullSupplierPOATie (final com.ericsson.irp.CosEventChannelAdmin.ProxyPullSupplierOperations _delegate) {
    this._delegate = _delegate;
  }

  public ProxyPullSupplierPOATie (final com.ericsson.irp.CosEventChannelAdmin.ProxyPullSupplierOperations _delegate, 
                              final org.omg.PortableServer.POA _poa) {
    this._delegate = _delegate;
    this._poa = _poa;
  }

  public com.ericsson.irp.CosEventChannelAdmin.ProxyPullSupplierOperations _delegate () {
    return this._delegate;
  }

  public void _delegate (final com.ericsson.irp.CosEventChannelAdmin.ProxyPullSupplierOperations the_delegate) {
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
   *   void connect_pull_consumer (in com.ericsson.irp.CosEventComm.PullConsumer pull_consumer)
    raises (com.ericsson.irp.CosEventChannelAdmin.AlreadyConnected);
   * </pre>
   */
  public void connect_pull_consumer (com.ericsson.irp.CosEventComm.PullConsumer pull_consumer) throws  com.ericsson.irp.CosEventChannelAdmin.AlreadyConnected {
    this._delegate.connect_pull_consumer(pull_consumer);
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

}
