package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::UnsupportedFilterableData
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/UnsupportedFilterableData:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception UnsupportedFilterableData {
  ...
};
 * </pre>
 */
public final class UnsupportedFilterableDataHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData value;

public UnsupportedFilterableDataHolder () {
}

public UnsupportedFilterableDataHolder (final com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableDataHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableDataHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableDataHelper.type();
}
}
