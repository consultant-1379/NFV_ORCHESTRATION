package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::SequencePullConsumer
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/SequencePullConsumer:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface SequencePullConsumer : com.ericsson.irp.CosNotifyComm.NotifyPublish {
  ...
};
 * </pre>
 */
public interface SequencePullConsumerOperations extends com.ericsson.irp.CosNotifyComm.NotifyPublishOperations {
  /**
   * <pre>
   *   void disconnect_sequence_pull_consumer ();
   * </pre>
   */
  public void disconnect_sequence_pull_consumer ();

}
