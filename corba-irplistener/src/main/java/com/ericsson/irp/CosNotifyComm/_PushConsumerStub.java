package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::PushConsumer
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/PushConsumer:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface PushConsumer : com.ericsson.irp.CosNotifyComm.NotifyPublish,
                         com.ericsson.irp.CosEventComm.PushConsumer {
  ...
};
 * </pre>
 */
public class _PushConsumerStub extends com.ericsson.irp.CosNotifyComm._NotifyPublishStub implements com.ericsson.irp.CosNotifyComm.PushConsumer {
  /**
	 * 
	 */
	private static final long serialVersionUID = 6969028752360317292L;

final public static Class<PushConsumerOperations> _opsClass = com.ericsson.irp.CosNotifyComm.PushConsumerOperations.class;

  public java.lang.String[] _ids () {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:omg.org/CosNotifyComm/PushConsumer:1.0",
    "IDL:omg.org/CosNotifyComm/NotifyPublish:1.0",
    "IDL:omg.org/CosEventComm/PushConsumer:1.0"
  };

  public com.ericsson.irp.CosEventComm.PushConsumer __com_ericsson_irp_CosEventComm_PushConsumer;

  public void _set_delegate (org.omg.CORBA.portable.Delegate _vis_obj_delegate) {
    super._set_delegate(_vis_obj_delegate);
    this.__com_ericsson_irp_CosEventComm_PushConsumer = new com.ericsson.irp.CosEventComm._PushConsumerStub();
    ((org.omg.CORBA.portable.ObjectImpl) this.__com_ericsson_irp_CosEventComm_PushConsumer)._set_delegate(_vis_obj_delegate);
  }

  /**
   * <pre>
   *   void push (in any data)
    raises (com.ericsson.irp.CosEventComm.Disconnected);
   * </pre>
   */
  public void push (org.omg.CORBA.Any data) throws  com.ericsson.irp.CosEventComm.Disconnected {
    this.__com_ericsson_irp_CosEventComm_PushConsumer.push(data);
  }

  /**
   * <pre>
   *   void disconnect_push_consumer ();
   * </pre>
   */
  public void disconnect_push_consumer () {
    this.__com_ericsson_irp_CosEventComm_PushConsumer.disconnect_push_consumer();
  }

}
