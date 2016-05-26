/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package cmu.edu.cmu.pocketsphinx;

public class Jsgf implements Iterable<JsgfRule> {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected Jsgf(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(Jsgf obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        SphinxBaseJNI.delete_Jsgf(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public JsgfIterator iterator() {
    long cPtr = SphinxBaseJNI.Jsgf_iterator(swigCPtr, this);
    return (cPtr == 0) ? null : new JsgfIterator(cPtr, true);
  }

  public Jsgf(String path) {
    this(SphinxBaseJNI.new_Jsgf(path), true);
  }

  public String name() {
    return SphinxBaseJNI.Jsgf_name(swigCPtr, this);
  }

  public JsgfRule getRule(String name) {
    long cPtr = SphinxBaseJNI.Jsgf_getRule(swigCPtr, this, name);
    return (cPtr == 0) ? null : new JsgfRule(cPtr, false);
  }

  public FsgModel buildFsg(JsgfRule rule, LogMath logmath, float lw) {
    long cPtr = SphinxBaseJNI.Jsgf_buildFsg(swigCPtr, this, JsgfRule.getCPtr(rule), rule, LogMath.getCPtr(logmath), logmath, lw);
    return (cPtr == 0) ? null : new FsgModel(cPtr, false);
  }

}