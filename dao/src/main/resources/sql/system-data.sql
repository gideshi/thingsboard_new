
delete from admin_settings; 
INSERT INTO "admin_settings" VALUES ('1e746126a2266e4a91992ebcb67fe33', '{
	"baseUrl": "http://localhost:8080"
}', 'general');
INSERT INTO "admin_settings" VALUES ('1e746126eaaefa6a91992ebcb67fe33', '{
	"mailFrom": "Thingsboard <sysadmin@localhost.localdomain>",
	"smtpProtocol": "smtp",
	"smtpHost": "localhost",
	"smtpPort": "25",
	"timeout": "10000",
	"enableTls": "false",
	"username": "",
	"password": ""
}', 'mail');

delete from tb_user;
INSERT INTO "tb_user" VALUES ('1e746125a797660a91992ebcb67fe33', '{"lastLoginTs":1571383005697,"failedLoginAttempts":0}', 'SYS_ADMIN', '1b21dd2138140008080808080808080', 'sysadmin@thingsboard.org', NULL, NULL, 'sysadmin@thingsboard.org', '1b21dd2138140008080808080808080');
INSERT INTO "tb_user" VALUES ('1e9eff855d6d0209cfbd1d052e19e75', '{"userPasswordHistory":{"1571218569796":"$2a$10$CqSWfXCTx7MVCs9clFNQPuPAmWj0SdJ1UciIQ9plCNqDG/OV9M3Ii"},"lastLoginTs":1574680121317,"failedLoginAttempts":0}', 'TENANT_ADMIN', '1b21dd2138140008080808080808080', '7864051@qq.com', NULL, NULL, '7864051@qq.com', '1e9dac9ea0f1750aa93bbd6f4a91b88');

delete from type_spec;
INSERT INTO "type_spec" VALUES ('1e7461261441950a91992ebcb67fe33', '', '', 'DEVICE', '设备', '', '', '1e9dac9ea0f1750aa93bbd6f4a91b88');
INSERT INTO "type_spec" VALUES ('2e7461261441950a91992ebcb67fe33', '', '', 'EQUIPMENT', '装置', '', '', '1e9dac9ea0f1750aa93bbd6f4a91b88');
INSERT INTO "type_spec" VALUES ('3e7461261441950a91992ebcb67fe33', '', '', 'CHANNEL', '通道', '', '', '1e9dac9ea0f1750aa93bbd6f4a91b88');
INSERT INTO "type_spec" VALUES ('4e7461261441950a91992ebcb67fe33', '', '', 'SITE', '站点', '', '', '1e9dac9ea0f1750aa93bbd6f4a91b88');
INSERT INTO "type_spec" VALUES ('2e746126eaaefa6a91992ebcb67fe33', '1e7461261441950a91992ebcb67fe33', '', 'THSENSOR', '温湿度传感器', '', '', '1e9dac9ea0f1750aa93bbd6f4a91b88');
INSERT INTO "type_spec" VALUES ('3e746126eaaefa6a91992ebcb67fe33', '1e7461261441950a91992ebcb67fe33', '', 'SMOKESENSOR', '烟感传感器', '', '', '1e9dac9ea0f1750aa93bbd6f4a91b88');
INSERT INTO "type_spec" VALUES ('4e746126eaaefa6a91992ebcb67fe33', '1e7461261441950a91992ebcb67fe33', '', 'VIBRATESENSOR', '震动传感器', '', '', '1e9dac9ea0f1750aa93bbd6f4a91b88');
INSERT INTO "type_spec" VALUES ('5e746126eaaefa6a91992ebcb67fe33', '1e7461261441950a91992ebcb67fe33', '', 'SHSENSOR', '六氟化硫传感器', '', '', '1e9dac9ea0f1750aa93bbd6f4a91b88');
INSERT INTO "type_spec" VALUES ('6e746126eaaefa6a91992ebcb67fe33', '1e7461261441950a91992ebcb67fe33', '', 'SMARTLOCK', '智能锁', '', '', '1e9dac9ea0f1750aa93bbd6f4a91b88');
INSERT INTO "type_spec" VALUES ('7e746126eaaefa6a91992ebcb67fe33', '1e7461261441950a91992ebcb67fe33', '', 'SENSORCONTROLLER', '传感控制器', '', '', '1e9dac9ea0f1750aa93bbd6f4a91b88');


delete from user_credentials;  
INSERT INTO "user_credentials" VALUES ('1e7461261441950a91992ebcb67fe33', NULL, 't', '$2a$10$5JTB8/hxWc9WAy62nCGSxeefl3KWmipA9nFpVdDa0/xfIseeBB4Bu', NULL, '1e746125a797660a91992ebcb67fe33');
INSERT INTO "user_credentials" VALUES ('1e9eff855d87dd09cfbd1d052e19e75', NULL, 't', '$2a$10$CqSWfXCTx7MVCs9clFNQPuPAmWj0SdJ1UciIQ9plCNqDG/OV9M3Ii', NULL, '1e9eff855d6d0209cfbd1d052e19e75');
