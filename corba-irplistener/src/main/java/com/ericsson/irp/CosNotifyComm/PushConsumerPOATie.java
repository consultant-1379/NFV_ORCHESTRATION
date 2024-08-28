
package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::PushConsumer
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/PushConsumer:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface PushConsumer : com.ericsson.irp.CosNotifyComm.NotifyPublish,
                         com.ericsson.irp.CosEventComm.PushConsumer {
  ...
};
 * </pre>
 */
public class PushConsumerPOATie extends PushConsumerPOA {
  private com.ericsson.irp.CosNotifyComm.PushConsumerOperations _delegate;
  private org.omg.PortableServer.POA _poa;

  public PushConsumerPOATie (final com.ericsson.irp.CosNotifyComm.PushConsumerOperations _delegate) {
    this._delegate = _delegate;
  }

  public PushConsumerPOATie (final com.ericsson.irp.CosNotifyComm.PushConsumerOperations _delegate, 
                              final org.omg.PortableServer.POA _poa) {
    this._delegate = _delegate;
    this._poa = _poa;
  }

  public com.ericsson.irp.CosNotifyComm.PushConsumerOperations _delegate () {
    return this._delegate;
  }

  public void _delegate (final com.ericsson.irp.CosNotifyComm.PushConsumerOperations the_delegate) {
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
   *   void disconnect_push_consumer ();
   * </pre>
   */
  public void disconnect_push_consumer () {
    this._delegate.disconnect_push_consumer();
  }

  /**
   * <pre>
   *   void push (in any data)
    raises (com.ericsson.irp.CosEventComm.Disconnected);
   * </pre>
   */
  public void push (org.omg.CORBA.Any data) throws  com.ericsson.irp.CosEventComm.Disconnected {
    this._delegate.push(data);
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
