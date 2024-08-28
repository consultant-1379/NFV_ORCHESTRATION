package com.ericsson.irp.NotificationIRPConstDefs;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPConstDefs.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPConstDefs::CorrelatedNotificationSetType
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPConstDefs/CorrelatedNotificationSetType:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * typedef sequence&ltcom.ericsson.irp.NotificationIRPConstDefs.CorrelatedNotification&gt CorrelatedNotificationSetType;
 * </pre>
 */
public final class CorrelatedNotificationSetTypeHelper {
  private static org.omg.CORBA.TypeCode _type;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static com.ericsson.irp.NotificationIRPConstDefs.CorrelatedNotification[] read (final org.omg.CORBA.portable.InputStream _input) {
    com.ericsson.irp.NotificationIRPConstDefs.CorrelatedNotification[] result;
    final int $length16 = _input.read_long();
    if($length16 != 0) {
    result = new com.ericsson.irp.NotificationIRPConstDefs.CorrelatedNotification[$length16];
    for (int $counter17 = 0; $counter17 < $length16; $counter17++) {
    result[$counter17] = com.ericsson.irp.NotificationIRPConstDefs.CorrelatedNotificationHelper.read(_input);
    }
    }
    else {
    result = com.ericsson.irp.NotificationIRPConstDefs.CorrelatedNotificationSetTypeHelper.EMPTY_LIST;
    }
    return result;
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final com.ericsson.irp.NotificationIRPConstDefs.CorrelatedNotification[] _vis_value) {
    if(_vis_value == null){
      throw new org.omg.CORBA.BAD_PARAM("Null parameter");
    }
    _output.write_long(_vis_value.length);
    for (int $counter18 = 0;  $counter18 < _vis_value.length; $counter18++) {
    if (_vis_value[$counter18] == null) {
      throw new org.omg.CORBA.BAD_PARAM("Invalid array length");
    }
    com.ericsson.irp.NotificationIRPConstDefs.CorrelatedNotificationHelper.write(_output, _vis_value[$counter18]);
    }
  }

  public static void insert (final org.omg.CORBA.Any any, final com.ericsson.irp.NotificationIRPConstDefs.CorrelatedNotification[] _vis_value) {
    any.type(com.ericsson.irp.NotificationIRPConstDefs.CorrelatedNotificationSetTypeHelper.type());
    any.insert_Streamable(new com.ericsson.irp.NotificationIRPConstDefs.CorrelatedNotificationSetTypeHolder(_vis_value));
  }

  public static com.ericsson.irp.NotificationIRPConstDefs.CorrelatedNotification[] extract (final org.omg.CORBA.Any any) {
    com.ericsson.irp.NotificationIRPConstDefs.CorrelatedNotification[] _vis_value;
    _vis_value = com.ericsson.irp.NotificationIRPConstDefs.CorrelatedNotificationSetTypeHelper.read(any.create_input_stream());
    return _vis_value;
  }

  public static org.omg.CORBA.TypeCode type () {
    if (_type == null) {
      synchronized (org.omg.CORBA.TypeCode.class) {
        if (_type == null) {
          org.omg.CORBA.TypeCode originalType = _orb().create_sequence_tc(0, com.ericsson.irp.NotificationIRPConstDefs.CorrelatedNotificationHelper.type());
          _type = _orb().create_alias_tc(id(), "CorrelatedNotificationSetType", originalType);
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:3gppsa5.org/NotificationIRPConstDefs/CorrelatedNotificationSetType:1.0";
  }
  public final static com.ericsson.irp.NotificationIRPConstDefs.CorrelatedNotification[] EMPTY_LIST = {};
}
