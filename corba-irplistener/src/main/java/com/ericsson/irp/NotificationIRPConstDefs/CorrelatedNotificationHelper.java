package com.ericsson.irp.NotificationIRPConstDefs;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPConstDefs.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPConstDefs::CorrelatedNotification
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPConstDefs/CorrelatedNotification:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * struct CorrelatedNotification {
  ...
};
 * </pre>
 */
public final class CorrelatedNotificationHelper {
  private static org.omg.CORBA.TypeCode _type;
  private static boolean _initializing;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static com.ericsson.irp.NotificationIRPConstDefs.CorrelatedNotification read (final org.omg.CORBA.portable.InputStream _input) {
    final com.ericsson.irp.NotificationIRPConstDefs.CorrelatedNotification _result = new com.ericsson.irp.NotificationIRPConstDefs.CorrelatedNotification();
    _result.source = _input.read_string();
    _result.notifIDSet = com.ericsson.irp.NotificationIRPConstDefs.NotifIDSetTypeHelper.read(_input);
    return _result;
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final com.ericsson.irp.NotificationIRPConstDefs.CorrelatedNotification _vis_value) {
    _output.write_string((java.lang.String)_vis_value.source);
    com.ericsson.irp.NotificationIRPConstDefs.NotifIDSetTypeHelper.write(_output, _vis_value.notifIDSet);
  }

  public static void insert (final org.omg.CORBA.Any any, final com.ericsson.irp.NotificationIRPConstDefs.CorrelatedNotification _vis_value) {
    any.insert_Streamable(new com.ericsson.irp.NotificationIRPConstDefs.CorrelatedNotificationHolder(_vis_value));
  }

  public static com.ericsson.irp.NotificationIRPConstDefs.CorrelatedNotification extract (final org.omg.CORBA.Any any) {
    com.ericsson.irp.NotificationIRPConstDefs.CorrelatedNotification _vis_value;
    _vis_value = com.ericsson.irp.NotificationIRPConstDefs.CorrelatedNotificationHelper.read(any.create_input_stream());
    return _vis_value;
  }

  public static org.omg.CORBA.TypeCode type () {
    if (_type == null) {
      synchronized (org.omg.CORBA.TypeCode.class) {
        if (_type == null) {
          if (_initializing) {
            return _orb().create_recursive_tc(id());
          }
          _initializing = true;
          final org.omg.CORBA.StructMember[] members = new org.omg.CORBA.StructMember[2];
          members[0] = new org.omg.CORBA.StructMember("source", _orb().get_primitive_tc(org.omg.CORBA.TCKind.tk_string), null);
          members[1] = new org.omg.CORBA.StructMember("notifIDSet", com.ericsson.irp.NotificationIRPConstDefs.NotifIDSetTypeHelper.type(), null);
          _type = _orb().create_struct_tc(id(), "CorrelatedNotification", members);
          _initializing = false;
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:3gppsa5.org/NotificationIRPConstDefs/CorrelatedNotification:1.0";
  }
}
