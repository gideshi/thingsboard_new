/**
 * Copyright © 2016-2019 The Thingsboard Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.thingsboard.server.dao.model;
import com.datastax.driver.core.utils.UUIDs;
import org.thingsboard.server.common.data.Device;
import org.thingsboard.server.common.data.DeviceInfo;
import org.thingsboard.server.common.data.UUIDConverter;
import org.thingsboard.server.common.data.id.CustomerId;
import org.thingsboard.server.common.data.id.DashboardId;
import org.thingsboard.server.common.data.id.DeviceId;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.dao.model.BaseSqlEntity;

import java.util.UUID;

public interface DeviceDetailsEntity<D>{
//    public interface DeviceDetailsEntity extends BaseSqlEntity<Device>{
    String getTenatid();
    String getDeviceid();
    String getCustomerId();
    String getDevicename();
    String getEquipname();
    String getTypename();
    String getSpec();
    String getDashboardid();

    default DeviceInfo conData() {
        DeviceInfo deviceInfo = new DeviceInfo(new DeviceId(UUIDConverter.fromString(getDeviceid())));
        deviceInfo.setCreatedTime(UUIDs.unixTimestamp(UUIDConverter.fromString(this.getDeviceid())));
        if (this.getTenatid() != null) {
            deviceInfo.setTenantId(new TenantId(UUIDConverter.fromString((this.getTenatid()))));
        }
        if (this.getCustomerId() != null) {
            deviceInfo.setCustomerId(new CustomerId(UUIDConverter.fromString(this.getCustomerId())));
        }
        deviceInfo.setName(getDevicename());
        deviceInfo.setEquipname(getEquipname());
        deviceInfo.setType_name(getTypename());
        deviceInfo.setSitename("站点1");
        deviceInfo.setSpec(getSpec());
        if (this.getDashboardid() != null) {
            deviceInfo.setDashboardid(new DashboardId(UUIDConverter.fromString(this.getDashboardid())));
        }
//        deviceInfo.setDashboardid(getDashboardid());
//        deviceInfo.setAdditionalInfo(additionalInfo);
        return deviceInfo;
    }
}