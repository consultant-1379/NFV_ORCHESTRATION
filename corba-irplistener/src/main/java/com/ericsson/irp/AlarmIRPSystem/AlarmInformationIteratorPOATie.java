
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
public class AlarmInformationIteratorPOATie extends AlarmInformationIteratorPOA {
  private com.ericsson.irp.AlarmIRPSystem.AlarmInformationIteratorOperations _delegate;
  private org.omg.PortableServer.POA _poa;

  public AlarmInformationIteratorPOATie (final com.ericsson.irp.AlarmIRPSystem.AlarmInformationIteratorOperations _delegate) {
    this._delegate = _delegate;
  }

  public AlarmInformationIteratorPOATie (final com.ericsson.irp.AlarmIRPSystem.AlarmInformationIteratorOperations _delegate, 
                              final org.omg.PortableServer.POA _poa) {
    this._delegate = _delegate;
    this._poa = _poa;
  }

  public com.ericsson.irp.AlarmIRPSystem.AlarmInformationIteratorOperations _delegate () {
    return this._delegate;
  }

  public void _delegate (final com.ericsson.irp.AlarmIRPSystem.AlarmInformationIteratorOperations the_delegate) {
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
   *   void destroy ();
   * </pre>
   */
  public void destroy () {
    this._delegate.destroy();
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
    return this._delegate.next_alarmInformations(how_many, alarm_informations);
  }

}
