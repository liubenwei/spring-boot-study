package com.liu.mq.rabbitmq.entity;

import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.io.Serializable;

/**
 * 消息投递日志(MsgLog)实体类
 *
 * @author makejava
 * @since 2020-06-21 19:23:17
 */

public class MsgLog implements Serializable {
    private static final long serialVersionUID = 404200774526397647L;
    /**
    * 消息唯一标识
    */
    private String msgId;
    /**
    * 消息体, json格式化
    */
    private String msg;
    /**
    * 交换机
    */
    private String exchange;
    /**
    * 路由键
    */
    private String routingKey;
    /**
    * 状态: 0投递中 1投递成功 2投递失败 3已消费
    */
    private Integer status;
    /**
    * 重试次数
    */
    private Integer tryCount;
    /**
    * 下一次重试时间
    */
    private Date nextTryTime;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 更新时间
    */
    private Date updateTime;


    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTryCount() {
        return tryCount;
    }

    public void setTryCount(Integer tryCount) {
        this.tryCount = tryCount;
    }

    public Date getNextTryTime() {
        return nextTryTime;
    }

    public void setNextTryTime(Date nextTryTime) {
        this.nextTryTime = nextTryTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}