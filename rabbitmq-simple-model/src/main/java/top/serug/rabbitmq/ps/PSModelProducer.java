package top.serug.rabbitmq.ps;

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
 * @Date: 2020/6/3  15:13
 */
public class PSModelProducer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //1.创建连接
        Connection connection = ConnectionUtil.getConnection();
        //2.创建channel
        Channel channel = connection.createChannel();
        //3.声明交换机：交换机名称，交换机类型
        channel.exchangeDeclare(ConstantUtil.FANOUT_EXCHANGES, BuiltinExchangeType.FANOUT);
        //4.声明队列
        channel.queueDeclare(ConstantUtil.FANOUT_QUEUE_A,true,false,false,null);
        channel.queueDeclare(ConstantUtil.FANOUT_QUEUE_B,true,false,false,null);
        //5.队列绑定交换机
        channel.queueBind(ConstantUtil.FANOUT_QUEUE_A,ConstantUtil.FANOUT_EXCHANGES,"");
        channel.queueBind(ConstantUtil.FANOUT_QUEUE_B,ConstantUtil.FANOUT_EXCHANGES,"");
        //6.发送消息
        for (int i = 0; i < 10; i++) {
            String message = "这个是PS模式，广播类型的交换机，消息号："+i;
            channel.basicPublish(ConstantUtil.FANOUT_EXCHANGES,"",null,message.getBytes());
            System.out.println("============= 发送的消息为："+message);
        }

        //7.释放资源
        channel.close();
        connection.close();
    }
}
