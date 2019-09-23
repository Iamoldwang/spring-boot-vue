<template>
  <div class="mainDiv">

    <el-row>
      <span class="title-text">
        <i class="fa fa-user-o fa-fw"></i>学生管理
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
          <el-input v-model="filtersForm.code" placeholder="学号" style="width: 200px;" />
        </el-form-item>


        <el-form-item>
          <el-button type="blue" @click="handleQuery" icon="el-icon-search">查询</el-button>
        </el-form-item>

        <el-form-item>
          <el-button type="success" @click="handleReset" icon="el-icon-refresh">重置</el-button>
        </el-form-item>

        <el-form-item v-if="currentLoginType != 'student'">
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
        label="学号"
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
        label="入学时间"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.entranceDate }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="学生类型"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.studentTypeStr }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="电话"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.phone }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="状态"
        width="100"
        align="center">
        <template slot-scope="scope">
          <el-button
            size="mini"
            :type="scope.row.status == 1 ? 'info' : 'success'"
            @click="handleApproval(scope.$index, scope.row)">{{scope.row.status == 1 ? '待审核' : '已注册'}}</el-button>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" min-width="100px">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="warning"
            @click="handleSee(scope.$index, scope.row)">查看</el-button>
          <el-button
            v-if="currentLoginType != 'student'"
            size="mini"
            class="btn-blue"
            @click="handleUpdate(scope.$index, scope.row)">编辑</el-button>
          <el-button
            v-if="currentLoginType != 'student'"
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
      title="添加学生"
      :visible.sync="formVisibleAdd"
      :close-on-click-modal="false"
      top="15vh"
      width="55%"
      @close="closeDialogAdd">
      <el-form :model="addUserForm" :rules="rules" ref="addUserForm">
        <el-col :span="12">
          <el-form-item label="学生姓名" :label-width="formLabelWidth" prop="name">
            <el-input v-model="addUserForm.name"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="出生日期" :label-width="formLabelWidth" prop="birthday">
            <el-date-picker
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="选择出生日期"
              v-model="addUserForm.birthday"
              style="width: 100%;"></el-date-picker>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="学生性别" :label-width="formLabelWidth" prop="sex">
            <el-select v-model="addUserForm.sex" placeholder="请选择学生性别" style="width: 100%;">
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
            <el-input v-model="addUserForm.password" show-password></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="学生编号" :label-width="formLabelWidth" prop="code">
            <el-input v-model="addUserForm.code"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="学生电话" :label-width="formLabelWidth" prop="phone">
            <el-input v-model="addUserForm.phone"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="学生民族" :label-width="formLabelWidth" prop="nation">
            <el-input v-model="addUserForm.nation"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="所属学院" :label-width="formLabelWidth" prop="college">
            <el-input v-model="addUserForm.college"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="进修专业" :label-width="formLabelWidth" prop="major">
            <el-input v-model="addUserForm.major"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="学生国籍" :label-width="formLabelWidth" prop="nationality">
            <el-input v-model="addUserForm.nationality"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="入学时间" :label-width="formLabelWidth" prop="entranceDate">
            <el-date-picker
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="选择入学时间"
              v-model="addUserForm.entranceDate"
              style="width: 100%;"></el-date-picker>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="学生类型" :label-width="formLabelWidth" prop="studentType">
            <el-select v-model="addUserForm.studentType" placeholder="请选择学生类型" style="width: 100%;">
              <el-option
                v-for="item in studentTypeOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="身份证号" :label-width="formLabelWidth" prop="identity">
            <el-input v-model="addUserForm.identity"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="出生籍贯" :label-width="formLabelWidth" prop="nativePlace">
            <el-input v-model="addUserForm.nativePlace"></el-input>
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
      title="修改学生"
      :visible.sync="formVisibleUpdate"
      :close-on-click-modal="false"
      top="15vh"
      width="55%"
      @close="closeDialogUpdate">
      <el-form :model="updateUserForm" :rules="rules" ref="updateUserForm">
        <el-col :span="12">
          <el-form-item label="学生姓名" :label-width="formLabelWidth" prop="name">
            <el-input v-model="updateUserForm.name"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="出生日期" :label-width="formLabelWidth" prop="birthday">
            <el-date-picker
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="选择出生日期"
              v-model="updateUserForm.birthday"
              style="width: 100%;"></el-date-picker>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="学生性别" :label-width="formLabelWidth" prop="sex">
            <el-select v-model="updateUserForm.sex" placeholder="请选择学生性别" style="width: 100%;">
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
            <el-input v-model="updateUserForm.password" :disabled="true" type="password"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="学生编号" :label-width="formLabelWidth" prop="code">
            <el-input v-model="updateUserForm.code"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="学生电话" :label-width="formLabelWidth" prop="phone">
            <el-input v-model="updateUserForm.phone"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="学生民族" :label-width="formLabelWidth" prop="nation">
            <el-input v-model="updateUserForm.nation"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="所属学院" :label-width="formLabelWidth" prop="college">
            <el-input v-model="updateUserForm.college"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="进修专业" :label-width="formLabelWidth" prop="major">
            <el-input v-model="updateUserForm.major"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="学生国籍" :label-width="formLabelWidth" prop="nationality">
            <el-input v-model="updateUserForm.nationality"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="入学时间" :label-width="formLabelWidth" prop="entranceDate">
            <el-date-picker
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="选择入学时间"
              v-model="updateUserForm.entranceDate"
              style="width: 100%;"></el-date-picker>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="学生类型" :label-width="formLabelWidth" prop="studentType">
            <el-select v-model="updateUserForm.studentType" placeholder="请选择学生类型" style="width: 100%;">
              <el-option
                v-for="item in studentTypeOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="身份证号" :label-width="formLabelWidth" prop="identity">
            <el-input v-model="updateUserForm.identity"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="出生籍贯" :label-width="formLabelWidth" prop="nativePlace">
            <el-input v-model="updateUserForm.nativePlace"></el-input>
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
          <td>学生名族</td>
          <td>{{nation}}</td>
          <td>所属学院</td>
          <td>{{college}}</td>
        </tr>
        <tr>
          <td>进修专业</td>
          <td>{{major}}</td>
          <td>学生国籍</td>
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
<style>

  .btn-blue{
    color: #fff;
    background-color: #409eff;
    border-color: #409eff;
  }
  .btn-blue:hover{
    color: #fff;
    background-color: #409eff;
    border-color: #409eff;
  }
