package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::InvalidValue
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/InvalidValue:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception InvalidValue {
  ...
};
 * </pre>
 */
public final class InvalidValueHelper {
  private static org.omg.CORBA.TypeCode _type;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static com.ericsson.irp.CosNotifyFilter.InvalidValue read (final org.omg.CORBA.portable.InputStream _input) {
    if (!_input.read_string().equals(id())) {
      throw new org.omg.CORBA.MARSHAL("Mismatched repository id");
    }
    final com.ericsson.irp.CosNotifyFilter.InvalidValue _result = new com.ericsson.irp.CosNotifyFilter.InvalidValue();
    _result.constr = com.ericsson.irp.CosNotifyFilter.ConstraintExpHelper.read(_input);
    _result.value = _input.read_any();
    return _result;
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final com.ericsson.irp.CosNotifyFilter.InvalidValue _vis_value) {
    _output.write_string(id());
    if (_vis_value.constr == null) {
      throw new org.omg.CORBA.BAD_PARAM("Invalid array length");
    }
    com.ericsson.irp.CosNotifyFilter.ConstraintExpHelper.write(_output, _vis_value.constr);
    _output.write_any((org.omg.CORBA.Any)_vis_value.value);
  }

  public static void insert (final org.omg.CORBA.Any any, final com.ericsson.irp.CosNotifyFilter.InvalidValue _vis_value) {
    any.insert_Streamable(new com.ericsson.irp.CosNotifyFilter.InvalidValueHolder(_vis_value));
  }

  public static com.ericsson.irp.CosNotifyFilter.InvalidValue extract (final org.omg.CORBA.Any any) {
    com.ericsson.irp.CosNotifyFilter.InvalidValue _vis_value;
    _vis_value = com.ericsson.irp.CosNotifyFilter.InvalidValueHelper.read(any.create_input_stream());
    return _vis_value;
  }

  public static org.omg.CORBA.TypeCode type () {
    if (_type == null) {
      synchronized (org.omg.CORBA.TypeCode.class) {
        if (_type == null) {
          final org.omg.CORBA.StructMember[] members = new org.omg.CORBA.StructMember[2];
          members[0] = new org.omg.CORBA.StructMember("constr", com.ericsson.irp.CosNotifyFilter.ConstraintExpHelper.type(), null);
          members[1] = new org.omg.CORBA.StructMember("value", _orb().get_primitive_tc(org.omg.CORBA.TCKind.tk_any), null);
          _type = _orb().create_exception_tc(id(), "InvalidValue", members);
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:omg.org/CosNotifyFilter/InvalidValue:1.0";
  }
}
