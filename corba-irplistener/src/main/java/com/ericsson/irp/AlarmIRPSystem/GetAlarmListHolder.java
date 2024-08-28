package com.ericsson.irp.AlarmIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPSystem::GetAlarmList
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPSystem/GetAlarmList:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception GetAlarmList {
  ...
};
 * </pre>
 */
public final class GetAlarmListHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.AlarmIRPSystem.GetAlarmList value;

public GetAlarmListHolder () {
}

public GetAlarmListHolder (final com.ericsson.irp.AlarmIRPSystem.GetAlarmList _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.AlarmIRPSystem.GetAlarmListHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.AlarmIRPSystem.GetAlarmListHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.AlarmIRPSystem.GetAlarmListHelper.type();
}
}
