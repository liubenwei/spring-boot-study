package com.liu.springbatch.processor;

import com.liu.springbatch.entity.BatchMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * @author liu
 */

@Slf4j
public class MessageItemProcessor
		implements ItemProcessor<BatchMessage, BatchMessage> {
	@Override
	public BatchMessage process(BatchMessage batchMessage) throws Exception {
		log.info("process");
		return batchMessage;
	}
}
