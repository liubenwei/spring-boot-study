package com.liu.mq.rabbitmq.service.impl;

import com.liu.mq.rabbitmq.entity.MsgLog;
import com.liu.mq.rabbitmq.dao.MsgLogDao;
import com.liu.mq.rabbitmq.service.MsgLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 消息投递日志(MsgLog)表服务实现类
 *
 * @author makejava
 * @since 2020-06-21 19:23:22
 */
@Service("msgLogService")
public class MsgLogServiceImpl implements MsgLogService {
    @Resource
    private MsgLogDao msgLogDao;

    @Override
    public List<MsgLog> selectTimeoutMsg() {
        return msgLogDao.selectTimeoutMsg();
    }

    /**
     * 通过ID查询单条数据
     *
     * @param msgId 主键
     * @return 实例对象
     */
    @Override
    public MsgLog queryById(String msgId) {
        return this.msgLogDao.queryById(msgId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<MsgLog> queryAllByLimit(int offset, int limit) {
        return this.msgLogDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param msgLog 实例对象
     * @return 实例对象
     */
    @Override
    public MsgLog insert(MsgLog msgLog) {
        this.msgLogDao.insert(msgLog);
        return msgLog;
    }

    /**
     * 修改数据
     *
     * @param msgLog 实例对象
     * @return 实例对象
     */
    @Override
    public MsgLog update(MsgLog msgLog) {
        this.msgLogDao.update(msgLog);
        return this.queryById(msgLog.getMsgId());
    }

    /**
     * 通过主键删除数据
     *
     * @param msgId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String msgId) {
        return this.msgLogDao.deleteById(msgId) > 0;
    }
}