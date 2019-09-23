package com.wwhy.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.wwhy.common.util.DateUtil;
import com.wwhy.dao.BasicDataDao;
import com.wwhy.entity.BasicDataEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wwhy.dao.LogDao;
import com.wwhy.entity.LogEntity;
import com.wwhy.service.LogService;

import com.wwhy.vo.LogVO;
/**
 * @author wangpan
 * create date:2019-09-09
 */
@Transactional
@Service("logService")
public class LogServiceImpl implements LogService{

	@Resource
	private LogDao logDao;

	@Resource
	private BasicDataDao basicDataDao;

	/**
	 * 添加
	 * @author wangpan
	 * create date:2019-09-09
	 */
	@Override
	public boolean insertLog(LogEntity entity){
		boolean flag=false;
		int result = logDao.insertSelective(entity);
		if(result > 0){
			flag = true;
		}
		return flag;
	}
	/**
	 * 修改
	 * @author wangpan
	 * create date:2019-09-09
	 */
	@Override
	public boolean updateLog(LogEntity entity){
		boolean flag=false;
		int result = logDao.updateByPrimaryKeySelective(entity);
		if(result > 0){
			flag = true;
		}
		return flag;
	}
	/**
	 * 根据ID删除
	 * @author wangpan
	 * create date:2019-09-09
	 */
	@Override
	public boolean deleteLogById(Long id){
		boolean flag=false;
		int result = logDao.deleteByPrimaryKey(id);
		if(result > 0){
			flag = true;
		}
		return flag;
	}
	/**
	 * 根据ID查询
	 * @author wangpan
	 * create date:2019-09-09
	 */
	@Override
	@Transactional(readOnly=true)
	public LogVO getLogById(Long id){
		LogVO vo=new LogVO();
		LogEntity entity = logDao.selectByPrimaryKey(id);
		if(entity != null){
			BeanUtils.copyProperties(entity, vo);
		}
		return vo;
	}
	/**
	 * 获取全部
	 * @author wangpan
	 * @param entityCondition
	 * create date:2019-09-09
	 */
	@Override
	@Transactional(readOnly=true)
	public List<LogVO> getAllLogListByCondition(LogEntity entityCondition){
		List<LogVO> vos = new ArrayList<LogVO>();
		List<LogEntity> list = logDao.selectAll(entityCondition);
		if(list!=null && list.size()>0){
			for(LogEntity entity:list){
				LogVO vo=new LogVO();
				BeanUtils.copyProperties(entity, vo);
				vos.add(vo);
			}
		}
		return vos;
	}
	/**
	 * 获取分页数据
	 * @author wangpan
	 * @param pageStart 开始页数
	 * @param pageSize 每页条数
	 * @param entityCondition
	 * create date:2019-09-09
	 */
	@Override
	@Transactional(readOnly=true)
	public PageInfo<LogVO> getPageLogListByCondition(Integer pageStart,Integer pageSize,final LogEntity entityCondition){
		PageInfo<LogVO> pageVo=new PageInfo<LogVO>();
		PageInfo<LogEntity> pageInfo = PageHelper.startPage(pageStart, pageSize).doSelectPageInfo(new ISelect() {
			@Override
			public void doSelect() {
				logDao.selectAll(entityCondition);
			}
		});

		List<LogVO> vos=new ArrayList<LogVO>();
		for(LogEntity entity:pageInfo.getList()){
			LogVO vo=new LogVO();
			BeanUtils.copyProperties(entity, vo);

			vo.setLogTimeStr(DateUtil.date2String(entity.getLogTime(),DateUtil.yyyy_MM_dd_HH_mm_ss));
			BasicDataEntity basicDataEntity = basicDataDao.selectBasicDataByTypeCode((short)4,entity.getType().toString());
			if(basicDataEntity != null){
				vo.setTypeStr(basicDataEntity.getBasicName());
			}
			vos.add(vo);
		}

		pageVo.setList(vos);
		pageVo.setTotal(pageInfo.getTotal());
		return pageVo;
	}
}