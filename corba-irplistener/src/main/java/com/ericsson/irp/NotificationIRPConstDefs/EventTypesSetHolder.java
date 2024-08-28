package com.ericsson.irp.NotificationIRPConstDefs;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPConstDefs.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPConstDefs::EventTypesSet
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPConstDefs/EventTypesSet:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * typedef sequence&ltcom.ericsson.irp.NotificationIRPConstDefs.EventTypesPerNotificationCategory&gt EventTypesSet;
 * </pre>
 */
public final class EventTypesSetHolder implements org.omg.CORBA.portable.Streamable {
public java.lang.String[][] value;

public EventTypesSetHolder () {
}

public EventTypesSetHolder (final java.lang.String[][] _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.NotificationIRPConstDefs.EventTypesSetHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.NotificationIRPConstDefs.EventTypesSetHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.NotificationIRPConstDefs.EventTypesSetHelper.type();
}
}
