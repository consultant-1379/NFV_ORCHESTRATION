package com.ericsson.irp.CosNotifyComm;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyComm.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyComm::NotifyPublish
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyComm/NotifyPublish:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface NotifyPublish {
  ...
};
 * </pre>
 */
public class _NotifyPublishStub extends org.omg.CORBA.portable.ObjectImpl implements com.ericsson.irp.CosNotifyComm.NotifyPublish {
  /**
	 * 
	 */
	private static final long serialVersionUID = 6991142276832248867L;
final public static Class<NotifyPublishOperations> _opsClass = com.ericsson.irp.CosNotifyComm.NotifyPublishOperations.class;

  public java.lang.String[] _ids () {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:omg.org/CosNotifyComm/NotifyPublish:1.0"
  };

  /**
   * <pre>
   *   void offer_change (in org.omg.CosNotification.EventTypeSeq added,
                     in org.omg.CosNotification.EventTypeSeq removed)
    raises (com.ericsson.irp.CosNotifyComm.InvalidEventType);
   * </pre>
   */
  public void offer_change (org.omg.CosNotification.EventType[] added, 
                            org.omg.CosNotification.EventType[] removed) throws  com.ericsson.irp.CosNotifyComm.InvalidEventType {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        try {
          _output = this._request("offer_change", true);
          org.omg.CosNotification.EventTypeSeqHelper.write(_output, added);
          org.omg.CosNotification.EventTypeSeqHelper.write(_output, removed);
          _input = this._invoke(_output);
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
          if (_exception_id.equals(com.ericsson.irp.CosNotifyComm.InvalidEventTypeHelper.id())) {
            throw             com.ericsson.irp.CosNotifyComm.InvalidEventTypeHelper.read(_exception.getInputStream());
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("offer_change", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosNotifyComm.NotifyPublishOperations _self = (com.ericsson.irp.CosNotifyComm.NotifyPublishOperations)_so.servant;
        try {
          _self.offer_change(added, removed);
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
        } catch (com.ericsson.irp.CosNotifyComm.InvalidEventType x_1) {
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
