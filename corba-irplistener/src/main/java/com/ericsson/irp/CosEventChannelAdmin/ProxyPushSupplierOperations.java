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
public interface ProxyPushSupplierOperations extends com.ericsson.irp.CosEventComm.PushSupplierOperations {
  /**
   * <pre>
   *   void connect_push_consumer (in com.ericsson.irp.CosEventComm.PushConsumer push_consumer)
    raises (com.ericsson.irp.CosEventChannelAdmin.AlreadyConnected,
            com.ericsson.irp.CosEventChannelAdmin.TypeError);
   * </pre>
   */
  public void connect_push_consumer (com.ericsson.irp.CosEventComm.PushConsumer push_consumer) throws com.ericsson.irp.CosEventChannelAdmin.AlreadyConnected, 
                                                                                                      com.ericsson.irp.CosEventChannelAdmin.TypeError;

}
