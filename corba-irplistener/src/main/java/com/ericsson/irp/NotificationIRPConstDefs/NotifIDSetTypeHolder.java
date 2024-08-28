package com.ericsson.irp.NotificationIRPConstDefs;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPConstDefs.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPConstDefs::NotifIDSetType
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPConstDefs/NotifIDSetType:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * typedef sequence&ltlong&gt NotifIDSetType;
 * </pre>
 */
public final class NotifIDSetTypeHolder implements org.omg.CORBA.portable.Streamable {
public int[] value;

public NotifIDSetTypeHolder () {
}

public NotifIDSetTypeHolder (final int[] _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.NotificationIRPConstDefs.NotifIDSetTypeHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.NotificationIRPConstDefs.NotifIDSetTypeHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.NotificationIRPConstDefs.NotifIDSetTypeHelper.type();
}
}
