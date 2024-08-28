package com.ericsson.irp.CosNaming;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNaming.idl"
 * <li> <b>IDL Name</b>      ::CosNaming::BindingIterator
 * <li> <b>Repository Id</b> IDL:omg.org/CosNaming/BindingIterator:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface BindingIterator {
  ...
};
 * </pre>
 */
public abstract class BindingIteratorPOA extends org.omg.PortableServer.Servant implements 
org.omg.CORBA.portable.InvokeHandler, com.ericsson.irp.CosNaming.BindingIteratorOperations {

  public com.ericsson.irp.CosNaming.BindingIterator _this () {
   return com.ericsson.irp.CosNaming.BindingIteratorHelper.narrow(super._this_object());
  }

  public com.ericsson.irp.CosNaming.BindingIterator _this (org.omg.CORBA.ORB orb) {
    return com.ericsson.irp.CosNaming.BindingIteratorHelper.narrow(super._this_object(orb));
  }

  public java.lang.String[] _all_interfaces (final org.omg.PortableServer.POA poa, final byte[] objectId) {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:omg.org/CosNaming/BindingIterator:1.0"
  };

  private static java.util.Dictionary<String, int[]> _methods = new java.util.Hashtable<String, int[]>();

  static {
  _methods.put("next_one", new int[] { 0, 0 });
  _methods.put("next_n", new int[] { 0, 1 });
  _methods.put("destroy", new int[] { 0, 2 });
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
        return com.ericsson.irp.CosNaming.BindingIteratorPOA._invoke(this, method[1], _input, handler);
      }
    }
    throw new org.omg.CORBA.BAD_OPERATION(2, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
  }

  public static org.omg.CORBA.portable.OutputStream _invoke (com.ericsson.irp.CosNaming.BindingIteratorOperations _self,
                                                             int _method_id,
                                                             org.omg.CORBA.portable.InputStream _input,
                                                             org.omg.CORBA.portable.ResponseHandler _handler) {
  org.omg.CORBA.portable.OutputStream _output = null;
  {
    switch (_method_id) {
    case 0: {
      com.ericsson.irp.CosNaming.BindingHolder b = new com.ericsson.irp.CosNaming.BindingHolder();
      boolean _result = _self.next_one(b);
      _output = _handler.createReply();
      _output.write_boolean((boolean)_result);
      if (b.value == null) {
        throw new org.omg.CORBA.BAD_PARAM("Invalid array length");
      }
      com.ericsson.irp.CosNaming.BindingHelper.write(_output, b.value);
      return _output;
    }
    case 1: {
      int how_many;
      how_many = _input.read_ulong();
      com.ericsson.irp.CosNaming.BindingListHolder bl = new com.ericsson.irp.CosNaming.BindingListHolder();
      boolean _result = _self.next_n(how_many, bl);
      _output = _handler.createReply();
      _output.write_boolean((boolean)_result);
      com.ericsson.irp.CosNaming.BindingListHelper.write(_output, bl.value);
      return _output;
    }
    case 2: {
      _self.destroy();
      _output = _handler.createReply();
      return _output;
    }
    }
    throw new org.omg.CORBA.BAD_OPERATION(2, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
  }
  }
}
