package com.ericsson.irp.CosNaming.NamingContextPackage;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNaming.idl"
 * <li> <b>IDL Name</b>      ::CosNaming::NamingContext::CannotProceed
 * <li> <b>Repository Id</b> IDL:omg.org/CosNaming/NamingContext/CannotProceed:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception CannotProceed {
  ...
};
 * </pre>
 */
public final class CannotProceedHelper {
  private static org.omg.CORBA.TypeCode _type;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed read (final org.omg.CORBA.portable.InputStream _input) {
    if (!_input.read_string().equals(id())) {
      throw new org.omg.CORBA.MARSHAL("Mismatched repository id");
    }
    final com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed _result = new com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed();
    _result.cxt = com.ericsson.irp.CosNaming.NamingContextHelper.read(_input);
    _result.rest_of_name = com.ericsson.irp.CosNaming.NameHelper.read(_input);
    return _result;
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed _vis_value) {
    _output.write_string(id());
    com.ericsson.irp.CosNaming.NamingContextHelper.write(_output, _vis_value.cxt);
    com.ericsson.irp.CosNaming.NameHelper.write(_output, _vis_value.rest_of_name);
  }

  public static void insert (final org.omg.CORBA.Any any, final com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed _vis_value) {
    any.insert_Streamable(new com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceedHolder(_vis_value));
  }

  public static com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed extract (final org.omg.CORBA.Any any) {
    com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed _vis_value;
    _vis_value = com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceedHelper.read(any.create_input_stream());
    return _vis_value;
  }

  public static org.omg.CORBA.TypeCode type () {
    if (_type == null) {
      synchronized (org.omg.CORBA.TypeCode.class) {
        if (_type == null) {
          final org.omg.CORBA.StructMember[] members = new org.omg.CORBA.StructMember[2];
          members[0] = new org.omg.CORBA.StructMember("cxt", com.ericsson.irp.CosNaming.NamingContextHelper.type(), null);
          members[1] = new org.omg.CORBA.StructMember("rest_of_name", com.ericsson.irp.CosNaming.NameHelper.type(), null);
          _type = _orb().create_exception_tc(id(), "CannotProceed", members);
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:omg.org/CosNaming/NamingContext/CannotProceed:1.0";
  }
}
