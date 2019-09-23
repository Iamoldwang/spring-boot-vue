package com.wwhy.service;

import com.github.pagehelper.PageInfo;
import com.wwhy.entity.BasicDataEntity;
import com.wwhy.vo.BasicDataVO;

import java.util.List;

/**
 * @author wangpan
 * create date:2018-06-20
 */
public interface BasicDataService {

    /**
     * 根据type获取基本数据类型
     *
     * @param type
     * @return
     * @author wangpan
     * create date:2017年10月26日
     */
    public List<BasicDataVO> getBasicDataByType(Short type);

    /**
     * 添加
     * @author wangpan
     * create date:2019-09-11
     */
    public boolean insertBasicData(BasicDataEntity entity);
    /**
     * 修改
     * @author wangpan
     * create date:2019-09-11
     */
    public boolean updateBasicData(BasicDataEntity entity);
    /**
     * 根据type 和 basicCode删除
     * @author wangpan
     * create date:2019-09-11
     */
    public boolean deleteBasicDataByTypeAndBasicCode(Short type,String basicCode);

    /**
     * 按条件获取全部
     * @author wangpan
     * create date:2019-09-11
     */
    public List<BasicDataVO> getAllBasicDataListByCondition(BasicDataEntity entityCondition);
    /**
     * 按条件分页查询
     * @author wangpan
     * create date:2019-09-11
     */
    public PageInfo<BasicDataVO> getPageBasicDataListByCondition(Integer pageStart, Integer pageSize, BasicDataEntity entityCondition);

    /**
     * @description 获取所有类型名称，去重
     * @author      WangPan
     * @return
     * @date        2019/9/11 14:51
     */
    public List<BasicDataVO> getAllNames();
}