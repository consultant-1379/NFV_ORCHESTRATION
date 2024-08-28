package com.ericsson.irp.EventIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "EventIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::EventIRPSystem::EventIRPOperations
 * <li> <b>Repository Id</b> IDL:EventIRPSystem/EventIRPOperations:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface _EventIRPOperations {
  ...
};
 * </pre>
 */
public final class _EventIRPOperationsHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.EventIRPSystem._EventIRPOperations value;

public _EventIRPOperationsHolder () {
}

public _EventIRPOperationsHolder (final com.ericsson.irp.EventIRPSystem._EventIRPOperations _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.EventIRPSystem._EventIRPOperationsHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.EventIRPSystem._EventIRPOperationsHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.EventIRPSystem._EventIRPOperationsHelper.type();
}
}
