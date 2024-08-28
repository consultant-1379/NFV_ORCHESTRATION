package com.ericsson.irp.NotificationIRPConstDefs;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPConstDefs.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPConstDefs::ExtendedEventTypePerNotificationCategory
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPConstDefs/ExtendedEventTypePerNotificationCategory:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * typedef sequence&ltstring&gt ExtendedEventTypePerNotificationCategory;
 * </pre>
 */
public final class ExtendedEventTypePerNotificationCategoryHelper {
  private static org.omg.CORBA.TypeCode _type;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static java.lang.String[] read (final org.omg.CORBA.portable.InputStream _input) {
    java.lang.String[] result;
    final int $length9 = _input.read_long();
    if($length9 != 0) {
    result = new java.lang.String[$length9];
    for (int $counter10 = 0; $counter10 < $length9; $counter10++) {
    result[$counter10] = _input.read_string();
    }
    }
    else {
    result = com.ericsson.irp.NotificationIRPConstDefs.ExtendedEventTypePerNotificationCategoryHelper.EMPTY_LIST;
    }
    return result;
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final java.lang.String[] _vis_value) {
    if(_vis_value == null){
      throw new org.omg.CORBA.BAD_PARAM("Null parameter");
    }
    _output.write_long(_vis_value.length);
    for (int $counter11 = 0;  $counter11 < _vis_value.length; $counter11++) {
    _output.write_string((java.lang.String)_vis_value[$counter11]);
    }
  }

  public static void insert (final org.omg.CORBA.Any any, final java.lang.String[] _vis_value) {
    any.type(com.ericsson.irp.NotificationIRPConstDefs.ExtendedEventTypePerNotificationCategoryHelper.type());
    any.insert_Streamable(new com.ericsson.irp.NotificationIRPConstDefs.ExtendedEventTypePerNotificationCategoryHolder(_vis_value));
  }

  public static java.lang.String[] extract (final org.omg.CORBA.Any any) {
    java.lang.String[] _vis_value;
    _vis_value = com.ericsson.irp.NotificationIRPConstDefs.ExtendedEventTypePerNotificationCategoryHelper.read(any.create_input_stream());
    return _vis_value;
  }

  public static org.omg.CORBA.TypeCode type () {
    if (_type == null) {
      synchronized (org.omg.CORBA.TypeCode.class) {
        if (_type == null) {
          org.omg.CORBA.TypeCode originalType = _orb().create_sequence_tc(0, _orb().get_primitive_tc(org.omg.CORBA.TCKind.tk_string));
          _type = _orb().create_alias_tc(id(), "ExtendedEventTypePerNotificationCategory", originalType);
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:3gppsa5.org/NotificationIRPConstDefs/ExtendedEventTypePerNotificationCategory:1.0";
  }
  public final static java.lang.String[] EMPTY_LIST = {};
}
