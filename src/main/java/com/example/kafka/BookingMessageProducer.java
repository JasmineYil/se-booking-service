package com.example.kafka;

import com.example.dto.BookingRequestDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class BookingMessageProducer {

    private final KafkaTemplate<String, BookingRequestDto> updateBookingKafkaTemplate;

    public BookingMessageProducer(KafkaTemplate<String, BookingRequestDto> updateBookingKafkaTemplate) {
        this.updateBookingKafkaTemplate = updateBookingKafkaTemplate;
    }

    public void sendBookingMessage(BookingRequestDto bookingRequestDto) {
        String topic = "booking-update";
        updateBookingKafkaTemplate.send(topic, bookingRequestDto);
    }
}
