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
public abstract class _NotificationIRPOperationsPOA extends org.omg.PortableServer.Servant implements 
org.omg.CORBA.portable.InvokeHandler, com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperationsOperations {

  public com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperations _this () {
   return com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperationsHelper.narrow(super._this_object());
  }

  public com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperations _this (org.omg.CORBA.ORB orb) {
    return com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperationsHelper.narrow(super._this_object(orb));
  }

  public java.lang.String[] _all_interfaces (final org.omg.PortableServer.POA poa, final byte[] objectId) {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:3gppsa5.org/NotificationIRPSystem/NotificationIRPOperations:1.0"
  };

  private static java.util.Dictionary<String, int[]> _methods = new java.util.Hashtable<String, int[]>();

  static {
  _methods.put("attach_push", new int[] { 0, 0 });
  _methods.put("attach_push_b", new int[] { 0, 1 });
  _methods.put("attach_pull", new int[] { 0, 2 });
  _methods.put("detach", new int[] { 0, 3 });
  _methods.put("get_notification_IRP_version", new int[] { 0, 4 });
  _methods.put("get_subscription_status", new int[] { 0, 5 });
  _methods.put("get_subscription_ids", new int[] { 0, 6 });
  _methods.put("change_subscription_filter", new int[] { 0, 7 });
  _methods.put("get_notification_categories", new int[] { 0, 8 });
  }

  public org.omg.CORBA.portable.OutputStream _invoke (java.lang.String opName,
                                                      org.omg.CORBA.portable.InputStream _input,
                                                      org.omg.CORBA.portable.ResponseHandler handler) {
    int[] method = (int[]) _methods.get(opName);
    if (method == null) {
      throw new org.omg.CORBA.BAD_OPERATION();
    }
    switch (method[0]) {
      case 0: {
        return com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperationsPOA._invoke(this, method[1], _input, handler);
      }
    }
    throw new org.omg.CORBA.BAD_OPERATION(2, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
  }

  public static org.omg.CORBA.portable.OutputStream _invoke (com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperationsOperations _self,
                                                             int _method_id,
                                                             org.omg.CORBA.portable.InputStream _input,
                                                             org.omg.CORBA.portable.ResponseHandler _handler) {
  org.omg.CORBA.portable.OutputStream _output = null;
  {
    switch (_method_id) {
    case 0: {
    try {
      org.omg.CORBA.Object manager_reference;
      manager_reference = _input.read_Object();
      int time_tick;
      time_tick = _input.read_long();
      java.lang.String[] notification_category_set;
      notification_category_set = com.ericsson.irp.NotificationIRPConstDefs.NotificationCategorySetHelper.read(_input);
      java.lang.String filter;
      filter = _input.read_string();
      java.lang.String _result = _self.attach_push(manager_reference, time_tick, 
                                                   notification_category_set, filter);
      _output = _handler.createReply();
      _output.write_string((java.lang.String)_result);
    }
    catch (com.ericsson.irp.NotificationIRPSystem.Attach _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.NotificationIRPSystem.AttachHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.NotificationIRPSystem.ParameterNotSupported _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.NotificationIRPSystem.ParameterNotSupportedHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.NotificationIRPSystem.InvalidParameter _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.NotificationIRPSystem.InvalidParameterHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.NotificationIRPSystem.AlreadySubscribed _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.NotificationIRPSystem.AlreadySubscribedHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.NotificationIRPSystem.AtLeastOneNotificationCategoryNotSupported _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.NotificationIRPSystem.AtLeastOneNotificationCategoryNotSupportedHelper.write(_output, _exception);
    }
      return _output;
    }
    case 1: {
    try {
      org.omg.CORBA.Object manager_reference;
      manager_reference = _input.read_Object();
      int time_tick;
      time_tick = _input.read_long();
      java.lang.String[] notification_category_set;
      notification_category_set = com.ericsson.irp.NotificationIRPConstDefs.NotificationCategorySetHelper.read(_input);
      java.lang.String filter;
      filter = _input.read_string();
      org.omg.CosNotifyChannelAdmin.SequenceProxyPushSupplierHolder system_reference = new org.omg.CosNotifyChannelAdmin.SequenceProxyPushSupplierHolder();
      java.lang.String _result = _self.attach_push_b(manager_reference, time_tick, 
                                                     notification_category_set, 
                                                     filter, system_reference);
      _output = _handler.createReply();
      _output.write_string((java.lang.String)_result);
      org.omg.CosNotifyChannelAdmin.SequenceProxyPushSupplierHelper.write(_output, system_reference.value);
    }
    catch (com.ericsson.irp.NotificationIRPSystem.Attach _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.NotificationIRPSystem.AttachHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.NotificationIRPSystem.OperationNotSupported _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.NotificationIRPSystem.OperationNotSupportedHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.NotificationIRPSystem.ParameterNotSupported _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.NotificationIRPSystem.ParameterNotSupportedHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.NotificationIRPSystem.InvalidParameter _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.NotificationIRPSystem.InvalidParameterHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.NotificationIRPSystem.AlreadySubscribed _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.NotificationIRPSystem.AlreadySubscribedHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.NotificationIRPSystem.AtLeastOneNotificationCategoryNotSupported _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.NotificationIRPSystem.AtLeastOneNotificationCategoryNotSupportedHelper.write(_output, _exception);
    }
      return _output;
    }
    case 2: {
    try {
      org.omg.CORBA.Object manager_reference;
      manager_reference = _input.read_Object();
      int time_tick;
      time_tick = _input.read_long();
      java.lang.String[] notification_category_set;
      notification_category_set = com.ericsson.irp.NotificationIRPConstDefs.NotificationCategorySetHelper.read(_input);
      java.lang.String filter;
      filter = _input.read_string();
      org.omg.CosNotifyChannelAdmin.SequenceProxyPullSupplierHolder system_reference = new org.omg.CosNotifyChannelAdmin.SequenceProxyPullSupplierHolder();
      java.lang.String _result = _self.attach_pull(manager_reference, time_tick, 
                                                   notification_category_set, filter, 
                                                   system_reference);
      _output = _handler.createReply();
      _output.write_string((java.lang.String)_result);
      org.omg.CosNotifyChannelAdmin.SequenceProxyPullSupplierHelper.write(_output, system_reference.value);
    }
    catch (com.ericsson.irp.NotificationIRPSystem.Attach _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.NotificationIRPSystem.AttachHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.NotificationIRPSystem.OperationNotSupported _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.NotificationIRPSystem.OperationNotSupportedHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.NotificationIRPSystem.ParameterNotSupported _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.NotificationIRPSystem.ParameterNotSupportedHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.NotificationIRPSystem.InvalidParameter _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.NotificationIRPSystem.InvalidParameterHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.NotificationIRPSystem.AlreadySubscribed _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.NotificationIRPSystem.AlreadySubscribedHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.NotificationIRPSystem.AtLeastOneNotificationCategoryNotSupported _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.NotificationIRPSystem.AtLeastOneNotificationCategoryNotSupportedHelper.write(_output, _exception);
    }
      return _output;
    }
    case 3: {
    try {
      org.omg.CORBA.Object manager_reference;
      manager_reference = _input.read_Object();
      java.lang.String subscription_id;
      subscription_id = _input.read_string();
      _self.detach(manager_reference, subscription_id);
      _output = _handler.createReply();
    }
    catch (com.ericsson.irp.NotificationIRPSystem.DetachException _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.NotificationIRPSystem.DetachExceptionHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.NotificationIRPSystem.InvalidParameter _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.NotificationIRPSystem.InvalidParameterHelper.write(_output, _exception);
    }
      return _output;
    }
    case 4: {
    try {
      java.lang.String[] _result = _self.get_notification_IRP_version();
      _output = _handler.createReply();
      com.ericsson.irp.CommonIRPConstDefs.VersionNumberSetHelper.write(_output, _result);
    }
    catch (com.ericsson.irp.NotificationIRPSystem.GetNotificationIRPVersion _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.NotificationIRPSystem.GetNotificationIRPVersionHelper.write(_output, _exception);
    }
      return _output;
    }
    case 5: {
    try {
      java.lang.String subscription_id;
      subscription_id = _input.read_string();
      org.omg.CORBA.StringHolder filter_in_effect = new org.omg.CORBA.StringHolder();
      com.ericsson.irp.NotificationIRPConstDefs.SubscriptionStateHolder subscription_state = new com.ericsson.irp.NotificationIRPConstDefs.SubscriptionStateHolder();
      org.omg.CORBA.IntHolder time_tick = new org.omg.CORBA.IntHolder();
      java.lang.String[] _result = _self.get_subscription_status(subscription_id, 
                                                                 filter_in_effect, 
                                                                 subscription_state, 
                                                                 time_tick);
      _output = _handler.createReply();
      com.ericsson.irp.NotificationIRPConstDefs.NotificationCategorySetHelper.write(_output, _result);
      _output.write_string((java.lang.String)filter_in_effect.value);
      com.ericsson.irp.NotificationIRPConstDefs.SubscriptionStateHelper.write(_output, subscription_state.value);
      _output.write_long((int)time_tick.value);
    }
    catch (com.ericsson.irp.NotificationIRPSystem.GetSubscriptionStatus _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.NotificationIRPSystem.GetSubscriptionStatusHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.NotificationIRPSystem.OperationNotSupported _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.NotificationIRPSystem.OperationNotSupportedHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.NotificationIRPSystem.InvalidParameter _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.NotificationIRPSystem.InvalidParameterHelper.write(_output, _exception);
    }
      return _output;
    }
    case 6: {
    try {
      org.omg.CORBA.Object manager_reference;
      manager_reference = _input.read_Object();
      java.lang.String[] _result = _self.get_subscription_ids(manager_reference);
      _output = _handler.createReply();
      com.ericsson.irp.NotificationIRPConstDefs.SubscriptionIdSetHelper.write(_output, _result);
    }
    catch (com.ericsson.irp.NotificationIRPSystem.GetSubscriptionIds _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.NotificationIRPSystem.GetSubscriptionIdsHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.NotificationIRPSystem.OperationNotSupported _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.NotificationIRPSystem.OperationNotSupportedHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.NotificationIRPSystem.InvalidParameter _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.NotificationIRPSystem.InvalidParameterHelper.write(_output, _exception);
    }
      return _output;
    }
    case 7: {
    try {
      java.lang.String subscription_id;
      subscription_id = _input.read_string();
      java.lang.String filter;
      filter = _input.read_string();
      _self.change_subscription_filter(subscription_id, filter);
      _output = _handler.createReply();
    }
    catch (com.ericsson.irp.NotificationIRPSystem.ChangeSubscriptionFilter _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.NotificationIRPSystem.ChangeSubscriptionFilterHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.NotificationIRPSystem.OperationNotSupported _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.NotificationIRPSystem.OperationNotSupportedHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.NotificationIRPSystem.InvalidParameter _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.NotificationIRPSystem.InvalidParameterHelper.write(_output, _exception);
    }
      return _output;
    }
    case 8: {
    try {
      com.ericsson.irp.NotificationIRPConstDefs.EventTypesSetHolder event_type_list = new com.ericsson.irp.NotificationIRPConstDefs.EventTypesSetHolder();
      com.ericsson.irp.NotificationIRPConstDefs.ExtendedEventTypesSetHolder extended_event_type_list = new com.ericsson.irp.NotificationIRPConstDefs.ExtendedEventTypesSetHolder();
      java.lang.String[] _result = _self.get_notification_categories(event_type_list, 
                                                                     extended_event_type_list);
      _output = _handler.createReply();
      com.ericsson.irp.NotificationIRPConstDefs.NotificationCategorySetHelper.write(_output, _result);
      com.ericsson.irp.NotificationIRPConstDefs.EventTypesSetHelper.write(_output, event_type_list.value);
      com.ericsson.irp.NotificationIRPConstDefs.ExtendedEventTypesSetHelper.write(_output, extended_event_type_list.value);
    }
    catch (com.ericsson.irp.NotificationIRPSystem.GetNotificationCategories _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.NotificationIRPSystem.GetNotificationCategoriesHelper.write(_output, _exception);
    }
    catch (com.ericsson.irp.NotificationIRPSystem.OperationNotSupported _exception) {
      _output = _handler.createExceptionReply();
      com.ericsson.irp.NotificationIRPSystem.OperationNotSupportedHelper.write(_output, _exception);
    }
      return _output;
    }
    }
    throw new org.omg.CORBA.BAD_OPERATION(2, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
  }
  }
}
