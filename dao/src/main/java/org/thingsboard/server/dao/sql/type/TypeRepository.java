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

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.thingsboard.server.dao.model.DeviceDetailsEntity;
import org.thingsboard.server.dao.model.sql.DeviceEntity;
import org.thingsboard.server.dao.model.sql.TypeEntity;
import org.thingsboard.server.dao.util.SqlDao;

import java.util.List;

/**
 * Created by Valerii Sosliuk on 5/6/2017.
 */
@SqlDao
public interface TypeRepository extends CrudRepository<TypeEntity, String> {


    @Query(value="SELECT DISTINCT t.id, t.type_name, t.type "
            + "FROM type_spec t, "
            + "      (SELECT t.id, t.type_name, t.type "
            + "           FROM type_spec t WHERE t.type='DEVICE') dt "
            + "WHERE t.pid = dt.id "
                + "AND t.tenant_id = :tenantId "
            , nativeQuery = true)
    List<TypeEntity> findTenantDeviceTypes(@Param("tenantId") String tenantId);

    @Query(value="SELECT DISTINCT t.id, t.tenant_id, t.customer_id, t.pid, t.type_name, t.type, t.label, t.search_text "
            + "FROM type_spec t, "
            + "      (SELECT t.id, t.type_name, t.type "
            + "           FROM type_spec t WHERE t.type='DEVICE') dt "
            + "WHERE t.pid = dt.id "
            , nativeQuery = true)
    List<TypeEntity> findDeviceTypes();

}
