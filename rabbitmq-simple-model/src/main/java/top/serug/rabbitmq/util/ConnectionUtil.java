package top.serug.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description:
 * @Author: serug
 * @Date: 2020/6/3  11:12
 */
public class ConnectionUtil {
    public static Connection getConnection() throws IOException, TimeoutException {
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

        return connection;
    }
}
