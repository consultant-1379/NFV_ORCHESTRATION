package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::NotifySubscribe
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/NotifySubscribe:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface NotifySubscribe {
  ...
};
 * </pre>
 */
public interface NotifySubscribeOperations {
  /**
   * <pre>
   *   void subscription_change (in org.omg.CosNotification.EventTypeSeq added,
                            in org.omg.CosNotification.EventTypeSeq removed)
    raises (com.ericsson.irp.CosNotifyComm.InvalidEventType);
   * </pre>
   */
  public void subscription_change (org.omg.CosNotification.EventType[] added, 
                                   org.omg.CosNotification.EventType[] removed) throws com.ericsson.irp.CosNotifyComm.InvalidEventType;

}
