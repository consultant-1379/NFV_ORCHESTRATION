package com.ericsson.irp.EventIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "EventIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::EventIRPSystem::InvalidParameter
 * <li> <b>Repository Id</b> IDL:EventIRPSystem/InvalidParameter:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception InvalidParameter {
  ...
};
 * </pre>
 */
public final class InvalidParameterHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.EventIRPSystem.InvalidParameter value;

public InvalidParameterHolder () {
}

public InvalidParameterHolder (final com.ericsson.irp.EventIRPSystem.InvalidParameter _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.EventIRPSystem.InvalidParameterHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.EventIRPSystem.InvalidParameterHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.EventIRPSystem.InvalidParameterHelper.type();
}
}
