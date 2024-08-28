package com.ericsson.irp.AlarmIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPSystem::GetAlarmIRPVersion
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPSystem/GetAlarmIRPVersion:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception GetAlarmIRPVersion {
  ...
};
 * </pre>
 */
public final class GetAlarmIRPVersionHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.AlarmIRPSystem.GetAlarmIRPVersion value;

public GetAlarmIRPVersionHolder () {
}

public GetAlarmIRPVersionHolder (final com.ericsson.irp.AlarmIRPSystem.GetAlarmIRPVersion _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.AlarmIRPSystem.GetAlarmIRPVersionHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.AlarmIRPSystem.GetAlarmIRPVersionHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.AlarmIRPSystem.GetAlarmIRPVersionHelper.type();
}
}
