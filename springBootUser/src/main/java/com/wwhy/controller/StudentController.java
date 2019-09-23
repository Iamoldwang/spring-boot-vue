package com.wwhy.controller;

import com.wwhy.common.util.DateUtil;
import com.wwhy.common.util.MD5Util;
import com.wwhy.config.CustomConfiguration;
import com.wwhy.entity.StudentEntity;
import com.wwhy.service.RedisService;
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
import com.wwhy.vo.StudentVO;
import com.wwhy.service.StudentService;

/**
 * @author wangpan
 * create date:2019-09-05
 */
@Api(value = "API - StudentController", description = "学生接口说明")
@ResponseBody
@Controller
@RequestMapping("student")
public class StudentController{

	@Resource
	private StudentService studentService;

	@Resource
	private RedisService redisService;

	/**
	 * 添加
	 * @author wangpan
	 * create date:2019-09-05
	 */
	@ApiOperation(value="添加", response=CommResult.class)
	@PostMapping(value = "insert", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CommResult insertStudent(HttpServletRequest request, StudentEntity entity){
		entity.setPassword(MD5Util.hash(entity.getPassword()));
		boolean flag = studentService.insertStudent(entity);
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
	public CommResult updateStudent(HttpServletRequest request,StudentEntity entity){
		boolean flag = studentService.updateStudent(entity);
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
	public CommResult deleteStudent(
			@ApiParam(value = "id",required = true)@RequestParam(value = "id")Long id){
		if(id == null){
			return CommResult.error("id不允许为空。");
		}

		boolean flag = studentService.deleteStudentById(id);
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
		@ApiResponse(code=200,message="成功,返回content中vo类参数如下", response=StudentVO.class)
	})
	@PostMapping(value = "getById", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CommResult getStudentById(
			@ApiParam(value = "id",required = true)@RequestParam(value = "id")Long id){
		if(id == null){
			return CommResult.error("id不允许为空。");
		}

		StudentVO vo = studentService.getStudentById(id);
		return CommResult.ok(vo);
	}
	/**
	 * 查询所有数据
	 * @author wangpan
	 * create date 2019-09-05
	 */
	@ApiOperation(value="查询所有数据", response=CommResult.class)
	@ApiResponses({
		@ApiResponse(code=200,message="成功,返回content中vo类参数如下", response=StudentVO.class)
	})
	@PostMapping(value = "getAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CommResult getAllStudent(HttpServletRequest request,StudentEntity entity){
		List<StudentVO> list = studentService.getAllStudentListByCondition(entity);
		return CommResult.ok(list);
	}
	/**
	 * 分页查询所有数据
	 * @author wangpan
	 * create date:2019-09-05
	 */
	@ApiOperation(value="分页查询所有数据", response=CommResult.class)
	@ApiResponses({
		@ApiResponse(code=200,message="成功,返回content中vo类参数如下", response=StudentVO.class)
	})
	@PostMapping(value = "getAllByPage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<StudentVO> getAllByPageStudent(HttpServletRequest request,
			@ApiParam(value = "起始页",required = true)@RequestParam(value = "page",defaultValue="1")Integer page,
			@ApiParam(value = "每页条数",required = true)@RequestParam(value = "size",defaultValue="10")Integer size,StudentEntity entity){
		HttpResult<StudentVO> result = new HttpResult<StudentVO>();
		PageInfo<StudentVO> pages = studentService.getPageStudentListByCondition(page,size,entity);
		result.setData(pages.getList());
		result.setiTotalRecords(pages.getTotal());
		return result;
	}

	/**
	 * 注册
	 * @author wangpan
	 * create date:2019-09-05
	 */
	@ApiOperation(value="注册", response=CommResult.class)
	@PostMapping(value = "register", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CommResult registerStudent(HttpServletRequest request, StudentEntity entity){
		//注册先判断姓名是否已经注册
		StudentEntity register = new StudentEntity();
		register.setName(entity.getName());
		List<StudentVO> has = studentService.getAllStudentListByCondition(register);
		if(has.size() > 0){
			return CommResult.error("注册失败，姓名已经存在！");
		}else{
			entity.setPassword(MD5Util.hash(entity.getPassword()));
			boolean flag = studentService.insertStudent(entity);
			if(flag){
				return CommResult.ok();
			}
		}
		return CommResult.error("添加失败。");
	}

	/**
	 * 学生登录
	 * @author wangpan
	 * create date:2019-09-05
	 */
	@ApiOperation(value="学生登录", response=CommResult.class)
	@PostMapping(value = "studentLogin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CommResult studentLogin(HttpServletRequest request,
								   @ApiParam(value = "学生姓名",required = true)@RequestParam(value = "name",required = true)String name,
								   @ApiParam(value = "登录密码",required = true)@RequestParam(value = "password",required = true)String password){

		password = MD5Util.hash(password);
		StudentVO student = studentService.login(name,password);
		if(student != null){


			if(student.getStatus().equals((byte)1)){
				return CommResult.error("用户未经审核无法登录！");
			}
			String tokenString = student.getName() + student.getPassword() + DateUtil.getCurrentTime();
			String token = MD5Util.hash(tokenString);
			redisService.set(token, student, 86400);
			student.setToken(token);
			return CommResult.ok(student);
		}else{
			return CommResult.error("登录失败，用户密码错误！");
		}
	}

	/**
	 * 学生退出
	 * @author wangpan
	 * create date:2019-09-05
	 */
	@ApiOperation(value="学生退出", response=CommResult.class)
	@PostMapping(value = "studentLogout", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CommResult studentLogout(HttpServletRequest request) {
		String token = request.getHeader("token");
		redisService.remove(token);
		return CommResult.ok("退出成功！");
	}

	/**
	 * 审核学生注册
	 * @author wangpan
	 * create date:2019-09-05
	 */
	@ApiOperation(value="审核学生注册", response=CommResult.class)
	@PostMapping(value = "approvalRegister", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CommResult approvalRegister(HttpServletRequest request,
								   @ApiParam(value = "学生ID",required = true)@RequestParam(value = "studentId",required = true)Long studentId){
		boolean result = studentService.approvalRegister(studentId);
		if(result){
			return CommResult.ok("审核成功!");
		}else{
			return CommResult.error("审核失败！");
		}
	}

}