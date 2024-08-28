package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::StructuredPushConsumer
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/StructuredPushConsumer:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface StructuredPushConsumer : com.ericsson.irp.CosNotifyComm.NotifyPublish {
  ...
};
 * </pre>
 */
public interface StructuredPushConsumerOperations extends com.ericsson.irp.CosNotifyComm.NotifyPublishOperations {
  /**
   * <pre>
   *   void disconnect_structured_push_consumer ();
   * </pre>
   */
  public void disconnect_structured_push_consumer ();

  /**
   * <pre>
   *   void push_structured_event (in org.omg.CosNotification.StructuredEvent notification)
    raises (com.ericsson.irp.CosEventComm.Disconnected);
   * </pre>
   */
  public void push_structured_event (org.omg.CosNotification.StructuredEvent notification) throws com.ericsson.irp.CosEventComm.Disconnected;

}
