package com.liu.springbatch.service.impl;

import com.liu.springbatch.entity.BatchMessage;
import com.liu.springbatch.dao.BatchMessageDao;
import com.liu.springbatch.service.BatchMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (BatchMessage)表服务实现类
 *
 * @author liu
 * @since 2020-06-24 11:18:02
 */
@Service("batchMessageService")
public class BatchMessageServiceImpl implements BatchMessageService {
    @Resource
    private BatchMessageDao batchMessageDao;

    /**
     * 通过ID查询单条数据
     *
     * @param objectId 主键
     * @return 实例对象
     */
    @Override
    public BatchMessage queryById(Integer objectId) {
        return this.batchMessageDao.queryById(objectId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<BatchMessage> queryAllByLimit(int offset, int limit) {
        return this.batchMessageDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param batchMessage 实例对象
     * @return 实例对象
     */
    @Override
    public BatchMessage insert(BatchMessage batchMessage) {
        this.batchMessageDao.insert(batchMessage);
        return batchMessage;
    }

    /**
     * 修改数据
     *
     * @param batchMessage 实例对象
     * @return 实例对象
     */
    @Override
    public BatchMessage update(BatchMessage batchMessage) {
        this.batchMessageDao.update(batchMessage);
        return this.queryById(batchMessage.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param objectId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String objectId) {
        return this.batchMessageDao.deleteById(objectId) > 0;
    }
}