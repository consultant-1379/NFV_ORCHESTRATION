package com.ericsson.irp.NotificationIRPSystem;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "NotificationIRPSystem.idl"
 * <li> <b>IDL Name</b>      ::NotificationIRPSystem::NotificationIRPOperations
 * <li> <b>Repository Id</b> IDL:3gppsa5.org/NotificationIRPSystem/NotificationIRPOperations:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface _NotificationIRPOperations {
  ...
};
 * </pre>
 */
public interface _NotificationIRPOperationsOperations {
  /**
   * <pre>
   *   com.ericsson.irp.NotificationIRPConstDefs.NotificationCategorySet get_notification_categories (out com.ericsson.irp.NotificationIRPConstDefs.EventTypesSet event_type_list,
                                                                                                 out com.ericsson.irp.NotificationIRPConstDefs.ExtendedEventTypesSet extended_event_type_list)
    raises (com.ericsson.irp.NotificationIRPSystem.GetNotificationCategories,
            com.ericsson.irp.NotificationIRPSystem.OperationNotSupported);
   * </pre>
   */
  public java.lang.String[] get_notification_categories (com.ericsson.irp.NotificationIRPConstDefs.EventTypesSetHolder event_type_list, 
                                                         com.ericsson.irp.NotificationIRPConstDefs.ExtendedEventTypesSetHolder extended_event_type_list) throws com.ericsson.irp.NotificationIRPSystem.GetNotificationCategories, 
                                                                                                                                                                com.ericsson.irp.NotificationIRPSystem.OperationNotSupported;

  /**
   * <pre>
   *   void change_subscription_filter (in string subscription_id, in string filter)
    raises (com.ericsson.irp.NotificationIRPSystem.ChangeSubscriptionFilter,
            com.ericsson.irp.NotificationIRPSystem.OperationNotSupported,
            com.ericsson.irp.NotificationIRPSystem.InvalidParameter);
   * </pre>
   */
  public void change_subscription_filter (java.lang.String subscription_id, 
                                          java.lang.String filter) throws com.ericsson.irp.NotificationIRPSystem.ChangeSubscriptionFilter, 
                                                                          com.ericsson.irp.NotificationIRPSystem.OperationNotSupported, 
                                                                          com.ericsson.irp.NotificationIRPSystem.InvalidParameter;

  /**
   * <pre>
   *   com.ericsson.irp.NotificationIRPConstDefs.SubscriptionIdSet get_subscription_ids (in Object manager_reference)
    raises (com.ericsson.irp.NotificationIRPSystem.GetSubscriptionIds,
            com.ericsson.irp.NotificationIRPSystem.OperationNotSupported,
            com.ericsson.irp.NotificationIRPSystem.InvalidParameter);
   * </pre>
   */
  public java.lang.String[] get_subscription_ids (org.omg.CORBA.Object manager_reference) throws com.ericsson.irp.NotificationIRPSystem.GetSubscriptionIds, 
                                                                                                 com.ericsson.irp.NotificationIRPSystem.OperationNotSupported, 
                                                                                                 com.ericsson.irp.NotificationIRPSystem.InvalidParameter;

  /**
   * <pre>
   *   com.ericsson.irp.NotificationIRPConstDefs.NotificationCategorySet get_subscription_status (in string subscription_id,
                                                                                             out string filter_in_effect,
                                                                                             out com.ericsson.irp.NotificationIRPConstDefs.SubscriptionState subscription_state,
                                                                                             out long time_tick)
    raises (com.ericsson.irp.NotificationIRPSystem.GetSubscriptionStatus,
            com.ericsson.irp.NotificationIRPSystem.OperationNotSupported,
            com.ericsson.irp.NotificationIRPSystem.InvalidParameter);
   * </pre>
   */
  public java.lang.String[] get_subscription_status (java.lang.String subscription_id, 
                                                     org.omg.CORBA.StringHolder filter_in_effect, 
                                                     com.ericsson.irp.NotificationIRPConstDefs.SubscriptionStateHolder subscription_state, 
                                                     org.omg.CORBA.IntHolder time_tick) throws com.ericsson.irp.NotificationIRPSystem.GetSubscriptionStatus, 
                                                                                               com.ericsson.irp.NotificationIRPSystem.OperationNotSupported, 
                                                                                               com.ericsson.irp.NotificationIRPSystem.InvalidParameter;

  /**
   * <pre>
   *   com.ericsson.irp.CommonIRPConstDefs.VersionNumberSet get_notification_IRP_version ()
    raises (com.ericsson.irp.NotificationIRPSystem.GetNotificationIRPVersion);
   * </pre>
   */
  public java.lang.String[] get_notification_IRP_version () throws com.ericsson.irp.NotificationIRPSystem.GetNotificationIRPVersion;

  /**
   * <pre>
   *   void detach (in Object manager_reference, in string subscription_id)
    raises (com.ericsson.irp.NotificationIRPSystem.DetachException,
            com.ericsson.irp.NotificationIRPSystem.InvalidParameter);
   * </pre>
   */
  public void detach (org.omg.CORBA.Object manager_reference, 
                      java.lang.String subscription_id) throws com.ericsson.irp.NotificationIRPSystem.DetachException, 
                                                               com.ericsson.irp.NotificationIRPSystem.InvalidParameter;

