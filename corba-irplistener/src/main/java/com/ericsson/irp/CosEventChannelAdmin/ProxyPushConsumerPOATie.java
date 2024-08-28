
package com.ericsson.irp.CosEventChannelAdmin;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventChannelAdmin.idl"
 * <li> <b>IDL Name</b>      ::CosEventChannelAdmin::ProxyPushConsumer
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventChannelAdmin/ProxyPushConsumer:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface ProxyPushConsumer : com.ericsson.irp.CosEventComm.PushConsumer {
  ...
};
 * </pre>
 */
public class ProxyPushConsumerPOATie extends ProxyPushConsumerPOA {
  private com.ericsson.irp.CosEventChannelAdmin.ProxyPushConsumerOperations _delegate;
  private org.omg.PortableServer.POA _poa;

  public ProxyPushConsumerPOATie (final com.ericsson.irp.CosEventChannelAdmin.ProxyPushConsumerOperations _delegate) {
    this._delegate = _delegate;
  }

  public ProxyPushConsumerPOATie (final com.ericsson.irp.CosEventChannelAdmin.ProxyPushConsumerOperations _delegate, 
                              final org.omg.PortableServer.POA _poa) {
    this._delegate = _delegate;
    this._poa = _poa;
  }

  public com.ericsson.irp.CosEventChannelAdmin.ProxyPushConsumerOperations _delegate () {
    return this._delegate;
  }

  public void _delegate (final com.ericsson.irp.CosEventChannelAdmin.ProxyPushConsumerOperations the_delegate) {
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
   *   void connect_push_supplier (in com.ericsson.irp.CosEventComm.PushSupplier push_supplier)
    raises (com.ericsson.irp.CosEventChannelAdmin.AlreadyConnected);
   * </pre>
   */
  public void connect_push_supplier (com.ericsson.irp.CosEventComm.PushSupplier push_supplier) throws  com.ericsson.irp.CosEventChannelAdmin.AlreadyConnected {
    this._delegate.connect_push_supplier(push_supplier);
  }

  /**
   * <pre>
   *   void disconnect_push_consumer ();
   * </pre>
   */
  public void disconnect_push_consumer () {
    this._delegate.disconnect_push_consumer();
  }

  /**
   * <pre>
   *   void push (in any data)
    raises (com.ericsson.irp.CosEventComm.Disconnected);
   * </pre>
   */
  public void push (org.omg.CORBA.Any data) throws  com.ericsson.irp.CosEventComm.Disconnected {
    this._delegate.push(data);
  }

}
