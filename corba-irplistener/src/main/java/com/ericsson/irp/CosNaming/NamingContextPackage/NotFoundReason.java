package com.ericsson.irp.CosNaming.NamingContextPackage;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNaming.idl"
 * <li> <b>IDL Name</b>      ::CosNaming::NamingContext::NotFoundReason
 * <li> <b>Repository Id</b> IDL:omg.org/CosNaming/NamingContext/NotFoundReason:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * enum NotFoundReason {missing_node, not_context, not_object};
 * </pre>
 */
public final class NotFoundReason implements org.omg.CORBA.portable.IDLEntity {
  /**
	 * 
	 */
	private static final long serialVersionUID = -3196229791196734747L;


private /* [JDK 1.1.x BUG] final */ int __value;

  
  public static final int _missing_node = 0;
  
  public static final int _not_context = 1;
  
  public static final int _not_object = 2;

  
  public static final NotFoundReason missing_node = new NotFoundReason(_missing_node);
  
  public static final NotFoundReason not_context = new NotFoundReason(_not_context);
  
  public static final NotFoundReason not_object = new NotFoundReason(_not_object);

  protected NotFoundReason (final int _vis_value) {
    this.__value = _vis_value;
  }

  public int value () {
    return __value;
  }

  public static NotFoundReason from_int (final int _vis_value) {
    switch (_vis_value) {
      case 0: return missing_node;
      case 1: return not_context;
      case 2: return not_object;
      default: throw new org.omg.CORBA.BAD_PARAM();
    }
  }

}
