package com.ericsson.irp.CosEventChannelAdmin;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventChannelAdmin.idl"
 * <li> <b>IDL Name</b>      ::CosEventChannelAdmin::SupplierAdmin
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventChannelAdmin/SupplierAdmin:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface SupplierAdmin {
  ...
};
 * </pre>
 */
public class _SupplierAdminStub extends org.omg.CORBA.portable.ObjectImpl implements com.ericsson.irp.CosEventChannelAdmin.SupplierAdmin {
  /**
	 * 
	 */
	private static final long serialVersionUID = -2250139291071707847L;
final public static Class<SupplierAdminOperations> _opsClass = com.ericsson.irp.CosEventChannelAdmin.SupplierAdminOperations.class;

  public java.lang.String[] _ids () {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:omg.org/CosEventChannelAdmin/SupplierAdmin:1.0"
  };

  /**
   * <pre>
   *   com.ericsson.irp.CosEventChannelAdmin.ProxyPullConsumer obtain_pull_consumer ();
   * </pre>
   */
  public com.ericsson.irp.CosEventChannelAdmin.ProxyPullConsumer obtain_pull_consumer () {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        com.ericsson.irp.CosEventChannelAdmin.ProxyPullConsumer _result;
        try {
          _output = this._request("obtain_pull_consumer", true);
          _input = this._invoke(_output);
          _result = com.ericsson.irp.CosEventChannelAdmin.ProxyPullConsumerHelper.read(_input);
          return _result;
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
          throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: " + _exception_id);
        }
        catch (org.omg.CORBA.NO_RESPONSE _vis_nm ) {
          if(_vis_nm.minor == (org.omg.CORBA.OMGVMCID.value | 99)) {
            return null;
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("obtain_pull_consumer", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosEventChannelAdmin.SupplierAdminOperations _self = (com.ericsson.irp.CosEventChannelAdmin.SupplierAdminOperations)_so.servant;
        try {
          com.ericsson.irp.CosEventChannelAdmin.ProxyPullConsumer _ret = _self.obtain_pull_consumer();
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
          return _ret;

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
    }

  }

  /**
   * <pre>
   *   com.ericsson.irp.CosEventChannelAdmin.ProxyPushConsumer obtain_push_consumer ();
   * </pre>
   */
  public com.ericsson.irp.CosEventChannelAdmin.ProxyPushConsumer obtain_push_consumer () {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        com.ericsson.irp.CosEventChannelAdmin.ProxyPushConsumer _result;
        try {
          _output = this._request("obtain_push_consumer", true);
          _input = this._invoke(_output);
          _result = com.ericsson.irp.CosEventChannelAdmin.ProxyPushConsumerHelper.read(_input);
          return _result;
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
          throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: " + _exception_id);
        }
        catch (org.omg.CORBA.NO_RESPONSE _vis_nm ) {
          if(_vis_nm.minor == (org.omg.CORBA.OMGVMCID.value | 99)) {
            return null;
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("obtain_push_consumer", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosEventChannelAdmin.SupplierAdminOperations _self = (com.ericsson.irp.CosEventChannelAdmin.SupplierAdminOperations)_so.servant;
        try {
          com.ericsson.irp.CosEventChannelAdmin.ProxyPushConsumer _ret = _self.obtain_push_consumer();
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
          return _ret;

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
    }

  }

}
