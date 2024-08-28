
package com.ericsson.irp.CosNaming;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNaming.idl"
 * <li> <b>IDL Name</b>      ::CosNaming::BindingIterator
 * <li> <b>Repository Id</b> IDL:omg.org/CosNaming/BindingIterator:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface BindingIterator {
  ...
};
 * </pre>
 */
public class BindingIteratorPOATie extends BindingIteratorPOA {
  private com.ericsson.irp.CosNaming.BindingIteratorOperations _delegate;
  private org.omg.PortableServer.POA _poa;

  public BindingIteratorPOATie (final com.ericsson.irp.CosNaming.BindingIteratorOperations _delegate) {
    this._delegate = _delegate;
  }

  public BindingIteratorPOATie (final com.ericsson.irp.CosNaming.BindingIteratorOperations _delegate, 
                              final org.omg.PortableServer.POA _poa) {
    this._delegate = _delegate;
    this._poa = _poa;
  }

  public com.ericsson.irp.CosNaming.BindingIteratorOperations _delegate () {
    return this._delegate;
  }

  public void _delegate (final com.ericsson.irp.CosNaming.BindingIteratorOperations the_delegate) {
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
   *   boolean next_n (in unsigned long how_many,
                  out com.ericsson.irp.CosNaming.BindingList bl);
   * </pre>
   */
  public boolean next_n (int how_many, 
                         com.ericsson.irp.CosNaming.BindingListHolder bl) {
    return this._delegate.next_n(how_many, bl);
  }

  /**
   * <pre>
   *   boolean next_one (out com.ericsson.irp.CosNaming.Binding b);
   * </pre>
   */
  public boolean next_one (com.ericsson.irp.CosNaming.BindingHolder b) {
    return this._delegate.next_one(b);
  }

}
