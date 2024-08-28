package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::StructuredPullSupplier
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/StructuredPullSupplier:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface StructuredPullSupplier : com.ericsson.irp.CosNotifyComm.NotifySubscribe {
  ...
};
 * </pre>
 */
public interface StructuredPullSupplierOperations extends com.ericsson.irp.CosNotifyComm.NotifySubscribeOperations {
  /**
   * <pre>
   *   void disconnect_structured_pull_supplier ();
   * </pre>
   */
  public void disconnect_structured_pull_supplier ();

  /**
   * <pre>
   *   org.omg.CosNotification.StructuredEvent try_pull_structured_event (out boolean has_event)
    raises (com.ericsson.irp.CosEventComm.Disconnected);
   * </pre>
   */
  public org.omg.CosNotification.StructuredEvent try_pull_structured_event (org.omg.CORBA.BooleanHolder has_event) throws com.ericsson.irp.CosEventComm.Disconnected;

  /**
   * <pre>
   *   org.omg.CosNotification.StructuredEvent pull_structured_event ()
    raises (com.ericsson.irp.CosEventComm.Disconnected);
   * </pre>
   */
  public org.omg.CosNotification.StructuredEvent pull_structured_event () throws com.ericsson.irp.CosEventComm.Disconnected;

}
