package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::Filter
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/Filter:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface Filter {
  ...
};
 * </pre>
 */
public final class FilterHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNotifyFilter.Filter value;

public FilterHolder () {
}

public FilterHolder (final com.ericsson.irp.CosNotifyFilter.Filter _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNotifyFilter.FilterHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNotifyFilter.FilterHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNotifyFilter.FilterHelper.type();
}
}
