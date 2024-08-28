package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::NotifyPublish
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/NotifyPublish:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface NotifyPublish {
  ...
};
 * </pre>
 */
public final class NotifyPublishHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNotifyComm.NotifyPublish value;

public NotifyPublishHolder () {
}

public NotifyPublishHolder (final com.ericsson.irp.CosNotifyComm.NotifyPublish _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNotifyComm.NotifyPublishHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNotifyComm.NotifyPublishHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNotifyComm.NotifyPublishHelper.type();
}
}
