package com.liu.springbootstomp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestMessage {

	private String room;// 频道号
	private String type;// 消息类型('1':客户端到服务端 '2'：客户端到服务端)
	private String content;// 消息内容（即答案）
	private String userId;// 用户id
	private String questionId;// 题目id
	private String createTime;// 时间
}
