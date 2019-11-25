/*
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
/* eslint-disable import/no-unresolved, import/default */

import deviceFieldsetTemplate from './device-fieldset.tpl.html';

/* eslint-enable import/no-unresolved, import/default */

/*@ngInject*/
export default function DeviceDirective($compile, $templateCache, toast, $translate, types, clipboardService, deviceService, customerService, $log) {
    $log.info('=1======');
    var linker = function (scope, element) {
        var template = $templateCache.get(deviceFieldsetTemplate);
        element.html(template);
        $log.info('=2======');
        scope.types = types;
        scope.isAssignedToCustomer = false;
        scope.isPublic = false;
        scope.assignedCustomer = null;
        scope.deviceId = "12345678";
        //site+subTypes
        scope.siteNames = ["site1", "site2"];
        scope.selectedSite = {};
        scope.subTypes = ["THSENSOR", "VIBRATESENSOR", "SHSENSOR", "SENSORCONTROLLER", "SMARTLOCK"];

        var subTypesPromise;
        subTypesPromise = deviceService.getDeviceTypes({ignoreLoading: true});
        if (subTypesPromise) {
            subTypesPromise.then(
                function success(types) {
                    scope.subTypes = [];
                    $log.info('subTypesPromise============');
                    types.forEach(function (type) {
                        scope.subTypes.push({'type':type.type,'typeId':type.id.id,'name':type.name});
                    });
                    //deferred.resolve(scope.entitySubtypes);
                },
                function fail() {
                    scope.subTypes = [];
                    $log.info('fail============');
                }
            );
        } else {
            scope.subTypes = [];
            $log.info('data============');
        }

        //scope.selectedType = {};
        scope.$watch('device', function(newVal) {
            if (newVal) {
                if (scope.device.customerId && scope.device.customerId.id !== types.id.nullUid) {
                    scope.isAssignedToCustomer = true;
                    customerService.getShortCustomerInfo(scope.device.customerId.id).then(
                        function success(customer) {
                            scope.assignedCustomer = customer;
                            scope.isPublic = customer.isPublic;
                        }
                    );
                } else {
                    scope.isAssignedToCustomer = false;
                    scope.isPublic = false;
                    scope.assignedCustomer = null;
                }
            }
        });

        scope.onDeviceIdCopied = function() {
            toast.showSuccess($translate.instant('device.idCopiedMessage'), 750, angular.element(element).parent().parent(), 'bottom left');
        };

        scope.copyAccessToken = function(e) {
            const trigger = e.delegateTarget || e.currentTarget;
            if (scope.device.id) {
                deviceService.getDeviceCredentials(scope.device.id.id, true).then(
                    function success(credentials) {
                        var credentialsId = credentials.credentialsId;
                        clipboardService.copyToClipboard(trigger, credentialsId).then(
                            () => {
                                toast.showSuccess($translate.instant('device.accessTokenCopiedMessage'), 750, angular.element(element).parent().parent(), 'bottom left');
                            }
                        );
                    }
                );
            }
        };

        $compile(element.contents())(scope);
    }
    return {
        restrict: "E",
        link: linker,
        scope: {
            device: '=',
            isEdit: '=',
            deviceScope: '=',
            theForm: '=',
            onAssignToCustomer: '&',
            onMakePublic: '&',
            onUnassignFromCustomer: '&',
            onManageCredentials: '&',
            onDeleteDevice: '&'
        }
    };
}
