// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/speech/v1/cloud-speech.proto

package com.google.cloud.speech.v1;

public interface AudioRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:google.cloud.speech.v1.AudioRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional bytes content = 1;</code>
   *
   * <pre>
   * [Required] The audio data bytes encoded as specified in
   * `InitialRecognizeRequest`.
   * </pre>
   */
  com.google.protobuf.ByteString getContent();
}