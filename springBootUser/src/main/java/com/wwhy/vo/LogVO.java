package com.wwhy.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
* @author wangpan
* create date:2019-09-09
*/
@ApiModel(value="")
public class LogVO implements Serializable{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id",name="id")
	private Long id;

	@ApiModelProperty(value = "日志内容",name="content")
	private String content;

	@ApiModelProperty(value = "日志类型（1：学生；2：教师；3：超级管理员）",name="type")
	private Byte type;

	@ApiModelProperty(value = "日志类型（1：学生；2：教师；3：超级管理员）",name="typeStr")
	private String typeStr;

	@ApiModelProperty(value = "对应的人员ID",name="personId")
	private Long personId;

	@ApiModelProperty(value = "对应的人员姓名",name="personName")
	private String personName;

	@ApiModelProperty(value = "日志时间",name="logTime")
	private Date logTime;

	@ApiModelProperty(value = "日志时间",name="logTimeStr")
	private String logTimeStr;

	@ApiModelProperty(value = "备注",name="remark")
	private String remark;


	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getContent(){
		return content;
	}

	public void setContent(String content){
		this.content = content;
	}

	public Byte getType(){
		return type;
	}

	public void setType(Byte type){
		this.type = type;
	}

	public Long getPersonId(){
		return personId;
	}

	public void setPersonId(Long personId){
		this.personId = personId;
	}

	public String getPersonName(){
		return personName;
	}

	public void setPersonName(String personName){
		this.personName = personName;
	}

	public Date getLogTime(){
		return logTime;
	}

	public void setLogTime(Date logTime){
		this.logTime = logTime;
	}

	public String getRemark(){
		return remark;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getTypeStr() {
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}

	public String getLogTimeStr() {
		return logTimeStr;
	}

	public void setLogTimeStr(String logTimeStr) {
		this.logTimeStr = logTimeStr;
	}
}