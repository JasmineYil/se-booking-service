package com.example.kafka;

import com.example.dto.BookingRequestDto;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class BookingProducerConfig {

    private final String BOOTSTRAP_SERVER = "localhost:9092";

    @Bean
    public Map<String, Object> updateBookingProducerConfig() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return configProps;
    }

    @Bean
    public ProducerFactory<String, BookingRequestDto> updateBookingProducerFactory() {
        return new DefaultKafkaProducerFactory<>(
                updateBookingProducerConfig(),
                new StringSerializer(),
                new JsonSerializer<>()
        );
    }

    @Bean
    public KafkaTemplate<String, BookingRequestDto> upDateBookingKafkaTemplate() {
        return new KafkaTemplate<>(updateBookingProducerFactory());
    }
}
