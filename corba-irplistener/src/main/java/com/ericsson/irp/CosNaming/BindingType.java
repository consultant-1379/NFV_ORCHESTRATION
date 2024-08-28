package com.ericsson.irp.CosNaming;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNaming.idl"
 * <li> <b>IDL Name</b>      ::CosNaming::BindingType
 * <li> <b>Repository Id</b> IDL:omg.org/CosNaming/BindingType:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * enum BindingType {nobject, ncontext};
 * </pre>
 */
public final class BindingType implements org.omg.CORBA.portable.IDLEntity {
  /**
	 * 
	 */
	private static final long serialVersionUID = 8882990080532241907L;


private /* [JDK 1.1.x BUG] final */ int __value;

  
  public static final int _nobject = 0;
  
  public static final int _ncontext = 1;

  
  public static final BindingType nobject = new BindingType(_nobject);
  
  public static final BindingType ncontext = new BindingType(_ncontext);

  protected BindingType (final int _vis_value) {
    this.__value = _vis_value;
  }

  public int value () {
    return __value;
  }

  public static BindingType from_int (final int _vis_value) {
    switch (_vis_value) {
      case 0: return nobject;
      case 1: return ncontext;
      default: throw new org.omg.CORBA.BAD_PARAM();
    }
  }

}
