package com.ericsson.irp.NotificationIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPSystem::NotificationIRPOperations
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPSystem/NotificationIRPOperations:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface _NotificationIRPOperations {
  ...
};
 * </pre>
 */
public final class _NotificationIRPOperationsHelper {
  public static com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperations narrow (final org.omg.CORBA.Object obj) {
    return narrow(obj, false);
  }

  public static com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperations unchecked_narrow (org.omg.CORBA.Object obj) {
    return narrow(obj, true);
  }

  private static com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperations narrow (final org.omg.CORBA.Object obj, final boolean is_a) {
    if (obj == null) {
      return null;
    }
    if (obj instanceof com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperations) {
      return (com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperations)obj;
    }
    if (is_a || obj._is_a(id())) {
      final org.omg.CORBA.portable.ObjectImpl _obj = (org.omg.CORBA.portable.ObjectImpl)obj;
      com.ericsson.irp.NotificationIRPSystem.__NotificationIRPOperationsStub result = new com.ericsson.irp.NotificationIRPSystem.__NotificationIRPOperationsStub();
      final org.omg.CORBA.portable.Delegate _delegate = _obj._get_delegate();
      result._set_delegate(_delegate);
      return result;
    }
    throw new org.omg.CORBA.BAD_PARAM();
  }

  public java.lang.Object read_Object (final org.omg.CORBA.portable.InputStream istream) {
    return read(istream);
  }

  public void write_Object (final org.omg.CORBA.portable.OutputStream ostream, final java.lang.Object obj) {
    if (!(obj instanceof com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperations)) {
      throw new org.omg.CORBA.BAD_PARAM();
    }
    write(ostream, (com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperations)obj);
  }

  public java.lang.String get_id () {
    return id();
  }

  public org.omg.CORBA.TypeCode get_type () {
    return type();
  }

  private static org.omg.CORBA.TypeCode _type;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperations read (final org.omg.CORBA.portable.InputStream _input) {
    return narrow(_input.read_Object(com.ericsson.irp.NotificationIRPSystem.__NotificationIRPOperationsStub.class), true);
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperations _vis_value) {
    if (_vis_value != null && !(_vis_value instanceof org.omg.CORBA.portable.ObjectImpl)) {
      throw new org.omg.CORBA.BAD_PARAM();
    }
    _output.write_Object((org.omg.CORBA.Object)_vis_value);
  }

  public static void insert (final org.omg.CORBA.Any any, final com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperations _vis_value) {
    any.insert_Object((org.omg.CORBA.Object)_vis_value, com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperationsHelper.type());
  }

  public static com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperations extract (final org.omg.CORBA.Any any) {
    com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperations _vis_value;
    final org.omg.CORBA.Object _obj = any.extract_Object();
    _vis_value = com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperationsHelper.narrow(_obj);
    return _vis_value;
  }

  public static org.omg.CORBA.TypeCode type () {
    if (_type == null) {
      synchronized (org.omg.CORBA.TypeCode.class) {
        if (_type == null) {
          _type = _orb().create_interface_tc(id(), "NotificationIRPOperations");
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:3gppsa5.org/NotificationIRPSystem/NotificationIRPOperations:1.0";
  }
}
