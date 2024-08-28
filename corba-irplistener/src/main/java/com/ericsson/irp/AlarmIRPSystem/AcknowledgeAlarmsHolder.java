package com.ericsson.irp.AlarmIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPSystem::AcknowledgeAlarms
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPSystem/AcknowledgeAlarms:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception AcknowledgeAlarms {
  ...
};
 * </pre>
 */
public final class AcknowledgeAlarmsHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.AlarmIRPSystem.AcknowledgeAlarms value;

public AcknowledgeAlarmsHolder () {
}

public AcknowledgeAlarmsHolder (final com.ericsson.irp.AlarmIRPSystem.AcknowledgeAlarms _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.AlarmIRPSystem.AcknowledgeAlarmsHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.AlarmIRPSystem.AcknowledgeAlarmsHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.AlarmIRPSystem.AcknowledgeAlarmsHelper.type();
}
}
