package com.liu.springbatch;

import com.liu.springbatch.config.SpringBatchConfiguration;
import com.liu.springbatch.entity.BatchMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;

@SpringBootApplication
@ComponentScan("com.liu.springbatch.dao")
@ComponentScan("com.liu.springbatch.config")
@ComponentScan("com.liu.springbatch.*")
@RestController
public class SpringBatchApplication {

    @Autowired
    SpringBatchConfiguration configuration;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public static void main(String[] args) {
        SpringApplication.run(SpringBatchApplication.class, args);
    }

    @GetMapping("/all")
    public String all(){
        configuration.writer();
        //封装行数据映射
        RowMapper<BatchMessage> rowMapper=new RowMapper<BatchMessage>() {
            @Override
            public BatchMessage mapRow(ResultSet rs, int rowNum) throws SQLException {
                BatchMessage grade=new BatchMessage(rs.getInt("id"),rs.getString("content"));
                return grade;
            }
        };
       return  jdbcTemplate.query("select * from batch_message",rowMapper).toString();
    }

}
