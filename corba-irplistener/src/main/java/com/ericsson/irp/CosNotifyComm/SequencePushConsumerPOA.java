package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::SequencePushConsumer
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/SequencePushConsumer:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface SequencePushConsumer : com.ericsson.irp.CosNotifyComm.NotifyPublish {
  ...
};
 * </pre>
 */
public abstract class SequencePushConsumerPOA extends org.omg.PortableServer.Servant implements 
org.omg.CORBA.portable.InvokeHandler, com.ericsson.irp.CosNotifyComm.SequencePushConsumerOperations {

  public com.ericsson.irp.CosNotifyComm.SequencePushConsumer _this () {
   return com.ericsson.irp.CosNotifyComm.SequencePushConsumerHelper.narrow(super._this_object());
  }

  public com.ericsson.irp.CosNotifyComm.SequencePushConsumer _this (org.omg.CORBA.ORB orb) {
    return com.ericsson.irp.CosNotifyComm.SequencePushConsumerHelper.narrow(super._this_object(orb));
  }

  public java.lang.String[] _all_interfaces (final org.omg.PortableServer.POA poa, final byte[] objectId) {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:omg.org/CosNotifyComm/SequencePushConsumer:1.0",
    "IDL:omg.org/CosNotifyComm/NotifyPublish:1.0"
  };

  private static java.util.Dictionary<String, int[]> _methods = new java.util.Hashtable<String, int[]>();

  static {
  _methods.put("push_structured_events", new int[] { 0, 0 });
  _methods.put("disconnect_sequence_push_consumer", new int[] { 0, 1 });
  _methods.put("offer_change", new int[] { 1, 0 });
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
        return com.ericsson.irp.CosNotifyComm.SequencePushConsumerPOA._invoke(this, method[1], _input, handler);
      }
      case 1: {
        return com.ericsson.irp.CosNotifyComm.NotifyPublishPOA._invoke(this, method[1], _input, handler);
      }
    }
    throw new org.omg.CORBA.BAD_OPERATION(2, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
  }

  public static org.omg.CORBA.portable.OutputStream _invoke (com.ericsson.irp.CosNotifyComm.SequencePushConsumerOperations _self,
                                                             int _method_id,
                                                             org.omg.CORBA.portable.InputStream _input,
                                                             org.omg.CORBA.portable.ResponseHandler _handler) {
  org.omg.CORBA.portable.OutputStream _output = null;
  {
    switch (_method_id) {
    case 0: {
    try {
      org.omg.CosNotification.StructuredEvent[] notifications;
      notifications = org.omg.CosNotification.EventBatchHelper.read(_input);
      _self.push_structured_events(notifications);
      _output = _handler.createReply();
    }
    catch (com.ericsson.irp.CosEventComm.Disconnected _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosEventComm.DisconnectedHelper.write(_output, _exception);
    }
      return _output;
    }
    case 1: {
      _self.disconnect_sequence_push_consumer();
      _output = _handler.createReply();
      return _output;
    }
    }
    throw new org.omg.CORBA.BAD_OPERATION(2, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
  }
  }
}
