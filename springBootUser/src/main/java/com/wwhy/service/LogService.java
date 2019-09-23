package com.wwhy.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wwhy.entity.LogEntity;
import com.wwhy.vo.LogVO;

/**
 * @author wangpan
 * create date:2019-09-09
 */
public interface LogService{
	/**
	 * 添加
	 * @author wangpan
	 * create date:2019-09-09
	 */
	public boolean insertLog(LogEntity entity);
	/**
	 * 修改
	 * @author wangpan
	 * create date:2019-09-09
	 */
	public boolean updateLog(LogEntity entity);
	/**
	 * 根据ID删除
	 * @author wangpan
	 * create date:2019-09-09
	 */
	public boolean deleteLogById(Long id);
	/**
	 * 根据ID查询
	 * @author wangpan
	 * create date:2019-09-09
	 */
	public LogVO getLogById(Long id);
	/**
	 * 按条件获取全部
	 * @author wangpan
	 * create date:2019-09-09
	 */
	public List<LogVO> getAllLogListByCondition(LogEntity entityCondition);
	/**
	 * 按条件分页查询
	 * @author wangpan
	 * create date:2019-09-09
	 */
	public PageInfo<LogVO> getPageLogListByCondition(Integer pageStart, Integer pageSize, LogEntity entityCondition);
}