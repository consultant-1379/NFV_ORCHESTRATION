package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::SequencePullConsumer
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/SequencePullConsumer:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface SequencePullConsumer : com.ericsson.irp.CosNotifyComm.NotifyPublish {
  ...
};
 * </pre>
 */
public final class SequencePullConsumerHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNotifyComm.SequencePullConsumer value;

public SequencePullConsumerHolder () {
}

public SequencePullConsumerHolder (final com.ericsson.irp.CosNotifyComm.SequencePullConsumer _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNotifyComm.SequencePullConsumerHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNotifyComm.SequencePullConsumerHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNotifyComm.SequencePullConsumerHelper.type();
}
}
