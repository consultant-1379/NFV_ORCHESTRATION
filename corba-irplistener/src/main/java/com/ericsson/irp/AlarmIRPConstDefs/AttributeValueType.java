package com.ericsson.irp.AlarmIRPConstDefs;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPConstDefs.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPConstDefs::AttributeValueType
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPConstDefs/AttributeValueType:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * struct AttributeValueType {
  ...
};
 * </pre>
 */
public final class AttributeValueType implements org.omg.CORBA.portable.IDLEntity {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = -2814873419238426026L;

public java.lang.String attributeName;
  
  public org.omg.CORBA.Any value;

  public AttributeValueType () {
    attributeName = "";
  }

  public AttributeValueType (final java.lang.String attributeName, 
                             final org.omg.CORBA.Any value) {
    this.attributeName = attributeName;
    this.value = value;
  }

}
