<template>
  <div class="mainDiv">

    <el-row>
      <span class="title-text">
        <i class="fa fa-user-circle fa-fw"></i>教师管理
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

          <el-form-item v-if="currentLoginType == 'admin'">
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
        label="姓名"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="编号"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.code }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="性别"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.sexStr }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="年龄"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.age }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="任教时间"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.entranceDate }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="电话"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.phone }}</span>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" min-width="100px">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="warning"
            @click="handleSee(scope.$index, scope.row)">查看</el-button>
          <el-button
            v-if="currentLoginType == 'admin'"
            size="mini"
            type="success"
            @click="handleUpdate(scope.$index, scope.row)">编辑</el-button>
          <el-button
            v-if="currentLoginType == 'admin'"
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
      title="添加教师"
      :visible.sync="formVisibleAdd"
      :close-on-click-modal="false"
      top="15vh"
      width="55%"
      @close="closeDialogAdd">
      <el-form :model="addTeacherForm" :rules="rules" ref="addTeacherForm">
        <el-col :span="12">
          <el-form-item label="教师姓名" :label-width="formLabelWidth" prop="name">
            <el-input v-model="addTeacherForm.name"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="出生日期" :label-width="formLabelWidth" prop="birthday">
            <el-date-picker
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="选择出生日期"
              v-model="addTeacherForm.birthday"
              style="width: 100%;"></el-date-picker>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="教师性别" :label-width="formLabelWidth" prop="sex">
            <el-select v-model="addTeacherForm.sex" placeholder="请选择教师性别" style="width: 100%;">
              <el-option
                v-for="item in sexOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="登录密码" :label-width="formLabelWidth" prop="password">
            <el-input v-model="addTeacherForm.password" show-password></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="教师编号" :label-width="formLabelWidth" prop="code">
            <el-input v-model="addTeacherForm.code"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="教师电话" :label-width="formLabelWidth" prop="phone">
            <el-input v-model="addTeacherForm.phone"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="教师民族" :label-width="formLabelWidth" prop="nation">
            <el-input v-model="addTeacherForm.nation"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="进修专业" :label-width="formLabelWidth" prop="major">
            <el-input v-model="addTeacherForm.major"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="教师国籍" :label-width="formLabelWidth" prop="nationality">
            <el-input v-model="addTeacherForm.nationality"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="任教时间" :label-width="formLabelWidth" prop="entranceDate">
            <el-date-picker
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="选择任教时间"
              v-model="addTeacherForm.entranceDate"
              style="width: 100%;"></el-date-picker>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="身份证号" :label-width="formLabelWidth" prop="identity">
            <el-input v-model="addTeacherForm.identity"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="出生籍贯" :label-width="formLabelWidth" prop="nativePlace">
            <el-input v-model="addTeacherForm.nativePlace"></el-input>
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
      title="修改教师"
      :visible.sync="formVisibleUpdate"
      :close-on-click-modal="false"
      top="15vh"
      width="55%"
      @close="closeDialogUpdate">
      <el-form :model="updateTeacherForm" :rules="rules" ref="updateTeacherForm">
        <el-col :span="12">
          <el-form-item label="教师姓名" :label-width="formLabelWidth" prop="name">
            <el-input v-model="updateTeacherForm.name"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="出生日期" :label-width="formLabelWidth" prop="birthday">
            <el-date-picker
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="选择出生日期"
              v-model="updateTeacherForm.birthday"
              style="width: 100%;"></el-date-picker>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="教师性别" :label-width="formLabelWidth" prop="sex">
            <el-select v-model="updateTeacherForm.sex" placeholder="请选择教师性别" style="width: 100%;">
              <el-option
                v-for="item in sexOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="登录密码" :label-width="formLabelWidth" prop="password">
            <el-input v-model="updateTeacherForm.password" :disabled="true" type="password"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="教师编号" :label-width="formLabelWidth" prop="code">
            <el-input v-model="updateTeacherForm.code"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="教师电话" :label-width="formLabelWidth" prop="phone">
            <el-input v-model="updateTeacherForm.phone"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="教师民族" :label-width="formLabelWidth" prop="nation">
            <el-input v-model="updateTeacherForm.nation"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="进修专业" :label-width="formLabelWidth" prop="major">
            <el-input v-model="updateTeacherForm.major"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="教师国籍" :label-width="formLabelWidth" prop="nationality">
            <el-input v-model="updateTeacherForm.nationality"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="任教时间" :label-width="formLabelWidth" prop="entranceDate">
            <el-date-picker
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="选择任教时间"
              v-model="updateTeacherForm.entranceDate"
              style="width: 100%;"></el-date-picker>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="身份证号" :label-width="formLabelWidth" prop="identity">
            <el-input v-model="updateTeacherForm.identity"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="出生籍贯" :label-width="formLabelWidth" prop="nativePlace">
            <el-input v-model="updateTeacherForm.nativePlace"></el-input>
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

    <el-dialog
      title="详细信息"
      :visible.sync="dialogTableVisible"
      top="20vh"
      width="50%"
      @close="closeDialogDetail">
      <table cellspacing="15px" style="width: 100%;border: 1px solid gray;">
        <tr>
          <td>教师名族</td>
          <td>{{nation}}</td>
          <td>所属学院</td>
          <td>{{college}}</td>
        </tr>
        <tr>
          <td>进修专业</td>
          <td>{{major}}</td>
          <td>教师国籍</td>
          <td>{{nationality}}</td>
        </tr>
        <tr>
          <td>身份证号</td>
          <td>{{identity}}</td>
          <td>出生籍贯</td>
          <td>{{nativePlace}}</td>
        </tr>
      </table>
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
    this.addTeacherForm = {}
    this.formVisibleAdd = true
  }

  //点击修改按钮
  let handleUpdate = function (index, row) {
    this.updateTeacherForm = Object.assign({}, row)
    this.formVisibleUpdate = true
  }

  //点击修改按钮
  let handleSee = function (index, row) {
    this.dialogTableVisible = true
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
        id:row.id
      }
      this.$api.post(this.GLOBAL.httpRequestUrl + '/teacher/delete', params, response => {
        this.pageLoading = false;
        this.initDataTable()
      });

      this.$message({
        message: "删除教师成功",
        type: 'success'
      });
      this.pageLoading = false
      this.$common.recordLogs("删除教师" + row.name + "的信息")
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
    this.$refs.addTeacherForm.resetFields()
  }


  //关闭对话框时的操作（修改）
  let closeDialogUpdate = function () {
    this.formVisibleUpdate = false;
    this.$refs.updateTeacherForm.resetFields()
  }

  //点击添加提交按钮
  let handleSubmitAdd = function () {
    if (this.formLoading) {
      return
    }
    this.$refs.addTeacherForm.validate(valid => {
      if (!valid) {
        return
      }
      this.formLoading = true
      this.formVisibleAdd = false

      let params = {
        birthday: this.addTeacherForm.birthday,
        name: this.addTeacherForm.name,
        code:this.addTeacherForm.code,
        phone:this.addTeacherForm.phone,
        sex:this.addTeacherForm.sex,
        nation:this.addTeacherForm.nation,
        major:this.addTeacherForm.major,
        nationality:this.addTeacherForm.nationality,
        entranceDate:this.addTeacherForm.entranceDate,
        identity:this.addTeacherForm.identity,
        nativePlace:this.addTeacherForm.nativePlace,
        password:this.$md5(this.addTeacherForm.password)
      }


      this.$api.post(this.GLOBAL.httpRequestUrl + '/teacher/insert', params, response => {
        this.pageLoading = false;
        this.initDataTable()
      });

      this.$message({
        message: "添加教师成功",
        type: 'success'
      });
      this.$common.recordLogs("添加教师" + this.addTeacherForm.name + "的信息")
      this.formLoading = false
    })

  }

  //点击修改提交按钮
  let handleSubmitUpdate = function () {
    if (this.formLoading) {
      return
    }
    this.$refs.updateTeacherForm.validate(valid => {
      if (!valid) {
        return
      }
      this.formLoading = true
      this.formVisibleUpdate = false

      let params = {
        id:this.updateTeacherForm.id,
        birthday: this.updateTeacherForm.birthday,
        name: this.updateTeacherForm.name,
        code:this.updateTeacherForm.code,
        phone:this.updateTeacherForm.phone,
        sex:this.updateTeacherForm.sex,
        nation:this.updateTeacherForm.nation,
        major:this.updateTeacherForm.major,
        nationality:this.updateTeacherForm.nationality,
        entranceDate:this.updateTeacherForm.entranceDate,
        identity:this.updateTeacherForm.identity,
        nativePlace:this.updateTeacherForm.nativePlace
      }


      this.$api.post(this.GLOBAL.httpRequestUrl + '/teacher/update', params, response => {
        this.pageLoading = false;
        this.initDataTable()
      });

      this.$message({
        message: "修改教师成功",
        type: 'success'
      });
      this.$common.recordLogs("修改教师" + this.updateTeacherForm.name + "的信息")
      this.formLoading = false
    })

  }

  //form验证规则
  const rules = {
    name: [{
      required: true,
      message: '请输入教师姓名',
      trigger: 'blur'
    }],
    code: [{
      required: true,
      message: '请输入教师编号',
      trigger: 'blur'
    }],
    birthday: [{
      required: true,
      message: '请选择出生日期',
      trigger: 'blur'
    }],
    phone: [{
      required: true,
      message: '请输入电话号码',
      trigger: 'blur'
    }],
    sex: [{
      required: true,
      message: '请输入教师性别',
      trigger: 'blur'
    }],
    nation: [{
      required: true,
      message: '请输入教师民族',
      trigger: 'blur'
    }],
    college: [{
      required: true,
      message: '请输入所属学院',
      trigger: 'blur'
    }],
    major: [{
      required: true,
      message: '请输入进修专业',
      trigger: 'blur'
    }],
    nationality: [{
      required: true,
      message: '请输入教师国籍',
      trigger: 'blur'
    }],
    entranceDate: [{
      required: true,
      message: '请输入入学时间',
      trigger: 'blur'
    }],
    studentType: [{
      required: true,
      message: '请输入教师类型',
      trigger: 'blur'
    }],
    identity: [{
      required: true,
      message: '请输入身份证号',
      trigger: 'blur'
    }],
    nativePlace: [{
      required: true,
      message: '请输入出生籍贯',
      trigger: 'blur'
    }],
    password: [{
      required: true,
      message: '请输入登录密码',
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

    this.$api.post(this.GLOBAL.httpRequestUrl + '/teacher/getAllByPage', params, response => {
      if (response.status == 200) {
        this.total = response.data.iTotalRecords;
        this.tableData = response.data.data;
      }
      this.pageLoading = false;
    });
  }

  let closeDialogDetail = function(){
    this.dialogTableVisible = false
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
        sexOptions:[
          {
            value: 1,
            label: '男'
          },
          {
            value: 2,
            label: '女'
          }
        ],
        formVisibleAdd:false,
        formVisibleUpdate:false,
        addTeacherForm:{
          birthday: '',
          name: '',
          code:'',
          phone:'',
          sex:'',
          nation:'',
          major:'',
          nationality:'',
          entranceDate:'',
          identity:'',
          nativePlace:'',
          password:''
        },
        updateTeacherForm:{
          id:'',
          birthday: '',
          name: '',
          code:'',
          phone:'',
          sex:'',
          nation:'',
          major:'',
          nationality:'',
          entranceDate:'',
          identity:'',
          nativePlace:'',
          password:''
        },
        formLoading: false,
        formLabelWidth: '100px',
        rules: rules,
        nation:'',
        college:'',
        major:'',
        nationality:'',
        identity:'',
        nativePlace:''

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
      closeDialogDetail
    },
    mounted: function () {
      this.initDataTable();
      this.currentLoginType = sessionStorage.getItem('userType');
    }
  }
</script>
