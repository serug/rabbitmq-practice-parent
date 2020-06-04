package top.serug.rabbitmq.topic;

import com.rabbitmq.client.*;
import top.serug.rabbitmq.util.ConnectionUtil;
import top.serug.rabbitmq.util.ConstantUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description:
 * @Author: serug
 * @Date: 2020/6/3  16:01
 */
public class TopicConsumerB {
    public static void main(String[] args) throws IOException, TimeoutException {
        //1.创建连接
        Connection connection = ConnectionUtil.getConnection();
        //2.创建channel
        Channel channel = connection.createChannel();
        //3.声明队列
        channel.queueDeclare(ConstantUtil.TOPIC_QUEUE_B,true,false,false,null);
        //4.队列绑定交换机:队列名称，交换机名称，路由Key
        channel.queueBind(ConstantUtil.TOPIC_QUEUE_B,ConstantUtil.TOPIC_EXCHANGES,"*.BBB");
        //5.创建消费者，并设置消息处理
        DefaultConsumer consumer = new DefaultConsumer(channel){
            /**
             * @Description:
             * @param consumerTag: 消费者标签，在channel.basicConsumer是可以指定
             * @param envelope: 消息包内容，可以从中获取消息ID，消息routingkey，交换机，消息，和重转标记（收到消息失败后是否需要重新发送）
             * @param properties: 消息属性
             * @param body: 消息
             * @return: void
             */
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("=============== TopicConsumerB  处理【*.BBB】类型的消息。。。。。");
                System.out.println("消息routingkey："+envelope.getRoutingKey());
                System.out.println("交换机："+envelope.getExchange());
                System.out.println("消息ID："+envelope.getDeliveryTag());
                System.out.println("*.BBB 路由，消息内容："+new String(body,"UTF-8"));
            }
        };

        //6.消息消费
        channel.basicConsume(ConstantUtil.TOPIC_QUEUE_B,true,consumer);


    }

}
