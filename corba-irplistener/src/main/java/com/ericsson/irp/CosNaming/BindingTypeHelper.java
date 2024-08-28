package com.ericsson.irp.CosNaming;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNaming.idl"
 * <li> <b>IDL Name</b>      ::CosNaming::BindingType
 * <li> <b>Repository Id</b> IDL:omg.org/CosNaming/BindingType:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * enum BindingType {nobject, ncontext};
 * </pre>
 */
public final class BindingTypeHelper {
  private static org.omg.CORBA.TypeCode _type;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static com.ericsson.irp.CosNaming.BindingType read (final org.omg.CORBA.portable.InputStream _input) {
    return com.ericsson.irp.CosNaming.BindingType.from_int(_input.read_long());
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final com.ericsson.irp.CosNaming.BindingType _vis_value) {
    if (_vis_value == null) {
      throw new org.omg.CORBA.BAD_PARAM("Invalid array length");
    }
    _output.write_long(_vis_value.value());
  }

  public static void insert (final org.omg.CORBA.Any any, final com.ericsson.irp.CosNaming.BindingType _vis_value) {
    org.omg.CORBA.portable.OutputStream output = any.create_output_stream();
    com.ericsson.irp.CosNaming.BindingTypeHelper.write(output, _vis_value);
    any.read_value(output.create_input_stream(), com.ericsson.irp.CosNaming.BindingTypeHelper.type());

  }

  public static com.ericsson.irp.CosNaming.BindingType extract (final org.omg.CORBA.Any any) {
    com.ericsson.irp.CosNaming.BindingType _vis_value;
    _vis_value = com.ericsson.irp.CosNaming.BindingTypeHelper.read(any.create_input_stream());
    return _vis_value;
  }

  public static org.omg.CORBA.TypeCode type () {
    if (_type == null) {
      synchronized (org.omg.CORBA.TypeCode.class) {
        if (_type == null) {
          final java.lang.String members[] = new java.lang.String[] {"nobject",
                                                                     "ncontext"};
          _type = _orb().create_enum_tc(id(), "BindingType", members);
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:omg.org/CosNaming/BindingType:1.0";
  }
}
