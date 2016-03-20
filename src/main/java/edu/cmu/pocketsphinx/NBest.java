/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package edu.cmu.pocketsphinx;

public class NBest {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected NBest(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(NBest obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        PocketSphinxJNI.delete_NBest(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setHypstr(String value) {
    PocketSphinxJNI.NBest_hypstr_set(swigCPtr, this, value);
  }

  public String getHypstr() {
    return PocketSphinxJNI.NBest_hypstr_get(swigCPtr, this);
  }

  public void setScore(int value) {
    PocketSphinxJNI.NBest_score_set(swigCPtr, this, value);
  }

  public int getScore() {
    return PocketSphinxJNI.NBest_score_get(swigCPtr, this);
  }

  public static NBest fromIter(SWIGTYPE_p_ps_nbest_t itor) {
    long cPtr = PocketSphinxJNI.NBest_fromIter(SWIGTYPE_p_ps_nbest_t.getCPtr(itor));
    return (cPtr == 0) ? null : new NBest(cPtr, false);
  }

  public Hypothesis hyp() {
    long cPtr = PocketSphinxJNI.NBest_hyp(swigCPtr, this);
    return (cPtr == 0) ? null : new Hypothesis(cPtr, true);
  }

  public NBest() {
    this(PocketSphinxJNI.new_nBest(), true);
  }

}
