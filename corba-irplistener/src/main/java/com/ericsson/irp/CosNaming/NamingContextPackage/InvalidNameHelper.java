package com.ericsson.irp.CosNaming.NamingContextPackage;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNaming.idl"
 * <li> <b>IDL Name</b>      ::CosNaming::NamingContext::InvalidName
 * <li> <b>Repository Id</b> IDL:omg.org/CosNaming/NamingContext/InvalidName:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception InvalidName {
  ...
};
 * </pre>
 */
public final class InvalidNameHelper {
  private static org.omg.CORBA.TypeCode _type;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName read (final org.omg.CORBA.portable.InputStream _input) {
    if (!_input.read_string().equals(id())) {
      throw new org.omg.CORBA.MARSHAL("Mismatched repository id");
    }
    final com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName _result = new com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName();
    return _result;
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName _vis_value) {
    _output.write_string(id());
  }

  public static void insert (final org.omg.CORBA.Any any, final com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName _vis_value) {
    any.insert_Streamable(new com.ericsson.irp.CosNaming.NamingContextPackage.InvalidNameHolder(_vis_value));
  }

  public static com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName extract (final org.omg.CORBA.Any any) {
    com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName _vis_value;
    _vis_value = com.ericsson.irp.CosNaming.NamingContextPackage.InvalidNameHelper.read(any.create_input_stream());
    return _vis_value;
  }

  public static org.omg.CORBA.TypeCode type () {
    if (_type == null) {
      synchronized (org.omg.CORBA.TypeCode.class) {
        if (_type == null) {
          final org.omg.CORBA.StructMember[] members = new org.omg.CORBA.StructMember[0];
          
          _type = _orb().create_exception_tc(id(), "InvalidName", members);
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:omg.org/CosNaming/NamingContext/InvalidName:1.0";
  }
}
