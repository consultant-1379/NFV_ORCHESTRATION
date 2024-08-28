package com.ericsson.irp.CosNaming;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "CosNaming.idl"
 * <li> <b>IDL Name</b>      ::CosNaming::BindingIterator
 * <li> <b>Repository Id</b> IDL:omg.org/CosNaming/BindingIterator:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface BindingIterator {
  ...
};
 * </pre>
 */
public interface BindingIteratorOperations {
  /**
   * <pre>
   *   void destroy ();
   * </pre>
   */
  public void destroy ();

  /**
   * <pre>
   *   boolean next_n (in unsigned long how_many,
                  out com.ericsson.irp.CosNaming.BindingList bl);
   * </pre>
   */
  public boolean next_n (int how_many, 
                         com.ericsson.irp.CosNaming.BindingListHolder bl);

  /**
   * <pre>
   *   boolean next_one (out com.ericsson.irp.CosNaming.Binding b);
   * </pre>
   */
  public boolean next_one (com.ericsson.irp.CosNaming.BindingHolder b);

}
