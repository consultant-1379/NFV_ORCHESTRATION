package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::FilterFactory
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/FilterFactory:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface FilterFactory {
  ...
};
 * </pre>
 */
public abstract class FilterFactoryPOA extends org.omg.PortableServer.Servant implements 
org.omg.CORBA.portable.InvokeHandler, com.ericsson.irp.CosNotifyFilter.FilterFactoryOperations {

  public com.ericsson.irp.CosNotifyFilter.FilterFactory _this () {
   return com.ericsson.irp.CosNotifyFilter.FilterFactoryHelper.narrow(super._this_object());
  }

  public com.ericsson.irp.CosNotifyFilter.FilterFactory _this (org.omg.CORBA.ORB orb) {
    return com.ericsson.irp.CosNotifyFilter.FilterFactoryHelper.narrow(super._this_object(orb));
  }

  public java.lang.String[] _all_interfaces (final org.omg.PortableServer.POA poa, final byte[] objectId) {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:omg.org/CosNotifyFilter/FilterFactory:1.0"
  };

  private static java.util.Dictionary<String, int[]> _methods = new java.util.Hashtable<String, int[]>();

  static {
  _methods.put("create_filter", new int[] { 0, 0 });
  _methods.put("create_mapping_filter", new int[] { 0, 1 });
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
        return com.ericsson.irp.CosNotifyFilter.FilterFactoryPOA._invoke(this, method[1], _input, handler);
      }
    }
    throw new org.omg.CORBA.BAD_OPERATION(2, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
  }

  public static org.omg.CORBA.portable.OutputStream _invoke (com.ericsson.irp.CosNotifyFilter.FilterFactoryOperations _self,
                                                             int _method_id,
                                                             org.omg.CORBA.portable.InputStream _input,
                                                             org.omg.CORBA.portable.ResponseHandler _handler) {
  org.omg.CORBA.portable.OutputStream _output = null;
  {
    switch (_method_id) {
    case 0: {
    try {
      java.lang.String constraint_grammar;
      constraint_grammar = _input.read_string();
      com.ericsson.irp.CosNotifyFilter.Filter _result = _self.create_filter(constraint_grammar);
      _output = _handler.createReply();
      com.ericsson.irp.CosNotifyFilter.FilterHelper.write(_output, _result);
    }
    catch (com.ericsson.irp.CosNotifyFilter.InvalidGrammar _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNotifyFilter.InvalidGrammarHelper.write(_output, _exception);
    }
      return _output;
    }
    case 1: {
    try {
      java.lang.String constraint_grammar;
      constraint_grammar = _input.read_string();
      org.omg.CORBA.Any default_value;
      default_value = _input.read_any();
      com.ericsson.irp.CosNotifyFilter.MappingFilter _result = _self.create_mapping_filter(constraint_grammar, 
                                                                                           default_value);
      _output = _handler.createReply();
      com.ericsson.irp.CosNotifyFilter.MappingFilterHelper.write(_output, _result);
    }
    catch (com.ericsson.irp.CosNotifyFilter.InvalidGrammar _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNotifyFilter.InvalidGrammarHelper.write(_output, _exception);
    }
      return _output;
    }
    }
    throw new org.omg.CORBA.BAD_OPERATION(2, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
  }
  }
}
