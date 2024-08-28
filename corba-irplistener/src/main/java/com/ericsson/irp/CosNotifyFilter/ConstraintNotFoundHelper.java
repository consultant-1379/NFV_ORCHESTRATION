package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::ConstraintNotFound
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/ConstraintNotFound:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception ConstraintNotFound {
  ...
};
 * </pre>
 */
public final class ConstraintNotFoundHelper {
  private static org.omg.CORBA.TypeCode _type;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static com.ericsson.irp.CosNotifyFilter.ConstraintNotFound read (final org.omg.CORBA.portable.InputStream _input) {
    if (!_input.read_string().equals(id())) {
      throw new org.omg.CORBA.MARSHAL("Mismatched repository id");
    }
    final com.ericsson.irp.CosNotifyFilter.ConstraintNotFound _result = new com.ericsson.irp.CosNotifyFilter.ConstraintNotFound();
    _result.id = _input.read_long();
    return _result;
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final com.ericsson.irp.CosNotifyFilter.ConstraintNotFound _vis_value) {
    _output.write_string(id());
    _output.write_long((int)_vis_value.id);
  }

  public static void insert (final org.omg.CORBA.Any any, final com.ericsson.irp.CosNotifyFilter.ConstraintNotFound _vis_value) {
    any.insert_Streamable(new com.ericsson.irp.CosNotifyFilter.ConstraintNotFoundHolder(_vis_value));
  }

  public static com.ericsson.irp.CosNotifyFilter.ConstraintNotFound extract (final org.omg.CORBA.Any any) {
    com.ericsson.irp.CosNotifyFilter.ConstraintNotFound _vis_value;
    _vis_value = com.ericsson.irp.CosNotifyFilter.ConstraintNotFoundHelper.read(any.create_input_stream());
    return _vis_value;
  }

  public static org.omg.CORBA.TypeCode type () {
    if (_type == null) {
      synchronized (org.omg.CORBA.TypeCode.class) {
        if (_type == null) {
          final org.omg.CORBA.StructMember[] members = new org.omg.CORBA.StructMember[1];
          members[0] = new org.omg.CORBA.StructMember("id", com.ericsson.irp.CosNotifyFilter.ConstraintIDHelper.type(), null);
          _type = _orb().create_exception_tc(id(), "ConstraintNotFound", members);
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:omg.org/CosNotifyFilter/ConstraintNotFound:1.0";
  }
}
