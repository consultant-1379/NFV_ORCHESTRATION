package com.ericsson.irp.NotificationIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPSystem::ParameterNotSupported
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPSystem/ParameterNotSupported:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception ParameterNotSupported {
  ...
};
 * </pre>
 */
public final class ParameterNotSupportedHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.NotificationIRPSystem.ParameterNotSupported value;

public ParameterNotSupportedHolder () {
}

public ParameterNotSupportedHolder (final com.ericsson.irp.NotificationIRPSystem.ParameterNotSupported _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.NotificationIRPSystem.ParameterNotSupportedHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.NotificationIRPSystem.ParameterNotSupportedHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.NotificationIRPSystem.ParameterNotSupportedHelper.type();
}
}
