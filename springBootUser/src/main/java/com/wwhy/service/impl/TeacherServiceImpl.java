package com.wwhy.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.wwhy.common.util.DateUtil;
import com.wwhy.common.util.StringUtil;
import com.wwhy.dao.TeacherDao;
import com.wwhy.entity.StudentEntity;
import com.wwhy.entity.TeacherEntity;
import com.wwhy.service.TeacherService;
import com.wwhy.vo.StudentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.wwhy.vo.TeacherVO;
/**
 * @author wangpan
 * create date:2019-09-05
 */
@Transactional
@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {

	@Resource
	private TeacherDao teacherDao;

	/**
	 * 添加
	 * @author wangpan
	 * create date:2019-09-05
	 */
	@Override
	public boolean insertTeacher(TeacherEntity entity){
		boolean flag=false;
		int result = teacherDao.insertSelective(entity);
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
	public boolean updateTeacher(TeacherEntity entity){
		boolean flag=false;
		int result = teacherDao.updateByPrimaryKeySelective(entity);
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
	public boolean deleteTeacherById(Long id){
		boolean flag=false;
		int result = teacherDao.deleteByPrimaryKey(id);
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
	public TeacherVO getTeacherById(Long id){
		TeacherVO vo=new TeacherVO();
		TeacherEntity entity = teacherDao.selectByPrimaryKey(id);
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
	public List<TeacherVO> getAllTeacherListByCondition(TeacherEntity entityCondition){
		List<TeacherVO> vos = new ArrayList<TeacherVO>();
		List<TeacherEntity> list = teacherDao.selectAll(entityCondition);
		if(list!=null && list.size()>0){
			for(TeacherEntity entity:list){
				TeacherVO vo=new TeacherVO();
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
	public PageInfo<TeacherVO> getPageTeacherListByCondition(Integer pageStart,Integer pageSize,final TeacherEntity entityCondition){
		PageInfo<TeacherVO> pageVo=new PageInfo<TeacherVO>();
		PageInfo<TeacherEntity> pageInfo = PageHelper.startPage(pageStart, pageSize).doSelectPageInfo(new ISelect() {
			@Override
			public void doSelect() {
				teacherDao.selectAll(entityCondition);
			}
		});

		List<TeacherVO> vos=new ArrayList<TeacherVO>();
		for(TeacherEntity entity:pageInfo.getList()){
			TeacherVO vo=new TeacherVO();
			BeanUtils.copyProperties(entity, vo);
			vo.setAge(DateUtil.getAge(DateUtil.string2Date(entity.getBirthday(),DateUtil.yyyy_MM_dd)));
			if(entity.getSex().equals((byte)1)){
				vo.setSexStr("男");
			}else{
				vo.setSexStr("女");
			}
			vos.add(vo);
		}

		pageVo.setList(vos);
		pageVo.setTotal(pageInfo.getTotal());
		return pageVo;
	}

	/**
	 * @description 系统登录
	 * @author      WangPan
	 * @return
	 * @date        2019/9/6 14:51
	 */
	public TeacherVO login(String name, String password){
		TeacherVO teacher = null;
		if(StringUtil.isNotEmpty(name) && StringUtil.isNotEmpty(password)){
			TeacherEntity entity = teacherDao.selectForLogin(name,password);
			if(entity != null){
				teacher = new TeacherVO();
				BeanUtils.copyProperties(entity, teacher);
			}
		}
		return teacher;
	}
}