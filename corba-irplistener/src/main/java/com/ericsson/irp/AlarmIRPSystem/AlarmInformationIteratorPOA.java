package com.ericsson.irp.AlarmIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPSystem::AlarmInformationIterator
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPSystem/AlarmInformationIterator:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface AlarmInformationIterator {
  ...
};
 * </pre>
 */
@SuppressWarnings("unchecked")
public abstract class AlarmInformationIteratorPOA extends org.omg.PortableServer.Servant implements 
org.omg.CORBA.portable.InvokeHandler, com.ericsson.irp.AlarmIRPSystem.AlarmInformationIteratorOperations {

  public com.ericsson.irp.AlarmIRPSystem.AlarmInformationIterator _this () {
   return com.ericsson.irp.AlarmIRPSystem.AlarmInformationIteratorHelper.narrow(super._this_object());
  }

  public com.ericsson.irp.AlarmIRPSystem.AlarmInformationIterator _this (org.omg.CORBA.ORB orb) {
    return com.ericsson.irp.AlarmIRPSystem.AlarmInformationIteratorHelper.narrow(super._this_object(orb));
  }

  public java.lang.String[] _all_interfaces (final org.omg.PortableServer.POA poa, final byte[] objectId) {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:3gppsa5.org/AlarmIRPSystem/AlarmInformationIterator:1.0"
  };

  @SuppressWarnings("rawtypes")
private static java.util.Dictionary _methods = new java.util.Hashtable();

  static {
  _methods.put("next_alarmInformations", new int[] { 0, 0 });
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
        return com.ericsson.irp.AlarmIRPSystem.AlarmInformationIteratorPOA._invoke(this, method[1], _input, handler);
      }
    }
    throw new org.omg.CORBA.BAD_OPERATION(2, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
  }

  public static org.omg.CORBA.portable.OutputStream _invoke (com.ericsson.irp.AlarmIRPSystem.AlarmInformationIteratorOperations _self,
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
      org.omg.CosNotification.EventBatchHolder alarm_informations = new org.omg.CosNotification.EventBatchHolder();
      boolean _result = _self.next_alarmInformations(how_many, alarm_informations);
      _output = _handler.createReply();
      _output.write_boolean((boolean)_result);
      org.omg.CosNotification.EventBatchHelper.write(_output, alarm_informations.value);
    }
    catch (com.ericsson.irp.AlarmIRPSystem.NextAlarmInformations _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.AlarmIRPSystem.NextAlarmInformationsHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.AlarmIRPSystem.InvalidParameter _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.AlarmIRPSystem.InvalidParameterHelper.write(_output, _exception);
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
