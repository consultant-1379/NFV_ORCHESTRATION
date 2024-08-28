package com.ericsson.irp.CosNaming;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNaming.idl"
 * <li> <b>IDL Name</b>      ::CosNaming::Binding
 * <li> <b>Repository Id</b> IDL:omg.org/CosNaming/Binding:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * struct Binding {
  ...
};
 * </pre>
 */
public final class Binding implements org.omg.CORBA.portable.IDLEntity {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = -815753666942685348L;

public com.ericsson.irp.CosNaming.NameComponent[] binding_name;
  
  public com.ericsson.irp.CosNaming.BindingType binding_type;

  public Binding () {
  }

  public Binding (final com.ericsson.irp.CosNaming.NameComponent[] binding_name, 
                  final com.ericsson.irp.CosNaming.BindingType binding_type) {
    this.binding_name = binding_name;
    this.binding_type = binding_type;
  }

}
