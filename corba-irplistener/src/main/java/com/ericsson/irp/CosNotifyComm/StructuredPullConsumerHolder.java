package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::StructuredPullConsumer
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/StructuredPullConsumer:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface StructuredPullConsumer : com.ericsson.irp.CosNotifyComm.NotifyPublish {
  ...
};
 * </pre>
 */
public final class StructuredPullConsumerHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNotifyComm.StructuredPullConsumer value;

public StructuredPullConsumerHolder () {
}

public StructuredPullConsumerHolder (final com.ericsson.irp.CosNotifyComm.StructuredPullConsumer _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNotifyComm.StructuredPullConsumerHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNotifyComm.StructuredPullConsumerHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNotifyComm.StructuredPullConsumerHelper.type();
}
}
