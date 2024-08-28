package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::FilterFactory
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/FilterFactory:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface FilterFactory {
  ...
};
 * </pre>
 */
public interface FilterFactoryOperations {
  /**
   * <pre>
   *   com.ericsson.irp.CosNotifyFilter.MappingFilter create_mapping_filter (in string constraint_grammar,
                                                                        in any default_value)
    raises (com.ericsson.irp.CosNotifyFilter.InvalidGrammar);
   * </pre>
   */
  public com.ericsson.irp.CosNotifyFilter.MappingFilter create_mapping_filter (java.lang.String constraint_grammar, 
                                                                               org.omg.CORBA.Any default_value) throws com.ericsson.irp.CosNotifyFilter.InvalidGrammar;

  /**
   * <pre>
   *   com.ericsson.irp.CosNotifyFilter.Filter create_filter (in string constraint_grammar)
    raises (com.ericsson.irp.CosNotifyFilter.InvalidGrammar);
   * </pre>
   */
  public com.ericsson.irp.CosNotifyFilter.Filter create_filter (java.lang.String constraint_grammar) throws com.ericsson.irp.CosNotifyFilter.InvalidGrammar;

}
