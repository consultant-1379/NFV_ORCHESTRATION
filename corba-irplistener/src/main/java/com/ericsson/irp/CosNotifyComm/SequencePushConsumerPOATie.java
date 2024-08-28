
package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::SequencePushConsumer
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/SequencePushConsumer:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface SequencePushConsumer : com.ericsson.irp.CosNotifyComm.NotifyPublish {
  ...
};
 * </pre>
 */
public class SequencePushConsumerPOATie extends SequencePushConsumerPOA {
  private com.ericsson.irp.CosNotifyComm.SequencePushConsumerOperations _delegate;
  private org.omg.PortableServer.POA _poa;

  public SequencePushConsumerPOATie (final com.ericsson.irp.CosNotifyComm.SequencePushConsumerOperations _delegate) {
    this._delegate = _delegate;
  }

  public SequencePushConsumerPOATie (final com.ericsson.irp.CosNotifyComm.SequencePushConsumerOperations _delegate, 
                              final org.omg.PortableServer.POA _poa) {
    this._delegate = _delegate;
    this._poa = _poa;
  }

  public com.ericsson.irp.CosNotifyComm.SequencePushConsumerOperations _delegate () {
    return this._delegate;
  }

  public void _delegate (final com.ericsson.irp.CosNotifyComm.SequencePushConsumerOperations the_delegate) {
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
   *   void disconnect_sequence_push_consumer ();
   * </pre>
   */
  public void disconnect_sequence_push_consumer () {
    this._delegate.disconnect_sequence_push_consumer();
  }

  /**
   * <pre>
   *   void push_structured_events (in org.omg.CosNotification.EventBatch notifications)
    raises (com.ericsson.irp.CosEventComm.Disconnected);
   * </pre>
   */
  public void push_structured_events (org.omg.CosNotification.StructuredEvent[] notifications) throws  com.ericsson.irp.CosEventComm.Disconnected {
    this._delegate.push_structured_events(notifications);
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
