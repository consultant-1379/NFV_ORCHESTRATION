package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::CallbackNotFound
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/CallbackNotFound:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception CallbackNotFound {
  ...
};
 * </pre>
 */
public final class CallbackNotFoundHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNotifyFilter.CallbackNotFound value;

public CallbackNotFoundHolder () {
}

public CallbackNotFoundHolder (final com.ericsson.irp.CosNotifyFilter.CallbackNotFound _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNotifyFilter.CallbackNotFoundHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNotifyFilter.CallbackNotFoundHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNotifyFilter.CallbackNotFoundHelper.type();
}
}
