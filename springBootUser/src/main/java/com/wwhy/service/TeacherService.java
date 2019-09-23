package com.wwhy.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wwhy.entity.TeacherEntity;
import com.wwhy.vo.StudentVO;
import com.wwhy.vo.TeacherVO;

/**
 * @author wangpan
 * create date:2019-09-05
 */
public interface TeacherService{
	/**
	 * 添加
	 * @author wangpan
	 * create date:2019-09-05
	 */
	public boolean insertTeacher(TeacherEntity entity);
	/**
	 * 修改
	 * @author wangpan
	 * create date:2019-09-05
	 */
	public boolean updateTeacher(TeacherEntity entity);
	/**
	 * 根据ID删除
	 * @author wangpan
	 * create date:2019-09-05
	 */
	public boolean deleteTeacherById(Long id);
	/**
	 * 根据ID查询
	 * @author wangpan
	 * create date:2019-09-05
	 */
	public TeacherVO getTeacherById(Long id);
	/**
	 * 按条件获取全部
	 * @author wangpan
	 * create date:2019-09-05
	 */
	public List<TeacherVO> getAllTeacherListByCondition(TeacherEntity entityCondition);
	/**
	 * 按条件分页查询
	 * @author wangpan
	 * create date:2019-09-05
	 */
	public PageInfo<TeacherVO> getPageTeacherListByCondition(Integer pageStart, Integer pageSize, TeacherEntity entityCondition);

	/**
	 * @description 系统登录
	 * @author      WangPan
	 * @return
	 * @date        2019/9/6 14:51
	 */
	public TeacherVO login(String name, String password);
}