package com.ericsson.irp.CommonIRPConstDefs;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CommonIRPConstDefs.idl"
 * <li> <b>IDL Name</b>      ::CommonIRPConstDefs::IRPTime
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/CommonIRPConstDefs/IRPTime:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * typedef org.omg.TimeBase.UtcT IRPTime;
 * </pre>
 */
public final class IRPTimeHelper {
  private static org.omg.CORBA.TypeCode _type;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static org.omg.TimeBase.UtcT read (final org.omg.CORBA.portable.InputStream _input) {
    org.omg.TimeBase.UtcT result;
    result = org.omg.TimeBase.UtcTHelper.read(_input);
    return result;
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final org.omg.TimeBase.UtcT _vis_value) {
    if (_vis_value == null) {
      throw new org.omg.CORBA.BAD_PARAM("Invalid array length");
    }
    org.omg.TimeBase.UtcTHelper.write(_output, _vis_value);
  }

  public static void insert (final org.omg.CORBA.Any any, final org.omg.TimeBase.UtcT _vis_value) {
    any.type(com.ericsson.irp.CommonIRPConstDefs.IRPTimeHelper.type());
    any.insert_Streamable(new org.omg.TimeBase.UtcTHolder(_vis_value));
  }

  public static org.omg.TimeBase.UtcT extract (final org.omg.CORBA.Any any) {
    org.omg.TimeBase.UtcT _vis_value;
    _vis_value = org.omg.TimeBase.UtcTHelper.read(any.create_input_stream());
    return _vis_value;
  }

  public static org.omg.CORBA.TypeCode type () {
    if (_type == null) {
      synchronized (org.omg.CORBA.TypeCode.class) {
        if (_type == null) {
          org.omg.CORBA.TypeCode originalType = org.omg.TimeBase.UtcTHelper.type();
          _type = _orb().create_alias_tc(id(), "IRPTime", originalType);
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:3gppsa5.org/CommonIRPConstDefs/IRPTime:1.0";
  }
}
