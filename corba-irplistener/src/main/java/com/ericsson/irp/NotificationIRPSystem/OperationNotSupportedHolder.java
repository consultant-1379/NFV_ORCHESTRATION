package com.ericsson.irp.NotificationIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPSystem::OperationNotSupported
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPSystem/OperationNotSupported:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception OperationNotSupported {
  ...
};
 * </pre>
 */
public final class OperationNotSupportedHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.NotificationIRPSystem.OperationNotSupported value;

public OperationNotSupportedHolder () {
}

public OperationNotSupportedHolder (final com.ericsson.irp.NotificationIRPSystem.OperationNotSupported _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.NotificationIRPSystem.OperationNotSupportedHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.NotificationIRPSystem.OperationNotSupportedHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.NotificationIRPSystem.OperationNotSupportedHelper.type();
}
}
