package com.ericsson.irp.AlarmIRPConstDefs;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPConstDefs.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPConstDefs::AttributeChangeSetType
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPConstDefs/AttributeChangeSetType:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * typedef sequence&ltcom.ericsson.irp.AlarmIRPConstDefs.AttributeValueChangeType&gt AttributeChangeSetType;
 * </pre>
 */
public final class AttributeChangeSetTypeHelper {
  private static org.omg.CORBA.TypeCode _type;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static com.ericsson.irp.AlarmIRPConstDefs.AttributeValueChangeType[] read (final org.omg.CORBA.portable.InputStream _input) {
    com.ericsson.irp.AlarmIRPConstDefs.AttributeValueChangeType[] result;
    final int $length0 = _input.read_long();
    if($length0 != 0) {
    result = new com.ericsson.irp.AlarmIRPConstDefs.AttributeValueChangeType[$length0];
    for (int $counter1 = 0; $counter1 < $length0; $counter1++) {
    result[$counter1] = com.ericsson.irp.AlarmIRPConstDefs.AttributeValueChangeTypeHelper.read(_input);
    }
    }
    else {
    result = com.ericsson.irp.AlarmIRPConstDefs.AttributeChangeSetTypeHelper.EMPTY_LIST;
    }
    return result;
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final com.ericsson.irp.AlarmIRPConstDefs.AttributeValueChangeType[] _vis_value) {
    if(_vis_value == null){
      throw new org.omg.CORBA.BAD_PARAM("Null parameter");
    }
    _output.write_long(_vis_value.length);
    for (int $counter2 = 0;  $counter2 < _vis_value.length; $counter2++) {
    if (_vis_value[$counter2] == null) {
      throw new org.omg.CORBA.BAD_PARAM("Invalid array length");
    }
    com.ericsson.irp.AlarmIRPConstDefs.AttributeValueChangeTypeHelper.write(_output, _vis_value[$counter2]);
    }
  }

  public static void insert (final org.omg.CORBA.Any any, final com.ericsson.irp.AlarmIRPConstDefs.AttributeValueChangeType[] _vis_value) {
    any.type(com.ericsson.irp.AlarmIRPConstDefs.AttributeChangeSetTypeHelper.type());
    any.insert_Streamable(new com.ericsson.irp.AlarmIRPConstDefs.AttributeChangeSetTypeHolder(_vis_value));
  }

  public static com.ericsson.irp.AlarmIRPConstDefs.AttributeValueChangeType[] extract (final org.omg.CORBA.Any any) {
    com.ericsson.irp.AlarmIRPConstDefs.AttributeValueChangeType[] _vis_value;
    _vis_value = com.ericsson.irp.AlarmIRPConstDefs.AttributeChangeSetTypeHelper.read(any.create_input_stream());
    return _vis_value;
  }

  public static org.omg.CORBA.TypeCode type () {
    if (_type == null) {
      synchronized (org.omg.CORBA.TypeCode.class) {
        if (_type == null) {
          org.omg.CORBA.TypeCode originalType = _orb().create_sequence_tc(0, com.ericsson.irp.AlarmIRPConstDefs.AttributeValueChangeTypeHelper.type());
          _type = _orb().create_alias_tc(id(), "AttributeChangeSetType", originalType);
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:3gppsa5.org/AlarmIRPConstDefs/AttributeChangeSetType:1.0";
  }
  public final static com.ericsson.irp.AlarmIRPConstDefs.AttributeValueChangeType[] EMPTY_LIST = {};
}
