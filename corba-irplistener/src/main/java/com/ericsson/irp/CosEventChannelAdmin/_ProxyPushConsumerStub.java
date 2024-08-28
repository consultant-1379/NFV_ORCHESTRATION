package com.ericsson.irp.CosEventChannelAdmin;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventChannelAdmin.idl"
 * <li> <b>IDL Name</b>      ::CosEventChannelAdmin::ProxyPushConsumer
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventChannelAdmin/ProxyPushConsumer:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface ProxyPushConsumer : com.ericsson.irp.CosEventComm.PushConsumer {
  ...
};
 * </pre>
 */
public class _ProxyPushConsumerStub extends com.ericsson.irp.CosEventComm._PushConsumerStub implements com.ericsson.irp.CosEventChannelAdmin.ProxyPushConsumer {
  /**
	 * 
	 */
	private static final long serialVersionUID = -3065148637689852125L;
final public static Class<ProxyPushConsumerOperations> _opsClass = com.ericsson.irp.CosEventChannelAdmin.ProxyPushConsumerOperations.class;

  public java.lang.String[] _ids () {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:omg.org/CosEventChannelAdmin/ProxyPushConsumer:1.0",
    "IDL:omg.org/CosEventComm/PushConsumer:1.0"
  };

  /**
   * <pre>
   *   void connect_push_supplier (in com.ericsson.irp.CosEventComm.PushSupplier push_supplier)
    raises (com.ericsson.irp.CosEventChannelAdmin.AlreadyConnected);
   * </pre>
   */
  public void connect_push_supplier (com.ericsson.irp.CosEventComm.PushSupplier push_supplier) throws  com.ericsson.irp.CosEventChannelAdmin.AlreadyConnected {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        try {
          _output = this._request("connect_push_supplier", true);
          com.ericsson.irp.CosEventComm.PushSupplierHelper.write(_output, push_supplier);
          _input = this._invoke(_output);
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
          if (_exception_id.equals(com.ericsson.irp.CosEventChannelAdmin.AlreadyConnectedHelper.id())) {
            throw             com.ericsson.irp.CosEventChannelAdmin.AlreadyConnectedHelper.read(_exception.getInputStream());
          }
          throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: " + _exception_id);
        }
        catch (org.omg.CORBA.NO_RESPONSE _vis_nm ) {
          if(_vis_nm.minor == (org.omg.CORBA.OMGVMCID.value | 99)) {
            return;
          }
          throw _vis_nm;
        }
        catch (org.omg.CORBA.portable.RemarshalException _exception) {
          continue;
        }
        finally {
          this._releaseReply(_input);
        }
      } else {
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("connect_push_supplier", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosEventChannelAdmin.ProxyPushConsumerOperations _self = (com.ericsson.irp.CosEventChannelAdmin.ProxyPushConsumerOperations)_so.servant;
        try {
          _self.connect_push_supplier(push_supplier);
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
        } catch (com.ericsson.irp.CosEventChannelAdmin.AlreadyConnected x_1) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_1);
          }
          throw x_1;

        } catch (java.lang.RuntimeException _vis_re) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)_vis_re);
          }
          throw _vis_re;
        } catch (java.lang.Error _vis_err) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)_vis_err);
          }
          throw _vis_err;
        } finally {
          _servant_postinvoke(_so);
        }
      }
      break;
    }

  }

}
