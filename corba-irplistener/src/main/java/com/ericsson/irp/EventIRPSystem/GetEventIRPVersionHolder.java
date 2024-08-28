package com.ericsson.irp.EventIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "EventIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::EventIRPSystem::GetEventIRPVersion
 * <li> <b>Repository Id</b> IDL:EventIRPSystem/GetEventIRPVersion:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception GetEventIRPVersion {
  ...
};
 * </pre>
 */
public final class GetEventIRPVersionHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.EventIRPSystem.GetEventIRPVersion value;

public GetEventIRPVersionHolder () {
}

public GetEventIRPVersionHolder (final com.ericsson.irp.EventIRPSystem.GetEventIRPVersion _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.EventIRPSystem.GetEventIRPVersionHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.EventIRPSystem.GetEventIRPVersionHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.EventIRPSystem.GetEventIRPVersionHelper.type();
}
}
