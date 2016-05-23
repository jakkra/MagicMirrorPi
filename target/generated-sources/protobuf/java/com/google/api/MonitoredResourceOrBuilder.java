// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/api/monitored_resource.proto

package com.google.api;

public interface MonitoredResourceOrBuilder extends
    // @@protoc_insertion_point(interface_extends:google.api.MonitoredResource)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional string type = 1;</code>
   *
   * <pre>
   * The monitored resource type. This field must match the corresponding
   * [MonitoredResourceDescriptor.type][google.api.MonitoredResourceDescriptor.type] to this resource..  For example,
   * `"cloudsql_database"` represents Cloud SQL databases.
   * </pre>
   */
  java.lang.String getType();
  /**
   * <code>optional string type = 1;</code>
   *
   * <pre>
   * The monitored resource type. This field must match the corresponding
   * [MonitoredResourceDescriptor.type][google.api.MonitoredResourceDescriptor.type] to this resource..  For example,
   * `"cloudsql_database"` represents Cloud SQL databases.
   * </pre>
   */
  com.google.protobuf.ByteString
      getTypeBytes();

  /**
   * <code>map&lt;string, string&gt; labels = 2;</code>
   *
   * <pre>
   * Values for some or all of the labels listed in the associated monitored
   * resource descriptor. For example, you specify a specific Cloud SQL database
   * by supplying values for both the `"database_id"` and `"zone"` labels.
   * </pre>
   */
  java.util.Map<java.lang.String, java.lang.String>
  getLabels();
}
