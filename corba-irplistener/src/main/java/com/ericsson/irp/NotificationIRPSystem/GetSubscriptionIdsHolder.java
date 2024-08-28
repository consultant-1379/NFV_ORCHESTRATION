package com.ericsson.irp.NotificationIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPSystem::GetSubscriptionIds
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPSystem/GetSubscriptionIds:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception GetSubscriptionIds {
  ...
};
 * </pre>
 */
public final class GetSubscriptionIdsHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.NotificationIRPSystem.GetSubscriptionIds value;

public GetSubscriptionIdsHolder () {
}

public GetSubscriptionIdsHolder (final com.ericsson.irp.NotificationIRPSystem.GetSubscriptionIds _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.NotificationIRPSystem.GetSubscriptionIdsHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.NotificationIRPSystem.GetSubscriptionIdsHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.NotificationIRPSystem.GetSubscriptionIdsHelper.type();
}
}
