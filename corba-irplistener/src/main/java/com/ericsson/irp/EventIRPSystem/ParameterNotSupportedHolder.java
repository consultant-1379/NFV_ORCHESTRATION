package com.ericsson.irp.EventIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "EventIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::EventIRPSystem::ParameterNotSupported
 * <li> <b>Repository Id</b> IDL:EventIRPSystem/ParameterNotSupported:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception ParameterNotSupported {
  ...
};
 * </pre>
 */
public final class ParameterNotSupportedHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.EventIRPSystem.ParameterNotSupported value;

public ParameterNotSupportedHolder () {
}

public ParameterNotSupportedHolder (final com.ericsson.irp.EventIRPSystem.ParameterNotSupported _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.EventIRPSystem.ParameterNotSupportedHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.EventIRPSystem.ParameterNotSupportedHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.EventIRPSystem.ParameterNotSupportedHelper.type();
}
}
