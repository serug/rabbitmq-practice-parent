package top.serug.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import top.serug.rabbitmq.util.ConstantUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description:
 * @Author: serug
 * @Date: 2020/6/3  10:44
 */
public class SimpleModelProducer {

    public static void main(String[] args) throws IOException, TimeoutException {
        //1：创建连接工厂对象
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //2：设置工厂对象属性
        connectionFactory.setHost("192.168.1.4");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/serug-host");
        connectionFactory.setUsername("serug");
        connectionFactory.setPassword("serug");
        //3.创建连接对象
        Connection connection = connectionFactory.newConnection();
        //4.创建channel对象
        Channel channel = connection.createChannel();
        //5.创建队列
        /*
        params：队列名称，是否定义持久化，是否独占本次连接，是否在不使用的时候自动删除队列，队列其他参数
         */
        channel.queueDeclare(ConstantUtil.SIMPLE_QUEUE_NAME,true,false,false,null);
        //6.发送消息
        String message = "hello, rabbitMQ! i am simple message";
        channel.basicPublish("",ConstantUtil.SIMPLE_QUEUE_NAME,null,message.getBytes());
        System.out.println("============= 发送的消息为："+message);
        //7.释放资源
        channel.close();
        connection.close();
    }
}
