package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::InvalidGrammar
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/InvalidGrammar:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * exception InvalidGrammar {
  ...
};
 * </pre>
 */
public final class InvalidGrammarHolder implements org.omg.CORBA.portable.Streamable {
public com.ericsson.irp.CosNotifyFilter.InvalidGrammar value;

public InvalidGrammarHolder () {
}

public InvalidGrammarHolder (final com.ericsson.irp.CosNotifyFilter.InvalidGrammar _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = com.ericsson.irp.CosNotifyFilter.InvalidGrammarHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  com.ericsson.irp.CosNotifyFilter.InvalidGrammarHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return com.ericsson.irp.CosNotifyFilter.InvalidGrammarHelper.type();
}
}
