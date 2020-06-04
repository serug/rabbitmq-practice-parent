package top.serug.rabbitmq.work;

import com.rabbitmq.client.*;
import top.serug.rabbitmq.util.ConnectionUtil;
import top.serug.rabbitmq.util.ConstantUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description:
 * @Author: serug
 * @Date: 2020/6/3  14:22
 */
public class WorkModelConsumerA {
    public static void main(String[] args) throws IOException, TimeoutException {
        //1.获取连接
        Connection connection = ConnectionUtil.getConnection();
        //2.创建channel
        Channel channel = connection.createChannel();
        //3.创建队列：并设置消息处理
        channel.queueDeclare(ConstantUtil.WORK_QUEUE_NAME,true,false,false,null);
        //4.创建一个consumer：处理消息
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
                System.out.println("=============== Comsumer A 消息处理中。。。。。");
                System.out.println("消息routingkey："+envelope.getRoutingKey());
                System.out.println("交换机："+envelope.getExchange());
                System.out.println("消息ID："+envelope.getDeliveryTag());
                System.out.println("消息内容："+new String(body,"UTF-8"));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        //5.消费消息:
        /*
        队列名称，
        是否自动确认：设置为true，表示消息接收到自动向mq回复接收到，mq接收到回复后，会自动删除消息，设置我false则需要手动确认
         */
        channel.basicConsume(ConstantUtil.WORK_QUEUE_NAME,true,consumer);

    }

}
