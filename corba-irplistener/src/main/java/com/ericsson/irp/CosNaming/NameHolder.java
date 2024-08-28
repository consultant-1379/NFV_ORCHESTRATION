package com.ericsson.irp.CosNaming;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNaming.idl"
 * <li> <b>IDL Name</b>      ::CosNaming::Name
 * <li> <b>Repository Id</b> IDL:omg.org/CosNaming/Name:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * typedef sequence&ltcom.ericsson.irp.CosNaming.NameComponent&gt Name;
 * </pre>
 */
public final class NameHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNaming.NameComponent[] value;

public NameHolder () {
}

public NameHolder (final com.ericsson.irp.CosNaming.NameComponent[] _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNaming.NameHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNaming.NameHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNaming.NameHelper.type();
}
}
