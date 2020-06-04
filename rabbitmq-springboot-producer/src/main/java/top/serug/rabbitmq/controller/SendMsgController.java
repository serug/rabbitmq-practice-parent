package top.serug.rabbitmq.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.serug.rabbitmq.config.RabbitmqConfig;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: serug
 * @Date: 2020/6/3  18:08
 */
@RestController
public class SendMsgController {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/sendmsg")
    public String sendMsg(String msg,String routingKey){
        rabbitTemplate.convertAndSend(RabbitmqConfig.ITEM_TOPIC_EXCHANGE,routingKey,msg);
        return "消息发送成功";
    }

}
