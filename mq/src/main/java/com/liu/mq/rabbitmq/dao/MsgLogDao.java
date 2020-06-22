package com.liu.mq.rabbitmq.dao;

import com.liu.mq.rabbitmq.entity.MsgLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 消息投递日志(MsgLog)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-21 19:23:21
 */
@Mapper
public interface MsgLogDao {

    /**
     * 通过ID查询单条数据
     *
     * @param msgId 主键
     * @return 实例对象
     */
    MsgLog queryById(String msgId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<MsgLog> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param msgLog 实例对象
     * @return 对象列表
     */
    List<MsgLog> queryAll(MsgLog msgLog);

    /**
     * 新增数据
     *
     * @param msgLog 实例对象
     * @return 影响行数
     */
    int insert(@Param("msgLog") MsgLog msgLog);

    /**
     * 修改数据
     *
     * @param msgLog 实例对象
     * @return 影响行数
     */
    int update(@Param("msgLog") MsgLog msgLog);

    /**
     * 通过主键删除数据
     *
     * @param msgId 主键
     * @return 影响行数
     */
    int deleteById(String msgId);

    List<MsgLog> selectTimeoutMsg();
}