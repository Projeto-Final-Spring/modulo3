package br.com.modulo3.consumer.consumer;

import br.com.modulo3.consumer.entity.Mensagem;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @RetryableTopic(
            autoCreateTopics = "false",
            backoff = @Backoff(
                    delay = 15000,
                    multiplier = 2.0,
                    maxDelay = 54000
            ),
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE
    )
    @KafkaListener(
            topics = "${spring.kafka.topic.modulo3}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "criarListener"
    )
    public void lerMensagem(@Payload ConsumerRecord<String, Mensagem> registro) {
        System.out.println("A mensagem chegou: " + registro.value());
    }
}