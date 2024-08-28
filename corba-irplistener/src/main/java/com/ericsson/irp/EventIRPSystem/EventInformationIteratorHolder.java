package com.ericsson.irp.EventIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "EventIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::EventIRPSystem::EventInformationIterator
 * <li> <b>Repository Id</b> IDL:EventIRPSystem/EventInformationIterator:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface EventInformationIterator {
  ...
};
 * </pre>
 */
public final class EventInformationIteratorHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.EventIRPSystem.EventInformationIterator value;

public EventInformationIteratorHolder () {
}

public EventInformationIteratorHolder (final com.ericsson.irp.EventIRPSystem.EventInformationIterator _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.EventIRPSystem.EventInformationIteratorHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.EventIRPSystem.EventInformationIteratorHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.EventIRPSystem.EventInformationIteratorHelper.type();
}
}
