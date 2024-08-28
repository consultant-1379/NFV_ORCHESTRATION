package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::MappingConstraintPairSeq
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/MappingConstraintPairSeq:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * typedef sequence&ltcom.ericsson.irp.CosNotifyFilter.MappingConstraintPair&gt MappingConstraintPairSeq;
 * </pre>
 */
public final class MappingConstraintPairSeqHelper {
  private static org.omg.CORBA.TypeCode _type;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static com.ericsson.irp.CosNotifyFilter.MappingConstraintPair[] read (final org.omg.CORBA.portable.InputStream _input) {
    com.ericsson.irp.CosNotifyFilter.MappingConstraintPair[] result;
    final int $length7 = _input.read_long();
    if($length7 != 0) {
    result = new com.ericsson.irp.CosNotifyFilter.MappingConstraintPair[$length7];
    for (int $counter8 = 0; $counter8 < $length7; $counter8++) {
    result[$counter8] = com.ericsson.irp.CosNotifyFilter.MappingConstraintPairHelper.read(_input);
    }
    }
    else {
    result = com.ericsson.irp.CosNotifyFilter.MappingConstraintPairSeqHelper.EMPTY_LIST;
    }
    return result;
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final com.ericsson.irp.CosNotifyFilter.MappingConstraintPair[] _vis_value) {
    if(_vis_value == null){
      throw new org.omg.CORBA.BAD_PARAM("Null parameter");
    }
    _output.write_long(_vis_value.length);
    for (int $counter9 = 0;  $counter9 < _vis_value.length; $counter9++) {
    if (_vis_value[$counter9] == null) {
      throw new org.omg.CORBA.BAD_PARAM("Invalid array length");
    }
    com.ericsson.irp.CosNotifyFilter.MappingConstraintPairHelper.write(_output, _vis_value[$counter9]);
    }
  }

  public static void insert (final org.omg.CORBA.Any any, final com.ericsson.irp.CosNotifyFilter.MappingConstraintPair[] _vis_value) {
    any.type(com.ericsson.irp.CosNotifyFilter.MappingConstraintPairSeqHelper.type());
    any.insert_Streamable(new com.ericsson.irp.CosNotifyFilter.MappingConstraintPairSeqHolder(_vis_value));
  }

  public static com.ericsson.irp.CosNotifyFilter.MappingConstraintPair[] extract (final org.omg.CORBA.Any any) {
    com.ericsson.irp.CosNotifyFilter.MappingConstraintPair[] _vis_value;
    _vis_value = com.ericsson.irp.CosNotifyFilter.MappingConstraintPairSeqHelper.read(any.create_input_stream());
    return _vis_value;
  }

  public static org.omg.CORBA.TypeCode type () {
    if (_type == null) {
      synchronized (org.omg.CORBA.TypeCode.class) {
        if (_type == null) {
          org.omg.CORBA.TypeCode originalType = _orb().create_sequence_tc(0, com.ericsson.irp.CosNotifyFilter.MappingConstraintPairHelper.type());
          _type = _orb().create_alias_tc(id(), "MappingConstraintPairSeq", originalType);
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:omg.org/CosNotifyFilter/MappingConstraintPairSeq:1.0";
  }
  public final static com.ericsson.irp.CosNotifyFilter.MappingConstraintPair[] EMPTY_LIST = {};
}
