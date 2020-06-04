package top.serug.rabbitmq.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import top.serug.rabbitmq.util.ConnectionUtil;
import top.serug.rabbitmq.util.ConstantUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description:
 * @Author: serug
 * @Date: 2020/6/3  14:21
 */
public class WorkModelProducer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //1.创建连接对象
        Connection connection = ConnectionUtil.getConnection();
        //2.创建channel对象
        Channel channel = connection.createChannel();
        //3.创建队列
        /*
        params：队列名称，是否定义持久化，是否独占本次连接，是否在不使用的时候自动删除队列，队列其他参数
         */
        channel.queueDeclare(ConstantUtil.WORK_QUEUE_NAME,true,false,false,null);
        //4.发送消息
        for (int i = 0; i < 30; i++) {
            String message = "hello, rabbitMQ! i am simple message【"+i+"】";
            channel.basicPublish("",ConstantUtil.WORK_QUEUE_NAME,null,message.getBytes());
            System.out.println("============= 发送的消息为："+message);
        }
        //5.释放资源
        channel.close();
        connection.close();
    }
}
