package com.xxx.server.config.security;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.rabbitmq.client.ReturnCallback;
import com.xxx.server.pojo.MailConstants;
import com.xxx.server.pojo.MailLog;
import com.xxx.server.service.IMailLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PutMapping;

@Configuration
public class RabbitMQConfig {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQConfig.class);
    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;
    @Autowired
    private IMailLogService mailLogService;
    /**
     * 创建一个持久化的队列
     * @return
     */
    @Bean
    public Queue queue(){
        return new Queue(MailConstants.MAIL_QUEUE_NAME,true);
    }
    /**
     * 创建交换机
     */
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(MailConstants.MAIL_EXCHANGE_NAME);
    }
    /**
     * 绑定交换机 队列
     */
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(directExchange()).with(MailConstants.MAIL_ROUTING_KEY_NAME);
    }
    @Bean
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate template = new RabbitTemplate(cachingConnectionFactory);
        template.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String s) {
                String id = correlationData.getId();
                if(ack){
                    logger.info("{}===>发送成功",id);
                    mailLogService.update(new UpdateWrapper<MailLog>().set("status",1).eq("msgId",id));
                }else{
                    logger.info("{}===>发送失败",id);
                }
            }
        });
        template.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returned) {
                logger.info("{}===>消息发送queue时失败",returned.getMessage().getBody());
            }
        });

        return template;
    }
}
