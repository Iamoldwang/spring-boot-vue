package com.wwhy.controller;

import com.wwhy.common.util.DateUtil;
import com.wwhy.common.util.MD5Util;
import com.wwhy.entity.StudentEntity;
import com.wwhy.entity.TeacherEntity;
import com.wwhy.service.RedisService;
import com.wwhy.service.TeacherService;
import com.wwhy.vo.StudentVO;
import io.swagger.annotations.*;

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
import com.wwhy.vo.TeacherVO;

/**
 * @author wangpan
 * create date:2019-09-05
 */
@Api(value = "API - TeacherController", description = "教师接口说明")
@ResponseBody
@Controller
@RequestMapping("teacher")
public class TeacherController{

	@Resource
	private TeacherService teacherService;

	@Resource
	private RedisService redisService;

	/**
	 * 添加
	 * @author wangpan
	 * create date:2019-09-05
	 */
	@ApiOperation(value="添加", response=CommResult.class)
	@PostMapping(value = "insert", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CommResult insertTeacher(HttpServletRequest request, TeacherEntity entity){
		entity.setPassword(MD5Util.hash(entity.getPassword()));
		boolean flag = teacherService.insertTeacher(entity);
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
	public CommResult updateTeacher(HttpServletRequest request,TeacherEntity entity){
		boolean flag = teacherService.updateTeacher(entity);
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
	public CommResult deleteTeacher(
			@ApiParam(value = "id",required = true)@RequestParam(value = "id")Long id){
		if(id == null){
			return CommResult.error("id不允许为空。");
		}

		boolean flag = teacherService.deleteTeacherById(id);
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
		@ApiResponse(code=200,message="成功,返回content中vo类参数如下", response=TeacherVO.class)
	})
	@PostMapping(value = "getById", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CommResult getTeacherById(
			@ApiParam(value = "id",required = true)@RequestParam(value = "id")Long id){
		if(id == null){
			return CommResult.error("id不允许为空。");
		}

		TeacherVO vo = teacherService.getTeacherById(id);
		return CommResult.ok(vo);
	}
	/**
	 * 查询所有数据
	 * @author wangpan
	 * create date 2019-09-05
	 */
	@ApiOperation(value="查询所有数据", response=CommResult.class)
	@ApiResponses({
		@ApiResponse(code=200,message="成功,返回content中vo类参数如下", response=TeacherVO.class)
	})
	@PostMapping(value = "getAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CommResult getAllTeacher(HttpServletRequest request,TeacherEntity entity){
		List<TeacherVO> list = teacherService.getAllTeacherListByCondition(entity);
		return CommResult.ok(list);
	}
	/**
	 * 分页查询所有数据
	 * @author wangpan
	 * create date:2019-09-05
	 */
	@ApiOperation(value="分页查询所有数据", response=CommResult.class)
	@ApiResponses({
		@ApiResponse(code=200,message="成功,返回content中vo类参数如下", response=TeacherVO.class)
	})
	@PostMapping(value = "getAllByPage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<TeacherVO> getAllByPageTeacher(HttpServletRequest request,
			@ApiParam(value = "起始页",required = true)@RequestParam(value = "page",defaultValue="1")Integer page,
			@ApiParam(value = "每页条数",required = true)@RequestParam(value = "size",defaultValue="10")Integer size,TeacherEntity entity){
		HttpResult<TeacherVO> result = new HttpResult<TeacherVO>();
		PageInfo<TeacherVO> pages = teacherService.getPageTeacherListByCondition(page,size,entity);
		result.setData(pages.getList());
		result.setiTotalRecords(pages.getTotal());
		return result;
	}

	/**
	 * 教师登录
	 * @author wangpan
	 * create date:2019-09-05
	 */
	@ApiOperation(value="教师登录", response=CommResult.class)
	@PostMapping(value = "teacherLogin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CommResult teacherLogin(HttpServletRequest request,
								   @ApiParam(value = "教师姓名",required = true)@RequestParam(value = "name",required = true)String name,
								   @ApiParam(value = "登录密码",required = true)@RequestParam(value = "password",required = true)String password){
		password = MD5Util.hash(password);
		TeacherVO teacher = teacherService.login(name,password);
		if(teacher != null){
			String tokenString = teacher.getName() + teacher.getPassword() + DateUtil.getCurrentTime();
			String token = MD5Util.hash(tokenString);
			redisService.set(token, teacher, 86400);
			teacher.setToken(token);
			return CommResult.ok(teacher);
		}else{
			return CommResult.error("登录失败，用户密码错误！");
		}
	}

	/**
	 * 教师退出
	 * @author wangpan
	 * create date:2019-09-05
	 */
	@ApiOperation(value="教师退出", response=CommResult.class)
	@PostMapping(value = "teacherLogout", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CommResult teacherLogout(HttpServletRequest request) {
		String token = request.getHeader("token");
		redisService.remove(token);
		return CommResult.ok("退出成功！");
	}
}