package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::ConstraintInfoSeq
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/ConstraintInfoSeq:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * typedef sequence&ltcom.ericsson.irp.CosNotifyFilter.ConstraintInfo&gt ConstraintInfoSeq;
 * </pre>
 */
public final class ConstraintInfoSeqHelper {
  private static org.omg.CORBA.TypeCode _type;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static com.ericsson.irp.CosNotifyFilter.ConstraintInfo[] read (final org.omg.CORBA.portable.InputStream _input) {
    com.ericsson.irp.CosNotifyFilter.ConstraintInfo[] result;
    final int $length4 = _input.read_long();
    if($length4 != 0) {
    result = new com.ericsson.irp.CosNotifyFilter.ConstraintInfo[$length4];
    for (int $counter5 = 0; $counter5 < $length4; $counter5++) {
    result[$counter5] = com.ericsson.irp.CosNotifyFilter.ConstraintInfoHelper.read(_input);
    }
    }
    else {
    result = com.ericsson.irp.CosNotifyFilter.ConstraintInfoSeqHelper.EMPTY_LIST;
    }
    return result;
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final com.ericsson.irp.CosNotifyFilter.ConstraintInfo[] _vis_value) {
    if(_vis_value == null){
      throw new org.omg.CORBA.BAD_PARAM("Null parameter");
    }
    _output.write_long(_vis_value.length);
    for (int $counter6 = 0;  $counter6 < _vis_value.length; $counter6++) {
    if (_vis_value[$counter6] == null) {
      throw new org.omg.CORBA.BAD_PARAM("Invalid array length");
    }
    com.ericsson.irp.CosNotifyFilter.ConstraintInfoHelper.write(_output, _vis_value[$counter6]);
    }
  }

  public static void insert (final org.omg.CORBA.Any any, final com.ericsson.irp.CosNotifyFilter.ConstraintInfo[] _vis_value) {
    any.type(com.ericsson.irp.CosNotifyFilter.ConstraintInfoSeqHelper.type());
    any.insert_Streamable(new com.ericsson.irp.CosNotifyFilter.ConstraintInfoSeqHolder(_vis_value));
  }

  public static com.ericsson.irp.CosNotifyFilter.ConstraintInfo[] extract (final org.omg.CORBA.Any any) {
    com.ericsson.irp.CosNotifyFilter.ConstraintInfo[] _vis_value;
    _vis_value = com.ericsson.irp.CosNotifyFilter.ConstraintInfoSeqHelper.read(any.create_input_stream());
    return _vis_value;
  }

  public static org.omg.CORBA.TypeCode type () {
    if (_type == null) {
      synchronized (org.omg.CORBA.TypeCode.class) {
        if (_type == null) {
          org.omg.CORBA.TypeCode originalType = _orb().create_sequence_tc(0, com.ericsson.irp.CosNotifyFilter.ConstraintInfoHelper.type());
          _type = _orb().create_alias_tc(id(), "ConstraintInfoSeq", originalType);
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:omg.org/CosNotifyFilter/ConstraintInfoSeq:1.0";
  }
  public final static com.ericsson.irp.CosNotifyFilter.ConstraintInfo[] EMPTY_LIST = {};
}
