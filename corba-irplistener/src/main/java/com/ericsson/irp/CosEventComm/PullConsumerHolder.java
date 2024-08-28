package com.ericsson.irp.CosEventComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventComm.idl"
 * <li> <b>IDL Name</b>      ::CosEventComm::PullConsumer
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventComm/PullConsumer:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface PullConsumer {
  ...
};
 * </pre>
 */
public final class PullConsumerHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosEventComm.PullConsumer value;

public PullConsumerHolder () {
}

public PullConsumerHolder (final com.ericsson.irp.CosEventComm.PullConsumer _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosEventComm.PullConsumerHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosEventComm.PullConsumerHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosEventComm.PullConsumerHelper.type();
}
}
