package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::ConstraintInfoSeq
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/ConstraintInfoSeq:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * typedef sequence&ltcom.ericsson.irp.CosNotifyFilter.ConstraintInfo&gt ConstraintInfoSeq;
 * </pre>
 */
public final class ConstraintInfoSeqHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNotifyFilter.ConstraintInfo[] value;

public ConstraintInfoSeqHolder () {
}

public ConstraintInfoSeqHolder (final com.ericsson.irp.CosNotifyFilter.ConstraintInfo[] _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNotifyFilter.ConstraintInfoSeqHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNotifyFilter.ConstraintInfoSeqHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNotifyFilter.ConstraintInfoSeqHelper.type();
}
}
