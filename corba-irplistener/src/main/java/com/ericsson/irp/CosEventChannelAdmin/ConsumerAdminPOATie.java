
package com.ericsson.irp.CosEventChannelAdmin;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventChannelAdmin.idl"
 * <li> <b>IDL Name</b>      ::CosEventChannelAdmin::ConsumerAdmin
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventChannelAdmin/ConsumerAdmin:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface ConsumerAdmin {
  ...
};
 * </pre>
 */
public class ConsumerAdminPOATie extends ConsumerAdminPOA {
  private com.ericsson.irp.CosEventChannelAdmin.ConsumerAdminOperations _delegate;
  private org.omg.PortableServer.POA _poa;

  public ConsumerAdminPOATie (final com.ericsson.irp.CosEventChannelAdmin.ConsumerAdminOperations _delegate) {
    this._delegate = _delegate;
  }

  public ConsumerAdminPOATie (final com.ericsson.irp.CosEventChannelAdmin.ConsumerAdminOperations _delegate, 
                              final org.omg.PortableServer.POA _poa) {
    this._delegate = _delegate;
    this._poa = _poa;
  }

  public com.ericsson.irp.CosEventChannelAdmin.ConsumerAdminOperations _delegate () {
    return this._delegate;
  }

  public void _delegate (final com.ericsson.irp.CosEventChannelAdmin.ConsumerAdminOperations the_delegate) {
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
   *   com.ericsson.irp.CosEventChannelAdmin.ProxyPullSupplier obtain_pull_supplier ();
   * </pre>
   */
  public com.ericsson.irp.CosEventChannelAdmin.ProxyPullSupplier obtain_pull_supplier () {
    return this._delegate.obtain_pull_supplier();
  }

  /**
   * <pre>
   *   com.ericsson.irp.CosEventChannelAdmin.ProxyPushSupplier obtain_push_supplier ();
   * </pre>
   */
  public com.ericsson.irp.CosEventChannelAdmin.ProxyPushSupplier obtain_push_supplier () {
    return this._delegate.obtain_push_supplier();
  }

}
