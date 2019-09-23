package com.wwhy.service.impl;


import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wwhy.dao.BasicDataDao;
import com.wwhy.entity.BasicDataEntity;
import com.wwhy.service.BasicDataService;
import com.wwhy.vo.BasicDataVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangpan
 * create date:2018-06-20
 */
@Transactional
@Service("basicDataService")
public class BasicDataServiceImpl implements BasicDataService {

    @Resource
    private BasicDataDao basicDataDao;

    /**
     * 根据type获取基本数据类型
     *
     * @param type
     * @return
     * @author wangpan
     * create date:2017年10月26日
     */
    @Transactional(readOnly = true)
    public List<BasicDataVO> getBasicDataByType(Short type) {
        List<BasicDataVO> vos = new ArrayList<BasicDataVO>();
        List<BasicDataEntity> entityList = basicDataDao.selectByType(type);
        if(entityList != null && entityList.size() > 0){
            for(BasicDataEntity entity : entityList){
                BasicDataVO vo = new BasicDataVO();
                BeanUtils.copyProperties(entity, vo);
                vos.add(vo);
            }
        }
        return vos;
    }

    /**
     * 添加
     * @author wangpan
     * create date:2019-09-11
     */
    @Override
    public boolean insertBasicData(BasicDataEntity entity){
        boolean flag=false;
        int result = basicDataDao.insertSelective(entity);
        if(result > 0){
            flag = true;
        }
        return flag;
    }
    /**
     * 修改
     * @author wangpan
     * create date:2019-09-11
     */
    @Override
    public boolean updateBasicData(BasicDataEntity entity){
        boolean flag=false;
        int result = basicDataDao.updateByPrimaryKeySelective(entity);
        if(result > 0){
            flag = true;
        }
        return flag;
    }
    /**
     * 根据type 和 basicCode删除
     * @author wangpan
     * create date:2019-09-11
     */
    @Override
    public boolean deleteBasicDataByTypeAndBasicCode(Short type,String basicCode){
        boolean flag=false;
        int result = basicDataDao.deleteByTypeAndBasicCode(type,basicCode);
        if(result > 0){
            flag = true;
        }
        return flag;
    }

    /**
     * 获取全部
     * @author wangpan
     * @param entityCondition
     * create date:2019-09-11
     */
    @Override
    @Transactional(readOnly=true)
    public List<BasicDataVO> getAllBasicDataListByCondition(BasicDataEntity entityCondition){
        List<BasicDataVO> vos = new ArrayList<BasicDataVO>();
        List<BasicDataEntity> list = basicDataDao.selectAll(entityCondition);
        if(list!=null && list.size()>0){
            for(BasicDataEntity entity:list){
                BasicDataVO vo=new BasicDataVO();
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
     * create date:2019-09-11
     */
    @Override
    @Transactional(readOnly=true)
    public PageInfo<BasicDataVO> getPageBasicDataListByCondition(Integer pageStart, Integer pageSize, final BasicDataEntity entityCondition){
        PageInfo<BasicDataVO> pageVo=new PageInfo<BasicDataVO>();
        PageInfo<BasicDataEntity> pageInfo = PageHelper.startPage(pageStart, pageSize).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                basicDataDao.selectAll(entityCondition);
            }
        });

        List<BasicDataVO> vos=new ArrayList<BasicDataVO>();
        for(BasicDataEntity entity:pageInfo.getList()){
            BasicDataVO vo=new BasicDataVO();
            BeanUtils.copyProperties(entity, vo);
            vos.add(vo);
        }

        pageVo.setList(vos);
        pageVo.setTotal(pageInfo.getTotal());
        return pageVo;
    }

    /**
     * @description 获取所有类型名称，去重
     * @author      WangPan
     * @return
     * @date        2019/9/11 14:51
     */
    public List<BasicDataVO> getAllNames(){
        List<BasicDataVO> vos=new ArrayList<BasicDataVO>();
        List<BasicDataEntity> names = basicDataDao.getAllNames();
        for(BasicDataEntity entity : names){
            BasicDataVO vo=new BasicDataVO();
            BeanUtils.copyProperties(entity, vo);
            vos.add(vo);
        }
        return vos;
    }
}