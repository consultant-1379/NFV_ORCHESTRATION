package com.ericsson.irp.CosEventChannelAdmin;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventChannelAdmin.idl"
 * <li> <b>IDL Name</b>      ::CosEventChannelAdmin::AlreadyConnected
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventChannelAdmin/AlreadyConnected:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception AlreadyConnected {
  ...
};
 * </pre>
 */
public final class AlreadyConnected extends org.omg.CORBA.UserException {

  /**
	 * 
	 */
	private static final long serialVersionUID = 7597795422556484023L;

public AlreadyConnected () {
    super(com.ericsson.irp.CosEventChannelAdmin.AlreadyConnectedHelper.id());
  }

  public AlreadyConnected (java.lang.String _reason) {
    super(com.ericsson.irp.CosEventChannelAdmin.AlreadyConnectedHelper.id() + ' ' + _reason);
    
  }

}
