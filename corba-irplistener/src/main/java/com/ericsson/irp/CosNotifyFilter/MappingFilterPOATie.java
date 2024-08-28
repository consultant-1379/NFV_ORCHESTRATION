
package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::MappingFilter
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/MappingFilter:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface MappingFilter {
  ...
};
 * </pre>
 */
public class MappingFilterPOATie extends MappingFilterPOA {
  private com.ericsson.irp.CosNotifyFilter.MappingFilterOperations _delegate;
  private org.omg.PortableServer.POA _poa;

  public MappingFilterPOATie (final com.ericsson.irp.CosNotifyFilter.MappingFilterOperations _delegate) {
    this._delegate = _delegate;
  }

  public MappingFilterPOATie (final com.ericsson.irp.CosNotifyFilter.MappingFilterOperations _delegate, 
                              final org.omg.PortableServer.POA _poa) {
    this._delegate = _delegate;
    this._poa = _poa;
  }

  public com.ericsson.irp.CosNotifyFilter.MappingFilterOperations _delegate () {
    return this._delegate;
  }

  public void _delegate (final com.ericsson.irp.CosNotifyFilter.MappingFilterOperations the_delegate) {
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
   *   boolean match_typed (in org.omg.CosNotification.PropertySeq filterable_data,
                       out any result_to_set)
    raises (com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData);
   * </pre>
   */
  public boolean match_typed (org.omg.CosNotification.Property[] filterable_data, 
                              org.omg.CORBA.AnyHolder result_to_set) throws  com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData {
    return this._delegate.match_typed(filterable_data, result_to_set);
  }

  /**
   * <pre>
   *   boolean match_structured (in org.omg.CosNotification.StructuredEvent filterable_data,
                            out any result_to_set)
    raises (com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData);
   * </pre>
   */
  public boolean match_structured (org.omg.CosNotification.StructuredEvent filterable_data, 
                                   org.omg.CORBA.AnyHolder result_to_set) throws  com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData {
    return this._delegate.match_structured(filterable_data, result_to_set);
  }

  /**
   * <pre>
   *   boolean match (in any filterable_data, out any result_to_set)
    raises (com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData);
   * </pre>
   */
  public boolean match (org.omg.CORBA.Any filterable_data, 
                        org.omg.CORBA.AnyHolder result_to_set) throws  com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData {
    return this._delegate.match(filterable_data, result_to_set);
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
   *   void remove_all_mapping_constraints ();
   * </pre>
   */
  public void remove_all_mapping_constraints () {
    this._delegate.remove_all_mapping_constraints();
  }

  /**
   * <pre>
   *   com.ericsson.irp.CosNotifyFilter.MappingConstraintInfoSeq get_all_mapping_constraints ();
   * </pre>
   */
  public com.ericsson.irp.CosNotifyFilter.MappingConstraintInfo[] get_all_mapping_constraints () {
    return this._delegate.get_all_mapping_constraints();
  }

  /**
   * <pre>
   *   com.ericsson.irp.CosNotifyFilter.MappingConstraintInfoSeq get_mapping_constraints (in com.ericsson.irp.CosNotifyFilter.ConstraintIDSeq id_list)
    raises (com.ericsson.irp.CosNotifyFilter.ConstraintNotFound);
   * </pre>
   */
  public com.ericsson.irp.CosNotifyFilter.MappingConstraintInfo[] get_mapping_constraints (int[] id_list) throws  com.ericsson.irp.CosNotifyFilter.ConstraintNotFound {
    return this._delegate.get_mapping_constraints(id_list);
  }

  /**
   * <pre>
   *   void modify_mapping_constraints (in com.ericsson.irp.CosNotifyFilter.ConstraintIDSeq del_list,
                                   in com.ericsson.irp.CosNotifyFilter.MappingConstraintInfoSeq modify_list)
    raises (com.ericsson.irp.CosNotifyFilter.InvalidConstraint,
            com.ericsson.irp.CosNotifyFilter.InvalidValue,
            com.ericsson.irp.CosNotifyFilter.ConstraintNotFound);
   * </pre>
   */
  public void modify_mapping_constraints (int[] del_list, 
                                          com.ericsson.irp.CosNotifyFilter.MappingConstraintInfo[] modify_list) throws  com.ericsson.irp.CosNotifyFilter.InvalidConstraint, 
                                                                                                                        com.ericsson.irp.CosNotifyFilter.InvalidValue, 
                                                                                                                        com.ericsson.irp.CosNotifyFilter.ConstraintNotFound {
    this._delegate.modify_mapping_constraints(del_list, modify_list);
  }

  /**
   * <pre>
   *   com.ericsson.irp.CosNotifyFilter.MappingConstraintInfoSeq add_mapping_constraints (in com.ericsson.irp.CosNotifyFilter.MappingConstraintPairSeq pair_list)
    raises (com.ericsson.irp.CosNotifyFilter.InvalidConstraint,
            com.ericsson.irp.CosNotifyFilter.InvalidValue);
   * </pre>
   */
  public com.ericsson.irp.CosNotifyFilter.MappingConstraintInfo[] add_mapping_constraints (com.ericsson.irp.CosNotifyFilter.MappingConstraintPair[] pair_list) throws  com.ericsson.irp.CosNotifyFilter.InvalidConstraint, 
                                                                                                                                                                       com.ericsson.irp.CosNotifyFilter.InvalidValue {
    return this._delegate.add_mapping_constraints(pair_list);
  }

  /**
   * <pre>
   *   readonly attribute any default_value;
   * </pre>
   */
  public org.omg.CORBA.Any default_value () {
    return this._delegate.default_value();
  }

  /**
   * <pre>
   *   readonly attribute ::CORBA::TypeCode value_type;
   * </pre>
   */
  public org.omg.CORBA.TypeCode value_type () {
    return this._delegate.value_type();
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
