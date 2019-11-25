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
package org.thingsboard.server.dao.type;

import com.google.common.base.Function;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.thingsboard.server.common.data.*;
import org.thingsboard.server.common.data.device.DeviceSearchQuery;
import org.thingsboard.server.common.data.id.CustomerId;
import org.thingsboard.server.common.data.id.DeviceId;
import org.thingsboard.server.common.data.id.EntityId;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.data.page.TextPageData;
import org.thingsboard.server.common.data.page.TextPageLink;
import org.thingsboard.server.common.data.relation.EntityRelation;
import org.thingsboard.server.common.data.relation.EntitySearchDirection;
import org.thingsboard.server.common.data.security.DeviceCredentials;
import org.thingsboard.server.common.data.security.DeviceCredentialsType;
import org.thingsboard.server.dao.customer.CustomerDao;
import org.thingsboard.server.dao.device.DeviceCredentialsService;
import org.thingsboard.server.dao.device.DeviceService;
import org.thingsboard.server.dao.entity.AbstractEntityService;
import org.thingsboard.server.dao.entityview.EntityViewService;
import org.thingsboard.server.dao.exception.DataValidationException;
import org.thingsboard.server.dao.service.DataValidator;
import org.thingsboard.server.dao.service.PaginatedRemover;
import org.thingsboard.server.dao.tenant.TenantDao;
import org.thingsboard.server.dao.types.TypeService;

import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static org.thingsboard.server.common.data.CacheConstants.DEVICE_CACHE;
import static org.thingsboard.server.dao.DaoUtil.toUUIDs;
import static org.thingsboard.server.dao.model.ModelConstants.NULL_UUID;
import static org.thingsboard.server.dao.service.Validator.*;

@Service
@Slf4j
public class TypeServiceImpl extends AbstractEntityService implements TypeService {

    public static final String INCORRECT_TENANT_ID = "Incorrect tenantId ";
    public static final String INCORRECT_PAGE_LINK = "Incorrect page link ";
    public static final String INCORRECT_CUSTOMER_ID = "Incorrect customerId ";
    public static final String INCORRECT_DEVICE_ID = "Incorrect deviceId ";
    @Autowired
    private TypeDao deviceDao;

    @Autowired
    private TenantDao tenantDao;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private DeviceCredentialsService deviceCredentialsService;

    @Autowired
    private EntityViewService entityViewService;

    @Autowired
    private CacheManager cacheManager;
//
//    @Override
//    public ListenableFuture<List<EntitySubtype>> findDeviceTypesByTenantId(TenantId tenantId) {
//        log.trace("Executing findDeviceTypesByTenantId, tenantId [{}]", tenantId);
//        validateId(tenantId, INCORRECT_TENANT_ID + tenantId);
//        ListenableFuture<List<EntitySubtype>> tenantDeviceTypes = deviceDao.findTenantDeviceTypesAsync(tenantId.getId());
//        return Futures.transform(tenantDeviceTypes,
//                deviceTypes -> {
//                    deviceTypes.sort(Comparator.comparing(EntitySubtype::getTypeId));
//                    return deviceTypes;
//                });
//    }

    @Override
    public ListenableFuture<List<TypeSpec>> findDeviceTypes() {
        log.trace("Executing findDeviceTypesByTenantId, tenantId [{}]", "");
//        validateId(tenantId, INCORRECT_TENANT_ID + tenantId);
        return deviceDao.findDeviceTypesAsync();

    }
//
//    private DataValidator<Device> deviceValidator =
//            new DataValidator<Device>() {
//
//                @Override
//                protected void validateCreate(TenantId tenantId, Device device) {
//                    deviceDao.findDeviceByTenantIdAndName(device.getTenantId().getId(), device.getName()).ifPresent(
//                            d -> {
//                                throw new DataValidationException("Device with such name already exists!");
//                            }
//                    );
//                }
//
//                @Override
//                protected void validateUpdate(TenantId tenantId, Device device) {
//                    deviceDao.findDeviceByTenantIdAndName(device.getTenantId().getId(), device.getName()).ifPresent(
//                            d -> {
//                                if (!d.getUuidId().equals(device.getUuidId())) {
//                                    throw new DataValidationException("Device with such name already exists!");
//                                }
//                            }
//                    );
//                }
//
//                @Override
//                protected void validateDataImpl(TenantId tenantId, Device device) {
//                    if (StringUtils.isEmpty(device.getTypeId())) {
//                        throw new DataValidationException("Device type should be specified!");
//                    }
//                    if (StringUtils.isEmpty(device.getName())) {
//                        throw new DataValidationException("Device name should be specified!");
//                    }
//                    if (device.getTenantId() == null) {
//                        throw new DataValidationException("Device should be assigned to tenant!");
//                    } else {
//                        Tenant tenant = tenantDao.findById(device.getTenantId(), device.getTenantId().getId());
//                        if (tenant == null) {
//                            throw new DataValidationException("Device is referencing to non-existent tenant!");
//                        }
//                    }
//                    if (device.getCustomerId() == null) {
//                        device.setCustomerId(new CustomerId(NULL_UUID));
//                    } else if (!device.getCustomerId().getId().equals(NULL_UUID)) {
//                        Customer customer = customerDao.findById(device.getTenantId(), device.getCustomerId().getId());
//                        if (customer == null) {
//                            throw new DataValidationException("Can't assign device to non-existent customer!");
//                        }
//                        if (!customer.getTenantId().getId().equals(device.getTenantId().getId())) {
//                            throw new DataValidationException("Can't assign device to customer from different tenant!");
//                        }
//                    }
//                }
//            };
//
//    private PaginatedRemover<TenantId, Device> tenantDevicesRemover =
//        new PaginatedRemover<TenantId, Device>() {
//
//            @Override
//            protected List<Device> findEntities(TenantId tenantId, TenantId id, TextPageLink pageLink) {
//                return deviceDao.findDevicesByTenantId(id.getId(), pageLink);
//            }
//
//            @Override
//            protected void removeEntity(TenantId tenantId, Device entity) {
//                deleteDevice(tenantId, new DeviceId(entity.getUuidId()));
//            }
//        };
//
//    private PaginatedRemover<CustomerId, Device> customerDeviceUnasigner = new PaginatedRemover<CustomerId, Device>() {
//
//        @Override
//        protected List<Device> findEntities(TenantId tenantId, CustomerId id, TextPageLink pageLink) {
//            return deviceDao.findDevicesByTenantIdAndCustomerId(tenantId.getId(), id.getId(), pageLink);
//        }
//
//        @Override
//        protected void removeEntity(TenantId tenantId, Device entity) {
//            unassignDeviceFromCustomer(tenantId, new DeviceId(entity.getUuidId()));
//        }
//    };
}
