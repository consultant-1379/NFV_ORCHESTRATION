package com.ericsson.irp.NotificationIRPConstDefs;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPConstDefs.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPConstDefs::EventTypesSet
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPConstDefs/EventTypesSet:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * typedef sequence&ltcom.ericsson.irp.NotificationIRPConstDefs.EventTypesPerNotificationCategory&gt EventTypesSet;
 * </pre>
 */
public final class EventTypesSetHelper {
  private static org.omg.CORBA.TypeCode _type;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static java.lang.String[][] read (final org.omg.CORBA.portable.InputStream _input) {
    java.lang.String[][] result;
    final int $length6 = _input.read_long();
    if($length6 != 0) {
    result = new java.lang.String[$length6][];
    for (int $counter7 = 0; $counter7 < $length6; $counter7++) {
    result[$counter7] = com.ericsson.irp.NotificationIRPConstDefs.EventTypesPerNotificationCategoryHelper.read(_input);
    }
    }
    else {
    result = com.ericsson.irp.NotificationIRPConstDefs.EventTypesSetHelper.EMPTY_LIST;
    }
    return result;
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final java.lang.String[][] _vis_value) {
    if(_vis_value == null){
      throw new org.omg.CORBA.BAD_PARAM("Null parameter");
    }
    _output.write_long(_vis_value.length);
    for (int $counter8 = 0;  $counter8 < _vis_value.length; $counter8++) {
    com.ericsson.irp.NotificationIRPConstDefs.EventTypesPerNotificationCategoryHelper.write(_output, _vis_value[$counter8]);
    }
  }

  public static void insert (final org.omg.CORBA.Any any, final java.lang.String[][] _vis_value) {
    any.type(com.ericsson.irp.NotificationIRPConstDefs.EventTypesSetHelper.type());
    any.insert_Streamable(new com.ericsson.irp.NotificationIRPConstDefs.EventTypesSetHolder(_vis_value));
  }

  public static java.lang.String[][] extract (final org.omg.CORBA.Any any) {
    java.lang.String[][] _vis_value;
    _vis_value = com.ericsson.irp.NotificationIRPConstDefs.EventTypesSetHelper.read(any.create_input_stream());
    return _vis_value;
  }

  public static org.omg.CORBA.TypeCode type () {
    if (_type == null) {
      synchronized (org.omg.CORBA.TypeCode.class) {
        if (_type == null) {
          org.omg.CORBA.TypeCode originalType = _orb().create_sequence_tc(0, com.ericsson.irp.NotificationIRPConstDefs.EventTypesPerNotificationCategoryHelper.type());
          _type = _orb().create_alias_tc(id(), "EventTypesSet", originalType);
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:3gppsa5.org/NotificationIRPConstDefs/EventTypesSet:1.0";
  }
  public final static java.lang.String[][] EMPTY_LIST = {};
}
