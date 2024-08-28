package com.ericsson.irp.NotificationIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPSystem::GetNotificationCategories
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPSystem/GetNotificationCategories:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception GetNotificationCategories {
  ...
};
 * </pre>
 */
public final class GetNotificationCategoriesHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.NotificationIRPSystem.GetNotificationCategories value;

public GetNotificationCategoriesHolder () {
}

public GetNotificationCategoriesHolder (final com.ericsson.irp.NotificationIRPSystem.GetNotificationCategories _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.NotificationIRPSystem.GetNotificationCategoriesHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.NotificationIRPSystem.GetNotificationCategoriesHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.NotificationIRPSystem.GetNotificationCategoriesHelper.type();
}
}
