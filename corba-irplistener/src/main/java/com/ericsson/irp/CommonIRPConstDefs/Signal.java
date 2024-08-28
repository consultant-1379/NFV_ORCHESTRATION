package com.ericsson.irp.CommonIRPConstDefs;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CommonIRPConstDefs.idl"
 * <li> <b>IDL Name</b>      ::CommonIRPConstDefs::Signal
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/CommonIRPConstDefs/Signal:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * enum Signal {OK, Failure, PartialFailure};
 * </pre>
 */
public final class Signal implements org.omg.CORBA.portable.IDLEntity {
  /**
	 * 
	 */
	private static final long serialVersionUID = -2034704236820030653L;


private /* [JDK 1.1.x BUG] final */ int __value;

  
  public static final int _OK = 0;
  
  public static final int _Failure = 1;
  
  public static final int _PartialFailure = 2;

  
  public static final Signal OK = new Signal(_OK);
  
  public static final Signal Failure = new Signal(_Failure);
  
  public static final Signal PartialFailure = new Signal(_PartialFailure);

  protected Signal (final int _vis_value) {
    this.__value = _vis_value;
  }

  public int value () {
    return __value;
  }

  public static Signal from_int (final int _vis_value) {
    switch (_vis_value) {
      case 0: return OK;
      case 1: return Failure;
      case 2: return PartialFailure;
      default: throw new org.omg.CORBA.BAD_PARAM();
    }
  }

}