  /**
   * <pre>
   *   com.ericsson.irp.NotificationIRPConstDefs.SubscriptionId attach_pull (in Object manager_reference,
                                                                        in long time_tick,
                                                                        in com.ericsson.irp.NotificationIRPConstDefs.NotificationCategorySet notification_category_set,
                                                                        in string filter,
                                                                        out org.omg.CosNotifyChannelAdmin.SequenceProxyPullSupplier system_reference)
    raises (com.ericsson.irp.NotificationIRPSystem.Attach,
            com.ericsson.irp.NotificationIRPSystem.OperationNotSupported,
            com.ericsson.irp.NotificationIRPSystem.ParameterNotSupported,
            com.ericsson.irp.NotificationIRPSystem.InvalidParameter,
            com.ericsson.irp.NotificationIRPSystem.AlreadySubscribed,
            com.ericsson.irp.NotificationIRPSystem.AtLeastOneNotificationCategoryNotSupported);
   * </pre>
   */
  public java.lang.String attach_pull (org.omg.CORBA.Object manager_reference, 
                                       int time_tick, 
                                       java.lang.String[] notification_category_set, 
                                       java.lang.String filter, 
                                       org.omg.CosNotifyChannelAdmin.SequenceProxyPullSupplierHolder system_reference) throws com.ericsson.irp.NotificationIRPSystem.Attach, 
                                                                                                                              com.ericsson.irp.NotificationIRPSystem.OperationNotSupported, 
                                                                                                                              com.ericsson.irp.NotificationIRPSystem.ParameterNotSupported, 
                                                                                                                              com.ericsson.irp.NotificationIRPSystem.InvalidParameter, 
                                                                                                                              com.ericsson.irp.NotificationIRPSystem.AlreadySubscribed, 
                                                                                                                              com.ericsson.irp.NotificationIRPSystem.AtLeastOneNotificationCategoryNotSupported;

  /**
   * <pre>
   *   com.ericsson.irp.NotificationIRPConstDefs.SubscriptionId attach_push_b (in Object manager_reference,
                                                                          in long time_tick,
                                                                          in com.ericsson.irp.NotificationIRPConstDefs.NotificationCategorySet notification_category_set,
                                                                          in string filter,
                                                                          out org.omg.CosNotifyChannelAdmin.SequenceProxyPushSupplier system_reference)
    raises (com.ericsson.irp.NotificationIRPSystem.Attach,
            com.ericsson.irp.NotificationIRPSystem.OperationNotSupported,
            com.ericsson.irp.NotificationIRPSystem.ParameterNotSupported,
            com.ericsson.irp.NotificationIRPSystem.InvalidParameter,
            com.ericsson.irp.NotificationIRPSystem.AlreadySubscribed,
            com.ericsson.irp.NotificationIRPSystem.AtLeastOneNotificationCategoryNotSupported);
   * </pre>
   */
  public java.lang.String attach_push_b (org.omg.CORBA.Object manager_reference, 
                                         int time_tick, 
                                         java.lang.String[] notification_category_set, 
                                         java.lang.String filter, 
                                         org.omg.CosNotifyChannelAdmin.SequenceProxyPushSupplierHolder system_reference) throws com.ericsson.irp.NotificationIRPSystem.Attach, 
                                                                                                                                com.ericsson.irp.NotificationIRPSystem.OperationNotSupported, 
                                                                                                                                com.ericsson.irp.NotificationIRPSystem.ParameterNotSupported, 
                                                                                                                                com.ericsson.irp.NotificationIRPSystem.InvalidParameter, 
                                                                                                                                com.ericsson.irp.NotificationIRPSystem.AlreadySubscribed, 
                                                                                                                                com.ericsson.irp.NotificationIRPSystem.AtLeastOneNotificationCategoryNotSupported;

  /**
   * <pre>
   *   com.ericsson.irp.NotificationIRPConstDefs.SubscriptionId attach_push (in Object manager_reference,
                                                                        in long time_tick,
                                                                        in com.ericsson.irp.NotificationIRPConstDefs.NotificationCategorySet notification_category_set,
                                                                        in string filter)
    raises (com.ericsson.irp.NotificationIRPSystem.Attach,
            com.ericsson.irp.NotificationIRPSystem.ParameterNotSupported,
            com.ericsson.irp.NotificationIRPSystem.InvalidParameter,
            com.ericsson.irp.NotificationIRPSystem.AlreadySubscribed,
            com.ericsson.irp.NotificationIRPSystem.AtLeastOneNotificationCategoryNotSupported);
   * </pre>
   */
  public java.lang.String attach_push (org.omg.CORBA.Object manager_reference, 
                                       int time_tick, 
                                       java.lang.String[] notification_category_set, 
                                       java.lang.String filter) throws com.ericsson.irp.NotificationIRPSystem.Attach, 
                                                                       com.ericsson.irp.NotificationIRPSystem.ParameterNotSupported, 
                                                                       com.ericsson.irp.NotificationIRPSystem.InvalidParameter, 
                                                                       com.ericsson.irp.NotificationIRPSystem.AlreadySubscribed, 
                                                                       com.ericsson.irp.NotificationIRPSystem.AtLeastOneNotificationCategoryNotSupported;

}
