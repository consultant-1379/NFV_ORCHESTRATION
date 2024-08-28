package com.ericsson.irp.NotificationIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPSystem::GetNotificationIRPVersion
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPSystem/GetNotificationIRPVersion:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception GetNotificationIRPVersion {
  ...
};
 * </pre>
 */
public final class GetNotificationIRPVersionHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.NotificationIRPSystem.GetNotificationIRPVersion value;

public GetNotificationIRPVersionHolder () {
}

public GetNotificationIRPVersionHolder (final com.ericsson.irp.NotificationIRPSystem.GetNotificationIRPVersion _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.NotificationIRPSystem.GetNotificationIRPVersionHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.NotificationIRPSystem.GetNotificationIRPVersionHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.NotificationIRPSystem.GetNotificationIRPVersionHelper.type();
}
}
