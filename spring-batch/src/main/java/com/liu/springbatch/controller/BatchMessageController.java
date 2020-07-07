package com.liu.springbatch.controller;

import com.liu.springbatch.entity.BatchMessage;
import com.liu.springbatch.service.BatchMessageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (BatchMessage)表控制层
 *
 * @author liu
 * @since 2020-06-24 11:18:02
 */
@RestController
@RequestMapping("batchMessage")
public class BatchMessageController {
    /**
     * 服务对象
     */
    @Resource
    private BatchMessageService batchMessageService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public BatchMessage selectOne(Integer id) {
        return this.batchMessageService.queryById(id);
    }

}