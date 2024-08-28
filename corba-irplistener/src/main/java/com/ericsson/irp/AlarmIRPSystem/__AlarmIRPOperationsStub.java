package com.ericsson.irp.AlarmIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "AlarmIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::AlarmIRPSystem::AlarmIRPOperations
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/AlarmIRPSystem/AlarmIRPOperations:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface _AlarmIRPOperations {
  ...
};
 * </pre>
 */
public class __AlarmIRPOperationsStub extends org.omg.CORBA.portable.ObjectImpl implements com.ericsson.irp.AlarmIRPSystem._AlarmIRPOperations {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4265798735264506426L;
	@SuppressWarnings("rawtypes")
	final public static java.lang.Class _opsClass = com.ericsson.irp.AlarmIRPSystem._AlarmIRPOperationsOperations.class;

	public java.lang.String[] _ids () {
		return __ids;
	}

	private static java.lang.String[] __ids = {
		"IDL:3gppsa5.org/AlarmIRPSystem/AlarmIRPOperations:1.0"
	};

	/**
	 * <pre>
	 *   com.ericsson.irp.CommonIRPConstDefs.VersionNumberSet get_alarm_IRP_version ()
    raises (com.ericsson.irp.AlarmIRPSystem.GetAlarmIRPVersion);
	 * </pre>
	 */
	public java.lang.String[] get_alarm_IRP_version () throws  com.ericsson.irp.AlarmIRPSystem.GetAlarmIRPVersion {

		while (true) {
			if (!_is_local()) {
				org.omg.CORBA.portable.OutputStream _output = null;
				org.omg.CORBA.portable.InputStream  _input  = null;
				java.lang.String[] _result;
				try {
					_output = this._request("get_alarm_IRP_version", true);
					_input = this._invoke(_output);
					_result = com.ericsson.irp.CommonIRPConstDefs.VersionNumberSetHelper.read(_input);
					return _result;
				}
				catch (org.omg.CORBA.portable.ApplicationException _exception) {
					java.lang.String _exception_id = _exception.getId();
					if (_exception_id.equals(com.ericsson.irp.AlarmIRPSystem.GetAlarmIRPVersionHelper.id())) {
						throw             com.ericsson.irp.AlarmIRPSystem.GetAlarmIRPVersionHelper.read(_exception.getInputStream());
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
				final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("get_alarm_IRP_version", _opsClass);
				if (_so == null) {
					continue;
				}
				final com.ericsson.irp.AlarmIRPSystem._AlarmIRPOperationsOperations _self = (com.ericsson.irp.AlarmIRPSystem._AlarmIRPOperationsOperations)_so.servant;
				try {
					java.lang.String[] _ret = _self.get_alarm_IRP_version();
					if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
						((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
					}
					return _ret;
				} catch (com.ericsson.irp.AlarmIRPSystem.GetAlarmIRPVersion x_1) {
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
	 *   void get_alarm_count (in string filter, out long critical_count,
                        out long major_count, out long minor_count,
                        out long warning_count, out long indeterminate_count,
                        out long cleared_count)
    raises (com.ericsson.irp.AlarmIRPSystem.GetAlarmCount,
            com.ericsson.irp.AlarmIRPSystem.OperationNotSupported,
            com.ericsson.irp.AlarmIRPSystem.ParameterNotSupported,
            com.ericsson.irp.AlarmIRPSystem.InvalidParameter);
	 * </pre>
	 */
	public void get_alarm_count (java.lang.String filter, 
			org.omg.CORBA.IntHolder critical_count, 
			org.omg.CORBA.IntHolder major_count, 
			org.omg.CORBA.IntHolder minor_count, 
			org.omg.CORBA.IntHolder warning_count, 
			org.omg.CORBA.IntHolder indeterminate_count, 
			org.omg.CORBA.IntHolder cleared_count) throws  com.ericsson.irp.AlarmIRPSystem.GetAlarmCount, 
			com.ericsson.irp.AlarmIRPSystem.OperationNotSupported, 
			com.ericsson.irp.AlarmIRPSystem.ParameterNotSupported, 
			com.ericsson.irp.AlarmIRPSystem.InvalidParameter {

		while (true) {
			if (!_is_local()) {
				org.omg.CORBA.portable.OutputStream _output = null;
				org.omg.CORBA.portable.InputStream  _input  = null;
				try {
					_output = this._request("get_alarm_count", true);
					_output.write_string((java.lang.String)filter);
					_input = this._invoke(_output);
					critical_count.value = _input.read_long();
					major_count.value = _input.read_long();
					minor_count.value = _input.read_long();
					warning_count.value = _input.read_long();
					indeterminate_count.value = _input.read_long();
					cleared_count.value = _input.read_long();
				}
				catch (org.omg.CORBA.portable.ApplicationException _exception) {
					java.lang.String _exception_id = _exception.getId();
					if (_exception_id.equals(com.ericsson.irp.AlarmIRPSystem.GetAlarmCountHelper.id())) {
						throw             com.ericsson.irp.AlarmIRPSystem.GetAlarmCountHelper.read(_exception.getInputStream());
					}
					if (_exception_id.equals(com.ericsson.irp.AlarmIRPSystem.OperationNotSupportedHelper.id())) {
						throw             com.ericsson.irp.AlarmIRPSystem.OperationNotSupportedHelper.read(_exception.getInputStream());
					}
					if (_exception_id.equals(com.ericsson.irp.AlarmIRPSystem.ParameterNotSupportedHelper.id())) {
						throw             com.ericsson.irp.AlarmIRPSystem.ParameterNotSupportedHelper.read(_exception.getInputStream());
					}
					if (_exception_id.equals(com.ericsson.irp.AlarmIRPSystem.InvalidParameterHelper.id())) {
						throw             com.ericsson.irp.AlarmIRPSystem.InvalidParameterHelper.read(_exception.getInputStream());
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
				final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("get_alarm_count", _opsClass);
				if (_so == null) {
					continue;
				}
				final com.ericsson.irp.AlarmIRPSystem._AlarmIRPOperationsOperations _self = (com.ericsson.irp.AlarmIRPSystem._AlarmIRPOperationsOperations)_so.servant;
				try {
					_self.get_alarm_count(filter, critical_count, major_count, minor_count, 
							warning_count, indeterminate_count, cleared_count);
					if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
						((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
					}
				} catch (com.ericsson.irp.AlarmIRPSystem.GetAlarmCount x_1) {
					if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
						((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_1);
					}
					throw x_1;
				} catch (com.ericsson.irp.AlarmIRPSystem.OperationNotSupported x_2) {
					if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
						((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_2);
					}
					throw x_2;
				} catch (com.ericsson.irp.AlarmIRPSystem.ParameterNotSupported x_3) {
					if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
						((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_3);
					}
					throw x_3;
				} catch (com.ericsson.irp.AlarmIRPSystem.InvalidParameter x_4) {
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
			break;
		}

	}

	/**
	 * <pre>
	 *   com.ericsson.irp.AlarmIRPConstDefs.AlarmInformationSeq get_alarm_list (in string filter,
                                                                         out boolean flag,
                                                                         out com.ericsson.irp.AlarmIRPSystem.AlarmInformationIterator iter)
    raises (com.ericsson.irp.AlarmIRPSystem.GetAlarmList,
            com.ericsson.irp.AlarmIRPSystem.ParameterNotSupported,
            com.ericsson.irp.AlarmIRPSystem.InvalidParameter);
	 * </pre>
	 */
	public org.omg.CosNotification.StructuredEvent[] get_alarm_list (java.lang.String filter, 
			org.omg.CORBA.BooleanHolder flag, 
			com.ericsson.irp.AlarmIRPSystem.AlarmInformationIteratorHolder iter) throws  com.ericsson.irp.AlarmIRPSystem.GetAlarmList, 
			com.ericsson.irp.AlarmIRPSystem.ParameterNotSupported, 
			com.ericsson.irp.AlarmIRPSystem.InvalidParameter {

		while (true) {
			if (!_is_local()) {
				org.omg.CORBA.portable.OutputStream _output = null;
				org.omg.CORBA.portable.InputStream  _input  = null;
				org.omg.CosNotification.StructuredEvent[] _result;
				try {
					_output = this._request("get_alarm_list", true);
					_output.write_string((java.lang.String)filter);
					_input = this._invoke(_output);
					_result = org.omg.CosNotification.EventBatchHelper.read(_input);
					flag.value = _input.read_boolean();
					iter.value = com.ericsson.irp.AlarmIRPSystem.AlarmInformationIteratorHelper.read(_input);
					return _result;
				}
				catch (org.omg.CORBA.portable.ApplicationException _exception) {
					java.lang.String _exception_id = _exception.getId();
					if (_exception_id.equals(com.ericsson.irp.AlarmIRPSystem.GetAlarmListHelper.id())) {
						throw             com.ericsson.irp.AlarmIRPSystem.GetAlarmListHelper.read(_exception.getInputStream());
					}
					if (_exception_id.equals(com.ericsson.irp.AlarmIRPSystem.ParameterNotSupportedHelper.id())) {
						throw             com.ericsson.irp.AlarmIRPSystem.ParameterNotSupportedHelper.read(_exception.getInputStream());
					}
					if (_exception_id.equals(com.ericsson.irp.AlarmIRPSystem.InvalidParameterHelper.id())) {
						throw             com.ericsson.irp.AlarmIRPSystem.InvalidParameterHelper.read(_exception.getInputStream());
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
				final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("get_alarm_list", _opsClass);
				if (_so == null) {
					continue;
				}
				final com.ericsson.irp.AlarmIRPSystem._AlarmIRPOperationsOperations _self = (com.ericsson.irp.AlarmIRPSystem._AlarmIRPOperationsOperations)_so.servant;
				try {
					org.omg.CosNotification.StructuredEvent[] _ret = _self.get_alarm_list(filter, 
							flag, 
							iter);
					if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
						((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
					}
					return _ret;
				} catch (com.ericsson.irp.AlarmIRPSystem.GetAlarmList x_1) {
					if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
						((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_1);
					}
					throw x_1;
				} catch (com.ericsson.irp.AlarmIRPSystem.ParameterNotSupported x_2) {
					if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
						((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_2);
					}
					throw x_2;
				} catch (com.ericsson.irp.AlarmIRPSystem.InvalidParameter x_3) {
					if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
						((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_3);
					}
					throw x_3;

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
	 *   com.ericsson.irp.CommonIRPConstDefs.Signal unacknowledge_alarms (in com.ericsson.irp.AlarmIRPConstDefs.AlarmInformationIdSeq alarm_information_id_list,
                                                                   in string ack_user_id,
                                                                   in string ack_system_id,
                                                                   out com.ericsson.irp.AlarmIRPConstDefs.AlarmInformationIdSeq bad_alarm_information_id_list)
    raises (com.ericsson.irp.AlarmIRPSystem.UnacknowledgeAlarms,
            com.ericsson.irp.AlarmIRPSystem.OperationNotSupported,
            com.ericsson.irp.AlarmIRPSystem.ParameterNotSupported,
            com.ericsson.irp.AlarmIRPSystem.InvalidParameter);
	 * </pre>
	 */
	public com.ericsson.irp.CommonIRPConstDefs.Signal unacknowledge_alarms (java.lang.String[] alarm_information_id_list, 
			java.lang.String ack_user_id, 
			java.lang.String ack_system_id, 
			com.ericsson.irp.AlarmIRPConstDefs.AlarmInformationIdSeqHolder bad_alarm_information_id_list) throws  com.ericsson.irp.AlarmIRPSystem.UnacknowledgeAlarms, 
			com.ericsson.irp.AlarmIRPSystem.OperationNotSupported, 
			com.ericsson.irp.AlarmIRPSystem.ParameterNotSupported, 
			com.ericsson.irp.AlarmIRPSystem.InvalidParameter {

		while (true) {
			if (!_is_local()) {
				org.omg.CORBA.portable.OutputStream _output = null;
				org.omg.CORBA.portable.InputStream  _input  = null;
				com.ericsson.irp.CommonIRPConstDefs.Signal _result;
				try {
					_output = this._request("unacknowledge_alarms", true);
					com.ericsson.irp.AlarmIRPConstDefs.AlarmInformationIdSeqHelper.write(_output, alarm_information_id_list);
					_output.write_string((java.lang.String)ack_user_id);
					_output.write_string((java.lang.String)ack_system_id);
					_input = this._invoke(_output);
					_result = com.ericsson.irp.CommonIRPConstDefs.SignalHelper.read(_input);
					bad_alarm_information_id_list.value = com.ericsson.irp.AlarmIRPConstDefs.AlarmInformationIdSeqHelper.read(_input);
					return _result;
				}
				catch (org.omg.CORBA.portable.ApplicationException _exception) {
					java.lang.String _exception_id = _exception.getId();
					if (_exception_id.equals(com.ericsson.irp.AlarmIRPSystem.UnacknowledgeAlarmsHelper.id())) {
						throw             com.ericsson.irp.AlarmIRPSystem.UnacknowledgeAlarmsHelper.read(_exception.getInputStream());
					}
					if (_exception_id.equals(com.ericsson.irp.AlarmIRPSystem.OperationNotSupportedHelper.id())) {
						throw             com.ericsson.irp.AlarmIRPSystem.OperationNotSupportedHelper.read(_exception.getInputStream());
					}
					if (_exception_id.equals(com.ericsson.irp.AlarmIRPSystem.ParameterNotSupportedHelper.id())) {
						throw             com.ericsson.irp.AlarmIRPSystem.ParameterNotSupportedHelper.read(_exception.getInputStream());
					}
					if (_exception_id.equals(com.ericsson.irp.AlarmIRPSystem.InvalidParameterHelper.id())) {
						throw             com.ericsson.irp.AlarmIRPSystem.InvalidParameterHelper.read(_exception.getInputStream());
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
				}catch (Exception e) {
					// TODO: handle exception silently
				}
				finally {
					this._releaseReply(_input);
				}
			} else {
				final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("unacknowledge_alarms", _opsClass);
				if (_so == null) {
					continue;
				}
				final com.ericsson.irp.AlarmIRPSystem._AlarmIRPOperationsOperations _self = (com.ericsson.irp.AlarmIRPSystem._AlarmIRPOperationsOperations)_so.servant;
				try {
					com.ericsson.irp.CommonIRPConstDefs.Signal _ret = _self.unacknowledge_alarms(alarm_information_id_list, 
							ack_user_id, 
							ack_system_id, 
							bad_alarm_information_id_list);
					if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
						((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
					}
					return _ret;
				} catch (com.ericsson.irp.AlarmIRPSystem.UnacknowledgeAlarms x_1) {
					if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
						((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_1);
					}
					throw x_1;
				} catch (com.ericsson.irp.AlarmIRPSystem.OperationNotSupported x_2) {
					if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
						((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_2);
					}
					throw x_2;
				} catch (com.ericsson.irp.AlarmIRPSystem.ParameterNotSupported x_3) {
					if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
						((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_3);
					}
					throw x_3;
				} catch (com.ericsson.irp.AlarmIRPSystem.InvalidParameter x_4) {
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
				}catch (Exception e) {
					// TODO: handle exception silently
				} 
				finally {
					_servant_postinvoke(_so);
				}
			}
		}

	}

	/**
	 * <pre>
	 *   com.ericsson.irp.CommonIRPConstDefs.Signal acknowledge_alarms (in com.ericsson.irp.AlarmIRPConstDefs.AlarmInformationIdSeq alarm_information_id_list,
                                                                 in string ack_user_id,
                                                                 in string ack_system_id,
                                                                 out com.ericsson.irp.AlarmIRPConstDefs.AlarmInformationIdSeq bad_alarm_information_id_list)
    raises (com.ericsson.irp.AlarmIRPSystem.AcknowledgeAlarms,
            com.ericsson.irp.AlarmIRPSystem.ParameterNotSupported,
            com.ericsson.irp.AlarmIRPSystem.InvalidParameter);
	 * </pre>
	 */
	public com.ericsson.irp.CommonIRPConstDefs.Signal acknowledge_alarms (java.lang.String[] alarm_information_id_list, 
			java.lang.String ack_user_id, 
			java.lang.String ack_system_id, 
			com.ericsson.irp.AlarmIRPConstDefs.AlarmInformationIdSeqHolder bad_alarm_information_id_list) throws  com.ericsson.irp.AlarmIRPSystem.AcknowledgeAlarms, 
			com.ericsson.irp.AlarmIRPSystem.ParameterNotSupported, 
			com.ericsson.irp.AlarmIRPSystem.InvalidParameter {

		while (true) {
			if (!_is_local()) {
				org.omg.CORBA.portable.OutputStream _output = null;
				org.omg.CORBA.portable.InputStream  _input  = null;
				com.ericsson.irp.CommonIRPConstDefs.Signal _result;
				try {
					_output = this._request("acknowledge_alarms", true);
					com.ericsson.irp.AlarmIRPConstDefs.AlarmInformationIdSeqHelper.write(_output, alarm_information_id_list);
					_output.write_string((java.lang.String)ack_user_id);
					_output.write_string((java.lang.String)ack_system_id);
					_input = this._invoke(_output);
					_result = com.ericsson.irp.CommonIRPConstDefs.SignalHelper.read(_input);
					bad_alarm_information_id_list.value = com.ericsson.irp.AlarmIRPConstDefs.AlarmInformationIdSeqHelper.read(_input);
					return _result;
				}
				catch (org.omg.CORBA.portable.ApplicationException _exception) {
					_exception.printStackTrace();
					java.lang.String _exception_id = _exception.getId();
					if (_exception_id.equals(com.ericsson.irp.AlarmIRPSystem.AcknowledgeAlarmsHelper.id())) {
						throw             com.ericsson.irp.AlarmIRPSystem.AcknowledgeAlarmsHelper.read(_exception.getInputStream());
					}
					if (_exception_id.equals(com.ericsson.irp.AlarmIRPSystem.ParameterNotSupportedHelper.id())) {
						throw             com.ericsson.irp.AlarmIRPSystem.ParameterNotSupportedHelper.read(_exception.getInputStream());
					}
					if (_exception_id.equals(com.ericsson.irp.AlarmIRPSystem.InvalidParameterHelper.id())) {
						throw             com.ericsson.irp.AlarmIRPSystem.InvalidParameterHelper.read(_exception.getInputStream());
					}
					throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: " + _exception_id);
				}
				catch (org.omg.CORBA.NO_RESPONSE _vis_nm ) {
					_vis_nm.printStackTrace();
					if(_vis_nm.minor == (org.omg.CORBA.OMGVMCID.value | 99)) {
						return null;
					}
					throw _vis_nm;
				}
				catch (org.omg.CORBA.portable.RemarshalException _exception) {
					_exception.printStackTrace();
					continue;
				}catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception silently
				}
				finally {
					this._releaseReply(_input);
				}
			} else {
				final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("acknowledge_alarms", _opsClass);
				if (_so == null) {
					continue;
				}
				final com.ericsson.irp.AlarmIRPSystem._AlarmIRPOperationsOperations _self = (com.ericsson.irp.AlarmIRPSystem._AlarmIRPOperationsOperations)_so.servant;
				try {
					com.ericsson.irp.CommonIRPConstDefs.Signal _ret = _self.acknowledge_alarms(alarm_information_id_list, 
							ack_user_id, 
							ack_system_id, 
							bad_alarm_information_id_list);
					if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
						((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
					}
					return _ret;
				} catch (com.ericsson.irp.AlarmIRPSystem.AcknowledgeAlarms x_1) {
					if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
						((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_1);
					}
					throw x_1;
				} catch (com.ericsson.irp.AlarmIRPSystem.ParameterNotSupported x_2) {
					if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
						((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_2);
					}
					throw x_2;
				} catch (com.ericsson.irp.AlarmIRPSystem.InvalidParameter x_3) {
					if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
						((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_3);
					}
					throw x_3;

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
				}catch (Exception e) {
					// TODO: handle exception silently
				} 
				finally {
					_servant_postinvoke(_so);
				}
			}
		}

	}

}
