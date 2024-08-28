package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::ConstraintNotFound
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/ConstraintNotFound:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception ConstraintNotFound {
  ...
};
 * </pre>
 */
public final class ConstraintNotFoundHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNotifyFilter.ConstraintNotFound value;

public ConstraintNotFoundHolder () {
}

public ConstraintNotFoundHolder (final com.ericsson.irp.CosNotifyFilter.ConstraintNotFound _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNotifyFilter.ConstraintNotFoundHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNotifyFilter.ConstraintNotFoundHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNotifyFilter.ConstraintNotFoundHelper.type();
}
}
