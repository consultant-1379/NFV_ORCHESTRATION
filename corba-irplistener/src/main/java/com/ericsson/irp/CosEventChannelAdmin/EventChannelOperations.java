package com.ericsson.irp.CosEventChannelAdmin;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventChannelAdmin.idl"
 * <li> <b>IDL Name</b>      ::CosEventChannelAdmin::EventChannel
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventChannelAdmin/EventChannel:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface EventChannel {
  ...
};
 * </pre>
 */
public interface EventChannelOperations {
  /**
   * <pre>
   *   void destroy ();
   * </pre>
   */
  public void destroy ();

  /**
   * <pre>
   *   com.ericsson.irp.CosEventChannelAdmin.SupplierAdmin for_suppliers ();
   * </pre>
   */
  public com.ericsson.irp.CosEventChannelAdmin.SupplierAdmin for_suppliers ();

  /**
   * <pre>
   *   com.ericsson.irp.CosEventChannelAdmin.ConsumerAdmin for_consumers ();
   * </pre>
   */
  public com.ericsson.irp.CosEventChannelAdmin.ConsumerAdmin for_consumers ();

}
