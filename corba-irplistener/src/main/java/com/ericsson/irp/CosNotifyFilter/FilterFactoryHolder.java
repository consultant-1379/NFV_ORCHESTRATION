package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::FilterFactory
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/FilterFactory:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface FilterFactory {
  ...
};
 * </pre>
 */
public final class FilterFactoryHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNotifyFilter.FilterFactory value;

public FilterFactoryHolder () {
}

public FilterFactoryHolder (final com.ericsson.irp.CosNotifyFilter.FilterFactory _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNotifyFilter.FilterFactoryHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNotifyFilter.FilterFactoryHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNotifyFilter.FilterFactoryHelper.type();
}
}
