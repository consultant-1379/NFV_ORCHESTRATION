package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::NotifyPublish
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/NotifyPublish:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface NotifyPublish {
  ...
};
 * </pre>
 */
public interface NotifyPublishOperations {
  /**
   * <pre>
   *   void offer_change (in org.omg.CosNotification.EventTypeSeq added,
                     in org.omg.CosNotification.EventTypeSeq removed)
    raises (com.ericsson.irp.CosNotifyComm.InvalidEventType);
   * </pre>
   */
  public void offer_change (org.omg.CosNotification.EventType[] added, 
                            org.omg.CosNotification.EventType[] removed) throws com.ericsson.irp.CosNotifyComm.InvalidEventType;

}
