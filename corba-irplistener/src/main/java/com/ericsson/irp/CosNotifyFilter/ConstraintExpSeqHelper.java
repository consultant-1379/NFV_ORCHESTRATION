package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::ConstraintExpSeq
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/ConstraintExpSeq:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * typedef sequence&ltcom.ericsson.irp.CosNotifyFilter.ConstraintExp&gt ConstraintExpSeq;
 * </pre>
 */
public final class ConstraintExpSeqHelper {
  private static org.omg.CORBA.TypeCode _type;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static com.ericsson.irp.CosNotifyFilter.ConstraintExp[] read (final org.omg.CORBA.portable.InputStream _input) {
    com.ericsson.irp.CosNotifyFilter.ConstraintExp[] result;
    final int $length1 = _input.read_long();
    if($length1 != 0) {
    result = new com.ericsson.irp.CosNotifyFilter.ConstraintExp[$length1];
    for (int $counter2 = 0; $counter2 < $length1; $counter2++) {
    result[$counter2] = com.ericsson.irp.CosNotifyFilter.ConstraintExpHelper.read(_input);
    }
    }
    else {
    result = com.ericsson.irp.CosNotifyFilter.ConstraintExpSeqHelper.EMPTY_LIST;
    }
    return result;
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final com.ericsson.irp.CosNotifyFilter.ConstraintExp[] _vis_value) {
    if(_vis_value == null){
      throw new org.omg.CORBA.BAD_PARAM("Null parameter");
    }
    _output.write_long(_vis_value.length);
    for (int $counter3 = 0;  $counter3 < _vis_value.length; $counter3++) {
    if (_vis_value[$counter3] == null) {
      throw new org.omg.CORBA.BAD_PARAM("Invalid array length");
    }
    com.ericsson.irp.CosNotifyFilter.ConstraintExpHelper.write(_output, _vis_value[$counter3]);
    }
  }

  public static void insert (final org.omg.CORBA.Any any, final com.ericsson.irp.CosNotifyFilter.ConstraintExp[] _vis_value) {
    any.type(com.ericsson.irp.CosNotifyFilter.ConstraintExpSeqHelper.type());
    any.insert_Streamable(new com.ericsson.irp.CosNotifyFilter.ConstraintExpSeqHolder(_vis_value));
  }

  public static com.ericsson.irp.CosNotifyFilter.ConstraintExp[] extract (final org.omg.CORBA.Any any) {
    com.ericsson.irp.CosNotifyFilter.ConstraintExp[] _vis_value;
    _vis_value = com.ericsson.irp.CosNotifyFilter.ConstraintExpSeqHelper.read(any.create_input_stream());
    return _vis_value;
  }

  public static org.omg.CORBA.TypeCode type () {
    if (_type == null) {
      synchronized (org.omg.CORBA.TypeCode.class) {
        if (_type == null) {
          org.omg.CORBA.TypeCode originalType = _orb().create_sequence_tc(0, com.ericsson.irp.CosNotifyFilter.ConstraintExpHelper.type());
          _type = _orb().create_alias_tc(id(), "ConstraintExpSeq", originalType);
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:omg.org/CosNotifyFilter/ConstraintExpSeq:1.0";
  }
  public final static com.ericsson.irp.CosNotifyFilter.ConstraintExp[] EMPTY_LIST = {};
}
