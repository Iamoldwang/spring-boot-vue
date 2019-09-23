package com.wwhy.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wwhy.entity.LogEntity;

/**
 * @author wangpan
 * create date:2019-09-09
 */
@Repository
public interface LogDao{

	/**
	 * 添加所有
	 * @author wangpan
	 * create date:2019-09-09
	 */
	public int insert(LogEntity entity);

	/**
	 * 添加非空字段
	 * @author wangpan
	 * create date:2019-09-09
	 */
	public int insertSelective(LogEntity entity);

	/**
	 * 根据ID修改所有
	 * @author wangpan
	 * create date:2019-09-09
	 */
	public int updateByPrimaryKey(LogEntity entity);

	/**
	 * 根据ID修改非空字段
	 * @author wangpan
	 * create date:2019-09-09
	 */
	public int updateByPrimaryKeySelective(LogEntity entity);

	/**
	 * 根据ID删除数据
	 * @author wangpan
	 * create date:2019-09-09
	 */
	public int deleteByPrimaryKey(Long id);

	/**
	 * 根据ID查询数据
	 * @author wangpan
	 * create date:2019-09-09
	 */
	public LogEntity selectByPrimaryKey(Long id);

	/**
	 * 查询所有数据
	 * @author wangpan
	 * create date:2019-09-09
	 */
	public List<LogEntity> selectAll(LogEntity entity);

}