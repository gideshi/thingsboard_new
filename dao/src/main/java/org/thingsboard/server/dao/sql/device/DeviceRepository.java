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
package org.thingsboard.server.dao.sql.device;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.thingsboard.server.dao.model.DeviceDetailsEntity;
import org.thingsboard.server.dao.model.sql.DeviceEntity;
import org.thingsboard.server.dao.util.SqlDao;

import java.util.List;

/**
 * Created by Valerii Sosliuk on 5/6/2017.
 */
@SqlDao
public interface DeviceRepository extends CrudRepository<DeviceEntity, String> {


    @Query("SELECT d FROM DeviceEntity d WHERE d.tenantId = :tenantId " +
            "AND d.customerId = :customerId " +
            "AND LOWER(d.searchText) LIKE LOWER(CONCAT(:searchText, '%')) " +
            "AND d.id > :idOffset ORDER BY d.id")
    List<DeviceEntity> findByTenantIdAndCustomerId(@Param("tenantId") String tenantId,
                                                   @Param("customerId") String customerId,
                                                   @Param("searchText") String searchText,
                                                   @Param("idOffset") String idOffset,
                                                   Pageable pageable);

    @Query("SELECT d FROM DeviceEntity d WHERE d.tenantId = :tenantId " +
            "AND LOWER(d.searchText) LIKE LOWER(CONCAT(:textSearch, '%')) " +
            "AND d.id > :idOffset ORDER BY d.id")
    List<DeviceEntity> findByTenantId(@Param("tenantId") String tenantId,
                                      @Param("textSearch") String textSearch,
                                      @Param("idOffset") String idOffset,
                                      Pageable pageable);

    @Query(value="SELECT count(1) from (SELECT d.id AS deviceid " +
            "FROM " +
            "     (select d.id, d.name as name, d.spec, d.tenant_id, d.type_id " +
            "    from device d " +
            "    where d.type_id in " +
            "                        (SELECT t.id" +
            "                        FROM" +
            "                                   type_spec t," +
            "                                    (SELECT t.id,t.type_name" +
            "                                          FROM type_spec t WHERE t.type='DEVICE') dt  " +
            "                         WHERE t.pid = dt.id)" +
            "    ) d" +
            "    LEFT JOIN type_spec t ON d.type_id =t.id " +
            "    LEFT JOIN (SELECT a.name,r.to_id,r.from_id" +
            "             FROM asset a," +
            "                  relation r" +
            "             WHERE a.id =r.from_id ) r ON d.id = r.to_id    "
            + "WHERE d.tenant_id = :tenantId "
            + "AND (t.id = :typeId OR :typeId='') "
            + "AND (r.from_id = :fromId or :fromId='')) a "
            ,nativeQuery = true)
    long findAllDevicesCount(@Param("tenantId") String tenantId,
                             @Param("typeId") String typeId,
                             @Param("fromId") String fromId);

