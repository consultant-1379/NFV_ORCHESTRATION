package com.ericsson.irp.CosEventComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventComm.idl"
 * <li> <b>IDL Name</b>      ::CosEventComm::PullSupplier
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventComm/PullSupplier:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface PullSupplier {
  ...
};
 * </pre>
 */
public final class PullSupplierHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosEventComm.PullSupplier value;

public PullSupplierHolder () {
}

public PullSupplierHolder (final com.ericsson.irp.CosEventComm.PullSupplier _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosEventComm.PullSupplierHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosEventComm.PullSupplierHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosEventComm.PullSupplierHelper.type();
}
}
