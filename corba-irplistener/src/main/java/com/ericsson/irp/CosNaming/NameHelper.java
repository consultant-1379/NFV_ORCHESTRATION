package com.ericsson.irp.CosNaming;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNaming.idl"
 * <li> <b>IDL Name</b>      ::CosNaming::Name
 * <li> <b>Repository Id</b> IDL:omg.org/CosNaming/Name:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * typedef sequence&ltcom.ericsson.irp.CosNaming.NameComponent&gt Name;
 * </pre>
 */
public final class NameHelper {
  private static org.omg.CORBA.TypeCode _type;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static com.ericsson.irp.CosNaming.NameComponent[] read (final org.omg.CORBA.portable.InputStream _input) {
    com.ericsson.irp.CosNaming.NameComponent[] result;
    final int $length0 = _input.read_long();
    if($length0 != 0) {
    result = new com.ericsson.irp.CosNaming.NameComponent[$length0];
    for (int $counter1 = 0; $counter1 < $length0; $counter1++) {
    result[$counter1] = com.ericsson.irp.CosNaming.NameComponentHelper.read(_input);
    }
    }
    else {
    result = com.ericsson.irp.CosNaming.NameHelper.EMPTY_LIST;
    }
    return result;
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final com.ericsson.irp.CosNaming.NameComponent[] _vis_value) {
    if(_vis_value == null){
      throw new org.omg.CORBA.BAD_PARAM("Null parameter");
    }
    _output.write_long(_vis_value.length);
    for (int $counter2 = 0;  $counter2 < _vis_value.length; $counter2++) {
    if (_vis_value[$counter2] == null) {
      throw new org.omg.CORBA.BAD_PARAM("Invalid array length");
    }
    com.ericsson.irp.CosNaming.NameComponentHelper.write(_output, _vis_value[$counter2]);
    }
  }

  public static void insert (final org.omg.CORBA.Any any, final com.ericsson.irp.CosNaming.NameComponent[] _vis_value) {
    any.type(com.ericsson.irp.CosNaming.NameHelper.type());
    any.insert_Streamable(new com.ericsson.irp.CosNaming.NameHolder(_vis_value));
  }

  public static com.ericsson.irp.CosNaming.NameComponent[] extract (final org.omg.CORBA.Any any) {
    com.ericsson.irp.CosNaming.NameComponent[] _vis_value;
    _vis_value = com.ericsson.irp.CosNaming.NameHelper.read(any.create_input_stream());
    return _vis_value;
  }

  public static org.omg.CORBA.TypeCode type () {
    if (_type == null) {
      synchronized (org.omg.CORBA.TypeCode.class) {
        if (_type == null) {
          org.omg.CORBA.TypeCode originalType = _orb().create_sequence_tc(0, com.ericsson.irp.CosNaming.NameComponentHelper.type());
          _type = _orb().create_alias_tc(id(), "Name", originalType);
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:omg.org/CosNaming/Name:1.0";
  }
  public final static com.ericsson.irp.CosNaming.NameComponent[] EMPTY_LIST = {};
}
