package br.com.modulo3.consumer.configuration;

import br.com.modulo3.consumer.entity.Mensagem;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper;

@Configuration
public class ListenerConfig {
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Mensagem> criarListener(KafkaProperties propriedadesKafka) {
        ConcurrentKafkaListenerContainerFactory<String, Mensagem> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(criarConsumerFactory(propriedadesKafka));
        return factory;
    }

    private ConsumerFactory<String, Mensagem> criarConsumerFactory(KafkaProperties kafkaProperties) {
        DefaultJackson2JavaTypeMapper mapper = new DefaultJackson2JavaTypeMapper();
        mapper.addTrustedPackages("*");

        JsonDeserializer<Mensagem> jsonDeserializer = new JsonDeserializer<>(Mensagem.class);
        jsonDeserializer.setTypeMapper(mapper);
        jsonDeserializer.setUseTypeMapperForKey(true);

        return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties(), new StringDeserializer(), jsonDeserializer);
    }
}
