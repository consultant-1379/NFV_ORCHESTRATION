package com.ericsson.irp.CosEventChannelAdmin;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventChannelAdmin.idl"
 * <li> <b>IDL Name</b>      ::CosEventChannelAdmin::ProxyPullSupplier
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventChannelAdmin/ProxyPullSupplier:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface ProxyPullSupplier : com.ericsson.irp.CosEventComm.PullSupplier {
  ...
};
 * </pre>
 */
public final class ProxyPullSupplierHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosEventChannelAdmin.ProxyPullSupplier value;

public ProxyPullSupplierHolder () {
}

public ProxyPullSupplierHolder (final com.ericsson.irp.CosEventChannelAdmin.ProxyPullSupplier _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosEventChannelAdmin.ProxyPullSupplierHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosEventChannelAdmin.ProxyPullSupplierHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosEventChannelAdmin.ProxyPullSupplierHelper.type();
}
}
