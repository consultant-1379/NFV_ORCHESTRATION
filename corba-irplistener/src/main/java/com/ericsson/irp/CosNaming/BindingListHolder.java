package com.ericsson.irp.CosNaming;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNaming.idl"
 * <li> <b>IDL Name</b>      ::CosNaming::BindingList
 * <li> <b>Repository Id</b> IDL:omg.org/CosNaming/BindingList:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * typedef sequence&ltcom.ericsson.irp.CosNaming.Binding&gt BindingList;
 * </pre>
 */
public final class BindingListHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNaming.Binding[] value;

public BindingListHolder () {
}

public BindingListHolder (final com.ericsson.irp.CosNaming.Binding[] _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNaming.BindingListHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNaming.BindingListHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNaming.BindingListHelper.type();
}
}
