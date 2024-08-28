
package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::StructuredPullConsumer
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/StructuredPullConsumer:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface StructuredPullConsumer : com.ericsson.irp.CosNotifyComm.NotifyPublish {
  ...
};
 * </pre>
 */
public class StructuredPullConsumerPOATie extends StructuredPullConsumerPOA {
  private com.ericsson.irp.CosNotifyComm.StructuredPullConsumerOperations _delegate;
  private org.omg.PortableServer.POA _poa;

  public StructuredPullConsumerPOATie (final com.ericsson.irp.CosNotifyComm.StructuredPullConsumerOperations _delegate) {
    this._delegate = _delegate;
  }

  public StructuredPullConsumerPOATie (final com.ericsson.irp.CosNotifyComm.StructuredPullConsumerOperations _delegate, 
                              final org.omg.PortableServer.POA _poa) {
    this._delegate = _delegate;
    this._poa = _poa;
  }

  public com.ericsson.irp.CosNotifyComm.StructuredPullConsumerOperations _delegate () {
    return this._delegate;
  }

  public void _delegate (final com.ericsson.irp.CosNotifyComm.StructuredPullConsumerOperations the_delegate) {
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
   *   void disconnect_structured_pull_consumer ();
   * </pre>
   */
  public void disconnect_structured_pull_consumer () {
    this._delegate.disconnect_structured_pull_consumer();
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
