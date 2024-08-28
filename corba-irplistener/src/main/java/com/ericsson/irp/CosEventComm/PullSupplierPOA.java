package com.ericsson.irp.CosEventComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventComm.idl"
 * <li> <b>IDL Name</b>      ::CosEventComm::PullSupplier
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventComm/PullSupplier:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface PullSupplier {
  ...
};
 * </pre>
 */
public abstract class PullSupplierPOA extends org.omg.PortableServer.Servant implements 
org.omg.CORBA.portable.InvokeHandler, com.ericsson.irp.CosEventComm.PullSupplierOperations {

  public com.ericsson.irp.CosEventComm.PullSupplier _this () {
   return com.ericsson.irp.CosEventComm.PullSupplierHelper.narrow(super._this_object());
  }

  public com.ericsson.irp.CosEventComm.PullSupplier _this (org.omg.CORBA.ORB orb) {
    return com.ericsson.irp.CosEventComm.PullSupplierHelper.narrow(super._this_object(orb));
  }

  public java.lang.String[] _all_interfaces (final org.omg.PortableServer.POA poa, final byte[] objectId) {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:omg.org/CosEventComm/PullSupplier:1.0"
  };

  private static java.util.Dictionary<String, int[]> _methods = new java.util.Hashtable<String, int[]>();

  static {
  _methods.put("pull", new int[] { 0, 0 });
  _methods.put("try_pull", new int[] { 0, 1 });
  _methods.put("disconnect_pull_supplier", new int[] { 0, 2 });
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
        return com.ericsson.irp.CosEventComm.PullSupplierPOA._invoke(this, method[1], _input, handler);
      }
    }
    throw new org.omg.CORBA.BAD_OPERATION(2, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
  }

  public static org.omg.CORBA.portable.OutputStream _invoke (com.ericsson.irp.CosEventComm.PullSupplierOperations _self,
                                                             int _method_id,
                                                             org.omg.CORBA.portable.InputStream _input,
                                                             org.omg.CORBA.portable.ResponseHandler _handler) {
  org.omg.CORBA.portable.OutputStream _output = null;
  {
    switch (_method_id) {
    case 0: {
    try {
      org.omg.CORBA.Any _result = _self.pull();
      _output = _handler.createReply();
      _output.write_any((org.omg.CORBA.Any)_result);
    }
    catch (com.ericsson.irp.CosEventComm.Disconnected _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosEventComm.DisconnectedHelper.write(_output, _exception);
    }
      return _output;
    }
    case 1: {
    try {
      org.omg.CORBA.BooleanHolder has_event = new org.omg.CORBA.BooleanHolder();
      org.omg.CORBA.Any _result = _self.try_pull(has_event);
      _output = _handler.createReply();
      _output.write_any((org.omg.CORBA.Any)_result);
      _output.write_boolean((boolean)has_event.value);
    }
    catch (com.ericsson.irp.CosEventComm.Disconnected _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosEventComm.DisconnectedHelper.write(_output, _exception);
    }
      return _output;
    }
    case 2: {
      _self.disconnect_pull_supplier();
      _output = _handler.createReply();
      return _output;
    }
    }
    throw new org.omg.CORBA.BAD_OPERATION(2, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
  }
  }
}
