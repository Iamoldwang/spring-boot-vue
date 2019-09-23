package com.wwhy.common.result;

import java.io.Serializable;


import com.wwhy.common.Contant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "公用返回类")
public class CommResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 0:成功/1:失败/2:未登录
     */
    @ApiModelProperty(value = "返回状态", name = "code", notes = "0:成功/1:失败/2:未登录")
    private Integer code = Contant.RESULT_SUCCESS;
    /**
     * 返回内容
     */
    @ApiModelProperty(value = "返回内容", name = "content", notes = "返回单个值时为数据，多个值时为json格式数据")
    private Object content;
    /**
     * code为1时错误信息内容
     */
    @ApiModelProperty(value = "code为1时错误信息内容", name = "message")
    private String message = "";

    public CommResult() {
    }

    public CommResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static CommResult ok() {
        CommResult result = new CommResult();
        return result;
    }

    public static CommResult ok(Object data) {
        CommResult result = new CommResult();
        result.setContent(data);
        return result;
    }

    public static CommResult error() {
        CommResult result = new CommResult();
        result.setCode(Contant.RESULT_FAILED);
        return result;
    }

    public static CommResult error(String message) {
        CommResult result = new CommResult();
        result.setCode(Contant.RESULT_FAILED);
        result.setMessage(message);
        return result;
    }

    public static CommResult error(Integer code, String message) {
        CommResult result = new CommResult();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static CommResult error(Object data) {
        CommResult result = new CommResult();
        result.setCode(Contant.RESULT_FAILED);
        result.setContent(data);
        return result;
    }
}
