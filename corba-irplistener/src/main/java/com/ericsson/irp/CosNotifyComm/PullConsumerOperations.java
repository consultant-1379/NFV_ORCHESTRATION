package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::PullConsumer
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/PullConsumer:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface PullConsumer : com.ericsson.irp.CosNotifyComm.NotifyPublish,
                         com.ericsson.irp.CosEventComm.PullConsumer {
  ...
};
 * </pre>
 */
public interface PullConsumerOperations extends com.ericsson.irp.CosNotifyComm.NotifyPublishOperations, 
                                                com.ericsson.irp.CosEventComm.PullConsumerOperations {
}
