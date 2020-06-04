package top.serug.rabbitmq.controller;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: serug
 * @Date: 2020/6/3  18:08
 */
@Component
public class ConsumerListener {

    @RabbitListener(queues = "item_queue")
    public void msg(String msg){
        System.out.println("消费者消费了消息："+msg);
    }

}
