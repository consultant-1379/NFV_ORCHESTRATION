package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::CallbackIDSeq
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/CallbackIDSeq:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * typedef sequence&ltcom.ericsson.irp.CosNotifyFilter.CallbackID&gt CallbackIDSeq;
 * </pre>
 */
public final class CallbackIDSeqHelper {
  private static org.omg.CORBA.TypeCode _type;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static int[] read (final org.omg.CORBA.portable.InputStream _input) {
    int[] result;
    final int $length13 = _input.read_long();
    if($length13 != 0) {
    result = new int[$length13];
    _input.read_long_array(result, 0, $length13);
    }
    else {
    result = com.ericsson.irp.CosNotifyFilter.CallbackIDSeqHelper.EMPTY_LIST;
    }
    return result;
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final int[] _vis_value) {
    if(_vis_value == null){
      throw new org.omg.CORBA.BAD_PARAM("Null parameter");
    }
    _output.write_long(_vis_value.length);
    _output.write_long_array(_vis_value, 0, _vis_value.length);
  }

  public static void insert (final org.omg.CORBA.Any any, final int[] _vis_value) {
    any.type(com.ericsson.irp.CosNotifyFilter.CallbackIDSeqHelper.type());
    any.insert_Streamable(new com.ericsson.irp.CosNotifyFilter.CallbackIDSeqHolder(_vis_value));
  }

  public static int[] extract (final org.omg.CORBA.Any any) {
    int[] _vis_value;
    _vis_value = com.ericsson.irp.CosNotifyFilter.CallbackIDSeqHelper.read(any.create_input_stream());
    return _vis_value;
  }

  public static org.omg.CORBA.TypeCode type () {
    if (_type == null) {
      synchronized (org.omg.CORBA.TypeCode.class) {
        if (_type == null) {
          org.omg.CORBA.TypeCode originalType = _orb().create_sequence_tc(0, com.ericsson.irp.CosNotifyFilter.CallbackIDHelper.type());
          _type = _orb().create_alias_tc(id(), "CallbackIDSeq", originalType);
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:omg.org/CosNotifyFilter/CallbackIDSeq:1.0";
  }
  public final static int[] EMPTY_LIST = {};
}
