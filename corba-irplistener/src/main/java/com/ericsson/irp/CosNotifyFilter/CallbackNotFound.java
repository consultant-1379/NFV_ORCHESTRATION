package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::CallbackNotFound
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/CallbackNotFound:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception CallbackNotFound {
  ...
};
 * </pre>
 */
public final class CallbackNotFound extends org.omg.CORBA.UserException {

  /**
	 * 
	 */
	private static final long serialVersionUID = -8044696175806503213L;

public CallbackNotFound () {
    super(com.ericsson.irp.CosNotifyFilter.CallbackNotFoundHelper.id());
  }

  public CallbackNotFound (java.lang.String _reason) {
    super(com.ericsson.irp.CosNotifyFilter.CallbackNotFoundHelper.id() + ' ' + _reason);
    
  }

}
