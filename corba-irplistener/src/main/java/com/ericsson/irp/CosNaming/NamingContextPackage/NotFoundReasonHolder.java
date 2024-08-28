package com.ericsson.irp.CosNaming.NamingContextPackage;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNaming.idl"
 * <li> <b>IDL Name</b>      ::CosNaming::NamingContext::NotFoundReason
 * <li> <b>Repository Id</b> IDL:omg.org/CosNaming/NamingContext/NotFoundReason:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * enum NotFoundReason {missing_node, not_context, not_object};
 * </pre>
 */
public final class NotFoundReasonHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNaming.NamingContextPackage.NotFoundReason value;

public NotFoundReasonHolder () {
}

public NotFoundReasonHolder (final com.ericsson.irp.CosNaming.NamingContextPackage.NotFoundReason _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNaming.NamingContextPackage.NotFoundReasonHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNaming.NamingContextPackage.NotFoundReasonHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNaming.NamingContextPackage.NotFoundReasonHelper.type();
}
}
