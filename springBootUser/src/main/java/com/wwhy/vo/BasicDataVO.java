package com.wwhy.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author wangpan
 * create date:2018-06-20
 */
@ApiModel(value="")
public class BasicDataVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据类型",name="type")
    private Short type;

    @ApiModelProperty(value = "类型名称",name="name")
    private String name;

    @ApiModelProperty(value = "数据编号",name="basicCode")
    private String basicCode;

    @ApiModelProperty(value = "数据名称",name="basicName")
    private String basicName;


    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBasicCode() {
        return basicCode;
    }

    public void setBasicCode(String basicCode) {
        this.basicCode = basicCode;
    }

    public String getBasicName() {
        return basicName;
    }

    public void setBasicName(String basicName) {
        this.basicName = basicName;
    }

}