
package com.ericsson.irp.CosEventComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventComm.idl"
 * <li> <b>IDL Name</b>      ::CosEventComm::PushSupplier
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventComm/PushSupplier:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface PushSupplier {
  ...
};
 * </pre>
 */
public class PushSupplierPOATie extends PushSupplierPOA {
  private com.ericsson.irp.CosEventComm.PushSupplierOperations _delegate;
  private org.omg.PortableServer.POA _poa;

  public PushSupplierPOATie (final com.ericsson.irp.CosEventComm.PushSupplierOperations _delegate) {
    this._delegate = _delegate;
  }

  public PushSupplierPOATie (final com.ericsson.irp.CosEventComm.PushSupplierOperations _delegate, 
                              final org.omg.PortableServer.POA _poa) {
    this._delegate = _delegate;
    this._poa = _poa;
  }

  public com.ericsson.irp.CosEventComm.PushSupplierOperations _delegate () {
    return this._delegate;
  }

  public void _delegate (final com.ericsson.irp.CosEventComm.PushSupplierOperations the_delegate) {
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
   *   void disconnect_push_supplier ();
   * </pre>
   */
  public void disconnect_push_supplier () {
    this._delegate.disconnect_push_supplier();
  }

}
