package com.ericsson.irp.CosNaming;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNaming.idl"
 * <li> <b>IDL Name</b>      ::CosNaming::Binding
 * <li> <b>Repository Id</b> IDL:omg.org/CosNaming/Binding:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * struct Binding {
  ...
};
 * </pre>
 */
public final class BindingHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNaming.Binding value;

public BindingHolder () {
}

public BindingHolder (final com.ericsson.irp.CosNaming.Binding _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNaming.BindingHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNaming.BindingHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNaming.BindingHelper.type();
}
}
