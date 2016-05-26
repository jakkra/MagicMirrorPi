// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/speech/v1/cloud-speech.proto

package com.google.cloud.speech.v1;

/**
 * Protobuf type {@code google.cloud.speech.v1.AudioRequest}
 *
 * <pre>
 * Contains audio data in the format specified in the `InitialRecognizeRequest`.
 * </pre>
 */
public  final class AudioRequest extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:google.cloud.speech.v1.AudioRequest)
    AudioRequestOrBuilder {
  // Use AudioRequest.newBuilder() to construct.
  private AudioRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private AudioRequest() {
    content_ = com.google.protobuf.ByteString.EMPTY;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private AudioRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry) {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 10: {

            content_ = input.readBytes();
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw new RuntimeException(e.setUnfinishedMessage(this));
    } catch (java.io.IOException e) {
      throw new RuntimeException(
          new com.google.protobuf.InvalidProtocolBufferException(
              e.getMessage()).setUnfinishedMessage(this));
    } finally {
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.google.cloud.speech.v1.SpeechProto.internal_static_google_cloud_speech_v1_AudioRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.google.cloud.speech.v1.SpeechProto.internal_static_google_cloud_speech_v1_AudioRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.google.cloud.speech.v1.AudioRequest.class, com.google.cloud.speech.v1.AudioRequest.Builder.class);
  }

  public static final int CONTENT_FIELD_NUMBER = 1;
  private com.google.protobuf.ByteString content_;
  /**
   * <code>optional bytes content = 1;</code>
   *
   * <pre>
   * [Required] The audio data bytes encoded as specified in
   * `InitialRecognizeRequest`.
   * </pre>
   */
  public com.google.protobuf.ByteString getContent() {
    return content_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!content_.isEmpty()) {
      output.writeBytes(1, content_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!content_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(1, content_);
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  public static com.google.cloud.speech.v1.AudioRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.cloud.speech.v1.AudioRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.cloud.speech.v1.AudioRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.cloud.speech.v1.AudioRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.cloud.speech.v1.AudioRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static com.google.cloud.speech.v1.AudioRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }
  public static com.google.cloud.speech.v1.AudioRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input);
  }
  public static com.google.cloud.speech.v1.AudioRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input, extensionRegistry);
  }
  public static com.google.cloud.speech.v1.AudioRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static com.google.cloud.speech.v1.AudioRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.google.cloud.speech.v1.AudioRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessage.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code google.cloud.speech.v1.AudioRequest}
   *
   * <pre>
   * Contains audio data in the format specified in the `InitialRecognizeRequest`.
   * </pre>
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:google.cloud.speech.v1.AudioRequest)
      com.google.cloud.speech.v1.AudioRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.google.cloud.speech.v1.SpeechProto.internal_static_google_cloud_speech_v1_AudioRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.google.cloud.speech.v1.SpeechProto.internal_static_google_cloud_speech_v1_AudioRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.google.cloud.speech.v1.AudioRequest.class, com.google.cloud.speech.v1.AudioRequest.Builder.class);
    }

    // Construct using com.google.cloud.speech.v1.AudioRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      content_ = com.google.protobuf.ByteString.EMPTY;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.google.cloud.speech.v1.SpeechProto.internal_static_google_cloud_speech_v1_AudioRequest_descriptor;
    }

    public com.google.cloud.speech.v1.AudioRequest getDefaultInstanceForType() {
      return com.google.cloud.speech.v1.AudioRequest.getDefaultInstance();
    }

    public com.google.cloud.speech.v1.AudioRequest build() {
      com.google.cloud.speech.v1.AudioRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.google.cloud.speech.v1.AudioRequest buildPartial() {
      com.google.cloud.speech.v1.AudioRequest result = new com.google.cloud.speech.v1.AudioRequest(this);
      result.content_ = content_;
      onBuilt();
      return result;
    }

    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.google.cloud.speech.v1.AudioRequest) {
        return mergeFrom((com.google.cloud.speech.v1.AudioRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.google.cloud.speech.v1.AudioRequest other) {
      if (other == com.google.cloud.speech.v1.AudioRequest.getDefaultInstance()) return this;
      if (other.getContent() != com.google.protobuf.ByteString.EMPTY) {
        setContent(other.getContent());
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.google.cloud.speech.v1.AudioRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.google.cloud.speech.v1.AudioRequest) e.getUnfinishedMessage();
        throw e;
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private com.google.protobuf.ByteString content_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>optional bytes content = 1;</code>
     *
     * <pre>
     * [Required] The audio data bytes encoded as specified in
     * `InitialRecognizeRequest`.
     * </pre>
     */
    public com.google.protobuf.ByteString getContent() {
      return content_;
    }
    /**
     * <code>optional bytes content = 1;</code>
     *
     * <pre>
     * [Required] The audio data bytes encoded as specified in
     * `InitialRecognizeRequest`.
     * </pre>
     */
    public Builder setContent(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      content_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional bytes content = 1;</code>
     *
     * <pre>
     * [Required] The audio data bytes encoded as specified in
     * `InitialRecognizeRequest`.
     * </pre>
     */
    public Builder clearContent() {
      
      content_ = getDefaultInstance().getContent();
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:google.cloud.speech.v1.AudioRequest)
  }

  // @@protoc_insertion_point(class_scope:google.cloud.speech.v1.AudioRequest)
  private static final com.google.cloud.speech.v1.AudioRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.google.cloud.speech.v1.AudioRequest();
  }

  public static com.google.cloud.speech.v1.AudioRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AudioRequest>
      PARSER = new com.google.protobuf.AbstractParser<AudioRequest>() {
    public AudioRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      try {
        return new AudioRequest(input, extensionRegistry);
      } catch (RuntimeException e) {
        if (e.getCause() instanceof
            com.google.protobuf.InvalidProtocolBufferException) {
          throw (com.google.protobuf.InvalidProtocolBufferException)
              e.getCause();
        }
        throw e;
      }
    }
  };

  public static com.google.protobuf.Parser<AudioRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AudioRequest> getParserForType() {
    return PARSER;
  }

  public com.google.cloud.speech.v1.AudioRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
