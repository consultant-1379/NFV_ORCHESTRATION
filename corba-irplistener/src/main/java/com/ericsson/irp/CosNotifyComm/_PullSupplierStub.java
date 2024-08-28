package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::PullSupplier
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/PullSupplier:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface PullSupplier : com.ericsson.irp.CosNotifyComm.NotifySubscribe,
                         com.ericsson.irp.CosEventComm.PullSupplier {
  ...
};
 * </pre>
 */
public class _PullSupplierStub extends com.ericsson.irp.CosNotifyComm._NotifySubscribeStub implements com.ericsson.irp.CosNotifyComm.PullSupplier {
  /**
	 * 
	 */
	private static final long serialVersionUID = -7501712944059894503L;

final public static Class<PullSupplierOperations> _opsClass = com.ericsson.irp.CosNotifyComm.PullSupplierOperations.class;

  public java.lang.String[] _ids () {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:omg.org/CosNotifyComm/PullSupplier:1.0",
    "IDL:omg.org/CosNotifyComm/NotifySubscribe:1.0",
    "IDL:omg.org/CosEventComm/PullSupplier:1.0"
  };

  public com.ericsson.irp.CosEventComm.PullSupplier __com_ericsson_irp_CosEventComm_PullSupplier;

  public void _set_delegate (org.omg.CORBA.portable.Delegate _vis_obj_delegate) {
    super._set_delegate(_vis_obj_delegate);
    this.__com_ericsson_irp_CosEventComm_PullSupplier = new com.ericsson.irp.CosEventComm._PullSupplierStub();
    ((org.omg.CORBA.portable.ObjectImpl) this.__com_ericsson_irp_CosEventComm_PullSupplier)._set_delegate(_vis_obj_delegate);
  }

  /**
   * <pre>
   *   any pull ()
    raises (com.ericsson.irp.CosEventComm.Disconnected);
   * </pre>
   */
  public org.omg.CORBA.Any pull () throws  com.ericsson.irp.CosEventComm.Disconnected {
    return this.__com_ericsson_irp_CosEventComm_PullSupplier.pull();
  }

  /**
   * <pre>
   *   any try_pull (out boolean has_event)
    raises (com.ericsson.irp.CosEventComm.Disconnected);
   * </pre>
   */
  public org.omg.CORBA.Any try_pull (org.omg.CORBA.BooleanHolder has_event) throws  com.ericsson.irp.CosEventComm.Disconnected {
    return this.__com_ericsson_irp_CosEventComm_PullSupplier.try_pull(has_event);
  }

  /**
   * <pre>
   *   void disconnect_pull_supplier ();
   * </pre>
   */
  public void disconnect_pull_supplier () {
    this.__com_ericsson_irp_CosEventComm_PullSupplier.disconnect_pull_supplier();
  }

}
