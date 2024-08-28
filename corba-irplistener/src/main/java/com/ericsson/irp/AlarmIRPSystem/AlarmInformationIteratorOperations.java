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
public interface AlarmInformationIteratorOperations {
  /**
   * <pre>
   *   void destroy ();
   * </pre>
   */
  public void destroy ();

  /**
   * <pre>
   *   boolean next_alarmInformations (in unsigned long how_many,
                                  out com.ericsson.irp.AlarmIRPConstDefs.AlarmInformationSeq alarm_informations)
    raises (com.ericsson.irp.AlarmIRPSystem.NextAlarmInformations,
            com.ericsson.irp.AlarmIRPSystem.InvalidParameter);
   * </pre>
   */
  public boolean next_alarmInformations (int how_many, 
                                         org.omg.CosNotification.EventBatchHolder alarm_informations) throws com.ericsson.irp.AlarmIRPSystem.NextAlarmInformations, 
                                                                                                             com.ericsson.irp.AlarmIRPSystem.InvalidParameter;

}
