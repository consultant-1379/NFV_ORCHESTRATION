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
public interface MappingFilterOperations {
  /**
   * <pre>
   *   boolean match_typed (in org.omg.CosNotification.PropertySeq filterable_data,
                       out any result_to_set)
    raises (com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData);
   * </pre>
   */
  public boolean match_typed (org.omg.CosNotification.Property[] filterable_data, 
                              org.omg.CORBA.AnyHolder result_to_set) throws com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData;

  /**
   * <pre>
   *   boolean match_structured (in org.omg.CosNotification.StructuredEvent filterable_data,
                            out any result_to_set)
    raises (com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData);
   * </pre>
   */
  public boolean match_structured (org.omg.CosNotification.StructuredEvent filterable_data, 
                                   org.omg.CORBA.AnyHolder result_to_set) throws com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData;

  /**
   * <pre>
   *   boolean match (in any filterable_data, out any result_to_set)
    raises (com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData);
   * </pre>
   */
  public boolean match (org.omg.CORBA.Any filterable_data, 
                        org.omg.CORBA.AnyHolder result_to_set) throws com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData;

  /**
   * <pre>
   *   void destroy ();
   * </pre>
   */
  public void destroy ();

  /**
   * <pre>
   *   void remove_all_mapping_constraints ();
   * </pre>
   */
  public void remove_all_mapping_constraints ();

  /**
   * <pre>
   *   com.ericsson.irp.CosNotifyFilter.MappingConstraintInfoSeq get_all_mapping_constraints ();
   * </pre>
   */
  public com.ericsson.irp.CosNotifyFilter.MappingConstraintInfo[] get_all_mapping_constraints ();

  /**
   * <pre>
   *   com.ericsson.irp.CosNotifyFilter.MappingConstraintInfoSeq get_mapping_constraints (in com.ericsson.irp.CosNotifyFilter.ConstraintIDSeq id_list)
    raises (com.ericsson.irp.CosNotifyFilter.ConstraintNotFound);
   * </pre>
   */
  public com.ericsson.irp.CosNotifyFilter.MappingConstraintInfo[] get_mapping_constraints (int[] id_list) throws com.ericsson.irp.CosNotifyFilter.ConstraintNotFound;

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
                                          com.ericsson.irp.CosNotifyFilter.MappingConstraintInfo[] modify_list) throws com.ericsson.irp.CosNotifyFilter.InvalidConstraint, 
                                                                                                                       com.ericsson.irp.CosNotifyFilter.InvalidValue, 
                                                                                                                       com.ericsson.irp.CosNotifyFilter.ConstraintNotFound;

  /**
   * <pre>
   *   com.ericsson.irp.CosNotifyFilter.MappingConstraintInfoSeq add_mapping_constraints (in com.ericsson.irp.CosNotifyFilter.MappingConstraintPairSeq pair_list)
    raises (com.ericsson.irp.CosNotifyFilter.InvalidConstraint,
            com.ericsson.irp.CosNotifyFilter.InvalidValue);
   * </pre>
   */
  public com.ericsson.irp.CosNotifyFilter.MappingConstraintInfo[] add_mapping_constraints (com.ericsson.irp.CosNotifyFilter.MappingConstraintPair[] pair_list) throws com.ericsson.irp.CosNotifyFilter.InvalidConstraint, 
                                                                                                                                                                      com.ericsson.irp.CosNotifyFilter.InvalidValue;

  /**
   * <pre>
   *   readonly attribute any default_value;
   * </pre>
   */
  public org.omg.CORBA.Any default_value ();

  /**
   * <pre>
   *   readonly attribute ::CORBA::TypeCode value_type;
   * </pre>
   */
  public org.omg.CORBA.TypeCode value_type ();

  /**
   * <pre>
   *   readonly attribute string constraint_grammar;
   * </pre>
   */
  public java.lang.String constraint_grammar ();

}
