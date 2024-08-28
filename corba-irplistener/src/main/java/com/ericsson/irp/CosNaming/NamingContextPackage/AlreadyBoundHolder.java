package com.ericsson.irp.CosNaming.NamingContextPackage;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNaming.idl"
 * <li> <b>IDL Name</b>      ::CosNaming::NamingContext::AlreadyBound
 * <li> <b>Repository Id</b> IDL:omg.org/CosNaming/NamingContext/AlreadyBound:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception AlreadyBound {
  ...
};
 * </pre>
 */
public final class AlreadyBoundHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBound value;

public AlreadyBoundHolder () {
}

public AlreadyBoundHolder (final com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBound _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBoundHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBoundHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBoundHelper.type();
}
}
