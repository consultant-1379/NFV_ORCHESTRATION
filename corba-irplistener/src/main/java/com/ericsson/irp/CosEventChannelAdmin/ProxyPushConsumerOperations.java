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
public interface ProxyPushConsumerOperations extends com.ericsson.irp.CosEventComm.PushConsumerOperations {
  /**
   * <pre>
   *   void connect_push_supplier (in com.ericsson.irp.CosEventComm.PushSupplier push_supplier)
    raises (com.ericsson.irp.CosEventChannelAdmin.AlreadyConnected);
   * </pre>
   */
  public void connect_push_supplier (com.ericsson.irp.CosEventComm.PushSupplier push_supplier) throws com.ericsson.irp.CosEventChannelAdmin.AlreadyConnected;

}
