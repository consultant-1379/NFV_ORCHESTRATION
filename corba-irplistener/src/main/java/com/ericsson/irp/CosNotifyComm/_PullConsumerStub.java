package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::PullConsumer
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/PullConsumer:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface PullConsumer : com.ericsson.irp.CosNotifyComm.NotifyPublish,
                         com.ericsson.irp.CosEventComm.PullConsumer {
  ...
};
 * </pre>
 */
public class _PullConsumerStub extends com.ericsson.irp.CosNotifyComm._NotifyPublishStub implements com.ericsson.irp.CosNotifyComm.PullConsumer {
  /**
	 * 
	 */
	private static final long serialVersionUID = -8020163624560573720L;

final public static Class<PullConsumerOperations> _opsClass = com.ericsson.irp.CosNotifyComm.PullConsumerOperations.class;

  public java.lang.String[] _ids () {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:omg.org/CosNotifyComm/PullConsumer:1.0",
    "IDL:omg.org/CosNotifyComm/NotifyPublish:1.0",
    "IDL:omg.org/CosEventComm/PullConsumer:1.0"
  };

  public com.ericsson.irp.CosEventComm.PullConsumer __com_ericsson_irp_CosEventComm_PullConsumer;

  public void _set_delegate (org.omg.CORBA.portable.Delegate _vis_obj_delegate) {
    super._set_delegate(_vis_obj_delegate);
    this.__com_ericsson_irp_CosEventComm_PullConsumer = new com.ericsson.irp.CosEventComm._PullConsumerStub();
    ((org.omg.CORBA.portable.ObjectImpl) this.__com_ericsson_irp_CosEventComm_PullConsumer)._set_delegate(_vis_obj_delegate);
  }

  /**
   * <pre>
   *   void disconnect_pull_consumer ();
   * </pre>
   */
  public void disconnect_pull_consumer () {
    this.__com_ericsson_irp_CosEventComm_PullConsumer.disconnect_pull_consumer();
  }

}
