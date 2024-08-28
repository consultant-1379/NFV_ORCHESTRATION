package com.ericsson.irp.CosEventComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventComm.idl"
 * <li> <b>IDL Name</b>      ::CosEventComm::PullSupplier
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventComm/PullSupplier:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface PullSupplier {
  ...
};
 * </pre>
 */
public interface PullSupplierOperations {
  /**
   * <pre>
   *   void disconnect_pull_supplier ();
   * </pre>
   */
  public void disconnect_pull_supplier ();

  /**
   * <pre>
   *   any try_pull (out boolean has_event)
    raises (com.ericsson.irp.CosEventComm.Disconnected);
   * </pre>
   */
  public org.omg.CORBA.Any try_pull (org.omg.CORBA.BooleanHolder has_event) throws com.ericsson.irp.CosEventComm.Disconnected;

  /**
   * <pre>
   *   any pull ()
    raises (com.ericsson.irp.CosEventComm.Disconnected);
   * </pre>
   */
  public org.omg.CORBA.Any pull () throws com.ericsson.irp.CosEventComm.Disconnected;

}
