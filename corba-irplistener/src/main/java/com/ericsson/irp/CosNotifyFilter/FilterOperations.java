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
public interface FilterOperations {
  /**
   * <pre>
   *   com.ericsson.irp.CosNotifyFilter.CallbackIDSeq get_callbacks ();
   * </pre>
   */
  public int[] get_callbacks ();

  /**
   * <pre>
   *   void detach_callback (in com.ericsson.irp.CosNotifyFilter.CallbackID callback)
    raises (com.ericsson.irp.CosNotifyFilter.CallbackNotFound);
   * </pre>
   */
  public void detach_callback (int callback) throws com.ericsson.irp.CosNotifyFilter.CallbackNotFound;

  /**
   * <pre>
   *   com.ericsson.irp.CosNotifyFilter.CallbackID attach_callback (in com.ericsson.irp.CosNotifyComm.NotifySubscribe callback);
   * </pre>
   */
  public int attach_callback (com.ericsson.irp.CosNotifyComm.NotifySubscribe callback);

  /**
   * <pre>
   *   boolean match_typed (in org.omg.CosNotification.PropertySeq filterable_data)
    raises (com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData);
   * </pre>
   */
  public boolean match_typed (org.omg.CosNotification.Property[] filterable_data) throws com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData;

  /**
   * <pre>
   *   boolean match_structured (in org.omg.CosNotification.StructuredEvent filterable_data)
    raises (com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData);
   * </pre>
   */
  public boolean match_structured (org.omg.CosNotification.StructuredEvent filterable_data) throws com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData;

  /**
   * <pre>
   *   boolean match (in any filterable_data)
    raises (com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData);
   * </pre>
   */
  public boolean match (org.omg.CORBA.Any filterable_data) throws com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData;

  /**
   * <pre>
   *   void destroy ();
   * </pre>
   */
  public void destroy ();

  /**
   * <pre>
   *   void remove_all_constraints ();
   * </pre>
   */
  public void remove_all_constraints ();

  /**
   * <pre>
   *   com.ericsson.irp.CosNotifyFilter.ConstraintInfoSeq get_all_constraints ();
   * </pre>
   */
  public com.ericsson.irp.CosNotifyFilter.ConstraintInfo[] get_all_constraints ();

  /**
   * <pre>
   *   com.ericsson.irp.CosNotifyFilter.ConstraintInfoSeq get_constraints (in com.ericsson.irp.CosNotifyFilter.ConstraintIDSeq id_list)
    raises (com.ericsson.irp.CosNotifyFilter.ConstraintNotFound);
   * </pre>
   */
  public com.ericsson.irp.CosNotifyFilter.ConstraintInfo[] get_constraints (int[] id_list) throws com.ericsson.irp.CosNotifyFilter.ConstraintNotFound;

  /**
   * <pre>
   *   void modify_constraints (in com.ericsson.irp.CosNotifyFilter.ConstraintIDSeq del_list,
                           in com.ericsson.irp.CosNotifyFilter.ConstraintInfoSeq modify_list)
    raises (com.ericsson.irp.CosNotifyFilter.InvalidConstraint,
            com.ericsson.irp.CosNotifyFilter.ConstraintNotFound);
   * </pre>
   */
  public void modify_constraints (int[] del_list, 
                                  com.ericsson.irp.CosNotifyFilter.ConstraintInfo[] modify_list) throws com.ericsson.irp.CosNotifyFilter.InvalidConstraint, 
                                                                                                        com.ericsson.irp.CosNotifyFilter.ConstraintNotFound;

  /**
   * <pre>
   *   com.ericsson.irp.CosNotifyFilter.ConstraintInfoSeq add_constraints (in com.ericsson.irp.CosNotifyFilter.ConstraintExpSeq constraint_list)
    raises (com.ericsson.irp.CosNotifyFilter.InvalidConstraint);
   * </pre>
   */
  public com.ericsson.irp.CosNotifyFilter.ConstraintInfo[] add_constraints (com.ericsson.irp.CosNotifyFilter.ConstraintExp[] constraint_list) throws com.ericsson.irp.CosNotifyFilter.InvalidConstraint;

  /**
   * <pre>
   *   readonly attribute string constraint_grammar;
   * </pre>
   */
  public java.lang.String constraint_grammar ();

}
