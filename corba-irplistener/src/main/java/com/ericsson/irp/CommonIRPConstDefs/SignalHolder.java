package com.ericsson.irp.CommonIRPConstDefs;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CommonIRPConstDefs.idl"
 * <li> <b>IDL Name</b>      ::CommonIRPConstDefs::Signal
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/CommonIRPConstDefs/Signal:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * enum Signal {OK, Failure, PartialFailure};
 * </pre>
 */
public final class SignalHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CommonIRPConstDefs.Signal value;

public SignalHolder () {
}

public SignalHolder (final com.ericsson.irp.CommonIRPConstDefs.Signal _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CommonIRPConstDefs.SignalHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CommonIRPConstDefs.SignalHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CommonIRPConstDefs.SignalHelper.type();
}
}
