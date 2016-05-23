// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/speech/v1/cloud-speech.proto

package com.google.cloud.speech.v1;

public interface SpeechRecognitionAlternativeOrBuilder extends
    // @@protoc_insertion_point(interface_extends:google.cloud.speech.v1.SpeechRecognitionAlternative)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional string transcript = 1;</code>
   *
   * <pre>
   * [Output-only] Transcript text representing the words that the user spoke.
   * </pre>
   */
  java.lang.String getTranscript();
  /**
   * <code>optional string transcript = 1;</code>
   *
   * <pre>
   * [Output-only] Transcript text representing the words that the user spoke.
   * </pre>
   */
  com.google.protobuf.ByteString
      getTranscriptBytes();

  /**
   * <code>optional float confidence = 2;</code>
   *
   * <pre>
   * [Output-only] The confidence estimate between 0.0 and 1.0. A higher number
   * means the system is more confident that the recognition is correct.
   * This field is typically provided only for the top hypothesis. and only for
   * `is_final=true` results.
   * The default of 0.0 is a sentinel value indicating confidence was not set.
   * </pre>
   */
  float getConfidence();
}
