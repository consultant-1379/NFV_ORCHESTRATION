package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::DuplicateConstraintID
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/DuplicateConstraintID:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception DuplicateConstraintID {
  ...
};
 * </pre>
 */
public final class DuplicateConstraintIDHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNotifyFilter.DuplicateConstraintID value;

public DuplicateConstraintIDHolder () {
}

public DuplicateConstraintIDHolder (final com.ericsson.irp.CosNotifyFilter.DuplicateConstraintID _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNotifyFilter.DuplicateConstraintIDHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNotifyFilter.DuplicateConstraintIDHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNotifyFilter.DuplicateConstraintIDHelper.type();
}
}
