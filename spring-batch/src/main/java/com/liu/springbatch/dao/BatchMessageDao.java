package com.liu.springbatch.dao;

import com.liu.springbatch.entity.BatchMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (BatchMessage)表数据库访问层
 *
 * @author liu
 * @since 2020-06-24 11:18:01
 */
@Mapper
public interface BatchMessageDao {

    /**
     * 通过ID查询单条数据
     *
     * @param objectId 主键
     * @return 实例对象
     */
    BatchMessage queryById(Integer objectId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<BatchMessage> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param batchMessage 实例对象
     * @return 对象列表
     */
    List<BatchMessage> queryAll(BatchMessage batchMessage);

    /**
     * 新增数据
     *
     * @param batchMessage 实例对象
     * @return 影响行数
     */
    int insert(BatchMessage batchMessage);

    /**
     * 修改数据
     *
     * @param batchMessage 实例对象
     * @return 影响行数
     */
    int update(BatchMessage batchMessage);

    /**
     * 通过主键删除数据
     *
     * @param objectId 主键
     * @return 影响行数
     */
    int deleteById(String objectId);

}