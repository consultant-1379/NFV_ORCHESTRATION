package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::PullConsumer
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/PullConsumer:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface PullConsumer : com.ericsson.irp.CosNotifyComm.NotifyPublish,
                         com.ericsson.irp.CosEventComm.PullConsumer {
  ...
};
 * </pre>
 */
public final class PullConsumerHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNotifyComm.PullConsumer value;

public PullConsumerHolder () {
}

public PullConsumerHolder (final com.ericsson.irp.CosNotifyComm.PullConsumer _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNotifyComm.PullConsumerHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNotifyComm.PullConsumerHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNotifyComm.PullConsumerHelper.type();
}
}
