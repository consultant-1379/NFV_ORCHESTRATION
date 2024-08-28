package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::PushSupplier
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/PushSupplier:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface PushSupplier : com.ericsson.irp.CosNotifyComm.NotifySubscribe,
                         com.ericsson.irp.CosEventComm.PushSupplier {
  ...
};
 * </pre>
 */
public interface PushSupplierOperations extends com.ericsson.irp.CosNotifyComm.NotifySubscribeOperations, 
                                                com.ericsson.irp.CosEventComm.PushSupplierOperations {
}
