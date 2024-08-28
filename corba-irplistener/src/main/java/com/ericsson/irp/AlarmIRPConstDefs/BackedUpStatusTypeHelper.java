package com.ericsson.irp.AlarmIRPConstDefs;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPConstDefs.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPConstDefs::BackedUpStatusType
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPConstDefs/BackedUpStatusType:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * typedef boolean BackedUpStatusType;
 * </pre>
 */
public final class BackedUpStatusTypeHelper {
  private static org.omg.CORBA.TypeCode _type;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static boolean read (final org.omg.CORBA.portable.InputStream _input) {
    boolean result;
    result = _input.read_boolean();
    return result;
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final boolean _vis_value) {
    _output.write_boolean((boolean)_vis_value);
  }

  public static void insert (final org.omg.CORBA.Any any, final boolean _vis_value) {
    any.type(com.ericsson.irp.AlarmIRPConstDefs.BackedUpStatusTypeHelper.type());
    any.insert_boolean((boolean)_vis_value);
  }

  public static boolean extract (final org.omg.CORBA.Any any) {
    boolean _vis_value;
    _vis_value = any.extract_boolean();

    return _vis_value;
  }

  public static org.omg.CORBA.TypeCode type () {
    if (_type == null) {
      synchronized (org.omg.CORBA.TypeCode.class) {
        if (_type == null) {
          org.omg.CORBA.TypeCode originalType = _orb().get_primitive_tc(org.omg.CORBA.TCKind.tk_boolean);
          _type = _orb().create_alias_tc(id(), "BackedUpStatusType", originalType);
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:3gppsa5.org/AlarmIRPConstDefs/BackedUpStatusType:1.0";
  }
}
