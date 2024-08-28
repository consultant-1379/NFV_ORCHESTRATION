package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::UnsupportedFilterableData
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/UnsupportedFilterableData:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception UnsupportedFilterableData {
  ...
};
 * </pre>
 */
public final class UnsupportedFilterableDataHelper {
  private static org.omg.CORBA.TypeCode _type;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData read (final org.omg.CORBA.portable.InputStream _input) {
    if (!_input.read_string().equals(id())) {
      throw new org.omg.CORBA.MARSHAL("Mismatched repository id");
    }
    final com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData _result = new com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData();
    return _result;
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData _vis_value) {
    _output.write_string(id());
  }

  public static void insert (final org.omg.CORBA.Any any, final com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData _vis_value) {
    any.insert_Streamable(new com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableDataHolder(_vis_value));
  }

  public static com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData extract (final org.omg.CORBA.Any any) {
    com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData _vis_value;
    _vis_value = com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableDataHelper.read(any.create_input_stream());
    return _vis_value;
  }

  public static org.omg.CORBA.TypeCode type () {
    if (_type == null) {
      synchronized (org.omg.CORBA.TypeCode.class) {
        if (_type == null) {
          final org.omg.CORBA.StructMember[] members = new org.omg.CORBA.StructMember[0];
          
          _type = _orb().create_exception_tc(id(), "UnsupportedFilterableData", members);
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:omg.org/CosNotifyFilter/UnsupportedFilterableData:1.0";
  }
}
