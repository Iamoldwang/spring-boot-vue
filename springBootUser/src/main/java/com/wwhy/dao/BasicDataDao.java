package com.wwhy.dao;

import com.wwhy.entity.BasicDataEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangpan
 * create date:2018-06-20
 */
@Repository
public interface BasicDataDao {
    /**
     * 根据type获取基本数据类型
     *
     * @param type
     * @return
     * @author wangpan
     * create date:2017年10月26日
     */
    List<BasicDataEntity> selectByType(Short type);

    /**
     * @param type
     * @param basicCode
     * @return
     * @description:根据type和basicCode获取基本数据类型
     * @time:2018年9月5日 上午10:02:10
     * @author:WangPan
     */
    BasicDataEntity selectBasicDataByTypeCode(@Param("type") Short type, @Param("basicCode") String basicCode);

    /**
     * 添加所有
     * @author wangpan
     * create date:2019-09-11
     */
    public int insert(BasicDataEntity entity);

    /**
     * 添加非空字段
     * @author wangpan
     * create date:2019-09-11
     */
    public int insertSelective(BasicDataEntity entity);

    /**
     * 根据ID修改所有
     * @author wangpan
     * create date:2019-09-11
     */
    public int updateByPrimaryKey(BasicDataEntity entity);

    /**
     * 根据ID修改非空字段
     * @author wangpan
     * create date:2019-09-11
     */
    public int updateByPrimaryKeySelective(BasicDataEntity entity);

    /**
     * 根据type 和 basicCode删除
     * @author wangpan
     * create date:2019-09-11
     */
    public int deleteByTypeAndBasicCode(@Param("type") Short type, @Param("basicCode") String basicCode);

    /**
     * 查询所有数据
     * @author wangpan
     * create date:2019-09-11
     */
    public List<BasicDataEntity> selectAll(BasicDataEntity entity);

    /**
     * @description 获取所有类型名称，去重
     * @author      WangPan
     * @return
     * @date        2019/9/11 14:51
     */
    public List<BasicDataEntity> getAllNames();

}