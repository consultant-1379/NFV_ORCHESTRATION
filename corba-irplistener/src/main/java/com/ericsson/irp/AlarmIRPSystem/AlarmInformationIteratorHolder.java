package com.ericsson.irp.AlarmIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPSystem::AlarmInformationIterator
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPSystem/AlarmInformationIterator:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface AlarmInformationIterator {
  ...
};
 * </pre>
 */
public final class AlarmInformationIteratorHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.AlarmIRPSystem.AlarmInformationIterator value;

public AlarmInformationIteratorHolder () {
}

public AlarmInformationIteratorHolder (final com.ericsson.irp.AlarmIRPSystem.AlarmInformationIterator _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.AlarmIRPSystem.AlarmInformationIteratorHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.AlarmIRPSystem.AlarmInformationIteratorHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.AlarmIRPSystem.AlarmInformationIteratorHelper.type();
}
}
