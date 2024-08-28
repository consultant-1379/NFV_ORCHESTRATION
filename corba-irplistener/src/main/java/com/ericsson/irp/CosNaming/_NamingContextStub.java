package com.ericsson.irp.CosNaming;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNaming.idl"
 * <li> <b>IDL Name</b>      ::CosNaming::NamingContext
 * <li> <b>Repository Id</b> IDL:omg.org/CosNaming/NamingContext:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface NamingContext {
  ...
};
 * </pre>
 */
public class _NamingContextStub extends org.omg.CORBA.portable.ObjectImpl implements com.ericsson.irp.CosNaming.NamingContext {
  /**
	 * 
	 */
	private static final long serialVersionUID = 732098365158991477L;
final public static Class<NamingContextOperations> _opsClass = com.ericsson.irp.CosNaming.NamingContextOperations.class;

  public java.lang.String[] _ids () {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:omg.org/CosNaming/NamingContext:1.0"
  };

  /**
   * <pre>
   *   void list (in unsigned long how_many,
             out com.ericsson.irp.CosNaming.BindingList bl,
             out com.ericsson.irp.CosNaming.BindingIterator bi);
   * </pre>
   */
  public void list (int how_many, 
                    com.ericsson.irp.CosNaming.BindingListHolder bl, 
                    com.ericsson.irp.CosNaming.BindingIteratorHolder bi) {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        try {
          _output = this._request("list", true);
          _output.write_ulong((int)how_many);
          _input = this._invoke(_output);
          bl.value = com.ericsson.irp.CosNaming.BindingListHelper.read(_input);
          bi.value = com.ericsson.irp.CosNaming.BindingIteratorHelper.read(_input);
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("list", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosNaming.NamingContextOperations _self = (com.ericsson.irp.CosNaming.NamingContextOperations)_so.servant;
        try {
          _self.list(how_many, bl, bi);
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
   *   void destroy ()
    raises (com.ericsson.irp.CosNaming.NamingContextPackage.NotEmpty);
   * </pre>
   */
  public void destroy () throws  com.ericsson.irp.CosNaming.NamingContextPackage.NotEmpty {

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
          if (_exception_id.equals(com.ericsson.irp.CosNaming.NamingContextPackage.NotEmptyHelper.id())) {
            throw             com.ericsson.irp.CosNaming.NamingContextPackage.NotEmptyHelper.read(_exception.getInputStream());
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("destroy", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosNaming.NamingContextOperations _self = (com.ericsson.irp.CosNaming.NamingContextOperations)_so.servant;
        try {
          _self.destroy();
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
        } catch (com.ericsson.irp.CosNaming.NamingContextPackage.NotEmpty x_1) {
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
      break;
    }

  }

  /**
   * <pre>
   *   com.ericsson.irp.CosNaming.NamingContext bind_new_context (in com.ericsson.irp.CosNaming.Name n)
    raises (com.ericsson.irp.CosNaming.NamingContextPackage.NotFound,
            com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBound,
            com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed,
            com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName);
   * </pre>
   */
  public com.ericsson.irp.CosNaming.NamingContext bind_new_context (com.ericsson.irp.CosNaming.NameComponent[] n) throws  com.ericsson.irp.CosNaming.NamingContextPackage.NotFound, 
                                                                                                                          com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBound, 
                                                                                                                          com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed, 
                                                                                                                          com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        com.ericsson.irp.CosNaming.NamingContext _result;
        try {
          _output = this._request("bind_new_context", true);
          com.ericsson.irp.CosNaming.NameHelper.write(_output, n);
          _input = this._invoke(_output);
          _result = com.ericsson.irp.CosNaming.NamingContextHelper.read(_input);
          return _result;
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
          if (_exception_id.equals(com.ericsson.irp.CosNaming.NamingContextPackage.NotFoundHelper.id())) {
            throw             com.ericsson.irp.CosNaming.NamingContextPackage.NotFoundHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBoundHelper.id())) {
            throw             com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBoundHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceedHelper.id())) {
            throw             com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceedHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.CosNaming.NamingContextPackage.InvalidNameHelper.id())) {
            throw             com.ericsson.irp.CosNaming.NamingContextPackage.InvalidNameHelper.read(_exception.getInputStream());
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("bind_new_context", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosNaming.NamingContextOperations _self = (com.ericsson.irp.CosNaming.NamingContextOperations)_so.servant;
        try {
          com.ericsson.irp.CosNaming.NamingContext _ret = _self.bind_new_context(n);
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
          return _ret;
        } catch (com.ericsson.irp.CosNaming.NamingContextPackage.NotFound x_1) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_1);
          }
          throw x_1;
        } catch (com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBound x_2) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_2);
          }
          throw x_2;
        } catch (com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed x_3) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_3);
          }
          throw x_3;
        } catch (com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName x_4) {
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
    }

  }

  /**
   * <pre>
   *   com.ericsson.irp.CosNaming.NamingContext new_context ();
   * </pre>
   */
  public com.ericsson.irp.CosNaming.NamingContext new_context () {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        com.ericsson.irp.CosNaming.NamingContext _result;
        try {
          _output = this._request("new_context", true);
          _input = this._invoke(_output);
          _result = com.ericsson.irp.CosNaming.NamingContextHelper.read(_input);
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("new_context", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosNaming.NamingContextOperations _self = (com.ericsson.irp.CosNaming.NamingContextOperations)_so.servant;
        try {
          com.ericsson.irp.CosNaming.NamingContext _ret = _self.new_context();
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
   *   void unbind (in com.ericsson.irp.CosNaming.Name n)
    raises (com.ericsson.irp.CosNaming.NamingContextPackage.NotFound,
            com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed,
            com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName);
   * </pre>
   */
  public void unbind (com.ericsson.irp.CosNaming.NameComponent[] n) throws  com.ericsson.irp.CosNaming.NamingContextPackage.NotFound, 
                                                                            com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed, 
                                                                            com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        try {
          _output = this._request("unbind", true);
          com.ericsson.irp.CosNaming.NameHelper.write(_output, n);
          _input = this._invoke(_output);
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
          if (_exception_id.equals(com.ericsson.irp.CosNaming.NamingContextPackage.NotFoundHelper.id())) {
            throw             com.ericsson.irp.CosNaming.NamingContextPackage.NotFoundHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceedHelper.id())) {
            throw             com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceedHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.CosNaming.NamingContextPackage.InvalidNameHelper.id())) {
            throw             com.ericsson.irp.CosNaming.NamingContextPackage.InvalidNameHelper.read(_exception.getInputStream());
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("unbind", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosNaming.NamingContextOperations _self = (com.ericsson.irp.CosNaming.NamingContextOperations)_so.servant;
        try {
          _self.unbind(n);
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
        } catch (com.ericsson.irp.CosNaming.NamingContextPackage.NotFound x_1) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_1);
          }
          throw x_1;
        } catch (com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed x_2) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_2);
          }
          throw x_2;
        } catch (com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName x_3) {
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
   *   Object resolve (in com.ericsson.irp.CosNaming.Name n)
    raises (com.ericsson.irp.CosNaming.NamingContextPackage.NotFound,
            com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed,
            com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName);
   * </pre>
   */
  public org.omg.CORBA.Object resolve (com.ericsson.irp.CosNaming.NameComponent[] n) throws  com.ericsson.irp.CosNaming.NamingContextPackage.NotFound, 
                                                                                             com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed, 
                                                                                             com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        org.omg.CORBA.Object _result;
        try {
          _output = this._request("resolve", true);
          com.ericsson.irp.CosNaming.NameHelper.write(_output, n);
          _input = this._invoke(_output);
          _result = _input.read_Object();
          return _result;
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
          if (_exception_id.equals(com.ericsson.irp.CosNaming.NamingContextPackage.NotFoundHelper.id())) {
            throw             com.ericsson.irp.CosNaming.NamingContextPackage.NotFoundHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceedHelper.id())) {
            throw             com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceedHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.CosNaming.NamingContextPackage.InvalidNameHelper.id())) {
            throw             com.ericsson.irp.CosNaming.NamingContextPackage.InvalidNameHelper.read(_exception.getInputStream());
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("resolve", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosNaming.NamingContextOperations _self = (com.ericsson.irp.CosNaming.NamingContextOperations)_so.servant;
        try {
          org.omg.CORBA.Object _ret = _self.resolve(n);
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
          return _ret;
        } catch (com.ericsson.irp.CosNaming.NamingContextPackage.NotFound x_1) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_1);
          }
          throw x_1;
        } catch (com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed x_2) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_2);
          }
          throw x_2;
        } catch (com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName x_3) {
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
   *   void rebind_context (in com.ericsson.irp.CosNaming.Name n,
                       in com.ericsson.irp.CosNaming.NamingContext nc)
    raises (com.ericsson.irp.CosNaming.NamingContextPackage.NotFound,
            com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed,
            com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName);
   * </pre>
   */
  public void rebind_context (com.ericsson.irp.CosNaming.NameComponent[] n, 
                              com.ericsson.irp.CosNaming.NamingContext nc) throws  com.ericsson.irp.CosNaming.NamingContextPackage.NotFound, 
                                                                                   com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed, 
                                                                                   com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        try {
          _output = this._request("rebind_context", true);
          com.ericsson.irp.CosNaming.NameHelper.write(_output, n);
          com.ericsson.irp.CosNaming.NamingContextHelper.write(_output, nc);
          _input = this._invoke(_output);
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
          if (_exception_id.equals(com.ericsson.irp.CosNaming.NamingContextPackage.NotFoundHelper.id())) {
            throw             com.ericsson.irp.CosNaming.NamingContextPackage.NotFoundHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceedHelper.id())) {
            throw             com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceedHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.CosNaming.NamingContextPackage.InvalidNameHelper.id())) {
            throw             com.ericsson.irp.CosNaming.NamingContextPackage.InvalidNameHelper.read(_exception.getInputStream());
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("rebind_context", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosNaming.NamingContextOperations _self = (com.ericsson.irp.CosNaming.NamingContextOperations)_so.servant;
        try {
          _self.rebind_context(n, nc);
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
        } catch (com.ericsson.irp.CosNaming.NamingContextPackage.NotFound x_1) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_1);
          }
          throw x_1;
        } catch (com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed x_2) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_2);
          }
          throw x_2;
        } catch (com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName x_3) {
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
   *   void bind_context (in com.ericsson.irp.CosNaming.Name n,
                     in com.ericsson.irp.CosNaming.NamingContext nc)
    raises (com.ericsson.irp.CosNaming.NamingContextPackage.NotFound,
            com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed,
            com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName,
            com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBound);
   * </pre>
   */
  public void bind_context (com.ericsson.irp.CosNaming.NameComponent[] n, 
                            com.ericsson.irp.CosNaming.NamingContext nc) throws  com.ericsson.irp.CosNaming.NamingContextPackage.NotFound, 
                                                                                 com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed, 
                                                                                 com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName, 
                                                                                 com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBound {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        try {
          _output = this._request("bind_context", true);
          com.ericsson.irp.CosNaming.NameHelper.write(_output, n);
          com.ericsson.irp.CosNaming.NamingContextHelper.write(_output, nc);
          _input = this._invoke(_output);
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
          if (_exception_id.equals(com.ericsson.irp.CosNaming.NamingContextPackage.NotFoundHelper.id())) {
            throw             com.ericsson.irp.CosNaming.NamingContextPackage.NotFoundHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceedHelper.id())) {
            throw             com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceedHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.CosNaming.NamingContextPackage.InvalidNameHelper.id())) {
            throw             com.ericsson.irp.CosNaming.NamingContextPackage.InvalidNameHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBoundHelper.id())) {
            throw             com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBoundHelper.read(_exception.getInputStream());
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("bind_context", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosNaming.NamingContextOperations _self = (com.ericsson.irp.CosNaming.NamingContextOperations)_so.servant;
        try {
          _self.bind_context(n, nc);
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
        } catch (com.ericsson.irp.CosNaming.NamingContextPackage.NotFound x_1) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_1);
          }
          throw x_1;
        } catch (com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed x_2) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_2);
          }
          throw x_2;
        } catch (com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName x_3) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_3);
          }
          throw x_3;
        } catch (com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBound x_4) {
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
   *   void rebind (in com.ericsson.irp.CosNaming.Name n, in Object obj)
    raises (com.ericsson.irp.CosNaming.NamingContextPackage.NotFound,
            com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed,
            com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName);
   * </pre>
   */
  public void rebind (com.ericsson.irp.CosNaming.NameComponent[] n, 
                      org.omg.CORBA.Object obj) throws  com.ericsson.irp.CosNaming.NamingContextPackage.NotFound, 
                                                        com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed, 
                                                        com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        try {
          _output = this._request("rebind", true);
          com.ericsson.irp.CosNaming.NameHelper.write(_output, n);
          _output.write_Object((org.omg.CORBA.Object)obj);
          _input = this._invoke(_output);
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
          if (_exception_id.equals(com.ericsson.irp.CosNaming.NamingContextPackage.NotFoundHelper.id())) {
            throw             com.ericsson.irp.CosNaming.NamingContextPackage.NotFoundHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceedHelper.id())) {
            throw             com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceedHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.CosNaming.NamingContextPackage.InvalidNameHelper.id())) {
            throw             com.ericsson.irp.CosNaming.NamingContextPackage.InvalidNameHelper.read(_exception.getInputStream());
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("rebind", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosNaming.NamingContextOperations _self = (com.ericsson.irp.CosNaming.NamingContextOperations)_so.servant;
        try {
          _self.rebind(n, obj);
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
        } catch (com.ericsson.irp.CosNaming.NamingContextPackage.NotFound x_1) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_1);
          }
          throw x_1;
        } catch (com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed x_2) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_2);
          }
          throw x_2;
        } catch (com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName x_3) {
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
   *   void bind (in com.ericsson.irp.CosNaming.Name n, in Object obj)
    raises (com.ericsson.irp.CosNaming.NamingContextPackage.NotFound,
            com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed,
            com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName,
            com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBound);
   * </pre>
   */
  public void bind (com.ericsson.irp.CosNaming.NameComponent[] n, 
                    org.omg.CORBA.Object obj) throws  com.ericsson.irp.CosNaming.NamingContextPackage.NotFound, 
                                                      com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed, 
                                                      com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName, 
                                                      com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBound {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        try {
          _output = this._request("bind", true);
          com.ericsson.irp.CosNaming.NameHelper.write(_output, n);
          _output.write_Object((org.omg.CORBA.Object)obj);
          _input = this._invoke(_output);
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          java.lang.String _exception_id = _exception.getId();
          if (_exception_id.equals(com.ericsson.irp.CosNaming.NamingContextPackage.NotFoundHelper.id())) {
            throw             com.ericsson.irp.CosNaming.NamingContextPackage.NotFoundHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceedHelper.id())) {
            throw             com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceedHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.CosNaming.NamingContextPackage.InvalidNameHelper.id())) {
            throw             com.ericsson.irp.CosNaming.NamingContextPackage.InvalidNameHelper.read(_exception.getInputStream());
          }
          if (_exception_id.equals(com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBoundHelper.id())) {
            throw             com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBoundHelper.read(_exception.getInputStream());
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("bind", _opsClass);
        if (_so == null) {
          continue;
        }
        final com.ericsson.irp.CosNaming.NamingContextOperations _self = (com.ericsson.irp.CosNaming.NamingContextOperations)_so.servant;
        try {
          _self.bind(n, obj);
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
          }
        } catch (com.ericsson.irp.CosNaming.NamingContextPackage.NotFound x_1) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_1);
          }
          throw x_1;
        } catch (com.ericsson.irp.CosNaming.NamingContextPackage.CannotProceed x_2) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_2);
          }
          throw x_2;
        } catch (com.ericsson.irp.CosNaming.NamingContextPackage.InvalidName x_3) {
          if (_so instanceof org.omg.CORBA.portable.ServantObjectExt) {
            ((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion((Throwable)x_3);
          }
          throw x_3;
        } catch (com.ericsson.irp.CosNaming.NamingContextPackage.AlreadyBound x_4) {
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

}
