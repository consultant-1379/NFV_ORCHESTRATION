package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::SequencePushSupplier
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/SequencePushSupplier:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface SequencePushSupplier : com.ericsson.irp.CosNotifyComm.NotifySubscribe {
  ...
};
 * </pre>
 */
public final class SequencePushSupplierHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNotifyComm.SequencePushSupplier value;

public SequencePushSupplierHolder () {
}

public SequencePushSupplierHolder (final com.ericsson.irp.CosNotifyComm.SequencePushSupplier _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNotifyComm.SequencePushSupplierHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNotifyComm.SequencePushSupplierHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNotifyComm.SequencePushSupplierHelper.type();
}
}
