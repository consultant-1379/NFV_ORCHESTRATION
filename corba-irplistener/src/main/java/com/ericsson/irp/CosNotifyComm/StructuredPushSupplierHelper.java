
package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::StructuredPushSupplier
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/StructuredPushSupplier:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface StructuredPushSupplier : com.ericsson.irp.CosNotifyComm.NotifySubscribe {
  ...
};
 * </pre>
 */
public final class StructuredPushSupplierHelper {
  public static com.ericsson.irp.CosNotifyComm.StructuredPushSupplier narrow (final org.omg.CORBA.Object obj) {
    return narrow(obj, false);
  }

  public static com.ericsson.irp.CosNotifyComm.StructuredPushSupplier unchecked_narrow (org.omg.CORBA.Object obj) {
    return narrow(obj, true);
  }

  private static com.ericsson.irp.CosNotifyComm.StructuredPushSupplier narrow (final org.omg.CORBA.Object obj, final boolean is_a) {
    if (obj == null) {
      return null;
    }
    if (obj instanceof com.ericsson.irp.CosNotifyComm.StructuredPushSupplier) {
      return (com.ericsson.irp.CosNotifyComm.StructuredPushSupplier)obj;
    }
    if (is_a || obj._is_a(id())) {
      final org.omg.CORBA.portable.ObjectImpl _obj = (org.omg.CORBA.portable.ObjectImpl)obj;
      com.ericsson.irp.CosNotifyComm._StructuredPushSupplierStub result = new com.ericsson.irp.CosNotifyComm._StructuredPushSupplierStub();
      final org.omg.CORBA.portable.Delegate _delegate = _obj._get_delegate();
      result._set_delegate(_delegate);
      return result;
    }
    throw new org.omg.CORBA.BAD_PARAM();
  }

  public java.lang.Object read_Object (final org.omg.CORBA.portable.InputStream istream) {
    return read(istream);
  }

  public void write_Object (final org.omg.CORBA.portable.OutputStream ostream, final java.lang.Object obj) {
    if (!(obj instanceof com.ericsson.irp.CosNotifyComm.StructuredPushSupplier)) {
      throw new org.omg.CORBA.BAD_PARAM();
    }
    write(ostream, (com.ericsson.irp.CosNotifyComm.StructuredPushSupplier)obj);
  }

  public java.lang.String get_id () {
    return id();
  }

  public org.omg.CORBA.TypeCode get_type () {
    return type();
  }

  private static org.omg.CORBA.TypeCode _type;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static com.ericsson.irp.CosNotifyComm.StructuredPushSupplier read (final org.omg.CORBA.portable.InputStream _input) {
    return narrow(_input.read_Object(com.ericsson.irp.CosNotifyComm._StructuredPushSupplierStub.class), true);
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final com.ericsson.irp.CosNotifyComm.StructuredPushSupplier _vis_value) {
    if (_vis_value != null && !(_vis_value instanceof org.omg.CORBA.portable.ObjectImpl)) {
      throw new org.omg.CORBA.BAD_PARAM();
    }
    _output.write_Object((org.omg.CORBA.Object)_vis_value);
  }

  public static void insert (final org.omg.CORBA.Any any, final com.ericsson.irp.CosNotifyComm.StructuredPushSupplier _vis_value) {
    any.insert_Object((org.omg.CORBA.Object)_vis_value, com.ericsson.irp.CosNotifyComm.StructuredPushSupplierHelper.type());
  }

  public static com.ericsson.irp.CosNotifyComm.StructuredPushSupplier extract (final org.omg.CORBA.Any any) {
    com.ericsson.irp.CosNotifyComm.StructuredPushSupplier _vis_value;
    final org.omg.CORBA.Object _obj = any.extract_Object();
    _vis_value = com.ericsson.irp.CosNotifyComm.StructuredPushSupplierHelper.narrow(_obj);
    return _vis_value;
  }

  public static org.omg.CORBA.TypeCode type () {
    if (_type == null) {
      synchronized (org.omg.CORBA.TypeCode.class) {
        if (_type == null) {
          _type = _orb().create_interface_tc(id(), "StructuredPushSupplier");
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:omg.org/CosNotifyComm/StructuredPushSupplier:1.0";
  }
}
