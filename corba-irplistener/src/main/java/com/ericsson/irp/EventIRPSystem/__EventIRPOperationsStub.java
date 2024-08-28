package com.ericsson.irp.EventIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "EventIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::EventIRPSystem::EventIRPOperations
 * <li> <b>Repository Id</b> IDL:EventIRPSystem/EventIRPOperations:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface _EventIRPOperations {
  ...
};
 * </pre>
 */
public class __EventIRPOperationsStub extends org.omg.CORBA.portable.ObjectImpl implements com.ericsson.irp.EventIRPSystem._EventIRPOperations {
  /**
	 * 
	 */
	private static final long serialVersionUID = -6350721187492422629L;
final public static Class<_EventIRPOperationsOperations> _opsClass = com.ericsson.irp.EventIRPSystem._EventIRPOperationsOperations.class;

  public java.lang.String[] _ids () {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:EventIRPSystem/EventIRPOperations:1.0"
  };

  /**
   * <pre>
   *   com.ericsson.irp.EventIRPConstDefs.VersionNumberSet get_event_IRP_version ()
    raises (com.ericsson.irp.EventIRPSystem.GetEventIRPVersion);
   * </pre>
   */
  public java.lang.String[] get_event_IRP_version () throws  com.ericsson.irp.EventIRPSystem.GetEventIRPVersion {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        java.lang.String[] _result;
        try {
          _output = this._request("get_event_IRP_version", true);
          _input = this._invoke(_output);
          _result = com.ericsson.irp.EventIRPConstDefs.VersionNumberSetHelper.read(_input);
          return _result;
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
          if (_exception_id.equals(com.ericsson.irp.EventIRPSystem.GetEventIRPVersionHelper.id())) {
            throw             com.ericsson.irp.EventIRPSystem.GetEventIRPVersionHelper.read(_exception.getInputStream());
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("get_event_IRP_version", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.EventIRPSystem._EventIRPOperationsOperations _self = (com.ericsson.irp.EventIRPSystem._EventIRPOperationsOperations)_so.servant;
        try {
          java.lang.String[] _ret = _self.get_event_IRP_version();
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
          return _ret;
        } catch (com.ericsson.irp.EventIRPSystem.GetEventIRPVersion x_1) {
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
   *   com.ericsson.irp.EventIRPConstDefs.EventInformationSeq get_event_list (in string filter,
                                                                         out boolean flag,
                                                                         out com.ericsson.irp.EventIRPSystem.EventInformationIterator iter)
    raises (com.ericsson.irp.EventIRPSystem.OperationNotSupported,
            com.ericsson.irp.EventIRPSystem.ParameterNotSupported,
            com.ericsson.irp.EventIRPSystem.InvalidParameter,
            com.ericsson.irp.EventIRPSystem.GetEventList);
   * </pre>
   */
  public org.omg.CosNotification.StructuredEvent[] get_event_list (java.lang.String filter, 
                                                                   org.omg.CORBA.BooleanHolder flag, 
                                                                   com.ericsson.irp.EventIRPSystem.EventInformationIteratorHolder iter) throws  com.ericsson.irp.EventIRPSystem.OperationNotSupported, 
                                                                                                                                                com.ericsson.irp.EventIRPSystem.ParameterNotSupported, 
                                                                                                                                                com.ericsson.irp.EventIRPSystem.InvalidParameter, 
                                                                                                                                                com.ericsson.irp.EventIRPSystem.GetEventList {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        org.omg.CosNotification.StructuredEvent[] _result;
        try {
          _output = this._request("get_event_list", true);
          _output.write_string((java.lang.String)filter);
          _input = this._invoke(_output);
          _result = org.omg.CosNotification.EventBatchHelper.read(_input);
          flag.value = _input.read_boolean();
          iter.value = com.ericsson.irp.EventIRPSystem.EventInformationIteratorHelper.read(_input);
          return _result;
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
          if (_exception_id.equals(com.ericsson.irp.EventIRPSystem.OperationNotSupportedHelper.id())) {
            throw             com.ericsson.irp.EventIRPSystem.OperationNotSupportedHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.EventIRPSystem.ParameterNotSupportedHelper.id())) {
            throw             com.ericsson.irp.EventIRPSystem.ParameterNotSupportedHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.EventIRPSystem.InvalidParameterHelper.id())) {
            throw             com.ericsson.irp.EventIRPSystem.InvalidParameterHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.EventIRPSystem.GetEventListHelper.id())) {
            throw             com.ericsson.irp.EventIRPSystem.GetEventListHelper.read(_exception.getInputStream());
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("get_event_list", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.EventIRPSystem._EventIRPOperationsOperations _self = (com.ericsson.irp.EventIRPSystem._EventIRPOperationsOperations)_so.servant;
        try {
          org.omg.CosNotification.StructuredEvent[] _ret = _self.get_event_list(filter, 
                                                                                flag, 
                                                                                iter);
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
          return _ret;
        } catch (com.ericsson.irp.EventIRPSystem.OperationNotSupported x_1) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_1);
          }
          throw x_1;
        } catch (com.ericsson.irp.EventIRPSystem.ParameterNotSupported x_2) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_2);
          }
          throw x_2;
        } catch (com.ericsson.irp.EventIRPSystem.InvalidParameter x_3) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_3);
          }
          throw x_3;
        } catch (com.ericsson.irp.EventIRPSystem.GetEventList x_4) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_4);
          }
          throw x_4;

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
