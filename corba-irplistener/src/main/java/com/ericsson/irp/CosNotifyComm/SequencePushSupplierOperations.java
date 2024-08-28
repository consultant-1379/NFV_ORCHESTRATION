package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::SequencePushSupplier
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/SequencePushSupplier:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface SequencePushSupplier : com.ericsson.irp.CosNotifyComm.NotifySubscribe {
  ...
};
 * </pre>
 */
public interface SequencePushSupplierOperations extends com.ericsson.irp.CosNotifyComm.NotifySubscribeOperations {
  /**
   * <pre>
   *   void disconnect_sequence_push_supplier ();
   * </pre>
   */
  public void disconnect_sequence_push_supplier ();

}
