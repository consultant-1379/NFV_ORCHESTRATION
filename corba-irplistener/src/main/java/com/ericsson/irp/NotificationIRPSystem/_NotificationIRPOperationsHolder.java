package com.ericsson.irp.NotificationIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPSystem::NotificationIRPOperations
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPSystem/NotificationIRPOperations:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface _NotificationIRPOperations {
  ...
};
 * </pre>
 */
public final class _NotificationIRPOperationsHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperations value;

public _NotificationIRPOperationsHolder () {
}

public _NotificationIRPOperationsHolder (final com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperations _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperationsHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperationsHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperationsHelper.type();
}
}
