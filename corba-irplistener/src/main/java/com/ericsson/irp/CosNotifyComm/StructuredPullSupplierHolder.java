package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::StructuredPullSupplier
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/StructuredPullSupplier:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface StructuredPullSupplier : com.ericsson.irp.CosNotifyComm.NotifySubscribe {
  ...
};
 * </pre>
 */
public final class StructuredPullSupplierHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNotifyComm.StructuredPullSupplier value;

public StructuredPullSupplierHolder () {
}

public StructuredPullSupplierHolder (final com.ericsson.irp.CosNotifyComm.StructuredPullSupplier _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNotifyComm.StructuredPullSupplierHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNotifyComm.StructuredPullSupplierHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNotifyComm.StructuredPullSupplierHelper.type();
}
}
