package com.ericsson.irp.AlarmIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPSystem::InvalidParameter
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPSystem/InvalidParameter:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception InvalidParameter {
  ...
};
 * </pre>
 */
public final class InvalidParameterHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.AlarmIRPSystem.InvalidParameter value;

public InvalidParameterHolder () {
}

public InvalidParameterHolder (final com.ericsson.irp.AlarmIRPSystem.InvalidParameter _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.AlarmIRPSystem.InvalidParameterHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.AlarmIRPSystem.InvalidParameterHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.AlarmIRPSystem.InvalidParameterHelper.type();
}
}
