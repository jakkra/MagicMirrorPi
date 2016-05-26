// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/logging/v2/logging_config.proto

package com.google.logging.v2;

/**
 * Protobuf type {@code google.logging.v2.CreateSinkRequest}
 *
 * <pre>
 * The parameters to `CreateSink`.
 * </pre>
 */
public  final class CreateSinkRequest extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:google.logging.v2.CreateSinkRequest)
    CreateSinkRequestOrBuilder {
  // Use CreateSinkRequest.newBuilder() to construct.
  private CreateSinkRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private CreateSinkRequest() {
    projectName_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private CreateSinkRequest(
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
            java.lang.String s = input.readStringRequireUtf8();

            projectName_ = s;
            break;
          }
          case 18: {
            com.google.logging.v2.LogSink.Builder subBuilder = null;
            if (sink_ != null) {
              subBuilder = sink_.toBuilder();
            }
            sink_ = input.readMessage(com.google.logging.v2.LogSink.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(sink_);
              sink_ = subBuilder.buildPartial();
            }

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
    return com.google.logging.v2.LoggingConfig.internal_static_google_logging_v2_CreateSinkRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.google.logging.v2.LoggingConfig.internal_static_google_logging_v2_CreateSinkRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.google.logging.v2.CreateSinkRequest.class, com.google.logging.v2.CreateSinkRequest.Builder.class);
  }

  public static final int PROJECT_NAME_FIELD_NUMBER = 1;
  private volatile java.lang.Object projectName_;
  /**
   * <code>optional string project_name = 1;</code>
   *
   * <pre>
   * The resource name of the project in which to create the sink.
   * Example: `"projects/my-project-id"`.
   * The new sink must be provided in the request.
   * </pre>
   */
  public java.lang.String getProjectName() {
    java.lang.Object ref = projectName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      projectName_ = s;
      return s;
    }
  }
  /**
   * <code>optional string project_name = 1;</code>
   *
   * <pre>
   * The resource name of the project in which to create the sink.
   * Example: `"projects/my-project-id"`.
   * The new sink must be provided in the request.
   * </pre>
   */
  public com.google.protobuf.ByteString
      getProjectNameBytes() {
    java.lang.Object ref = projectName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      projectName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int SINK_FIELD_NUMBER = 2;
  private com.google.logging.v2.LogSink sink_;
  /**
   * <code>optional .google.logging.v2.LogSink sink = 2;</code>
   *
   * <pre>
   * The new sink, which must not have an identifier that already
   * exists.
   * </pre>
   */
  public boolean hasSink() {
    return sink_ != null;
  }
  /**
   * <code>optional .google.logging.v2.LogSink sink = 2;</code>
   *
   * <pre>
   * The new sink, which must not have an identifier that already
   * exists.
   * </pre>
   */
  public com.google.logging.v2.LogSink getSink() {
    return sink_ == null ? com.google.logging.v2.LogSink.getDefaultInstance() : sink_;
  }
  /**
   * <code>optional .google.logging.v2.LogSink sink = 2;</code>
   *
   * <pre>
   * The new sink, which must not have an identifier that already
   * exists.
   * </pre>
   */
  public com.google.logging.v2.LogSinkOrBuilder getSinkOrBuilder() {
    return getSink();
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
    if (!getProjectNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessage.writeString(output, 1, projectName_);
    }
    if (sink_ != null) {
      output.writeMessage(2, getSink());
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getProjectNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(1, projectName_);
    }
    if (sink_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getSink());
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  public static com.google.logging.v2.CreateSinkRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.logging.v2.CreateSinkRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.logging.v2.CreateSinkRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.logging.v2.CreateSinkRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.logging.v2.CreateSinkRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static com.google.logging.v2.CreateSinkRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }
  public static com.google.logging.v2.CreateSinkRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input);
  }
  public static com.google.logging.v2.CreateSinkRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input, extensionRegistry);
  }
  public static com.google.logging.v2.CreateSinkRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static com.google.logging.v2.CreateSinkRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.google.logging.v2.CreateSinkRequest prototype) {
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
   * Protobuf type {@code google.logging.v2.CreateSinkRequest}
   *
   * <pre>
   * The parameters to `CreateSink`.
   * </pre>
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:google.logging.v2.CreateSinkRequest)
      com.google.logging.v2.CreateSinkRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.google.logging.v2.LoggingConfig.internal_static_google_logging_v2_CreateSinkRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.google.logging.v2.LoggingConfig.internal_static_google_logging_v2_CreateSinkRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.google.logging.v2.CreateSinkRequest.class, com.google.logging.v2.CreateSinkRequest.Builder.class);
    }

    // Construct using com.google.logging.v2.CreateSinkRequest.newBuilder()
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
      projectName_ = "";

      if (sinkBuilder_ == null) {
        sink_ = null;
      } else {
        sink_ = null;
        sinkBuilder_ = null;
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.google.logging.v2.LoggingConfig.internal_static_google_logging_v2_CreateSinkRequest_descriptor;
    }

    public com.google.logging.v2.CreateSinkRequest getDefaultInstanceForType() {
      return com.google.logging.v2.CreateSinkRequest.getDefaultInstance();
    }

    public com.google.logging.v2.CreateSinkRequest build() {
      com.google.logging.v2.CreateSinkRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.google.logging.v2.CreateSinkRequest buildPartial() {
      com.google.logging.v2.CreateSinkRequest result = new com.google.logging.v2.CreateSinkRequest(this);
      result.projectName_ = projectName_;
      if (sinkBuilder_ == null) {
        result.sink_ = sink_;
      } else {
        result.sink_ = sinkBuilder_.build();
      }
      onBuilt();
      return result;
    }

    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.google.logging.v2.CreateSinkRequest) {
        return mergeFrom((com.google.logging.v2.CreateSinkRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.google.logging.v2.CreateSinkRequest other) {
      if (other == com.google.logging.v2.CreateSinkRequest.getDefaultInstance()) return this;
      if (!other.getProjectName().isEmpty()) {
        projectName_ = other.projectName_;
        onChanged();
      }
      if (other.hasSink()) {
        mergeSink(other.getSink());
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
      com.google.logging.v2.CreateSinkRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.google.logging.v2.CreateSinkRequest) e.getUnfinishedMessage();
        throw e;
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object projectName_ = "";
    /**
     * <code>optional string project_name = 1;</code>
     *
     * <pre>
     * The resource name of the project in which to create the sink.
     * Example: `"projects/my-project-id"`.
     * The new sink must be provided in the request.
     * </pre>
     */
    public java.lang.String getProjectName() {
      java.lang.Object ref = projectName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        projectName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string project_name = 1;</code>
     *
     * <pre>
     * The resource name of the project in which to create the sink.
     * Example: `"projects/my-project-id"`.
     * The new sink must be provided in the request.
     * </pre>
     */
    public com.google.protobuf.ByteString
        getProjectNameBytes() {
      java.lang.Object ref = projectName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        projectName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string project_name = 1;</code>
     *
     * <pre>
     * The resource name of the project in which to create the sink.
     * Example: `"projects/my-project-id"`.
     * The new sink must be provided in the request.
     * </pre>
     */
    public Builder setProjectName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      projectName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string project_name = 1;</code>
     *
     * <pre>
     * The resource name of the project in which to create the sink.
     * Example: `"projects/my-project-id"`.
     * The new sink must be provided in the request.
     * </pre>
     */
    public Builder clearProjectName() {
      
      projectName_ = getDefaultInstance().getProjectName();
      onChanged();
      return this;
    }
    /**
     * <code>optional string project_name = 1;</code>
     *
     * <pre>
     * The resource name of the project in which to create the sink.
     * Example: `"projects/my-project-id"`.
     * The new sink must be provided in the request.
     * </pre>
     */
    public Builder setProjectNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      projectName_ = value;
      onChanged();
      return this;
    }

    private com.google.logging.v2.LogSink sink_ = null;
    private com.google.protobuf.SingleFieldBuilder<
        com.google.logging.v2.LogSink, com.google.logging.v2.LogSink.Builder, com.google.logging.v2.LogSinkOrBuilder> sinkBuilder_;
    /**
     * <code>optional .google.logging.v2.LogSink sink = 2;</code>
     *
     * <pre>
     * The new sink, which must not have an identifier that already
     * exists.
     * </pre>
     */
    public boolean hasSink() {
      return sinkBuilder_ != null || sink_ != null;
    }
    /**
     * <code>optional .google.logging.v2.LogSink sink = 2;</code>
     *
     * <pre>
     * The new sink, which must not have an identifier that already
     * exists.
     * </pre>
     */
    public com.google.logging.v2.LogSink getSink() {
      if (sinkBuilder_ == null) {
        return sink_ == null ? com.google.logging.v2.LogSink.getDefaultInstance() : sink_;
      } else {
        return sinkBuilder_.getMessage();
      }
    }
    /**
     * <code>optional .google.logging.v2.LogSink sink = 2;</code>
     *
     * <pre>
     * The new sink, which must not have an identifier that already
     * exists.
     * </pre>
     */
    public Builder setSink(com.google.logging.v2.LogSink value) {
      if (sinkBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        sink_ = value;
        onChanged();
      } else {
        sinkBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>optional .google.logging.v2.LogSink sink = 2;</code>
     *
     * <pre>
     * The new sink, which must not have an identifier that already
     * exists.
     * </pre>
     */
    public Builder setSink(
        com.google.logging.v2.LogSink.Builder builderForValue) {
      if (sinkBuilder_ == null) {
        sink_ = builderForValue.build();
        onChanged();
      } else {
        sinkBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>optional .google.logging.v2.LogSink sink = 2;</code>
     *
     * <pre>
     * The new sink, which must not have an identifier that already
     * exists.
     * </pre>
     */
    public Builder mergeSink(com.google.logging.v2.LogSink value) {
      if (sinkBuilder_ == null) {
        if (sink_ != null) {
          sink_ =
            com.google.logging.v2.LogSink.newBuilder(sink_).mergeFrom(value).buildPartial();
        } else {
          sink_ = value;
        }
        onChanged();
      } else {
        sinkBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>optional .google.logging.v2.LogSink sink = 2;</code>
     *
     * <pre>
     * The new sink, which must not have an identifier that already
     * exists.
     * </pre>
     */
    public Builder clearSink() {
      if (sinkBuilder_ == null) {
        sink_ = null;
        onChanged();
      } else {
        sink_ = null;
        sinkBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>optional .google.logging.v2.LogSink sink = 2;</code>
     *
     * <pre>
     * The new sink, which must not have an identifier that already
     * exists.
     * </pre>
     */
    public com.google.logging.v2.LogSink.Builder getSinkBuilder() {
      
      onChanged();
      return getSinkFieldBuilder().getBuilder();
    }
    /**
     * <code>optional .google.logging.v2.LogSink sink = 2;</code>
     *
     * <pre>
     * The new sink, which must not have an identifier that already
     * exists.
     * </pre>
     */
    public com.google.logging.v2.LogSinkOrBuilder getSinkOrBuilder() {
      if (sinkBuilder_ != null) {
        return sinkBuilder_.getMessageOrBuilder();
      } else {
        return sink_ == null ?
            com.google.logging.v2.LogSink.getDefaultInstance() : sink_;
      }
    }
    /**
     * <code>optional .google.logging.v2.LogSink sink = 2;</code>
     *
     * <pre>
     * The new sink, which must not have an identifier that already
     * exists.
     * </pre>
     */
    private com.google.protobuf.SingleFieldBuilder<
        com.google.logging.v2.LogSink, com.google.logging.v2.LogSink.Builder, com.google.logging.v2.LogSinkOrBuilder> 
        getSinkFieldBuilder() {
      if (sinkBuilder_ == null) {
        sinkBuilder_ = new com.google.protobuf.SingleFieldBuilder<
            com.google.logging.v2.LogSink, com.google.logging.v2.LogSink.Builder, com.google.logging.v2.LogSinkOrBuilder>(
                getSink(),
                getParentForChildren(),
                isClean());
        sink_ = null;
      }
      return sinkBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:google.logging.v2.CreateSinkRequest)
  }

  // @@protoc_insertion_point(class_scope:google.logging.v2.CreateSinkRequest)
  private static final com.google.logging.v2.CreateSinkRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.google.logging.v2.CreateSinkRequest();
  }

  public static com.google.logging.v2.CreateSinkRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CreateSinkRequest>
      PARSER = new com.google.protobuf.AbstractParser<CreateSinkRequest>() {
    public CreateSinkRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      try {
        return new CreateSinkRequest(input, extensionRegistry);
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

  public static com.google.protobuf.Parser<CreateSinkRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CreateSinkRequest> getParserForType() {
    return PARSER;
  }

  public com.google.logging.v2.CreateSinkRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
