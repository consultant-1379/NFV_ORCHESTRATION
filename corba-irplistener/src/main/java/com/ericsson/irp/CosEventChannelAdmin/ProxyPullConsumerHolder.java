package com.ericsson.irp.CosEventChannelAdmin;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventChannelAdmin.idl"
 * <li> <b>IDL Name</b>      ::CosEventChannelAdmin::ProxyPullConsumer
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventChannelAdmin/ProxyPullConsumer:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface ProxyPullConsumer : com.ericsson.irp.CosEventComm.PullConsumer {
  ...
};
 * </pre>
 */
public final class ProxyPullConsumerHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosEventChannelAdmin.ProxyPullConsumer value;

public ProxyPullConsumerHolder () {
}

public ProxyPullConsumerHolder (final com.ericsson.irp.CosEventChannelAdmin.ProxyPullConsumer _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosEventChannelAdmin.ProxyPullConsumerHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosEventChannelAdmin.ProxyPullConsumerHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosEventChannelAdmin.ProxyPullConsumerHelper.type();
}
}
