package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::SequencePushConsumer
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/SequencePushConsumer:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface SequencePushConsumer : com.ericsson.irp.CosNotifyComm.NotifyPublish {
  ...
};
 * </pre>
 */
public final class SequencePushConsumerHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNotifyComm.SequencePushConsumer value;

public SequencePushConsumerHolder () {
}

public SequencePushConsumerHolder (final com.ericsson.irp.CosNotifyComm.SequencePushConsumer _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNotifyComm.SequencePushConsumerHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNotifyComm.SequencePushConsumerHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNotifyComm.SequencePushConsumerHelper.type();
}
}
