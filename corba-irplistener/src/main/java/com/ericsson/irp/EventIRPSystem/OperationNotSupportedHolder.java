package com.ericsson.irp.EventIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "EventIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::EventIRPSystem::OperationNotSupported
 * <li> <b>Repository Id</b> IDL:EventIRPSystem/OperationNotSupported:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception OperationNotSupported {
  ...
};
 * </pre>
 */
public final class OperationNotSupportedHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.EventIRPSystem.OperationNotSupported value;

public OperationNotSupportedHolder () {
}

public OperationNotSupportedHolder (final com.ericsson.irp.EventIRPSystem.OperationNotSupported _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.EventIRPSystem.OperationNotSupportedHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.EventIRPSystem.OperationNotSupportedHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.EventIRPSystem.OperationNotSupportedHelper.type();
}
}
