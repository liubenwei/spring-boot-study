package com.liu.mq.rabbitmq.service;

import com.liu.mq.rabbitmq.entity.MsgLog;
import java.util.List;

/**
 * 消息投递日志(MsgLog)表服务接口
 *
 * @author makejava
 * @since 2020-06-21 19:23:22
 */
public interface MsgLogService {

    public List<MsgLog> selectTimeoutMsg();

    /**
     * 通过ID查询单条数据
     *
     * @param msgId 主键
     * @return 实例对象
     */
    MsgLog queryById(String msgId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<MsgLog> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param msgLog 实例对象
     * @return 实例对象
     */
    MsgLog insert(MsgLog msgLog);

    /**
     * 修改数据
     *
     * @param msgLog 实例对象
     * @return 实例对象
     */
    MsgLog update(MsgLog msgLog);

    /**
     * 通过主键删除数据
     *
     * @param msgId 主键
     * @return 是否成功
     */
    boolean deleteById(String msgId);

}