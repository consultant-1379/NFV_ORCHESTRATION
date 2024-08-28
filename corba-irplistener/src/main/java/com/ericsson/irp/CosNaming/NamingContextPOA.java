package com.ericsson.irp.CosNaming;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNaming.idl"
 * <li> <b>IDL Name</b>      ::CosNaming::NamingContext
 * <li> <b>Repository Id</b> IDL:omg.org/CosNaming/NamingContext:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface NamingContext {
  ...
};
 * </pre>
 */
public abstract class NamingContextPOA extends org.omg.PortableServer.Servant implements 
org.omg.CORBA.portable.InvokeHandler, com.ericsson.irp.CosNaming.NamingContextOperations {

  public com.ericsson.irp.CosNaming.NamingContext _this () {
   return com.ericsson.irp.CosNaming.NamingContextHelper.narrow(super._this_object());
  }

  public com.ericsson.irp.CosNaming.NamingContext _this (org.omg.CORBA.ORB orb) {
    return com.ericsson.irp.CosNaming.NamingContextHelper.narrow(super._this_object(orb));
  }

  public java.lang.String[] _all_interfaces (final org.omg.PortableServer.POA poa, final byte[] objectId) {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:omg.org/CosNaming/NamingContext:1.0"
  };

  private static java.util.Dictionary<String, int[]> _methods = new java.util.Hashtable<String, int[]>();

  static {
  _methods.put("bind", new int[] { 0, 0 });
  _methods.put("rebind", new int[] { 0, 1 });
  _methods.put("bind_context", new int[] { 0, 2 });
  _methods.put("rebind_context", new int[] { 0, 3 });
  _methods.put("resolve", new int[] { 0, 4 });
  _methods.put("unbind", new int[] { 0, 5 });
  _methods.put("new_context", new int[] { 0, 6 });
  _methods.put("bind_new_context", new int[] { 0, 7 });
  _methods.put("destroy", new int[] { 0, 8 });
  _methods.put("list", new int[] { 0, 9 });
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
        return com.ericsson.irp.CosNaming.NamingContextPOA._invoke(this, method[1], _input, handler);
      }
    }
    throw new org.omg.CORBA.BAD_OPERATION(2, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
  }

  public static org.omg.CORBA.portable.OutputStream _invoke (com.ericsson.irp.CosNaming.NamingContextOperations _self,
                                                             int _method_id,
                                                             org.omg.CORBA.portable.InputStream _input,
                                                             org.omg.CORBA.portable.ResponseHandler _handler) {
  org.omg.CORBA.portable.OutputStream _output = null;
  {
    switch (_method_id) {
    case 0: {
    try {
      com.ericsson.irp.CosNaming.NameComponent[] n;
      n = com.ericsson.irp.CosNaming.NameHelper.read(_input);
      org.omg.CORBA.Object obj;
      obj = _input.read_Object();
      _self.bind(n, obj);
      _output = _handler.createReply();
    }
    catch (com.ericsson.irp.CosNaming.NamingContextPackage.NotFound _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNaming.NamingContextPackage.NotFoundHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceedHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNaming.NamingContextPackage.InvalidNameHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBound _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBoundHelper.write(_output, _exception);
    }
      return _output;
    }
    case 1: {
    try {
      com.ericsson.irp.CosNaming.NameComponent[] n;
      n = com.ericsson.irp.CosNaming.NameHelper.read(_input);
      org.omg.CORBA.Object obj;
      obj = _input.read_Object();
      _self.rebind(n, obj);
      _output = _handler.createReply();
    }
    catch (com.ericsson.irp.CosNaming.NamingContextPackage.NotFound _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNaming.NamingContextPackage.NotFoundHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceedHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNaming.NamingContextPackage.InvalidNameHelper.write(_output, _exception);
    }
      return _output;
    }
    case 2: {
    try {
      com.ericsson.irp.CosNaming.NameComponent[] n;
      n = com.ericsson.irp.CosNaming.NameHelper.read(_input);
      com.ericsson.irp.CosNaming.NamingContext nc;
      nc = com.ericsson.irp.CosNaming.NamingContextHelper.read(_input);
      _self.bind_context(n, nc);
      _output = _handler.createReply();
    }
    catch (com.ericsson.irp.CosNaming.NamingContextPackage.NotFound _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNaming.NamingContextPackage.NotFoundHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceedHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNaming.NamingContextPackage.InvalidNameHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBound _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBoundHelper.write(_output, _exception);
    }
      return _output;
    }
    case 3: {
    try {
      com.ericsson.irp.CosNaming.NameComponent[] n;
      n = com.ericsson.irp.CosNaming.NameHelper.read(_input);
      com.ericsson.irp.CosNaming.NamingContext nc;
      nc = com.ericsson.irp.CosNaming.NamingContextHelper.read(_input);
      _self.rebind_context(n, nc);
      _output = _handler.createReply();
    }
    catch (com.ericsson.irp.CosNaming.NamingContextPackage.NotFound _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNaming.NamingContextPackage.NotFoundHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceedHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNaming.NamingContextPackage.InvalidNameHelper.write(_output, _exception);
    }
      return _output;
    }
    case 4: {
    try {
      com.ericsson.irp.CosNaming.NameComponent[] n;
      n = com.ericsson.irp.CosNaming.NameHelper.read(_input);
      org.omg.CORBA.Object _result = _self.resolve(n);
      _output = _handler.createReply();
      _output.write_Object((org.omg.CORBA.Object)_result);
    }
    catch (com.ericsson.irp.CosNaming.NamingContextPackage.NotFound _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNaming.NamingContextPackage.NotFoundHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceedHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNaming.NamingContextPackage.InvalidNameHelper.write(_output, _exception);
    }
      return _output;
    }
    case 5: {
    try {
      com.ericsson.irp.CosNaming.NameComponent[] n;
      n = com.ericsson.irp.CosNaming.NameHelper.read(_input);
      _self.unbind(n);
      _output = _handler.createReply();
    }
    catch (com.ericsson.irp.CosNaming.NamingContextPackage.NotFound _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNaming.NamingContextPackage.NotFoundHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceedHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNaming.NamingContextPackage.InvalidNameHelper.write(_output, _exception);
    }
      return _output;
    }
    case 6: {
      com.ericsson.irp.CosNaming.NamingContext _result = _self.new_context();
      _output = _handler.createReply();
      com.ericsson.irp.CosNaming.NamingContextHelper.write(_output, _result);
      return _output;
    }
    case 7: {
    try {
      com.ericsson.irp.CosNaming.NameComponent[] n;
      n = com.ericsson.irp.CosNaming.NameHelper.read(_input);
      com.ericsson.irp.CosNaming.NamingContext _result = _self.bind_new_context(n);
      _output = _handler.createReply();
      com.ericsson.irp.CosNaming.NamingContextHelper.write(_output, _result);
    }
    catch (com.ericsson.irp.CosNaming.NamingContextPackage.NotFound _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNaming.NamingContextPackage.NotFoundHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBound _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBoundHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceedHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNaming.NamingContextPackage.InvalidNameHelper.write(_output, _exception);
    }
      return _output;
    }
    case 8: {
    try {
      _self.destroy();
      _output = _handler.createReply();
    }
    catch (com.ericsson.irp.CosNaming.NamingContextPackage.NotEmpty _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.CosNaming.NamingContextPackage.NotEmptyHelper.write(_output, _exception);
    }
      return _output;
    }
    case 9: {
      int how_many;
      how_many = _input.read_ulong();
      com.ericsson.irp.CosNaming.BindingListHolder bl = new com.ericsson.irp.CosNaming.BindingListHolder();
      com.ericsson.irp.CosNaming.BindingIteratorHolder bi = new com.ericsson.irp.CosNaming.BindingIteratorHolder();
      _self.list(how_many, bl, bi);
      _output = _handler.createReply();
      com.ericsson.irp.CosNaming.BindingListHelper.write(_output, bl.value);
      com.ericsson.irp.CosNaming.BindingIteratorHelper.write(_output, bi.value);
      return _output;
    }
    }
    throw new org.omg.CORBA.BAD_OPERATION(2, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
  }
  }
}
