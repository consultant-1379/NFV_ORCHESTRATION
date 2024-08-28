package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::InvalidEventType
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/InvalidEventType:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception InvalidEventType {
  ...
};
 * </pre>
 */
public final class InvalidEventTypeHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNotifyComm.InvalidEventType value;

public InvalidEventTypeHolder () {
}

public InvalidEventTypeHolder (final com.ericsson.irp.CosNotifyComm.InvalidEventType _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNotifyComm.InvalidEventTypeHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNotifyComm.InvalidEventTypeHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNotifyComm.InvalidEventTypeHelper.type();
}
}
