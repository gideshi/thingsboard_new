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
package org.thingsboard.server.dao.sql.type;

import com.google.common.util.concurrent.ListenableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.thingsboard.server.common.data.*;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.data.page.TextPageLink;
import org.thingsboard.server.dao.DaoUtil;
import org.thingsboard.server.dao.device.DeviceDao;
import org.thingsboard.server.dao.model.sql.DeviceEntity;
import org.thingsboard.server.dao.model.sql.TypeEntity;
import org.thingsboard.server.dao.sql.JpaAbstractSearchTextDao;
import org.thingsboard.server.dao.type.TypeDao;
import org.thingsboard.server.dao.util.SqlDao;

import java.util.*;

import static org.thingsboard.server.common.data.UUIDConverter.fromTimeUUID;
import static org.thingsboard.server.common.data.UUIDConverter.fromTimeUUIDs;
import static org.thingsboard.server.dao.model.ModelConstants.NULL_UUID_STR;

/**
 * Created by Valerii Sosliuk on 5/6/2017.
 */
@Component
@SqlDao
public class JpaTypeDao extends JpaAbstractSearchTextDao<TypeEntity, TypeSpec> implements TypeDao {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    protected Class<TypeEntity> getEntityClass() {
        return TypeEntity.class;
    }

    @Override
    protected CrudRepository<TypeEntity, String> getCrudRepository() {
        return typeRepository;
    }

    @Override
    public ListenableFuture<List<TypeSpec>> findDeviceTypesAsync() {
        return service.submit(() -> DaoUtil.convertDataList(typeRepository.findDeviceTypes()));
    }
//
//    @Override
//    public ListenableFuture<List<EntitySubtype>> findAllTenantDeviceTypesAsync(UUID tenantId) {
//        return service.submit(() -> convertTenantDeviceTypesToDto(tenantId, deviceRepository.findAllDeviceTypes(fromTimeUUID(tenantId))));
//    }

//    private List<EntitySubtype> convertTenantDeviceTypesToDto(UUID tenantId, List<String> types) {
//        List<EntitySubtype> list = Collections.emptyList();
//        if (types != null && !types.isEmpty()) {
//            list = new ArrayList<>();
//            for (String type : types) {
//                list.add(new EntitySubtype(new TenantId(tenantId), EntityType.DEVICE, type));
//            }
//        }
//        return list;
//    }
}
