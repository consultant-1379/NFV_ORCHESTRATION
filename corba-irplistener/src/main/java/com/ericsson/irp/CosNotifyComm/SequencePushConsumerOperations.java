package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::SequencePushConsumer
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/SequencePushConsumer:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface SequencePushConsumer : com.ericsson.irp.CosNotifyComm.NotifyPublish {
  ...
};
 * </pre>
 */
public interface SequencePushConsumerOperations extends com.ericsson.irp.CosNotifyComm.NotifyPublishOperations {
  /**
   * <pre>
   *   void disconnect_sequence_push_consumer ();
   * </pre>
   */
  public void disconnect_sequence_push_consumer ();

  /**
   * <pre>
   *   void push_structured_events (in org.omg.CosNotification.EventBatch notifications)
    raises (com.ericsson.irp.CosEventComm.Disconnected);
   * </pre>
   */
  public void push_structured_events (org.omg.CosNotification.StructuredEvent[] notifications) throws com.ericsson.irp.CosEventComm.Disconnected;

}
