package com.ericsson.irp.CosEventComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventComm.idl"
 * <li> <b>IDL Name</b>      ::CosEventComm::PushSupplier
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventComm/PushSupplier:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface PushSupplier {
  ...
};
 * </pre>
 */
public class _PushSupplierStub extends org.omg.CORBA.portable.ObjectImpl implements com.ericsson.irp.CosEventComm.PushSupplier {
  /**
	 * 
	 */
	private static final long serialVersionUID = -5641274892541423584L;
final public static Class<PushSupplierOperations> _opsClass = com.ericsson.irp.CosEventComm.PushSupplierOperations.class;

  public java.lang.String[] _ids () {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:omg.org/CosEventComm/PushSupplier:1.0"
  };

  /**
   * <pre>
   *   void disconnect_push_supplier ();
   * </pre>
   */
  public void disconnect_push_supplier () {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        try {
          _output = this._request("disconnect_push_supplier", true);
          _input = this._invoke(_output);
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("disconnect_push_supplier", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosEventComm.PushSupplierOperations _self = (com.ericsson.irp.CosEventComm.PushSupplierOperations)_so.servant;
        try {
          _self.disconnect_push_supplier();
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }

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
