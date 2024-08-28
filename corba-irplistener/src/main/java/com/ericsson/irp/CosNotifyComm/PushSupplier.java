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
public interface PushSupplier extends com.ericsson.irp.CosNotifyComm.PushSupplierOperations, com.ericsson.irp.CosNotifyComm.NotifySubscribe, 
                                                                                             com.ericsson.irp.CosEventComm.PushSupplier, org.omg.CORBA.portable.IDLEntity {
}
