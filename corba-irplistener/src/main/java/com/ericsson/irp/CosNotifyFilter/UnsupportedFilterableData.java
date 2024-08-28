package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::UnsupportedFilterableData
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/UnsupportedFilterableData:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception UnsupportedFilterableData {
  ...
};
 * </pre>
 */
public final class UnsupportedFilterableData extends org.omg.CORBA.UserException {

  /**
	 * 
	 */
	private static final long serialVersionUID = 118996023757131087L;

public UnsupportedFilterableData () {
    super(com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableDataHelper.id());
  }

  public UnsupportedFilterableData (java.lang.String _reason) {
    super(com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableDataHelper.id() + ' ' + _reason);
    
  }

}
