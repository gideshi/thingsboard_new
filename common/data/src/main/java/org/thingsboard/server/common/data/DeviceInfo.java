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
package org.thingsboard.server.common.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thingsboard.server.common.data.id.CustomerId;
import org.thingsboard.server.common.data.id.DashboardId;
import org.thingsboard.server.common.data.id.DeviceId;
import org.thingsboard.server.common.data.id.TenantId;

@Data
@EqualsAndHashCode(callSuper = true)
public class DeviceInfo extends SearchTextBasedWithAdditionalInfo<DeviceId> implements HasName, HasTenantId, HasCustomerId {

    private static final long serialVersionUID = 2807343040519543363L;

    private TenantId tenantId;
    private CustomerId customerId;
    private String sitename;
    private String name;
    private String equipname;
    private String type_name;
    private String type_id;
    private String equipid;
    private String spec;
    private String deviceid;
    private String pageIndex;
    private String limit;
    private DashboardId dashboardid;

    public DeviceInfo() {
        super();
    }

    public DeviceInfo(DeviceId id) {
        super(id);
    }

    public DeviceInfo(DeviceInfo deviceInfo) {
        super(deviceInfo);
//        this.devicename = deviceInfo.getDevicename();
        this.equipname = deviceInfo.getEquipname();
        this.type_name = deviceInfo.getType_name();
        this.spec = deviceInfo.getSpec();
        this.deviceid = deviceInfo.getDeviceid();
        this.dashboardid = deviceInfo.getDashboardid();
    }

//    public String getEquipname() {
//        return equipname;
//    }
//
//    public void setEquipname(String equipname) {
//        this.equipname = equipname;
//    }
//
//    public String getType_name() {
//        return type_name;
//    }
//
//    public void setType_name(String type_name) {
//        this.type_name = type_name;
//    }
//
//    public String getSpec() {
//        return spec;
//    }
//
//    public void setSpec(String spec) {
//        this.spec = spec;
//    }
//
//    public String getDeviceid() {
//        return deviceid;
//    }
//
//    public void setDeviceid(String deviceid) {
//        this.deviceid = deviceid;
//    }


    @Override
    public TenantId getTenantId() {
        return tenantId;
    }

    public void setTenantId(TenantId tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public CustomerId getCustomerId() {
        return customerId;
    }

    public void setCustomerId(CustomerId customerId) {
        this.customerId = customerId;
    }

    @Override
    public String getSearchText() {
        return getName();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Device [tenantId=");
        builder.append(tenantId);
        builder.append(", customerId=");
        builder.append(customerId);
        builder.append(", name=");
        builder.append(name);
        builder.append(", type=");
        builder.append(type_name);
        builder.append(", spec=");
        builder.append(spec);
        builder.append(", additionalInfo=");
        builder.append(getAdditionalInfo());
        builder.append(", createdTime=");
        builder.append(createdTime);
        builder.append(", id=");
        builder.append(id);
        builder.append("]");
        return builder.toString();
    }

}
