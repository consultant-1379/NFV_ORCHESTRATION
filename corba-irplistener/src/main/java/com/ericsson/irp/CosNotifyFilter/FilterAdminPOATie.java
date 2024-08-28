
package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::FilterAdmin
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/FilterAdmin:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface FilterAdmin {
  ...
};
 * </pre>
 */
public class FilterAdminPOATie extends FilterAdminPOA {
  private com.ericsson.irp.CosNotifyFilter.FilterAdminOperations _delegate;
  private org.omg.PortableServer.POA _poa;

  public FilterAdminPOATie (final com.ericsson.irp.CosNotifyFilter.FilterAdminOperations _delegate) {
    this._delegate = _delegate;
  }

  public FilterAdminPOATie (final com.ericsson.irp.CosNotifyFilter.FilterAdminOperations _delegate, 
                              final org.omg.PortableServer.POA _poa) {
    this._delegate = _delegate;
    this._poa = _poa;
  }

  public com.ericsson.irp.CosNotifyFilter.FilterAdminOperations _delegate () {
    return this._delegate;
  }

  public void _delegate (final com.ericsson.irp.CosNotifyFilter.FilterAdminOperations the_delegate) {
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
   *   void remove_all_filters ();
   * </pre>
   */
  public void remove_all_filters () {
    this._delegate.remove_all_filters();
  }

  /**
   * <pre>
   *   com.ericsson.irp.CosNotifyFilter.FilterIDSeq get_all_filters ();
   * </pre>
   */
  public int[] get_all_filters () {
    return this._delegate.get_all_filters();
  }

  /**
   * <pre>
   *   com.ericsson.irp.CosNotifyFilter.Filter get_filter (in com.ericsson.irp.CosNotifyFilter.FilterID filter)
    raises (com.ericsson.irp.CosNotifyFilter.FilterNotFound);
   * </pre>
   */
  public com.ericsson.irp.CosNotifyFilter.Filter get_filter (int filter) throws  com.ericsson.irp.CosNotifyFilter.FilterNotFound {
    return this._delegate.get_filter(filter);
  }

  /**
   * <pre>
   *   void remove_filter (in com.ericsson.irp.CosNotifyFilter.FilterID filter)
    raises (com.ericsson.irp.CosNotifyFilter.FilterNotFound);
   * </pre>
   */
  public void remove_filter (int filter) throws  com.ericsson.irp.CosNotifyFilter.FilterNotFound {
    this._delegate.remove_filter(filter);
  }

  /**
   * <pre>
   *   com.ericsson.irp.CosNotifyFilter.FilterID add_filter (in com.ericsson.irp.CosNotifyFilter.Filter new_filter);
   * </pre>
   */
  public int add_filter (com.ericsson.irp.CosNotifyFilter.Filter new_filter) {
    return this._delegate.add_filter(new_filter);
  }

}
