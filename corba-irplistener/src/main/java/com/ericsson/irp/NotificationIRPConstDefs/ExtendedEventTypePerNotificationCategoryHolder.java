package com.ericsson.irp.NotificationIRPConstDefs;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPConstDefs.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPConstDefs::ExtendedEventTypePerNotificationCategory
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPConstDefs/ExtendedEventTypePerNotificationCategory:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * typedef sequence&ltstring&gt ExtendedEventTypePerNotificationCategory;
 * </pre>
 */
public final class ExtendedEventTypePerNotificationCategoryHolder implements org.omg.CORBA.portable.Streamable {
public java.lang.String[] value;

public ExtendedEventTypePerNotificationCategoryHolder () {
}

public ExtendedEventTypePerNotificationCategoryHolder (final java.lang.String[] _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.NotificationIRPConstDefs.ExtendedEventTypePerNotificationCategoryHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.NotificationIRPConstDefs.ExtendedEventTypePerNotificationCategoryHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.NotificationIRPConstDefs.ExtendedEventTypePerNotificationCategoryHelper.type();
}
}
