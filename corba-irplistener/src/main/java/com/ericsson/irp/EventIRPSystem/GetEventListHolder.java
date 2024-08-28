package com.ericsson.irp.EventIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "EventIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::EventIRPSystem::GetEventList
 * <li> <b>Repository Id</b> IDL:EventIRPSystem/GetEventList:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception GetEventList {
  ...
};
 * </pre>
 */
public final class GetEventListHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.EventIRPSystem.GetEventList value;

public GetEventListHolder () {
}

public GetEventListHolder (final com.ericsson.irp.EventIRPSystem.GetEventList _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.EventIRPSystem.GetEventListHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.EventIRPSystem.GetEventListHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.EventIRPSystem.GetEventListHelper.type();
}
}
