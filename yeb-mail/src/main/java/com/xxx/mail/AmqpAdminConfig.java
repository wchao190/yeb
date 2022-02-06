package com.xxx.mail;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

@Configurable
public class AmqpAdminConfig implements AmqpAdmin{

    @Override
    public void declareExchange(Exchange exchange) {

    }

    @Override
    public boolean deleteExchange(String s) {
        return false;
    }

    @Override
    public Queue declareQueue() {
        return null;
    }

    @Override
    public String declareQueue(Queue queue) {
        return null;
    }

    @Override
    public boolean deleteQueue(String s) {
        return false;
    }

    @Override
    public void deleteQueue(String s, boolean b, boolean b1) {

    }

    @Override
    public void purgeQueue(String s, boolean b) {

    }

    @Override
    public int purgeQueue(String s) {
        return 0;
    }

    @Override
    public void declareBinding(Binding binding) {

    }

    @Override
    public void removeBinding(Binding binding) {

    }

    @Override
    public Properties getQueueProperties(String s) {
        return null;
    }

    @Override
    public QueueInformation getQueueInfo(String s) {
        return null;
    }
}
