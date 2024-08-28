package com.ericsson.irp.CosNaming.NamingContextPackage;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNaming.idl"
 * <li> <b>IDL Name</b>      ::CosNaming::NamingContext::InvalidName
 * <li> <b>Repository Id</b> IDL:omg.org/CosNaming/NamingContext/InvalidName:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception InvalidName {
  ...
};
 * </pre>
 */
public final class InvalidNameHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName value;

public InvalidNameHolder () {
}

public InvalidNameHolder (final com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNaming.NamingContextPackage.InvalidNameHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNaming.NamingContextPackage.InvalidNameHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNaming.NamingContextPackage.InvalidNameHelper.type();
}
}
