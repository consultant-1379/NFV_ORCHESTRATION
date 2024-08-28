package com.ericsson.irp.NotificationIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPSystem::Attach
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPSystem/Attach:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception Attach {
  ...
};
 * </pre>
 */
public final class AttachHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.NotificationIRPSystem.Attach value;

public AttachHolder () {
}

public AttachHolder (final com.ericsson.irp.NotificationIRPSystem.Attach _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.NotificationIRPSystem.AttachHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.NotificationIRPSystem.AttachHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.NotificationIRPSystem.AttachHelper.type();
}
}
