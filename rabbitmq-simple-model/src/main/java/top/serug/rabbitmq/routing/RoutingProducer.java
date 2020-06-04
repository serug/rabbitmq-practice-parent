package top.serug.rabbitmq.routing;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import top.serug.rabbitmq.util.ConnectionUtil;
import top.serug.rabbitmq.util.ConstantUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description:
 * @Author: serug
 * @Date: 2020/6/3  16:00
 */
public class RoutingProducer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //1.创建连接
        Connection connection = ConnectionUtil.getConnection();
        //2.创建channel
        Channel channel = connection.createChannel();
        //3.声明交换机：交换机名称，交换机类型
        channel.exchangeDeclare(ConstantUtil.DIRECT_EXCHANGES, BuiltinExchangeType.DIRECT);
        //4.声明队列
        channel.queueDeclare(ConstantUtil.DIRECT_QUEUE_A,true,false,false,null);
        channel.queueDeclare(ConstantUtil.DIRECT_QUEUE_B,true,false,false,null);
        //5.队列绑定交换机
        channel.queueBind(ConstantUtil.DIRECT_QUEUE_A,ConstantUtil.DIRECT_EXCHANGES,"AAA");
        channel.queueBind(ConstantUtil.DIRECT_QUEUE_B,ConstantUtil.DIRECT_EXCHANGES,"BBB");
        //6.发送消息
        String messageA = "这个是路由模式，定向类型的交换机，消息路由为：【AAA】";
        /*
           交换机名称，路由Key，消息其他属性，消息内容
         */
        channel.basicPublish(ConstantUtil.DIRECT_EXCHANGES,"AAA",null,messageA.getBytes());
        System.out.println("=============AAA 发送的消息为："+messageA);


        String messageB = "这个是路由模式，定向类型的交换机，消息路由为：【BBB】";
        /*
           交换机名称，路由Key，消息其他属性，消息内容
         */
        channel.basicPublish(ConstantUtil.DIRECT_EXCHANGES,"BBB",null,messageB.getBytes());
        System.out.println("=============BBB  发送的消息为："+messageB);


        //7.释放资源
        channel.close();
        connection.close();
    }

}
