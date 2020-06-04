package top.serug.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: serug
 * @Date: 2020/6/3  17:49
 */
@Configuration
public class RabbitmqConfig {

    //交换机名称
    public static final String ITEM_TOPIC_EXCHANGE = "item_topic_exchange";

    //队列名称
    public static final String ITEM_QUEUE = "item_queue";

    /**
     * @Description: 声明交换机
      * @param :
     * @return: org.springframework.amqp.rabbit.annotation.Exchange
     */
    @Bean("itemTopicExchange")
    public Exchange topicExchange(){
        return ExchangeBuilder.topicExchange(ITEM_TOPIC_EXCHANGE).durable(true).build();
    }

    /**
     * @Description: 声明队列
      * @param :
     * @return: org.springframework.amqp.core.Queue
     */
    @Bean("itemQueue")
    public Queue itemQueue(){
        return QueueBuilder.durable(ITEM_QUEUE).build();
    }

    /**
     * @Description: 绑定队列交换机
      * @param :
     * @return: org.springframework.amqp.core.Queue
     */
    @Bean
    public Binding bindQuueueAndExchange(@Qualifier("itemQueue") Queue queue, @Qualifier("itemTopicExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("item.#").noargs();
    }
}
