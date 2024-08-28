package com.ericsson.irp.CosEventComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventComm.idl"
 * <li> <b>IDL Name</b>      ::CosEventComm::PushConsumer
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventComm/PushConsumer:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface PushConsumer {
  ...
};
 * </pre>
 */
public final class PushConsumerHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosEventComm.PushConsumer value;

public PushConsumerHolder () {
}

public PushConsumerHolder (final com.ericsson.irp.CosEventComm.PushConsumer _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosEventComm.PushConsumerHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosEventComm.PushConsumerHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosEventComm.PushConsumerHelper.type();
}
}
