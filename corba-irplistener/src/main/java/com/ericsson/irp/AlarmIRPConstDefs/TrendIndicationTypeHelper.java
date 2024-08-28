package com.ericsson.irp.AlarmIRPConstDefs;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPConstDefs.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPConstDefs::TrendIndicationType
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPConstDefs/TrendIndicationType:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * enum TrendIndicationType {LessSevere, NoChange, MoreSevere};
 * </pre>
 */
public final class TrendIndicationTypeHelper {
  private static org.omg.CORBA.TypeCode _type;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static com.ericsson.irp.AlarmIRPConstDefs.TrendIndicationType read (final org.omg.CORBA.portable.InputStream _input) {
    return com.ericsson.irp.AlarmIRPConstDefs.TrendIndicationType.from_int(_input.read_long());
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final com.ericsson.irp.AlarmIRPConstDefs.TrendIndicationType _vis_value) {
    if (_vis_value == null) {
      throw new org.omg.CORBA.BAD_PARAM("Invalid array length");
    }
    _output.write_long(_vis_value.value());
  }

  public static void insert (final org.omg.CORBA.Any any, final com.ericsson.irp.AlarmIRPConstDefs.TrendIndicationType _vis_value) {
    org.omg.CORBA.portable.OutputStream output = any.create_output_stream();
    com.ericsson.irp.AlarmIRPConstDefs.TrendIndicationTypeHelper.write(output, _vis_value);
    any.read_value(output.create_input_stream(), com.ericsson.irp.AlarmIRPConstDefs.TrendIndicationTypeHelper.type());

  }

  public static com.ericsson.irp.AlarmIRPConstDefs.TrendIndicationType extract (final org.omg.CORBA.Any any) {
    com.ericsson.irp.AlarmIRPConstDefs.TrendIndicationType _vis_value;
    _vis_value = com.ericsson.irp.AlarmIRPConstDefs.TrendIndicationTypeHelper.read(any.create_input_stream());
    return _vis_value;
  }

  public static org.omg.CORBA.TypeCode type () {
    if (_type == null) {
      synchronized (org.omg.CORBA.TypeCode.class) {
        if (_type == null) {
          final java.lang.String members[] = new java.lang.String[] {"LessSevere",
                                                                     "NoChange",
                                                                     "MoreSevere"};
          _type = _orb().create_enum_tc(id(), "TrendIndicationType", members);
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:3gppsa5.org/AlarmIRPConstDefs/TrendIndicationType:1.0";
  }
}
