package com.ericsson.irp.AlarmIRPConstDefs;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPConstDefs.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPConstDefs::AttributeSetType
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPConstDefs/AttributeSetType:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * typedef sequence&ltcom.ericsson.irp.AlarmIRPConstDefs.AttributeValueType&gt AttributeSetType;
 * </pre>
 */
public final class AttributeSetTypeHelper {
  private static org.omg.CORBA.TypeCode _type;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static com.ericsson.irp.AlarmIRPConstDefs.AttributeValueType[] read (final org.omg.CORBA.portable.InputStream _input) {
    com.ericsson.irp.AlarmIRPConstDefs.AttributeValueType[] result;
    final int $length3 = _input.read_long();
    if($length3 != 0) {
    result = new com.ericsson.irp.AlarmIRPConstDefs.AttributeValueType[$length3];
    for (int $counter4 = 0; $counter4 < $length3; $counter4++) {
    result[$counter4] = com.ericsson.irp.AlarmIRPConstDefs.AttributeValueTypeHelper.read(_input);
    }
    }
    else {
    result = com.ericsson.irp.AlarmIRPConstDefs.AttributeSetTypeHelper.EMPTY_LIST;
    }
    return result;
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final com.ericsson.irp.AlarmIRPConstDefs.AttributeValueType[] _vis_value) {
    if(_vis_value == null){
      throw new org.omg.CORBA.BAD_PARAM("Null parameter");
    }
    _output.write_long(_vis_value.length);
    for (int $counter5 = 0;  $counter5 < _vis_value.length; $counter5++) {
    if (_vis_value[$counter5] == null) {
      throw new org.omg.CORBA.BAD_PARAM("Invalid array length");
    }
    com.ericsson.irp.AlarmIRPConstDefs.AttributeValueTypeHelper.write(_output, _vis_value[$counter5]);
    }
  }

  public static void insert (final org.omg.CORBA.Any any, final com.ericsson.irp.AlarmIRPConstDefs.AttributeValueType[] _vis_value) {
    any.type(com.ericsson.irp.AlarmIRPConstDefs.AttributeSetTypeHelper.type());
    any.insert_Streamable(new com.ericsson.irp.AlarmIRPConstDefs.AttributeSetTypeHolder(_vis_value));
  }

  public static com.ericsson.irp.AlarmIRPConstDefs.AttributeValueType[] extract (final org.omg.CORBA.Any any) {
    com.ericsson.irp.AlarmIRPConstDefs.AttributeValueType[] _vis_value;
    _vis_value = com.ericsson.irp.AlarmIRPConstDefs.AttributeSetTypeHelper.read(any.create_input_stream());
    return _vis_value;
  }

  public static org.omg.CORBA.TypeCode type () {
    if (_type == null) {
      synchronized (org.omg.CORBA.TypeCode.class) {
        if (_type == null) {
          org.omg.CORBA.TypeCode originalType = _orb().create_sequence_tc(0, com.ericsson.irp.AlarmIRPConstDefs.AttributeValueTypeHelper.type());
          _type = _orb().create_alias_tc(id(), "AttributeSetType", originalType);
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:3gppsa5.org/AlarmIRPConstDefs/AttributeSetType:1.0";
  }
  public final static com.ericsson.irp.AlarmIRPConstDefs.AttributeValueType[] EMPTY_LIST = {};
}
