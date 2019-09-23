package com.wwhy.controller;

import com.github.pagehelper.PageInfo;
import com.wwhy.common.result.CommResult;
import com.wwhy.common.result.HttpResult;
import com.wwhy.entity.BasicDataEntity;
import com.wwhy.service.BasicDataService;
import com.wwhy.vo.BasicDataVO;
import com.wwhy.vo.LogVO;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author wangpan
 * create date:2018-06-20
 */
@Api(value = "API - BasicDataController", description = "基础数据接口说明")
@ResponseBody
@Controller
@RequestMapping("basicData")
public class BasicDataController {

    @Resource
    private BasicDataService basicDataService;

    @ApiOperation(value="获取基础数据", response= CommResult.class)
    @ApiResponses({
            @ApiResponse(code=200,message="成功,返回content中vo类参数如下", response= BasicDataVO.class)
    })
    @PostMapping(value = "/getBasicDataByType", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpResult<BasicDataVO> getBasicDataByType(HttpServletRequest request,
            @ApiParam(value = "数据类型",required = true)@RequestParam("type") String type) {
        HttpResult<BasicDataVO> result = new HttpResult<BasicDataVO>();
        List<BasicDataVO> list = null;
        if (type != null) {
            list = basicDataService.getBasicDataByType(Short.valueOf(type));
        }

        result.setData(list);
        return result;
    }

    /**
     * 添加
     * @author wangpan
     * create date:2019-09-11
     */
    @ApiOperation(value="添加", response=CommResult.class)
    @PostMapping(value = "insert", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommResult insertBasicData(HttpServletRequest request,BasicDataEntity entity){
        boolean flag = basicDataService.insertBasicData(entity);
        if(flag){
            return CommResult.ok();
        }
        return CommResult.error("添加失败。");
    }
    /**
     * 修改
     * @author wangpan
     * create date:2019-09-11
     */
    @ApiOperation(value="修改", response=CommResult.class)
    @PostMapping(value = "update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommResult updateBasicData(HttpServletRequest request,BasicDataEntity entity){
        boolean flag = basicDataService.updateBasicData(entity);
        if(flag){
            return CommResult.ok();
        }
        return CommResult.error("修改失败。");
    }
    /**
     * 根据id进行删除
     * @author wangpan
     * create date:2019-09-11
     */
    @ApiOperation(value="根据type 和 basicCode删除", response=CommResult.class)
    @PostMapping(value = "delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommResult deleteBasicData(
            @ApiParam(value = "type",required = true)@RequestParam(value = "type")Short type,
            @ApiParam(value = "basicCode",required = true)@RequestParam(value = "basicCode")String basicCode){
        if(type == null || basicCode == null){
            return CommResult.error("type不允许为空。");
        }

        boolean flag = basicDataService.deleteBasicDataByTypeAndBasicCode(type,basicCode);
        if(flag){
            return CommResult.ok();
        }
        return CommResult.error("删除失败。");
    }

    /**
     * 查询所有数据
     * @author wangpan
     * create date 2019-09-11
     */
    @ApiOperation(value="查询所有数据", response=CommResult.class)
    @ApiResponses({
            @ApiResponse(code=200,message="成功,返回content中vo类参数如下", response=BasicDataVO.class)
    })
    @PostMapping(value = "getAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommResult getAllBasicData(HttpServletRequest request,BasicDataEntity entity){
        List<BasicDataVO> list = basicDataService.getAllBasicDataListByCondition(entity);
        return CommResult.ok(list);
    }

    /**
     * 分页查询所有数据
     * @author wangpan
     * create date:2019-09-11
     */
    @ApiOperation(value="分页查询所有数据", response=CommResult.class)
    @ApiResponses({
            @ApiResponse(code=200,message="成功,返回content中vo类参数如下", response=BasicDataVO.class)
    })
    @PostMapping(value = "getAllByPage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpResult<BasicDataVO> getAllByPageBasicData(HttpServletRequest request,
                                                         @ApiParam(value = "起始页",required = true)@RequestParam(value = "page",defaultValue="1")Integer page,
                                                         @ApiParam(value = "每页条数",required = true)@RequestParam(value = "size",defaultValue="10")Integer size,BasicDataEntity entity){
        HttpResult<BasicDataVO> result = new HttpResult<BasicDataVO>();
        PageInfo<BasicDataVO> pages = basicDataService.getPageBasicDataListByCondition(page,size,entity);
        result.setData(pages.getList());
        result.setiTotalRecords(pages.getTotal());
        return result;
    }

    /**
     * 查询所有数据
     * @author wangpan
     * create date 2019-09-11
     */
    @ApiOperation(value="查询所有类型", response=CommResult.class)
    @ApiResponses({
            @ApiResponse(code=200,message="成功,返回content中vo类参数如下", response=BasicDataVO.class)
    })
    @PostMapping(value = "getAllNames", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommResult getAllNames(HttpServletRequest request){
        List<BasicDataVO> list = basicDataService.getAllNames();
        return CommResult.ok(list);
    }
}



