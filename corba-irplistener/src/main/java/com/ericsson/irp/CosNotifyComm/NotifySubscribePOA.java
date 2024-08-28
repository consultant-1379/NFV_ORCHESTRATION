package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::NotifySubscribe
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/NotifySubscribe:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface NotifySubscribe {
  ...
};
 * </pre>
 */
public abstract class NotifySubscribePOA extends org.omg.PortableServer.Servant implements 
org.omg.CORBA.portable.InvokeHandler, com.ericsson.irp.CosNotifyComm.NotifySubscribeOperations {

  public com.ericsson.irp.CosNotifyComm.NotifySubscribe _this () {
   return com.ericsson.irp.CosNotifyComm.NotifySubscribeHelper.narrow(super._this_object());
  }

  public com.ericsson.irp.CosNotifyComm.NotifySubscribe _this (org.omg.CORBA.ORB orb) {
    return com.ericsson.irp.CosNotifyComm.NotifySubscribeHelper.narrow(super._this_object(orb));
  }

  public java.lang.String[] _all_interfaces (final org.omg.PortableServer.POA poa, final byte[] objectId) {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:omg.org/CosNotifyComm/NotifySubscribe:1.0"
  };

  private static java.util.Dictionary<String, int[]> _methods = new java.util.Hashtable<String, int[]>();

  static {
  _methods.put("subscription_change", new int[] { 0, 0 });
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
        return com.ericsson.irp.CosNotifyComm.NotifySubscribePOA._invoke(this, method[1], _input, handler);
      }
    }
    throw new org.omg.CORBA.BAD_OPERATION(2, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
  }

  public static org.omg.CORBA.portable.OutputStream _invoke (com.ericsson.irp.CosNotifyComm.NotifySubscribeOperations _self,
                                                             int _method_id,
                                                             org.omg.CORBA.portable.InputStream _input,
                                                             org.omg.CORBA.portable.ResponseHandler _handler) {
  org.omg.CORBA.portable.OutputStream _output = null;
  {
    switch (_method_id) {
    case 0: {
    try {
      org.omg.CosNotification.EventType[] added;
      added = org.omg.CosNotification.EventTypeSeqHelper.read(_input);
      org.omg.CosNotification.EventType[] removed;
      removed = org.omg.CosNotification.EventTypeSeqHelper.read(_input);
      _self.subscription_change(added, removed);
      _output = _handler.createReply();
    }
    catch (com.ericsson.irp.CosNotifyComm.InvalidEventType _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNotifyComm.InvalidEventTypeHelper.write(_output, _exception);
    }
      return _output;
    }
    }
    throw new org.omg.CORBA.BAD_OPERATION(2, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
  }
  }
}
