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
 * JVMMetrics module for displaying JVM Metrics page content.
 */

angular
  .module('commonUI.jvmMetrics')
  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/collector/jvmMetrics',
      {
        templateUrl: 'common/administration/jvmMetrics/jvmMetrics.tpl.html',
        controller: 'JVMMetricsController',
        resolve: {
          myVar: function(authService) {
            return authService.init();
          }
        },
        data: {
          authorizedRoles: ['admin']
        }
      }
    );
  }])
  .controller('JVMMetricsController', function (
    $scope, $rootScope, $timeout, api, configuration, Analytics, visibilityBroadcaster, $modal
  ) {
    var jvmMetricsTimer,
      destroyed = false,
      dateFormat = function(d) {
        return d3.time.format('%H:%M:%S')(new Date(d));
      },
      sizeFormat = function(d) {
        var mbValue = d / 1000000;
        return mbValue.toFixed(1) + ' MB';
      },
      cpuPercentageFormat = function(d){
        var mbValue = d * 1000/ 10.0;
        return mbValue.toFixed(1) + ' %';
      },
      formatValue = function(d, chart) {
        if(chart.yAxisTickFormat) {
          return chart.yAxisTickFormat(d);
        } else {
          return d;
        }
      },
      defaultChartOptions = {
        chart: {
          type: 'lineChart', //'multiBarChart', //'lineChart'
          height: 250,
          showLabels: true,
          showControls: false,
          duration: 0,
          x:function(d){
            return (new Date(d[0])).getTime();
          },
          y: function(d) {
            return d[1];
          },
          showLegend: true,
          xAxis: {
            tickFormat: dateFormat
          },
          yAxis: {
            //tickFormat: sizeFormat
          },
          margin: {
            left: 60,
            top: 20,
            bottom: 30,
            right: 30
          },
          useInteractiveGuideline: true
        }
      };

    configuration.init().then(function() {
      if(configuration.isAnalyticsEnabled()) {
        Analytics.trackPage('/collector/jvmMetrics');
      }
    });

    angular.extend($scope, {
      metrics: {},
      jmxList: [],

      getChartOptions: function(metricChartObject) {
        var chartOptions = angular.copy(defaultChartOptions);
        if(metricChartObject.yAxisTickFormat) {
          chartOptions.chart.yAxis.tickFormat = metricChartObject.yAxisTickFormat;
        }
        return chartOptions;
      },

      dateFormat: function() {
        return function(d){
          return d3.time.format('%H:%M:%S')(new Date(d));
        };
      },

      sizeFormat: function(){
        return function(d){
          var mbValue = d / 1000000;
          return mbValue.toFixed(0) + ' MB';
        };
      },

      cpuPercentageFormat: function() {
        return function(d){
          var mbValue = d * 1000/ 10.0;
          return mbValue.toFixed(1) + ' %';
        };
      },

      formatValue: function(d, chart) {
        if(chart.yAxisTickFormat) {
          return chart.yAxisTickFormat(d);
        } else {
          return d;
        }
      },

      removeChart: function(chart) {
        var index = _.indexOf($rootScope.$storage.jvmMetricsChartList, chart.name);

        if(index !== -1) {
          $rootScope.$storage.jvmMetricsChartList.splice(index, 1);
        }
      },

      filterChart: function(chart) {
        return _.contains($rootScope.$storage.jvmMetricsChartList, chart.name);
      },

      /**
       * Launch Settings Modal Dialog
       */
      launchSettings: function() {
        var modalInstance = $modal.open({
          templateUrl: 'common/administration/jvmMetrics/settings/settingsModal.tpl.html',
          controller: 'JVMMetricsSettingsModalInstanceController',
          backdrop: 'static',
          size: 'lg',
          resolve: {
            availableCharts: function () {
              return $scope.chartList;
            },
            selectedCharts: function() {
              return _.filter($scope.chartList, function(chart) {
                return _.contains($rootScope.$storage.jvmMetricsChartList, chart.name);
              });
            }
          }
        });

        modalInstance.result.then(function (selectedCharts) {
          $rootScope.$storage.jvmMetricsChartList = _.pluck(selectedCharts, 'name');
        }, function () {

        });
      },


      /**
       * Launch Thread Dump Modal Dialog
       */
      launchThreadDump: function() {
        $modal.open({
          templateUrl: 'common/administration/jvmMetrics/threadDump/threadDumpModal.tpl.html',
          controller: 'ThreadDumpModalInstanceController',
          backdrop: 'static',
          size: 'lg'
        });
      }

    });

    $scope.chartList = [
      {
        name: 'runnerPool',
        label: '线程池: 任务运行',
        xAxisTickFormat: $scope.dateFormat(),
        values: [
          {
            name: 'metrics:name=sdc.pipeline.safe-executor.runner.runtime.gauge',
            property: 'Value/running',
            key: '运行中',
            values: [],
            area: true
          },
          {
            name: 'metrics:name=sdc.pipeline.safe-executor.runner.runtime.gauge',
            property: 'Value/waiting',
            key: '等待中',
            values: [],
            area: false
          },
          {
            name: 'metrics:name=sdc.pipeline.safe-executor.runner.runtime.gauge',
            property: 'Value/max',
            key: '最大值',
            values: [],
            area: false
          },
          {
            name: 'metrics:name=sdc.pipeline.safe-executor.runner.runtime.gauge',
            property: 'Value/periodic',
            key: '周期',
            values: [],
            area: false
          }
        ]
      },
      {
        name: 'runnerStopPool',
        label: '线程池: 任务停止运行',
        xAxisTickFormat: $scope.dateFormat(),
        values: [
          {
            name: 'metrics:name=sdc.pipeline.safe-executor.runnerStop.runtime.gauge',
            property: 'Value/running',
            key: '运行中',
            values: [],
            area: true
          },
          {
            name: 'metrics:name=sdc.pipeline.safe-executor.runnerStop.runtime.gauge',
            property: 'Value/waiting',
            key: '等待中',
            values: [],
            area: false
          },
          {
            name: 'metrics:name=sdc.pipeline.safe-executor.runnerStop.runtime.gauge',
            property: 'Value/max',
            key: '最大值',
            values: [],
            area: false
          },
          {
            name: 'metrics:name=sdc.pipeline.safe-executor.runnerStop.runtime.gauge',
            property: 'Value/periodic',
            key: '周期',
            values: [],
            area: false
          }
        ]
      },
      {
        name: 'previewerPool',
        label: '线程池: 任务预览',
        xAxisTickFormat: $scope.dateFormat(),
        values: [
          {
            name: 'metrics:name=sdc.pipeline.safe-executor.preview.runtime.gauge',
            property: 'Value/running',
            key: '运行中',
            values: [],
            area: true
          },
          {
            name: 'metrics:name=sdc.pipeline.safe-executor.preview.runtime.gauge',
            property: 'Value/waiting',
            key: '等待中',
            values: [],
            area: false
          },
          {
            name: 'metrics:name=sdc.pipeline.safe-executor.preview.runtime.gauge',
            property: 'Value/max',
            key: '最大值',
            values: [],
            area: false
          },
          {
            name: 'metrics:name=sdc.pipeline.safe-executor.preview.runtime.gauge',
            property: 'Value/periodic',
            key: '周期',
            values: [],
            area: false
          }
        ]
      },
      {
        name: 'bundlePool',
        label: '线程池: 支持包',
        xAxisTickFormat: $scope.dateFormat(),
        values: [
          {
            name: 'metrics:name=sdc.pipeline.safe-executor.supportBundleExecutor.runtime.gauge',
            property: 'Value/running',
            key: '运行中',
            values: [],
            area: true
          },
          {
            name: 'metrics:name=sdc.pipeline.safe-executor.supportBundleExecutor.runtime.gauge',
            property: 'Value/waiting',
            key: '等待中',
            values: [],
            area: false
          },
          {
            name: 'metrics:name=sdc.pipeline.safe-executor.supportBundleExecutor.runtime.gauge',
            property: 'Value/max',
            key: '最大值',
            values: [],
            area: false
          },
          {
            name: 'metrics:name=sdc.pipeline.safe-executor.supportBundleExecutor.runtime.gauge',
            property: 'Value/periodic',
            key: '周期',
            values: [],
            area: false
          }
        ]
      },
      {
        name: 'eventPool',
        label: '线程池: 事件处理程序',
        xAxisTickFormat: $scope.dateFormat(),
        values: [
          {
            name: 'metrics:name=sdc.pipeline.safe-executor.eventHandlerExecutor.runtime.gauge',
            property: 'Value/running',
            key: '运行中',
            values: [],
            area: true
          },
          {
            name: 'metrics:name=sdc.pipeline.safe-executor.eventHandlerExecutor.runtime.gauge',
            property: 'Value/waiting',
            key: '等待中',
            values: [],
            area: false
          },
          {
            name: 'metrics:name=sdc.pipeline.safe-executor.eventHandlerExecutor.runtime.gauge',
            property: 'Value/max',
            key: '最大值',
            values: [],
            area: false
          },
          {
            name: 'metrics:name=sdc.pipeline.safe-executor.eventHandlerExecutor.runtime.gauge',
            property: 'Value/periodic',
            key: '周期',
            values: [],
            area: false
          }
        ]
      },
      {
        name: 'managerPool',
        label: '线程池: 管理者',
        xAxisTickFormat: $scope.dateFormat(),
        values: [
          {
            name: 'metrics:name=sdc.pipeline.safe-executor.managerExecutor.runtime.gauge',
            property: 'Value/running',
            key: '运行中',
            values: [],
            area: true
          },
          {
            name: 'metrics:name=sdc.pipeline.safe-executor.managerExecutor.runtime.gauge',
            property: 'Value/waiting',
            key: '等待中',
            values: [],
            area: false
          },
          {
            name: 'metrics:name=sdc.pipeline.safe-executor.managerExecutor.runtime.gauge',
            property: 'Value/max',
            key: '最大值',
            values: [],
            area: false
          },
          {
            name: 'metrics:name=sdc.pipeline.safe-executor.managerExecutor.runtime.gauge',
            property: 'Value/periodic',
            key: '周期',
            values: [],
            area: false
          }
        ]
      },
      {
        name: 'managerRunnerCache',
        label: '缓存:任务运行',
        xAxisTickFormat: $scope.dateFormat(),
        values: [
          {
            name: 'metrics:name=sdc.pipeline.metrics-cache.manager-runner-cache.runtime.gauge',
            property: 'Value/count',
            key: '条目',
            values: [],
            area: true
          }
        ]
      },
      {
        name: 'managerPreviewerCache',
        label: '缓存:任务预览',
        xAxisTickFormat: $scope.dateFormat(),
        values: [
          {
            name: 'metrics:name=sdc.pipeline.metrics-cache.manager-previewer-cache.runtime.gauge',
            property: 'Value/count',
            key: '条目',
            values: [],
            area: true
          }
        ]
      },
      {
        name: 'cpuUsage',
        label: 'CPU 使用',
        xAxisTickFormat: $scope.dateFormat(),
        yAxisTickFormat: cpuPercentageFormat,
        values: [
          {
            name: 'java.lang:type=OperatingSystem',
            property: 'ProcessCpuLoad',
            key: 'CPU 使用',
            values: [],
            area: true
          }
        ]
      },
      {
        name: 'threads',
        label: '线程',
        xAxisTickFormat: $scope.dateFormat(),
        values: [
          {
            name: 'java.lang:type=Threading',
            property: 'ThreadCount',
            key: '存活线程',
            values: [],
            area: true
          }
        ],
        displayProperties: [
          {
            name: 'java.lang:type=Threading',
            property: 'PeakThreadCount',
            key: '峰值',
            value:''
          },
          {
            name: 'java.lang:type=Threading',
            property: 'TotalStartedThreadCount',
            key: '总共',
            value: ''
          }
        ]
      },
      {
        name: 'classes',
        label: '类',
        xAxisTickFormat: $scope.dateFormat(),
        values: [
          {
            name: 'java.lang:type=ClassLoading',
            property: 'LoadedClassCount',
            key: '装载',
            values: [],
            area: true
          },
          {
            name: 'java.lang:type=ClassLoading',
            property: 'UnloadedClassCount',
            key: '未加载',
            values: []
          },
          {
            name: 'java.lang:type=ClassLoading',
            property: 'TotalLoadedClassCount',
            key: '总共',
            values: []
          }
        ]
      },
      {
        name: 'heapMemoryUsage',
        label: '堆内存使用',
        xAxisTickFormat: $scope.dateFormat(),
        yAxisTickFormat: sizeFormat,
        values: [
          {
            name: 'metrics:name=jvm.memory.heap.max',
            property: 'Value',
            key: '最大值',
            values: []
          },
          {
            name: 'metrics:name=jvm.memory.heap.committed',
            property: 'Value',
            key: '提交',
            values: []
          },
          {
            name: 'metrics:name=jvm.memory.heap.used',
            property: 'Value',
            key: '使用',
            values: [],
            area: true
          }
        ]
      },
      {
        name: 'nonHeapMemoryUsage',
        label: '非堆内存使用',
        xAxisTickFormat: $scope.dateFormat(),
        yAxisTickFormat: sizeFormat,
        values: [
          {
            name: 'metrics:name=jvm.memory.non-heap.max',
            property: 'Value',
            key: '最大值',
            values: []
          },
          {
            name: 'metrics:name=jvm.memory.non-heap.committed',
            property: 'Value',
            key: '提交',
            values: []
          },
          {
            name: 'metrics:name=jvm.memory.non-heap.used',
            property: 'Value',
            key: '使用',
            values: [],
            area: true
          }
        ]
      },
      {
        name: 'psEdenSpaceHeapMemoryUsage',
        label: '内存池 "PS Eden Space"',
        xAxisTickFormat: $scope.dateFormat(),
        yAxisTickFormat: sizeFormat,
        values: [
          {
            name: 'java.lang:type=MemoryPool,name=[a-zA-Z ]*Eden Space',
            property: 'Usage/max',
            key: '最大值',
            values: []
          },
          {
            name: 'java.lang:type=MemoryPool,name=[a-zA-Z ]*Eden Space',
            property: 'Usage/committed',
            key: '提交',
            values: []
          },
          {
            name: 'java.lang:type=MemoryPool,name=[a-zA-Z ]*Eden Space',
            property: 'Usage/used',
            key: '使用',
            values: [],
            area: true
          }
        ]
      },
      {
        name: 'psSurvivorSpaceHeapMemoryUsage',
        label: '内存池 "PS Survivor Space"',
        xAxisTickFormat: $scope.dateFormat(),
        yAxisTickFormat: sizeFormat,
        values: [
          {
            name: 'java.lang:type=MemoryPool,name=[a-zA-Z ]*Survivor Space',
            property: 'Usage/max',
            key: '最大值',
            values: []
          },
          {
            name: 'java.lang:type=MemoryPool,name=[a-zA-Z ]*Survivor Space',
            property: 'Usage/committed',
            key: '提交',
            values: []
          },
          {
            name: 'java.lang:type=MemoryPool,name=[a-zA-Z ]*Survivor Space',
            property: 'Usage/used',
            key: '使用',
            values: [],
            area: true
          }
        ]
      },
      {
        name: 'psOldGenHeapMemoryUsage',
        label: '内存池 "PS Old Gen"',
        xAxisTickFormat: $scope.dateFormat(),
        yAxisTickFormat: sizeFormat,
        values: [
          {
            name: 'java.lang:type=MemoryPool,name=PS Old Gen|java.lang:type=MemoryPool,name=Tenured Gen',
            property: 'Usage/max',
            key: '最大值',
            values: []
          },
          {
            name: 'java.lang:type=MemoryPool,name=PS Old Gen|java.lang:type=MemoryPool,name=Tenured Gen',
            property: 'Usage/committed',
            key: '提交',
            values: []
          },
          {
            name: 'java.lang:type=MemoryPool,name=PS Old Gen|java.lang:type=MemoryPool,name=Tenured Gen',
            property: 'Usage/used',
            key: '使用',
            values: [],
            area: true
          }
        ]
      },
      /*{
        name: 'psPermGenHeapMemoryUsage',
        label: 'Memory Pool "PS Perm Gen"',
        xAxisTickFormat: $scope.dateFormat(),
        yAxisTickFormat: sizeFormat,
        values: [
          {
            name: 'java.lang:type=MemoryPool,name=[a-zA-Z ]*Perm Gen',
            property: 'Usage/max',
            key: '最大值',
            values: []
          },
          {
            name: 'java.lang:type=MemoryPool,name=[a-zA-Z ]*Perm Gen',
            property: 'Usage/committed',
            key: '提交',
            values: []
          },
          {
            name: 'java.lang:type=MemoryPool,name=[a-zA-Z ]*Perm Gen',
            property: 'Usage/used',
            key: '使用',
            values: []
          }
        ]
      },*/
      {
        name: 'psCodeCacheHeapMemoryUsage',
        label: '内存池 "Code Cache"',
        xAxisTickFormat: $scope.dateFormat(),
        yAxisTickFormat: sizeFormat,
        values: [
          {
            name: 'java.lang:type=MemoryPool,name=Code Cache',
            property: 'Usage/max',
            key: '最大值',
            values: []
          },
          {
            name: 'java.lang:type=MemoryPool,name=Code Cache',
            property: 'Usage/committed',
            key: '提交',
            values: []
          },
          {
            name: 'java.lang:type=MemoryPool,name=Code Cache',
            property: 'Usage/used',
            key: '使用',
            values: [],
            area: true
          }
        ]
      }
    ];

    if(!$rootScope.$storage.jvmMetricsChartList) {
      $rootScope.$storage.jvmMetricsChartList = _.pluck($scope.chartList, 'name');
    }

    /**
     * Fetch the JVM Metrics every Refresh Interval time.
     *
     */
    var refreshPipelineJMX = function() {

      jvmMetricsTimer = $timeout(
        function() {
        },
        configuration.getJVMMetricsRefreshInterval()
      );

      jvmMetricsTimer.then(
        function() {
          api.admin.getJMX()
            .then(function(res) {
              updateGraphData(res.data);
              refreshPipelineJMX();
            })
            .catch(function(res) {
              $rootScope.common.errors = [res.data];
            });
        },
        function() {
          console.log( "Timer rejected!" );
        }
      );
    };


    var updateGraphData = function(jmxData) {
      var chartList = $scope.chartList,
        date = (new Date()).getTime();

      angular.forEach(jmxData.beans, function(bean) {
        angular.forEach(chartList, function(chartObj) {

          angular.forEach(chartObj.values, function(chartBean) {
            //chartBean.area = true;
            var regExp = new RegExp(chartBean.name);
            if(regExp.test(bean.name)) {
              var propertyList = chartBean.property.split('/'),
                propertyValue = bean;

              angular.forEach(propertyList, function(property) {
                if(propertyValue) {
                  propertyValue = propertyValue[property];
                }
              });

              chartBean.values.push([date, propertyValue]);

              //Store only last 10 minutes data
              if(chartBean.values.length > 150) {
                chartBean.values.shift();
              }
            }
          });

          if(chartObj.displayProperties) {
            angular.forEach(chartObj.displayProperties, function(chartBean) {
              if(chartBean.name === bean.name) {
                var propertyList = chartBean.property.split('/'),
                  propertyValue = bean;

                angular.forEach(propertyList, function(property) {
                  if(propertyValue) {
                    propertyValue = propertyValue[property];
                  }
                });

                chartBean.value = propertyValue;
              }
            });
          }

        });
      });
    };

    api.admin.getJMX()
      .then(function(res) {
        updateGraphData(res.data);
        refreshPipelineJMX();
      })
      .catch(function(res) {
        $rootScope.common.errors = [res.data];
      });


    $scope.$on('$destroy', function(){
      $timeout.cancel(jvmMetricsTimer);
      destroyed = true;
    });

    $scope.$on('visibilityChange', function(event, isHidden) {
      if (isHidden) {
        $timeout.cancel(jvmMetricsTimer);
        destroyed = true;
      } else {
        refreshPipelineJMX();
        destroyed = false;
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
