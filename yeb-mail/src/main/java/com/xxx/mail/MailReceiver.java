package com.xxx.mail;

import com.rabbitmq.client.Channel;
import com.xxx.server.pojo.Employee;
import com.xxx.server.pojo.MailConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.Map;


@Component
public class MailReceiver {
    private static final Logger logger = LoggerFactory.getLogger(MailReceiver.class);
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MailProperties mailProperties;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private RedisTemplate redisTemplate;

    @RabbitListener(queues = MailConstants.MAIL_QUEUE_NAME)
    public void handler(Message msg, Channel channel, @Payload Employee em, @Headers Map<String,Object> hd){

        logger.info("payload===>{}",em);
        logger.info("header1===>{}",hd);

        Employee employee = (Employee)msg.getPayload();
        MessageHeaders headers = msg.getHeaders();
        logger.info("data===>{}",employee);
        logger.info("header2==>{}",headers);
        //消息序号
        long tag = (long)headers.get(AmqpHeaders.DELIVERY_TAG);

        String msgId = (String)headers.get("spring_returned_message_correlation");

        MimeMessage message = javaMailSender.createMimeMessage();
        HashOperations hashOperations = redisTemplate.opsForHash();
        try {
            //如果 redis 中包含key，说明 消息已经被消费
            if(hashOperations.entries("mail_log").containsKey(msgId)){
                logger.info("消息已经被消费===>{}",msgId);
                channel.basicAck(tag,false);
                return;
            }
            MimeMessageHelper helper = new MimeMessageHelper(message);
            //发件人
            helper.setFrom(mailProperties.getUsername());
            //收件人
            helper.setTo(employee.getEmail());
            //主题
            helper.setSubject("入职欢迎邮件");
            //发送日期
            helper.setSentDate(new Date());
            //邮件内容
            Context context = new Context();
            context.setVariable("name",employee.getName());
            context.setVariable("posName",employee.getPosition().getName());
            context.setVariable("joblevelName",employee.getJoblevel().getName());
            context.setVariable("departmentName",employee.getDepartment().getName());
            String mail = templateEngine.process("mail", context);
            helper.setText(mail,true);
            //发送邮件
            javaMailSender.send(message);
            logger.info("邮件发送成功!");
            hashOperations.put("mail_log",msgId,"ok");
            channel.basicAck(tag,false);
        } catch (Exception e) {
            try {
                channel.basicNack(tag,false,true);
            } catch (IOException ioException) {

                logger.info("消息确认失败===>{}",ioException.getMessage());
            }
            logger.error("邮件发送失败====>{}",e.getMessage());
        }
    }
}
