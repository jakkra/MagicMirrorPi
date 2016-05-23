// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/speech/v1/cloud-speech.proto

package com.google.cloud.speech.v1;

public interface RecognizeResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:google.cloud.speech.v1.RecognizeResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional .google.rpc.Status error = 1;</code>
   *
   * <pre>
   * [Output-only] If set, returns a [google.rpc.Status][] message that
   * specifies the error for the operation.
   * </pre>
   */
  boolean hasError();
  /**
   * <code>optional .google.rpc.Status error = 1;</code>
   *
   * <pre>
   * [Output-only] If set, returns a [google.rpc.Status][] message that
   * specifies the error for the operation.
   * </pre>
   */
  com.google.rpc.Status getError();
  /**
   * <code>optional .google.rpc.Status error = 1;</code>
   *
   * <pre>
   * [Output-only] If set, returns a [google.rpc.Status][] message that
   * specifies the error for the operation.
   * </pre>
   */
  com.google.rpc.StatusOrBuilder getErrorOrBuilder();

  /**
   * <code>repeated .google.cloud.speech.v1.SpeechRecognitionResult results = 2;</code>
   *
   * <pre>
   * [Output-only] May contain zero or one `is_final=true` result (the newly
   * settled portion). May also contain zero or more `is_final=false` results.
   * </pre>
   */
  java.util.List<com.google.cloud.speech.v1.SpeechRecognitionResult> 
      getResultsList();
  /**
   * <code>repeated .google.cloud.speech.v1.SpeechRecognitionResult results = 2;</code>
   *
   * <pre>
   * [Output-only] May contain zero or one `is_final=true` result (the newly
   * settled portion). May also contain zero or more `is_final=false` results.
   * </pre>
   */
  com.google.cloud.speech.v1.SpeechRecognitionResult getResults(int index);
  /**
   * <code>repeated .google.cloud.speech.v1.SpeechRecognitionResult results = 2;</code>
   *
   * <pre>
   * [Output-only] May contain zero or one `is_final=true` result (the newly
   * settled portion). May also contain zero or more `is_final=false` results.
   * </pre>
   */
  int getResultsCount();
  /**
   * <code>repeated .google.cloud.speech.v1.SpeechRecognitionResult results = 2;</code>
   *
   * <pre>
   * [Output-only] May contain zero or one `is_final=true` result (the newly
   * settled portion). May also contain zero or more `is_final=false` results.
   * </pre>
   */
  java.util.List<? extends com.google.cloud.speech.v1.SpeechRecognitionResultOrBuilder> 
      getResultsOrBuilderList();
  /**
   * <code>repeated .google.cloud.speech.v1.SpeechRecognitionResult results = 2;</code>
   *
   * <pre>
   * [Output-only] May contain zero or one `is_final=true` result (the newly
   * settled portion). May also contain zero or more `is_final=false` results.
   * </pre>
   */
  com.google.cloud.speech.v1.SpeechRecognitionResultOrBuilder getResultsOrBuilder(
      int index);

  /**
   * <code>optional int32 result_index = 3;</code>
   *
   * <pre>
   * [Output-only] Indicates the lowest index in the `results` array that has
   * changed. The repeated `SpeechRecognitionResult` results overwrite past
   * results at this index and higher.
   * </pre>
   */
  int getResultIndex();

  /**
   * <code>optional .google.cloud.speech.v1.RecognizeResponse.EndpointerEvent endpoint = 4;</code>
   *
   * <pre>
   * [Output-only] Indicates the type of endpointer event.
   * </pre>
   */
  int getEndpointValue();
  /**
   * <code>optional .google.cloud.speech.v1.RecognizeResponse.EndpointerEvent endpoint = 4;</code>
   *
   * <pre>
   * [Output-only] Indicates the type of endpointer event.
   * </pre>
   */
  com.google.cloud.speech.v1.RecognizeResponse.EndpointerEvent getEndpoint();
}
