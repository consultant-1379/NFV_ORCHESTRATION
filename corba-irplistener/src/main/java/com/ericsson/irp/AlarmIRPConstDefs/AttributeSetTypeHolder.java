package com.ericsson.irp.AlarmIRPConstDefs;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPConstDefs.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPConstDefs::AttributeSetType
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPConstDefs/AttributeSetType:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * typedef sequence&ltcom.ericsson.irp.AlarmIRPConstDefs.AttributeValueType&gt AttributeSetType;
 * </pre>
 */
public final class AttributeSetTypeHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.AlarmIRPConstDefs.AttributeValueType[] value;

public AttributeSetTypeHolder () {
}

public AttributeSetTypeHolder (final com.ericsson.irp.AlarmIRPConstDefs.AttributeValueType[] _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.AlarmIRPConstDefs.AttributeSetTypeHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.AlarmIRPConstDefs.AttributeSetTypeHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.AlarmIRPConstDefs.AttributeSetTypeHelper.type();
}
}
