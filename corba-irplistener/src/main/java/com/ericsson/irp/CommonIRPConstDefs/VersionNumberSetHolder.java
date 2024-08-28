package com.ericsson.irp.CommonIRPConstDefs;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CommonIRPConstDefs.idl"
 * <li> <b>IDL Name</b>      ::CommonIRPConstDefs::VersionNumberSet
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/CommonIRPConstDefs/VersionNumberSet:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * typedef sequence&ltstring&gt VersionNumberSet;
 * </pre>
 */
public final class VersionNumberSetHolder implements org.omg.CORBA.portable.Streamable {
public java.lang.String[] value;

public VersionNumberSetHolder () {
}

public VersionNumberSetHolder (final java.lang.String[] _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CommonIRPConstDefs.VersionNumberSetHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CommonIRPConstDefs.VersionNumberSetHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CommonIRPConstDefs.VersionNumberSetHelper.type();
}
}
