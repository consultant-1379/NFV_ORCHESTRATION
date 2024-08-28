package com.ericsson.irp.CosEventChannelAdmin;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventChannelAdmin.idl"
 * <li> <b>IDL Name</b>      ::CosEventChannelAdmin::ProxyPushSupplier
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventChannelAdmin/ProxyPushSupplier:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface ProxyPushSupplier : com.ericsson.irp.CosEventComm.PushSupplier {
  ...
};
 * </pre>
 */
public final class ProxyPushSupplierHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosEventChannelAdmin.ProxyPushSupplier value;

public ProxyPushSupplierHolder () {
}

public ProxyPushSupplierHolder (final com.ericsson.irp.CosEventChannelAdmin.ProxyPushSupplier _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosEventChannelAdmin.ProxyPushSupplierHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosEventChannelAdmin.ProxyPushSupplierHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosEventChannelAdmin.ProxyPushSupplierHelper.type();
}
}
