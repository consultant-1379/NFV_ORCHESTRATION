package com.ericsson.irp.NotificationIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPSystem::GetSubscriptionStatus
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPSystem/GetSubscriptionStatus:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception GetSubscriptionStatus {
  ...
};
 * </pre>
 */
public final class GetSubscriptionStatusHelper {
  private static org.omg.CORBA.TypeCode _type;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static com.ericsson.irp.NotificationIRPSystem.GetSubscriptionStatus read (final org.omg.CORBA.portable.InputStream _input) {
    if (!_input.read_string().equals(id())) {
      throw new org.omg.CORBA.MARSHAL("Mismatched repository id");
    }
    final com.ericsson.irp.NotificationIRPSystem.GetSubscriptionStatus _result = new com.ericsson.irp.NotificationIRPSystem.GetSubscriptionStatus();
    _result.reason = _input.read_string();
    return _result;
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final com.ericsson.irp.NotificationIRPSystem.GetSubscriptionStatus _vis_value) {
    _output.write_string(id());
    _output.write_string((java.lang.String)_vis_value.reason);
  }

  public static void insert (final org.omg.CORBA.Any any, final com.ericsson.irp.NotificationIRPSystem.GetSubscriptionStatus _vis_value) {
    any.insert_Streamable(new com.ericsson.irp.NotificationIRPSystem.GetSubscriptionStatusHolder(_vis_value));
  }

  public static com.ericsson.irp.NotificationIRPSystem.GetSubscriptionStatus extract (final org.omg.CORBA.Any any) {
    com.ericsson.irp.NotificationIRPSystem.GetSubscriptionStatus _vis_value;
    _vis_value = com.ericsson.irp.NotificationIRPSystem.GetSubscriptionStatusHelper.read(any.create_input_stream());
    return _vis_value;
  }

  public static org.omg.CORBA.TypeCode type () {
    if (_type == null) {
      synchronized (org.omg.CORBA.TypeCode.class) {
        if (_type == null) {
          final org.omg.CORBA.StructMember[] members = new org.omg.CORBA.StructMember[1];
          members[0] = new org.omg.CORBA.StructMember("reason", _orb().get_primitive_tc(org.omg.CORBA.TCKind.tk_string), null);
          _type = _orb().create_exception_tc(id(), "GetSubscriptionStatus", members);
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:3gppsa5.org/NotificationIRPSystem/GetSubscriptionStatus:1.0";
  }
}
