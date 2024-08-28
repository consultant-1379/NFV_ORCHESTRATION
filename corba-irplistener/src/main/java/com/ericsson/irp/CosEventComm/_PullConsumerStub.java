package com.ericsson.irp.CosEventComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosEventComm.idl"
 * <li> <b>IDL Name</b>      ::CosEventComm::PullConsumer
 * <li> <b>Repository Id</b> IDL:omg.org/CosEventComm/PullConsumer:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface PullConsumer {
  ...
};
 * </pre>
 */
public class _PullConsumerStub extends org.omg.CORBA.portable.ObjectImpl implements com.ericsson.irp.CosEventComm.PullConsumer {
  /**
	 * 
	 */
	private static final long serialVersionUID = -829726176143098845L;
final public static Class<PullConsumerOperations> _opsClass = com.ericsson.irp.CosEventComm.PullConsumerOperations.class;

  public java.lang.String[] _ids () {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:omg.org/CosEventComm/PullConsumer:1.0"
  };

  /**
   * <pre>
   *   void disconnect_pull_consumer ();
   * </pre>
   */
  public void disconnect_pull_consumer () {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        try {
          _output = this._request("disconnect_pull_consumer", true);
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("disconnect_pull_consumer", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosEventComm.PullConsumerOperations _self = (com.ericsson.irp.CosEventComm.PullConsumerOperations)_so.servant;
        try {
          _self.disconnect_pull_consumer();
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
