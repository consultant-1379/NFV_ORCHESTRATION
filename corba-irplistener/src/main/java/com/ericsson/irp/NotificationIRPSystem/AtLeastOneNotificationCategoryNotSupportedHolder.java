package com.ericsson.irp.NotificationIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPSystem::AtLeastOneNotificationCategoryNotSupported
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPSystem/AtLeastOneNotificationCategoryNotSupported:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception AtLeastOneNotificationCategoryNotSupported {
  ...
};
 * </pre>
 */
public final class AtLeastOneNotificationCategoryNotSupportedHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.NotificationIRPSystem.AtLeastOneNotificationCategoryNotSupported value;

public AtLeastOneNotificationCategoryNotSupportedHolder () {
}

public AtLeastOneNotificationCategoryNotSupportedHolder (final com.ericsson.irp.NotificationIRPSystem.AtLeastOneNotificationCategoryNotSupported _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.NotificationIRPSystem.AtLeastOneNotificationCategoryNotSupportedHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.NotificationIRPSystem.AtLeastOneNotificationCategoryNotSupportedHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.NotificationIRPSystem.AtLeastOneNotificationCategoryNotSupportedHelper.type();
}
}
