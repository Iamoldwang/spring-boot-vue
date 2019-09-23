<template>
  <div class="mainDiv">

    <el-row>
      <span class="title-text">
        <i class="fa fa-database fa-fw"></i>基础数据
      </span>
      <el-divider></el-divider>
    </el-row>

    <!--工具条-->
    <el-row>
      <el-col>
        <el-form :inline="true" ref="filtersForm" :model="filtersForm">

          <el-form-item>
            <el-select v-model="filtersForm.name" placeholder="请选择学生性别" style="width: 100%;">
              <el-option
                v-for="(item,index) in nameOptions"
                :key="item.type"
                :label="item.name"
                :value="item.type">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item>
            <el-button type="blue" @click="handleQuery" icon="el-icon-search">查询</el-button>
          </el-form-item>

          <el-form-item>
            <el-button type="success" @click="handleReset" icon="el-icon-refresh">重置</el-button>
          </el-form-item>

          <el-form-item v-if="currentLoginType == 'teacher'">
            <el-button type="primary" @click="handleAdd" icon="el-icon-circle-plus-outline">添加</el-button>
          </el-form-item>

        </el-form>
      </el-col>
    </el-row>


    <el-table
      :data="tableData"
      v-loading="pageLoading"
      style="width: 100%">

      <el-table-column
        label="类型ID"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.type }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="类型名称"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="数据编号"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.basicCode }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="数据名称"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.basicName }}</span>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" min-width="100px">
        <template slot-scope="scope">
          <el-button
            v-if="currentLoginType == 'teacher'"
            size="mini"
            type="success"
            @click="handleUpdate(scope.$index, scope.row)">编辑</el-button>
          <el-button
            v-if="currentLoginType == 'teacher'"
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
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


    <el-dialog
      title="添加数据"
      :visible.sync="formVisibleAdd"
      :close-on-click-modal="false"
      top="25vh"
      width="20%"
      @close="closeDialogAdd">
      <el-form :model="addUserForm" :rules="rules" ref="addUserForm">
        <el-col :span="24">
          <el-form-item label="类型名称" :label-width="formLabelWidth" prop="name">
            <el-select
              v-model="addUserForm.name"
              filterable
              allow-create
              default-first-option
              @change="changeName($event)"
              placeholder="请选择或者输入类型名称"
              style="width: 100%;">
              <el-option
                v-for="item in nameOptions"
                :key="item.type"
                :label="item.name"
                :value="item.type">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="数据名称" :label-width="formLabelWidth" prop="basicName">
            <el-input v-model="addUserForm.basicName"></el-input>
          </el-form-item>
        </el-col>


      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="info" @click="closeDialogAdd">取 消</el-button>
        <el-button
          type="success"
          @click="handleSubmitAdd"
          :loading="formLoading">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog
      title="修改数据"
      :visible.sync="formVisibleUpdate"
      :close-on-click-modal="false"
      top="25vh"
      width="20%"
      @close="closeDialogUpdate">
      <el-form :model="updateUserForm" :rules="rules" ref="updateUserForm">
        <el-col :span="24">
          <el-form-item label="类型名称" :label-width="formLabelWidth" prop="name">
            <el-input v-model="updateUserForm.name" :disabled="true"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="数据名称" :label-width="formLabelWidth" prop="basicName">
            <el-input v-model="updateUserForm.basicName"></el-input>
          </el-form-item>
        </el-col>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="info" @click="closeDialogUpdate">取 消</el-button>
        <el-button
          type="success"
          @click="handleSubmitUpdate"
          :loading="formLoading">确 定</el-button>
      </div>
    </el-dialog>

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

  //点击添加按钮
  let handleAdd = function () {
    this.addUserForm = {}
    this.formVisibleAdd = true
  }

  //点击修改按钮
  let handleUpdate = function (index, row) {
    this.updateUserForm = Object.assign({}, row)
    this.formVisibleUpdate = true
  }

  //点击修改按钮
  let handleSee = function (index, row) {
    this.nation = row.nation
    this.college = row.college
    this.major = row.major
    this.nationality = row.nationality
    this.identity = row.identity
    this.nativePlace = row.nativePlace
  }

  //点击删除按钮
  let handleDelete = function (index, row) {
    if (this.pageLoading)
      return

    this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }).then(() => {
      this.pageLoading = true

      let params = {
        type:row.type,
        basicCode:row.basicCode

      }
      this.$api.post(this.GLOBAL.httpRequestUrl + '/basicData/delete', params, response => {
        this.pageLoading = false;
        this.initDataTable()
      });

      this.$message({
        message: "删除数据成功",
        type: 'success'
      });
      this.pageLoading = false

    }).catch(e => {
    })
  }

  //点击翻页
  let handleCurrentChange = function (val) {
    this.page = val
    this.initDataTable()
  }

  //关闭对话框时的操作（添加）
  let closeDialogAdd = function () {
    this.formVisibleAdd = false;
    this.$refs.addUserForm.resetFields()
  }


  //关闭对话框时的操作（修改）
  let closeDialogUpdate = function () {
    this.formVisibleUpdate = false;
    this.$refs.updateUserForm.resetFields()
  }

  //点击添加提交按钮
  let handleSubmitAdd = function () {
    if (this.formLoading) {
      return
    }
    this.$refs.addUserForm.validate(valid => {
      if (!valid) {
        return
      }
      this.formLoading = true
      this.formVisibleAdd = false


      let params = {
        basicName: this.addUserForm.basicName,
        name: this.addUserForm.name

      }

      let flag = this.typeIds.indexOf(this.addUserForm.name)
      if(flag == -1){
        params.type = parseInt(this.typeIds[this.typeIds.length - 1]) + 1
        params.basicCode = 1
      }else{
        params.type = this.addUserForm.name
        for(var i = 0; i < this.nameOptions.length; i ++){
          if(this.nameOptions[i].type == this.addUserForm.name){
            params.basicCode = parseInt(this.nameOptions[i].basicCode) + 1
            params.name = this.nameOptions[i].name
          }
        }
      }

      this.$api.post(this.GLOBAL.httpRequestUrl + '/basicData/insert', params, response => {
        this.pageLoading = false;
        this.initDataTable()
      });

      this.$message({
        message: "添加数据成功",
        type: 'success'
      });
      this.formLoading = false
    })

  }

  //点击修改提交按钮
  let handleSubmitUpdate = function () {
    if (this.formLoading) {
      return
    }
    this.$refs.updateUserForm.validate(valid => {
      if (!valid) {
        return
      }
      this.formLoading = true
      this.formVisibleUpdate = false

      let params = {
        type: this.updateUserForm.type,
        basicCode: this.updateUserForm.basicCode,
        basicName: this.updateUserForm.basicName
      }


      this.$api.post(this.GLOBAL.httpRequestUrl + '/basicData/update', params, response => {
        this.pageLoading = false;
        this.initDataTable()
      });

      this.$message({
        message: "修改数据成功",
        type: 'success'
      });
      this.formLoading = false
    })

  }

  //form验证规则
  const rules = {
    name: [{
      required: true,
      message: '请选择类型名称',
      trigger: 'change'
    }],
    basicName: [{
      required: true,
      message: '请输入数据名称',
      trigger: 'blur'
    }]
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

    this.$api.post(this.GLOBAL.httpRequestUrl + '/basicData/getAllByPage', params, response => {
      if (response.status == 200) {
        this.total = response.data.iTotalRecords;
        this.tableData = response.data.data;
      }
      this.pageLoading = false;
    });
  }
  
  let initAllNames = function () {
    this.typeIds = []
    this.$api.post(this.GLOBAL.httpRequestUrl + '/basicData/getAllNames', null, response => {
      if (response.status == 200) {
        this.nameOptions = response.data.content;
        for(var i = 0; i < this.nameOptions.length; i ++){
          this.typeIds[i] = this.nameOptions[i].type
        }
      }
    });
  }

  let changeName = function (event) {
    this.selectName = event.target.value
  }

  export default {
    data() {
      return {
        currentLoginType:'student',
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
        selectName:'',
        typeIds:[],
        nameOptions:[],
        studentTypeOptions:[
          {
            value:1,
            label:'专科'
          },
          {
            value:2,
            label:'本科'
          },
          {
            value:3,
            label:'研究生'
          },
          {
            value:4,
            label:'留教师'
          },
          {
            value:5,
            label:'交换生'
          },
          {
            value:6,
            label:'国防生'
          }
        ],
        formVisibleAdd:false,
        formVisibleUpdate:false,
        addUserForm:{
          basicName: '',
          name: ''
        },
        updateUserForm:{
          type:'',
          basicCode: '',
          name: '',
          basicName:''
        },
        formLoading: false,
        formLabelWidth: '100px',
        rules: rules

      }
    },
    methods: {
      handleUpdate,
      handleDelete,
      handleQuery,
      handleReset,
      handleAdd,
      handleCurrentChange,
      closeDialogAdd,
      closeDialogUpdate,
      handleSubmitAdd,
      handleSubmitUpdate,
      handleSee,
      initDataTable,
      initAllNames,
      changeName
    },
    mounted: function () {
      this.initDataTable();
      this.initAllNames();
      this.currentLoginType = sessionStorage.getItem('userType');
    }
  }
</script>
