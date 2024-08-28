package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::ConstraintExpSeq
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/ConstraintExpSeq:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * typedef sequence&ltcom.ericsson.irp.CosNotifyFilter.ConstraintExp&gt ConstraintExpSeq;
 * </pre>
 */
public final class ConstraintExpSeqHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNotifyFilter.ConstraintExp[] value;

public ConstraintExpSeqHolder () {
}

public ConstraintExpSeqHolder (final com.ericsson.irp.CosNotifyFilter.ConstraintExp[] _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNotifyFilter.ConstraintExpSeqHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNotifyFilter.ConstraintExpSeqHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNotifyFilter.ConstraintExpSeqHelper.type();
}
}
