
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
public class _AlarmIRPOperationsPOATie extends _AlarmIRPOperationsPOA {
  private com.ericsson.irp.AlarmIRPSystem._AlarmIRPOperationsOperations _delegate;
  private org.omg.PortableServer.POA _poa;

  public _AlarmIRPOperationsPOATie (final com.ericsson.irp.AlarmIRPSystem._AlarmIRPOperationsOperations _delegate) {
    this._delegate = _delegate;
  }

  public _AlarmIRPOperationsPOATie (final com.ericsson.irp.AlarmIRPSystem._AlarmIRPOperationsOperations _delegate, 
                              final org.omg.PortableServer.POA _poa) {
    this._delegate = _delegate;
    this._poa = _poa;
  }

  public com.ericsson.irp.AlarmIRPSystem._AlarmIRPOperationsOperations _delegate () {
    return this._delegate;
  }

  public void _delegate (final com.ericsson.irp.AlarmIRPSystem._AlarmIRPOperationsOperations the_delegate) {
    this._delegate = the_delegate;
  }

  public org.omg.PortableServer.POA _default_POA () {
    if (_poa != null) {
      return _poa;
    } 
    else {
      return super._default_POA();
    }
  }

  /**
   * <pre>
   *   com.ericsson.irp.CommonIRPConstDefs.VersionNumberSet get_alarm_IRP_version ()
    raises (com.ericsson.irp.AlarmIRPSystem.GetAlarmIRPVersion);
   * </pre>
   */
  public java.lang.String[] get_alarm_IRP_version () throws  com.ericsson.irp.AlarmIRPSystem.GetAlarmIRPVersion {
    return this._delegate.get_alarm_IRP_version();
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
    this._delegate.get_alarm_count(filter, critical_count, major_count, minor_count, 
                                   warning_count, indeterminate_count, cleared_count);
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
    return this._delegate.get_alarm_list(filter, flag, iter);
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
    return this._delegate.unacknowledge_alarms(alarm_information_id_list, ack_user_id, 
                                               ack_system_id, bad_alarm_information_id_list);
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
    return this._delegate.acknowledge_alarms(alarm_information_id_list, ack_user_id, 
                                             ack_system_id, bad_alarm_information_id_list);
  }

}
