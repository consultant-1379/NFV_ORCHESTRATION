package com.ericsson.irp.EventIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "EventIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::EventIRPSystem::EventInformationIterator
 * <li> <b>Repository Id</b> IDL:EventIRPSystem/EventInformationIterator:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface EventInformationIterator {
  ...
};
 * </pre>
 */
public abstract class EventInformationIteratorPOA extends org.omg.PortableServer.Servant implements 
org.omg.CORBA.portable.InvokeHandler, com.ericsson.irp.EventIRPSystem.EventInformationIteratorOperations {

  public com.ericsson.irp.EventIRPSystem.EventInformationIterator _this () {
   return com.ericsson.irp.EventIRPSystem.EventInformationIteratorHelper.narrow(super._this_object());
  }

  public com.ericsson.irp.EventIRPSystem.EventInformationIterator _this (org.omg.CORBA.ORB orb) {
    return com.ericsson.irp.EventIRPSystem.EventInformationIteratorHelper.narrow(super._this_object(orb));
  }

  public java.lang.String[] _all_interfaces (final org.omg.PortableServer.POA poa, final byte[] objectId) {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:EventIRPSystem/EventInformationIterator:1.0"
  };

  private static java.util.Dictionary<String, int[]> _methods = new java.util.Hashtable<String, int[]>();

  static {
  _methods.put("next_eventInformations", new int[] { 0, 0 });
  _methods.put("destroy", new int[] { 0, 1 });
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
        return com.ericsson.irp.EventIRPSystem.EventInformationIteratorPOA._invoke(this, method[1], _input, handler);
      }
    }
    throw new org.omg.CORBA.BAD_OPERATION(2, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
  }

  public static org.omg.CORBA.portable.OutputStream _invoke (com.ericsson.irp.EventIRPSystem.EventInformationIteratorOperations _self,
                                                             int _method_id,
                                                             org.omg.CORBA.portable.InputStream _input,
                                                             org.omg.CORBA.portable.ResponseHandler _handler) {
  org.omg.CORBA.portable.OutputStream _output = null;
  {
    switch (_method_id) {
    case 0: {
    try {
      int how_many;
      how_many = _input.read_ulong();
      org.omg.CosNotification.EventBatchHolder event_informations = new org.omg.CosNotification.EventBatchHolder();
      boolean _result = _self.next_eventInformations(how_many, event_informations);
      _output = _handler.createReply();
      _output.write_boolean((boolean)_result);
      org.omg.CosNotification.EventBatchHelper.write(_output, event_informations.value);
    }
    catch (com.ericsson.irp.EventIRPSystem.NextEventInformations _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.EventIRPSystem.NextEventInformationsHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.EventIRPSystem.InvalidParameter _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.EventIRPSystem.InvalidParameterHelper.write(_output, _exception);
    }
      return _output;
    }
    case 1: {
      _self.destroy();
      _output = _handler.createReply();
      return _output;
    }
    }
    throw new org.omg.CORBA.BAD_OPERATION(2, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
  }
  }
}
