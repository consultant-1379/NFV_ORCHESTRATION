package com.ericsson.irp.AlarmIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPSystem::NextAlarmInformations
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPSystem/NextAlarmInformations:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception NextAlarmInformations {
  ...
};
 * </pre>
 */
public final class NextAlarmInformationsHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.AlarmIRPSystem.NextAlarmInformations value;

public NextAlarmInformationsHolder () {
}

public NextAlarmInformationsHolder (final com.ericsson.irp.AlarmIRPSystem.NextAlarmInformations _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.AlarmIRPSystem.NextAlarmInformationsHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.AlarmIRPSystem.NextAlarmInformationsHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.AlarmIRPSystem.NextAlarmInformationsHelper.type();
}
}
