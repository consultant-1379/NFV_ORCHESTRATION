package com.ericsson.irp.CosNaming.NamingContextPackage;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNaming.idl"
 * <li> <b>IDL Name</b>      ::CosNaming::NamingContext::CannotProceed
 * <li> <b>Repository Id</b> IDL:omg.org/CosNaming/NamingContext/CannotProceed:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception CannotProceed {
  ...
};
 * </pre>
 */
public final class CannotProceedHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed value;

public CannotProceedHolder () {
}

public CannotProceedHolder (final com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceedHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceedHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceedHelper.type();
}
}
