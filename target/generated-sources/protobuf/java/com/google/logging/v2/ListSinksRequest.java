// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/logging/v2/logging_config.proto

package com.google.logging.v2;

/**
 * Protobuf type {@code google.logging.v2.ListSinksRequest}
 *
 * <pre>
 * The parameters to `ListSinks`.
 * </pre>
 */
public  final class ListSinksRequest extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:google.logging.v2.ListSinksRequest)
    ListSinksRequestOrBuilder {
  // Use ListSinksRequest.newBuilder() to construct.
  private ListSinksRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private ListSinksRequest() {
    projectName_ = "";
    pageToken_ = "";
    pageSize_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private ListSinksRequest(
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
            java.lang.String s = input.readStringRequireUtf8();

            pageToken_ = s;
            break;
          }
          case 24: {

            pageSize_ = input.readInt32();
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
    return com.google.logging.v2.LoggingConfig.internal_static_google_logging_v2_ListSinksRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.google.logging.v2.LoggingConfig.internal_static_google_logging_v2_ListSinksRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.google.logging.v2.ListSinksRequest.class, com.google.logging.v2.ListSinksRequest.Builder.class);
  }

  public static final int PROJECT_NAME_FIELD_NUMBER = 1;
  private volatile java.lang.Object projectName_;
  /**
   * <code>optional string project_name = 1;</code>
   *
   * <pre>
   * Required. The resource name of the project containing the sinks.
   * Example: `"projects/my-logging-project"`, `"projects/01234567890"`.
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
   * Required. The resource name of the project containing the sinks.
   * Example: `"projects/my-logging-project"`, `"projects/01234567890"`.
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

  public static final int PAGE_TOKEN_FIELD_NUMBER = 2;
  private volatile java.lang.Object pageToken_;
  /**
   * <code>optional string page_token = 2;</code>
   *
   * <pre>
   * Optional. If the `pageToken` request parameter is supplied, then the next
   * page of results in the set are retrieved.  The `pageToken` parameter must
   * be set with the value of the `nextPageToken` result parameter from the
   * previous request. The value of `projectName` must be the same as in the
   * previous request.
   * </pre>
   */
  public java.lang.String getPageToken() {
    java.lang.Object ref = pageToken_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      pageToken_ = s;
      return s;
    }
  }
  /**
   * <code>optional string page_token = 2;</code>
   *
   * <pre>
   * Optional. If the `pageToken` request parameter is supplied, then the next
   * page of results in the set are retrieved.  The `pageToken` parameter must
   * be set with the value of the `nextPageToken` result parameter from the
   * previous request. The value of `projectName` must be the same as in the
   * previous request.
   * </pre>
   */
  public com.google.protobuf.ByteString
      getPageTokenBytes() {
    java.lang.Object ref = pageToken_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      pageToken_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PAGE_SIZE_FIELD_NUMBER = 3;
  private int pageSize_;
  /**
   * <code>optional int32 page_size = 3;</code>
   *
   * <pre>
   * Optional. The maximum number of results to return from this request.  Fewer
   * results might be returned. You must check for the `nextPageToken` result to
   * determine if additional results are available, which you can retrieve by
   * passing the `nextPageToken` value in the `pageToken` parameter to the next
   * request.
   * </pre>
   */
  public int getPageSize() {
    return pageSize_;
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
    if (!getPageTokenBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessage.writeString(output, 2, pageToken_);
    }
    if (pageSize_ != 0) {
      output.writeInt32(3, pageSize_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getProjectNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(1, projectName_);
    }
    if (!getPageTokenBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(2, pageToken_);
    }
    if (pageSize_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, pageSize_);
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  public static com.google.logging.v2.ListSinksRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.logging.v2.ListSinksRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.logging.v2.ListSinksRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.logging.v2.ListSinksRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.logging.v2.ListSinksRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static com.google.logging.v2.ListSinksRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }
  public static com.google.logging.v2.ListSinksRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input);
  }
  public static com.google.logging.v2.ListSinksRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input, extensionRegistry);
  }
  public static com.google.logging.v2.ListSinksRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static com.google.logging.v2.ListSinksRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.google.logging.v2.ListSinksRequest prototype) {
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
   * Protobuf type {@code google.logging.v2.ListSinksRequest}
   *
   * <pre>
   * The parameters to `ListSinks`.
   * </pre>
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:google.logging.v2.ListSinksRequest)
      com.google.logging.v2.ListSinksRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.google.logging.v2.LoggingConfig.internal_static_google_logging_v2_ListSinksRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.google.logging.v2.LoggingConfig.internal_static_google_logging_v2_ListSinksRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.google.logging.v2.ListSinksRequest.class, com.google.logging.v2.ListSinksRequest.Builder.class);
    }

    // Construct using com.google.logging.v2.ListSinksRequest.newBuilder()
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

      pageToken_ = "";

      pageSize_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.google.logging.v2.LoggingConfig.internal_static_google_logging_v2_ListSinksRequest_descriptor;
    }

    public com.google.logging.v2.ListSinksRequest getDefaultInstanceForType() {
      return com.google.logging.v2.ListSinksRequest.getDefaultInstance();
    }

    public com.google.logging.v2.ListSinksRequest build() {
      com.google.logging.v2.ListSinksRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.google.logging.v2.ListSinksRequest buildPartial() {
      com.google.logging.v2.ListSinksRequest result = new com.google.logging.v2.ListSinksRequest(this);
      result.projectName_ = projectName_;
      result.pageToken_ = pageToken_;
      result.pageSize_ = pageSize_;
      onBuilt();
      return result;
    }

    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.google.logging.v2.ListSinksRequest) {
        return mergeFrom((com.google.logging.v2.ListSinksRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.google.logging.v2.ListSinksRequest other) {
      if (other == com.google.logging.v2.ListSinksRequest.getDefaultInstance()) return this;
      if (!other.getProjectName().isEmpty()) {
        projectName_ = other.projectName_;
        onChanged();
      }
      if (!other.getPageToken().isEmpty()) {
        pageToken_ = other.pageToken_;
        onChanged();
      }
      if (other.getPageSize() != 0) {
        setPageSize(other.getPageSize());
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
      com.google.logging.v2.ListSinksRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.google.logging.v2.ListSinksRequest) e.getUnfinishedMessage();
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
     * Required. The resource name of the project containing the sinks.
     * Example: `"projects/my-logging-project"`, `"projects/01234567890"`.
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
     * Required. The resource name of the project containing the sinks.
     * Example: `"projects/my-logging-project"`, `"projects/01234567890"`.
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
     * Required. The resource name of the project containing the sinks.
     * Example: `"projects/my-logging-project"`, `"projects/01234567890"`.
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
     * Required. The resource name of the project containing the sinks.
     * Example: `"projects/my-logging-project"`, `"projects/01234567890"`.
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
     * Required. The resource name of the project containing the sinks.
     * Example: `"projects/my-logging-project"`, `"projects/01234567890"`.
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

    private java.lang.Object pageToken_ = "";
    /**
     * <code>optional string page_token = 2;</code>
     *
     * <pre>
     * Optional. If the `pageToken` request parameter is supplied, then the next
     * page of results in the set are retrieved.  The `pageToken` parameter must
     * be set with the value of the `nextPageToken` result parameter from the
     * previous request. The value of `projectName` must be the same as in the
     * previous request.
     * </pre>
     */
    public java.lang.String getPageToken() {
      java.lang.Object ref = pageToken_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        pageToken_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string page_token = 2;</code>
     *
     * <pre>
     * Optional. If the `pageToken` request parameter is supplied, then the next
     * page of results in the set are retrieved.  The `pageToken` parameter must
     * be set with the value of the `nextPageToken` result parameter from the
     * previous request. The value of `projectName` must be the same as in the
     * previous request.
     * </pre>
     */
    public com.google.protobuf.ByteString
        getPageTokenBytes() {
      java.lang.Object ref = pageToken_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        pageToken_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string page_token = 2;</code>
     *
     * <pre>
     * Optional. If the `pageToken` request parameter is supplied, then the next
     * page of results in the set are retrieved.  The `pageToken` parameter must
     * be set with the value of the `nextPageToken` result parameter from the
     * previous request. The value of `projectName` must be the same as in the
     * previous request.
     * </pre>
     */
    public Builder setPageToken(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      pageToken_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string page_token = 2;</code>
     *
     * <pre>
     * Optional. If the `pageToken` request parameter is supplied, then the next
     * page of results in the set are retrieved.  The `pageToken` parameter must
     * be set with the value of the `nextPageToken` result parameter from the
     * previous request. The value of `projectName` must be the same as in the
     * previous request.
     * </pre>
     */
    public Builder clearPageToken() {
      
      pageToken_ = getDefaultInstance().getPageToken();
      onChanged();
      return this;
    }
    /**
     * <code>optional string page_token = 2;</code>
     *
     * <pre>
     * Optional. If the `pageToken` request parameter is supplied, then the next
     * page of results in the set are retrieved.  The `pageToken` parameter must
     * be set with the value of the `nextPageToken` result parameter from the
     * previous request. The value of `projectName` must be the same as in the
     * previous request.
     * </pre>
     */
    public Builder setPageTokenBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      pageToken_ = value;
      onChanged();
      return this;
    }

    private int pageSize_ ;
    /**
     * <code>optional int32 page_size = 3;</code>
     *
     * <pre>
     * Optional. The maximum number of results to return from this request.  Fewer
     * results might be returned. You must check for the `nextPageToken` result to
     * determine if additional results are available, which you can retrieve by
     * passing the `nextPageToken` value in the `pageToken` parameter to the next
     * request.
     * </pre>
     */
    public int getPageSize() {
      return pageSize_;
    }
    /**
     * <code>optional int32 page_size = 3;</code>
     *
     * <pre>
     * Optional. The maximum number of results to return from this request.  Fewer
     * results might be returned. You must check for the `nextPageToken` result to
     * determine if additional results are available, which you can retrieve by
     * passing the `nextPageToken` value in the `pageToken` parameter to the next
     * request.
     * </pre>
     */
    public Builder setPageSize(int value) {
      
      pageSize_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int32 page_size = 3;</code>
     *
     * <pre>
     * Optional. The maximum number of results to return from this request.  Fewer
     * results might be returned. You must check for the `nextPageToken` result to
     * determine if additional results are available, which you can retrieve by
     * passing the `nextPageToken` value in the `pageToken` parameter to the next
     * request.
     * </pre>
     */
    public Builder clearPageSize() {
      
      pageSize_ = 0;
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


    // @@protoc_insertion_point(builder_scope:google.logging.v2.ListSinksRequest)
  }

  // @@protoc_insertion_point(class_scope:google.logging.v2.ListSinksRequest)
  private static final com.google.logging.v2.ListSinksRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.google.logging.v2.ListSinksRequest();
  }

  public static com.google.logging.v2.ListSinksRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ListSinksRequest>
      PARSER = new com.google.protobuf.AbstractParser<ListSinksRequest>() {
    public ListSinksRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      try {
        return new ListSinksRequest(input, extensionRegistry);
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

  public static com.google.protobuf.Parser<ListSinksRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ListSinksRequest> getParserForType() {
    return PARSER;
  }

  public com.google.logging.v2.ListSinksRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
