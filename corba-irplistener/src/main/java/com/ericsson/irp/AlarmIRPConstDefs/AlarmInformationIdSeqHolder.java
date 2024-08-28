package com.ericsson.irp.AlarmIRPConstDefs;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPConstDefs.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPConstDefs::AlarmInformationIdSeq
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPConstDefs/AlarmInformationIdSeq:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * typedef sequence&ltstring&gt AlarmInformationIdSeq;
 * </pre>
 */
public final class AlarmInformationIdSeqHolder implements org.omg.CORBA.portable.Streamable {
public java.lang.String[] value;

public AlarmInformationIdSeqHolder () {
}

public AlarmInformationIdSeqHolder (final java.lang.String[] _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.AlarmIRPConstDefs.AlarmInformationIdSeqHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.AlarmIRPConstDefs.AlarmInformationIdSeqHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.AlarmIRPConstDefs.AlarmInformationIdSeqHelper.type();
}
}
