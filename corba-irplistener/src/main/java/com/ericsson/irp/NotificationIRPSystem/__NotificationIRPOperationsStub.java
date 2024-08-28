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
public class __NotificationIRPOperationsStub extends org.omg.CORBA.portable.ObjectImpl implements com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperations {
  /**
	 * 
	 */
	private static final long serialVersionUID = -674992559315011824L;
final public static Class<_NotificationIRPOperationsOperations> _opsClass = com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperationsOperations.class;

  public java.lang.String[] _ids () {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:3gppsa5.org/NotificationIRPSystem/NotificationIRPOperations:1.0"
  };

  /**
   * <pre>
   *   com.ericsson.irp.NotificationIRPConstDefs.NotificationCategorySet get_notification_categories (out com.ericsson.irp.NotificationIRPConstDefs.EventTypesSet event_type_list,
                                                                                                 out com.ericsson.irp.NotificationIRPConstDefs.ExtendedEventTypesSet extended_event_type_list)
    raises (com.ericsson.irp.NotificationIRPSystem.GetNotificationCategories,
            com.ericsson.irp.NotificationIRPSystem.OperationNotSupported);
   * </pre>
   */
  public java.lang.String[] get_notification_categories (com.ericsson.irp.NotificationIRPConstDefs.EventTypesSetHolder event_type_list, 
                                                         com.ericsson.irp.NotificationIRPConstDefs.ExtendedEventTypesSetHolder extended_event_type_list) throws  com.ericsson.irp.NotificationIRPSystem.GetNotificationCategories, 
                                                                                                                                                                 com.ericsson.irp.NotificationIRPSystem.OperationNotSupported {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        java.lang.String[] _result;
        try {
          _output = this._request("get_notification_categories", true);
          _input = this._invoke(_output);
          _result = com.ericsson.irp.NotificationIRPConstDefs.NotificationCategorySetHelper.read(_input);
          event_type_list.value = com.ericsson.irp.NotificationIRPConstDefs.EventTypesSetHelper.read(_input);
          extended_event_type_list.value = com.ericsson.irp.NotificationIRPConstDefs.ExtendedEventTypesSetHelper.read(_input);
          return _result;
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
          if (_exception_id.equals(com.ericsson.irp.NotificationIRPSystem.GetNotificationCategoriesHelper.id())) {
            throw             com.ericsson.irp.NotificationIRPSystem.GetNotificationCategoriesHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.NotificationIRPSystem.OperationNotSupportedHelper.id())) {
            throw             com.ericsson.irp.NotificationIRPSystem.OperationNotSupportedHelper.read(_exception.getInputStream());
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("get_notification_categories", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperationsOperations _self = (com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperationsOperations)_so.servant;
        try {
          java.lang.String[] _ret = _self.get_notification_categories(event_type_list, 
                                                                      extended_event_type_list);
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
          return _ret;
        } catch (com.ericsson.irp.NotificationIRPSystem.GetNotificationCategories x_1) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_1);
          }
          throw x_1;
        } catch (com.ericsson.irp.NotificationIRPSystem.OperationNotSupported x_2) {
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

  /**
   * <pre>
   *   void change_subscription_filter (in string subscription_id, in string filter)
    raises (com.ericsson.irp.NotificationIRPSystem.ChangeSubscriptionFilter,
            com.ericsson.irp.NotificationIRPSystem.OperationNotSupported,
            com.ericsson.irp.NotificationIRPSystem.InvalidParameter);
   * </pre>
   */
  public void change_subscription_filter (java.lang.String subscription_id, 
                                          java.lang.String filter) throws  com.ericsson.irp.NotificationIRPSystem.ChangeSubscriptionFilter, 
                                                                           com.ericsson.irp.NotificationIRPSystem.OperationNotSupported, 
                                                                           com.ericsson.irp.NotificationIRPSystem.InvalidParameter {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        try {
          _output = this._request("change_subscription_filter", true);
          _output.write_string((java.lang.String)subscription_id);
          _output.write_string((java.lang.String)filter);
          _input = this._invoke(_output);
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
          if (_exception_id.equals(com.ericsson.irp.NotificationIRPSystem.ChangeSubscriptionFilterHelper.id())) {
            throw             com.ericsson.irp.NotificationIRPSystem.ChangeSubscriptionFilterHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.NotificationIRPSystem.OperationNotSupportedHelper.id())) {
            throw             com.ericsson.irp.NotificationIRPSystem.OperationNotSupportedHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.NotificationIRPSystem.InvalidParameterHelper.id())) {
            throw             com.ericsson.irp.NotificationIRPSystem.InvalidParameterHelper.read(_exception.getInputStream());
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("change_subscription_filter", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperationsOperations _self = (com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperationsOperations)_so.servant;
        try {
          _self.change_subscription_filter(subscription_id, filter);
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
        } catch (com.ericsson.irp.NotificationIRPSystem.ChangeSubscriptionFilter x_1) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_1);
          }
          throw x_1;
        } catch (com.ericsson.irp.NotificationIRPSystem.OperationNotSupported x_2) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_2);
          }
          throw x_2;
        } catch (com.ericsson.irp.NotificationIRPSystem.InvalidParameter x_3) {
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
      break;
    }

  }

  /**
   * <pre>
   *   com.ericsson.irp.NotificationIRPConstDefs.SubscriptionIdSet get_subscription_ids (in Object manager_reference)
    raises (com.ericsson.irp.NotificationIRPSystem.GetSubscriptionIds,
            com.ericsson.irp.NotificationIRPSystem.OperationNotSupported,
            com.ericsson.irp.NotificationIRPSystem.InvalidParameter);
   * </pre>
   */
  public java.lang.String[] get_subscription_ids (org.omg.CORBA.Object manager_reference) throws  com.ericsson.irp.NotificationIRPSystem.GetSubscriptionIds, 
                                                                                                  com.ericsson.irp.NotificationIRPSystem.OperationNotSupported, 
                                                                                                  com.ericsson.irp.NotificationIRPSystem.InvalidParameter {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        java.lang.String[] _result;
        try {
          _output = this._request("get_subscription_ids", true);
          _output.write_Object((org.omg.CORBA.Object)manager_reference);
          _input = this._invoke(_output);
          _result = com.ericsson.irp.NotificationIRPConstDefs.SubscriptionIdSetHelper.read(_input);
          return _result;
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
          if (_exception_id.equals(com.ericsson.irp.NotificationIRPSystem.GetSubscriptionIdsHelper.id())) {
            throw             com.ericsson.irp.NotificationIRPSystem.GetSubscriptionIdsHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.NotificationIRPSystem.OperationNotSupportedHelper.id())) {
            throw             com.ericsson.irp.NotificationIRPSystem.OperationNotSupportedHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.NotificationIRPSystem.InvalidParameterHelper.id())) {
            throw             com.ericsson.irp.NotificationIRPSystem.InvalidParameterHelper.read(_exception.getInputStream());
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("get_subscription_ids", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperationsOperations _self = (com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperationsOperations)_so.servant;
        try {
          java.lang.String[] _ret = _self.get_subscription_ids(manager_reference);
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
          return _ret;
        } catch (com.ericsson.irp.NotificationIRPSystem.GetSubscriptionIds x_1) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_1);
          }
          throw x_1;
        } catch (com.ericsson.irp.NotificationIRPSystem.OperationNotSupported x_2) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_2);
          }
          throw x_2;
        } catch (com.ericsson.irp.NotificationIRPSystem.InvalidParameter x_3) {
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
                                                     org.omg.CORBA.IntHolder time_tick) throws  com.ericsson.irp.NotificationIRPSystem.GetSubscriptionStatus, 
                                                                                                com.ericsson.irp.NotificationIRPSystem.OperationNotSupported, 
                                                                                                com.ericsson.irp.NotificationIRPSystem.InvalidParameter {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        java.lang.String[] _result;
        try {
          _output = this._request("get_subscription_status", true);
          _output.write_string((java.lang.String)subscription_id);
          _input = this._invoke(_output);
          _result = com.ericsson.irp.NotificationIRPConstDefs.NotificationCategorySetHelper.read(_input);
          filter_in_effect.value = _input.read_string();
          subscription_state.value = com.ericsson.irp.NotificationIRPConstDefs.SubscriptionStateHelper.read(_input);
          time_tick.value = _input.read_long();
          return _result;
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
          if (_exception_id.equals(com.ericsson.irp.NotificationIRPSystem.GetSubscriptionStatusHelper.id())) {
            throw             com.ericsson.irp.NotificationIRPSystem.GetSubscriptionStatusHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.NotificationIRPSystem.OperationNotSupportedHelper.id())) {
            throw             com.ericsson.irp.NotificationIRPSystem.OperationNotSupportedHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.NotificationIRPSystem.InvalidParameterHelper.id())) {
            throw             com.ericsson.irp.NotificationIRPSystem.InvalidParameterHelper.read(_exception.getInputStream());
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("get_subscription_status", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperationsOperations _self = (com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperationsOperations)_so.servant;
        try {
          java.lang.String[] _ret = _self.get_subscription_status(subscription_id, 
                                                                  filter_in_effect, 
                                                                  subscription_state, 
                                                                  time_tick);
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
          return _ret;
        } catch (com.ericsson.irp.NotificationIRPSystem.GetSubscriptionStatus x_1) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_1);
          }
          throw x_1;
        } catch (com.ericsson.irp.NotificationIRPSystem.OperationNotSupported x_2) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_2);
          }
          throw x_2;
        } catch (com.ericsson.irp.NotificationIRPSystem.InvalidParameter x_3) {
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
   *   com.ericsson.irp.CommonIRPConstDefs.VersionNumberSet get_notification_IRP_version ()
    raises (com.ericsson.irp.NotificationIRPSystem.GetNotificationIRPVersion);
   * </pre>
   */
  public java.lang.String[] get_notification_IRP_version () throws  com.ericsson.irp.NotificationIRPSystem.GetNotificationIRPVersion {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        java.lang.String[] _result;
        try {
          _output = this._request("get_notification_IRP_version", true);
          _input = this._invoke(_output);
          _result = com.ericsson.irp.CommonIRPConstDefs.VersionNumberSetHelper.read(_input);
          return _result;
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
          if (_exception_id.equals(com.ericsson.irp.NotificationIRPSystem.GetNotificationIRPVersionHelper.id())) {
            throw             com.ericsson.irp.NotificationIRPSystem.GetNotificationIRPVersionHelper.read(_exception.getInputStream());
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("get_notification_IRP_version", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperationsOperations _self = (com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperationsOperations)_so.servant;
        try {
          java.lang.String[] _ret = _self.get_notification_IRP_version();
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
          return _ret;
        } catch (com.ericsson.irp.NotificationIRPSystem.GetNotificationIRPVersion x_1) {
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
   *   void detach (in Object manager_reference, in string subscription_id)
    raises (com.ericsson.irp.NotificationIRPSystem.DetachException,
            com.ericsson.irp.NotificationIRPSystem.InvalidParameter);
   * </pre>
   */
  public void detach (org.omg.CORBA.Object manager_reference, 
                      java.lang.String subscription_id) throws  com.ericsson.irp.NotificationIRPSystem.DetachException, 
                                                                com.ericsson.irp.NotificationIRPSystem.InvalidParameter {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        try {
          _output = this._request("detach", true);
          _output.write_Object((org.omg.CORBA.Object)manager_reference);
          _output.write_string((java.lang.String)subscription_id);
          _input = this._invoke(_output);
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
          if (_exception_id.equals(com.ericsson.irp.NotificationIRPSystem.DetachExceptionHelper.id())) {
            throw             com.ericsson.irp.NotificationIRPSystem.DetachExceptionHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.NotificationIRPSystem.InvalidParameterHelper.id())) {
            throw             com.ericsson.irp.NotificationIRPSystem.InvalidParameterHelper.read(_exception.getInputStream());
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("detach", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperationsOperations _self = (com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperationsOperations)_so.servant;
        try {
          _self.detach(manager_reference, subscription_id);
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
        } catch (com.ericsson.irp.NotificationIRPSystem.DetachException x_1) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_1);
          }
          throw x_1;
        } catch (com.ericsson.irp.NotificationIRPSystem.InvalidParameter x_2) {
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
      break;
    }

  }

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
                                       org.omg.CosNotifyChannelAdmin.SequenceProxyPullSupplierHolder system_reference) throws  com.ericsson.irp.NotificationIRPSystem.Attach, 
                                                                                                                               com.ericsson.irp.NotificationIRPSystem.OperationNotSupported, 
                                                                                                                               com.ericsson.irp.NotificationIRPSystem.ParameterNotSupported, 
                                                                                                                               com.ericsson.irp.NotificationIRPSystem.InvalidParameter, 
                                                                                                                               com.ericsson.irp.NotificationIRPSystem.AlreadySubscribed, 
                                                                                                                               com.ericsson.irp.NotificationIRPSystem.AtLeastOneNotificationCategoryNotSupported {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        java.lang.String _result;
        try {
          _output = this._request("attach_pull", true);
          _output.write_Object((org.omg.CORBA.Object)manager_reference);
          _output.write_long((int)time_tick);
          com.ericsson.irp.NotificationIRPConstDefs.NotificationCategorySetHelper.write(_output, notification_category_set);
          _output.write_string((java.lang.String)filter);
          _input = this._invoke(_output);
          _result = _input.read_string();
          system_reference.value = org.omg.CosNotifyChannelAdmin.SequenceProxyPullSupplierHelper.read(_input);
          return _result;
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
          if (_exception_id.equals(com.ericsson.irp.NotificationIRPSystem.AttachHelper.id())) {
            throw             com.ericsson.irp.NotificationIRPSystem.AttachHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.NotificationIRPSystem.OperationNotSupportedHelper.id())) {
            throw             com.ericsson.irp.NotificationIRPSystem.OperationNotSupportedHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.NotificationIRPSystem.ParameterNotSupportedHelper.id())) {
            throw             com.ericsson.irp.NotificationIRPSystem.ParameterNotSupportedHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.NotificationIRPSystem.InvalidParameterHelper.id())) {
            throw             com.ericsson.irp.NotificationIRPSystem.InvalidParameterHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.NotificationIRPSystem.AlreadySubscribedHelper.id())) {
            throw             com.ericsson.irp.NotificationIRPSystem.AlreadySubscribedHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.NotificationIRPSystem.AtLeastOneNotificationCategoryNotSupportedHelper.id())) {
            throw             com.ericsson.irp.NotificationIRPSystem.AtLeastOneNotificationCategoryNotSupportedHelper.read(_exception.getInputStream());
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("attach_pull", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperationsOperations _self = (com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperationsOperations)_so.servant;
        try {
          java.lang.String _ret = _self.attach_pull(manager_reference, time_tick, 
                                                    notification_category_set, 
                                                    filter, system_reference);
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
          return _ret;
        } catch (com.ericsson.irp.NotificationIRPSystem.Attach x_1) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_1);
          }
          throw x_1;
        } catch (com.ericsson.irp.NotificationIRPSystem.OperationNotSupported x_2) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_2);
          }
          throw x_2;
        } catch (com.ericsson.irp.NotificationIRPSystem.ParameterNotSupported x_3) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_3);
          }
          throw x_3;
        } catch (com.ericsson.irp.NotificationIRPSystem.InvalidParameter x_4) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_4);
          }
          throw x_4;
        } catch (com.ericsson.irp.NotificationIRPSystem.AlreadySubscribed x_5) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_5);
          }
          throw x_5;
        } catch (com.ericsson.irp.NotificationIRPSystem.AtLeastOneNotificationCategoryNotSupported x_6) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_6);
          }
          throw x_6;

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
                                         org.omg.CosNotifyChannelAdmin.SequenceProxyPushSupplierHolder system_reference) throws  com.ericsson.irp.NotificationIRPSystem.Attach, 
                                                                                                                                 com.ericsson.irp.NotificationIRPSystem.OperationNotSupported, 
                                                                                                                                 com.ericsson.irp.NotificationIRPSystem.ParameterNotSupported, 
                                                                                                                                 com.ericsson.irp.NotificationIRPSystem.InvalidParameter, 
                                                                                                                                 com.ericsson.irp.NotificationIRPSystem.AlreadySubscribed, 
                                                                                                                                 com.ericsson.irp.NotificationIRPSystem.AtLeastOneNotificationCategoryNotSupported {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        java.lang.String _result;
        try {
          _output = this._request("attach_push_b", true);
          _output.write_Object((org.omg.CORBA.Object)manager_reference);
          _output.write_long((int)time_tick);
          com.ericsson.irp.NotificationIRPConstDefs.NotificationCategorySetHelper.write(_output, notification_category_set);
          _output.write_string((java.lang.String)filter);
          _input = this._invoke(_output);
          _result = _input.read_string();
          system_reference.value = org.omg.CosNotifyChannelAdmin.SequenceProxyPushSupplierHelper.read(_input);
          return _result;
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
          if (_exception_id.equals(com.ericsson.irp.NotificationIRPSystem.AttachHelper.id())) {
            throw             com.ericsson.irp.NotificationIRPSystem.AttachHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.NotificationIRPSystem.OperationNotSupportedHelper.id())) {
            throw             com.ericsson.irp.NotificationIRPSystem.OperationNotSupportedHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.NotificationIRPSystem.ParameterNotSupportedHelper.id())) {
            throw             com.ericsson.irp.NotificationIRPSystem.ParameterNotSupportedHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.NotificationIRPSystem.InvalidParameterHelper.id())) {
            throw             com.ericsson.irp.NotificationIRPSystem.InvalidParameterHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.NotificationIRPSystem.AlreadySubscribedHelper.id())) {
            throw             com.ericsson.irp.NotificationIRPSystem.AlreadySubscribedHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.NotificationIRPSystem.AtLeastOneNotificationCategoryNotSupportedHelper.id())) {
            throw             com.ericsson.irp.NotificationIRPSystem.AtLeastOneNotificationCategoryNotSupportedHelper.read(_exception.getInputStream());
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("attach_push_b", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperationsOperations _self = (com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperationsOperations)_so.servant;
        try {
          java.lang.String _ret = _self.attach_push_b(manager_reference, time_tick, 
                                                      notification_category_set, 
                                                      filter, system_reference);
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
          return _ret;
        } catch (com.ericsson.irp.NotificationIRPSystem.Attach x_1) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_1);
          }
          throw x_1;
        } catch (com.ericsson.irp.NotificationIRPSystem.OperationNotSupported x_2) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_2);
          }
          throw x_2;
        } catch (com.ericsson.irp.NotificationIRPSystem.ParameterNotSupported x_3) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_3);
          }
          throw x_3;
        } catch (com.ericsson.irp.NotificationIRPSystem.InvalidParameter x_4) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_4);
          }
          throw x_4;
        } catch (com.ericsson.irp.NotificationIRPSystem.AlreadySubscribed x_5) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_5);
          }
          throw x_5;
        } catch (com.ericsson.irp.NotificationIRPSystem.AtLeastOneNotificationCategoryNotSupported x_6) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_6);
          }
          throw x_6;

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
                                       java.lang.String filter) throws  com.ericsson.irp.NotificationIRPSystem.Attach, 
                                                                        com.ericsson.irp.NotificationIRPSystem.ParameterNotSupported, 
                                                                        com.ericsson.irp.NotificationIRPSystem.InvalidParameter, 
                                                                        com.ericsson.irp.NotificationIRPSystem.AlreadySubscribed, 
                                                                        com.ericsson.irp.NotificationIRPSystem.AtLeastOneNotificationCategoryNotSupported {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        java.lang.String _result;
        try {
          _output = this._request("attach_push", true);
          _output.write_Object((org.omg.CORBA.Object)manager_reference);
          _output.write_long((int)time_tick);
          com.ericsson.irp.NotificationIRPConstDefs.NotificationCategorySetHelper.write(_output, notification_category_set);
          _output.write_string((java.lang.String)filter);
          _input = this._invoke(_output);
          _result = _input.read_string();
          return _result;
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
          if (_exception_id.equals(com.ericsson.irp.NotificationIRPSystem.AttachHelper.id())) {
            throw             com.ericsson.irp.NotificationIRPSystem.AttachHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.NotificationIRPSystem.ParameterNotSupportedHelper.id())) {
            throw             com.ericsson.irp.NotificationIRPSystem.ParameterNotSupportedHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.NotificationIRPSystem.InvalidParameterHelper.id())) {
            throw             com.ericsson.irp.NotificationIRPSystem.InvalidParameterHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.NotificationIRPSystem.AlreadySubscribedHelper.id())) {
            throw             com.ericsson.irp.NotificationIRPSystem.AlreadySubscribedHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.NotificationIRPSystem.AtLeastOneNotificationCategoryNotSupportedHelper.id())) {
            throw             com.ericsson.irp.NotificationIRPSystem.AtLeastOneNotificationCategoryNotSupportedHelper.read(_exception.getInputStream());
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("attach_push", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperationsOperations _self = (com.ericsson.irp.NotificationIRPSystem._NotificationIRPOperationsOperations)_so.servant;
        try {
          java.lang.String _ret = _self.attach_push(manager_reference, time_tick, 
                                                    notification_category_set, 
                                                    filter);
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
          return _ret;
        } catch (com.ericsson.irp.NotificationIRPSystem.Attach x_1) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_1);
          }
          throw x_1;
        } catch (com.ericsson.irp.NotificationIRPSystem.ParameterNotSupported x_2) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_2);
          }
          throw x_2;
        } catch (com.ericsson.irp.NotificationIRPSystem.InvalidParameter x_3) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_3);
          }
          throw x_3;
        } catch (com.ericsson.irp.NotificationIRPSystem.AlreadySubscribed x_4) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_4);
          }
          throw x_4;
        } catch (com.ericsson.irp.NotificationIRPSystem.AtLeastOneNotificationCategoryNotSupported x_5) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_5);
          }
          throw x_5;

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
