package com.ericsson.irp.NotificationIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPSystem::AlreadySubscribed
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPSystem/AlreadySubscribed:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception AlreadySubscribed {
  ...
};
 * </pre>
 */
public final class AlreadySubscribedHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.NotificationIRPSystem.AlreadySubscribed value;

public AlreadySubscribedHolder () {
}

public AlreadySubscribedHolder (final com.ericsson.irp.NotificationIRPSystem.AlreadySubscribed _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.NotificationIRPSystem.AlreadySubscribedHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.NotificationIRPSystem.AlreadySubscribedHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.NotificationIRPSystem.AlreadySubscribedHelper.type();
}
}
