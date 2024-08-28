
package com.ericsson.irp.CosEventComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventComm.idl"
 * <li> <b>IDL Name</b>      ::CosEventComm::PullConsumer
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventComm/PullConsumer:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface PullConsumer {
  ...
};
 * </pre>
 */
public class PullConsumerPOATie extends PullConsumerPOA {
  private com.ericsson.irp.CosEventComm.PullConsumerOperations _delegate;
  private org.omg.PortableServer.POA _poa;

  public PullConsumerPOATie (final com.ericsson.irp.CosEventComm.PullConsumerOperations _delegate) {
    this._delegate = _delegate;
  }

  public PullConsumerPOATie (final com.ericsson.irp.CosEventComm.PullConsumerOperations _delegate, 
                              final org.omg.PortableServer.POA _poa) {
    this._delegate = _delegate;
    this._poa = _poa;
  }

  public com.ericsson.irp.CosEventComm.PullConsumerOperations _delegate () {
    return this._delegate;
  }

  public void _delegate (final com.ericsson.irp.CosEventComm.PullConsumerOperations the_delegate) {
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
   *   void disconnect_pull_consumer ();
   * </pre>
   */
  public void disconnect_pull_consumer () {
    this._delegate.disconnect_pull_consumer();
  }

}
