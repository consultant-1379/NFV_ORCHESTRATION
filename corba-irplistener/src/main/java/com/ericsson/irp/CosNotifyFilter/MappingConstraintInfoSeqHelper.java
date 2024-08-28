package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::MappingConstraintInfoSeq
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/MappingConstraintInfoSeq:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * typedef sequence&ltcom.ericsson.irp.CosNotifyFilter.MappingConstraintInfo&gt MappingConstraintInfoSeq;
 * </pre>
 */
public final class MappingConstraintInfoSeqHelper {
  private static org.omg.CORBA.TypeCode _type;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static com.ericsson.irp.CosNotifyFilter.MappingConstraintInfo[] read (final org.omg.CORBA.portable.InputStream _input) {
    com.ericsson.irp.CosNotifyFilter.MappingConstraintInfo[] result;
    final int $length10 = _input.read_long();
    if($length10 != 0) {
    result = new com.ericsson.irp.CosNotifyFilter.MappingConstraintInfo[$length10];
    for (int $counter11 = 0; $counter11 < $length10; $counter11++) {
    result[$counter11] = com.ericsson.irp.CosNotifyFilter.MappingConstraintInfoHelper.read(_input);
    }
    }
    else {
    result = com.ericsson.irp.CosNotifyFilter.MappingConstraintInfoSeqHelper.EMPTY_LIST;
    }
    return result;
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final com.ericsson.irp.CosNotifyFilter.MappingConstraintInfo[] _vis_value) {
    if(_vis_value == null){
      throw new org.omg.CORBA.BAD_PARAM("Null parameter");
    }
    _output.write_long(_vis_value.length);
    for (int $counter12 = 0;  $counter12 < _vis_value.length; $counter12++) {
    if (_vis_value[$counter12] == null) {
      throw new org.omg.CORBA.BAD_PARAM("Invalid array length");
    }
    com.ericsson.irp.CosNotifyFilter.MappingConstraintInfoHelper.write(_output, _vis_value[$counter12]);
    }
  }

  public static void insert (final org.omg.CORBA.Any any, final com.ericsson.irp.CosNotifyFilter.MappingConstraintInfo[] _vis_value) {
    any.type(com.ericsson.irp.CosNotifyFilter.MappingConstraintInfoSeqHelper.type());
    any.insert_Streamable(new com.ericsson.irp.CosNotifyFilter.MappingConstraintInfoSeqHolder(_vis_value));
  }

  public static com.ericsson.irp.CosNotifyFilter.MappingConstraintInfo[] extract (final org.omg.CORBA.Any any) {
    com.ericsson.irp.CosNotifyFilter.MappingConstraintInfo[] _vis_value;
    _vis_value = com.ericsson.irp.CosNotifyFilter.MappingConstraintInfoSeqHelper.read(any.create_input_stream());
    return _vis_value;
  }

  public static org.omg.CORBA.TypeCode type () {
    if (_type == null) {
      synchronized (org.omg.CORBA.TypeCode.class) {
        if (_type == null) {
          org.omg.CORBA.TypeCode originalType = _orb().create_sequence_tc(0, com.ericsson.irp.CosNotifyFilter.MappingConstraintInfoHelper.type());
          _type = _orb().create_alias_tc(id(), "MappingConstraintInfoSeq", originalType);
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:omg.org/CosNotifyFilter/MappingConstraintInfoSeq:1.0";
  }
  public final static com.ericsson.irp.CosNotifyFilter.MappingConstraintInfo[] EMPTY_LIST = {};
}
