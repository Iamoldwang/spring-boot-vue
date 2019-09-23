package com.wwhy.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.wwhy.common.util.DateUtil;
import com.wwhy.common.util.StringUtil;
import com.wwhy.dao.StudentDao;
import com.wwhy.entity.StudentEntity;
import com.wwhy.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.wwhy.vo.StudentVO;
/**
 * @author wangpan
 * create date:2019-09-05
 */
@Transactional
@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Resource
	private StudentDao studentDao;

	/**
	 * 添加
	 * @author wangpan
	 * create date:2019-09-05
	 */
	@Override
	public boolean insertStudent(StudentEntity entity){
		boolean flag=false;
		int result = studentDao.insertSelective(entity);
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
	public boolean updateStudent(StudentEntity entity){
		boolean flag=false;
		int result = studentDao.updateByPrimaryKeySelective(entity);
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
	public boolean deleteStudentById(Long id){
		boolean flag=false;
		int result = studentDao.deleteByPrimaryKey(id);
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
	public StudentVO getStudentById(Long id){
		StudentVO vo=new StudentVO();
		StudentEntity entity = studentDao.selectByPrimaryKey(id);
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
	public List<StudentVO> getAllStudentListByCondition(StudentEntity entityCondition){
		List<StudentVO> vos = new ArrayList<StudentVO>();
		List<StudentEntity> list = studentDao.selectAll(entityCondition);
		if(list!=null && list.size()>0){
			for(StudentEntity entity:list){
				StudentVO vo=new StudentVO();
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
	public PageInfo<StudentVO> getPageStudentListByCondition(Integer pageStart,Integer pageSize,final StudentEntity entityCondition){
		PageInfo<StudentVO> pageVo=new PageInfo<StudentVO>();
		PageInfo<StudentEntity> pageInfo = PageHelper.startPage(pageStart, pageSize).doSelectPageInfo(new ISelect() {
			@Override
			public void doSelect() {
				studentDao.selectAll(entityCondition);
			}
		});

		List<StudentVO> vos=new ArrayList<StudentVO>();
		for(StudentEntity entity:pageInfo.getList()){
			StudentVO vo=new StudentVO();
			BeanUtils.copyProperties(entity, vo);
			if(entity.getBirthday() != null){
				vo.setAge(DateUtil.getAge(DateUtil.string2Date(entity.getBirthday(),DateUtil.yyyy_MM_dd)));
			}
			if(entity.getSex() != null){
				if(entity.getSex().equals((byte)1)){
					vo.setSexStr("男");
				}else{
					vo.setSexStr("女");
				}
			}
			if(entity.getStudentType() != null){
				switch (entity.getStudentType()){
					case 1:
						vo.setStudentTypeStr("专科");
						break;
					case 2:
						vo.setStudentTypeStr("本科");
						break;
					case 3:
						vo.setStudentTypeStr("研究生");
						break;
					case 4:
						vo.setStudentTypeStr("留学生");
						break;
					case 5:
						vo.setStudentTypeStr("交换生");
						break;
					case 6:
						vo.setStudentTypeStr("国防生");
						break;
				}
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
	public StudentVO login(String name,String password){
		StudentVO student = null;
		if(StringUtil.isNotEmpty(name) && StringUtil.isNotEmpty(password)){
			StudentEntity entity = studentDao.selectForLogin(name,password);
			if(entity != null){
				student = new StudentVO();
				BeanUtils.copyProperties(entity, student);
			}
		}
		return student;
	}

	/**
	 * @description 审批学生注册
	 * @author      WangPan
	 * @return
	 * @date        2019/9/10 15:41
	 */
	public boolean approvalRegister(Long studentId){
		boolean flag = false;
		if(studentId > 0){
			StudentEntity student = new StudentEntity();
			student.setId(studentId);
			student.setStatus((byte)2);
			int result = studentDao.updateByPrimaryKeySelective(student);
			if(result > 0){
				flag = true;
			}
		}
		return  flag;
	}
}