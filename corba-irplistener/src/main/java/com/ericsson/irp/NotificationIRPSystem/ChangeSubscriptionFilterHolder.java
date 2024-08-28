package com.ericsson.irp.NotificationIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPSystem::ChangeSubscriptionFilter
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPSystem/ChangeSubscriptionFilter:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception ChangeSubscriptionFilter {
  ...
};
 * </pre>
 */
public final class ChangeSubscriptionFilterHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.NotificationIRPSystem.ChangeSubscriptionFilter value;

public ChangeSubscriptionFilterHolder () {
}

public ChangeSubscriptionFilterHolder (final com.ericsson.irp.NotificationIRPSystem.ChangeSubscriptionFilter _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.NotificationIRPSystem.ChangeSubscriptionFilterHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.NotificationIRPSystem.ChangeSubscriptionFilterHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.NotificationIRPSystem.ChangeSubscriptionFilterHelper.type();
}
}
