package com.ericsson.irp.AlarmIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPSystem::AlarmIRPOperations
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPSystem/AlarmIRPOperations:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface _AlarmIRPOperations {
  ...
};
 * </pre>
 */
public final class _AlarmIRPOperationsHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.AlarmIRPSystem._AlarmIRPOperations value;

public _AlarmIRPOperationsHolder () {
}

public _AlarmIRPOperationsHolder (final com.ericsson.irp.AlarmIRPSystem._AlarmIRPOperations _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.AlarmIRPSystem._AlarmIRPOperationsHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.AlarmIRPSystem._AlarmIRPOperationsHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.AlarmIRPSystem._AlarmIRPOperationsHelper.type();
}
}
