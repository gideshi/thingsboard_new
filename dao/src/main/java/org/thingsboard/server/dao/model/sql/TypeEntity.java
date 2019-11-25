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
package org.thingsboard.server.dao.model.sql;

import com.datastax.driver.core.utils.UUIDs;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.thingsboard.server.common.data.Device;
import org.thingsboard.server.common.data.TypeSpec;
import org.thingsboard.server.common.data.id.CustomerId;
import org.thingsboard.server.common.data.id.DeviceId;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.data.id.TypeId;
import org.thingsboard.server.dao.model.BaseSqlEntity;
import org.thingsboard.server.dao.model.ModelConstants;
import org.thingsboard.server.dao.model.SearchTextEntity;
import org.thingsboard.server.dao.util.mapping.JsonStringType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@TypeDef(name = "json", typeClass = JsonStringType.class)
@Table(name = ModelConstants.TYPE_COLUMN_FAMILY_NAME)
public final class TypeEntity extends BaseSqlEntity<TypeSpec> implements SearchTextEntity<TypeSpec> {

    @Column(name = ModelConstants.DEVICE_TENANT_ID_PROPERTY)
    private String tenantId;

    @Column(name = ModelConstants.DEVICE_CUSTOMER_ID_PROPERTY)
    private String customerId;

    @Column(name = ModelConstants.TYPE_PID_PROPERTY)
    private String pid;

    @Column(name = ModelConstants.TYPE_NAME_PROPERTY)
    private String typeName;

    @Column(name = ModelConstants.TYPE_TYPE_PROPERTY)
    private String type;

    @Column(name = ModelConstants.DEVICE_LABEL_PROPERTY)
    private String label;

    @Column(name = ModelConstants.SEARCH_TEXT_PROPERTY)
    private String searchText;

//    @Type(type = "json")
//    @Column(name = ModelConstants.DEVICE_ADDITIONAL_INFO_PROPERTY)
//    private JsonNode additionalInfo;

    public TypeEntity() {
        super();
    }

    public TypeEntity(TypeSpec typeSpec) {
        if (typeSpec.getId() != null) {
            this.setId(typeSpec.getId().getId());
        }
        if (typeSpec.getTenantId() != null) {
            this.tenantId = toString(typeSpec.getTenantId().getId());
        }
        if (typeSpec.getCustomerId() != null) {
            this.customerId = toString(typeSpec.getCustomerId().getId());
        }
        this.typeName = typeSpec.getName();
        this.pid = toString(typeSpec.getPid().getId());
        this.label = typeSpec.getLabel();
//        this.additionalInfo = device.getAdditionalInfo();
    }

    @Override
    public String getSearchTextSource() {
        return typeName;
    }

    @Override
    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    @Override
    public TypeSpec toData() {
        TypeSpec typeSpec = new TypeSpec(new TypeId(getId()));
        typeSpec.setCreatedTime(UUIDs.unixTimestamp(getId()));
        if (tenantId != null && !"".equals(tenantId)) {
            typeSpec.setTenantId(new TenantId(toUUID(tenantId)));
        }
        if (customerId != null && !"".equals(customerId)) {
            typeSpec.setCustomerId(new CustomerId(toUUID(customerId)));
        }
        typeSpec.setName(typeName);
        typeSpec.setPid(new TypeId(toUUID(pid)));
        typeSpec.setType(type);
        typeSpec.setLabel(label);
        return typeSpec;
    }
}