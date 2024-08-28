package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::FilterNotFound
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/FilterNotFound:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception FilterNotFound {
  ...
};
 * </pre>
 */
public final class FilterNotFound extends org.omg.CORBA.UserException {

  /**
	 * 
	 */
	private static final long serialVersionUID = 8258724731947107830L;

public FilterNotFound () {
    super(com.ericsson.irp.CosNotifyFilter.FilterNotFoundHelper.id());
  }

  public FilterNotFound (java.lang.String _reason) {
    super(com.ericsson.irp.CosNotifyFilter.FilterNotFoundHelper.id() + ' ' + _reason);
    
  }

}
