package com.wwhy.controller;

import io.swagger.annotations.*;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import springfox.documentation.annotations.ApiIgnore;

import com.github.pagehelper.PageInfo;
import com.wwhy.common.result.CommResult;
import com.wwhy.common.result.HttpResult;
import com.wwhy.entity.LogEntity;
import com.wwhy.vo.LogVO;
import com.wwhy.service.LogService;

/**
 * @author wangpan
 * create date:2019-09-09
 */
@Api(value = "API - LogController", description = "日志接口说明")
@ResponseBody
@Controller
@RequestMapping("log")
public class LogController{

	@Resource
	private LogService logService;

	/**
	 * 添加
	 * @author wangpan
	 * create date:2019-09-09
	 */

	@ApiOperation(value="添加", response=CommResult.class)
	@PostMapping(value = "insert", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CommResult insertLog(HttpServletRequest request,LogEntity entity){
		entity.setLogTime(new Date());
		boolean flag = logService.insertLog(entity);
		if(flag){
			return CommResult.ok();
		}
		return CommResult.error("添加失败。");
	}
	/**
	 * 修改
	 * @author wangpan
	 * create date:2019-09-09
	 */

	@ApiOperation(value="修改", response=CommResult.class)
	@PostMapping(value = "update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CommResult updateLog(HttpServletRequest request,LogEntity entity){
		boolean flag = logService.updateLog(entity);
		if(flag){
			return CommResult.ok();
		}
		return CommResult.error("修改失败。");
	}
	/**
	 * 根据id进行删除
	 * @author wangpan
	 * create date:2019-09-09
	 */

	@ApiOperation(value="根据id进行删除", response=CommResult.class)
	@PostMapping(value = "delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CommResult deleteLog(
			@ApiParam(value = "id",required = true)@RequestParam(value = "id")Long id){
		if(id == null){
			return CommResult.error("id不允许为空。");
		}

		boolean flag = logService.deleteLogById(id);
		if(flag){
			return CommResult.ok();
		}
		return CommResult.error("删除失败。");
	}
	/**
	 * 根据id查询数据
	 * @author wangpan
	 * create date:2019-09-09
	 */

	@ApiOperation(value="根据id进行查询", response=CommResult.class)
	@ApiResponses({
		@ApiResponse(code=200,message="成功,返回content中vo类参数如下", response=LogVO.class)
	})
	@PostMapping(value = "getById", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CommResult getLogById(
			@ApiParam(value = "id",required = true)@RequestParam(value = "id")Long id){
		if(id == null){
			return CommResult.error("id不允许为空。");
		}

		LogVO vo = logService.getLogById(id);
		return CommResult.ok(vo);
	}
	/**
	 * 查询所有数据
	 * @author wangpan
	 * create date 2019-09-09
	 */

	@ApiOperation(value="查询所有数据", response=CommResult.class)
	@ApiResponses({
		@ApiResponse(code=200,message="成功,返回content中vo类参数如下", response=LogVO.class)
	})
	@PostMapping(value = "getAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CommResult getAllLog(HttpServletRequest request,LogEntity entity){
		List<LogVO> list = logService.getAllLogListByCondition(entity);
		return CommResult.ok(list);
	}
	/**
	 * 分页查询所有数据
	 * @author wangpan
	 * create date:2019-09-09
	 */

	@ApiOperation(value="分页查询所有数据", response=CommResult.class)
	@ApiResponses({
		@ApiResponse(code=200,message="成功,返回content中vo类参数如下", response=LogVO.class)
	})
	@PostMapping(value = "getAllByPage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<LogVO> getAllByPageLog(HttpServletRequest request,
			@ApiParam(value = "起始页",required = true)@RequestParam(value = "page",defaultValue="1")Integer page,
			@ApiParam(value = "每页条数",required = true)@RequestParam(value = "size",defaultValue="20")Integer size,LogEntity entity){
		HttpResult<LogVO> result = new HttpResult<LogVO>();
		PageInfo<LogVO> pages = logService.getPageLogListByCondition(page,size,entity);
		result.setData(pages.getList());
		result.setiTotalRecords(pages.getTotal());
		return result;
	}
}