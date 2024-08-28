package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::MappingFilter
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/MappingFilter:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface MappingFilter {
  ...
};
 * </pre>
 */
public class _MappingFilterStub extends org.omg.CORBA.portable.ObjectImpl implements com.ericsson.irp.CosNotifyFilter.MappingFilter {
  /**
	 * 
	 */
	private static final long serialVersionUID = -3984430725235195494L;
final public static Class<MappingFilterOperations> _opsClass = com.ericsson.irp.CosNotifyFilter.MappingFilterOperations.class;

  public java.lang.String[] _ids () {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:omg.org/CosNotifyFilter/MappingFilter:1.0"
  };

  /**
   * <pre>
   *   boolean match_typed (in org.omg.CosNotification.PropertySeq filterable_data,
                       out any result_to_set)
    raises (com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData);
   * </pre>
   */
  public boolean match_typed (org.omg.CosNotification.Property[] filterable_data, 
                              org.omg.CORBA.AnyHolder result_to_set) throws  com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        boolean _result;
        try {
          _output = this._request("match_typed", true);
          org.omg.CosNotification.PropertySeqHelper.write(_output, filterable_data);
          _input = this._invoke(_output);
          _result = _input.read_boolean();
          result_to_set.value = _input.read_any();
          return _result;
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
          if (_exception_id.equals(com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableDataHelper.id())) {
            throw             com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableDataHelper.read(_exception.getInputStream());
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("match_typed", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosNotifyFilter.MappingFilterOperations _self = (com.ericsson.irp.CosNotifyFilter.MappingFilterOperations)_so.servant;
        try {
          boolean _ret = _self.match_typed(filterable_data, result_to_set);
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
          return _ret;
        } catch (com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData x_1) {
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
   *   boolean match_structured (in org.omg.CosNotification.StructuredEvent filterable_data,
                            out any result_to_set)
    raises (com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData);
   * </pre>
   */
  public boolean match_structured (org.omg.CosNotification.StructuredEvent filterable_data, 
                                   org.omg.CORBA.AnyHolder result_to_set) throws  com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        boolean _result;
        try {
          _output = this._request("match_structured", true);
          if (filterable_data == null) {
            throw new org.omg.CORBA.BAD_PARAM("Invalid array length");
          }
          org.omg.CosNotification.StructuredEventHelper.write(_output, filterable_data);
          _input = this._invoke(_output);
          _result = _input.read_boolean();
          result_to_set.value = _input.read_any();
          return _result;
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
          if (_exception_id.equals(com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableDataHelper.id())) {
            throw             com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableDataHelper.read(_exception.getInputStream());
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("match_structured", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosNotifyFilter.MappingFilterOperations _self = (com.ericsson.irp.CosNotifyFilter.MappingFilterOperations)_so.servant;
        try {
          boolean _ret = _self.match_structured(filterable_data, result_to_set);
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
          return _ret;
        } catch (com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData x_1) {
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
   *   boolean match (in any filterable_data, out any result_to_set)
    raises (com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData);
   * </pre>
   */
  public boolean match (org.omg.CORBA.Any filterable_data, 
                        org.omg.CORBA.AnyHolder result_to_set) throws  com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        boolean _result;
        try {
          _output = this._request("match", true);
          _output.write_any((org.omg.CORBA.Any)filterable_data);
          _input = this._invoke(_output);
          _result = _input.read_boolean();
          result_to_set.value = _input.read_any();
          return _result;
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
          if (_exception_id.equals(com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableDataHelper.id())) {
            throw             com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableDataHelper.read(_exception.getInputStream());
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("match", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosNotifyFilter.MappingFilterOperations _self = (com.ericsson.irp.CosNotifyFilter.MappingFilterOperations)_so.servant;
        try {
          boolean _ret = _self.match(filterable_data, result_to_set);
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
          return _ret;
        } catch (com.ericsson.irp.CosNotifyFilter.UnsupportedFilterableData x_1) {
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
        final com.ericsson.irp.CosNotifyFilter.MappingFilterOperations _self = (com.ericsson.irp.CosNotifyFilter.MappingFilterOperations)_so.servant;
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
   *   void remove_all_mapping_constraints ();
   * </pre>
   */
  public void remove_all_mapping_constraints () {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        try {
          _output = this._request("remove_all_mapping_constraints", true);
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("remove_all_mapping_constraints", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosNotifyFilter.MappingFilterOperations _self = (com.ericsson.irp.CosNotifyFilter.MappingFilterOperations)_so.servant;
        try {
          _self.remove_all_mapping_constraints();
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
   *   com.ericsson.irp.CosNotifyFilter.MappingConstraintInfoSeq get_all_mapping_constraints ();
   * </pre>
   */
  public com.ericsson.irp.CosNotifyFilter.MappingConstraintInfo[] get_all_mapping_constraints () {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        com.ericsson.irp.CosNotifyFilter.MappingConstraintInfo[] _result;
        try {
          _output = this._request("get_all_mapping_constraints", true);
          _input = this._invoke(_output);
          _result = com.ericsson.irp.CosNotifyFilter.MappingConstraintInfoSeqHelper.read(_input);
          return _result;
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("get_all_mapping_constraints", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosNotifyFilter.MappingFilterOperations _self = (com.ericsson.irp.CosNotifyFilter.MappingFilterOperations)_so.servant;
        try {
          com.ericsson.irp.CosNotifyFilter.MappingConstraintInfo[] _ret = _self.get_all_mapping_constraints();
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
          return _ret;

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
   *   com.ericsson.irp.CosNotifyFilter.MappingConstraintInfoSeq get_mapping_constraints (in com.ericsson.irp.CosNotifyFilter.ConstraintIDSeq id_list)
    raises (com.ericsson.irp.CosNotifyFilter.ConstraintNotFound);
   * </pre>
   */
  public com.ericsson.irp.CosNotifyFilter.MappingConstraintInfo[] get_mapping_constraints (int[] id_list) throws  com.ericsson.irp.CosNotifyFilter.ConstraintNotFound {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        com.ericsson.irp.CosNotifyFilter.MappingConstraintInfo[] _result;
        try {
          _output = this._request("get_mapping_constraints", true);
          com.ericsson.irp.CosNotifyFilter.ConstraintIDSeqHelper.write(_output, id_list);
          _input = this._invoke(_output);
          _result = com.ericsson.irp.CosNotifyFilter.MappingConstraintInfoSeqHelper.read(_input);
          return _result;
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
          if (_exception_id.equals(com.ericsson.irp.CosNotifyFilter.ConstraintNotFoundHelper.id())) {
            throw             com.ericsson.irp.CosNotifyFilter.ConstraintNotFoundHelper.read(_exception.getInputStream());
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("get_mapping_constraints", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosNotifyFilter.MappingFilterOperations _self = (com.ericsson.irp.CosNotifyFilter.MappingFilterOperations)_so.servant;
        try {
          com.ericsson.irp.CosNotifyFilter.MappingConstraintInfo[] _ret = _self.get_mapping_constraints(id_list);
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
          return _ret;
        } catch (com.ericsson.irp.CosNotifyFilter.ConstraintNotFound x_1) {
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
   *   void modify_mapping_constraints (in com.ericsson.irp.CosNotifyFilter.ConstraintIDSeq del_list,
                                   in com.ericsson.irp.CosNotifyFilter.MappingConstraintInfoSeq modify_list)
    raises (com.ericsson.irp.CosNotifyFilter.InvalidConstraint,
            com.ericsson.irp.CosNotifyFilter.InvalidValue,
            com.ericsson.irp.CosNotifyFilter.ConstraintNotFound);
   * </pre>
   */
  public void modify_mapping_constraints (int[] del_list, 
                                          com.ericsson.irp.CosNotifyFilter.MappingConstraintInfo[] modify_list) throws  com.ericsson.irp.CosNotifyFilter.InvalidConstraint, 
                                                                                                                        com.ericsson.irp.CosNotifyFilter.InvalidValue, 
                                                                                                                        com.ericsson.irp.CosNotifyFilter.ConstraintNotFound {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        try {
          _output = this._request("modify_mapping_constraints", true);
          com.ericsson.irp.CosNotifyFilter.ConstraintIDSeqHelper.write(_output, del_list);
          com.ericsson.irp.CosNotifyFilter.MappingConstraintInfoSeqHelper.write(_output, modify_list);
          _input = this._invoke(_output);
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
          if (_exception_id.equals(com.ericsson.irp.CosNotifyFilter.InvalidConstraintHelper.id())) {
            throw             com.ericsson.irp.CosNotifyFilter.InvalidConstraintHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.CosNotifyFilter.InvalidValueHelper.id())) {
            throw             com.ericsson.irp.CosNotifyFilter.InvalidValueHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.CosNotifyFilter.ConstraintNotFoundHelper.id())) {
            throw             com.ericsson.irp.CosNotifyFilter.ConstraintNotFoundHelper.read(_exception.getInputStream());
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("modify_mapping_constraints", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosNotifyFilter.MappingFilterOperations _self = (com.ericsson.irp.CosNotifyFilter.MappingFilterOperations)_so.servant;
        try {
          _self.modify_mapping_constraints(del_list, modify_list);
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
        } catch (com.ericsson.irp.CosNotifyFilter.InvalidConstraint x_1) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_1);
          }
          throw x_1;
        } catch (com.ericsson.irp.CosNotifyFilter.InvalidValue x_2) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_2);
          }
          throw x_2;
        } catch (com.ericsson.irp.CosNotifyFilter.ConstraintNotFound x_3) {
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
   *   com.ericsson.irp.CosNotifyFilter.MappingConstraintInfoSeq add_mapping_constraints (in com.ericsson.irp.CosNotifyFilter.MappingConstraintPairSeq pair_list)
    raises (com.ericsson.irp.CosNotifyFilter.InvalidConstraint,
            com.ericsson.irp.CosNotifyFilter.InvalidValue);
   * </pre>
   */
  public com.ericsson.irp.CosNotifyFilter.MappingConstraintInfo[] add_mapping_constraints (com.ericsson.irp.CosNotifyFilter.MappingConstraintPair[] pair_list) throws  com.ericsson.irp.CosNotifyFilter.InvalidConstraint, 
                                                                                                                                                                       com.ericsson.irp.CosNotifyFilter.InvalidValue {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        com.ericsson.irp.CosNotifyFilter.MappingConstraintInfo[] _result;
        try {
          _output = this._request("add_mapping_constraints", true);
          com.ericsson.irp.CosNotifyFilter.MappingConstraintPairSeqHelper.write(_output, pair_list);
          _input = this._invoke(_output);
          _result = com.ericsson.irp.CosNotifyFilter.MappingConstraintInfoSeqHelper.read(_input);
          return _result;
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
          if (_exception_id.equals(com.ericsson.irp.CosNotifyFilter.InvalidConstraintHelper.id())) {
            throw             com.ericsson.irp.CosNotifyFilter.InvalidConstraintHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.CosNotifyFilter.InvalidValueHelper.id())) {
            throw             com.ericsson.irp.CosNotifyFilter.InvalidValueHelper.read(_exception.getInputStream());
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("add_mapping_constraints", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosNotifyFilter.MappingFilterOperations _self = (com.ericsson.irp.CosNotifyFilter.MappingFilterOperations)_so.servant;
        try {
          com.ericsson.irp.CosNotifyFilter.MappingConstraintInfo[] _ret = _self.add_mapping_constraints(pair_list);
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
          return _ret;
        } catch (com.ericsson.irp.CosNotifyFilter.InvalidConstraint x_1) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_1);
          }
          throw x_1;
        } catch (com.ericsson.irp.CosNotifyFilter.InvalidValue x_2) {
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
   *   readonly attribute any default_value;
   * </pre>
   */
  public org.omg.CORBA.Any default_value () {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        org.omg.CORBA.Any _result;
        try {
          _output = this._request("_get_default_value", true);
          _input = this._invoke(_output);
          _result = _input.read_any();
          return _result;
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("_get_default_value", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosNotifyFilter.MappingFilterOperations _self = (com.ericsson.irp.CosNotifyFilter.MappingFilterOperations)_so.servant;
        try {
          org.omg.CORBA.Any _ret = _self.default_value();
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
          return _ret;

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
   *   readonly attribute ::CORBA::TypeCode value_type;
   * </pre>
   */
  public org.omg.CORBA.TypeCode value_type () {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        org.omg.CORBA.TypeCode _result;
        try {
          _output = this._request("_get_value_type", true);
          _input = this._invoke(_output);
          _result = _input.read_TypeCode();
          return _result;
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("_get_value_type", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosNotifyFilter.MappingFilterOperations _self = (com.ericsson.irp.CosNotifyFilter.MappingFilterOperations)_so.servant;
        try {
          org.omg.CORBA.TypeCode _ret = _self.value_type();
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
          return _ret;

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
   *   readonly attribute string constraint_grammar;
   * </pre>
   */
  public java.lang.String constraint_grammar () {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        java.lang.String _result;
        try {
          _output = this._request("_get_constraint_grammar", true);
          _input = this._invoke(_output);
          _result = _input.read_string();
          return _result;
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("_get_constraint_grammar", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosNotifyFilter.MappingFilterOperations _self = (com.ericsson.irp.CosNotifyFilter.MappingFilterOperations)_so.servant;
        try {
          java.lang.String _ret = _self.constraint_grammar();
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
          return _ret;

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
