/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package cmu.edu.cmu.pocketsphinx;

public class NBestList implements Iterable<NBest> {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected NBestList(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(NBestList obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        PocketSphinxJNI.delete_NBestList(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public NBestIterator iterator() {
    long cPtr = PocketSphinxJNI.NBestList_iterator(swigCPtr, this);
    return (cPtr == 0) ? null : new NBestIterator(cPtr, true);
  }

}