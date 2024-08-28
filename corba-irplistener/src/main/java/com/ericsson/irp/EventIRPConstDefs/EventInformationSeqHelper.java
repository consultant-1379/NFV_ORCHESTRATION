package com.ericsson.irp.EventIRPConstDefs;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "EventIRPConstDefs.idl"
 * <li> <b>IDL Name</b>      ::EventIRPConstDefs::EventInformationSeq
 * <li> <b>Repository Id</b> IDL:EventIRPConstDefs/EventInformationSeq:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * typedef org.omg.CosNotification.EventBatch EventInformationSeq;
 * </pre>
 */
public final class EventInformationSeqHelper {
  private static org.omg.CORBA.TypeCode _type;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static org.omg.CosNotification.StructuredEvent[] read (final org.omg.CORBA.portable.InputStream _input) {
    org.omg.CosNotification.StructuredEvent[] result;
    result = org.omg.CosNotification.EventBatchHelper.read(_input);
    return result;
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final org.omg.CosNotification.StructuredEvent[] _vis_value) {
    org.omg.CosNotification.EventBatchHelper.write(_output, _vis_value);
  }

  public static void insert (final org.omg.CORBA.Any any, final org.omg.CosNotification.StructuredEvent[] _vis_value) {
    any.type(com.ericsson.irp.EventIRPConstDefs.EventInformationSeqHelper.type());
    any.type(org.omg.CosNotification.EventBatchHelper.type());
    any.insert_Streamable(new org.omg.CosNotification.EventBatchHolder(_vis_value));
  }

  public static org.omg.CosNotification.StructuredEvent[] extract (final org.omg.CORBA.Any any) {
    org.omg.CosNotification.StructuredEvent[] _vis_value;
    _vis_value = org.omg.CosNotification.EventBatchHelper.read(any.create_input_stream());
    return _vis_value;
  }

  public static org.omg.CORBA.TypeCode type () {
    if (_type == null) {
      synchronized (org.omg.CORBA.TypeCode.class) {
        if (_type == null) {
          org.omg.CORBA.TypeCode originalType = org.omg.CosNotification.EventBatchHelper.type();
          _type = _orb().create_alias_tc(id(), "EventInformationSeq", originalType);
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:EventIRPConstDefs/EventInformationSeq:1.0";
  }
  public final static org.omg.CosNotification.StructuredEvent[] EMPTY_LIST = {};
}
