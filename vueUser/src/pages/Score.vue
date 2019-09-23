<template>
  <div class="mainDiv">

    <el-row>
      <span class="title-text">
        <i class="fa fa-file-text-o fa-fw"></i>成绩管理
      </span>
      <el-divider></el-divider>
    </el-row>

    <!--工具条-->
    <el-row>
      <el-col>
        <el-form :inline="true" ref="filtersForm" :model="filtersForm">

        <el-form-item>
          <el-select v-model="filtersForm.studentId" placeholder="请选择学生" style="width: 200px;">
            <el-option
              v-for="item in studentOptions"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-select v-model="filtersForm.project" placeholder="请选择项目" style="width: 200px;">
            <el-option
              v-for="item in projectOptions"
              :key="item.basicCode"
              :label="item.basicName"
              :value="item.basicCode">
            </el-option>
          </el-select>
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
      style="width: 100%">

      <el-table-column
        label="姓名"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.studentName }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="项目"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.projectStr }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="单位"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.unit }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="分数"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.score }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="排名"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.ranking }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="教师"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.teacherName }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="更新时间"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.updateTimeStr }}</span>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" v-if="currentLoginType != 'student'">
        <template slot-scope="scope">
          <el-button
            size="mini"
            v-if="currentLoginType != 'student'"
            type="primary"
            @click="handleUpdate(scope.$index, scope.row)">编辑</el-button>
          <el-button
            size="mini"
            v-if="currentLoginType != 'student'"
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
      title="添加成绩"
      :visible.sync="formVisibleAdd"
      :close-on-click-modal="false"
      top="20vh"
      width="25%"
      @close="closeDialogAdd">
      <el-form :model="addScoreForm" :rules="rules" ref="addScoreForm">
        <el-col :span="24">
          <el-form-item label="学生姓名" :label-width="formLabelWidth" prop="studentId">
            <el-select v-model="addScoreForm.studentId" placeholder="请选择学生" style="width: 100%;">
              <el-option
                v-for="item in studentOptions"
                :key="item.id"
                :label="item.name"
                :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="运动项目" :label-width="formLabelWidth" prop="project">
            <el-select v-model="addScoreForm.project" placeholder="请选择项目" style="width: 100%;">
              <el-option
                v-for="item in projectOptions"
                :key="item.basicCode"
                :label="item.basicName"
                :value="item.basicCode">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="项目单位" :label-width="formLabelWidth" prop="score">
            <el-input v-model="addScoreForm.unit"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="成绩分数" :label-width="formLabelWidth" prop="score">
            <el-input v-model="addScoreForm.score"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="成绩排名" :label-width="formLabelWidth" prop="ranking">
            <el-input v-model="addScoreForm.ranking"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="成绩说明" :label-width="formLabelWidth" prop="remark">
            <el-input v-model="addScoreForm.remark"></el-input>
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
      title="修改成绩"
      :visible.sync="formVisibleUpdate"
      :close-on-click-modal="false"
      top="20vh"
      width="25%"
      @close="closeDialogUpdate">
      <el-form :model="updateScoreForm" :rules="rules" ref="updateScoreForm">
        <el-col :span="24">
          <el-form-item label="学生姓名" :label-width="formLabelWidth" prop="studentId">
            <el-select v-model="updateScoreForm.studentId" placeholder="请选择学生" style="width: 100%;">
              <el-option
                v-for="item in studentOptions"
                :key="item.id"
                :label="item.name"
                :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="运动项目" :label-width="formLabelWidth" prop="project">
            <el-select v-model="updateScoreForm.project" placeholder="请选择项目" style="width: 100%;">
              <el-option
                v-for="item in projectOptions"
                :key="item.basicCode"
                :label="item.basicName"
                :value="item.basicCode">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="项目单位" :label-width="formLabelWidth" prop="score">
            <el-input v-model="updateScoreForm.unit"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="成绩分数" :label-width="formLabelWidth" prop="score">
            <el-input v-model="updateScoreForm.score"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="成绩排名" :label-width="formLabelWidth" prop="ranking">
            <el-input v-model="updateScoreForm.ranking"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="成绩说明" :label-width="formLabelWidth" prop="remark">
            <el-input v-model="updateScoreForm.remark"></el-input>
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
    this.addScoreForm = {}
    this.formVisibleAdd = true
  }

  //点击修改按钮
  let handleUpdate = function (index, row) {
    this.updateScoreForm = Object.assign({}, row)
    this.updateScoreForm.project = row.project + ''
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

      let params = {
        id:row.id
      }
      this.$api.post(this.GLOBAL.httpRequestUrl + '/score/delete', params, response => {
        this.pageLoading = false;
        this.initDataTable()
      });

      this.$message({
        message: "删除成绩成功",
        type: 'success'
      });
      this.pageLoading = false
      this.$common.recordLogs("删除学生" + row.studentName + "的成绩")
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
    this.$refs.addScoreForm.resetFields()
  }


  //关闭对话框时的操作（修改）
  let closeDialogUpdate = function () {
    this.formVisibleUpdate = false;
    this.$refs.updateScoreForm.resetFields()
  }

  //点击添加提交按钮
  let handleSubmitAdd = function () {
    if (this.formLoading) {
      return
    }
    this.$refs.addScoreForm.validate(valid => {
      if (!valid) {
        return
      }
      this.formLoading = true
      this.formVisibleAdd = false

      let params = {
        studentId: this.addScoreForm.studentId,
        project: this.addScoreForm.project,
        score:this.addScoreForm.score,
        ranking:this.addScoreForm.ranking,
        remark:this.addScoreForm.remark,
        unit:this.addScoreForm.unit
      }

      this.$api.post(this.GLOBAL.httpRequestUrl + '/score/insert', params, response => {
        this.pageLoading = false;
        this.initDataTable()
      });

      this.$message({
        message: "添加成绩成功",
        type: 'success'
      });
      this.$common.recordLogs("添加学生成绩")
      this.formLoading = false
    })

  }

  //点击修改提交按钮
  let handleSubmitUpdate = function () {
    if (this.formLoading) {
      return
    }
    this.$refs.updateScoreForm.validate(valid => {
      if (!valid) {
        return
      }
      this.formLoading = true
      this.formVisibleUpdate = false

      let params = {
        id:this.updateScoreForm.id,
        studentId: this.updateScoreForm.studentId,
        project: this.updateScoreForm.project,
        score:this.updateScoreForm.score,
        ranking:this.updateScoreForm.ranking,
        remark:this.updateScoreForm.remark,
        unit:this.updateScoreForm.unit
      }

      this.$api.post(this.GLOBAL.httpRequestUrl + '/score/update', params, response => {
        this.pageLoading = false;
        this.initDataTable()
      });

      this.$message({
        message: "修改成绩成功",
        type: 'success'
      });
      this.$common.recordLogs("修改学生成绩")
      this.formLoading = false
    })

  }
  
  let initAllStudents = function () {
    this.$api.post(this.GLOBAL.httpRequestUrl + '/student/getAll', null, response => {
      if (response.status == 200) {
        this.studentOptions = response.data.content;
      }
    });
  }

  let initAllProjects = function () {
    let params = {
      type:1
    }
    this.$api.post(this.GLOBAL.httpRequestUrl + '/basicData/getBasicDataByType', params, response => {
      if (response.status == 200) {
        this.projectOptions = response.data.data;
      }
    });
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

    if (this.filtersForm.studentId != undefined && this.filtersForm.studentId != null && this.filtersForm.studentId != '') {
      params.studentId = this.filtersForm.studentId
    }

    if (this.filtersForm.project != undefined && this.filtersForm.project != null && this.filtersForm.project != '') {
      params.project = this.filtersForm.project
    }

    this.$api.post(this.GLOBAL.httpRequestUrl + '/score/getAllByPage', params, response => {
      if (response.status == 200) {
        this.total = response.data.iTotalRecords;
        this.tableData = response.data.data;
      }
      this.pageLoading = false;
    });
  }

  //form验证规则
  const rules = {
    studentId: [{
      required: true,
      message: '请选择学生',
      trigger: 'change'
    }],
    project: [{
      required: true,
      message: '请选择项目',
      trigger: 'blur'
    }],
    unit: [{
      required: true,
      message: '请输入单位',
      trigger: 'blur'
    }],
    score: [{
      required: true,
      message: '请输入分数',
      trigger: 'blur'
    }],
    ranking: [{
      required: true,
      message: '请输入排名',
      trigger: 'blur'
    }]
  }

  export default {
    data() {
      return {
        //页码
        page: 1,
        //每页数量
        size: 10,
        total: 0,
        studentOptions:'',
        projectOptions:'',
        tableData: [],
        filtersForm: {
          studentId:'',
          project:''
        },
        formVisibleAdd:false,
        formVisibleUpdate:false,
        addScoreForm:{
          studentId: '',
          project: '',
          score: '',
          ranking:'',
          remark:'',
          unit:''
        },
        updateScoreForm:{
          id: '',
          studentId: '',
          project: '',
          score: '',
          ranking:'',
          remark:'',
          unit:''
        },
        formLoading: false,
        formLabelWidth: '100px',
        rules: rules,
        currentLoginType:''
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
      initAllStudents,
      initAllProjects,
      initDataTable
    },
    mounted: function () {
      this.initAllStudents();
      this.initAllProjects();
      this.initDataTable();
      this.currentLoginType = sessionStorage.getItem('userType');
    }
  }
</script>
