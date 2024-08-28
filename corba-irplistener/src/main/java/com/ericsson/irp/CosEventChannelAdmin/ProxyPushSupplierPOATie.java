
package com.ericsson.irp.CosEventChannelAdmin;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventChannelAdmin.idl"
 * <li> <b>IDL Name</b>      ::CosEventChannelAdmin::ProxyPushSupplier
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventChannelAdmin/ProxyPushSupplier:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface ProxyPushSupplier : com.ericsson.irp.CosEventComm.PushSupplier {
  ...
};
 * </pre>
 */
public class ProxyPushSupplierPOATie extends ProxyPushSupplierPOA {
  private com.ericsson.irp.CosEventChannelAdmin.ProxyPushSupplierOperations _delegate;
  private org.omg.PortableServer.POA _poa;

  public ProxyPushSupplierPOATie (final com.ericsson.irp.CosEventChannelAdmin.ProxyPushSupplierOperations _delegate) {
    this._delegate = _delegate;
  }

  public ProxyPushSupplierPOATie (final com.ericsson.irp.CosEventChannelAdmin.ProxyPushSupplierOperations _delegate, 
                              final org.omg.PortableServer.POA _poa) {
    this._delegate = _delegate;
    this._poa = _poa;
  }

  public com.ericsson.irp.CosEventChannelAdmin.ProxyPushSupplierOperations _delegate () {
    return this._delegate;
  }

  public void _delegate (final com.ericsson.irp.CosEventChannelAdmin.ProxyPushSupplierOperations the_delegate) {
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
   *   void connect_push_consumer (in com.ericsson.irp.CosEventComm.PushConsumer push_consumer)
    raises (com.ericsson.irp.CosEventChannelAdmin.AlreadyConnected,
            com.ericsson.irp.CosEventChannelAdmin.TypeError);
   * </pre>
   */
  public void connect_push_consumer (com.ericsson.irp.CosEventComm.PushConsumer push_consumer) throws  com.ericsson.irp.CosEventChannelAdmin.AlreadyConnected, 
                                                                                                       com.ericsson.irp.CosEventChannelAdmin.TypeError {
    this._delegate.connect_push_consumer(push_consumer);
  }

  /**
   * <pre>
   *   void disconnect_push_supplier ();
   * </pre>
   */
  public void disconnect_push_supplier () {
    this._delegate.disconnect_push_supplier();
  }

}
