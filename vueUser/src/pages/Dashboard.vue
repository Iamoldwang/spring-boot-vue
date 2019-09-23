<template>
    <div class="mainDiv">
      <el-row :gutter="30" style="height: 100%">
        <el-col :span="8">
          <div><el-tag type="success">成绩排行榜</el-tag></div>
          <el-divider></el-divider>
          <table class="rankingTable">
            <thead>
              <tr>
                <th>项目</th>
                <th align="center"><img src="../assets/img/diyi.png"></th>
                <th align="center"><img src="../assets/img/dier.png"></th>
                <th align="center"><img src="../assets/img/disan.png"></th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td style="width: 120px;">
                  <img src="../assets/img/bq.png" style="float:left;margin-right: -30px;"><span style="line-height:30px;">(标枪)</span>
                </td>
                <td align="left">
                  李美文(44m)
                </td>
                <td align="left">
                  张翰林(42m)
                </td>
                <td align="left">
                  李金铭(41m)
                </td>
              </tr>
              <tr>
                <td>
                  <img src="../assets/img/jl.png" style="float:left;margin-right: -30px;"><span style="line-height:30px;">(接力)</span>
                </td>
                <td align="left">
                  李美文(1m22s)
                </td>
                <td align="left">
                  张翰林(1m24s)
                </td>
                <td align="left">
                  李金铭(1m30s)
                </td>
              </tr>
              <tr>
                <td>
                  <img src="../assets/img/jz.png" style="float:left;margin-right: -30px;"><span style="line-height:30px;">(举重)</span>
                </td>
                <td align="left">
                  李美文(50kg)
                </td>
                <td align="left">
                  张翰林(48kg)
                </td>
                <td align="left">
                  李金铭(46kg)
                </td>
              </tr>
              <tr>
                <td>
                  <img src="../assets/img/kl.png" style="float:left;margin-right: -30px;"><span style="line-height:30px;">(跨栏)</span>
                </td>
                <td align="left">
                  李美文(33s)
                </td>
                <td align="left">
                  张翰林(32s)
                </td>
                <td align="left">
                  李金铭(31s)
                </td>
              </tr>
              <tr>
                <td>
                  <img src="../assets/img/lq.png" style="float:left;margin-right: -30px;"><span style="line-height:30px;">(篮球)</span>
                </td>
                <td align="left">
                  李美文
                </td>
                <td align="left">
                  张翰林
                </td>
                <td align="left">
                  李金铭
                </td>
              </tr>
              <tr>
                <td>
                  <img src="../assets/img/pb.png" style="float:left;margin-right: -30px;"><span style="line-height:30px;">(跑步)</span>
                </td>
                <td align="left">
                  李美文(47s)
                </td>
                <td align="left">
                  张翰林(55s)
                </td>
                <td align="left">
                  李金铭(60s)
                </td>
              </tr>
              <tr>
                <td>
                  <img src="../assets/img/ppq.png" style="float:left;margin-right: -30px;"><span style="line-height:30px;">(乒乓球)</span>
                </td>
                <td align="left">
                  李美文
                </td>
                <td align="left">
                  张翰林
                </td>
                <td align="left">
                  李金铭
                </td>
              </tr>
              <tr>
                <td>
                  <img src="../assets/img/pq.png" style="float:left;margin-right: -30px;"><span style="line-height:30px;">(排球)</span>
                </td>
                <td align="left">
                  李美文
                </td>
                <td align="left">
                  张翰林
                </td>
                <td align="left">
                  李金铭
                </td>
              </tr>
              <tr>
                <td>
                  <img src="../assets/img/sj.png" style="float:left;margin-right: -30px;"><span style="line-height:30px;">(射箭)</span>
                </td>
                <td align="left">
                  李美文(41s)
                </td>
                <td align="left">
                  张翰林(44s)
                </td>
                <td align="left">
                  李金铭(47s)
                </td>
              </tr>
              <tr>
                <td>
                  <img src="../assets/img/tam.png" style="float:left;margin-right: -30px;"><span style="line-height:30px;">(鞍马)</span>
                </td>
                <td align="left">
                  李美文(47分)
                </td>
                <td align="left">
                  张翰林(40分)
                </td>
                <td align="left">
                  李金铭(37分)
                </td>
              </tr>
              <tr>
                <td>
                  <img src="../assets/img/ty.png" style="float:left;margin-right: -30px;"><span style="line-height:30px;">(跳远)</span>
                </td>
                <td align="left">
                  李美文(3.4m)
                </td>
                <td align="left">
                  张翰林(3.2m)
                </td>
                <td align="left">
                  李金铭(3.1m)
                </td>
              </tr>
              <tr>
                <td>
                  <img src="../assets/img/xc.png" style="float:left;margin-right: -30px;"><span style="line-height:30px;">(自行车)</span>
                </td>
                <td align="left">
                  李美文(56s)
                </td>
                <td align="left">
                  张翰林(58s)
                </td>
                <td align="left">
                  李金铭(60s)
                </td>
              </tr>
              <tr>
                <td>
                  <img src="../assets/img/ymq.png" style="float:left;margin-right: -30px;"><span style="line-height:30px;">(羽毛球)</span>
                </td>
                <td align="left">
                  李美文
                </td>
                <td align="left">
                  张翰林
                </td>
                <td align="left">
                  李金铭
                </td>
              </tr>
              <tr>
                <td>
                  <img src="../assets/img/yy.png" style="float:left;margin-right: -30px;"><span style="line-height:30px;">(游泳)</span>
                </td>
                <td align="left">
                  李小林(1m36s)
                </td>
                <td align="left">
                  张梦婷(1m44s)
                </td>
                <td align="left">
                  李飘逸(1m49s)
                </td>
              </tr>
              <tr>
                <td>
                  <img src="../assets/img/zq.png" style="float:left;margin-right: -30px;"><span style="line-height:30px;">(足球)</span>
                </td>
                <td align="left">
                  李桂峰
                </td>
                <td align="left">
                  张凯宏
                </td>
                <td align="left">
                  李灵纹
                </td>
              </tr>
            </tbody>
          </table>
        </el-col>
        <el-col :span="8">
          <div><el-tag type="success">奖牌榜(个人)</el-tag></div>
          <el-divider></el-divider>
          <table class="rankingTable">
            <thead>
            <tr>
              <th>姓名</th>
              <th align="center"><img src="../assets/img/1.png"></th>
              <th align="center"><img src="../assets/img/2.png"></th>
              <th align="center"><img src="../assets/img/3.png"></th>
            </tr>
            </thead>
            <tbody>
            <tr>
              <td>
                张雪梅
              </td>
              <td align="center">
                19
              </td>
              <td align="center">
                9
              </td>
              <td align="center">
                12
              </td>
            </tr>
            <tr>
              <td>
                李龙辉
              </td>
              <td align="center">
                17
              </td>
              <td align="center">
                11
              </td>
              <td align="center">
                9
              </td>
            </tr>
            <tr>
              <td>
                李龙辉
              </td>
              <td align="center">
                17
              </td>
              <td align="center">
                11
              </td>
              <td align="center">
                9
              </td>
            </tr>
            <tr>
              <td>
                李龙辉
              </td>
              <td align="center">
                17
              </td>
              <td align="center">
                11
              </td>
              <td align="center">
                9
              </td>
            </tr>
            <tr>
              <td>
                李龙辉
              </td>
              <td align="center">
                17
              </td>
              <td align="center">
                11
              </td>
              <td align="center">
                9
              </td>
            </tr>
            <tr>
              <td>
                李龙辉
              </td>
              <td align="center">
                17
              </td>
              <td align="center">
                11
              </td>
              <td align="center">
                9
              </td>
            </tr>
            <tr>
              <td>
                李龙辉
              </td>
              <td align="center">
                17
              </td>
              <td align="center">
                11
              </td>
              <td align="center">
                9
              </td>
            </tr>
            <tr>
              <td>
                李龙辉
              </td>
              <td align="center">
                17
              </td>
              <td align="center">
                11
              </td>
              <td align="center">
                9
              </td>
            </tr>
            <tr>
              <td>
                李龙辉
              </td>
              <td align="center">
                17
              </td>
              <td align="center">
                11
              </td>
              <td align="center">
                9
              </td>
            </tr>
            <tr>
              <td>
                李龙辉
              </td>
              <td align="center">
                17
              </td>
              <td align="center">
                11
              </td>
              <td align="center">
                9
              </td>
            </tr>
            <tr>
              <td>
                李龙辉
              </td>
              <td align="center">
                17
              </td>
              <td align="center">
                11
              </td>
              <td align="center">
                9
              </td>
            </tr>
            <tr>
              <td>
                李龙辉
              </td>
              <td align="center">
                17
              </td>
              <td align="center">
                11
              </td>
              <td align="center">
                9
              </td>
            </tr>
            <tr>
              <td>
                李龙辉
              </td>
              <td align="center">
                17
              </td>
              <td align="center">
                11
              </td>
              <td align="center">
                9
              </td>
            </tr>
            <tr>
              <td>
                李龙辉
              </td>
              <td align="center">
                17
              </td>
              <td align="center">
                11
              </td>
              <td align="center">
                9
              </td>
            </tr>
            <tr>
              <td>
                李龙辉
              </td>
              <td align="center">
                17
              </td>
              <td align="center">
                11
              </td>
              <td align="center">
                9
              </td>
            </tr>

            </tbody>
          </table>
        </el-col>
        <el-col :span="8">
          <el-row>
            <div><el-tag type="success">参赛人数统计</el-tag></div>
            <el-divider></el-divider>
            <div id="lineChart" style="width: 100%;height: 300px;"></div>
          </el-row>
          <el-row>
            <div id="pieChart" style="width: 100%;height: 500px;"></div>
          </el-row>
        </el-col>
      </el-row>
    </div>
