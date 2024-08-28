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
public interface ProxyPullSupplierOperations extends com.ericsson.irp.CosEventComm.PullSupplierOperations {
  /**
   * <pre>
   *   void connect_pull_consumer (in com.ericsson.irp.CosEventComm.PullConsumer pull_consumer)
    raises (com.ericsson.irp.CosEventChannelAdmin.AlreadyConnected);
   * </pre>
   */
  public void connect_pull_consumer (com.ericsson.irp.CosEventComm.PullConsumer pull_consumer) throws com.ericsson.irp.CosEventChannelAdmin.AlreadyConnected;

}
