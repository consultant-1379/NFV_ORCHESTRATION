package com.ericsson.irp.NotificationIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPSystem::GetSubscriptionStatus
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPSystem/GetSubscriptionStatus:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception GetSubscriptionStatus {
  ...
};
 * </pre>
 */
public final class GetSubscriptionStatusHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.NotificationIRPSystem.GetSubscriptionStatus value;

public GetSubscriptionStatusHolder () {
}

public GetSubscriptionStatusHolder (final com.ericsson.irp.NotificationIRPSystem.GetSubscriptionStatus _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.NotificationIRPSystem.GetSubscriptionStatusHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.NotificationIRPSystem.GetSubscriptionStatusHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.NotificationIRPSystem.GetSubscriptionStatusHelper.type();
}
}
