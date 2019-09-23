<template>
  <div class="mainDiv">

    <el-row>
      <span class="title-text">
        <i class="fa fa-calendar-check-o fa-fw"></i>日志管理
      </span>
      <el-divider></el-divider>
    </el-row>

    <!--工具条-->
    <el-row>
      <el-col>
        <el-form :inline="true" ref="filtersForm" :model="filtersForm">

          <el-form-item>
            <el-input v-model="filtersForm.name" placeholder="姓名" style="width: 200px;" />
          </el-form-item>

          <el-form-item>
            <el-input v-model="filtersForm.code" placeholder="编号" style="width: 200px;" />
          </el-form-item>


          <el-form-item>
            <el-button type="blue" @click="handleQuery" icon="el-icon-search">查询</el-button>
          </el-form-item>

          <el-form-item>
            <el-button type="success" @click="handleReset" icon="el-icon-refresh">重置</el-button>
          </el-form-item>

        </el-form>
      </el-col>
    </el-row>


    <el-table
      :data="tableData"
      v-loading="pageLoading"
      style="width: 100%">

      <el-table-column
        label="日志类型"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.typeStr }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="日志内容"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.content }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="人员"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.personName }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="记录时间"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.logTimeStr }}</span>
        </template>
      </el-table-column>

    </el-table>


    <el-pagination
      background
      layout="prev, pager, next, jumper,->, total, slot"
      @current-change="handleCurrentChange"
      :page-size="size"
      :total="total"
      style="float:right;margin-top: 10px;">
    </el-pagination>

  </div>

</template>

<script>

  //点击查询
  let handleQuery = function () {
    this.page = 1
    this.initDataTable()
  }

  //点击重置
  let handleReset = function () {
    this.filtersForm = {}
    this.page = 1
    this.initDataTable()
  }

  //点击翻页
  let handleCurrentChange = function (val) {
    this.page = val
    this.initDataTable()
  }

  let initDataTable = function () {
    if (this.pageLoading){
      return;
    }
    this.pageLoading = true

    let params = {
      page: this.page,
      size: this.size
    }

    if (this.filtersForm.code != undefined && this.filtersForm.code != null && this.filtersForm.code != '') {
      params.code = this.filtersForm.code
    }

    if (this.filtersForm.name != undefined && this.filtersForm.name != null && this.filtersForm.name != '') {
      params.name = this.filtersForm.name
    }

    this.$api.post(this.GLOBAL.httpRequestUrl + '/log/getAllByPage', params, response => {
      if (response.status == 200) {
        this.total = response.data.iTotalRecords;
        this.tableData = response.data.data;
      }
      this.pageLoading = false;
    });
  }
  export default {
    data() {
      return {
        dialogTableVisible:false,
        currentLoginType:'teacher',
        //页码
        page: 1,
        //每页数量
        size: 10,
        total: 0,
        pageLoading:false,
        tableData: [],
        filtersForm: {
          name:'',
          code:''
        },
        formVisibleAdd:false,
        formVisibleUpdate:false,
        formLoading: false,
        formLabelWidth: '100px'
      }
    },
    methods: {

      handleQuery,
      handleReset,
      handleCurrentChange,
      initDataTable
    },
    mounted: function () {
      this.initDataTable();
      this.currentLoginType = sessionStorage.getItem('userType');
    }
  }
</script>
