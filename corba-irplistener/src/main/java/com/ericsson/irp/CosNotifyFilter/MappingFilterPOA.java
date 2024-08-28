package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::MappingFilter
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/MappingFilter:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface MappingFilter {
  ...
};
 * </pre>
 */
public abstract class MappingFilterPOA extends org.omg.PortableServer.Servant implements 
org.omg.CORBA.portable.InvokeHandler, com.ericsson.irp.CosNotifyFilter.MappingFilterOperations {

  public com.ericsson.irp.CosNotifyFilter.MappingFilter _this () {
   return com.ericsson.irp.CosNotifyFilter.MappingFilterHelper.narrow(super._this_object());
  }

  public com.ericsson.irp.CosNotifyFilter.MappingFilter _this (org.omg.CORBA.ORB orb) {
    return com.ericsson.irp.CosNotifyFilter.MappingFilterHelper.narrow(super._this_object(orb));
  }

  public java.lang.String[] _all_interfaces (final org.omg.PortableServer.POA poa, final byte[] objectId) {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:omg.org/CosNotifyFilter/MappingFilter:1.0"
  };

  private static java.util.Dictionary<String, int[]> _methods = new java.util.Hashtable<String, int[]>();

  static {
  _methods.put("_get_constraint_grammar", new int[] { 0, 0 });
  _methods.put("_get_value_type", new int[] { 0, 1 });
  _methods.put("_get_default_value", new int[] { 0, 2 });
  _methods.put("add_mapping_constraints", new int[] { 0, 3 });
  _methods.put("modify_mapping_constraints", new int[] { 0, 4 });
  _methods.put("get_mapping_constraints", new int[] { 0, 5 });
  _methods.put("get_all_mapping_constraints", new int[] { 0, 6 });
  _methods.put("remove_all_mapping_constraints", new int[] { 0, 7 });
  _methods.put("destroy", new int[] { 0, 8 });
  _methods.put("match", new int[] { 0, 9 });
  _methods.put("match_structured", new int[] { 0, 10 });
  _methods.put("match_typed", new int[] { 0, 11 });
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
        return com.ericsson.irp.CosNotifyFilter.MappingFilterPOA._invoke(this, method[1], _input, handler);
      }
    }
    throw new org.omg.CORBA.BAD_OPERATION(2, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
  }

  public static org.omg.CORBA.portable.OutputStream _invoke (com.ericsson.irp.CosNotifyFilter.MappingFilterOperations _self,
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
      org.omg.CORBA.TypeCode _result = _self.value_type();
      _output = _handler.createReply();
      _output.write_TypeCode((org.omg.CORBA.TypeCode)_result);
      return _output;
    }
    case 2: {
      org.omg.CORBA.Any _result = _self.default_value();
      _output = _handler.createReply();
      _output.write_any((org.omg.CORBA.Any)_result);
      return _output;
    }
    case 3: {
    try {
      com.ericsson.irp.CosNotifyFilter.MappingConstraintPair[] pair_list;
      pair_list = com.ericsson.irp.CosNotifyFilter.MappingConstraintPairSeqHelper.read(_input);
      com.ericsson.irp.CosNotifyFilter.MappingConstraintInfo[] _result = _self.add_mapping_constraints(pair_list);
      _output = _handler.createReply();
      com.ericsson.irp.CosNotifyFilter.MappingConstraintInfoSeqHelper.write(_output, _result);
    }
    catch (com.ericsson.irp.CosNotifyFilter.InvalidConstraint _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNotifyFilter.InvalidConstraintHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.CosNotifyFilter.InvalidValue _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNotifyFilter.InvalidValueHelper.write(_output, _exception);
    }
      return _output;
    }
    case 4: {
    try {
      int[] del_list;
      del_list = com.ericsson.irp.CosNotifyFilter.ConstraintIDSeqHelper.read(_input);
      com.ericsson.irp.CosNotifyFilter.MappingConstraintInfo[] modify_list;
      modify_list = com.ericsson.irp.CosNotifyFilter.MappingConstraintInfoSeqHelper.read(_input);
      _self.modify_mapping_constraints(del_list, modify_list);
      _output = _handler.createReply();
    }
    catch (com.ericsson.irp.CosNotifyFilter.InvalidConstraint _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNotifyFilter.InvalidConstraintHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.CosNotifyFilter.InvalidValue _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNotifyFilter.InvalidValueHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.CosNotifyFilter.ConstraintNotFound _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNotifyFilter.ConstraintNotFoundHelper.write(_output, _exception);
    }
      return _output;
    }
    case 5: {
    try {
      int[] id_list;
      id_list = com.ericsson.irp.CosNotifyFilter.ConstraintIDSeqHelper.read(_input);
      com.ericsson.irp.CosNotifyFilter.MappingConstraintInfo[] _result = _self.get_mapping_constraints(id_list);
      _output = _handler.createReply();
      com.ericsson.irp.CosNotifyFilter.MappingConstraintInfoSeqHelper.write(_output, _result);
    }
    catch (com.ericsson.irp.CosNotifyFilter.ConstraintNotFound _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNotifyFilter.ConstraintNotFoundHelper.write(_output, _exception);
    }
      return _output;
    }
    case 6: {
      com.ericsson.irp.CosNotifyFilter.MappingConstraintInfo[] _result = _self.get_all_mapping_constraints();
      _output = _handler.createReply();
      com.ericsson.irp.CosNotifyFilter.MappingConstraintInfoSeqHelper.write(_output, _result);
      return _output;
    }
    case 7: {
      _self.remove_all_mapping_constraints();
      _output = _handler.createReply();
      return _output;
    }
    case 8: {
      _self.destroy();
      _output = _handler.createReply();
      return _output;
    }
    case 9: {
    try {
      org.omg.CORBA.Any filterable_data;
      filterable_data = _input.read_any();
      org.omg.CORBA.AnyHolder result_to_set = new org.omg.CORBA.AnyHolder();
      boolean _result = _self.match(filterable_data, result_to_set);
      _output = _handler.createReply();
      _output.write_boolean((boolean)_result);
      _output.write_any((org.omg.CORBA.Any)result_to_set.value);
    }
    catch (com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableDataHelper.write(_output, _exception);
    }
      return _output;
    }
    case 10: {
    try {
      org.omg.CosNotification.StructuredEvent filterable_data;
      filterable_data = org.omg.CosNotification.StructuredEventHelper.read(_input);
      org.omg.CORBA.AnyHolder result_to_set = new org.omg.CORBA.AnyHolder();
      boolean _result = _self.match_structured(filterable_data, result_to_set);
      _output = _handler.createReply();
      _output.write_boolean((boolean)_result);
      _output.write_any((org.omg.CORBA.Any)result_to_set.value);
    }
    catch (com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableDataHelper.write(_output, _exception);
    }
      return _output;
    }
    case 11: {
    try {
      org.omg.CosNotification.Property[] filterable_data;
      filterable_data = org.omg.CosNotification.PropertySeqHelper.read(_input);
      org.omg.CORBA.AnyHolder result_to_set = new org.omg.CORBA.AnyHolder();
      boolean _result = _self.match_typed(filterable_data, result_to_set);
      _output = _handler.createReply();
      _output.write_boolean((boolean)_result);
      _output.write_any((org.omg.CORBA.Any)result_to_set.value);
    }
    catch (com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableDataHelper.write(_output, _exception);
    }
      return _output;
    }
    }
    throw new org.omg.CORBA.BAD_OPERATION(2, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
  }
  }
}
