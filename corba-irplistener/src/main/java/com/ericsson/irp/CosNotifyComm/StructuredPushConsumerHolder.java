package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::StructuredPushConsumer
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/StructuredPushConsumer:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface StructuredPushConsumer : com.ericsson.irp.CosNotifyComm.NotifyPublish {
  ...
};
 * </pre>
 */
public final class StructuredPushConsumerHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNotifyComm.StructuredPushConsumer value;

public StructuredPushConsumerHolder () {
}

public StructuredPushConsumerHolder (final com.ericsson.irp.CosNotifyComm.StructuredPushConsumer _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNotifyComm.StructuredPushConsumerHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNotifyComm.StructuredPushConsumerHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNotifyComm.StructuredPushConsumerHelper.type();
}
}
