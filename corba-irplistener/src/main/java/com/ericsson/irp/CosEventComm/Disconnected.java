package com.ericsson.irp.CosEventComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventComm.idl"
 * <li> <b>IDL Name</b>      ::CosEventComm::Disconnected
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventComm/Disconnected:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception Disconnected {
  ...
};
 * </pre>
 */
public final class Disconnected extends org.omg.CORBA.UserException {

  /**
	 * 
	 */
	private static final long serialVersionUID = 7155375288412478038L;

public Disconnected () {
    super(com.ericsson.irp.CosEventComm.DisconnectedHelper.id());
  }

  public Disconnected (java.lang.String _reason) {
    super(com.ericsson.irp.CosEventComm.DisconnectedHelper.id() + ' ' + _reason);
    
  }

}
