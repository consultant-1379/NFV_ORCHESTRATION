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
public interface _AlarmIRPOperationsOperations {
  /**
   * <pre>
   *   com.ericsson.irp.CommonIRPConstDefs.VersionNumberSet get_alarm_IRP_version ()
    raises (com.ericsson.irp.AlarmIRPSystem.GetAlarmIRPVersion);
   * </pre>
   */
  public java.lang.String[] get_alarm_IRP_version () throws com.ericsson.irp.AlarmIRPSystem.GetAlarmIRPVersion;

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
                               org.omg.CORBA.IntHolder cleared_count) throws com.ericsson.irp.AlarmIRPSystem.GetAlarmCount, 
                                                                             com.ericsson.irp.AlarmIRPSystem.OperationNotSupported, 
                                                                             com.ericsson.irp.AlarmIRPSystem.ParameterNotSupported, 
                                                                             com.ericsson.irp.AlarmIRPSystem.InvalidParameter;

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
                                                                   com.ericsson.irp.AlarmIRPSystem.AlarmInformationIteratorHolder iter) throws com.ericsson.irp.AlarmIRPSystem.GetAlarmList, 
                                                                                                                                               com.ericsson.irp.AlarmIRPSystem.ParameterNotSupported, 
                                                                                                                                               com.ericsson.irp.AlarmIRPSystem.InvalidParameter;

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
                                                                          com.ericsson.irp.AlarmIRPConstDefs.AlarmInformationIdSeqHolder bad_alarm_information_id_list) throws com.ericsson.irp.AlarmIRPSystem.UnacknowledgeAlarms, 
                                                                                                                                                                               com.ericsson.irp.AlarmIRPSystem.OperationNotSupported, 
                                                                                                                                                                               com.ericsson.irp.AlarmIRPSystem.ParameterNotSupported, 
                                                                                                                                                                               com.ericsson.irp.AlarmIRPSystem.InvalidParameter;

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
                                                                        com.ericsson.irp.AlarmIRPConstDefs.AlarmInformationIdSeqHolder bad_alarm_information_id_list) throws com.ericsson.irp.AlarmIRPSystem.AcknowledgeAlarms, 
                                                                                                                                                                             com.ericsson.irp.AlarmIRPSystem.ParameterNotSupported, 
                                                                                                                                                                             com.ericsson.irp.AlarmIRPSystem.InvalidParameter;

}
