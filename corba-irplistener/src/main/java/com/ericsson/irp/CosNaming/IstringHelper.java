package com.ericsson.irp.CosNaming;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNaming.idl"
 * <li> <b>IDL Name</b>      ::CosNaming::Istring
 * <li> <b>Repository Id</b> IDL:omg.org/CosNaming/Istring:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * typedef string Istring;
 * </pre>
 */
public final class IstringHelper {
  private static org.omg.CORBA.TypeCode _type;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static java.lang.String read (final org.omg.CORBA.portable.InputStream _input) {
    java.lang.String result;
    result = _input.read_string();
    return result;
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final java.lang.String _vis_value) {
    _output.write_string((java.lang.String)_vis_value);
  }

  public static void insert (final org.omg.CORBA.Any any, final java.lang.String _vis_value) {
    any.type(com.ericsson.irp.CosNaming.IstringHelper.type());
    any.insert_string((java.lang.String)_vis_value);
  }

  public static java.lang.String extract (final org.omg.CORBA.Any any) {
    java.lang.String _vis_value;
    _vis_value = any.extract_string();

    return _vis_value;
  }

  public static org.omg.CORBA.TypeCode type () {
    if (_type == null) {
      synchronized (org.omg.CORBA.TypeCode.class) {
        if (_type == null) {
          org.omg.CORBA.TypeCode originalType = _orb().get_primitive_tc(org.omg.CORBA.TCKind.tk_string);
          _type = _orb().create_alias_tc(id(), "Istring", originalType);
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:omg.org/CosNaming/Istring:1.0";
  }
}
