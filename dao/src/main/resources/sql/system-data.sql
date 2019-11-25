--
-- Copyright © 2016-2019 The Thingsboard Authors
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
--     http://www.apache.org/licenses/LICENSE-2.0
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

/** SYSTEM **/

/** System admin **/
delete from tb_user;
INSERT INTO tb_user ( id, tenant_id, customer_id, email, search_text, authority )
VALUES ( '1e746125a797660a91992ebcb67fe33', '1b21dd2138140008080808080808080', '1b21dd2138140008080808080808080', 'sysadmin@thingsboard.org',
         'sysadmin@thingsboard.org', 'SYS_ADMIN' );

delete from user_credentials;         
INSERT INTO user_credentials ( id, user_id, enabled, password )
VALUES ( '1e7461261441950a91992ebcb67fe33', '1e746125a797660a91992ebcb67fe33', true,
         '$2a$10$5JTB8/hxWc9WAy62nCGSxeefl3KWmipA9nFpVdDa0/xfIseeBB4Bu' );

/** System settings **/
delete from admin_settings; 
INSERT INTO admin_settings ( id, key, json_value )
VALUES ( '1e746126a2266e4a91992ebcb67fe33', 'general', '{
	"baseUrl": "http://localhost:8080"
}' );

INSERT INTO admin_settings ( id, key, json_value )
VALUES ( '1e746126eaaefa6a91992ebcb67fe33', 'mail', '{
	"mailFrom": "Thingsboard <sysadmin@localhost.localdomain>",
	"smtpProtocol": "smtp",
	"smtpHost": "localhost",
	"smtpPort": "25",
	"timeout": "10000",
	"enableTls": "false",
	"username": "",
	"password": ""
}' );

delete from type_spec;
INSERT INTO type_spec ( id, pid, customer_id, type, type_name, label, search_text, tenant_id )
VALUES ( '2e746126eaaefa6a91992ebcb67fe33', 
    '1e7461261441950a91992ebcb67fe33', 
	'',
	'THSENSOR',
	'温湿度传感器',
	'',
	'',
	'' );
INSERT INTO type_spec ( id, pid, customer_id, type, type_name, label, search_text, tenant_id )
VALUES ( '3e746126eaaefa6a91992ebcb67fe33', 
    '1e7461261441950a91992ebcb67fe33', 
	'',
	'SMOKESENSOR',
	'烟感传感器',
	'',
	'',
	'' );
INSERT INTO type_spec ( id, pid, customer_id, type, type_name, label, search_text, tenant_id )
VALUES ( '4e746126eaaefa6a91992ebcb67fe33', 
    '1e7461261441950a91992ebcb67fe33', 
	'',
	'VIBRATESENSOR',
	'震动传感器',
	'',
	'',
	'' );
INSERT INTO type_spec ( id, pid, customer_id, type, type_name, label, search_text, tenant_id )
VALUES ( '5e746126eaaefa6a91992ebcb67fe33', 
    '1e7461261441950a91992ebcb67fe33', 
	'',
	'SHSENSOR',
	'六氟化硫传感器',
	'',
	'',
	'' );
INSERT INTO type_spec ( id, pid, customer_id, type, type_name, label, search_text, tenant_id )
VALUES ( '6e746126eaaefa6a91992ebcb67fe33', 
    '1e7461261441950a91992ebcb67fe33', 
	'',
	'SMARTLOCK',
	'智能锁',
	'',
	'',
	'' );
INSERT INTO type_spec ( id, pid, customer_id, type, type_name, label, search_text, tenant_id )
VALUES ( '7e746126eaaefa6a91992ebcb67fe33', 
    '1e7461261441950a91992ebcb67fe33', 
	'',
	'SENSORCONTROLLER',
	'传感控制器',
	'',
	'',
	'' );
INSERT INTO type_spec ( id, pid, customer_id, type, type_name, label, search_text, tenant_id )
VALUES ( '1e7461261441950a91992ebcb67fe33', 
    '', 
	'',
	'DEVICE',
	'设备',
	'',
	'',
	'' );

INSERT INTO type_spec ( id, pid, customer_id, type, type_name, label, search_text, tenant_id )
VALUES ( '2e7461261441950a91992ebcb67fe33', 
    '', 
	'',
	'EQUIPMENT',
	'装置',
	'',
	'',
	'' );

INSERT INTO type_spec ( id, pid, customer_id, type, type_name, label, search_text, tenant_id )
VALUES ( '3e7461261441950a91992ebcb67fe33', 
    '', 
	'',
	'CHANNEL',
	'通道',
	'',
	'',
	'' );
    

INSERT INTO type_spec ( id, pid, customer_id, type, type_name, label, search_text, tenant_id )
VALUES ( '4e7461261441950a91992ebcb67fe33', 
    '', 
	'',
	'SITE',
	'站点',
	'',
	'',
	'' );    
    
    