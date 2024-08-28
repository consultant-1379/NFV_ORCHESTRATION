
package com.ericsson.irp.CosEventChannelAdmin;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventChannelAdmin.idl"
 * <li> <b>IDL Name</b>      ::CosEventChannelAdmin::ProxyPullConsumer
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventChannelAdmin/ProxyPullConsumer:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface ProxyPullConsumer : com.ericsson.irp.CosEventComm.PullConsumer {
  ...
};
 * </pre>
 */
public class ProxyPullConsumerPOATie extends ProxyPullConsumerPOA {
  private com.ericsson.irp.CosEventChannelAdmin.ProxyPullConsumerOperations _delegate;
  private org.omg.PortableServer.POA _poa;

  public ProxyPullConsumerPOATie (final com.ericsson.irp.CosEventChannelAdmin.ProxyPullConsumerOperations _delegate) {
    this._delegate = _delegate;
  }

  public ProxyPullConsumerPOATie (final com.ericsson.irp.CosEventChannelAdmin.ProxyPullConsumerOperations _delegate, 
                              final org.omg.PortableServer.POA _poa) {
    this._delegate = _delegate;
    this._poa = _poa;
  }

  public com.ericsson.irp.CosEventChannelAdmin.ProxyPullConsumerOperations _delegate () {
    return this._delegate;
  }

  public void _delegate (final com.ericsson.irp.CosEventChannelAdmin.ProxyPullConsumerOperations the_delegate) {
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
   *   void connect_pull_supplier (in com.ericsson.irp.CosEventComm.PullSupplier pull_supplier)
    raises (com.ericsson.irp.CosEventChannelAdmin.AlreadyConnected,
            com.ericsson.irp.CosEventChannelAdmin.TypeError);
   * </pre>
   */
  public void connect_pull_supplier (com.ericsson.irp.CosEventComm.PullSupplier pull_supplier) throws  com.ericsson.irp.CosEventChannelAdmin.AlreadyConnected, 
                                                                                                       com.ericsson.irp.CosEventChannelAdmin.TypeError {
    this._delegate.connect_pull_supplier(pull_supplier);
  }

  /**
   * <pre>
   *   void disconnect_pull_consumer ();
   * </pre>
   */
  public void disconnect_pull_consumer () {
    this._delegate.disconnect_pull_consumer();
  }

}
