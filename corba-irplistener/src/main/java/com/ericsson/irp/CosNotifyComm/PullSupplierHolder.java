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
public final class PullSupplierHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNotifyComm.PullSupplier value;

public PullSupplierHolder () {
}

public PullSupplierHolder (final com.ericsson.irp.CosNotifyComm.PullSupplier _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNotifyComm.PullSupplierHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNotifyComm.PullSupplierHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNotifyComm.PullSupplierHelper.type();
}
}
