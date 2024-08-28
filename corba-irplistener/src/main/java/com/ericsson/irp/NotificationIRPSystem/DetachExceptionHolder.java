package com.ericsson.irp.NotificationIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPSystem::DetachException
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPSystem/DetachException:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception DetachException {
  ...
};
 * </pre>
 */
public final class DetachExceptionHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.NotificationIRPSystem.DetachException value;

public DetachExceptionHolder () {
}

public DetachExceptionHolder (final com.ericsson.irp.NotificationIRPSystem.DetachException _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.NotificationIRPSystem.DetachExceptionHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.NotificationIRPSystem.DetachExceptionHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.NotificationIRPSystem.DetachExceptionHelper.type();
}
}
