
package com.ericsson.irp.CosEventChannelAdmin;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventChannelAdmin.idl"
 * <li> <b>IDL Name</b>      ::CosEventChannelAdmin::EventChannel
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventChannelAdmin/EventChannel:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface EventChannel {
  ...
};
 * </pre>
 */
public class EventChannelPOATie extends EventChannelPOA {
  private com.ericsson.irp.CosEventChannelAdmin.EventChannelOperations _delegate;
  private org.omg.PortableServer.POA _poa;

  public EventChannelPOATie (final com.ericsson.irp.CosEventChannelAdmin.EventChannelOperations _delegate) {
    this._delegate = _delegate;
  }

  public EventChannelPOATie (final com.ericsson.irp.CosEventChannelAdmin.EventChannelOperations _delegate, 
                              final org.omg.PortableServer.POA _poa) {
    this._delegate = _delegate;
    this._poa = _poa;
  }

  public com.ericsson.irp.CosEventChannelAdmin.EventChannelOperations _delegate () {
    return this._delegate;
  }

  public void _delegate (final com.ericsson.irp.CosEventChannelAdmin.EventChannelOperations the_delegate) {
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
   *   void destroy ();
   * </pre>
   */
  public void destroy () {
    this._delegate.destroy();
  }

  /**
   * <pre>
   *   com.ericsson.irp.CosEventChannelAdmin.SupplierAdmin for_suppliers ();
   * </pre>
   */
  public com.ericsson.irp.CosEventChannelAdmin.SupplierAdmin for_suppliers () {
    return this._delegate.for_suppliers();
  }

  /**
   * <pre>
   *   com.ericsson.irp.CosEventChannelAdmin.ConsumerAdmin for_consumers ();
   * </pre>
   */
  public com.ericsson.irp.CosEventChannelAdmin.ConsumerAdmin for_consumers () {
    return this._delegate.for_consumers();
  }

}
