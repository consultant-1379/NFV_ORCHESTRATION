package com.ericsson.irp.AlarmIRPConstDefs;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPConstDefs.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPConstDefs::TrendIndicationType
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPConstDefs/TrendIndicationType:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * enum TrendIndicationType {LessSevere, NoChange, MoreSevere};
 * </pre>
 */
public final class TrendIndicationType implements org.omg.CORBA.portable.IDLEntity {
  /**
	 * 
	 */
	private static final long serialVersionUID = 2925932101767353279L;


private /* [JDK 1.1.x BUG] final */ int __value;

  
  public static final int _LessSevere = 0;
  
  public static final int _NoChange = 1;
  
  public static final int _MoreSevere = 2;

  
  public static final TrendIndicationType LessSevere = new TrendIndicationType(_LessSevere);
  
  public static final TrendIndicationType NoChange = new TrendIndicationType(_NoChange);
  
  public static final TrendIndicationType MoreSevere = new TrendIndicationType(_MoreSevere);

  protected TrendIndicationType (final int _vis_value) {
    this.__value = _vis_value;
  }

  public int value () {
    return __value;
  }

  public static TrendIndicationType from_int (final int _vis_value) {
    switch (_vis_value) {
      case 0: return LessSevere;
      case 1: return NoChange;
      case 2: return MoreSevere;
      default: throw new org.omg.CORBA.BAD_PARAM();
    }
  }

}
