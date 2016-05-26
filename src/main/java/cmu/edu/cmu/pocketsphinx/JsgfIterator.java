/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package cmu.edu.cmu.pocketsphinx;

public class JsgfIterator implements java.util.Iterator<JsgfRule> {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected JsgfIterator(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(JsgfIterator obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        SphinxBaseJNI.delete_JsgfIterator(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }

  public void setPtr(SWIGTYPE_p_jsgf_rule_iter_t value) {
    SphinxBaseJNI.JsgfIterator_ptr_set(swigCPtr, this, SWIGTYPE_p_jsgf_rule_iter_t.getCPtr(value));
  }

  public SWIGTYPE_p_jsgf_rule_iter_t getPtr() {
    long cPtr = SphinxBaseJNI.JsgfIterator_ptr_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_jsgf_rule_iter_t(cPtr, false);
  }

  public JsgfIterator(SWIGTYPE_p_jsgf_rule_iter_t ptr) {
    this(SphinxBaseJNI.new_JsgfIterator(SWIGTYPE_p_jsgf_rule_iter_t.getCPtr(ptr)), true);
  }

  public JsgfRule next() {
    long cPtr = SphinxBaseJNI.JsgfIterator_next(swigCPtr, this);
    return (cPtr == 0) ? null : new JsgfRule(cPtr, true);
  }

  public boolean hasNext() {
    return SphinxBaseJNI.JsgfIterator_hasNext(swigCPtr, this);
  }

}