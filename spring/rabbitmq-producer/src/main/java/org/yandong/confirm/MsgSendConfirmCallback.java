package org.yandong.confirm;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

@Component
public class MsgSendConfirmCallback implements RabbitTemplate.ConfirmCallback {
    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if (b) {
            System.out.println("消息确认成功！！");
        } else {
            System.out.println("消息确认失败。。。");
            System.out.println(s);
            // 如果本条消息一定要发送到队列中，例如下订单消息，我们可以采用消息补发
            // 采用递归（固定次数，不可无限）或 redis+定时任务
        }
    }
}
