
package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::Filter
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/Filter:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface Filter {
  ...
};
 * </pre>
 */
public class FilterPOATie extends FilterPOA {
  private com.ericsson.irp.CosNotifyFilter.FilterOperations _delegate;
  private org.omg.PortableServer.POA _poa;

  public FilterPOATie (final com.ericsson.irp.CosNotifyFilter.FilterOperations _delegate) {
    this._delegate = _delegate;
  }

  public FilterPOATie (final com.ericsson.irp.CosNotifyFilter.FilterOperations _delegate, 
                              final org.omg.PortableServer.POA _poa) {
    this._delegate = _delegate;
    this._poa = _poa;
  }

  public com.ericsson.irp.CosNotifyFilter.FilterOperations _delegate () {
    return this._delegate;
  }

  public void _delegate (final com.ericsson.irp.CosNotifyFilter.FilterOperations the_delegate) {
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
   *   com.ericsson.irp.CosNotifyFilter.CallbackIDSeq get_callbacks ();
   * </pre>
   */
  public int[] get_callbacks () {
    return this._delegate.get_callbacks();
  }

  /**
   * <pre>
   *   void detach_callback (in com.ericsson.irp.CosNotifyFilter.CallbackID callback)
    raises (com.ericsson.irp.CosNotifyFilter.CallbackNotFound);
   * </pre>
   */
  public void detach_callback (int callback) throws  com.ericsson.irp.CosNotifyFilter.CallbackNotFound {
    this._delegate.detach_callback(callback);
  }

  /**
   * <pre>
   *   com.ericsson.irp.CosNotifyFilter.CallbackID attach_callback (in com.ericsson.irp.CosNotifyComm.NotifySubscribe callback);
   * </pre>
   */
  public int attach_callback (com.ericsson.irp.CosNotifyComm.NotifySubscribe callback) {
    return this._delegate.attach_callback(callback);
  }

  /**
   * <pre>
   *   boolean match_typed (in org.omg.CosNotification.PropertySeq filterable_data)
    raises (com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData);
   * </pre>
   */
  public boolean match_typed (org.omg.CosNotification.Property[] filterable_data) throws  com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData {
    return this._delegate.match_typed(filterable_data);
  }

  /**
   * <pre>
   *   boolean match_structured (in org.omg.CosNotification.StructuredEvent filterable_data)
    raises (com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData);
   * </pre>
   */
  public boolean match_structured (org.omg.CosNotification.StructuredEvent filterable_data) throws  com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData {
    return this._delegate.match_structured(filterable_data);
  }

  /**
   * <pre>
   *   boolean match (in any filterable_data)
    raises (com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData);
   * </pre>
   */
  public boolean match (org.omg.CORBA.Any filterable_data) throws  com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData {
    return this._delegate.match(filterable_data);
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
   *   void remove_all_constraints ();
   * </pre>
   */
  public void remove_all_constraints () {
    this._delegate.remove_all_constraints();
  }

  /**
   * <pre>
   *   com.ericsson.irp.CosNotifyFilter.ConstraintInfoSeq get_all_constraints ();
   * </pre>
   */
  public com.ericsson.irp.CosNotifyFilter.ConstraintInfo[] get_all_constraints () {
    return this._delegate.get_all_constraints();
  }

  /**
   * <pre>
   *   com.ericsson.irp.CosNotifyFilter.ConstraintInfoSeq get_constraints (in com.ericsson.irp.CosNotifyFilter.ConstraintIDSeq id_list)
    raises (com.ericsson.irp.CosNotifyFilter.ConstraintNotFound);
   * </pre>
   */
  public com.ericsson.irp.CosNotifyFilter.ConstraintInfo[] get_constraints (int[] id_list) throws  com.ericsson.irp.CosNotifyFilter.ConstraintNotFound {
    return this._delegate.get_constraints(id_list);
  }

  /**
   * <pre>
   *   void modify_constraints (in com.ericsson.irp.CosNotifyFilter.ConstraintIDSeq del_list,
                           in com.ericsson.irp.CosNotifyFilter.ConstraintInfoSeq modify_list)
    raises (com.ericsson.irp.CosNotifyFilter.InvalidConstraint,
            com.ericsson.irp.CosNotifyFilter.ConstraintNotFound);
   * </pre>
   */
  public void modify_constraints (int[] del_list, 
                                  com.ericsson.irp.CosNotifyFilter.ConstraintInfo[] modify_list) throws  com.ericsson.irp.CosNotifyFilter.InvalidConstraint, 
                                                                                                         com.ericsson.irp.CosNotifyFilter.ConstraintNotFound {
    this._delegate.modify_constraints(del_list, modify_list);
  }

  /**
   * <pre>
   *   com.ericsson.irp.CosNotifyFilter.ConstraintInfoSeq add_constraints (in com.ericsson.irp.CosNotifyFilter.ConstraintExpSeq constraint_list)
    raises (com.ericsson.irp.CosNotifyFilter.InvalidConstraint);
   * </pre>
   */
  public com.ericsson.irp.CosNotifyFilter.ConstraintInfo[] add_constraints (com.ericsson.irp.CosNotifyFilter.ConstraintExp[] constraint_list) throws  com.ericsson.irp.CosNotifyFilter.InvalidConstraint {
    return this._delegate.add_constraints(constraint_list);
  }

  /**
   * <pre>
   *   readonly attribute string constraint_grammar;
   * </pre>
   */
  public java.lang.String constraint_grammar () {
    return this._delegate.constraint_grammar();
  }

}
