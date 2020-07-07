package com.liu.springbatch.service;

import com.liu.springbatch.entity.BatchMessage;
import java.util.List;

/**
 * (BatchMessage)表服务接口
 *
 * @author liu
 * @since 2020-06-24 11:18:02
 */
public interface BatchMessageService {

    /**
     * 通过ID查询单条数据
     *
     * @param objectId 主键
     * @return 实例对象
     */
    BatchMessage queryById(Integer objectId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<BatchMessage> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param batchMessage 实例对象
     * @return 实例对象
     */
    BatchMessage insert(BatchMessage batchMessage);

    /**
     * 修改数据
     *
     * @param batchMessage 实例对象
     * @return 实例对象
     */
    BatchMessage update(BatchMessage batchMessage);

    /**
     * 通过主键删除数据
     *
     * @param objectId 主键
     * @return 是否成功
     */
    boolean deleteById(String objectId);

}