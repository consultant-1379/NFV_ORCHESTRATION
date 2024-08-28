package com.ericsson.irp.CosNaming.NamingContextPackage;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNaming.idl"
 * <li> <b>IDL Name</b>      ::CosNaming::NamingContext::NotFound
 * <li> <b>Repository Id</b> IDL:omg.org/CosNaming/NamingContext/NotFound:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception NotFound {
  ...
};
 * </pre>
 */
public final class NotFoundHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNaming.NamingContextPackage.NotFound value;

public NotFoundHolder () {
}

public NotFoundHolder (final com.ericsson.irp.CosNaming.NamingContextPackage.NotFound _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNaming.NamingContextPackage.NotFoundHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNaming.NamingContextPackage.NotFoundHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNaming.NamingContextPackage.NotFoundHelper.type();
}
}
