package com.ericsson.irp.EventIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "EventIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::EventIRPSystem::EventIRPOperations
 * <li> <b>Repository Id</b> IDL:EventIRPSystem/EventIRPOperations:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface _EventIRPOperations {
  ...
};
 * </pre>
 */
public abstract class _EventIRPOperationsPOA extends org.omg.PortableServer.Servant implements 
org.omg.CORBA.portable.InvokeHandler, com.ericsson.irp.EventIRPSystem._EventIRPOperationsOperations {

  public com.ericsson.irp.EventIRPSystem._EventIRPOperations _this () {
   return com.ericsson.irp.EventIRPSystem._EventIRPOperationsHelper.narrow(super._this_object());
  }

  public com.ericsson.irp.EventIRPSystem._EventIRPOperations _this (org.omg.CORBA.ORB orb) {
    return com.ericsson.irp.EventIRPSystem._EventIRPOperationsHelper.narrow(super._this_object(orb));
  }

  public java.lang.String[] _all_interfaces (final org.omg.PortableServer.POA poa, final byte[] objectId) {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:EventIRPSystem/EventIRPOperations:1.0"
  };

  private static java.util.Dictionary<String, int[]> _methods = new java.util.Hashtable<String, int[]>();

  static {
  _methods.put("get_event_list", new int[] { 0, 0 });
  _methods.put("get_event_IRP_version", new int[] { 0, 1 });
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
        return com.ericsson.irp.EventIRPSystem._EventIRPOperationsPOA._invoke(this, method[1], _input, handler);
      }
    }
    throw new org.omg.CORBA.BAD_OPERATION(2, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
  }

  public static org.omg.CORBA.portable.OutputStream _invoke (com.ericsson.irp.EventIRPSystem._EventIRPOperationsOperations _self,
                                                             int _method_id,
                                                             org.omg.CORBA.portable.InputStream _input,
                                                             org.omg.CORBA.portable.ResponseHandler _handler) {
  org.omg.CORBA.portable.OutputStream _output = null;
  {
    switch (_method_id) {
    case 0: {
    try {
      java.lang.String filter;
      filter = _input.read_string();
      org.omg.CORBA.BooleanHolder flag = new org.omg.CORBA.BooleanHolder();
      com.ericsson.irp.EventIRPSystem.EventInformationIteratorHolder iter = new com.ericsson.irp.EventIRPSystem.EventInformationIteratorHolder();
      org.omg.CosNotification.StructuredEvent[] _result = _self.get_event_list(filter, 
                                                                               flag, 
                                                                               iter);
      _output = _handler.createReply();
      org.omg.CosNotification.EventBatchHelper.write(_output, _result);
      _output.write_boolean((boolean)flag.value);
      com.ericsson.irp.EventIRPSystem.EventInformationIteratorHelper.write(_output, iter.value);
    }
    catch (com.ericsson.irp.EventIRPSystem.OperationNotSupported _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.EventIRPSystem.OperationNotSupportedHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.EventIRPSystem.ParameterNotSupported _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.EventIRPSystem.ParameterNotSupportedHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.EventIRPSystem.InvalidParameter _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.EventIRPSystem.InvalidParameterHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.EventIRPSystem.GetEventList _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.EventIRPSystem.GetEventListHelper.write(_output, _exception);
    }
      return _output;
    }
    case 1: {
    try {
      java.lang.String[] _result = _self.get_event_IRP_version();
      _output = _handler.createReply();
      com.ericsson.irp.EventIRPConstDefs.VersionNumberSetHelper.write(_output, _result);
    }
    catch (com.ericsson.irp.EventIRPSystem.GetEventIRPVersion _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.EventIRPSystem.GetEventIRPVersionHelper.write(_output, _exception);
    }
      return _output;
    }
    }
    throw new org.omg.CORBA.BAD_OPERATION(2, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
  }
  }
}
