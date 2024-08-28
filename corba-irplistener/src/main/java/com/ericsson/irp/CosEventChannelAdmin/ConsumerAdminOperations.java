package com.ericsson.irp.CosEventChannelAdmin;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventChannelAdmin.idl"
 * <li> <b>IDL Name</b>      ::CosEventChannelAdmin::ConsumerAdmin
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventChannelAdmin/ConsumerAdmin:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface ConsumerAdmin {
  ...
};
 * </pre>
 */
public interface ConsumerAdminOperations {
  /**
   * <pre>
   *   com.ericsson.irp.CosEventChannelAdmin.ProxyPullSupplier obtain_pull_supplier ();
   * </pre>
   */
  public com.ericsson.irp.CosEventChannelAdmin.ProxyPullSupplier obtain_pull_supplier ();

  /**
   * <pre>
   *   com.ericsson.irp.CosEventChannelAdmin.ProxyPushSupplier obtain_push_supplier ();
   * </pre>
   */
  public com.ericsson.irp.CosEventChannelAdmin.ProxyPushSupplier obtain_push_supplier ();

}
