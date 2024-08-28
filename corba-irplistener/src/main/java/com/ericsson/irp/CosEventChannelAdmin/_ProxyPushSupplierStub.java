package com.ericsson.irp.CosEventChannelAdmin;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventChannelAdmin.idl"
 * <li> <b>IDL Name</b>      ::CosEventChannelAdmin::ProxyPushSupplier
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventChannelAdmin/ProxyPushSupplier:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface ProxyPushSupplier : com.ericsson.irp.CosEventComm.PushSupplier {
  ...
};
 * </pre>
 */
public class _ProxyPushSupplierStub extends com.ericsson.irp.CosEventComm._PushSupplierStub implements com.ericsson.irp.CosEventChannelAdmin.ProxyPushSupplier {
  /**
	 * 
	 */
	private static final long serialVersionUID = 4804918414477743263L;
final public static Class<ProxyPushSupplierOperations> _opsClass = com.ericsson.irp.CosEventChannelAdmin.ProxyPushSupplierOperations.class;

  public java.lang.String[] _ids () {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:omg.org/CosEventChannelAdmin/ProxyPushSupplier:1.0",
    "IDL:omg.org/CosEventComm/PushSupplier:1.0"
  };

  /**
   * <pre>
   *   void connect_push_consumer (in com.ericsson.irp.CosEventComm.PushConsumer push_consumer)
    raises (com.ericsson.irp.CosEventChannelAdmin.AlreadyConnected,
            com.ericsson.irp.CosEventChannelAdmin.TypeError);
   * </pre>
   */
  public void connect_push_consumer (com.ericsson.irp.CosEventComm.PushConsumer push_consumer) throws  com.ericsson.irp.CosEventChannelAdmin.AlreadyConnected, 
                                                                                                       com.ericsson.irp.CosEventChannelAdmin.TypeError {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        try {
          _output = this._request("connect_push_consumer", true);
          com.ericsson.irp.CosEventComm.PushConsumerHelper.write(_output, push_consumer);
          _input = this._invoke(_output);
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
          if (_exception_id.equals(com.ericsson.irp.CosEventChannelAdmin.AlreadyConnectedHelper.id())) {
            throw             com.ericsson.irp.CosEventChannelAdmin.AlreadyConnectedHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.CosEventChannelAdmin.TypeErrorHelper.id())) {
            throw             com.ericsson.irp.CosEventChannelAdmin.TypeErrorHelper.read(_exception.getInputStream());
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("connect_push_consumer", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosEventChannelAdmin.ProxyPushSupplierOperations _self = (com.ericsson.irp.CosEventChannelAdmin.ProxyPushSupplierOperations)_so.servant;
        try {
          _self.connect_push_consumer(push_consumer);
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
        } catch (com.ericsson.irp.CosEventChannelAdmin.AlreadyConnected x_1) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_1);
          }
          throw x_1;
        } catch (com.ericsson.irp.CosEventChannelAdmin.TypeError x_2) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_2);
          }
          throw x_2;

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
