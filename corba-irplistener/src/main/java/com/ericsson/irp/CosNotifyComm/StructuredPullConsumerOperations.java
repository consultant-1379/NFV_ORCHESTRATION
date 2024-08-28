package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::StructuredPullConsumer
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/StructuredPullConsumer:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface StructuredPullConsumer : com.ericsson.irp.CosNotifyComm.NotifyPublish {
  ...
};
 * </pre>
 */
public interface StructuredPullConsumerOperations extends com.ericsson.irp.CosNotifyComm.NotifyPublishOperations {
  /**
   * <pre>
   *   void disconnect_structured_pull_consumer ();
   * </pre>
   */
  public void disconnect_structured_pull_consumer ();

}
