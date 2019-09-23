package com.wwhy.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.wwhy.common.util.DateUtil;
import com.wwhy.dao.BasicDataDao;
import com.wwhy.dao.ScoreDao;
import com.wwhy.dao.StudentDao;
import com.wwhy.dao.TeacherDao;
import com.wwhy.entity.BasicDataEntity;
import com.wwhy.entity.ScoreEntity;
import com.wwhy.entity.StudentEntity;
import com.wwhy.entity.TeacherEntity;
import com.wwhy.service.ScoreService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.wwhy.vo.ScoreVO;
/**
 * @author wangpan
 * create date:2019-09-05
 */
@Transactional
@Service("scoreService")
public class ScoreServiceImpl implements ScoreService {

	@Resource
	private ScoreDao scoreDao;

	@Resource
	private StudentDao studentDao;

	@Resource
	private TeacherDao teacherDao;

	@Resource
	private BasicDataDao basicDataDao;

	/**
	 * 添加
	 * @author wangpan
	 * create date:2019-09-05
	 */
	@Override
	public boolean insertScore(ScoreEntity entity){
		boolean flag=false;
		int result = scoreDao.insertSelective(entity);
		if(result > 0){
			flag = true;
		}
		return flag;
	}
	/**
	 * 修改
	 * @author wangpan
	 * create date:2019-09-05
	 */
	@Override
	public boolean updateScore(ScoreEntity entity){
		boolean flag=false;
		int result = scoreDao.updateByPrimaryKeySelective(entity);
		if(result > 0){
			flag = true;
		}
		return flag;
	}
	/**
	 * 根据ID删除
	 * @author wangpan
	 * create date:2019-09-05
	 */
	@Override
	public boolean deleteScoreById(Long id){
		boolean flag=false;
		int result = scoreDao.deleteByPrimaryKey(id);
		if(result > 0){
			flag = true;
		}
		return flag;
	}
	/**
	 * 根据ID查询
	 * @author wangpan
	 * create date:2019-09-05
	 */
	@Override
	@Transactional(readOnly=true)
	public ScoreVO getScoreById(Long id){
		ScoreVO vo=new ScoreVO();
		ScoreEntity entity = scoreDao.selectByPrimaryKey(id);
		if(entity != null){
			BeanUtils.copyProperties(entity, vo);
		}
		return vo;
	}
	/**
	 * 获取全部
	 * @author wangpan
	 * @param entityCondition
	 * create date:2019-09-05
	 */
	@Override
	@Transactional(readOnly=true)
	public List<ScoreVO> getAllScoreListByCondition(ScoreEntity entityCondition){
		List<ScoreVO> vos = new ArrayList<ScoreVO>();
		List<ScoreEntity> list = scoreDao.selectAll(entityCondition);
		if(list!=null && list.size()>0){
			for(ScoreEntity entity:list){
				ScoreVO vo=new ScoreVO();
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
	 * create date:2019-09-05
	 */
	@Override
	@Transactional(readOnly=true)
	public PageInfo<ScoreVO> getPageScoreListByCondition(Integer pageStart,Integer pageSize,final ScoreEntity entityCondition){
		PageInfo<ScoreVO> pageVo=new PageInfo<ScoreVO>();
		PageInfo<ScoreEntity> pageInfo = PageHelper.startPage(pageStart, pageSize).doSelectPageInfo(new ISelect() {
			@Override
			public void doSelect() {
				scoreDao.selectAll(entityCondition);
			}
		});

		List<ScoreVO> vos=new ArrayList<ScoreVO>();
		for(ScoreEntity entity:pageInfo.getList()){
			ScoreVO vo=new ScoreVO();
			BeanUtils.copyProperties(entity, vo);

			StudentEntity studentEntity = studentDao.selectByPrimaryKey(entity.getStudentId());
			if(studentEntity != null){
				vo.setStudentName(studentEntity.getName());
			}
			TeacherEntity teacherEntity = teacherDao.selectByPrimaryKey(entity.getTeacherId());

			if(teacherEntity != null){
				vo.setTeacherName(teacherEntity.getName());
			}

			vo.setUpdateTimeStr(DateUtil.date2String(entity.getUpdateTime(),DateUtil.yyyy_MM_dd_HH_mm_ss));
			BasicDataEntity basicDataEntity = basicDataDao.selectBasicDataByTypeCode((short)1,entity.getProject().toString());
			if(basicDataEntity != null){
				vo.setProjectStr(basicDataEntity.getBasicName());
			}
			vos.add(vo);
		}

		pageVo.setList(vos);
		pageVo.setTotal(pageInfo.getTotal());
		return pageVo;
	}
}