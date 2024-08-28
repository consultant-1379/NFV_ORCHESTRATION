package com.ericsson.irp.CosNaming;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNaming.idl"
 * <li> <b>IDL Name</b>      ::CosNaming::BindingIterator
 * <li> <b>Repository Id</b> IDL:omg.org/CosNaming/BindingIterator:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface BindingIterator {
  ...
};
 * </pre>
 */
public final class BindingIteratorHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNaming.BindingIterator value;

public BindingIteratorHolder () {
}

public BindingIteratorHolder (final com.ericsson.irp.CosNaming.BindingIterator _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNaming.BindingIteratorHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNaming.BindingIteratorHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNaming.BindingIteratorHelper.type();
}
}
