package top.serug.rabbitmq.util;

/**
 * @Description:
 * @Author: serug
 * @Date: 2020/6/3  14:19
 */
public class ConstantUtil {
    //简单模式队列名
    public static final String SIMPLE_QUEUE_NAME = "simple-queue";
    //工作模式队列名
    public static final String WORK_QUEUE_NAME = "work-queue";


    //广播类型交换机--发布订阅模式
    public static final String FANOUT_EXCHANGES = "fanout_exchanges";
    public static final String FANOUT_QUEUE_A = "fanout_queue_a";
    public static final String FANOUT_QUEUE_B = "fanout_queue_b";

    //定向类型交换机--路由模式
    public static final String DIRECT_EXCHANGES = "direct_exchanges";
    public static final String DIRECT_QUEUE_A = "direct_queue_a";
    public static final String DIRECT_QUEUE_B = "direct_queue_b";

    //通配符类型交换机--主题模式
    public static final String TOPIC_EXCHANGES = "topic_exchanges";
    public static final String TOPIC_QUEUE_A = "topic_queue_a";
    public static final String TOPIC_QUEUE_B = "topic_queue_b";
}
