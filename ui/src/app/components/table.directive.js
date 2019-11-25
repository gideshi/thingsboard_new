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
import 'angular-material-data-table/dist/md-data-table.min.css';
import './table.scss';

import thingsboardScopeElement from './scope-element.directive';
import thingsboardDetailsSidenav from './details-sidenav.directive';

import tableTemplate from './table.tpl.html';

export default angular.module('thingsboard.directives.table', [thingsboardScopeElement, thingsboardDetailsSidenav])
    .directive('tbTable', Table)
    .controller('AddItemController', AddItemController)
    .controller('EditItemController', EditItemController)
    // .controller('ItemCardController', ItemCardController)
    // .directive('tbGridCardContent', GridCardContent)
    // .filter('range', RangeFilter)
    .name;

/*@ngInject*/
function Table() {
    return {
        restrict: "E",
        scope: true,
        transclude: {
            detailsButtons: '?detailsButtons'
        },
        bindToController: {
            tableConfiguration: '&?'
        },
        controller: TableController,
        controllerAs: 'vm',
        templateUrl: tableTemplate
    }
}

/*@ngInject*/
function TableController($scope, $state, $translate, $mdDialog, $document, $q, deviceService, assetService, $log) {

    $scope.siteNames = ["site1", "site2"];
    $scope.selectedSite = {};
    $scope.subTypes = [];
    $scope.searchRet = {};
    initSubTypes();
    initEquipments();
    function initSubTypes() {
        let subTypesPromise;
        subTypesPromise = deviceService.getDeviceTypes({ignoreLoading: true});
        if (subTypesPromise) {
            subTypesPromise.then(
                function success(types) {
                    $scope.subTypes = [];
                    $log.info('table subTypesPromise ============');
                    types.forEach(function (type) {
                        $scope.subTypes.push({'typeId':type.id.id,'name':type.name});
                    });
                    //deferred.resolve(scope.entitySubtypes);
                },
                function fail() {
                    $scope.subTypes = [];
                    //deferred.reject();
                }
            );
        } else {
            $scope.subTypes = [];
            //deferred.reject();
        }
    }

    function initEquipments() {
        let  equipmentsPromise;
        equipmentsPromise = assetService.getEquipments({ignoreLoading: true});
        if (equipmentsPromise) {
            equipmentsPromise.then(
                function success(equipments) {
                    $scope.equipments = [];
                    $log.info('table equipmentsPromise ============');
                    equipments.forEach(function (equipment) {
                        $scope.equipments.push({'equipid':equipment.id.id,'name':equipment.name});
                    });
                },
                function fail() {
                    $scope.equipments = [];
                }
            );
        } else {
            $scope.equipments = [];
        }
    }

	$scope.options = {
		rowSelection: true,
		multiSelect: false,
		autoSelect: false,
        decapitate: false,
        largeEditDialog: false,
        boundaryLinks: true,
        limitSelect: true,
        pageSelect: true
    };
    $scope.query = {
        limit: 5,
        page: 1
    };
    $scope.desserts = {
        "count": 0,
        "data": []
    };
    // $scope.desserts = {
    //  "count": 9,
    //  "data": [
    //      {
    //          "id": 1,
    //          "deviceName": "温湿度传感器1",
    //          "site": "北岭路1号环网柜1",
    //          "state": "正常",
    //          "currentData": "温度26 湿度30",
    //          "nestedEquipment": "装置1",
    //          "deviceType": "温湿度传感器",
    //          "deviceNumber": "AIX2001",
    //          "iron": { "value": 1.0 }
    //      },
    //         {
    //             "id": 2,
    //             "deviceName": "温湿度传感器2",
    //             "site": "北岭路1号环网柜2",
    //             "state": "正常",
    //             "currentData": "温度26 湿度30",
    //             "nestedEquipment": "装置2",
    //             "deviceType": "温湿度传感器",
    //             "deviceNumber": "AIX2002",
    //             "iron": { "value": 1.0 }
    //         },
    //         {
    //             "id": 3,
    //             "deviceName": "温湿度传感器3",
    //             "site": "北岭路1号环网柜3",
    //             "state": "正常",
    //             "currentData": "温度26 湿度30",
    //             "nestedEquipment": "装置3",
    //             "deviceType": "温湿度传感器",
    //             "deviceNumber": "AIX2003",
    //             "iron": { "value": 1.0 }
    //         },
    //         {
    //             "id": 4,
    //             "deviceName": "温湿度传感器4",
    //             "site": "北岭路1号环网柜4",
    //             "state": "正常",
    //             "currentData": "温度26 湿度30",
    //             "nestedEquipment": "装置4",
    //             "deviceType": "温湿度传感器",
    //             "deviceNumber": "AIX2004",
    //             "iron": { "value": 1.0 }
    //         }
    //  ]
    // };

    var vm = this;

    vm.hasData = hasData;
    vm.noData = noData;
	vm.searchButtonIsClick = false;

    vm.direction = [];
    vm.totalCount = 0;
    vm.selectedItems = [];

    vm.dessert = $scope.desserts.data.slice(0, 0 + $scope.query.limit);


    vm.onPaginate = onPaginate;

    initTableConfiguration();

    initList();
    vm.itemRows = {
        fetchMoreItems_: function () {
            if (vm.items.hasNext && !vm.items.pending) {
                $log.info('321');
            }else{
                $log.info('ccc');
                vm.items.hasNext = false;
            }
        }
    };
    function initList() {
        $log.info('table directive initList');
        $log.info('$scope.query.limit=' + $scope.query.limit);
        $log.info('$scope.query.page=' + $scope.query.page);
        var devicesPromise = deviceService.getTenantDevices({limit: $scope.query.limit,textSearch:'',pageIndex:$scope.query.page},{},{ignoreLoading: true});
        if (devicesPromise) {
            devicesPromise.then(
                function success(devices) {
                    $scope.desserts = {};
                    $log.info('table directive initList success');
                    $scope.searchRet = devices;
                    $scope.desserts.count = devices.datacount;
                    var data = devices.data;
                    $scope.desserts.data = data;
                    vm.dessert = $scope.desserts.data.slice(0);
                },
                function fail() {
                    $scope.desserts = {};
                }
            );
        } else {
            $scope.desserts = {};
        }
    }

    function refreshList() {
        $log.info('table refreshList');
        var devicesPromise = deviceService.getTenantDevices({limit: $scope.query.limit,textSearch:'', pageIndex:  $scope.query.page},{},{ignoreLoading: true});
        if (devicesPromise) {
            devicesPromise.then(
                function success(devices) {
                    $scope.desserts = {};
                    $log.info('table refreshList success');
                    $scope.searchRet = devices;
                    $scope.desserts.count =  devices.datacount;
                    var data = devices.data;
                    $scope.desserts.data = data;
                    vm.dessert = $scope.desserts.data.slice(0);
                },
                function fail() {
                    $scope.desserts = {};
                }
            );
        } else {
            $scope.desserts = {};
        }
    }
    function initTableConfiguration() {

        vm.tableConfiguration = vm.tableConfiguration || function () {
            return {};
        };

        vm.config = vm.tableConfiguration();

        vm.saveItemFunc = vm.config.saveItemFunc || function (item) {
            return $q.when(item);
        };
        vm.editItemFunc = vm.config.editItemFunc;

        vm.deleteItemFunc = vm.config.deleteItemFunc;

        if (vm.config.addItemController) {
            vm.addItemController =  vm.config.addItemController;
        } else {
            vm.addItemController = 'AddItemController';
        }

        vm.editItemController = 'EditItemController';

        vm.addItemAction = vm.config.addItemAction || {
            onAction: function ($event) {
                addItem($event);
            },
            name: function() { return $translate.instant('action.add') },
            details: function() { return vm.addItemText() },
            icon: "add"
        };

        vm.editItemAction = vm.config.editItemAction || {
            onAction: function ($event, item) {
                editItem($event, item);
            }
            ,
            name: function() { return $translate.instant('action.save') },
            details: function() { return vm.editItemText() },
            icon: "edit"
        };
        vm.deleteItem = deleteItem;
        vm.deleteItems = deleteItems;
        vm.editCheckItem = editCheckItem;
        vm.infoItem = infoItem;
        vm.searchDevices = searchDevices;
        vm.initSearchCondition = initSearchCondition;
        vm.openDashboard = openDashboard;
        vm.toggleItemSelection = toggleItemSelection;
        vm.addItemActions = vm.config.addItemActions || [];

        vm.addItemTemplateUrl = vm.config.addItemTemplateUrl;

        vm.editItemTemplateUrl = vm.config.editItemTemplateUrl;

        $log.info('addItemTemplateUrl:' + vm.addItemTemplateUrl);
        $log.info('editItemTemplateUrl:' + vm.editItemTemplateUrl);
        vm.noItemsText = vm.config.noItemsText || function () {
                return $translate.instant('grid.no-items-text');
            };

        vm.onTableInited = vm.config.onTableInited || function () {
            };

        vm.noItemsText = vm.config.noItemsText || function () {
            return $translate.instant('grid.no-items-text');
        };
        function openDashboard($event, device) {
            if ($event) {
                $event.stopPropagation();
            }
            $log.info(device);
            $log.info('----------------=' + device.dashboardid.id);
            $state.go('home.dashboards.dashboard', {dashboardId: device.dashboardid.id});
            // let stateParams = {
            //     dashboardId: targetDashboardId,
            //     state: utils.objToBase64([ stateObject ])
            // }
            // if ($state.current.name === 'dashboard') {
            //     $state.go('dashboard', stateParams);
            // } else {
            //     $state.go('home.dashboards.dashboard', stateParams);
            // }


            // if (vm.dashboardsScope === 'customer') {
            //     $state.go('home.customers.dashboards.dashboard', {
            //         customerId: customerId,
            //         dashboardId: dashboard.id.id
            //     });
            // } else {
            //     $state.go('home.dashboards.dashboard', {dashboardId: dashboard.id.id});
            // }
        }
        function addItem($event) {
            $mdDialog.show({
                controller: vm.addItemController,
                controllerAs: 'vm',
                templateUrl: vm.addItemTemplateUrl,
                parent: angular.element($document[0].body),
                locals: {saveItemFunction: vm.saveItemFunc,deviceId:"12345678"},
                fullscreen: true,
                targetEvent: $event
            }).then(function () {
                $log.info("table addItem ok");
                refreshList();
            }, function () {
            });
        }

        function editItem($event, item) {
            $mdDialog.show({
                controller: vm.editItemController,
                controllerAs: 'vm',
                templateUrl: vm.editItemTemplateUrl,
                parent: angular.element($document[0].body),
                locals: {saveItemFunction: vm.saveItemFunc, item: item, option: 'edit'},
                fullscreen: true,
                targetEvent: $event
            }).then(function () {
                $log.info("table editItem ok");
                refreshList();
            }, function () {
            });
        }

        function infoItem($event, item) {
            $mdDialog.show({
                controller: vm.editItemController,
                controllerAs: 'vm',
                templateUrl: vm.editItemTemplateUrl,
                parent: angular.element($document[0].body),
                locals: {saveItemFunction: vm.saveItemFunc, item: item, option: 'info'},
                fullscreen: true,
                targetEvent: $event
            }).then(function () {
                $log.info("table editItem ok");
                refreshList();
            }, function () {
            });
        }

        function editCheckItem($event) {
            if ($event) {
                $event.stopPropagation();
            }
            let device = {};

            for (var i=0;i<vm.selectedItems.length;i++) {
                let selectedItem = vm.selectedItems[i];
                $log.info('vm.selectedItems.index=' + selectedItem.id.id);
                device.id = {id: selectedItem.id.id};
            }
            $mdDialog.show({
                controller: vm.editItemController,
                controllerAs: 'vm',
                templateUrl: vm.editItemTemplateUrl,
                parent: angular.element($document[0].body),
                locals: {saveItemFunction: vm.saveItemFunc, item: device, option: 'edit'},
                fullscreen: true,
                targetEvent: $event
            }).then(function () {
                $log.info("table editItem ok");
                refreshList();
            }, function () {
            });
        }
        function deleteItem($event, item) {
            let title = $translate.instant('device.delete-device-title', {deviceName: item.name});
            let content = $translate.instant('device.delete-device-text');
            let label = $translate.instant('device.make-private');
            if ($event) {
                $event.stopPropagation();
            }
            var confirm = $mdDialog.confirm()
                .targetEvent($event)
                .title(title)
                .htmlContent(content)
                .ariaLabel(label)
                .cancel($translate.instant('action.no'))
                .ok($translate.instant('action.yes'));
            $mdDialog.show(confirm).then(function () {
                    vm.deleteItemFunc(item.id.id)
                        .then(function success() {
                        refreshList();
                    });
                },
                function () {
                });

        }

        function deleteItems($event) {
            if ($event) {
                $event.stopPropagation();
            }
            $log.info('vm.selectedItems.length=' + vm.selectedItems.length + vm.selectedItems[0].id.id);
            for (var i=0;i<vm.selectedItems.length;i++) {
                let selectedItem = vm.selectedItems[i];
                $log.info('vm.selectedItems.index=' + selectedItem.id.id);
            }
            vm.selectedItems.forEach(function(selectedItem2){
                if (selectedItem2.id) {
                    $log.info('vm.selectedItems.index2=' + selectedItem2.id.id);
                }
            });

            var confirm = $mdDialog.confirm()
                .targetEvent($event)
                .title($translate.instant('grid.delete-items-title', {count: vm.selectedItems.length}, 'messageformat'))
                .htmlContent($translate.instant('grid.delete-items-text'))
                .ariaLabel($translate.instant('grid.delete-items'))
                .cancel($translate.instant('action.no'))
                .ok($translate.instant('action.yes'));
            $mdDialog.show(confirm).then(function () {
                    var tasks = [];
                    for (var i=0;i<vm.selectedItems.length;i++) {
                        let selectedItem = vm.selectedItems[i];
                        if (selectedItem.id) {
                            tasks.push(vm.deleteItemFunc(selectedItem.id.id));
                        }
                    }
                    $q.all(tasks).then(function () {
                        vm.selectedItems = [];
                        refreshList();
                    });
                },
                function () {
                });
        }

        function searchDevices($event) {
            if ($event) {
                $event.stopPropagation();
            }
            vm.searchButtonIsClick = true;
            $log.info('vm.searchDevices');
            $log.info('vm.sitename=' + vm.sitename);
            $log.info('vm.equipid=' + vm.equipid);
            $log.info('vm.state=' + vm.state);
            $log.info('vm.type_id=' + vm.type_id);
            $log.info('vm.datafrom=' + vm.datafrom);
            $log.info('vm.datato=' + vm.datato);
            let devicesPromise = deviceService.getTenantDevicesByQuery({limit: $scope.query.limit, pageIndex: $scope.query.page, type_id:vm.type_id, equipid:vm.equipid},{},{ignoreLoading: true});
            if (devicesPromise) {
                devicesPromise.then(
                    function success(devices) {
                        $scope.desserts = {};
                        $log.info('table directive searchDevices success');
                        $scope.searchRet = devices;
                        $scope.desserts.count = devices.datacount;
                        var data = devices.data;
                        $scope.desserts.data = data;
                        vm.dessert = $scope.desserts.data.slice(0);
                    },
                    function fail() {
                        $scope.desserts = {};
                    }
                );
            } else {
                $scope.desserts = {};
            }
        }

        function toggleItemSelection($event, item) {
            $event.stopPropagation();
            var selected = angular.isDefined(item.selected) && item.selected;
            item.selected = !selected;
            if (item.selected) {
                vm.items.selections[item.id.id] = true;
                vm.items.selectedCount++;
            } else {
                delete vm.items.selections[item.id.id];
                vm.items.selectedCount--;
            }
        }

        function initSearchCondition($event) {
            if ($event) {
                $event.stopPropagation();
            }
            vm.equipid = "";
            vm.sitename = "";
            vm.state = "";
            vm.type_id = "";
            vm.datafrom = "";
            vm.datato = "";
        }

        vm.items = vm.config.items || {
                data: [],
                // rowData: [],
                // nextPageLink: {
                //     limit: vm.topIndex + pageSize,
                //     textSearch: $scope.searchConfig.searchText
                // },
                // selections: {},
                // selectedCount: 0,
                hasNext: false,
                pending: false
            };
    }

    vm.onTableInited(vm);

    vm.itemRows.fetchMoreItems_();

    function noData() {
        //return true;
        return vm.items.data.length == 0 && !vm.items.hasNext;
    }

    function hasData() {
        return vm.items.data.length > 0;
    }

    function onPaginate () {
        updateRelations();
    }

    function updateRelations () {
        let devicesPromise;
        // page init page change
        if (!vm.searchButtonIsClick) {
            $log.info('table directive updateRelations');
            $log.info('$scope.query.limit:' + $scope.query.limit);
            $log.info('$scope.query.page:' + $scope.query.page);
            devicesPromise = deviceService.getTenantDevices({limit: $scope.query.limit,textSearch:'', pageIndex: $scope.query.page},{},{ignoreLoading: true});

            // search button page change
        } else {
            $log.info('vm.searchDevices');
            $log.info('vm.sitename=' + vm.sitename);
            $log.info('vm.equipid=' + vm.equipid);
            $log.info('vm.state=' + vm.state);
            $log.info('vm.type_id=' + vm.type_id);
            $log.info('vm.datafrom=' + vm.datafrom);
            $log.info('vm.datato=' + vm.datato);
            devicesPromise = deviceService.getTenantDevicesByQuery({limit: $scope.query.limit, pageIndex: $scope.query.page, type_id:vm.type_id, equipid:vm.equipid},{},{ignoreLoading: true});
        }

        // var devicesPromise = deviceService.getTenantDevices({limit: $scope.query.limit,textSearch:'', pageIndex: $scope.query.page},{},{ignoreLoading: true});
        if (devicesPromise) {
            devicesPromise.then(
                function success(devices) {
                    $scope.desserts = {};
                    $log.info('table directive updateRelations success');
                    $scope.searchRet = devices;
                    $scope.desserts.count =  devices.datacount;
                    var data = devices.data;
                    $scope.desserts.data = data;
                    vm.dessert = $scope.desserts.data.slice(0);
                },
                function fail() {
                    $scope.desserts = {};
                }
            );
        } else {
            $scope.desserts = {};
        }
        // vm.selectedItems = [];
        //
        // vm.totalCount = $scope.desserts.count;
        // var startIndex = $scope.query.limit * ($scope.query.page - 1);
        // vm.dessert = $scope.desserts.data.slice(startIndex, startIndex + $scope.query.limit);
    }
}

