package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::FilterNotFound
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/FilterNotFound:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception FilterNotFound {
  ...
};
 * </pre>
 */
public final class FilterNotFoundHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNotifyFilter.FilterNotFound value;

public FilterNotFoundHolder () {
}

public FilterNotFoundHolder (final com.ericsson.irp.CosNotifyFilter.FilterNotFound _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNotifyFilter.FilterNotFoundHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNotifyFilter.FilterNotFoundHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNotifyFilter.FilterNotFoundHelper.type();
}
}
