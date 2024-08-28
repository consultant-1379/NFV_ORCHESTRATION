package com.ericsson.irp.AlarmIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPSystem::ParameterNotSupported
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPSystem/ParameterNotSupported:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception ParameterNotSupported {
  ...
};
 * </pre>
 */
public final class ParameterNotSupportedHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.AlarmIRPSystem.ParameterNotSupported value;

public ParameterNotSupportedHolder () {
}

public ParameterNotSupportedHolder (final com.ericsson.irp.AlarmIRPSystem.ParameterNotSupported _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.AlarmIRPSystem.ParameterNotSupportedHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.AlarmIRPSystem.ParameterNotSupportedHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.AlarmIRPSystem.ParameterNotSupportedHelper.type();
}
}