/*@ngInject*/
function AddItemController($scope, $mdDialog, $log, saveItemFunction, deviceId, helpLinks) {

    var vm = this;

    vm.helpLinks = helpLinks;
    $log.info('helpLinks:' + helpLinks);
    vm.item = {};
    $log.info('scope.deviceId============'+ $scope.deviceId + deviceId);
    vm.add = add;
    vm.cancel = cancel;
    initController();
    function initController() {
        $log.info('table directive AddItemController initController');
        $log.info('scope.isPublic='+ $scope.isPublic);
        vm.device = {};
        vm.device.name1 = deviceId;
        // vm.helpLinks.initPage(deviceId);
    }
    function cancel() {
        $log.info('table directive AddItemController cancel');
        $mdDialog.cancel();
    }

    function add() {
        $log.info('table directive AddItemController add');
        $log.info('saveItemFunction:' + saveItemFunction);
        saveItemFunction(vm.item).then(function success(item) {
            vm.item = item;
            $scope.theForm.$setPristine();
            $mdDialog.hide();
        });
    }
}

function EditItemController($scope, $mdDialog, $log, deviceService, saveItemFunction, item, option, helpLinks) {
    $log.info('table directive EdittemController item：' + item.name + item.number);
    var vm = this;
    vm.helpLinks = helpLinks;
    vm.item = {};
    initType();
    vm.edit = edit;
    vm.cancel = cancel;
    function initType() {
        vm.option = option;
        vm.siteNames = ["site1", "site2"];
        vm.selectedSite = "site1";
        // vm.device.typeId = '6eaaefa6-4612-14e7-a919-92ebcb67fe33';
        let subTypesPromise = deviceService.getDeviceTypes({ignoreLoading: true});
        if (subTypesPromise) {
            subTypesPromise.then(
                function success(types) {
                    vm.subTypes = [];
                    $log.info('subTypesPromise============');
                    types.forEach(function (type) {
                        vm.subTypes.push({'type':type.type,'typeId':type.id.id,'name':type.name});
                    });
                    //deferred.resolve(scope.entitySubtypes);
                },
                function fail() {
                    vm.subTypes = [];
                    $log.info('fail============');
                }
            );
        } else {
            vm.subTypes = [];
            $log.info('data============');
        }
        let devicePromise = deviceService.getDevice(item.id.id, {ignoreLoading: true});
        if (devicePromise) {
            devicePromise.then(
                function success(device) {
                    $log.info('devicePromise============');
                    vm.device = device;
                    vm.device.typeId = device.typeId.id;
                },
                function fail() {
                    $log.info('fail============');
                }
            );
        } else {
            $log.info('data============');
        }
    }
    function cancel() {
        $log.info('table directive EdittemController cancel');
        $mdDialog.cancel();
    }

    function edit() {
        $log.info('table EdittemController EdittemController directive edit');
        // $log.info('saveItemFunction:' + saveItemFunction);
        saveItemFunction(vm.device).then(function success(item) {
            vm.item = item;
            $scope.theForm.$setPristine();
            $mdDialog.hide();
        });
    }
}