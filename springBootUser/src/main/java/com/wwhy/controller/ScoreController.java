package com.wwhy.controller;

import com.wwhy.entity.ScoreEntity;
import com.wwhy.service.RedisService;
import com.wwhy.service.ScoreService;
import com.wwhy.vo.TeacherVO;
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

import com.github.pagehelper.PageInfo;
import com.wwhy.common.result.CommResult;
import com.wwhy.common.result.HttpResult;
import com.wwhy.vo.ScoreVO;

/**
 * @author wangpan
 * create date:2019-09-05
 */
@Api(value = "API - ScoreController", description = "成绩接口说明")
@ResponseBody
@Controller
@RequestMapping("score")
public class ScoreController{

	@Resource
	private ScoreService scoreService;

	@Resource
	private RedisService redisService;

	/**
	 * 添加
	 * @author wangpan
	 * create date:2019-09-05
	 */
	@ApiOperation(value="添加", response=CommResult.class)
	@PostMapping(value = "insert", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CommResult insertScore(HttpServletRequest request, ScoreEntity entity){
		TeacherVO teacher = (TeacherVO)redisService.get(request.getHeader("token"));
		entity.setUpdateTime(new Date());
		entity.setTeacherId(teacher.getId());
		boolean flag = scoreService.insertScore(entity);
		if(flag){
			return CommResult.ok();
		}
		return CommResult.error("添加失败。");
	}
	/**
	 * 修改
	 * @author wangpan
	 * create date:2019-09-05
	 */
	@ApiOperation(value="修改", response=CommResult.class)
	@PostMapping(value = "update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CommResult updateScore(HttpServletRequest request,ScoreEntity entity){
		TeacherVO teacher = (TeacherVO)redisService.get(request.getHeader("token"));
		entity.setUpdateTime(new Date());
		entity.setTeacherId(teacher.getId());
		boolean flag = scoreService.updateScore(entity);
		if(flag){
			return CommResult.ok();
		}
		return CommResult.error("修改失败。");
	}
	/**
	 * 根据id进行删除
	 * @author wangpan
	 * create date:2019-09-05
	 */
	@ApiOperation(value="根据id进行删除", response=CommResult.class)
	@PostMapping(value = "delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CommResult deleteScore(
			@ApiParam(value = "id",required = true)@RequestParam(value = "id")Long id){
		if(id == null){
			return CommResult.error("id不允许为空。");
		}

		boolean flag = scoreService.deleteScoreById(id);
		if(flag){
			return CommResult.ok();
		}
		return CommResult.error("删除失败。");
	}
	/**
	 * 根据id查询数据
	 * @author wangpan
	 * create date:2019-09-05
	 */
	@ApiOperation(value="根据id进行查询", response=CommResult.class)
	@ApiResponses({
		@ApiResponse(code=200,message="成功,返回content中vo类参数如下", response=ScoreVO.class)
	})
	@PostMapping(value = "getById", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CommResult getScoreById(
			@ApiParam(value = "id",required = true)@RequestParam(value = "id")Long id){
		if(id == null){
			return CommResult.error("id不允许为空。");
		}

		ScoreVO vo = scoreService.getScoreById(id);
		return CommResult.ok(vo);
	}
	/**
	 * 查询所有数据
	 * @author wangpan
	 * create date 2019-09-05
	 */
	@ApiOperation(value="查询所有数据", response=CommResult.class)
	@ApiResponses({
		@ApiResponse(code=200,message="成功,返回content中vo类参数如下", response=ScoreVO.class)
	})
	@PostMapping(value = "getAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CommResult getAllScore(HttpServletRequest request,ScoreEntity entity){
		List<ScoreVO> list = scoreService.getAllScoreListByCondition(entity);
		return CommResult.ok(list);
	}
	/**
	 * 分页查询所有数据
	 * @author wangpan
	 * create date:2019-09-05
	 */
	@ApiOperation(value="分页查询所有数据", response=CommResult.class)
	@ApiResponses({
		@ApiResponse(code=200,message="成功,返回content中vo类参数如下", response=ScoreVO.class)
	})
	@PostMapping(value = "getAllByPage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<ScoreVO> getAllByPageScore(HttpServletRequest request,
			@ApiParam(value = "起始页",required = true)@RequestParam(value = "page",defaultValue="1")Integer page,
			@ApiParam(value = "每页条数",required = true)@RequestParam(value = "size",defaultValue="10")Integer size,ScoreEntity entity){
		HttpResult<ScoreVO> result = new HttpResult<ScoreVO>();
		PageInfo<ScoreVO> pages = scoreService.getPageScoreListByCondition(page,size,entity);
		result.setData(pages.getList());
		result.setiTotalRecords(pages.getTotal());
		return result;
	}
}