package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::ConstraintExp
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/ConstraintExp:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * struct ConstraintExp {
  ...
};
 * </pre>
 */
public final class ConstraintExpHelper {
  private static org.omg.CORBA.TypeCode _type;
  private static boolean _initializing;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static com.ericsson.irp.CosNotifyFilter.ConstraintExp read (final org.omg.CORBA.portable.InputStream _input) {
    final com.ericsson.irp.CosNotifyFilter.ConstraintExp _result = new com.ericsson.irp.CosNotifyFilter.ConstraintExp();
    _result.event_types = org.omg.CosNotification.EventTypeSeqHelper.read(_input);
    _result.constraint_expr = _input.read_string();
    return _result;
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final com.ericsson.irp.CosNotifyFilter.ConstraintExp _vis_value) {
    org.omg.CosNotification.EventTypeSeqHelper.write(_output, _vis_value.event_types);
    _output.write_string((java.lang.String)_vis_value.constraint_expr);
  }

  public static void insert (final org.omg.CORBA.Any any, final com.ericsson.irp.CosNotifyFilter.ConstraintExp _vis_value) {
    any.insert_Streamable(new com.ericsson.irp.CosNotifyFilter.ConstraintExpHolder(_vis_value));
  }

  public static com.ericsson.irp.CosNotifyFilter.ConstraintExp extract (final org.omg.CORBA.Any any) {
    com.ericsson.irp.CosNotifyFilter.ConstraintExp _vis_value;
    _vis_value = com.ericsson.irp.CosNotifyFilter.ConstraintExpHelper.read(any.create_input_stream());
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
          members[0] = new org.omg.CORBA.StructMember("event_types", org.omg.CosNotification.EventTypeSeqHelper.type(), null);
          members[1] = new org.omg.CORBA.StructMember("constraint_expr", _orb().get_primitive_tc(org.omg.CORBA.TCKind.tk_string), null);
          _type = _orb().create_struct_tc(id(), "ConstraintExp", members);
          _initializing = false;
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:omg.org/CosNotifyFilter/ConstraintExp:1.0";
  }
}
