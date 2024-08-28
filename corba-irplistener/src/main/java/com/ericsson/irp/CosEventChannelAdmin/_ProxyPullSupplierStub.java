package com.ericsson.irp.CosEventChannelAdmin;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventChannelAdmin.idl"
 * <li> <b>IDL Name</b>      ::CosEventChannelAdmin::ProxyPullSupplier
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventChannelAdmin/ProxyPullSupplier:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface ProxyPullSupplier : com.ericsson.irp.CosEventComm.PullSupplier {
  ...
};
 * </pre>
 */
public class _ProxyPullSupplierStub extends com.ericsson.irp.CosEventComm._PullSupplierStub implements com.ericsson.irp.CosEventChannelAdmin.ProxyPullSupplier {
  /**
	 * 
	 */
	private static final long serialVersionUID = 5966918796743005366L;
@SuppressWarnings("rawtypes")
final public static java.lang.Class _opsClass = com.ericsson.irp.CosEventChannelAdmin.ProxyPullSupplierOperations.class;

  public java.lang.String[] _ids () {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:omg.org/CosEventChannelAdmin/ProxyPullSupplier:1.0",
    "IDL:omg.org/CosEventComm/PullSupplier:1.0"
  };

  /**
   * <pre>
   *   void connect_pull_consumer (in com.ericsson.irp.CosEventComm.PullConsumer pull_consumer)
    raises (com.ericsson.irp.CosEventChannelAdmin.AlreadyConnected);
   * </pre>
   */
  public void connect_pull_consumer (com.ericsson.irp.CosEventComm.PullConsumer pull_consumer) throws  com.ericsson.irp.CosEventChannelAdmin.AlreadyConnected {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        try {
          _output = this._request("connect_pull_consumer", true);
          com.ericsson.irp.CosEventComm.PullConsumerHelper.write(_output, pull_consumer);
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("connect_pull_consumer", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosEventChannelAdmin.ProxyPullSupplierOperations _self = (com.ericsson.irp.CosEventChannelAdmin.ProxyPullSupplierOperations)_so.servant;
        try {
          _self.connect_pull_consumer(pull_consumer);
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
