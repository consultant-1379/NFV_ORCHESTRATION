package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::SequencePullSupplier
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/SequencePullSupplier:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface SequencePullSupplier : com.ericsson.irp.CosNotifyComm.NotifySubscribe {
  ...
};
 * </pre>
 */
public interface SequencePullSupplierOperations extends com.ericsson.irp.CosNotifyComm.NotifySubscribeOperations {
  /**
   * <pre>
   *   void disconnect_sequence_pull_supplier ();
   * </pre>
   */
  public void disconnect_sequence_pull_supplier ();

  /**
   * <pre>
   *   org.omg.CosNotification.EventBatch try_pull_structured_events (in long max_number,
                                                                 out boolean has_event)
    raises (com.ericsson.irp.CosEventComm.Disconnected);
   * </pre>
   */
  public org.omg.CosNotification.StructuredEvent[] try_pull_structured_events (int max_number, 
                                                                               org.omg.CORBA.BooleanHolder has_event) throws com.ericsson.irp.CosEventComm.Disconnected;

  /**
   * <pre>
   *   org.omg.CosNotification.EventBatch pull_structured_events (in long max_number)
    raises (com.ericsson.irp.CosEventComm.Disconnected);
   * </pre>
   */
  public org.omg.CosNotification.StructuredEvent[] pull_structured_events (int max_number) throws com.ericsson.irp.CosEventComm.Disconnected;

}
