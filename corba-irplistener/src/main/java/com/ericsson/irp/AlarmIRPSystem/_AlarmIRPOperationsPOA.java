package com.ericsson.irp.AlarmIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPSystem::AlarmIRPOperations
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPSystem/AlarmIRPOperations:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface _AlarmIRPOperations {
  ...
};
 * </pre>
 */
@SuppressWarnings("unchecked")
public abstract class _AlarmIRPOperationsPOA extends org.omg.PortableServer.Servant implements 
org.omg.CORBA.portable.InvokeHandler, com.ericsson.irp.AlarmIRPSystem._AlarmIRPOperationsOperations {

  public com.ericsson.irp.AlarmIRPSystem._AlarmIRPOperations _this () {
   return com.ericsson.irp.AlarmIRPSystem._AlarmIRPOperationsHelper.narrow(super._this_object());
  }

  public com.ericsson.irp.AlarmIRPSystem._AlarmIRPOperations _this (org.omg.CORBA.ORB orb) {
    return com.ericsson.irp.AlarmIRPSystem._AlarmIRPOperationsHelper.narrow(super._this_object(orb));
  }

  public java.lang.String[] _all_interfaces (final org.omg.PortableServer.POA poa, final byte[] objectId) {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:3gppsa5.org/AlarmIRPSystem/AlarmIRPOperations:1.0"
  };

  @SuppressWarnings("rawtypes")
private static java.util.Dictionary _methods = new java.util.Hashtable();

  static {
  _methods.put("acknowledge_alarms", new int[] { 0, 0 });
  _methods.put("unacknowledge_alarms", new int[] { 0, 1 });
  _methods.put("get_alarm_list", new int[] { 0, 2 });
  _methods.put("get_alarm_count", new int[] { 0, 3 });
  _methods.put("get_alarm_IRP_version", new int[] { 0, 4 });
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
        return com.ericsson.irp.AlarmIRPSystem._AlarmIRPOperationsPOA._invoke(this, method[1], _input, handler);
      }
    }
    throw new org.omg.CORBA.BAD_OPERATION(2, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
  }

  public static org.omg.CORBA.portable.OutputStream _invoke (com.ericsson.irp.AlarmIRPSystem._AlarmIRPOperationsOperations _self,
                                                             int _method_id,
                                                             org.omg.CORBA.portable.InputStream _input,
                                                             org.omg.CORBA.portable.ResponseHandler _handler) {
  org.omg.CORBA.portable.OutputStream _output = null;
  {
    switch (_method_id) {
    case 0: {
    try {
      java.lang.String[] alarm_information_id_list;
      alarm_information_id_list = com.ericsson.irp.AlarmIRPConstDefs.AlarmInformationIdSeqHelper.read(_input);
      java.lang.String ack_user_id;
      ack_user_id = _input.read_string();
      java.lang.String ack_system_id;
      ack_system_id = _input.read_string();
      com.ericsson.irp.AlarmIRPConstDefs.AlarmInformationIdSeqHolder bad_alarm_information_id_list = new com.ericsson.irp.AlarmIRPConstDefs.AlarmInformationIdSeqHolder();
      com.ericsson.irp.CommonIRPConstDefs.Signal _result = _self.acknowledge_alarms(alarm_information_id_list, 
                                                                                    ack_user_id, 
                                                                                    ack_system_id, 
                                                                                    bad_alarm_information_id_list);
      _output = _handler.createReply();
      com.ericsson.irp.CommonIRPConstDefs.SignalHelper.write(_output, _result);
      com.ericsson.irp.AlarmIRPConstDefs.AlarmInformationIdSeqHelper.write(_output, bad_alarm_information_id_list.value);
    }
    catch (com.ericsson.irp.AlarmIRPSystem.AcknowledgeAlarms _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.AlarmIRPSystem.AcknowledgeAlarmsHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.AlarmIRPSystem.ParameterNotSupported _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.AlarmIRPSystem.ParameterNotSupportedHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.AlarmIRPSystem.InvalidParameter _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.AlarmIRPSystem.InvalidParameterHelper.write(_output, _exception);
    }
      return _output;
    }
    case 1: {
    try {
      java.lang.String[] alarm_information_id_list;
      alarm_information_id_list = com.ericsson.irp.AlarmIRPConstDefs.AlarmInformationIdSeqHelper.read(_input);
      java.lang.String ack_user_id;
      ack_user_id = _input.read_string();
      java.lang.String ack_system_id;
      ack_system_id = _input.read_string();
      com.ericsson.irp.AlarmIRPConstDefs.AlarmInformationIdSeqHolder bad_alarm_information_id_list = new com.ericsson.irp.AlarmIRPConstDefs.AlarmInformationIdSeqHolder();
      com.ericsson.irp.CommonIRPConstDefs.Signal _result = _self.unacknowledge_alarms(alarm_information_id_list, 
                                                                                      ack_user_id, 
                                                                                      ack_system_id, 
                                                                                      bad_alarm_information_id_list);
      _output = _handler.createReply();
      com.ericsson.irp.CommonIRPConstDefs.SignalHelper.write(_output, _result);
      com.ericsson.irp.AlarmIRPConstDefs.AlarmInformationIdSeqHelper.write(_output, bad_alarm_information_id_list.value);
    }
    catch (com.ericsson.irp.AlarmIRPSystem.UnacknowledgeAlarms _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.AlarmIRPSystem.UnacknowledgeAlarmsHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.AlarmIRPSystem.OperationNotSupported _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.AlarmIRPSystem.OperationNotSupportedHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.AlarmIRPSystem.ParameterNotSupported _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.AlarmIRPSystem.ParameterNotSupportedHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.AlarmIRPSystem.InvalidParameter _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.AlarmIRPSystem.InvalidParameterHelper.write(_output, _exception);
    }
      return _output;
    }
    case 2: {
    try {
      java.lang.String filter;
      filter = _input.read_string();
      org.omg.CORBA.BooleanHolder flag = new org.omg.CORBA.BooleanHolder();
      com.ericsson.irp.AlarmIRPSystem.AlarmInformationIteratorHolder iter = new com.ericsson.irp.AlarmIRPSystem.AlarmInformationIteratorHolder();
      org.omg.CosNotification.StructuredEvent[] _result = _self.get_alarm_list(filter, 
                                                                               flag, 
                                                                               iter);
      _output = _handler.createReply();
      org.omg.CosNotification.EventBatchHelper.write(_output, _result);
      _output.write_boolean((boolean)flag.value);
      com.ericsson.irp.AlarmIRPSystem.AlarmInformationIteratorHelper.write(_output, iter.value);
    }
    catch (com.ericsson.irp.AlarmIRPSystem.GetAlarmList _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.AlarmIRPSystem.GetAlarmListHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.AlarmIRPSystem.ParameterNotSupported _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.AlarmIRPSystem.ParameterNotSupportedHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.AlarmIRPSystem.InvalidParameter _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.AlarmIRPSystem.InvalidParameterHelper.write(_output, _exception);
    }
      return _output;
    }
    case 3: {
    try {
      java.lang.String filter;
      filter = _input.read_string();
      org.omg.CORBA.IntHolder critical_count = new org.omg.CORBA.IntHolder();
      org.omg.CORBA.IntHolder major_count = new org.omg.CORBA.IntHolder();
      org.omg.CORBA.IntHolder minor_count = new org.omg.CORBA.IntHolder();
      org.omg.CORBA.IntHolder warning_count = new org.omg.CORBA.IntHolder();
      org.omg.CORBA.IntHolder indeterminate_count = new org.omg.CORBA.IntHolder();
      org.omg.CORBA.IntHolder cleared_count = new org.omg.CORBA.IntHolder();
      _self.get_alarm_count(filter, critical_count, major_count, minor_count, warning_count, 
                            indeterminate_count, cleared_count);
      _output = _handler.createReply();
      _output.write_long((int)critical_count.value);
      _output.write_long((int)major_count.value);
      _output.write_long((int)minor_count.value);
      _output.write_long((int)warning_count.value);
      _output.write_long((int)indeterminate_count.value);
      _output.write_long((int)cleared_count.value);
    }
    catch (com.ericsson.irp.AlarmIRPSystem.GetAlarmCount _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.AlarmIRPSystem.GetAlarmCountHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.AlarmIRPSystem.OperationNotSupported _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.AlarmIRPSystem.OperationNotSupportedHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.AlarmIRPSystem.ParameterNotSupported _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.AlarmIRPSystem.ParameterNotSupportedHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.AlarmIRPSystem.InvalidParameter _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.AlarmIRPSystem.InvalidParameterHelper.write(_output, _exception);
    }
      return _output;
    }
    case 4: {
    try {
      java.lang.String[] _result = _self.get_alarm_IRP_version();
      _output = _handler.createReply();
      com.ericsson.irp.CommonIRPConstDefs.VersionNumberSetHelper.write(_output, _result);
    }
    catch (com.ericsson.irp.AlarmIRPSystem.GetAlarmIRPVersion _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.AlarmIRPSystem.GetAlarmIRPVersionHelper.write(_output, _exception);
    }
      return _output;
    }
    }
    throw new org.omg.CORBA.BAD_OPERATION(2, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
  }
  }
}
