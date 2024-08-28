package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::StructuredPushSupplier
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/StructuredPushSupplier:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface StructuredPushSupplier : com.ericsson.irp.CosNotifyComm.NotifySubscribe {
  ...
};
 * </pre>
 */
public final class StructuredPushSupplierHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNotifyComm.StructuredPushSupplier value;

public StructuredPushSupplierHolder () {
}

public StructuredPushSupplierHolder (final com.ericsson.irp.CosNotifyComm.StructuredPushSupplier _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNotifyComm.StructuredPushSupplierHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNotifyComm.StructuredPushSupplierHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNotifyComm.StructuredPushSupplierHelper.type();
}
}
