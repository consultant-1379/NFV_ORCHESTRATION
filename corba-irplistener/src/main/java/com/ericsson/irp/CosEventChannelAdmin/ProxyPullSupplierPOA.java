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
public abstract class ProxyPullSupplierPOA extends org.omg.PortableServer.Servant implements 
org.omg.CORBA.portable.InvokeHandler, com.ericsson.irp.CosEventChannelAdmin.ProxyPullSupplierOperations {

  public com.ericsson.irp.CosEventChannelAdmin.ProxyPullSupplier _this () {
   return com.ericsson.irp.CosEventChannelAdmin.ProxyPullSupplierHelper.narrow(super._this_object());
  }

  public com.ericsson.irp.CosEventChannelAdmin.ProxyPullSupplier _this (org.omg.CORBA.ORB orb) {
    return com.ericsson.irp.CosEventChannelAdmin.ProxyPullSupplierHelper.narrow(super._this_object(orb));
  }

  public java.lang.String[] _all_interfaces (final org.omg.PortableServer.POA poa, final byte[] objectId) {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:omg.org/CosEventChannelAdmin/ProxyPullSupplier:1.0",
    "IDL:omg.org/CosEventComm/PullSupplier:1.0"
  };

  private static java.util.Dictionary<String, int[]> _methods = new java.util.Hashtable<String, int[]>();

  static {
  _methods.put("connect_pull_consumer", new int[] { 0, 0 });
  _methods.put("pull", new int[] { 1, 0 });
  _methods.put("try_pull", new int[] { 1, 1 });
  _methods.put("disconnect_pull_supplier", new int[] { 1, 2 });
  }

  public org.omg.CORBA.portable.OutputStream _invoke (java.lang.String opName,
                                                      org.omg.CORBA.portable.InputStream _input,
                                                      org.omg.CORBA.portable.ResponseHandler handler) {
    int[] method = (int[]) _methods.get(opName);
    if (method == null) {
      throw new org.omg.CORBA.BAD_OPERATION();
    }
    switch (method[0]) {
      case 0: {
        return com.ericsson.irp.CosEventChannelAdmin.ProxyPullSupplierPOA._invoke(this, method[1], _input, handler);
      }
      case 1: {
        return com.ericsson.irp.CosEventComm.PullSupplierPOA._invoke(this, method[1], _input, handler);
      }
    }
    throw new org.omg.CORBA.BAD_OPERATION(2, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
  }

  public static org.omg.CORBA.portable.OutputStream _invoke (com.ericsson.irp.CosEventChannelAdmin.ProxyPullSupplierOperations _self,
                                                             int _method_id,
                                                             org.omg.CORBA.portable.InputStream _input,
                                                             org.omg.CORBA.portable.ResponseHandler _handler) {
  org.omg.CORBA.portable.OutputStream _output = null;
  {
    switch (_method_id) {
    case 0: {
    try {
      com.ericsson.irp.CosEventComm.PullConsumer pull_consumer;
      pull_consumer = com.ericsson.irp.CosEventComm.PullConsumerHelper.read(_input);
      _self.connect_pull_consumer(pull_consumer);
      _output = _handler.createReply();
    }
    catch (com.ericsson.irp.CosEventChannelAdmin.AlreadyConnected _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosEventChannelAdmin.AlreadyConnectedHelper.write(_output, _exception);
    }
      return _output;
    }
    }
    throw new org.omg.CORBA.BAD_OPERATION(2, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
  }
  }
}
