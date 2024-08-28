
package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::StructuredPushConsumer
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/StructuredPushConsumer:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface StructuredPushConsumer : com.ericsson.irp.CosNotifyComm.NotifyPublish {
  ...
};
 * </pre>
 */
public class StructuredPushConsumerPOATie extends StructuredPushConsumerPOA {
  private com.ericsson.irp.CosNotifyComm.StructuredPushConsumerOperations _delegate;
  private org.omg.PortableServer.POA _poa;

  public StructuredPushConsumerPOATie (final com.ericsson.irp.CosNotifyComm.StructuredPushConsumerOperations _delegate) {
    this._delegate = _delegate;
  }

  public StructuredPushConsumerPOATie (final com.ericsson.irp.CosNotifyComm.StructuredPushConsumerOperations _delegate, 
                              final org.omg.PortableServer.POA _poa) {
    this._delegate = _delegate;
    this._poa = _poa;
  }

  public com.ericsson.irp.CosNotifyComm.StructuredPushConsumerOperations _delegate () {
    return this._delegate;
  }

  public void _delegate (final com.ericsson.irp.CosNotifyComm.StructuredPushConsumerOperations the_delegate) {
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
   *   void disconnect_structured_push_consumer ();
   * </pre>
   */
  public void disconnect_structured_push_consumer () {
    this._delegate.disconnect_structured_push_consumer();
  }

  /**
   * <pre>
   *   void push_structured_event (in org.omg.CosNotification.StructuredEvent notification)
    raises (com.ericsson.irp.CosEventComm.Disconnected);
   * </pre>
   */
  public void push_structured_event (org.omg.CosNotification.StructuredEvent notification) throws  com.ericsson.irp.CosEventComm.Disconnected {
    this._delegate.push_structured_event(notification);
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
