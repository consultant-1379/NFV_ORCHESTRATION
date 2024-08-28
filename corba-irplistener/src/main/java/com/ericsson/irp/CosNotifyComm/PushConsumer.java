package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::PushConsumer
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/PushConsumer:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface PushConsumer : com.ericsson.irp.CosNotifyComm.NotifyPublish,
                         com.ericsson.irp.CosEventComm.PushConsumer {
  ...
};
 * </pre>
 */
public interface PushConsumer extends com.ericsson.irp.CosNotifyComm.PushConsumerOperations, com.ericsson.irp.CosNotifyComm.NotifyPublish, 
                                                                                             com.ericsson.irp.CosEventComm.PushConsumer, org.omg.CORBA.portable.IDLEntity {
}
