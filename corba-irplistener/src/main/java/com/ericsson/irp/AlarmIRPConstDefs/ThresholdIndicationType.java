package com.ericsson.irp.AlarmIRPConstDefs;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPConstDefs.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPConstDefs::ThresholdIndicationType
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPConstDefs/ThresholdIndicationType:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * enum ThresholdIndicationType {Up, Down};
 * </pre>
 */
public final class ThresholdIndicationType implements org.omg.CORBA.portable.IDLEntity {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1377672066391415835L;


private /* [JDK 1.1.x BUG] final */ int __value;

  
  public static final int _Up = 0;
  
  public static final int _Down = 1;

  
  public static final ThresholdIndicationType Up = new ThresholdIndicationType(_Up);
  
  public static final ThresholdIndicationType Down = new ThresholdIndicationType(_Down);

  protected ThresholdIndicationType (final int _vis_value) {
    this.__value = _vis_value;
  }

  public int value () {
    return __value;
  }

  public static ThresholdIndicationType from_int (final int _vis_value) {
    switch (_vis_value) {
      case 0: return Up;
      case 1: return Down;
      default: throw new org.omg.CORBA.BAD_PARAM();
    }
  }

}
