package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::PullSupplier
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/PullSupplier:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface PullSupplier : com.ericsson.irp.CosNotifyComm.NotifySubscribe,
                         com.ericsson.irp.CosEventComm.PullSupplier {
  ...
};
 * </pre>
 */
public interface PullSupplier extends com.ericsson.irp.CosNotifyComm.PullSupplierOperations, com.ericsson.irp.CosNotifyComm.NotifySubscribe, 
                                                                                             com.ericsson.irp.CosEventComm.PullSupplier, org.omg.CORBA.portable.IDLEntity {
}
