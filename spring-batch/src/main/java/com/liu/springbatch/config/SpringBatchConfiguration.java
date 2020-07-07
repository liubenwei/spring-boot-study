package com.liu.springbatch.config;

import com.liu.springbatch.entity.BatchMessage;
import com.liu.springbatch.listener.JobCompletionNotificationListener;
import com.liu.springbatch.processor.MessageItemProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.ApplicationContextFactory;
import org.springframework.batch.core.configuration.support.GenericApplicationContextFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
@Component
// enableBatfchProcessing是打开batch。如果要实现多job的情况，需要把这个注解的modular打开，让每个job使用自己的applicationContext
@EnableBatchProcessing//(modular = true)
@Slf4j
public class SpringBatchConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource dataSource;



	@Bean
	public MessageItemProcessor processor() {
		return new MessageItemProcessor();
	}

	@Bean
	public FlatFileItemReader<BatchMessage> reader() {
		return new FlatFileItemReaderBuilder<BatchMessage>()
				.name("messageItemReader")
				.resource(new ClassPathResource("sample-data.csv"))
				.delimited()
				.names(new String[]{"content"})
				.fieldSetMapper(new BeanWrapperFieldSetMapper<BatchMessage>(){
					{
						setTargetType(BatchMessage.class);
					}
				}).build();
	}

	@Bean
	public JdbcBatchItemWriter<BatchMessage> writer() {
		log.info("datasource:"+dataSource);
		return new JdbcBatchItemWriterBuilder<BatchMessage>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO batch_message (content) VALUES (:content)")
				.dataSource(dataSource)
				.build();
	}

	@Bean
	public Job importMessageJob(JobCompletionNotificationListener listener,
			Step step1) {
		log.info(listener.toString());
		return jobBuilderFactory.get("importMessageJob")
				.incrementer(new RunIdIncrementer()).listener(listener)
				.flow(step1).end().build();

	}

	@Bean
	public Step step1(JdbcBatchItemWriter<BatchMessage> writer) {
		return stepBuilderFactory.get("step1")
				.<BatchMessage, BatchMessage> chunk(10).reader(reader())
				.processor(processor()).writer(writer).build();
	}

}
