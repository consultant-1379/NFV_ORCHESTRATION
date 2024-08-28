package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::InvalidConstraint
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/InvalidConstraint:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception InvalidConstraint {
  ...
};
 * </pre>
 */
public final class InvalidConstraintHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNotifyFilter.InvalidConstraint value;

public InvalidConstraintHolder () {
}

public InvalidConstraintHolder (final com.ericsson.irp.CosNotifyFilter.InvalidConstraint _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNotifyFilter.InvalidConstraintHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNotifyFilter.InvalidConstraintHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNotifyFilter.InvalidConstraintHelper.type();
}
}
