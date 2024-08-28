package com.ericsson.irp.CosNaming;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNaming.idl"
 * <li> <b>IDL Name</b>      ::CosNaming::Binding
 * <li> <b>Repository Id</b> IDL:omg.org/CosNaming/Binding:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * struct Binding {
  ...
};
 * </pre>
 */
public final class BindingHelper {
  private static org.omg.CORBA.TypeCode _type;
  private static boolean _initializing;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static com.ericsson.irp.CosNaming.Binding read (final org.omg.CORBA.portable.InputStream _input) {
    final com.ericsson.irp.CosNaming.Binding _result = new com.ericsson.irp.CosNaming.Binding();
    _result.binding_name = com.ericsson.irp.CosNaming.NameHelper.read(_input);
    _result.binding_type = com.ericsson.irp.CosNaming.BindingTypeHelper.read(_input);
    return _result;
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final com.ericsson.irp.CosNaming.Binding _vis_value) {
    com.ericsson.irp.CosNaming.NameHelper.write(_output, _vis_value.binding_name);
    com.ericsson.irp.CosNaming.BindingTypeHelper.write(_output, _vis_value.binding_type);
  }

  public static void insert (final org.omg.CORBA.Any any, final com.ericsson.irp.CosNaming.Binding _vis_value) {
    any.insert_Streamable(new com.ericsson.irp.CosNaming.BindingHolder(_vis_value));
  }

  public static com.ericsson.irp.CosNaming.Binding extract (final org.omg.CORBA.Any any) {
    com.ericsson.irp.CosNaming.Binding _vis_value;
    _vis_value = com.ericsson.irp.CosNaming.BindingHelper.read(any.create_input_stream());
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
          members[0] = new org.omg.CORBA.StructMember("binding_name", com.ericsson.irp.CosNaming.NameHelper.type(), null);
          members[1] = new org.omg.CORBA.StructMember("binding_type", com.ericsson.irp.CosNaming.BindingTypeHelper.type(), null);
          _type = _orb().create_struct_tc(id(), "Binding", members);
          _initializing = false;
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:omg.org/CosNaming/Binding:1.0";
  }
}
