
package com.ericsson.irp.CosEventChannelAdmin;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventChannelAdmin.idl"
 * <li> <b>IDL Name</b>      ::CosEventChannelAdmin::SupplierAdmin
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventChannelAdmin/SupplierAdmin:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface SupplierAdmin {
  ...
};
 * </pre>
 */
public class SupplierAdminPOATie extends SupplierAdminPOA {
  private com.ericsson.irp.CosEventChannelAdmin.SupplierAdminOperations _delegate;
  private org.omg.PortableServer.POA _poa;

  public SupplierAdminPOATie (final com.ericsson.irp.CosEventChannelAdmin.SupplierAdminOperations _delegate) {
    this._delegate = _delegate;
  }

  public SupplierAdminPOATie (final com.ericsson.irp.CosEventChannelAdmin.SupplierAdminOperations _delegate, 
                              final org.omg.PortableServer.POA _poa) {
    this._delegate = _delegate;
    this._poa = _poa;
  }

  public com.ericsson.irp.CosEventChannelAdmin.SupplierAdminOperations _delegate () {
    return this._delegate;
  }

  public void _delegate (final com.ericsson.irp.CosEventChannelAdmin.SupplierAdminOperations the_delegate) {
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
   *   com.ericsson.irp.CosEventChannelAdmin.ProxyPullConsumer obtain_pull_consumer ();
   * </pre>
   */
  public com.ericsson.irp.CosEventChannelAdmin.ProxyPullConsumer obtain_pull_consumer () {
    return this._delegate.obtain_pull_consumer();
  }

  /**
   * <pre>
   *   com.ericsson.irp.CosEventChannelAdmin.ProxyPushConsumer obtain_push_consumer ();
   * </pre>
   */
  public com.ericsson.irp.CosEventChannelAdmin.ProxyPushConsumer obtain_push_consumer () {
    return this._delegate.obtain_push_consumer();
  }

}
