package com.ericsson.irp.CosEventChannelAdmin;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventChannelAdmin.idl"
 * <li> <b>IDL Name</b>      ::CosEventChannelAdmin::TypeError
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventChannelAdmin/TypeError:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception TypeError {
  ...
};
 * </pre>
 */
public final class TypeError extends org.omg.CORBA.UserException {

  /**
	 * 
	 */
	private static final long serialVersionUID = -6984613516276919815L;

public TypeError () {
    super(com.ericsson.irp.CosEventChannelAdmin.TypeErrorHelper.id());
  }

  public TypeError (java.lang.String _reason) {
    super(com.ericsson.irp.CosEventChannelAdmin.TypeErrorHelper.id() + ' ' + _reason);
    
  }

}
