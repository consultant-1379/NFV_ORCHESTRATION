package com.ericsson.irp.CosNaming;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNaming.idl"
 * <li> <b>IDL Name</b>      ::CosNaming::NameComponent
 * <li> <b>Repository Id</b> IDL:omg.org/CosNaming/NameComponent:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * struct NameComponent {
  ...
};
 * </pre>
 */
public final class NameComponent implements org.omg.CORBA.portable.IDLEntity {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = -8185560833825549417L;

public java.lang.String id;
  
  public java.lang.String kind;

  public NameComponent () {
    id = "";
    kind = "";
  }

  public NameComponent (final java.lang.String id, 
                        final java.lang.String kind) {
    this.id = id;
    this.kind = kind;
  }

}