    @Query(value="SELECT d.id AS deviceid, d.name as devicename, r.name AS equipname, t.type_name as typename, "
                       + "d.spec, d.tenant_id as tenantid, d.dashboard_id as dashboardid " +
            "FROM " +
            "     (select d.id, d.name as name, d.spec, d.tenant_id, d.type_id, d.dashboard_id " +
            "    from device d " +
            "    where d.type_id in" +
            "                        (SELECT t.id" +
            "                        FROM" +
            "                                            type_spec t," +
            "                                            (SELECT t.id,t.type_name" +
            "                                                    FROM type_spec t WHERE t.type='DEVICE') dt  " +
            "                         WHERE t.pid = dt.id)" +
            "    ) d" +
            "    LEFT JOIN type_spec t ON d.type_id =t.id " +
            "    LEFT JOIN (SELECT a.name,r.to_id,r.from_id" +
            "             FROM asset a," +
            "                  relation r" +
            "             WHERE a.id =r.from_id ) r ON d.id = r.to_id    "
            + "WHERE d.tenant_id = :tenantId "
            + "AND (t.id = :typeId OR :typeId='') "
            + "AND (r.from_id = :fromId or :fromId='') "
//            + "AND if(:fromId!='',r.from_id = :fromId,1=1) "
            + "ORDER BY d.id"
            ,nativeQuery = true)
    List<DeviceDetailsEntity> findAllDevices(@Param("tenantId") String tenantId,
                                             @Param("typeId") String typeId,
                                             @Param("fromId") String fromId,
                                             Pageable pageable);
//
//
//    @Query(value="select count(1) from (SELECT d.id AS deviceid, d.name as devicename, r.name AS equipname, t.type_name, d.spec,d.tenant_id " +
//            "FROM " +
//            "     (select d.id, d.name as name, d.spec, d.tenant_id, d.type_id " +
//            "    from device d " +
//            "    where d.type_id in" +
//            "                        (SELECT t.id" +
//            "                        FROM" +
//            "                                            type_spec t," +
//            "                                            (SELECT t.id,t.type_name" +
//            "                                                    FROM type_spec t WHERE t.type='DEVICE') dt  " +
//            "                         WHERE t.pid = dt.id)" +
//            "    ) d" +
//            "    LEFT JOIN type_spec t ON d.type_id =t.id " +
//            "    LEFT JOIN (SELECT d.name,r.to_id,r.from_id" +
//            "             FROM device d," +
//            "                  relation r" +
//            "             WHERE d.id =r.from_id ) r ON d.id = r.to_id  "
//            + "WHERE d.tenant_id = :tenantId) a"
//            ,nativeQuery = true)
//    long findAllDevicesCount(@Param("tenantId") String tenantId);
//
//    @Query(value="SELECT d.id AS deviceid, d.name as devicename, r.name AS equipname, t.type_name as typename, d.spec,d.tenant_id as tenantid " +
//            "FROM " +
//            "     (select d.id, d.name as name, d.spec, d.tenant_id, d.type_id " +
//            "    from device d " +
//            "    where d.type_id in" +
//            "                        (SELECT t.id" +
//            "                        FROM" +
//            "                                            type_spec t," +
//            "                                            (SELECT t.id,t.type_name" +
//            "                                                    FROM type_spec t WHERE t.type='DEVICE') dt  " +
//            "                         WHERE t.pid = dt.id)" +
//            "    ) d" +
//            "    LEFT JOIN type_spec t ON d.type_id =t.id " +
//            "    LEFT JOIN (SELECT d.name,r.to_id,r.from_id" +
//            "             FROM device d," +
//            "                  relation r" +
//            "             WHERE d.id =r.from_id ) r ON d.id = r.to_id    "
//            + "WHERE d.tenant_id = :tenantId "
////            + "AND d.id > :idOffset "
//            + "ORDER BY d.id"
//            ,nativeQuery = true)
//    List<DeviceDetailsEntity> findAllDevices(@Param("tenantId") String tenantId,
////                                             @Param("typeId") String type,
////                                             @Param("idOffset") String idOffset,
//                                             Pageable pageable);

    @Query("SELECT d FROM DeviceEntity d WHERE d.tenantId = :tenantId " +
            "AND d.typeId = :type " +
            "AND LOWER(d.searchText) LIKE LOWER(CONCAT(:textSearch, '%')) " +
            "AND d.id > :idOffset ORDER BY d.id")
    List<DeviceEntity> findByTenantIdAndType(@Param("tenantId") String tenantId,
                                             @Param("type") String type,
                                             @Param("textSearch") String textSearch,
                                             @Param("idOffset") String idOffset,
                                             Pageable pageable);

    @Query("SELECT d FROM DeviceEntity d WHERE d.tenantId = :tenantId " +
            "AND d.customerId = :customerId " +
            "AND d.typeId = :type " +
            "AND LOWER(d.searchText) LIKE LOWER(CONCAT(:textSearch, '%')) " +
            "AND d.id > :idOffset ORDER BY d.id")
    List<DeviceEntity> findByTenantIdAndCustomerIdAndType(@Param("tenantId") String tenantId,
                                                          @Param("customerId") String customerId,
                                                          @Param("type") String type,
                                                          @Param("textSearch") String textSearch,
                                                          @Param("idOffset") String idOffset,
                                                          Pageable pageable);

    @Query("SELECT DISTINCT d.typeId FROM DeviceEntity d WHERE d.tenantId = :tenantId")
    List<String> findTenantDeviceTypes(@Param("tenantId") String tenantId);

//    @Query(value="SELECT DISTINCT t.type_name "
//                + "FROM type_spec t, "
//                + "      (SELECT t.id,t.type_name"
//                + "           FROM type_spec t WHERE t.type='DEVICE') dt "
//                + "WHERE t.pid = dt.id AND t.tenant_id = :tenantId "
//            , nativeQuery = true)
//    List<String> findAllDeviceTypes(@Param("tenantId") String tenantId);

    DeviceEntity findByTenantIdAndName(String tenantId, String name);

    List<DeviceEntity> findDevicesByTenantIdAndCustomerIdAndIdIn(String tenantId, String customerId, List<String> deviceIds);

    List<DeviceEntity> findDevicesByTenantIdAndIdIn(String tenantId, List<String> deviceIds);
}
