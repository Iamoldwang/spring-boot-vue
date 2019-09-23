package com.wwhy.dao;

import java.util.List;

import com.wwhy.entity.StudentEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author wangpan
 * create date:2019-09-05
 */
@Repository
public interface StudentDao{

	/**
	 * 添加所有
	 * @author wangpan
	 * create date:2019-09-05
	 */
	public int insert(StudentEntity entity);

	/**
	 * 添加非空字段
	 * @author wangpan
	 * create date:2019-09-05
	 */
	public int insertSelective(StudentEntity entity);

	/**
	 * 根据ID修改所有
	 * @author wangpan
	 * create date:2019-09-05
	 */
	public int updateByPrimaryKey(StudentEntity entity);

	/**
	 * 根据ID修改非空字段
	 * @author wangpan
	 * create date:2019-09-05
	 */
	public int updateByPrimaryKeySelective(StudentEntity entity);

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
	public StudentEntity selectByPrimaryKey(Long id);

	/**
	 * 查询所有数据
	 * @author wangpan
	 * create date:2019-09-05
	 */
	public List<StudentEntity> selectAll(StudentEntity entity);

	/**
	 * @description 系统登录
	 * @author      WangPan
	 * @return
	 * @date        2019/9/6 14:55
	 */
	public StudentEntity selectForLogin(@Param("name")String name,@Param("password")String password);

}