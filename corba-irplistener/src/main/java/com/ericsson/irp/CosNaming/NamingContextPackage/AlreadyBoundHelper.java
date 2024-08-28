package com.ericsson.irp.CosNaming.NamingContextPackage;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNaming.idl"
 * <li> <b>IDL Name</b>      ::CosNaming::NamingContext::AlreadyBound
 * <li> <b>Repository Id</b> IDL:omg.org/CosNaming/NamingContext/AlreadyBound:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception AlreadyBound {
  ...
};
 * </pre>
 */
public final class AlreadyBoundHelper {
  private static org.omg.CORBA.TypeCode _type;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBound read (final org.omg.CORBA.portable.InputStream _input) {
    if (!_input.read_string().equals(id())) {
      throw new org.omg.CORBA.MARSHAL("Mismatched repository id");
    }
    final com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBound _result = new com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBound();
    return _result;
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBound _vis_value) {
    _output.write_string(id());
  }

  public static void insert (final org.omg.CORBA.Any any, final com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBound _vis_value) {
    any.insert_Streamable(new com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBoundHolder(_vis_value));
  }

  public static com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBound extract (final org.omg.CORBA.Any any) {
    com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBound _vis_value;
    _vis_value = com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBoundHelper.read(any.create_input_stream());
    return _vis_value;
  }

  public static org.omg.CORBA.TypeCode type () {
    if (_type == null) {
      synchronized (org.omg.CORBA.TypeCode.class) {
        if (_type == null) {
          final org.omg.CORBA.StructMember[] members = new org.omg.CORBA.StructMember[0];
          
          _type = _orb().create_exception_tc(id(), "AlreadyBound", members);
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:omg.org/CosNaming/NamingContext/AlreadyBound:1.0";
  }
}