</template>
<style>

  .rankingTable > tbody > tr{
    height: 40px;
  }

  .rankingTable{
    width: 100%;
    text-align: center;
    border: 1px solid rgba(227, 186, 99, 0.29);
    border-radius: 10px;
    background-color: rgba(227, 186, 99, 0.29);
  }
</style>
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

  let initLineChart = function() {
    // 基于准备好的dom，初始化echarts实例
    let myChart = echarts.init(document.getElementById('lineChart'))
    var arr1 = generateRandomArr(1,300,15)
    var arr2 = generateRandomArr(1,300,15)
    var arr3 = generateRandomArr(1,300,15)
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
          boundaryGap : true,
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
          data : ['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15']
        }
      ],
      yAxis : [
        {
          show:true,
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
            width:3
          },

          data:arr1
        },
        {
          name:'同比',
          type:'line',
          smooth: true,
          symbol:'none',
          lineStyle:{
            width:3
          },

          data:arr2
        },
        {
          name:'环比',
          type:'line',
          smooth: true,
          symbol:'none',
          lineStyle:{
            width:3
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
      legend: {
        orient: 'vertical',
        x: 'left',
        y: 'center',
        textStyle: {
          fontSize: 14,
          color:'#efc651'
        },
        data:['跑步','跳高','跳远','游泳','标枪','跨栏','射箭','鞍马']
      },
      toolbox: {
        show : false
      },
      series : [

        {
          name:'比赛项目',
          type:'pie',
          radius : [30, 80],
          center : ['50%', '30%'],
          roseType : 'radius',
          labelLine:{
            lineStyle:{
              color:'#efc651'
            }
          },
          data:[
            {value:35, name:'跑步',label:{
                color:'#efc651'
              }},
            {value:25, name:'跳高',label:{
                color:'#efc651'
              }},
            {value:22, name:'跳远',label:{
                color:'#efc651'
              }},
            {value:20, name:'游泳',label:{
                color:'#efc651'
              }},
            {value:19, name:'标枪',label:{
                color:'#efc651'
              }},
            {value:15, name:'跨栏',label:{
                color:'#efc651'
              }},
            {value:10, name:'射箭',label:{
                color:'#efc651'
              }},
            {value:8, name:'鞍马',label:{
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
    initLineChart,
    initPieChart
  },
  mounted: function() {
    this.initLineChart()
    this.initPieChart()
  }
}
</script>
