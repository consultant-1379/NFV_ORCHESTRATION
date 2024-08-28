package com.ericsson.irp.AlarmIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPSystem::GetAlarmCount
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPSystem/GetAlarmCount:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception GetAlarmCount {
  ...
};
 * </pre>
 */
public final class GetAlarmCountHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.AlarmIRPSystem.GetAlarmCount value;

public GetAlarmCountHolder () {
}

public GetAlarmCountHolder (final com.ericsson.irp.AlarmIRPSystem.GetAlarmCount _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.AlarmIRPSystem.GetAlarmCountHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.AlarmIRPSystem.GetAlarmCountHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.AlarmIRPSystem.GetAlarmCountHelper.type();
}
}
