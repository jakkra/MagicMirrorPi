// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/speech/v1/cloud-speech.proto

package com.google.cloud.speech.v1;

/**
 * Protobuf type {@code google.cloud.speech.v1.NonStreamingRecognizeResponse}
 *
 * <pre>
 * `NonStreamingRecognizeResponse` is the only message returned to the client by
 * `NonStreamingRecognize`. It contains the result as zero or more sequential
 * `RecognizeResponse` messages.
 * Note that streaming `Recognize` will also return multiple `RecognizeResponse`
 * messages, but each message is individually streamed.
 * </pre>
 */
public  final class NonStreamingRecognizeResponse extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:google.cloud.speech.v1.NonStreamingRecognizeResponse)
    NonStreamingRecognizeResponseOrBuilder {
  // Use NonStreamingRecognizeResponse.newBuilder() to construct.
  private NonStreamingRecognizeResponse(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private NonStreamingRecognizeResponse() {
    responses_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private NonStreamingRecognizeResponse(
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
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              responses_ = new java.util.ArrayList<com.google.cloud.speech.v1.RecognizeResponse>();
              mutable_bitField0_ |= 0x00000001;
            }
            responses_.add(input.readMessage(com.google.cloud.speech.v1.RecognizeResponse.parser(), extensionRegistry));
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
      if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
        responses_ = java.util.Collections.unmodifiableList(responses_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.google.cloud.speech.v1.SpeechProto.internal_static_google_cloud_speech_v1_NonStreamingRecognizeResponse_descriptor;
  }

  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.google.cloud.speech.v1.SpeechProto.internal_static_google_cloud_speech_v1_NonStreamingRecognizeResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.google.cloud.speech.v1.NonStreamingRecognizeResponse.class, com.google.cloud.speech.v1.NonStreamingRecognizeResponse.Builder.class);
  }

  public static final int RESPONSES_FIELD_NUMBER = 1;
  private java.util.List<com.google.cloud.speech.v1.RecognizeResponse> responses_;
  /**
   * <code>repeated .google.cloud.speech.v1.RecognizeResponse responses = 1;</code>
   *
   * <pre>
   * [Output-only] Sequential list of messages returned by the recognizer.
   * </pre>
   */
  public java.util.List<com.google.cloud.speech.v1.RecognizeResponse> getResponsesList() {
    return responses_;
  }
  /**
   * <code>repeated .google.cloud.speech.v1.RecognizeResponse responses = 1;</code>
   *
   * <pre>
   * [Output-only] Sequential list of messages returned by the recognizer.
   * </pre>
   */
  public java.util.List<? extends com.google.cloud.speech.v1.RecognizeResponseOrBuilder> 
      getResponsesOrBuilderList() {
    return responses_;
  }
  /**
   * <code>repeated .google.cloud.speech.v1.RecognizeResponse responses = 1;</code>
   *
   * <pre>
   * [Output-only] Sequential list of messages returned by the recognizer.
   * </pre>
   */
  public int getResponsesCount() {
    return responses_.size();
  }
  /**
   * <code>repeated .google.cloud.speech.v1.RecognizeResponse responses = 1;</code>
   *
   * <pre>
   * [Output-only] Sequential list of messages returned by the recognizer.
   * </pre>
   */
  public com.google.cloud.speech.v1.RecognizeResponse getResponses(int index) {
    return responses_.get(index);
  }
  /**
   * <code>repeated .google.cloud.speech.v1.RecognizeResponse responses = 1;</code>
   *
   * <pre>
   * [Output-only] Sequential list of messages returned by the recognizer.
   * </pre>
   */
  public com.google.cloud.speech.v1.RecognizeResponseOrBuilder getResponsesOrBuilder(
      int index) {
    return responses_.get(index);
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
    for (int i = 0; i < responses_.size(); i++) {
      output.writeMessage(1, responses_.get(i));
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < responses_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, responses_.get(i));
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  public static com.google.cloud.speech.v1.NonStreamingRecognizeResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.cloud.speech.v1.NonStreamingRecognizeResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.cloud.speech.v1.NonStreamingRecognizeResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.cloud.speech.v1.NonStreamingRecognizeResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.cloud.speech.v1.NonStreamingRecognizeResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static com.google.cloud.speech.v1.NonStreamingRecognizeResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }
  public static com.google.cloud.speech.v1.NonStreamingRecognizeResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input);
  }
  public static com.google.cloud.speech.v1.NonStreamingRecognizeResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input, extensionRegistry);
  }
  public static com.google.cloud.speech.v1.NonStreamingRecognizeResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static com.google.cloud.speech.v1.NonStreamingRecognizeResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.google.cloud.speech.v1.NonStreamingRecognizeResponse prototype) {
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
   * Protobuf type {@code google.cloud.speech.v1.NonStreamingRecognizeResponse}
   *
   * <pre>
   * `NonStreamingRecognizeResponse` is the only message returned to the client by
   * `NonStreamingRecognize`. It contains the result as zero or more sequential
   * `RecognizeResponse` messages.
   * Note that streaming `Recognize` will also return multiple `RecognizeResponse`
   * messages, but each message is individually streamed.
   * </pre>
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:google.cloud.speech.v1.NonStreamingRecognizeResponse)
      com.google.cloud.speech.v1.NonStreamingRecognizeResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.google.cloud.speech.v1.SpeechProto.internal_static_google_cloud_speech_v1_NonStreamingRecognizeResponse_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.google.cloud.speech.v1.SpeechProto.internal_static_google_cloud_speech_v1_NonStreamingRecognizeResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.google.cloud.speech.v1.NonStreamingRecognizeResponse.class, com.google.cloud.speech.v1.NonStreamingRecognizeResponse.Builder.class);
    }

    // Construct using com.google.cloud.speech.v1.NonStreamingRecognizeResponse.newBuilder()
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
        getResponsesFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (responsesBuilder_ == null) {
        responses_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        responsesBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.google.cloud.speech.v1.SpeechProto.internal_static_google_cloud_speech_v1_NonStreamingRecognizeResponse_descriptor;
    }

    public com.google.cloud.speech.v1.NonStreamingRecognizeResponse getDefaultInstanceForType() {
      return com.google.cloud.speech.v1.NonStreamingRecognizeResponse.getDefaultInstance();
    }

    public com.google.cloud.speech.v1.NonStreamingRecognizeResponse build() {
      com.google.cloud.speech.v1.NonStreamingRecognizeResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.google.cloud.speech.v1.NonStreamingRecognizeResponse buildPartial() {
      com.google.cloud.speech.v1.NonStreamingRecognizeResponse result = new com.google.cloud.speech.v1.NonStreamingRecognizeResponse(this);
      int from_bitField0_ = bitField0_;
      if (responsesBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          responses_ = java.util.Collections.unmodifiableList(responses_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.responses_ = responses_;
      } else {
        result.responses_ = responsesBuilder_.build();
      }
      onBuilt();
      return result;
    }

    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.google.cloud.speech.v1.NonStreamingRecognizeResponse) {
        return mergeFrom((com.google.cloud.speech.v1.NonStreamingRecognizeResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.google.cloud.speech.v1.NonStreamingRecognizeResponse other) {
      if (other == com.google.cloud.speech.v1.NonStreamingRecognizeResponse.getDefaultInstance()) return this;
      if (responsesBuilder_ == null) {
        if (!other.responses_.isEmpty()) {
          if (responses_.isEmpty()) {
            responses_ = other.responses_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureResponsesIsMutable();
            responses_.addAll(other.responses_);
          }
          onChanged();
        }
      } else {
        if (!other.responses_.isEmpty()) {
          if (responsesBuilder_.isEmpty()) {
            responsesBuilder_.dispose();
            responsesBuilder_ = null;
            responses_ = other.responses_;
            bitField0_ = (bitField0_ & ~0x00000001);
            responsesBuilder_ = 
              com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders ?
                 getResponsesFieldBuilder() : null;
          } else {
            responsesBuilder_.addAllMessages(other.responses_);
          }
        }
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
      com.google.cloud.speech.v1.NonStreamingRecognizeResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.google.cloud.speech.v1.NonStreamingRecognizeResponse) e.getUnfinishedMessage();
        throw e;
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<com.google.cloud.speech.v1.RecognizeResponse> responses_ =
      java.util.Collections.emptyList();
    private void ensureResponsesIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        responses_ = new java.util.ArrayList<com.google.cloud.speech.v1.RecognizeResponse>(responses_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilder<
        com.google.cloud.speech.v1.RecognizeResponse, com.google.cloud.speech.v1.RecognizeResponse.Builder, com.google.cloud.speech.v1.RecognizeResponseOrBuilder> responsesBuilder_;

    /**
     * <code>repeated .google.cloud.speech.v1.RecognizeResponse responses = 1;</code>
     *
     * <pre>
     * [Output-only] Sequential list of messages returned by the recognizer.
     * </pre>
     */
    public java.util.List<com.google.cloud.speech.v1.RecognizeResponse> getResponsesList() {
      if (responsesBuilder_ == null) {
        return java.util.Collections.unmodifiableList(responses_);
      } else {
        return responsesBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .google.cloud.speech.v1.RecognizeResponse responses = 1;</code>
     *
     * <pre>
     * [Output-only] Sequential list of messages returned by the recognizer.
     * </pre>
     */
    public int getResponsesCount() {
      if (responsesBuilder_ == null) {
        return responses_.size();
      } else {
        return responsesBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .google.cloud.speech.v1.RecognizeResponse responses = 1;</code>
     *
     * <pre>
     * [Output-only] Sequential list of messages returned by the recognizer.
     * </pre>
     */
    public com.google.cloud.speech.v1.RecognizeResponse getResponses(int index) {
      if (responsesBuilder_ == null) {
        return responses_.get(index);
      } else {
        return responsesBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .google.cloud.speech.v1.RecognizeResponse responses = 1;</code>
     *
     * <pre>
     * [Output-only] Sequential list of messages returned by the recognizer.
     * </pre>
     */
    public Builder setResponses(
        int index, com.google.cloud.speech.v1.RecognizeResponse value) {
      if (responsesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureResponsesIsMutable();
        responses_.set(index, value);
        onChanged();
      } else {
        responsesBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .google.cloud.speech.v1.RecognizeResponse responses = 1;</code>
     *
     * <pre>
     * [Output-only] Sequential list of messages returned by the recognizer.
     * </pre>
     */
    public Builder setResponses(
        int index, com.google.cloud.speech.v1.RecognizeResponse.Builder builderForValue) {
      if (responsesBuilder_ == null) {
        ensureResponsesIsMutable();
        responses_.set(index, builderForValue.build());
        onChanged();
      } else {
        responsesBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .google.cloud.speech.v1.RecognizeResponse responses = 1;</code>
     *
     * <pre>
     * [Output-only] Sequential list of messages returned by the recognizer.
     * </pre>
     */
    public Builder addResponses(com.google.cloud.speech.v1.RecognizeResponse value) {
      if (responsesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureResponsesIsMutable();
        responses_.add(value);
        onChanged();
      } else {
        responsesBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .google.cloud.speech.v1.RecognizeResponse responses = 1;</code>
     *
     * <pre>
     * [Output-only] Sequential list of messages returned by the recognizer.
     * </pre>
     */
    public Builder addResponses(
        int index, com.google.cloud.speech.v1.RecognizeResponse value) {
      if (responsesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureResponsesIsMutable();
        responses_.add(index, value);
        onChanged();
      } else {
        responsesBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .google.cloud.speech.v1.RecognizeResponse responses = 1;</code>
     *
     * <pre>
     * [Output-only] Sequential list of messages returned by the recognizer.
     * </pre>
     */
    public Builder addResponses(
        com.google.cloud.speech.v1.RecognizeResponse.Builder builderForValue) {
      if (responsesBuilder_ == null) {
        ensureResponsesIsMutable();
        responses_.add(builderForValue.build());
        onChanged();
      } else {
        responsesBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .google.cloud.speech.v1.RecognizeResponse responses = 1;</code>
     *
     * <pre>
     * [Output-only] Sequential list of messages returned by the recognizer.
     * </pre>
     */
    public Builder addResponses(
        int index, com.google.cloud.speech.v1.RecognizeResponse.Builder builderForValue) {
      if (responsesBuilder_ == null) {
        ensureResponsesIsMutable();
        responses_.add(index, builderForValue.build());
        onChanged();
      } else {
        responsesBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .google.cloud.speech.v1.RecognizeResponse responses = 1;</code>
     *
     * <pre>
     * [Output-only] Sequential list of messages returned by the recognizer.
     * </pre>
     */
    public Builder addAllResponses(
        java.lang.Iterable<? extends com.google.cloud.speech.v1.RecognizeResponse> values) {
      if (responsesBuilder_ == null) {
        ensureResponsesIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, responses_);
        onChanged();
      } else {
        responsesBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .google.cloud.speech.v1.RecognizeResponse responses = 1;</code>
     *
     * <pre>
     * [Output-only] Sequential list of messages returned by the recognizer.
     * </pre>
     */
    public Builder clearResponses() {
      if (responsesBuilder_ == null) {
        responses_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        responsesBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .google.cloud.speech.v1.RecognizeResponse responses = 1;</code>
     *
     * <pre>
     * [Output-only] Sequential list of messages returned by the recognizer.
     * </pre>
     */
    public Builder removeResponses(int index) {
      if (responsesBuilder_ == null) {
        ensureResponsesIsMutable();
        responses_.remove(index);
        onChanged();
      } else {
        responsesBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .google.cloud.speech.v1.RecognizeResponse responses = 1;</code>
     *
     * <pre>
     * [Output-only] Sequential list of messages returned by the recognizer.
     * </pre>
     */
    public com.google.cloud.speech.v1.RecognizeResponse.Builder getResponsesBuilder(
        int index) {
      return getResponsesFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .google.cloud.speech.v1.RecognizeResponse responses = 1;</code>
     *
     * <pre>
     * [Output-only] Sequential list of messages returned by the recognizer.
     * </pre>
     */
    public com.google.cloud.speech.v1.RecognizeResponseOrBuilder getResponsesOrBuilder(
        int index) {
      if (responsesBuilder_ == null) {
        return responses_.get(index);  } else {
        return responsesBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .google.cloud.speech.v1.RecognizeResponse responses = 1;</code>
     *
     * <pre>
     * [Output-only] Sequential list of messages returned by the recognizer.
     * </pre>
     */
    public java.util.List<? extends com.google.cloud.speech.v1.RecognizeResponseOrBuilder> 
         getResponsesOrBuilderList() {
      if (responsesBuilder_ != null) {
        return responsesBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(responses_);
      }
    }
    /**
     * <code>repeated .google.cloud.speech.v1.RecognizeResponse responses = 1;</code>
     *
     * <pre>
     * [Output-only] Sequential list of messages returned by the recognizer.
     * </pre>
     */
    public com.google.cloud.speech.v1.RecognizeResponse.Builder addResponsesBuilder() {
      return getResponsesFieldBuilder().addBuilder(
          com.google.cloud.speech.v1.RecognizeResponse.getDefaultInstance());
    }
    /**
     * <code>repeated .google.cloud.speech.v1.RecognizeResponse responses = 1;</code>
     *
     * <pre>
     * [Output-only] Sequential list of messages returned by the recognizer.
     * </pre>
     */
    public com.google.cloud.speech.v1.RecognizeResponse.Builder addResponsesBuilder(
        int index) {
      return getResponsesFieldBuilder().addBuilder(
          index, com.google.cloud.speech.v1.RecognizeResponse.getDefaultInstance());
    }
    /**
     * <code>repeated .google.cloud.speech.v1.RecognizeResponse responses = 1;</code>
     *
     * <pre>
     * [Output-only] Sequential list of messages returned by the recognizer.
     * </pre>
     */
    public java.util.List<com.google.cloud.speech.v1.RecognizeResponse.Builder> 
         getResponsesBuilderList() {
      return getResponsesFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilder<
        com.google.cloud.speech.v1.RecognizeResponse, com.google.cloud.speech.v1.RecognizeResponse.Builder, com.google.cloud.speech.v1.RecognizeResponseOrBuilder> 
        getResponsesFieldBuilder() {
      if (responsesBuilder_ == null) {
        responsesBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<
            com.google.cloud.speech.v1.RecognizeResponse, com.google.cloud.speech.v1.RecognizeResponse.Builder, com.google.cloud.speech.v1.RecognizeResponseOrBuilder>(
                responses_,
                ((bitField0_ & 0x00000001) == 0x00000001),
                getParentForChildren(),
                isClean());
        responses_ = null;
      }
      return responsesBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:google.cloud.speech.v1.NonStreamingRecognizeResponse)
  }

  // @@protoc_insertion_point(class_scope:google.cloud.speech.v1.NonStreamingRecognizeResponse)
  private static final com.google.cloud.speech.v1.NonStreamingRecognizeResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.google.cloud.speech.v1.NonStreamingRecognizeResponse();
  }

  public static com.google.cloud.speech.v1.NonStreamingRecognizeResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<NonStreamingRecognizeResponse>
      PARSER = new com.google.protobuf.AbstractParser<NonStreamingRecognizeResponse>() {
    public NonStreamingRecognizeResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      try {
        return new NonStreamingRecognizeResponse(input, extensionRegistry);
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

  public static com.google.protobuf.Parser<NonStreamingRecognizeResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<NonStreamingRecognizeResponse> getParserForType() {
    return PARSER;
  }

  public com.google.cloud.speech.v1.NonStreamingRecognizeResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
