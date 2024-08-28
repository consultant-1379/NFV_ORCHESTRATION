package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::StructuredPushSupplier
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/StructuredPushSupplier:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface StructuredPushSupplier : com.ericsson.irp.CosNotifyComm.NotifySubscribe {
  ...
};
 * </pre>
 */
public interface StructuredPushSupplierOperations extends com.ericsson.irp.CosNotifyComm.NotifySubscribeOperations {
  /**
   * <pre>
   *   void disconnect_structured_push_supplier ();
   * </pre>
   */
  public void disconnect_structured_push_supplier ();

}
