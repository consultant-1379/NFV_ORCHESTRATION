package com.ericsson.irp.CosEventComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventComm.idl"
 * <li> <b>IDL Name</b>      ::CosEventComm::PushConsumer
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventComm/PushConsumer:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface PushConsumer {
  ...
};
 * </pre>
 */
public abstract class PushConsumerPOA extends org.omg.PortableServer.Servant implements 
org.omg.CORBA.portable.InvokeHandler, com.ericsson.irp.CosEventComm.PushConsumerOperations {

  public com.ericsson.irp.CosEventComm.PushConsumer _this () {
   return com.ericsson.irp.CosEventComm.PushConsumerHelper.narrow(super._this_object());
  }

  public com.ericsson.irp.CosEventComm.PushConsumer _this (org.omg.CORBA.ORB orb) {
    return com.ericsson.irp.CosEventComm.PushConsumerHelper.narrow(super._this_object(orb));
  }

  public java.lang.String[] _all_interfaces (final org.omg.PortableServer.POA poa, final byte[] objectId) {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:omg.org/CosEventComm/PushConsumer:1.0"
  };

  private static java.util.Dictionary<String, int[]> _methods = new java.util.Hashtable<String, int[]>();

  static {
  _methods.put("push", new int[] { 0, 0 });
  _methods.put("disconnect_push_consumer", new int[] { 0, 1 });
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
        return com.ericsson.irp.CosEventComm.PushConsumerPOA._invoke(this, method[1], _input, handler);
      }
    }
    throw new org.omg.CORBA.BAD_OPERATION(2, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
  }

  public static org.omg.CORBA.portable.OutputStream _invoke (com.ericsson.irp.CosEventComm.PushConsumerOperations _self,
                                                             int _method_id,
                                                             org.omg.CORBA.portable.InputStream _input,
                                                             org.omg.CORBA.portable.ResponseHandler _handler) {
  org.omg.CORBA.portable.OutputStream _output = null;
  {
    switch (_method_id) {
    case 0: {
    try {
      org.omg.CORBA.Any data;
      data = _input.read_any();
      _self.push(data);
      _output = _handler.createReply();
    }
    catch (com.ericsson.irp.CosEventComm.Disconnected _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosEventComm.DisconnectedHelper.write(_output, _exception);
    }
      return _output;
    }
    case 1: {
      _self.disconnect_push_consumer();
      _output = _handler.createReply();
      return _output;
    }
    }
    throw new org.omg.CORBA.BAD_OPERATION(2, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
  }
  }
}
