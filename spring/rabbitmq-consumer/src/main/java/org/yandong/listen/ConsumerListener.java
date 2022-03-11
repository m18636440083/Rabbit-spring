package org.yandong.listen;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * MessageListener接口用于spring容器接收到消息后处理消息
 * 如果需要使用自己定义的类型 来实现 处理消息时，必须实现该接口，并重写onMessage()方 法当spring容器接收消息后，会自动交由onMessage进行处理
 */
@Component
public class ConsumerListener implements MessageListener {
    // jackson提供序列化和反序列中使用最多的类，用来转换json的
    private static final ObjectMapper MAPPER = new ObjectMapper();
    @Override
    public void onMessage(Message message) {
        // 将message对象转换成json
        try {
            JsonNode jsonNode = MAPPER.readTree(message.getBody());
            String name = jsonNode.get("name").asText();
            String iphone = jsonNode.get("iphone").asText();
            System.out.println("从队列中获取：【"+name+"的电话是:"+iphone+"】");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
