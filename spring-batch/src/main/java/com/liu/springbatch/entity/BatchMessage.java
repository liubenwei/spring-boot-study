package com.liu.springbatch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (BatchMessage)实体类
 *
 * @author liu
 * @since 2020-06-24 11:18:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchMessage implements Serializable {
    private static final long serialVersionUID = 740741231556847156L;
    
    private Integer id;
    
    private String content;
    
    private Date lastModifiedTime;
    
    private Date createTime;
    public BatchMessage(Integer id, String content){
        this.id = id;
        this.content = content;
    }





    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}