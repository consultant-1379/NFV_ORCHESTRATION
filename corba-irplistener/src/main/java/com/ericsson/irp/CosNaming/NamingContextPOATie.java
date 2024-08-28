
package com.ericsson.irp.CosNaming;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNaming.idl"
 * <li> <b>IDL Name</b>      ::CosNaming::NamingContext
 * <li> <b>Repository Id</b> IDL:omg.org/CosNaming/NamingContext:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface NamingContext {
  ...
};
 * </pre>
 */
public class NamingContextPOATie extends NamingContextPOA {
  private com.ericsson.irp.CosNaming.NamingContextOperations _delegate;
  private org.omg.PortableServer.POA _poa;

  public NamingContextPOATie (final com.ericsson.irp.CosNaming.NamingContextOperations _delegate) {
    this._delegate = _delegate;
  }

  public NamingContextPOATie (final com.ericsson.irp.CosNaming.NamingContextOperations _delegate, 
                              final org.omg.PortableServer.POA _poa) {
    this._delegate = _delegate;
    this._poa = _poa;
  }

  public com.ericsson.irp.CosNaming.NamingContextOperations _delegate () {
    return this._delegate;
  }

  public void _delegate (final com.ericsson.irp.CosNaming.NamingContextOperations the_delegate) {
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
   *   void list (in unsigned long how_many,
             out com.ericsson.irp.CosNaming.BindingList bl,
             out com.ericsson.irp.CosNaming.BindingIterator bi);
   * </pre>
   */
  public void list (int how_many, 
                    com.ericsson.irp.CosNaming.BindingListHolder bl, 
                    com.ericsson.irp.CosNaming.BindingIteratorHolder bi) {
    this._delegate.list(how_many, bl, bi);
  }

  /**
   * <pre>
   *   void destroy ()
    raises (com.ericsson.irp.CosNaming.NamingContextPackage.NotEmpty);
   * </pre>
   */
  public void destroy () throws  com.ericsson.irp.CosNaming.NamingContextPackage.NotEmpty {
    this._delegate.destroy();
  }

  /**
   * <pre>
   *   com.ericsson.irp.CosNaming.NamingContext bind_new_context (in com.ericsson.irp.CosNaming.Name n)
    raises (com.ericsson.irp.CosNaming.NamingContextPackage.NotFound,
            com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBound,
            com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed,
            com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName);
   * </pre>
   */
  public com.ericsson.irp.CosNaming.NamingContext bind_new_context (com.ericsson.irp.CosNaming.NameComponent[] n) throws  com.ericsson.irp.CosNaming.NamingContextPackage.NotFound, 
                                                                                                                          com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBound, 
                                                                                                                          com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed, 
                                                                                                                          com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName {
    return this._delegate.bind_new_context(n);
  }

  /**
   * <pre>
   *   com.ericsson.irp.CosNaming.NamingContext new_context ();
   * </pre>
   */
  public com.ericsson.irp.CosNaming.NamingContext new_context () {
    return this._delegate.new_context();
  }

  /**
   * <pre>
   *   void unbind (in com.ericsson.irp.CosNaming.Name n)
    raises (com.ericsson.irp.CosNaming.NamingContextPackage.NotFound,
            com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed,
            com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName);
   * </pre>
   */
  public void unbind (com.ericsson.irp.CosNaming.NameComponent[] n) throws  com.ericsson.irp.CosNaming.NamingContextPackage.NotFound, 
                                                                            com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed, 
                                                                            com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName {
    this._delegate.unbind(n);
  }

  /**
   * <pre>
   *   Object resolve (in com.ericsson.irp.CosNaming.Name n)
    raises (com.ericsson.irp.CosNaming.NamingContextPackage.NotFound,
            com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed,
            com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName);
   * </pre>
   */
  public org.omg.CORBA.Object resolve (com.ericsson.irp.CosNaming.NameComponent[] n) throws  com.ericsson.irp.CosNaming.NamingContextPackage.NotFound, 
                                                                                             com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed, 
                                                                                             com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName {
    return this._delegate.resolve(n);
  }

  /**
   * <pre>
   *   void rebind_context (in com.ericsson.irp.CosNaming.Name n,
                       in com.ericsson.irp.CosNaming.NamingContext nc)
    raises (com.ericsson.irp.CosNaming.NamingContextPackage.NotFound,
            com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed,
            com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName);
   * </pre>
   */
  public void rebind_context (com.ericsson.irp.CosNaming.NameComponent[] n, 
                              com.ericsson.irp.CosNaming.NamingContext nc) throws  com.ericsson.irp.CosNaming.NamingContextPackage.NotFound, 
                                                                                   com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed, 
                                                                                   com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName {
    this._delegate.rebind_context(n, nc);
  }

  /**
   * <pre>
   *   void bind_context (in com.ericsson.irp.CosNaming.Name n,
                     in com.ericsson.irp.CosNaming.NamingContext nc)
    raises (com.ericsson.irp.CosNaming.NamingContextPackage.NotFound,
            com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed,
            com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName,
            com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBound);
   * </pre>
   */
  public void bind_context (com.ericsson.irp.CosNaming.NameComponent[] n, 
                            com.ericsson.irp.CosNaming.NamingContext nc) throws  com.ericsson.irp.CosNaming.NamingContextPackage.NotFound, 
                                                                                 com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed, 
                                                                                 com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName, 
                                                                                 com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBound {
    this._delegate.bind_context(n, nc);
  }

  /**
   * <pre>
   *   void rebind (in com.ericsson.irp.CosNaming.Name n, in Object obj)
    raises (com.ericsson.irp.CosNaming.NamingContextPackage.NotFound,
            com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed,
            com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName);
   * </pre>
   */
  public void rebind (com.ericsson.irp.CosNaming.NameComponent[] n, 
                      org.omg.CORBA.Object obj) throws  com.ericsson.irp.CosNaming.NamingContextPackage.NotFound, 
                                                        com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed, 
                                                        com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName {
    this._delegate.rebind(n, obj);
  }

  /**
   * <pre>
   *   void bind (in com.ericsson.irp.CosNaming.Name n, in Object obj)
    raises (com.ericsson.irp.CosNaming.NamingContextPackage.NotFound,
            com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed,
            com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName,
            com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBound);
   * </pre>
   */
  public void bind (com.ericsson.irp.CosNaming.NameComponent[] n, 
                    org.omg.CORBA.Object obj) throws  com.ericsson.irp.CosNaming.NamingContextPackage.NotFound, 
                                                      com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed, 
                                                      com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName, 
                                                      com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBound {
    this._delegate.bind(n, obj);
  }

}
