package com.example.service;

import com.example.dto.BookingRequestDto;
import com.example.kafka.BookingMessageProducer;
import com.example.model.Booking;
import com.example.repository.BookingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMessageProducer bookingMessageProducer;
    private final JwtDecoderService jwtDecoderService;

    public BookingService(BookingRepository bookingRepository, BookingMessageProducer bookingMessageProducer, JwtDecoderService jwtDecoderService) {
        this.bookingRepository = bookingRepository;
        this.bookingMessageProducer = bookingMessageProducer;
        this.jwtDecoderService = jwtDecoderService;
    }

    @Transactional
    public void bookCar(BookingRequestDto bookingRequestDto, String jwtToken) {
        String email = getCurrentUserEmail(jwtToken);

        bookingMessageProducer.sendBookingMessage(bookingRequestDto);
        Booking booking = bookingRequestDtoToBooking(bookingRequestDto, email);

        bookingRepository.save(booking);
    }

    @Transactional(readOnly = true)
    public List<Booking> getAllBookingsForCurrentUser(String jwtToken) {
        String email = getCurrentUserEmail(jwtToken);

        return bookingRepository.findBookingsByCustomerEmail(email);
    }

    @Transactional
    public void deleteBooking(String bookingId) {
        Booking booking = bookingRepository.findByBookingId(bookingId);
        bookingRepository.delete(booking);
    }

    private Booking bookingRequestDtoToBooking(BookingRequestDto bookingRequestDto, String email) {
        Booking booking = new Booking();
        booking.setCarId(bookingRequestDto.getCardId());
        booking.setPrice(bookingRequestDto.getPrice());
        booking.setBookingDate(bookingRequestDto.getBookingDate());
        booking.setPickupDate(bookingRequestDto.getPickupDate());
        booking.setReturnDate(bookingRequestDto.getReturnDate());
        booking.setCustomerEmail(email);

        return booking;
    }

    private String getCurrentUserEmail(String jwtToken) {
        return jwtDecoderService.getEmailFromToken(jwtToken);
        //return "test2@test.com";
    }
}
