package com.ericsson.irp.CosEventChannelAdmin;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventChannelAdmin.idl"
 * <li> <b>IDL Name</b>      ::CosEventChannelAdmin::SupplierAdmin
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventChannelAdmin/SupplierAdmin:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface SupplierAdmin {
  ...
};
 * </pre>
 */
public interface SupplierAdminOperations {
  /**
   * <pre>
   *   com.ericsson.irp.CosEventChannelAdmin.ProxyPullConsumer obtain_pull_consumer ();
   * </pre>
   */
  public com.ericsson.irp.CosEventChannelAdmin.ProxyPullConsumer obtain_pull_consumer ();

  /**
   * <pre>
   *   com.ericsson.irp.CosEventChannelAdmin.ProxyPushConsumer obtain_push_consumer ();
   * </pre>
   */
  public com.ericsson.irp.CosEventChannelAdmin.ProxyPushConsumer obtain_push_consumer ();

}
