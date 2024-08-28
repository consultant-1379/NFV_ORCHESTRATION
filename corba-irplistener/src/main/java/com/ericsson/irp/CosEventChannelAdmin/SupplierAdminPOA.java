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
public abstract class SupplierAdminPOA extends org.omg.PortableServer.Servant implements 
org.omg.CORBA.portable.InvokeHandler, com.ericsson.irp.CosEventChannelAdmin.SupplierAdminOperations {

  public com.ericsson.irp.CosEventChannelAdmin.SupplierAdmin _this () {
   return com.ericsson.irp.CosEventChannelAdmin.SupplierAdminHelper.narrow(super._this_object());
  }

  public com.ericsson.irp.CosEventChannelAdmin.SupplierAdmin _this (org.omg.CORBA.ORB orb) {
    return com.ericsson.irp.CosEventChannelAdmin.SupplierAdminHelper.narrow(super._this_object(orb));
  }

  public java.lang.String[] _all_interfaces (final org.omg.PortableServer.POA poa, final byte[] objectId) {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:omg.org/CosEventChannelAdmin/SupplierAdmin:1.0"
  };

  private static java.util.Dictionary<String, int[]> _methods = new java.util.Hashtable<String, int[]>();

  static {
  _methods.put("obtain_push_consumer", new int[] { 0, 0 });
  _methods.put("obtain_pull_consumer", new int[] { 0, 1 });
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
        return com.ericsson.irp.CosEventChannelAdmin.SupplierAdminPOA._invoke(this, method[1], _input, handler);
      }
    }
    throw new org.omg.CORBA.BAD_OPERATION(2, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
  }

  public static org.omg.CORBA.portable.OutputStream _invoke (com.ericsson.irp.CosEventChannelAdmin.SupplierAdminOperations _self,
                                                             int _method_id,
                                                             org.omg.CORBA.portable.InputStream _input,
                                                             org.omg.CORBA.portable.ResponseHandler _handler) {
  org.omg.CORBA.portable.OutputStream _output = null;
  {
    switch (_method_id) {
    case 0: {
      com.ericsson.irp.CosEventChannelAdmin.ProxyPushConsumer _result = _self.obtain_push_consumer();
      _output = _handler.createReply();
      com.ericsson.irp.CosEventChannelAdmin.ProxyPushConsumerHelper.write(_output, _result);
      return _output;
    }
    case 1: {
      com.ericsson.irp.CosEventChannelAdmin.ProxyPullConsumer _result = _self.obtain_pull_consumer();
      _output = _handler.createReply();
      com.ericsson.irp.CosEventChannelAdmin.ProxyPullConsumerHelper.write(_output, _result);
      return _output;
    }
    }
    throw new org.omg.CORBA.BAD_OPERATION(2, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
  }
  }
}
