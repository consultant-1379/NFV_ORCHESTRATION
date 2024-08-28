package com.ericsson.irp.AlarmIRPConstDefs;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPConstDefs.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPConstDefs::TrendIndicationType
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPConstDefs/TrendIndicationType:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * enum TrendIndicationType {LessSevere, NoChange, MoreSevere};
 * </pre>
 */
public final class TrendIndicationTypeHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.AlarmIRPConstDefs.TrendIndicationType value;

public TrendIndicationTypeHolder () {
}

public TrendIndicationTypeHolder (final com.ericsson.irp.AlarmIRPConstDefs.TrendIndicationType _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.AlarmIRPConstDefs.TrendIndicationTypeHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.AlarmIRPConstDefs.TrendIndicationTypeHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.AlarmIRPConstDefs.TrendIndicationTypeHelper.type();
}
}
