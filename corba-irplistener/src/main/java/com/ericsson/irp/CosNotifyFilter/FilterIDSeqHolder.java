package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::FilterIDSeq
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/FilterIDSeq:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * typedef sequence&ltcom.ericsson.irp.CosNotifyFilter.FilterID&gt FilterIDSeq;
 * </pre>
 */
public final class FilterIDSeqHolder implements org.omg.CORBA.portable.Streamable {
public int[] value;

public FilterIDSeqHolder () {
}

public FilterIDSeqHolder (final int[] _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNotifyFilter.FilterIDSeqHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNotifyFilter.FilterIDSeqHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNotifyFilter.FilterIDSeqHelper.type();
}
}
