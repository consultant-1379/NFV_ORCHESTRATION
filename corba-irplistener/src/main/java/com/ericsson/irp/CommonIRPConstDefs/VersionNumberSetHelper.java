package com.ericsson.irp.CommonIRPConstDefs;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CommonIRPConstDefs.idl"
 * <li> <b>IDL Name</b>      ::CommonIRPConstDefs::VersionNumberSet
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/CommonIRPConstDefs/VersionNumberSet:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * typedef sequence&ltstring&gt VersionNumberSet;
 * </pre>
 */
public final class VersionNumberSetHelper {
  private static org.omg.CORBA.TypeCode _type;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static java.lang.String[] read (final org.omg.CORBA.portable.InputStream _input) {
    java.lang.String[] result;
    final int $length0 = _input.read_long();
    if($length0 != 0) {
    result = new java.lang.String[$length0];
    for (int $counter1 = 0; $counter1 < $length0; $counter1++) {
    result[$counter1] = _input.read_string();
    }
    }
    else {
    result = com.ericsson.irp.CommonIRPConstDefs.VersionNumberSetHelper.EMPTY_LIST;
    }
    return result;
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final java.lang.String[] _vis_value) {
    if(_vis_value == null){
      throw new org.omg.CORBA.BAD_PARAM("Null parameter");
    }
    _output.write_long(_vis_value.length);
    for (int $counter2 = 0;  $counter2 < _vis_value.length; $counter2++) {
    _output.write_string((java.lang.String)_vis_value[$counter2]);
    }
  }

  public static void insert (final org.omg.CORBA.Any any, final java.lang.String[] _vis_value) {
    any.type(com.ericsson.irp.CommonIRPConstDefs.VersionNumberSetHelper.type());
    any.insert_Streamable(new com.ericsson.irp.CommonIRPConstDefs.VersionNumberSetHolder(_vis_value));
  }

  public static java.lang.String[] extract (final org.omg.CORBA.Any any) {
    java.lang.String[] _vis_value;
    _vis_value = com.ericsson.irp.CommonIRPConstDefs.VersionNumberSetHelper.read(any.create_input_stream());
    return _vis_value;
  }

  public static org.omg.CORBA.TypeCode type () {
    if (_type == null) {
      synchronized (org.omg.CORBA.TypeCode.class) {
        if (_type == null) {
          org.omg.CORBA.TypeCode originalType = _orb().create_sequence_tc(0, _orb().get_primitive_tc(org.omg.CORBA.TCKind.tk_string));
          _type = _orb().create_alias_tc(id(), "VersionNumberSet", originalType);
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:3gppsa5.org/CommonIRPConstDefs/VersionNumberSet:1.0";
  }
  public final static java.lang.String[] EMPTY_LIST = {};
}
