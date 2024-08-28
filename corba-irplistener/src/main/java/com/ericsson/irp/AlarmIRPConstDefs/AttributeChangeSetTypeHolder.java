package com.ericsson.irp.AlarmIRPConstDefs;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPConstDefs.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPConstDefs::AttributeChangeSetType
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPConstDefs/AttributeChangeSetType:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * typedef sequence&ltcom.ericsson.irp.AlarmIRPConstDefs.AttributeValueChangeType&gt AttributeChangeSetType;
 * </pre>
 */
public final class AttributeChangeSetTypeHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.AlarmIRPConstDefs.AttributeValueChangeType[] value;

public AttributeChangeSetTypeHolder () {
}

public AttributeChangeSetTypeHolder (final com.ericsson.irp.AlarmIRPConstDefs.AttributeValueChangeType[] _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.AlarmIRPConstDefs.AttributeChangeSetTypeHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.AlarmIRPConstDefs.AttributeChangeSetTypeHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.AlarmIRPConstDefs.AttributeChangeSetTypeHelper.type();
}
}
