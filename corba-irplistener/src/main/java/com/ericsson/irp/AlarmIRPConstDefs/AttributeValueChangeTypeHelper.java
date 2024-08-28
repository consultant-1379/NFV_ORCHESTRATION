package com.ericsson.irp.AlarmIRPConstDefs;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPConstDefs.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPConstDefs::AttributeValueChangeType
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPConstDefs/AttributeValueChangeType:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * struct AttributeValueChangeType {
  ...
};
 * </pre>
 */
public final class AttributeValueChangeTypeHelper {
  private static org.omg.CORBA.TypeCode _type;
  private static boolean _initializing;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static com.ericsson.irp.AlarmIRPConstDefs.AttributeValueChangeType read (final org.omg.CORBA.portable.InputStream _input) {
    final com.ericsson.irp.AlarmIRPConstDefs.AttributeValueChangeType _result = new com.ericsson.irp.AlarmIRPConstDefs.AttributeValueChangeType();
    _result.attributeName = _input.read_string();
    _result.oldValue = _input.read_any();
    _result.newValue = _input.read_any();
    return _result;
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final com.ericsson.irp.AlarmIRPConstDefs.AttributeValueChangeType _vis_value) {
    _output.write_string((java.lang.String)_vis_value.attributeName);
    _output.write_any((org.omg.CORBA.Any)_vis_value.oldValue);
    _output.write_any((org.omg.CORBA.Any)_vis_value.newValue);
  }

  public static void insert (final org.omg.CORBA.Any any, final com.ericsson.irp.AlarmIRPConstDefs.AttributeValueChangeType _vis_value) {
    any.insert_Streamable(new com.ericsson.irp.AlarmIRPConstDefs.AttributeValueChangeTypeHolder(_vis_value));
  }

  public static com.ericsson.irp.AlarmIRPConstDefs.AttributeValueChangeType extract (final org.omg.CORBA.Any any) {
    com.ericsson.irp.AlarmIRPConstDefs.AttributeValueChangeType _vis_value;
    _vis_value = com.ericsson.irp.AlarmIRPConstDefs.AttributeValueChangeTypeHelper.read(any.create_input_stream());
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
          final org.omg.CORBA.StructMember[] members = new org.omg.CORBA.StructMember[3];
          members[0] = new org.omg.CORBA.StructMember("attributeName", _orb().get_primitive_tc(org.omg.CORBA.TCKind.tk_string), null);
          members[1] = new org.omg.CORBA.StructMember("oldValue", _orb().get_primitive_tc(org.omg.CORBA.TCKind.tk_any), null);
          members[2] = new org.omg.CORBA.StructMember("newValue", _orb().get_primitive_tc(org.omg.CORBA.TCKind.tk_any), null);
          _type = _orb().create_struct_tc(id(), "AttributeValueChangeType", members);
          _initializing = false;
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:3gppsa5.org/AlarmIRPConstDefs/AttributeValueChangeType:1.0";
  }
}
