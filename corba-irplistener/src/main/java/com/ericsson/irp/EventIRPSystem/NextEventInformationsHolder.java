package com.ericsson.irp.EventIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "EventIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::EventIRPSystem::NextEventInformations
 * <li> <b>Repository Id</b> IDL:EventIRPSystem/NextEventInformations:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception NextEventInformations {
  ...
};
 * </pre>
 */
public final class NextEventInformationsHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.EventIRPSystem.NextEventInformations value;

public NextEventInformationsHolder () {
}

public NextEventInformationsHolder (final com.ericsson.irp.EventIRPSystem.NextEventInformations _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.EventIRPSystem.NextEventInformationsHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.EventIRPSystem.NextEventInformationsHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.EventIRPSystem.NextEventInformationsHelper.type();
}
}
