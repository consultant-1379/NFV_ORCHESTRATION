package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::NotifySubscribe
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/NotifySubscribe:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface NotifySubscribe {
  ...
};
 * </pre>
 */
public final class NotifySubscribeHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNotifyComm.NotifySubscribe value;

public NotifySubscribeHolder () {
}

public NotifySubscribeHolder (final com.ericsson.irp.CosNotifyComm.NotifySubscribe _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNotifyComm.NotifySubscribeHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNotifyComm.NotifySubscribeHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNotifyComm.NotifySubscribeHelper.type();
}
}
