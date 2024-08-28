package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::MappingConstraintInfoSeq
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/MappingConstraintInfoSeq:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * typedef sequence&ltcom.ericsson.irp.CosNotifyFilter.MappingConstraintInfo&gt MappingConstraintInfoSeq;
 * </pre>
 */
public final class MappingConstraintInfoSeqHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNotifyFilter.MappingConstraintInfo[] value;

public MappingConstraintInfoSeqHolder () {
}

public MappingConstraintInfoSeqHolder (final com.ericsson.irp.CosNotifyFilter.MappingConstraintInfo[] _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNotifyFilter.MappingConstraintInfoSeqHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNotifyFilter.MappingConstraintInfoSeqHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNotifyFilter.MappingConstraintInfoSeqHelper.type();
}
}
