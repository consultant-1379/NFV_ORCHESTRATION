package com.ericsson.irp.CosEventChannelAdmin;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventChannelAdmin.idl"
 * <li> <b>IDL Name</b>      ::CosEventChannelAdmin::AlreadyConnected
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventChannelAdmin/AlreadyConnected:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception AlreadyConnected {
  ...
};
 * </pre>
 */
public final class AlreadyConnectedHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosEventChannelAdmin.AlreadyConnected value;

public AlreadyConnectedHolder () {
}

public AlreadyConnectedHolder (final com.ericsson.irp.CosEventChannelAdmin.AlreadyConnected _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosEventChannelAdmin.AlreadyConnectedHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosEventChannelAdmin.AlreadyConnectedHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosEventChannelAdmin.AlreadyConnectedHelper.type();
}
}
