package com.wwhy.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
* @author wangpan
* create date:2019-09-05
*/
@ApiModel(value="")
public class ScoreVO implements Serializable{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id",name="id")
	private Long id;

	@ApiModelProperty(value = "学生id",name="studentId")
	private Long studentId;

	@ApiModelProperty(value = "学生姓名",name="studentName")
	private String studentName;

	@ApiModelProperty(value = "比赛项目（对应基础数据表type=1）",name="project")
	private Byte project;

	@ApiModelProperty(value = "比赛项目（对应基础数据表type=1）",name="projectStr")
	private String projectStr;

	@ApiModelProperty(value = "项目单位",name="unit")
	private String unit;

	@ApiModelProperty(value = "分数",name="score")
	private Double score;

	@ApiModelProperty(value = "排名",name="ranking")
	private Short ranking;

	@ApiModelProperty(value = "教师id",name="teacherId")
	private Long teacherId;

	@ApiModelProperty(value = "教师姓名",name="teacherName")
	private String teacherName;

	@ApiModelProperty(value = "更新时间",name="updateTime")
	private Date updateTime;

	@ApiModelProperty(value = "更新时间",name="updateTimeStr")
	private String updateTimeStr;

	@ApiModelProperty(value = "备注",name="remark")
	private String remark;


	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public Long getStudentId(){
		return studentId;
	}

	public void setStudentId(Long studentId){
		this.studentId = studentId;
	}

	public Byte getProject(){
		return project;
	}

	public void setProject(Byte project){
		this.project = project;
	}

	public Double getScore(){
		return score;
	}

	public void setScore(Double score){
		this.score = score;
	}

	public Short getRanking(){
		return ranking;
	}

	public void setRanking(Short ranking){
		this.ranking = ranking;
	}

	public Long getTeacherId(){
		return teacherId;
	}

	public void setTeacherId(Long teacherId){
		this.teacherId = teacherId;
	}

	public Date getUpdateTime(){
		return updateTime;
	}

	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}

	public String getRemark(){
		return remark;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getProjectStr() {
		return projectStr;
	}

	public void setProjectStr(String projectStr) {
		this.projectStr = projectStr;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
}