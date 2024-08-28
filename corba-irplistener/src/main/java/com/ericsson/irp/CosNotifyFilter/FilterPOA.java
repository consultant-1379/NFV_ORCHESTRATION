package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::Filter
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/Filter:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface Filter {
  ...
};
 * </pre>
 */
public abstract class FilterPOA extends org.omg.PortableServer.Servant implements 
org.omg.CORBA.portable.InvokeHandler, com.ericsson.irp.CosNotifyFilter.FilterOperations {

  public com.ericsson.irp.CosNotifyFilter.Filter _this () {
   return com.ericsson.irp.CosNotifyFilter.FilterHelper.narrow(super._this_object());
  }

  public com.ericsson.irp.CosNotifyFilter.Filter _this (org.omg.CORBA.ORB orb) {
    return com.ericsson.irp.CosNotifyFilter.FilterHelper.narrow(super._this_object(orb));
  }

  public java.lang.String[] _all_interfaces (final org.omg.PortableServer.POA poa, final byte[] objectId) {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:omg.org/CosNotifyFilter/Filter:1.0"
  };

  private static java.util.Dictionary<String, int[]> _methods = new java.util.Hashtable<String, int[]>();

  static {
  _methods.put("_get_constraint_grammar", new int[] { 0, 0 });
  _methods.put("add_constraints", new int[] { 0, 1 });
  _methods.put("modify_constraints", new int[] { 0, 2 });
  _methods.put("get_constraints", new int[] { 0, 3 });
  _methods.put("get_all_constraints", new int[] { 0, 4 });
  _methods.put("remove_all_constraints", new int[] { 0, 5 });
  _methods.put("destroy", new int[] { 0, 6 });
  _methods.put("match", new int[] { 0, 7 });
  _methods.put("match_structured", new int[] { 0, 8 });
  _methods.put("match_typed", new int[] { 0, 9 });
  _methods.put("attach_callback", new int[] { 0, 10 });
  _methods.put("detach_callback", new int[] { 0, 11 });
  _methods.put("get_callbacks", new int[] { 0, 12 });
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
        return com.ericsson.irp.CosNotifyFilter.FilterPOA._invoke(this, method[1], _input, handler);
      }
    }
    throw new org.omg.CORBA.BAD_OPERATION(2, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
  }

  public static org.omg.CORBA.portable.OutputStream _invoke (com.ericsson.irp.CosNotifyFilter.FilterOperations _self,
                                                             int _method_id,
                                                             org.omg.CORBA.portable.InputStream _input,
                                                             org.omg.CORBA.portable.ResponseHandler _handler) {
  org.omg.CORBA.portable.OutputStream _output = null;
  {
    switch (_method_id) {
    case 0: {
      java.lang.String _result = _self.constraint_grammar();
      _output = _handler.createReply();
      _output.write_string((java.lang.String)_result);
      return _output;
    }
    case 1: {
    try {
      com.ericsson.irp.CosNotifyFilter.ConstraintExp[] constraint_list;
      constraint_list = com.ericsson.irp.CosNotifyFilter.ConstraintExpSeqHelper.read(_input);
      com.ericsson.irp.CosNotifyFilter.ConstraintInfo[] _result = _self.add_constraints(constraint_list);
      _output = _handler.createReply();
      com.ericsson.irp.CosNotifyFilter.ConstraintInfoSeqHelper.write(_output, _result);
    }
    catch (com.ericsson.irp.CosNotifyFilter.InvalidConstraint _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNotifyFilter.InvalidConstraintHelper.write(_output, _exception);
    }
      return _output;
    }
    case 2: {
    try {
      int[] del_list;
      del_list = com.ericsson.irp.CosNotifyFilter.ConstraintIDSeqHelper.read(_input);
      com.ericsson.irp.CosNotifyFilter.ConstraintInfo[] modify_list;
      modify_list = com.ericsson.irp.CosNotifyFilter.ConstraintInfoSeqHelper.read(_input);
      _self.modify_constraints(del_list, modify_list);
      _output = _handler.createReply();
    }
    catch (com.ericsson.irp.CosNotifyFilter.InvalidConstraint _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNotifyFilter.InvalidConstraintHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.CosNotifyFilter.ConstraintNotFound _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNotifyFilter.ConstraintNotFoundHelper.write(_output, _exception);
    }
      return _output;
    }
    case 3: {
    try {
      int[] id_list;
      id_list = com.ericsson.irp.CosNotifyFilter.ConstraintIDSeqHelper.read(_input);
      com.ericsson.irp.CosNotifyFilter.ConstraintInfo[] _result = _self.get_constraints(id_list);
      _output = _handler.createReply();
      com.ericsson.irp.CosNotifyFilter.ConstraintInfoSeqHelper.write(_output, _result);
    }
    catch (com.ericsson.irp.CosNotifyFilter.ConstraintNotFound _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNotifyFilter.ConstraintNotFoundHelper.write(_output, _exception);
    }
      return _output;
    }
    case 4: {
      com.ericsson.irp.CosNotifyFilter.ConstraintInfo[] _result = _self.get_all_constraints();
      _output = _handler.createReply();
      com.ericsson.irp.CosNotifyFilter.ConstraintInfoSeqHelper.write(_output, _result);
      return _output;
    }
    case 5: {
      _self.remove_all_constraints();
      _output = _handler.createReply();
      return _output;
    }
    case 6: {
      _self.destroy();
      _output = _handler.createReply();
      return _output;
    }
    case 7: {
    try {
      org.omg.CORBA.Any filterable_data;
      filterable_data = _input.read_any();
      boolean _result = _self.match(filterable_data);
      _output = _handler.createReply();
      _output.write_boolean((boolean)_result);
    }
    catch (com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableDataHelper.write(_output, _exception);
    }
      return _output;
    }
    case 8: {
    try {
      org.omg.CosNotification.StructuredEvent filterable_data;
      filterable_data = org.omg.CosNotification.StructuredEventHelper.read(_input);
      boolean _result = _self.match_structured(filterable_data);
      _output = _handler.createReply();
      _output.write_boolean((boolean)_result);
    }
    catch (com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableDataHelper.write(_output, _exception);
    }
      return _output;
    }
    case 9: {
    try {
      org.omg.CosNotification.Property[] filterable_data;
      filterable_data = org.omg.CosNotification.PropertySeqHelper.read(_input);
      boolean _result = _self.match_typed(filterable_data);
      _output = _handler.createReply();
      _output.write_boolean((boolean)_result);
    }
    catch (com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableDataHelper.write(_output, _exception);
    }
      return _output;
    }
    case 10: {
      com.ericsson.irp.CosNotifyComm.NotifySubscribe callback;
      callback = com.ericsson.irp.CosNotifyComm.NotifySubscribeHelper.read(_input);
      int _result = _self.attach_callback(callback);
      _output = _handler.createReply();
      _output.write_long((int)_result);
      return _output;
    }
    case 11: {
    try {
      int callback;
      callback = _input.read_long();
      _self.detach_callback(callback);
      _output = _handler.createReply();
    }
    catch (com.ericsson.irp.CosNotifyFilter.CallbackNotFound _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNotifyFilter.CallbackNotFoundHelper.write(_output, _exception);
    }
      return _output;
    }
    case 12: {
      int[] _result = _self.get_callbacks();
      _output = _handler.createReply();
      com.ericsson.irp.CosNotifyFilter.CallbackIDSeqHelper.write(_output, _result);
      return _output;
    }
    }
    throw new org.omg.CORBA.BAD_OPERATION(2, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
  }
  }
}
