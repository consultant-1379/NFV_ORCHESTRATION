package com.ericsson.irp.CosEventComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventComm.idl"
 * <li> <b>IDL Name</b>      ::CosEventComm::PushConsumer
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventComm/PushConsumer:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface PushConsumer {
  ...
};
 * </pre>
 */
public interface PushConsumerOperations {
  /**
   * <pre>
   *   void disconnect_push_consumer ();
   * </pre>
   */
  public void disconnect_push_consumer ();

  /**
   * <pre>
   *   void push (in any data)
    raises (com.ericsson.irp.CosEventComm.Disconnected);
   * </pre>
   */
  public void push (org.omg.CORBA.Any data) throws com.ericsson.irp.CosEventComm.Disconnected;

}
