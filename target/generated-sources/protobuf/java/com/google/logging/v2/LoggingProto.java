// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/logging/v2/logging.proto

package com.google.logging.v2;

public final class LoggingProto {
  private LoggingProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_google_logging_v2_DeleteLogRequest_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_google_logging_v2_DeleteLogRequest_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_google_logging_v2_WriteLogEntriesRequest_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_google_logging_v2_WriteLogEntriesRequest_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_google_logging_v2_WriteLogEntriesRequest_LabelsEntry_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_google_logging_v2_WriteLogEntriesRequest_LabelsEntry_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_google_logging_v2_WriteLogEntriesResponse_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_google_logging_v2_WriteLogEntriesResponse_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_google_logging_v2_ListLogEntriesRequest_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_google_logging_v2_ListLogEntriesRequest_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_google_logging_v2_ListLogEntriesResponse_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_google_logging_v2_ListLogEntriesResponse_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_google_logging_v2_ListMonitoredResourceDescriptorsRequest_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_google_logging_v2_ListMonitoredResourceDescriptorsRequest_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_google_logging_v2_ListMonitoredResourceDescriptorsResponse_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_google_logging_v2_ListMonitoredResourceDescriptorsResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\037google/logging/v2/logging.proto\022\021googl" +
      "e.logging.v2\032\034google/api/annotations.pro" +
      "to\032#google/api/monitored_resource.proto\032" +
      "!google/logging/v2/log_entry.proto\032\033goog" +
      "le/protobuf/empty.proto\032\027google/rpc/stat" +
      "us.proto\"$\n\020DeleteLogRequest\022\020\n\010log_name" +
      "\030\001 \001(\t\"\377\001\n\026WriteLogEntriesRequest\022\020\n\010log" +
      "_name\030\001 \001(\t\022/\n\010resource\030\002 \001(\0132\035.google.a" +
      "pi.MonitoredResource\022E\n\006labels\030\003 \003(\01325.g" +
      "oogle.logging.v2.WriteLogEntriesRequest.",
      "LabelsEntry\022,\n\007entries\030\004 \003(\0132\033.google.lo" +
      "gging.v2.LogEntry\032-\n\013LabelsEntry\022\013\n\003key\030" +
      "\001 \001(\t\022\r\n\005value\030\002 \001(\t:\0028\001\"\031\n\027WriteLogEntr" +
      "iesResponse\"u\n\025ListLogEntriesRequest\022\023\n\013" +
      "project_ids\030\001 \003(\t\022\016\n\006filter\030\002 \001(\t\022\020\n\010ord" +
      "er_by\030\003 \001(\t\022\021\n\tpage_size\030\004 \001(\005\022\022\n\npage_t" +
      "oken\030\005 \001(\t\"_\n\026ListLogEntriesResponse\022,\n\007" +
      "entries\030\001 \003(\0132\033.google.logging.v2.LogEnt" +
      "ry\022\027\n\017next_page_token\030\002 \001(\t\"P\n\'ListMonit" +
      "oredResourceDescriptorsRequest\022\021\n\tpage_s",
      "ize\030\001 \001(\005\022\022\n\npage_token\030\002 \001(\t\"\212\001\n(ListMo" +
      "nitoredResourceDescriptorsResponse\022E\n\024re" +
      "source_descriptors\030\001 \003(\0132\'.google.api.Mo" +
      "nitoredResourceDescriptor\022\027\n\017next_page_t" +
      "oken\030\002 \001(\t2\360\004\n\020LoggingServiceV2\022w\n\tDelet" +
      "eLog\022#.google.logging.v2.DeleteLogReques" +
      "t\032\026.google.protobuf.Empty\"-\202\323\344\223\002\'*%/v2be" +
      "ta1/{log_name=projects/*/logs/*}\022\213\001\n\017Wri" +
      "teLogEntries\022).google.logging.v2.WriteLo" +
      "gEntriesRequest\032*.google.logging.v2.Writ",
      "eLogEntriesResponse\"!\202\323\344\223\002\033\"\026/v2beta1/en" +
      "tries:write:\001*\022\207\001\n\016ListLogEntries\022(.goog" +
      "le.logging.v2.ListLogEntriesRequest\032).go" +
      "ogle.logging.v2.ListLogEntriesResponse\" " +
      "\202\323\344\223\002\032\"\025/v2beta1/entries:list:\001*\022\312\001\n Lis" +
      "tMonitoredResourceDescriptors\022:.google.l" +
      "ogging.v2.ListMonitoredResourceDescripto" +
      "rsRequest\032;.google.logging.v2.ListMonito" +
      "redResourceDescriptorsResponse\"-\202\323\344\223\002\'\022%" +
      "/v2beta1/monitoredResourceDescriptorsB*\n",
      "\025com.google.logging.v2B\014LoggingProtoP\001\370\001" +
      "\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.api.AnnotationsProto.getDescriptor(),
          com.google.api.MonitoredResourceProto.getDescriptor(),
          com.google.logging.v2.LogEntryProto.getDescriptor(),
          com.google.protobuf.EmptyProto.getDescriptor(),
          com.google.rpc.StatusProto.getDescriptor(),
        }, assigner);
    internal_static_google_logging_v2_DeleteLogRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_google_logging_v2_DeleteLogRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_google_logging_v2_DeleteLogRequest_descriptor,
        new java.lang.String[] { "LogName", });
    internal_static_google_logging_v2_WriteLogEntriesRequest_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_google_logging_v2_WriteLogEntriesRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_google_logging_v2_WriteLogEntriesRequest_descriptor,
        new java.lang.String[] { "LogName", "Resource", "Labels", "Entries", });
    internal_static_google_logging_v2_WriteLogEntriesRequest_LabelsEntry_descriptor =
      internal_static_google_logging_v2_WriteLogEntriesRequest_descriptor.getNestedTypes().get(0);
    internal_static_google_logging_v2_WriteLogEntriesRequest_LabelsEntry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_google_logging_v2_WriteLogEntriesRequest_LabelsEntry_descriptor,
        new java.lang.String[] { "Key", "Value", });
    internal_static_google_logging_v2_WriteLogEntriesResponse_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_google_logging_v2_WriteLogEntriesResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_google_logging_v2_WriteLogEntriesResponse_descriptor,
        new java.lang.String[] { });
    internal_static_google_logging_v2_ListLogEntriesRequest_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_google_logging_v2_ListLogEntriesRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_google_logging_v2_ListLogEntriesRequest_descriptor,
        new java.lang.String[] { "ProjectIds", "Filter", "OrderBy", "PageSize", "PageToken", });
    internal_static_google_logging_v2_ListLogEntriesResponse_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_google_logging_v2_ListLogEntriesResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_google_logging_v2_ListLogEntriesResponse_descriptor,
        new java.lang.String[] { "Entries", "NextPageToken", });
    internal_static_google_logging_v2_ListMonitoredResourceDescriptorsRequest_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_google_logging_v2_ListMonitoredResourceDescriptorsRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_google_logging_v2_ListMonitoredResourceDescriptorsRequest_descriptor,
        new java.lang.String[] { "PageSize", "PageToken", });
    internal_static_google_logging_v2_ListMonitoredResourceDescriptorsResponse_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_google_logging_v2_ListMonitoredResourceDescriptorsResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_google_logging_v2_ListMonitoredResourceDescriptorsResponse_descriptor,
        new java.lang.String[] { "ResourceDescriptors", "NextPageToken", });
    com.google.protobuf.ExtensionRegistry registry =
        com.google.protobuf.ExtensionRegistry.newInstance();
    registry.add(com.google.api.AnnotationsProto.http);
    com.google.protobuf.Descriptors.FileDescriptor
        .internalUpdateFileDescriptor(descriptor, registry);
    com.google.api.AnnotationsProto.getDescriptor();
    com.google.api.MonitoredResourceProto.getDescriptor();
    com.google.logging.v2.LogEntryProto.getDescriptor();
    com.google.protobuf.EmptyProto.getDescriptor();
    com.google.rpc.StatusProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}