package com.ericsson.irp.AlarmIRPConstDefs;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPConstDefs.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPConstDefs::AttributeValueChangeType
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPConstDefs/AttributeValueChangeType:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * struct AttributeValueChangeType {
  ...
};
 * </pre>
 */
public final class AttributeValueChangeType implements org.omg.CORBA.portable.IDLEntity {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = 876908755465173173L;

public java.lang.String attributeName;
  
  public org.omg.CORBA.Any oldValue;
  
  public org.omg.CORBA.Any newValue;

  public AttributeValueChangeType () {
    attributeName = "";
  }

  public AttributeValueChangeType (final java.lang.String attributeName, 
                                   final org.omg.CORBA.Any oldValue, 
                                   final org.omg.CORBA.Any newValue) {
    this.attributeName = attributeName;
    this.oldValue = oldValue;
    this.newValue = newValue;
  }

}
