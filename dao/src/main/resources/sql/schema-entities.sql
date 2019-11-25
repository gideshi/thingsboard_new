/*
 Navicat PostgreSQL Data Transfer

 Source Server         : postgresql
 Source Server Type    : PostgreSQL
 Source Server Version : 110001
 Source Host           : localhost:5432
 Source Catalog        : thingsboard24M
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 110001
 File Encoding         : 65001

 Date: 25/11/2019 23:18:34
*/


-- ----------------------------
-- Table structure for admin_settings
-- ----------------------------
DROP TABLE IF EXISTS "public"."admin_settings";
CREATE TABLE "public"."admin_settings" (
  "id" varchar(31) COLLATE "pg_catalog"."default" NOT NULL,
  "json_value" varchar COLLATE "pg_catalog"."default",
  "key" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for alarm
-- ----------------------------
DROP TABLE IF EXISTS "public"."alarm";
CREATE TABLE "public"."alarm" (
  "id" varchar(31) COLLATE "pg_catalog"."default" NOT NULL,
  "ack_ts" int8,
  "clear_ts" int8,
  "additional_info" varchar COLLATE "pg_catalog"."default",
  "end_ts" int8,
  "originator_id" varchar(31) COLLATE "pg_catalog"."default",
  "originator_type" int4,
  "propagate" bool,
  "severity" varchar(255) COLLATE "pg_catalog"."default",
  "start_ts" int8,
  "status" varchar(255) COLLATE "pg_catalog"."default",
  "tenant_id" varchar(31) COLLATE "pg_catalog"."default",
  "type" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for asset
-- ----------------------------
DROP TABLE IF EXISTS "public"."asset";
CREATE TABLE "public"."asset" (
  "id" varchar(31) COLLATE "pg_catalog"."default" NOT NULL,
  "additional_info" varchar COLLATE "pg_catalog"."default",
  "customer_id" varchar(31) COLLATE "pg_catalog"."default",
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "search_text" varchar(255) COLLATE "pg_catalog"."default",
  "tenant_id" varchar(31) COLLATE "pg_catalog"."default",
  "type_id" varchar(31) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for attribute_kv
-- ----------------------------
DROP TABLE IF EXISTS "public"."attribute_kv";
CREATE TABLE "public"."attribute_kv" (
  "entity_type" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "entity_id" varchar(31) COLLATE "pg_catalog"."default" NOT NULL,
  "attribute_type" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "attribute_key" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "bool_v" bool,
  "str_v" varchar(10000000) COLLATE "pg_catalog"."default",
  "long_v" int8,
  "dbl_v" float8,
  "last_update_ts" int8
)
;

-- ----------------------------
-- Table structure for audit_log
-- ----------------------------
DROP TABLE IF EXISTS "public"."audit_log";
CREATE TABLE "public"."audit_log" (
  "id" varchar(31) COLLATE "pg_catalog"."default" NOT NULL,
  "tenant_id" varchar(31) COLLATE "pg_catalog"."default",
  "customer_id" varchar(31) COLLATE "pg_catalog"."default",
  "entity_id" varchar(31) COLLATE "pg_catalog"."default",
  "entity_type" varchar(255) COLLATE "pg_catalog"."default",
  "entity_name" varchar(255) COLLATE "pg_catalog"."default",
  "user_id" varchar(31) COLLATE "pg_catalog"."default",
  "user_name" varchar(255) COLLATE "pg_catalog"."default",
  "action_type" varchar(255) COLLATE "pg_catalog"."default",
  "action_data" varchar(1000000) COLLATE "pg_catalog"."default",
  "action_status" varchar(255) COLLATE "pg_catalog"."default",
  "action_failure_details" varchar(1000000) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for component_descriptor
-- ----------------------------
DROP TABLE IF EXISTS "public"."component_descriptor";
CREATE TABLE "public"."component_descriptor" (
  "id" varchar(31) COLLATE "pg_catalog"."default" NOT NULL,
  "actions" varchar(255) COLLATE "pg_catalog"."default",
  "clazz" varchar COLLATE "pg_catalog"."default",
  "configuration_descriptor" varchar COLLATE "pg_catalog"."default",
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "scope" varchar(255) COLLATE "pg_catalog"."default",
  "search_text" varchar(255) COLLATE "pg_catalog"."default",
  "type" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS "public"."customer";
CREATE TABLE "public"."customer" (
  "id" varchar(31) COLLATE "pg_catalog"."default" NOT NULL,
  "additional_info" varchar COLLATE "pg_catalog"."default",
  "address" varchar COLLATE "pg_catalog"."default",
  "address2" varchar COLLATE "pg_catalog"."default",
  "city" varchar(255) COLLATE "pg_catalog"."default",
  "country" varchar(255) COLLATE "pg_catalog"."default",
  "email" varchar(255) COLLATE "pg_catalog"."default",
  "phone" varchar(255) COLLATE "pg_catalog"."default",
  "search_text" varchar(255) COLLATE "pg_catalog"."default",
  "state" varchar(255) COLLATE "pg_catalog"."default",
  "tenant_id" varchar(31) COLLATE "pg_catalog"."default",
  "title" varchar(255) COLLATE "pg_catalog"."default",
  "zip" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for dashboard
-- ----------------------------
DROP TABLE IF EXISTS "public"."dashboard";
CREATE TABLE "public"."dashboard" (
  "id" varchar(31) COLLATE "pg_catalog"."default" NOT NULL,
  "configuration" varchar(10000000) COLLATE "pg_catalog"."default",
  "assigned_customers" varchar(1000000) COLLATE "pg_catalog"."default",
  "search_text" varchar(255) COLLATE "pg_catalog"."default",
  "tenant_id" varchar(31) COLLATE "pg_catalog"."default",
  "title" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for dashboard_type
-- ----------------------------
DROP TABLE IF EXISTS "public"."dashboard_type";
CREATE TABLE "public"."dashboard_type" (
  "id" varchar(31) COLLATE "pg_catalog"."default" NOT NULL,
  "configuration" varchar(10000000) COLLATE "pg_catalog"."default",
  "assigned_customers" varchar(1000000) COLLATE "pg_catalog"."default",
  "search_text" varchar(255) COLLATE "pg_catalog"."default",
  "tenant_id" varchar(31) COLLATE "pg_catalog"."default",
  "title" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS "public"."device";
CREATE TABLE "public"."device" (
  "id" varchar(31) COLLATE "pg_catalog"."default" NOT NULL,
  "additional_info" varchar COLLATE "pg_catalog"."default",
  "customer_id" varchar(31) COLLATE "pg_catalog"."default",
  "type_id" varchar(31) COLLATE "pg_catalog"."default",
  "spec" varchar(255) COLLATE "pg_catalog"."default",
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "label" varchar(255) COLLATE "pg_catalog"."default",
  "search_text" varchar(255) COLLATE "pg_catalog"."default",
  "tenant_id" varchar(31) COLLATE "pg_catalog"."default",
  "dashboard_id" varchar(31) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for device_credentials
-- ----------------------------
DROP TABLE IF EXISTS "public"."device_credentials";
CREATE TABLE "public"."device_credentials" (
  "id" varchar(31) COLLATE "pg_catalog"."default" NOT NULL,
  "credentials_id" varchar COLLATE "pg_catalog"."default",
  "credentials_type" varchar(255) COLLATE "pg_catalog"."default",
  "credentials_value" varchar COLLATE "pg_catalog"."default",
  "device_id" varchar(31) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for entity_view
-- ----------------------------
DROP TABLE IF EXISTS "public"."entity_view";
CREATE TABLE "public"."entity_view" (
  "id" varchar(31) COLLATE "pg_catalog"."default" NOT NULL,
  "entity_id" varchar(31) COLLATE "pg_catalog"."default",
  "entity_type" varchar(255) COLLATE "pg_catalog"."default",
  "tenant_id" varchar(31) COLLATE "pg_catalog"."default",
  "customer_id" varchar(31) COLLATE "pg_catalog"."default",
  "type_id" varchar(31) COLLATE "pg_catalog"."default",
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "keys" varchar(10000000) COLLATE "pg_catalog"."default",
  "start_ts" int8,
  "end_ts" int8,
  "search_text" varchar(255) COLLATE "pg_catalog"."default",
  "additional_info" varchar COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for event
-- ----------------------------
DROP TABLE IF EXISTS "public"."event";
CREATE TABLE "public"."event" (
  "id" varchar(31) COLLATE "pg_catalog"."default" NOT NULL,
  "body" varchar(10000000) COLLATE "pg_catalog"."default",
  "entity_id" varchar(31) COLLATE "pg_catalog"."default",
  "entity_type" varchar(255) COLLATE "pg_catalog"."default",
  "event_type" varchar(255) COLLATE "pg_catalog"."default",
  "event_uid" varchar(255) COLLATE "pg_catalog"."default",
  "tenant_id" varchar(31) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for relation
-- ----------------------------
DROP TABLE IF EXISTS "public"."relation";
CREATE TABLE "public"."relation" (
  "from_id" varchar(31) COLLATE "pg_catalog"."default" NOT NULL,
  "from_type" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "to_id" varchar(31) COLLATE "pg_catalog"."default" NOT NULL,
  "to_type" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "relation_type_group" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "relation_type" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "additional_info" varchar COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for rule_chain
-- ----------------------------
DROP TABLE IF EXISTS "public"."rule_chain";
CREATE TABLE "public"."rule_chain" (
  "id" varchar(31) COLLATE "pg_catalog"."default" NOT NULL,
  "additional_info" varchar COLLATE "pg_catalog"."default",
  "configuration" varchar(10000000) COLLATE "pg_catalog"."default",
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "first_rule_node_id" varchar(31) COLLATE "pg_catalog"."default",
  "root" bool,
  "debug_mode" bool,
  "search_text" varchar(255) COLLATE "pg_catalog"."default",
  "tenant_id" varchar(31) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for rule_node
-- ----------------------------
DROP TABLE IF EXISTS "public"."rule_node";
CREATE TABLE "public"."rule_node" (
  "id" varchar(31) COLLATE "pg_catalog"."default" NOT NULL,
  "rule_chain_id" varchar(31) COLLATE "pg_catalog"."default",
  "additional_info" varchar COLLATE "pg_catalog"."default",
  "configuration" varchar(10000000) COLLATE "pg_catalog"."default",
  "type" varchar(255) COLLATE "pg_catalog"."default",
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "debug_mode" bool,
  "search_text" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."tb_user";
CREATE TABLE "public"."tb_user" (
  "id" varchar(31) COLLATE "pg_catalog"."default" NOT NULL,
  "additional_info" varchar COLLATE "pg_catalog"."default",
  "authority" varchar(255) COLLATE "pg_catalog"."default",
  "customer_id" varchar(31) COLLATE "pg_catalog"."default",
  "email" varchar(255) COLLATE "pg_catalog"."default",
  "first_name" varchar(255) COLLATE "pg_catalog"."default",
  "last_name" varchar(255) COLLATE "pg_catalog"."default",
  "search_text" varchar(255) COLLATE "pg_catalog"."default",
  "tenant_id" varchar(31) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for tenant
-- ----------------------------
DROP TABLE IF EXISTS "public"."tenant";
CREATE TABLE "public"."tenant" (
  "id" varchar(31) COLLATE "pg_catalog"."default" NOT NULL,
  "additional_info" varchar COLLATE "pg_catalog"."default",
  "address" varchar COLLATE "pg_catalog"."default",
  "address2" varchar COLLATE "pg_catalog"."default",
  "city" varchar(255) COLLATE "pg_catalog"."default",
  "country" varchar(255) COLLATE "pg_catalog"."default",
  "email" varchar(255) COLLATE "pg_catalog"."default",
  "phone" varchar(255) COLLATE "pg_catalog"."default",
  "region" varchar(255) COLLATE "pg_catalog"."default",
  "search_text" varchar(255) COLLATE "pg_catalog"."default",
  "state" varchar(255) COLLATE "pg_catalog"."default",
  "title" varchar(255) COLLATE "pg_catalog"."default",
  "zip" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for ts_kv
-- ----------------------------
DROP TABLE IF EXISTS "public"."ts_kv";
CREATE TABLE "public"."ts_kv" (
  "entity_type" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "entity_id" varchar(31) COLLATE "pg_catalog"."default" NOT NULL,
  "key" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "ts" int8 NOT NULL,
  "bool_v" bool,
  "str_v" varchar(10000000) COLLATE "pg_catalog"."default",
  "long_v" int8,
  "dbl_v" float8
)
;

-- ----------------------------
-- Table structure for ts_kv_latest
-- ----------------------------
DROP TABLE IF EXISTS "public"."ts_kv_latest";
CREATE TABLE "public"."ts_kv_latest" (
  "entity_type" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "entity_id" varchar(31) COLLATE "pg_catalog"."default" NOT NULL,
  "key" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "ts" int8 NOT NULL,
  "bool_v" bool,
  "str_v" varchar(10000000) COLLATE "pg_catalog"."default",
  "long_v" int8,
  "dbl_v" float8
)
;

-- ----------------------------
-- Table structure for type_spec
-- ----------------------------
DROP TABLE IF EXISTS "public"."type_spec";
CREATE TABLE "public"."type_spec" (
  "id" varchar(31) COLLATE "pg_catalog"."default" NOT NULL,
  "pid" varchar(31) COLLATE "pg_catalog"."default",
  "customer_id" varchar(31) COLLATE "pg_catalog"."default",
  "type" varchar(255) COLLATE "pg_catalog"."default",
  "type_name" varchar(255) COLLATE "pg_catalog"."default",
  "label" varchar(255) COLLATE "pg_catalog"."default",
  "search_text" varchar(255) COLLATE "pg_catalog"."default",
  "tenant_id" varchar(31) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for user_credentials
-- ----------------------------
DROP TABLE IF EXISTS "public"."user_credentials";
CREATE TABLE "public"."user_credentials" (
  "id" varchar(31) COLLATE "pg_catalog"."default" NOT NULL,
  "activate_token" varchar(255) COLLATE "pg_catalog"."default",
  "enabled" bool,
  "password" varchar(255) COLLATE "pg_catalog"."default",
  "reset_token" varchar(255) COLLATE "pg_catalog"."default",
  "user_id" varchar(31) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for widget_type
-- ----------------------------
DROP TABLE IF EXISTS "public"."widget_type";
CREATE TABLE "public"."widget_type" (
  "id" varchar(31) COLLATE "pg_catalog"."default" NOT NULL,
  "alias" varchar(255) COLLATE "pg_catalog"."default",
  "bundle_alias" varchar(255) COLLATE "pg_catalog"."default",
  "descriptor" varchar(1000000) COLLATE "pg_catalog"."default",
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "tenant_id" varchar(31) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for widgets_bundle
-- ----------------------------
DROP TABLE IF EXISTS "public"."widgets_bundle";
CREATE TABLE "public"."widgets_bundle" (
  "id" varchar(31) COLLATE "pg_catalog"."default" NOT NULL,
  "alias" varchar(255) COLLATE "pg_catalog"."default",
  "search_text" varchar(255) COLLATE "pg_catalog"."default",
  "tenant_id" varchar(31) COLLATE "pg_catalog"."default",
  "title" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Primary Key structure for table admin_settings
-- ----------------------------
ALTER TABLE "public"."admin_settings" ADD CONSTRAINT "admin_settings_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table alarm
-- ----------------------------
CREATE INDEX "idx_alarm_originator_alarm_type" ON "public"."alarm" USING btree (
  "tenant_id" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST,
  "type" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST,
  "originator_type" "pg_catalog"."int4_ops" ASC NULLS LAST,
  "originator_id" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table alarm
-- ----------------------------
ALTER TABLE "public"."alarm" ADD CONSTRAINT "alarm_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table asset
-- ----------------------------
ALTER TABLE "public"."asset" ADD CONSTRAINT "asset_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table attribute_kv
-- ----------------------------
ALTER TABLE "public"."attribute_kv" ADD CONSTRAINT "attribute_kv_pkey" PRIMARY KEY ("entity_type", "entity_id", "attribute_type", "attribute_key");

-- ----------------------------
-- Primary Key structure for table audit_log
-- ----------------------------
ALTER TABLE "public"."audit_log" ADD CONSTRAINT "audit_log_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table component_descriptor
-- ----------------------------
ALTER TABLE "public"."component_descriptor" ADD CONSTRAINT "component_descriptor_clazz_key" UNIQUE ("clazz");

-- ----------------------------
-- Primary Key structure for table component_descriptor
-- ----------------------------
ALTER TABLE "public"."component_descriptor" ADD CONSTRAINT "component_descriptor_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table customer
-- ----------------------------
ALTER TABLE "public"."customer" ADD CONSTRAINT "customer_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table dashboard
-- ----------------------------
ALTER TABLE "public"."dashboard" ADD CONSTRAINT "dashboard_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table dashboard_type
-- ----------------------------
ALTER TABLE "public"."dashboard_type" ADD CONSTRAINT "dashboard_copy1_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table device
-- ----------------------------
ALTER TABLE "public"."device" ADD CONSTRAINT "device_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table device_credentials
-- ----------------------------
ALTER TABLE "public"."device_credentials" ADD CONSTRAINT "device_credentials_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table entity_view
-- ----------------------------
ALTER TABLE "public"."entity_view" ADD CONSTRAINT "entity_view_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table event
-- ----------------------------
CREATE INDEX "idx_event_type_entity_id" ON "public"."event" USING btree (
  "tenant_id" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST,
  "event_type" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST,
  "entity_type" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST,
  "entity_id" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Uniques structure for table event
-- ----------------------------
ALTER TABLE "public"."event" ADD CONSTRAINT "event_unq_key" UNIQUE ("tenant_id", "entity_type", "entity_id", "event_type", "event_uid");

-- ----------------------------
-- Primary Key structure for table event
-- ----------------------------
ALTER TABLE "public"."event" ADD CONSTRAINT "event_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table relation
-- ----------------------------
ALTER TABLE "public"."relation" ADD CONSTRAINT "relation_pkey" PRIMARY KEY ("from_id", "from_type", "relation_type_group", "relation_type", "to_id", "to_type");

-- ----------------------------
-- Primary Key structure for table rule_chain
-- ----------------------------
ALTER TABLE "public"."rule_chain" ADD CONSTRAINT "rule_chain_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table rule_node
-- ----------------------------
ALTER TABLE "public"."rule_node" ADD CONSTRAINT "rule_node_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table tb_user
-- ----------------------------
ALTER TABLE "public"."tb_user" ADD CONSTRAINT "tb_user_email_key" UNIQUE ("email");

-- ----------------------------
-- Primary Key structure for table tb_user
-- ----------------------------
ALTER TABLE "public"."tb_user" ADD CONSTRAINT "tb_user_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table tenant
-- ----------------------------
ALTER TABLE "public"."tenant" ADD CONSTRAINT "tenant_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table ts_kv
-- ----------------------------
ALTER TABLE "public"."ts_kv" ADD CONSTRAINT "ts_kv_pkey" PRIMARY KEY ("entity_type", "entity_id", "key", "ts");

-- ----------------------------
-- Primary Key structure for table ts_kv_latest
-- ----------------------------
ALTER TABLE "public"."ts_kv_latest" ADD CONSTRAINT "ts_kv_latest_pkey" PRIMARY KEY ("entity_type", "entity_id", "key");

-- ----------------------------
-- Primary Key structure for table type_spec
-- ----------------------------
ALTER TABLE "public"."type_spec" ADD CONSTRAINT "type_spec_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table user_credentials
-- ----------------------------
ALTER TABLE "public"."user_credentials" ADD CONSTRAINT "user_credentials_activate_token_key" UNIQUE ("activate_token");
ALTER TABLE "public"."user_credentials" ADD CONSTRAINT "user_credentials_reset_token_key" UNIQUE ("reset_token");
ALTER TABLE "public"."user_credentials" ADD CONSTRAINT "user_credentials_user_id_key" UNIQUE ("user_id");

-- ----------------------------
-- Primary Key structure for table user_credentials
-- ----------------------------
ALTER TABLE "public"."user_credentials" ADD CONSTRAINT "user_credentials_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table widget_type
-- ----------------------------
ALTER TABLE "public"."widget_type" ADD CONSTRAINT "widget_type_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table widgets_bundle
-- ----------------------------
ALTER TABLE "public"."widgets_bundle" ADD CONSTRAINT "widgets_bundle_pkey" PRIMARY KEY ("id");
