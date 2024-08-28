package com.ericsson.irp.CosEventChannelAdmin;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventChannelAdmin.idl"
 * <li> <b>IDL Name</b>      ::CosEventChannelAdmin::EventChannel
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventChannelAdmin/EventChannel:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface EventChannel {
  ...
};
 * </pre>
 */
public final class EventChannelHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosEventChannelAdmin.EventChannel value;

public EventChannelHolder () {
}

public EventChannelHolder (final com.ericsson.irp.CosEventChannelAdmin.EventChannel _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosEventChannelAdmin.EventChannelHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosEventChannelAdmin.EventChannelHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosEventChannelAdmin.EventChannelHelper.type();
}
}
