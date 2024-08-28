package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::StructuredPushConsumer
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/StructuredPushConsumer:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface StructuredPushConsumer : com.ericsson.irp.CosNotifyComm.NotifyPublish {
  ...
};
 * </pre>
 */
public class _StructuredPushConsumerStub extends com.ericsson.irp.CosNotifyComm._NotifyPublishStub implements com.ericsson.irp.CosNotifyComm.StructuredPushConsumer {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1866403734072734759L;
final public static Class<StructuredPushConsumerOperations> _opsClass = com.ericsson.irp.CosNotifyComm.StructuredPushConsumerOperations.class;

  public java.lang.String[] _ids () {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:omg.org/CosNotifyComm/StructuredPushConsumer:1.0",
    "IDL:omg.org/CosNotifyComm/NotifyPublish:1.0"
  };

  /**
   * <pre>
   *   void disconnect_structured_push_consumer ();
   * </pre>
   */
  public void disconnect_structured_push_consumer () {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        try {
          _output = this._request("disconnect_structured_push_consumer", true);
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("disconnect_structured_push_consumer", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosNotifyComm.StructuredPushConsumerOperations _self = (com.ericsson.irp.CosNotifyComm.StructuredPushConsumerOperations)_so.servant;
        try {
          _self.disconnect_structured_push_consumer();
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
   *   void push_structured_event (in org.omg.CosNotification.StructuredEvent notification)
    raises (com.ericsson.irp.CosEventComm.Disconnected);
   * </pre>
   */
  public void push_structured_event (org.omg.CosNotification.StructuredEvent notification) throws  com.ericsson.irp.CosEventComm.Disconnected {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        try {
          _output = this._request("push_structured_event", true);
          if (notification == null) {
            throw new org.omg.CORBA.BAD_PARAM("Invalid array length");
          }
          org.omg.CosNotification.StructuredEventHelper.write(_output, notification);
          _input = this._invoke(_output);
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("push_structured_event", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosNotifyComm.StructuredPushConsumerOperations _self = (com.ericsson.irp.CosNotifyComm.StructuredPushConsumerOperations)_so.servant;
        try {
          _self.push_structured_event(notification);
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
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
      break;
    }

  }

}
