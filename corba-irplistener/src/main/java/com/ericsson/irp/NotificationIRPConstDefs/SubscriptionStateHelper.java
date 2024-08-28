package com.ericsson.irp.NotificationIRPConstDefs;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPConstDefs.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPConstDefs::SubscriptionState
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPConstDefs/SubscriptionState:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * enum SubscriptionState {Inactive, Active, DontKnow};
 * </pre>
 */
public final class SubscriptionStateHelper {
  private static org.omg.CORBA.TypeCode _type;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static com.ericsson.irp.NotificationIRPConstDefs.SubscriptionState read (final org.omg.CORBA.portable.InputStream _input) {
    return com.ericsson.irp.NotificationIRPConstDefs.SubscriptionState.from_int(_input.read_long());
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final com.ericsson.irp.NotificationIRPConstDefs.SubscriptionState _vis_value) {
    if (_vis_value == null) {
      throw new org.omg.CORBA.BAD_PARAM("Invalid array length");
    }
    _output.write_long(_vis_value.value());
  }

  public static void insert (final org.omg.CORBA.Any any, final com.ericsson.irp.NotificationIRPConstDefs.SubscriptionState _vis_value) {
    org.omg.CORBA.portable.OutputStream output = any.create_output_stream();
    com.ericsson.irp.NotificationIRPConstDefs.SubscriptionStateHelper.write(output, _vis_value);
    any.read_value(output.create_input_stream(), com.ericsson.irp.NotificationIRPConstDefs.SubscriptionStateHelper.type());

  }

  public static com.ericsson.irp.NotificationIRPConstDefs.SubscriptionState extract (final org.omg.CORBA.Any any) {
    com.ericsson.irp.NotificationIRPConstDefs.SubscriptionState _vis_value;
    _vis_value = com.ericsson.irp.NotificationIRPConstDefs.SubscriptionStateHelper.read(any.create_input_stream());
    return _vis_value;
  }

  public static org.omg.CORBA.TypeCode type () {
    if (_type == null) {
      synchronized (org.omg.CORBA.TypeCode.class) {
        if (_type == null) {
          final java.lang.String members[] = new java.lang.String[] {"Inactive",
                                                                     "Active",
                                                                     "DontKnow"};
          _type = _orb().create_enum_tc(id(), "SubscriptionState", members);
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:3gppsa5.org/NotificationIRPConstDefs/SubscriptionState:1.0";
  }
}
