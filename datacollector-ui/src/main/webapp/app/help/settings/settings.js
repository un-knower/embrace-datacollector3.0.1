/*
 * Copyright 2017 StreamSets Inc.
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
/**
 * Controller for Settings Modal Dialog.
 */

angular
    .module('dataCollectorApp')
    .controller('SettingsModalInstanceController', function ($scope, $rootScope, $modalInstance) {

  // Default Timezone (local storage)
  var clientTimezone = moment().tz(moment.tz.guess()).format('z'),
      timezoneOptions = _([clientTimezone, $rootScope.$storage.serverTimezone, 'UTC']).uniq();

  if (!_.contains(timezoneOptions, $rootScope.$storage.preferredTimezone)) {
    $rootScope.$storage.preferredTimezone = clientTimezone;
  }

  angular.extend($scope, {
    timezoneOptions: timezoneOptions,

    done: function() {
      $modalInstance.dismiss('cancel');
    }
  });
})
 .filter('trusted', ['$sce', function($sce) {
        var div = document.createElement('div');
        return function(text) {
            div.innerHTML = text;
            return $sce.trustAsHtml(div.textContent);
        };
    }])
;