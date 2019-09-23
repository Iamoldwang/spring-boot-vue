package com.wwhy.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wwhy.entity.StudentEntity;
import com.wwhy.vo.StudentVO;

/**
 * @author wangpan
 * create date:2019-09-05
 */
public interface StudentService{
	/**
	 * 添加
	 * @author wangpan
	 * create date:2019-09-05
	 */
	public boolean insertStudent(StudentEntity entity);
	/**
	 * 修改
	 * @author wangpan
	 * create date:2019-09-05
	 */
	public boolean updateStudent(StudentEntity entity);
	/**
	 * 根据ID删除
	 * @author wangpan
	 * create date:2019-09-05
	 */
	public boolean deleteStudentById(Long id);
	/**
	 * 根据ID查询
	 * @author wangpan
	 * create date:2019-09-05
	 */
	public StudentVO getStudentById(Long id);
	/**
	 * 按条件获取全部
	 * @author wangpan
	 * create date:2019-09-05
	 */
	public List<StudentVO> getAllStudentListByCondition(StudentEntity entityCondition);
	/**
	 * 按条件分页查询
	 * @author wangpan
	 * create date:2019-09-05
	 */
	public PageInfo<StudentVO> getPageStudentListByCondition(Integer pageStart, Integer pageSize, StudentEntity entityCondition);

	/**
	 * @description 系统登录
	 * @author      WangPan
	 * @return
	 * @date        2019/9/6 14:51
	 */
	public StudentVO login(String name,String password);

	/**
	 * @description 审批学生注册
	 * @author      WangPan
	 * @return
	 * @date        2019/9/10 15:41
	 */
	public boolean approvalRegister(Long studentId);
}