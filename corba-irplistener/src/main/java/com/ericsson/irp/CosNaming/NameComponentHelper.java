package com.ericsson.irp.CosNaming;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNaming.idl"
 * <li> <b>IDL Name</b>      ::CosNaming::NameComponent
 * <li> <b>Repository Id</b> IDL:omg.org/CosNaming/NameComponent:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * struct NameComponent {
  ...
};
 * </pre>
 */
public final class NameComponentHelper {
  private static org.omg.CORBA.TypeCode _type;
  private static boolean _initializing;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static com.ericsson.irp.CosNaming.NameComponent read (final org.omg.CORBA.portable.InputStream _input) {
    final com.ericsson.irp.CosNaming.NameComponent _result = new com.ericsson.irp.CosNaming.NameComponent();
    _result.id = _input.read_string();
    _result.kind = _input.read_string();
    return _result;
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final com.ericsson.irp.CosNaming.NameComponent _vis_value) {
    _output.write_string((java.lang.String)_vis_value.id);
    _output.write_string((java.lang.String)_vis_value.kind);
  }

  public static void insert (final org.omg.CORBA.Any any, final com.ericsson.irp.CosNaming.NameComponent _vis_value) {
    any.insert_Streamable(new com.ericsson.irp.CosNaming.NameComponentHolder(_vis_value));
  }

  public static com.ericsson.irp.CosNaming.NameComponent extract (final org.omg.CORBA.Any any) {
    com.ericsson.irp.CosNaming.NameComponent _vis_value;
    _vis_value = com.ericsson.irp.CosNaming.NameComponentHelper.read(any.create_input_stream());
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
          members[0] = new org.omg.CORBA.StructMember("id", com.ericsson.irp.CosNaming.IstringHelper.type(), null);
          members[1] = new org.omg.CORBA.StructMember("kind", com.ericsson.irp.CosNaming.IstringHelper.type(), null);
          _type = _orb().create_struct_tc(id(), "NameComponent", members);
          _initializing = false;
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:omg.org/CosNaming/NameComponent:1.0";
  }
}
