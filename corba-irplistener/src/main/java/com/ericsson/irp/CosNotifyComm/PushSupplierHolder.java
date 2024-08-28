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
public final class PushSupplierHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNotifyComm.PushSupplier value;

public PushSupplierHolder () {
}

public PushSupplierHolder (final com.ericsson.irp.CosNotifyComm.PushSupplier _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNotifyComm.PushSupplierHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNotifyComm.PushSupplierHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNotifyComm.PushSupplierHelper.type();
}
}
