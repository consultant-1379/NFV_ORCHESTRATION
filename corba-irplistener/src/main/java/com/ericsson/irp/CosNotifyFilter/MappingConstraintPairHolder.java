package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::MappingConstraintPair
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/MappingConstraintPair:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * struct MappingConstraintPair {
  ...
};
 * </pre>
 */
public final class MappingConstraintPairHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNotifyFilter.MappingConstraintPair value;

public MappingConstraintPairHolder () {
}

public MappingConstraintPairHolder (final com.ericsson.irp.CosNotifyFilter.MappingConstraintPair _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNotifyFilter.MappingConstraintPairHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNotifyFilter.MappingConstraintPairHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNotifyFilter.MappingConstraintPairHelper.type();
}
}
