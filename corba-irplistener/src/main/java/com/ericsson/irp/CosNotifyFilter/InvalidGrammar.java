package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::InvalidGrammar
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/InvalidGrammar:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception InvalidGrammar {
  ...
};
 * </pre>
 */
public final class InvalidGrammar extends org.omg.CORBA.UserException {

  /**
	 * 
	 */
	private static final long serialVersionUID = -8061347926132550425L;

public InvalidGrammar () {
    super(com.ericsson.irp.CosNotifyFilter.InvalidGrammarHelper.id());
  }

  public InvalidGrammar (java.lang.String _reason) {
    super(com.ericsson.irp.CosNotifyFilter.InvalidGrammarHelper.id() + ' ' + _reason);
    
  }

}
