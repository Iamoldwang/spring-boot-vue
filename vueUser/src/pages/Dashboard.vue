<template>
    <div style="background: #559b9a">
      <el-row :gutter="30" style="height: 100%">
        <el-col :span="24"><div id="lineChart" style="width:100%;height:400px;"></div></el-col>
      </el-row>
      <el-row :gutter="30" style="height: 100%">
        <el-col :span="12"><div id="barChart" style="width:800px;height:400px;"></div></el-col>
        <el-col :span="12"><div id="pieChart" style="width:800px;height:400px;"></div></el-col>
      </el-row>
    </div>
</template>
<style></style>
<script>
  // 引入基本模板
  let echarts = require('echarts/lib/echarts')
  // 引入柱状图组件
  require('echarts/lib/chart/bar')
  require('echarts/lib/chart/line')
  require('echarts/lib/chart/pie')
  // 引入提示框和title组件
  require('echarts/lib/component/tooltip')
  require('echarts/lib/component/title')

  let initBarChart = function() {
    // 基于准备好的dom，初始化echarts实例
    let myChart = echarts.init(document.getElementById('barChart'))
    var arr1 = generateRandomArr(1,100,7)
// 绘制图表
    var option = {
      tooltip : {
        trigger: 'axis'
      },
      grid: {
        left: '1%',
        right: '5%',
        bottom: '1%',
        containLabel: true
      },
      xAxis : [
        {
          type : 'category',
          splitLine: {
            show: false
          },
          axisLabel:{
            color:'#fff'
          },
          axisTick:{
            lineStyle:{
              color:'#fff'
            }
          },
          axisLine:{
            lineStyle:{
              color:'#fff'
            }
          },
          data : ['周一','周二','周三','周四','周五','周六','周日']
        }
      ],
      yAxis : [
        {
          show:false,
          type : 'value',
          splitLine: {
            show: false
          },
        }
      ],
      series : [
        {
          name:'巡更统计',
          type:'bar',
          barWidth: '30%',
          itemStyle: {
            normal: {
              barBorderRadius: [10, 10, 0, 0],
              color: new echarts.graphic.LinearGradient(
                0, 0, 0, 1,
                [
                  {offset: 0, color: '#1d17e9'},
                  {offset: 0.5, color: '#706df1'},
                  {offset: 1, color: '#d2c9f1'}
                ]
              ),
              opacity: 0.85
            }
          },
          data:arr1
        }
      ]
    };

    myChart.setOption(option);
  }

  let initLineChart = function() {
    // 基于准备好的dom，初始化echarts实例
    let myChart = echarts.init(document.getElementById('lineChart'))
    var arr1 = generateRandomArr(1,300,30)
    var arr2 = generateRandomArr(1,300,30)
    var arr3 = generateRandomArr(1,300,30)
// 绘制图表
    var option = {
      tooltip : {
        trigger: 'axis'
      },
      legend: {
        textStyle:{
          color:'#fff'
        },
        data:['本月','同比','环比']
      },
      grid: {
        left: '1%',
        right: '2%',
        bottom: '1%',
        containLabel: true
      },
      color:['#c994f4','#8A41FD', '#00e5e2'],
      xAxis : [
        {
          type : 'category',
          boundaryGap : false,
          splitLine: {
            show: false
          },
          axisLabel:{
            color:'#fff'
          },
          axisTick:{
            lineStyle:{
              color:'#fff'
            }
          },
          axisLine:{
            lineStyle:{
              color:'#fff'
            }
          },
          data : ['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30']
        }
      ],
      yAxis : [
        {
          show:false,
          type : 'value',
          splitLine: {
            show: false
          },
        }
      ],
      series : [
        {
          name:'本月',
          type:'line',
          smooth: true,
          symbol:'none',
          lineStyle:{
            color:'#fff',
            width:3
          },
          areaStyle: {
            normal: {
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0,
                color: '#3D1DE0'
              }, {
                offset: 0.6,
                color: '#3d1de0'
              }, {
                offset: 1,
                color: '#dbd9ff'
              }])
            }
          },
          data:arr1
        },
        {
          name:'同比',
          type:'line',
          smooth: true,
          symbol:'none',
          lineStyle:{
            color:'#fff',
            width:3
          },
          areaStyle: {
            normal: {
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0,
                color: '#4054D0'
              }, {
                offset: 0.6,
                color: '#7b85f4'
              }, {
                offset: 1,
                color: '#fffdfe'
              }])
            }
          },
          data:arr2
        },
        {
          name:'环比',
          type:'line',
          smooth: true,
          symbol:'none',
          lineStyle:{
            color:'#fff',
            width:3
          },
          areaStyle: {
            normal: {
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0,
                color: '#23279f'
              }, {
                offset: 0.6,
                color: '#6694d9'
              }, {
                offset: 1,
                color: '#cee0ff'
              }])
            }
          },
          data:arr3
        }
      ]
    };

    myChart.setOption(option);
  }



  let generateRandomArr = function (startNum, endNum, count) {
    var ret = [];
    for (var i = 0; i < count; i++) {
      ret[i] = Math.floor(Math.random() * Math.floor(endNum));
    }
    return ret;
  }

  let initPieChart = function() {
    // 基于准备好的dom，初始化echarts实例
    let myChart = echarts.init(document.getElementById('pieChart'))
    var option = {
      tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
      },
      color:['#8be672','#abb5a9', '#efc651','#FB6363'],
      legend: {
        orient: 'vertical',
        x: 'left',
        y: 'center',
        textStyle: {
          fontSize: 14,
          color:'#efc651'
        },
        data:['在线','离线','故障','报警']
      },
      toolbox: {
        show : false
      },
      series : [

        {
          name:'设备状态',
          type:'pie',
          radius : [10, 50],
          center : ['60%', '50%'],
          roseType : 'radius',
          labelLine:{
            lineStyle:{
              color:'#efc651'
            }
          },
          data:[
            {value:25, name:'在线',label:{
                color:'#efc651'
              }},
            {value:5, name:'离线',label:{
                color:'#efc651'
              }},
            {value:15, name:'故障',label:{
                color:'#efc651'
              }},
            {value:10, name:'报警',label:{
                color:'#efc651'
              }}
          ]
        }
      ]
    };

    myChart.setOption(option);
  }
export default {
  data() {
      return {

      }
  },
  methods:{
    initBarChart,
    initLineChart,
    initPieChart
  },
  mounted: function() {
    this.initBarChart()
    this.initLineChart()
    this.initPieChart()
  }
}
</script>
