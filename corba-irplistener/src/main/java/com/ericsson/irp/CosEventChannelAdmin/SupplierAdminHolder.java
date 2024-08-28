package com.ericsson.irp.CosEventChannelAdmin;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventChannelAdmin.idl"
 * <li> <b>IDL Name</b>      ::CosEventChannelAdmin::SupplierAdmin
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventChannelAdmin/SupplierAdmin:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface SupplierAdmin {
  ...
};
 * </pre>
 */
public final class SupplierAdminHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosEventChannelAdmin.SupplierAdmin value;

public SupplierAdminHolder () {
}

public SupplierAdminHolder (final com.ericsson.irp.CosEventChannelAdmin.SupplierAdmin _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosEventChannelAdmin.SupplierAdminHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosEventChannelAdmin.SupplierAdminHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosEventChannelAdmin.SupplierAdminHelper.type();
}
}
