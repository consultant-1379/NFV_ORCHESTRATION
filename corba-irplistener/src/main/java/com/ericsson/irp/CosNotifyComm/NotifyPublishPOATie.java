
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
public class NotifyPublishPOATie extends NotifyPublishPOA {
  private com.ericsson.irp.CosNotifyComm.NotifyPublishOperations _delegate;
  private org.omg.PortableServer.POA _poa;

  public NotifyPublishPOATie (final com.ericsson.irp.CosNotifyComm.NotifyPublishOperations _delegate) {
    this._delegate = _delegate;
  }

  public NotifyPublishPOATie (final com.ericsson.irp.CosNotifyComm.NotifyPublishOperations _delegate, 
                              final org.omg.PortableServer.POA _poa) {
    this._delegate = _delegate;
    this._poa = _poa;
  }

  public com.ericsson.irp.CosNotifyComm.NotifyPublishOperations _delegate () {
    return this._delegate;
  }

  public void _delegate (final com.ericsson.irp.CosNotifyComm.NotifyPublishOperations the_delegate) {
    this._delegate = the_delegate;
  }

  public org.omg.PortableServer.POA _default_POA () {
    if (_poa != null) {
      return _poa;
    } 
    else {
      return super._default_POA();
    }
  }

  /**
   * <pre>
   *   void offer_change (in org.omg.CosNotification.EventTypeSeq added,
                     in org.omg.CosNotification.EventTypeSeq removed)
    raises (com.ericsson.irp.CosNotifyComm.InvalidEventType);
   * </pre>
   */
  public void offer_change (org.omg.CosNotification.EventType[] added, 
                            org.omg.CosNotification.EventType[] removed) throws  com.ericsson.irp.CosNotifyComm.InvalidEventType {
    this._delegate.offer_change(added, removed);
  }

}
