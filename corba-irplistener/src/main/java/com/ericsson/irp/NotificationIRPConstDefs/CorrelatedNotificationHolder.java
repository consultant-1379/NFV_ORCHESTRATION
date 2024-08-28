package com.ericsson.irp.NotificationIRPConstDefs;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPConstDefs.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPConstDefs::CorrelatedNotification
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPConstDefs/CorrelatedNotification:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * struct CorrelatedNotification {
  ...
};
 * </pre>
 */
public final class CorrelatedNotificationHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.NotificationIRPConstDefs.CorrelatedNotification value;

public CorrelatedNotificationHolder () {
}

public CorrelatedNotificationHolder (final com.ericsson.irp.NotificationIRPConstDefs.CorrelatedNotification _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.NotificationIRPConstDefs.CorrelatedNotificationHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.NotificationIRPConstDefs.CorrelatedNotificationHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.NotificationIRPConstDefs.CorrelatedNotificationHelper.type();
}
}