</style>
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
      this.$api.post(this.GLOBAL.httpRequestUrl + '/student/delete', params, response => {
        this.pageLoading = false;
        this.initDataTable()
      });

      this.$message({
        message: "删除学生成功",
        type: 'success'
      });

      this.$common.recordLogs("删除学生" + row.name + "的信息")
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
        birthday: this.addUserForm.birthday,
        name: this.addUserForm.name,
        code:this.addUserForm.code,
        phone:this.addUserForm.phone,
        sex:this.addUserForm.sex,
        nation:this.addUserForm.nation,
        college:this.addUserForm.college,
        major:this.addUserForm.major,
        nationality:this.addUserForm.nationality,
        entranceDate:this.addUserForm.entranceDate,
        studentType:this.addUserForm.studentType,
        identity:this.addUserForm.identity,
        nativePlace:this.addUserForm.nativePlace,
        password:this.$md5(this.addUserForm.password)
      }


      this.$api.post(this.GLOBAL.httpRequestUrl + '/student/insert', params, response => {
        this.pageLoading = false;
        this.initDataTable()
      });

      this.$message({
        message: "添加学生成功",
        type: 'success'
      });
      this.$common.recordLogs("添加学生" + this.addUserForm.name + "的信息")
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
        id:this.updateUserForm.id,
        birthday: this.updateUserForm.birthday,
        name: this.updateUserForm.name,
        code:this.updateUserForm.code,
        phone:this.updateUserForm.phone,
        sex:this.updateUserForm.sex,
        nation:this.updateUserForm.nation,
        college:this.updateUserForm.college,
        major:this.updateUserForm.major,
        nationality:this.updateUserForm.nationality,
        entranceDate:this.updateUserForm.entranceDate,
        studentType:this.updateUserForm.studentType,
        identity:this.updateUserForm.identity,
        nativePlace:this.updateUserForm.nativePlace
      }


      this.$api.post(this.GLOBAL.httpRequestUrl + '/student/update', params, response => {
        this.pageLoading = false;
        this.initDataTable()
      });

      this.$message({
        message: "修改学生成功",
        type: 'success'
      });
      this.$common.recordLogs("修改学生" + this.updateUserForm.name + "的信息")
      this.formLoading = false
    })

  }

  //form验证规则
  const rules = {
    name: [{
      required: true,
      message: '请输入学生姓名',
      trigger: 'blur'
    }],
    code: [{
      required: true,
      message: '请输入学生编号',
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
      message: '请输入学生性别',
      trigger: 'blur'
    }],
    nation: [{
      required: true,
      message: '请输入学生民族',
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
      message: '请输入学生国籍',
      trigger: 'blur'
    }],
    entranceDate: [{
      required: true,
      message: '请输入入学时间',
      trigger: 'blur'
    }],
    studentType: [{
      required: true,
      message: '请输入学生类型',
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

    this.$api.post(this.GLOBAL.httpRequestUrl + '/student/getAllByPage', params, response => {
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

  let handleApproval = function (index,row) {

    if(row.status != 1 || this.currentLoginType == 'student'){
      return
    }

    this.$confirm('确定同意该学生的注册申请吗？', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }).then(() => {

      let params = {
        studentId:row.id
      }
      this.$api.post(this.GLOBAL.httpRequestUrl + '/student/approvalRegister', params, response => {
        this.initDataTable()
      });

      this.$message({
        message: "审核成功",
        type: 'success'
      });
      this.pageLoading = false
      this.$common.recordLogs("审核学生注册通过")
    }).catch(e => {

    })

  }
  export default {
    data() {
      return {
        dialogTableVisible:false,
        currentLoginType:'',
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
            label:'留学生'
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
          birthday: '',
          name: '',
          code:'',
          phone:'',
          sex:'',
          nation:'',
          college:'',
          major:'',
          nationality:'',
          entranceDate:'',
          studentType:'',
          identity:'',
          nativePlace:'',
          password:''
        },
        updateUserForm:{
          id:'',
          birthday: '',
          name: '',
          code:'',
          phone:'',
          sex:'',
          nation:'',
          college:'',
          major:'',
          nationality:'',
          entranceDate:'',
          studentType:'',
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
      closeDialogDetail,
      handleApproval
    },
    mounted: function () {
      this.initDataTable();
      this.currentLoginType = sessionStorage.getItem('userType');
    }
  }
</script>
