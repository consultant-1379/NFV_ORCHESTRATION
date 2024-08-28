package com.ericsson.irp.CosNaming;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNaming.idl"
 * <li> <b>IDL Name</b>      ::CosNaming::BindingType
 * <li> <b>Repository Id</b> IDL:omg.org/CosNaming/BindingType:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * enum BindingType {nobject, ncontext};
 * </pre>
 */
public final class BindingTypeHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNaming.BindingType value;

public BindingTypeHolder () {
}

public BindingTypeHolder (final com.ericsson.irp.CosNaming.BindingType _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNaming.BindingTypeHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNaming.BindingTypeHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNaming.BindingTypeHelper.type();
}
}
