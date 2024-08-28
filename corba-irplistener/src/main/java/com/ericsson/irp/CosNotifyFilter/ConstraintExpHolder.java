package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::ConstraintExp
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/ConstraintExp:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * struct ConstraintExp {
  ...
};
 * </pre>
 */
public final class ConstraintExpHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNotifyFilter.ConstraintExp value;

public ConstraintExpHolder () {
}

public ConstraintExpHolder (final com.ericsson.irp.CosNotifyFilter.ConstraintExp _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNotifyFilter.ConstraintExpHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNotifyFilter.ConstraintExpHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNotifyFilter.ConstraintExpHelper.type();
}
}
