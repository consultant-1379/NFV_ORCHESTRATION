package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::InvalidEventType
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/InvalidEventType:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception InvalidEventType {
  ...
};
 * </pre>
 */
public final class InvalidEventTypeHelper {
  private static org.omg.CORBA.TypeCode _type;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static com.ericsson.irp.CosNotifyComm.InvalidEventType read (final org.omg.CORBA.portable.InputStream _input) {
    if (!_input.read_string().equals(id())) {
      throw new org.omg.CORBA.MARSHAL("Mismatched repository id");
    }
    final com.ericsson.irp.CosNotifyComm.InvalidEventType _result = new com.ericsson.irp.CosNotifyComm.InvalidEventType();
    _result.type = org.omg.CosNotification.EventTypeHelper.read(_input);
    return _result;
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final com.ericsson.irp.CosNotifyComm.InvalidEventType _vis_value) {
    _output.write_string(id());
    if (_vis_value.type == null) {
      throw new org.omg.CORBA.BAD_PARAM("Invalid array length");
    }
    org.omg.CosNotification.EventTypeHelper.write(_output, _vis_value.type);
  }

  public static void insert (final org.omg.CORBA.Any any, final com.ericsson.irp.CosNotifyComm.InvalidEventType _vis_value) {
    any.insert_Streamable(new com.ericsson.irp.CosNotifyComm.InvalidEventTypeHolder(_vis_value));
  }

  public static com.ericsson.irp.CosNotifyComm.InvalidEventType extract (final org.omg.CORBA.Any any) {
    com.ericsson.irp.CosNotifyComm.InvalidEventType _vis_value;
    _vis_value = com.ericsson.irp.CosNotifyComm.InvalidEventTypeHelper.read(any.create_input_stream());
    return _vis_value;
  }

  public static org.omg.CORBA.TypeCode type () {
    if (_type == null) {
      synchronized (org.omg.CORBA.TypeCode.class) {
        if (_type == null) {
          final org.omg.CORBA.StructMember[] members = new org.omg.CORBA.StructMember[1];
          members[0] = new org.omg.CORBA.StructMember("type", org.omg.CosNotification.EventTypeHelper.type(), null);
          _type = _orb().create_exception_tc(id(), "InvalidEventType", members);
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:omg.org/CosNotifyComm/InvalidEventType:1.0";
  }
}
