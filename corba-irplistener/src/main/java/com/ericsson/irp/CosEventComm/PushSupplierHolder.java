package com.ericsson.irp.CosEventComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventComm.idl"
 * <li> <b>IDL Name</b>      ::CosEventComm::PushSupplier
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventComm/PushSupplier:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface PushSupplier {
  ...
};
 * </pre>
 */
public final class PushSupplierHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosEventComm.PushSupplier value;

public PushSupplierHolder () {
}

public PushSupplierHolder (final com.ericsson.irp.CosEventComm.PushSupplier _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosEventComm.PushSupplierHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosEventComm.PushSupplierHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosEventComm.PushSupplierHelper.type();
}
}
