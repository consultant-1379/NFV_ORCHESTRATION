package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::SequencePullSupplier
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/SequencePullSupplier:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface SequencePullSupplier : com.ericsson.irp.CosNotifyComm.NotifySubscribe {
  ...
};
 * </pre>
 */
public class _SequencePullSupplierStub extends com.ericsson.irp.CosNotifyComm._NotifySubscribeStub implements com.ericsson.irp.CosNotifyComm.SequencePullSupplier {
  /**
	 * 
	 */
	private static final long serialVersionUID = 6810433465763711526L;
final public static Class<SequencePullSupplierOperations> _opsClass = com.ericsson.irp.CosNotifyComm.SequencePullSupplierOperations.class;

  public java.lang.String[] _ids () {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:omg.org/CosNotifyComm/SequencePullSupplier:1.0",
    "IDL:omg.org/CosNotifyComm/NotifySubscribe:1.0"
  };

  /**
   * <pre>
   *   void disconnect_sequence_pull_supplier ();
   * </pre>
   */
  public void disconnect_sequence_pull_supplier () {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        try {
          _output = this._request("disconnect_sequence_pull_supplier", true);
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("disconnect_sequence_pull_supplier", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosNotifyComm.SequencePullSupplierOperations _self = (com.ericsson.irp.CosNotifyComm.SequencePullSupplierOperations)_so.servant;
        try {
          _self.disconnect_sequence_pull_supplier();
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

  /**
   * <pre>
   *   org.omg.CosNotification.EventBatch try_pull_structured_events (in long max_number,
                                                                 out boolean has_event)
    raises (com.ericsson.irp.CosEventComm.Disconnected);
   * </pre>
   */
  public org.omg.CosNotification.StructuredEvent[] try_pull_structured_events (int max_number, 
                                                                               org.omg.CORBA.BooleanHolder has_event) throws  com.ericsson.irp.CosEventComm.Disconnected {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        org.omg.CosNotification.StructuredEvent[] _result;
        try {
          _output = this._request("try_pull_structured_events", true);
          _output.write_long((int)max_number);
          _input = this._invoke(_output);
          _result = org.omg.CosNotification.EventBatchHelper.read(_input);
          has_event.value = _input.read_boolean();
          return _result;
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
          if (_exception_id.equals(com.ericsson.irp.CosEventComm.DisconnectedHelper.id())) {
            throw             com.ericsson.irp.CosEventComm.DisconnectedHelper.read(_exception.getInputStream());
          }
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("try_pull_structured_events", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosNotifyComm.SequencePullSupplierOperations _self = (com.ericsson.irp.CosNotifyComm.SequencePullSupplierOperations)_so.servant;
        try {
          org.omg.CosNotification.StructuredEvent[] _ret = _self.try_pull_structured_events(max_number, 
                                                                                            has_event);
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
          return _ret;
        } catch (com.ericsson.irp.CosEventComm.Disconnected x_1) {
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
    }

  }

  /**
   * <pre>
   *   org.omg.CosNotification.EventBatch pull_structured_events (in long max_number)
    raises (com.ericsson.irp.CosEventComm.Disconnected);
   * </pre>
   */
  public org.omg.CosNotification.StructuredEvent[] pull_structured_events (int max_number) throws  com.ericsson.irp.CosEventComm.Disconnected {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        org.omg.CosNotification.StructuredEvent[] _result;
        try {
          _output = this._request("pull_structured_events", true);
          _output.write_long((int)max_number);
          _input = this._invoke(_output);
          _result = org.omg.CosNotification.EventBatchHelper.read(_input);
          return _result;
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
          if (_exception_id.equals(com.ericsson.irp.CosEventComm.DisconnectedHelper.id())) {
            throw             com.ericsson.irp.CosEventComm.DisconnectedHelper.read(_exception.getInputStream());
          }
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("pull_structured_events", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosNotifyComm.SequencePullSupplierOperations _self = (com.ericsson.irp.CosNotifyComm.SequencePullSupplierOperations)_so.servant;
        try {
          org.omg.CosNotification.StructuredEvent[] _ret = _self.pull_structured_events(max_number);
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
          return _ret;
        } catch (com.ericsson.irp.CosEventComm.Disconnected x_1) {
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
    }

  }

}
