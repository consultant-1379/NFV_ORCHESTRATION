package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::PushSupplier
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/PushSupplier:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface PushSupplier : com.ericsson.irp.CosNotifyComm.NotifySubscribe,
                         com.ericsson.irp.CosEventComm.PushSupplier {
  ...
};
 * </pre>
 */
public class _PushSupplierStub extends com.ericsson.irp.CosNotifyComm._NotifySubscribeStub implements com.ericsson.irp.CosNotifyComm.PushSupplier {
  /**
	 * 
	 */
	private static final long serialVersionUID = 2978209334134065946L;

final public static Class<PushSupplierOperations> _opsClass = com.ericsson.irp.CosNotifyComm.PushSupplierOperations.class;

  public java.lang.String[] _ids () {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:omg.org/CosNotifyComm/PushSupplier:1.0",
    "IDL:omg.org/CosNotifyComm/NotifySubscribe:1.0",
    "IDL:omg.org/CosEventComm/PushSupplier:1.0"
  };

  public com.ericsson.irp.CosEventComm.PushSupplier __com_ericsson_irp_CosEventComm_PushSupplier;

  public void _set_delegate (org.omg.CORBA.portable.Delegate _vis_obj_delegate) {
    super._set_delegate(_vis_obj_delegate);
    this.__com_ericsson_irp_CosEventComm_PushSupplier = new com.ericsson.irp.CosEventComm._PushSupplierStub();
    ((org.omg.CORBA.portable.ObjectImpl) this.__com_ericsson_irp_CosEventComm_PushSupplier)._set_delegate(_vis_obj_delegate);
  }

  /**
   * <pre>
   *   void disconnect_push_supplier ();
   * </pre>
   */
  public void disconnect_push_supplier () {
    this.__com_ericsson_irp_CosEventComm_PushSupplier.disconnect_push_supplier();
  }

}
