/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package edu.cmu.pocketsphinx;

public class Hypothesis {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected Hypothesis(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(Hypothesis obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        PocketSphinxJNI.delete_Hypothesis(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setHypstr(String value) {
    PocketSphinxJNI.Hypothesis_hypstr_set(swigCPtr, this, value);
  }

  public String getHypstr() {
    return PocketSphinxJNI.Hypothesis_hypstr_get(swigCPtr, this);
  }

  public void setBestScore(int value) {
    PocketSphinxJNI.Hypothesis_bestScore_set(swigCPtr, this, value);
  }

  public int getBestScore() {
    return PocketSphinxJNI.Hypothesis_bestScore_get(swigCPtr, this);
  }

  public void setProb(int value) {
    PocketSphinxJNI.Hypothesis_prob_set(swigCPtr, this, value);
  }

  public int getProb() {
    return PocketSphinxJNI.Hypothesis_prob_get(swigCPtr, this);
  }

  public Hypothesis(String hypstr, int best_score, int prob) {
    this(PocketSphinxJNI.new_Hypothesis(hypstr, best_score, prob), true);
  }

}
