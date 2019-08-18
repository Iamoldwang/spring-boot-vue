<template>
  <div class="mainDiv">

    <el-row>
      <span class="title-text">
        <i class="fa fa-user fa-fw"></i>用户管理
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

        <el-form-item label="起止时间">
          <el-col :span="11">
            <el-date-picker type="date" placeholder="选择开始日期" v-model="filtersForm.dateStart" style="width: 100%;"></el-date-picker>
          </el-col>
          <el-col class="line" :span="2" style="text-align: center;">-</el-col>
          <el-col :span="11">
            <el-date-picker type="date" placeholder="选择结束日期" v-model="filtersForm.dateEnd" style="width: 100%;"></el-date-picker>
          </el-col>
        </el-form-item>

        <el-form-item>
        <el-button type="blue" @click="handleQuery" icon="el-icon-search">查询</el-button>
        </el-form-item>

        <el-form-item>
        <el-button type="success" @click="handleReset" icon="el-icon-refresh">重置</el-button>
        </el-form-item>

        <el-form-item>
        <el-button type="primary" @click="handleAdd" icon="el-icon-circle-plus-outline">添加</el-button>
        </el-form-item>

        </el-form>
      </el-col>
    </el-row>


    <el-table
      :data="tableData"
      style="width: 100%">

      <el-table-column
        label="姓名"
        align="center">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>姓名: {{ scope.row.name }}</p>
            <p>住址: {{ scope.row.address }}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.name }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>

      <el-table-column
        label="生日"
        align="center">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.birthDate }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="编号"
        align="center">
        <template slot-scope="scope">
          <i class="el-icon-star-off"></i>
          <span style="margin-left: 10px">{{ scope.row.code }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="电话"
        align="center">
        <template slot-scope="scope">
          <i class="el-icon-mobile-phone"></i>
          <span style="margin-left: 10px">{{ scope.row.phone }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="邮件"
        align="center">
        <template slot-scope="scope">
          <i class="el-icon-message"></i>
          <span style="margin-left: 10px">{{ scope.row.email }}</span>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleUpdate(scope.$index, scope.row)">编辑</el-button>
          <el-button
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
      :page-size="10"
      :total="15"
      style="float:right;margin-top: 10px;">
    </el-pagination>


    <el-dialog
      title="添加用户"
      :visible.sync="formVisibleAdd"
      :close-on-click-modal="false"
      top="30vh"
      width="55%"
      @close="closeDialogAdd">
      <el-form :model="addUserForm" :rules="rules" ref="addUserForm">
        <el-col :span="12">
          <el-form-item label="用户姓名" :label-width="formLabelWidth" prop="name">
            <el-input v-model="addUserForm.name"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="出生日期" :label-width="formLabelWidth" prop="birthDate">
            <el-date-picker
              type="date"
              placeholder="选择出生日期"
              v-model="addUserForm.birthDate"
              style="width: 100%;"></el-date-picker>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="用户编号" :label-width="formLabelWidth" prop="code">
            <el-input v-model="addUserForm.code"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="用户电话" :label-width="formLabelWidth" prop="phone">
            <el-input v-model="addUserForm.phone"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="用户邮箱" :label-width="formLabelWidth" prop="email">
            <el-input v-model="addUserForm.email"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="详细住址" :label-width="formLabelWidth" prop="address">
            <el-input v-model="addUserForm.address"></el-input>
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
      title="修改用户"
      :visible.sync="formVisibleUpdate"
      :close-on-click-modal="false"
      top="30vh"
      width="55%"
      @close="closeDialogUpdate">
      <el-form :model="updateUserForm" :rules="rules" ref="updateUserForm">
        <el-col :span="12">
          <el-form-item label="用户姓名" :label-width="formLabelWidth" prop="name">
            <el-input v-model="updateUserForm.name"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="出生日期" :label-width="formLabelWidth" prop="birthDate">
            <el-date-picker
              type="date"
              placeholder="选择出生日期"
              v-model="updateUserForm.birthDate"
              style="width: 100%;"></el-date-picker>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="用户编号" :label-width="formLabelWidth" prop="code">
            <el-input v-model="updateUserForm.code"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="用户电话" :label-width="formLabelWidth" prop="phone">
            <el-input v-model="updateUserForm.phone"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="用户邮箱" :label-width="formLabelWidth" prop="email">
            <el-input v-model="updateUserForm.email"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="详细住址" :label-width="formLabelWidth" prop="address">
            <el-input v-model="updateUserForm.address"></el-input>
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
    //TODO ajax
  }

  //点击重置
  let handleReset = function () {
    this.filtersForm = {}
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
      //TODO ajax
      this.$message({
        message: "删除用户成功",
        type: 'success'
      });
      this.pageLoading = false

    }).catch(e => {
    })
  }

  //点击翻页
  let handleCurrentChange = function (val) {
    //TODO 翻页
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

      //TODO ajax
      this.$message({
        message: "添加用户成功",
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
      //TODO ajax
      this.$message({
        message: "修改用户成功",
        type: 'success'
      });
      this.formLoading = false
    })

  }

  //form验证规则
  const rules = {
    name: [{
      required: true,
      message: '请输入用户姓名',
      trigger: 'blur'
    }],
    code: [{
      required: true,
      message: '请输入用户编号',
      trigger: 'blur'
    }],
    birthDate: [{
      required: true,
      message: '请选择出生日期',
      trigger: 'blur'
    }],
    phone: [{
      required: true,
      message: '请输入电话号码',
      trigger: 'blur'
    }],
    email: [{
      required: true,
      message: '请输入邮箱地址',
      trigger: 'blur'
    }],
    address: [{
      required: true,
      message: '请输入详细住址',
      trigger: 'blur'
    }],
  }

  export default {
    data() {
      return {
        tableData: [{
          birthDate: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄',
          code:'1001001',
          phone:'18566547896',
          email:'test@126.com'
        }, {
          birthDate: '2016-05-04',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1517 弄',
          code:'1001001',
          phone:'18566547896',
          email:'test@126.com'
        }, {
          birthDate: '2016-05-01',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1519 弄',
          code:'1001001',
          phone:'18566547896',
          email:'test@126.com'
        }, {
          birthDate: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄',
          code:'1001001',
          phone:'18566547896',
          email:'test@126.com'
        }, {
          birthDate: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄',
          code:'1001001',
          phone:'18566547896',
          email:'test@126.com'
        }, {
          birthDate: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄',
          code:'1001001',
          phone:'18566547896',
          email:'test@126.com'
        },{
          birthDate: '2016-05-01',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1519 弄',
          code:'1001001',
          phone:'18566547896',
          email:'test@126.com'
        }],
        filtersForm: {
          name:'',
          code:'',
          dateStart:'',
          dateEnd:''
        },
        formVisibleAdd:false,
        formVisibleUpdate:false,
        addUserForm:{
          birthDate: '',
          name: '',
          address: '',
          code:'',
          phone:'',
          email:''
        },
        updateUserForm:{
          birthDate: '',
          name: '',
          address: '',
          code:'',
          phone:'',
          email:''
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
      handleSubmitUpdate
    }
  }
</script>
