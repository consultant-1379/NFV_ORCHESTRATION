package com.ericsson.irp.AlarmIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPSystem::AlarmInformationIterator
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPSystem/AlarmInformationIterator:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface AlarmInformationIterator {
  ...
};
 * </pre>
 */
public class _AlarmInformationIteratorStub extends org.omg.CORBA.portable.ObjectImpl implements com.ericsson.irp.AlarmIRPSystem.AlarmInformationIterator {
  /**
	 * 
	 */
	private static final long serialVersionUID = -4786934879467106435L;
@SuppressWarnings("rawtypes")
final public static java.lang.Class _opsClass = com.ericsson.irp.AlarmIRPSystem.AlarmInformationIteratorOperations.class;

  public java.lang.String[] _ids () {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:3gppsa5.org/AlarmIRPSystem/AlarmInformationIterator:1.0"
  };

  /**
   * <pre>
   *   void destroy ();
   * </pre>
   */
  public void destroy () {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        try {
          _output = this._request("destroy", true);
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("destroy", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.AlarmIRPSystem.AlarmInformationIteratorOperations _self = (com.ericsson.irp.AlarmIRPSystem.AlarmInformationIteratorOperations)_so.servant;
        try {
          _self.destroy();
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
   *   boolean next_alarmInformations (in unsigned long how_many,
                                  out com.ericsson.irp.AlarmIRPConstDefs.AlarmInformationSeq alarm_informations)
    raises (com.ericsson.irp.AlarmIRPSystem.NextAlarmInformations,
            com.ericsson.irp.AlarmIRPSystem.InvalidParameter);
   * </pre>
   */
  public boolean next_alarmInformations (int how_many, 
                                         org.omg.CosNotification.EventBatchHolder alarm_informations) throws  com.ericsson.irp.AlarmIRPSystem.NextAlarmInformations, 
                                                                                                              com.ericsson.irp.AlarmIRPSystem.InvalidParameter {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        boolean _result;
        try {
          _output = this._request("next_alarmInformations", true);
          _output.write_ulong((int)how_many);
          _input = this._invoke(_output);
          _result = _input.read_boolean();
          alarm_informations.value = org.omg.CosNotification.EventBatchHelper.read(_input);
          return _result;
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
          if (_exception_id.equals(com.ericsson.irp.AlarmIRPSystem.NextAlarmInformationsHelper.id())) {
            throw             com.ericsson.irp.AlarmIRPSystem.NextAlarmInformationsHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.AlarmIRPSystem.InvalidParameterHelper.id())) {
            throw             com.ericsson.irp.AlarmIRPSystem.InvalidParameterHelper.read(_exception.getInputStream());
          }
          throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: " + _exception_id);
        }
        catch (org.omg.CORBA.NO_RESPONSE _vis_nm ) {
          if(_vis_nm.minor == (org.omg.CORBA.OMGVMCID.value | 99)) {
            return false;
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("next_alarmInformations", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.AlarmIRPSystem.AlarmInformationIteratorOperations _self = (com.ericsson.irp.AlarmIRPSystem.AlarmInformationIteratorOperations)_so.servant;
        try {
          boolean _ret = _self.next_alarmInformations(how_many, alarm_informations);
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
          return _ret;
        } catch (com.ericsson.irp.AlarmIRPSystem.NextAlarmInformations x_1) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_1);
          }
          throw x_1;
        } catch (com.ericsson.irp.AlarmIRPSystem.InvalidParameter x_2) {
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
    }

  }

}
