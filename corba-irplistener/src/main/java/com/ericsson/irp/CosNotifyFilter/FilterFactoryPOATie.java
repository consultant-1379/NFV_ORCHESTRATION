
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
public class FilterFactoryPOATie extends FilterFactoryPOA {
  private com.ericsson.irp.CosNotifyFilter.FilterFactoryOperations _delegate;
  private org.omg.PortableServer.POA _poa;

  public FilterFactoryPOATie (final com.ericsson.irp.CosNotifyFilter.FilterFactoryOperations _delegate) {
    this._delegate = _delegate;
  }

  public FilterFactoryPOATie (final com.ericsson.irp.CosNotifyFilter.FilterFactoryOperations _delegate, 
                              final org.omg.PortableServer.POA _poa) {
    this._delegate = _delegate;
    this._poa = _poa;
  }

  public com.ericsson.irp.CosNotifyFilter.FilterFactoryOperations _delegate () {
    return this._delegate;
  }

  public void _delegate (final com.ericsson.irp.CosNotifyFilter.FilterFactoryOperations the_delegate) {
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
   *   com.ericsson.irp.CosNotifyFilter.MappingFilter create_mapping_filter (in string constraint_grammar,
                                                                        in any default_value)
    raises (com.ericsson.irp.CosNotifyFilter.InvalidGrammar);
   * </pre>
   */
  public com.ericsson.irp.CosNotifyFilter.MappingFilter create_mapping_filter (java.lang.String constraint_grammar, 
                                                                               org.omg.CORBA.Any default_value) throws  com.ericsson.irp.CosNotifyFilter.InvalidGrammar {
    return this._delegate.create_mapping_filter(constraint_grammar, default_value);
  }

  /**
   * <pre>
   *   com.ericsson.irp.CosNotifyFilter.Filter create_filter (in string constraint_grammar)
    raises (com.ericsson.irp.CosNotifyFilter.InvalidGrammar);
   * </pre>
   */
  public com.ericsson.irp.CosNotifyFilter.Filter create_filter (java.lang.String constraint_grammar) throws  com.ericsson.irp.CosNotifyFilter.InvalidGrammar {
    return this._delegate.create_filter(constraint_grammar);
  }

}
