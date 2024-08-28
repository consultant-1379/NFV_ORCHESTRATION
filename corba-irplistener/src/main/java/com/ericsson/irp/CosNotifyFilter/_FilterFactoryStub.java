package com.ericsson.irp.CosNotifyFilter;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNotifyFilter.idl"
 * <li> <b>IDL Name</b>      ::CosNotifyFilter::FilterFactory
 * <li> <b>Repository Id</b> IDL:omg.org/CosNotifyFilter/FilterFactory:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface FilterFactory {
  ...
};
 * </pre>
 */
public class _FilterFactoryStub extends org.omg.CORBA.portable.ObjectImpl implements com.ericsson.irp.CosNotifyFilter.FilterFactory {
  /**
	 * 
	 */
	private static final long serialVersionUID = 3486296643345182635L;
final public static Class<FilterFactoryOperations> _opsClass = com.ericsson.irp.CosNotifyFilter.FilterFactoryOperations.class;

  public java.lang.String[] _ids () {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:omg.org/CosNotifyFilter/FilterFactory:1.0"
  };

  /**
   * <pre>
   *   com.ericsson.irp.CosNotifyFilter.MappingFilter create_mapping_filter (in string constraint_grammar,
                                                                        in any default_value)
    raises (com.ericsson.irp.CosNotifyFilter.InvalidGrammar);
   * </pre>
   */
  public com.ericsson.irp.CosNotifyFilter.MappingFilter create_mapping_filter (java.lang.String constraint_grammar, 
                                                                               org.omg.CORBA.Any default_value) throws  com.ericsson.irp.CosNotifyFilter.InvalidGrammar {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        com.ericsson.irp.CosNotifyFilter.MappingFilter _result;
        try {
          _output = this._request("create_mapping_filter", true);
          _output.write_string((java.lang.String)constraint_grammar);
          _output.write_any((org.omg.CORBA.Any)default_value);
          _input = this._invoke(_output);
          _result = com.ericsson.irp.CosNotifyFilter.MappingFilterHelper.read(_input);
          return _result;
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
          if (_exception_id.equals(com.ericsson.irp.CosNotifyFilter.InvalidGrammarHelper.id())) {
            throw             com.ericsson.irp.CosNotifyFilter.InvalidGrammarHelper.read(_exception.getInputStream());
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("create_mapping_filter", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosNotifyFilter.FilterFactoryOperations _self = (com.ericsson.irp.CosNotifyFilter.FilterFactoryOperations)_so.servant;
        try {
          com.ericsson.irp.CosNotifyFilter.MappingFilter _ret = _self.create_mapping_filter(constraint_grammar, 
                                                                                            default_value);
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
          return _ret;
        } catch (com.ericsson.irp.CosNotifyFilter.InvalidGrammar x_1) {
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
   *   com.ericsson.irp.CosNotifyFilter.Filter create_filter (in string constraint_grammar)
    raises (com.ericsson.irp.CosNotifyFilter.InvalidGrammar);
   * </pre>
   */
  public com.ericsson.irp.CosNotifyFilter.Filter create_filter (java.lang.String constraint_grammar) throws  com.ericsson.irp.CosNotifyFilter.InvalidGrammar {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        com.ericsson.irp.CosNotifyFilter.Filter _result;
        try {
          _output = this._request("create_filter", true);
          _output.write_string((java.lang.String)constraint_grammar);
          _input = this._invoke(_output);
          _result = com.ericsson.irp.CosNotifyFilter.FilterHelper.read(_input);
          return _result;
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
          if (_exception_id.equals(com.ericsson.irp.CosNotifyFilter.InvalidGrammarHelper.id())) {
            throw             com.ericsson.irp.CosNotifyFilter.InvalidGrammarHelper.read(_exception.getInputStream());
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("create_filter", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosNotifyFilter.FilterFactoryOperations _self = (com.ericsson.irp.CosNotifyFilter.FilterFactoryOperations)_so.servant;
        try {
          com.ericsson.irp.CosNotifyFilter.Filter _ret = _self.create_filter(constraint_grammar);
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
          return _ret;
        } catch (com.ericsson.irp.CosNotifyFilter.InvalidGrammar x_1) {
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

}
