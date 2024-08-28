package com.ericsson.irp.AlarmIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPSystem::ParameterNotSupported
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPSystem/ParameterNotSupported:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception ParameterNotSupported {
  ...
};
 * </pre>
 */
public final class ParameterNotSupportedHelper {
  private static org.omg.CORBA.TypeCode _type;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static com.ericsson.irp.AlarmIRPSystem.ParameterNotSupported read (final org.omg.CORBA.portable.InputStream _input) {
    if (!_input.read_string().equals(id())) {
      throw new org.omg.CORBA.MARSHAL("Mismatched repository id");
    }
    final com.ericsson.irp.AlarmIRPSystem.ParameterNotSupported _result = new com.ericsson.irp.AlarmIRPSystem.ParameterNotSupported();
    _result.parameter = _input.read_string();
    return _result;
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final com.ericsson.irp.AlarmIRPSystem.ParameterNotSupported _vis_value) {
    _output.write_string(id());
    _output.write_string((java.lang.String)_vis_value.parameter);
  }

  public static void insert (final org.omg.CORBA.Any any, final com.ericsson.irp.AlarmIRPSystem.ParameterNotSupported _vis_value) {
    any.insert_Streamable(new com.ericsson.irp.AlarmIRPSystem.ParameterNotSupportedHolder(_vis_value));
  }

  public static com.ericsson.irp.AlarmIRPSystem.ParameterNotSupported extract (final org.omg.CORBA.Any any) {
    com.ericsson.irp.AlarmIRPSystem.ParameterNotSupported _vis_value;
    _vis_value = com.ericsson.irp.AlarmIRPSystem.ParameterNotSupportedHelper.read(any.create_input_stream());
    return _vis_value;
  }

  public static org.omg.CORBA.TypeCode type () {
    if (_type == null) {
      synchronized (org.omg.CORBA.TypeCode.class) {
        if (_type == null) {
          final org.omg.CORBA.StructMember[] members = new org.omg.CORBA.StructMember[1];
          members[0] = new org.omg.CORBA.StructMember("parameter", _orb().get_primitive_tc(org.omg.CORBA.TCKind.tk_string), null);
          _type = _orb().create_exception_tc(id(), "ParameterNotSupported", members);
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:3gppsa5.org/AlarmIRPSystem/ParameterNotSupported:1.0";
  }
}
