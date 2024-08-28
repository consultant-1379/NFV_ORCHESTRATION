
package com.ericsson.irp.CosEventComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventComm.idl"
 * <li> <b>IDL Name</b>      ::CosEventComm::PullSupplier
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventComm/PullSupplier:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface PullSupplier {
  ...
};
 * </pre>
 */
public class PullSupplierPOATie extends PullSupplierPOA {
  private com.ericsson.irp.CosEventComm.PullSupplierOperations _delegate;
  private org.omg.PortableServer.POA _poa;

  public PullSupplierPOATie (final com.ericsson.irp.CosEventComm.PullSupplierOperations _delegate) {
    this._delegate = _delegate;
  }

  public PullSupplierPOATie (final com.ericsson.irp.CosEventComm.PullSupplierOperations _delegate, 
                              final org.omg.PortableServer.POA _poa) {
    this._delegate = _delegate;
    this._poa = _poa;
  }

  public com.ericsson.irp.CosEventComm.PullSupplierOperations _delegate () {
    return this._delegate;
  }

  public void _delegate (final com.ericsson.irp.CosEventComm.PullSupplierOperations the_delegate) {
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
   *   void disconnect_pull_supplier ();
   * </pre>
   */
  public void disconnect_pull_supplier () {
    this._delegate.disconnect_pull_supplier();
  }

  /**
   * <pre>
   *   any try_pull (out boolean has_event)
    raises (com.ericsson.irp.CosEventComm.Disconnected);
   * </pre>
   */
  public org.omg.CORBA.Any try_pull (org.omg.CORBA.BooleanHolder has_event) throws  com.ericsson.irp.CosEventComm.Disconnected {
    return this._delegate.try_pull(has_event);
  }

  /**
   * <pre>
   *   any pull ()
    raises (com.ericsson.irp.CosEventComm.Disconnected);
   * </pre>
   */
  public org.omg.CORBA.Any pull () throws  com.ericsson.irp.CosEventComm.Disconnected {
    return this._delegate.pull();
  }

}
