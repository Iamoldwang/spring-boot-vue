package com.wwhy.dao;

import java.util.List;

import com.wwhy.entity.TeacherEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author wangpan
 * create date:2019-09-05
 */
@Repository
public interface TeacherDao{

	/**
	 * 添加所有
	 * @author wangpan
	 * create date:2019-09-05
	 */
	public int insert(TeacherEntity entity);

	/**
	 * 添加非空字段
	 * @author wangpan
	 * create date:2019-09-05
	 */
	public int insertSelective(TeacherEntity entity);

	/**
	 * 根据ID修改所有
	 * @author wangpan
	 * create date:2019-09-05
	 */
	public int updateByPrimaryKey(TeacherEntity entity);

	/**
	 * 根据ID修改非空字段
	 * @author wangpan
	 * create date:2019-09-05
	 */
	public int updateByPrimaryKeySelective(TeacherEntity entity);

	/**
	 * 根据ID删除数据
	 * @author wangpan
	 * create date:2019-09-05
	 */
	public int deleteByPrimaryKey(Long id);

	/**
	 * 根据ID查询数据
	 * @author wangpan
	 * create date:2019-09-05
	 */
	public TeacherEntity selectByPrimaryKey(Long id);

	/**
	 * 查询所有数据
	 * @author wangpan
	 * create date:2019-09-05
	 */
	public List<TeacherEntity> selectAll(TeacherEntity entity);

	/**
	 * @description 系统登录
	 * @author      WangPan
	 * @return
	 * @date        2019/9/6 14:55
	 */
	public TeacherEntity selectForLogin(@Param("name")String name, @Param("password")String password);
}