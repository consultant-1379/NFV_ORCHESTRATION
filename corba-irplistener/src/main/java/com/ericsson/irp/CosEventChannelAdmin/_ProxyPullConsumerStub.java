package com.ericsson.irp.CosEventChannelAdmin;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventChannelAdmin.idl"
 * <li> <b>IDL Name</b>      ::CosEventChannelAdmin::ProxyPullConsumer
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventChannelAdmin/ProxyPullConsumer:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface ProxyPullConsumer : com.ericsson.irp.CosEventComm.PullConsumer {
  ...
};
 * </pre>
 */
public class _ProxyPullConsumerStub extends com.ericsson.irp.CosEventComm._PullConsumerStub implements com.ericsson.irp.CosEventChannelAdmin.ProxyPullConsumer {
  /**
	 * 
	 */
	private static final long serialVersionUID = 6693042971189309660L;
@SuppressWarnings("rawtypes")
final public static java.lang.Class _opsClass = com.ericsson.irp.CosEventChannelAdmin.ProxyPullConsumerOperations.class;

  public java.lang.String[] _ids () {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:omg.org/CosEventChannelAdmin/ProxyPullConsumer:1.0",
    "IDL:omg.org/CosEventComm/PullConsumer:1.0"
  };

  /**
   * <pre>
   *   void connect_pull_supplier (in com.ericsson.irp.CosEventComm.PullSupplier pull_supplier)
    raises (com.ericsson.irp.CosEventChannelAdmin.AlreadyConnected,
            com.ericsson.irp.CosEventChannelAdmin.TypeError);
   * </pre>
   */
  public void connect_pull_supplier (com.ericsson.irp.CosEventComm.PullSupplier pull_supplier) throws  com.ericsson.irp.CosEventChannelAdmin.AlreadyConnected, 
                                                                                                       com.ericsson.irp.CosEventChannelAdmin.TypeError {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        try {
          _output = this._request("connect_pull_supplier", true);
          com.ericsson.irp.CosEventComm.PullSupplierHelper.write(_output, pull_supplier);
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("connect_pull_supplier", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosEventChannelAdmin.ProxyPullConsumerOperations _self = (com.ericsson.irp.CosEventChannelAdmin.ProxyPullConsumerOperations)_so.servant;
        try {
          _self.connect_pull_supplier(pull_supplier);
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
