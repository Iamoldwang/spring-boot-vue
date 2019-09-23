package com.wwhy.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wwhy.entity.ScoreEntity;
import com.wwhy.vo.ScoreVO;

/**
 * @author wangpan
 * create date:2019-09-05
 */
public interface ScoreService{
	/**
	 * 添加
	 * @author wangpan
	 * create date:2019-09-05
	 */
	public boolean insertScore(ScoreEntity entity);
	/**
	 * 修改
	 * @author wangpan
	 * create date:2019-09-05
	 */
	public boolean updateScore(ScoreEntity entity);
	/**
	 * 根据ID删除
	 * @author wangpan
	 * create date:2019-09-05
	 */
	public boolean deleteScoreById(Long id);
	/**
	 * 根据ID查询
	 * @author wangpan
	 * create date:2019-09-05
	 */
	public ScoreVO getScoreById(Long id);
	/**
	 * 按条件获取全部
	 * @author wangpan
	 * create date:2019-09-05
	 */
	public List<ScoreVO> getAllScoreListByCondition(ScoreEntity entityCondition);
	/**
	 * 按条件分页查询
	 * @author wangpan
	 * create date:2019-09-05
	 */
	public PageInfo<ScoreVO> getPageScoreListByCondition(Integer pageStart, Integer pageSize, ScoreEntity entityCondition);
}