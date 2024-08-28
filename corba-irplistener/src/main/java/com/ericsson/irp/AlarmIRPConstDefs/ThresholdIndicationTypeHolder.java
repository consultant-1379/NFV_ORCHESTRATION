package com.ericsson.irp.AlarmIRPConstDefs;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPConstDefs.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPConstDefs::ThresholdIndicationType
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPConstDefs/ThresholdIndicationType:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * enum ThresholdIndicationType {Up, Down};
 * </pre>
 */
public final class ThresholdIndicationTypeHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.AlarmIRPConstDefs.ThresholdIndicationType value;

public ThresholdIndicationTypeHolder () {
}

public ThresholdIndicationTypeHolder (final com.ericsson.irp.AlarmIRPConstDefs.ThresholdIndicationType _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.AlarmIRPConstDefs.ThresholdIndicationTypeHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.AlarmIRPConstDefs.ThresholdIndicationTypeHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.AlarmIRPConstDefs.ThresholdIndicationTypeHelper.type();
}
}
