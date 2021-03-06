package com.xxx.mail;

import com.xxx.server.pojo.MailConstants;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Queue;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//@EnableRabbit
public class MailApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailApplication.class, args);
    }

//    @Bean
//    public Queue queue(){
//        return new Queue(MailConstants.MAIL_QUEUE_NAME);
//    }
}
