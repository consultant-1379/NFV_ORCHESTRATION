package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::ConstraintInfo
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/ConstraintInfo:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * struct ConstraintInfo {
  ...
};
 * </pre>
 */
public final class ConstraintInfoHelper {
  private static org.omg.CORBA.TypeCode _type;
  private static boolean _initializing;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static com.ericsson.irp.CosNotifyFilter.ConstraintInfo read (final org.omg.CORBA.portable.InputStream _input) {
    final com.ericsson.irp.CosNotifyFilter.ConstraintInfo _result = new com.ericsson.irp.CosNotifyFilter.ConstraintInfo();
    _result.constraint_expression = com.ericsson.irp.CosNotifyFilter.ConstraintExpHelper.read(_input);
    _result.constraint_id = _input.read_long();
    return _result;
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final com.ericsson.irp.CosNotifyFilter.ConstraintInfo _vis_value) {
    if (_vis_value.constraint_expression == null) {
      throw new org.omg.CORBA.BAD_PARAM("Invalid array length");
    }
    com.ericsson.irp.CosNotifyFilter.ConstraintExpHelper.write(_output, _vis_value.constraint_expression);
    _output.write_long((int)_vis_value.constraint_id);
  }

  public static void insert (final org.omg.CORBA.Any any, final com.ericsson.irp.CosNotifyFilter.ConstraintInfo _vis_value) {
    any.insert_Streamable(new com.ericsson.irp.CosNotifyFilter.ConstraintInfoHolder(_vis_value));
  }

  public static com.ericsson.irp.CosNotifyFilter.ConstraintInfo extract (final org.omg.CORBA.Any any) {
    com.ericsson.irp.CosNotifyFilter.ConstraintInfo _vis_value;
    _vis_value = com.ericsson.irp.CosNotifyFilter.ConstraintInfoHelper.read(any.create_input_stream());
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
          members[0] = new org.omg.CORBA.StructMember("constraint_expression", com.ericsson.irp.CosNotifyFilter.ConstraintExpHelper.type(), null);
          members[1] = new org.omg.CORBA.StructMember("constraint_id", com.ericsson.irp.CosNotifyFilter.ConstraintIDHelper.type(), null);
          _type = _orb().create_struct_tc(id(), "ConstraintInfo", members);
          _initializing = false;
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:omg.org/CosNotifyFilter/ConstraintInfo:1.0";
  }
}
