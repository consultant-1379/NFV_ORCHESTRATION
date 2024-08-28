package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::FilterAdmin
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/FilterAdmin:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface FilterAdmin {
  ...
};
 * </pre>
 */
public abstract class FilterAdminPOA extends org.omg.PortableServer.Servant implements 
org.omg.CORBA.portable.InvokeHandler, com.ericsson.irp.CosNotifyFilter.FilterAdminOperations {

  public com.ericsson.irp.CosNotifyFilter.FilterAdmin _this () {
   return com.ericsson.irp.CosNotifyFilter.FilterAdminHelper.narrow(super._this_object());
  }

  public com.ericsson.irp.CosNotifyFilter.FilterAdmin _this (org.omg.CORBA.ORB orb) {
    return com.ericsson.irp.CosNotifyFilter.FilterAdminHelper.narrow(super._this_object(orb));
  }

  public java.lang.String[] _all_interfaces (final org.omg.PortableServer.POA poa, final byte[] objectId) {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:omg.org/CosNotifyFilter/FilterAdmin:1.0"
  };

  private static java.util.Dictionary<String, int[]> _methods = new java.util.Hashtable<String, int[]>();

  static {
  _methods.put("add_filter", new int[] { 0, 0 });
  _methods.put("remove_filter", new int[] { 0, 1 });
  _methods.put("get_filter", new int[] { 0, 2 });
  _methods.put("get_all_filters", new int[] { 0, 3 });
  _methods.put("remove_all_filters", new int[] { 0, 4 });
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
        return com.ericsson.irp.CosNotifyFilter.FilterAdminPOA._invoke(this, method[1], _input, handler);
      }
    }
    throw new org.omg.CORBA.BAD_OPERATION(2, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
  }

  public static org.omg.CORBA.portable.OutputStream _invoke (com.ericsson.irp.CosNotifyFilter.FilterAdminOperations _self,
                                                             int _method_id,
                                                             org.omg.CORBA.portable.InputStream _input,
                                                             org.omg.CORBA.portable.ResponseHandler _handler) {
  org.omg.CORBA.portable.OutputStream _output = null;
  {
    switch (_method_id) {
    case 0: {
      com.ericsson.irp.CosNotifyFilter.Filter new_filter;
      new_filter = com.ericsson.irp.CosNotifyFilter.FilterHelper.read(_input);
      int _result = _self.add_filter(new_filter);
      _output = _handler.createReply();
      _output.write_long((int)_result);
      return _output;
    }
    case 1: {
    try {
      int filter;
      filter = _input.read_long();
      _self.remove_filter(filter);
      _output = _handler.createReply();
    }
    catch (com.ericsson.irp.CosNotifyFilter.FilterNotFound _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNotifyFilter.FilterNotFoundHelper.write(_output, _exception);
    }
      return _output;
    }
    case 2: {
    try {
      int filter;
      filter = _input.read_long();
      com.ericsson.irp.CosNotifyFilter.Filter _result = _self.get_filter(filter);
      _output = _handler.createReply();
      com.ericsson.irp.CosNotifyFilter.FilterHelper.write(_output, _result);
    }
    catch (com.ericsson.irp.CosNotifyFilter.FilterNotFound _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNotifyFilter.FilterNotFoundHelper.write(_output, _exception);
    }
      return _output;
    }
    case 3: {
      int[] _result = _self.get_all_filters();
      _output = _handler.createReply();
      com.ericsson.irp.CosNotifyFilter.FilterIDSeqHelper.write(_output, _result);
      return _output;
    }
    case 4: {
      _self.remove_all_filters();
      _output = _handler.createReply();
      return _output;
    }
    }
    throw new org.omg.CORBA.BAD_OPERATION(2, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
  }
  }
}
