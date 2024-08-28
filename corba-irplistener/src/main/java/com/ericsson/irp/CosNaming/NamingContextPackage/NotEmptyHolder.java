package com.ericsson.irp.CosNaming.NamingContextPackage;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNaming.idl"
 * <li> <b>IDL Name</b>      ::CosNaming::NamingContext::NotEmpty
 * <li> <b>Repository Id</b> IDL:omg.org/CosNaming/NamingContext/NotEmpty:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception NotEmpty {
  ...
};
 * </pre>
 */
public final class NotEmptyHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNaming.NamingContextPackage.NotEmpty value;

public NotEmptyHolder () {
}

public NotEmptyHolder (final com.ericsson.irp.CosNaming.NamingContextPackage.NotEmpty _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNaming.NamingContextPackage.NotEmptyHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNaming.NamingContextPackage.NotEmptyHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNaming.NamingContextPackage.NotEmptyHelper.type();
}
}
