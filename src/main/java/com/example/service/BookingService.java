package com.example.service;

import com.example.dto.BookingRequestDto;
import com.example.dto.BookingResponseDto;
import com.example.kafka.BookingMessageProducer;
import com.example.model.Booking;
import com.example.repository.BookingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
        Booking booking = bookingRequestDtoToBooking(bookingRequestDto, email);

        bookingRepository.save(booking);
        bookingMessageProducer.sendBookingMessage(bookingRequestDto);
    }

    @Transactional(readOnly = true)
    public List<BookingResponseDto> getAllBookingsForCurrentUser(String jwtToken) {
        String email = getCurrentUserEmail(jwtToken);
        List<Booking> bookingList = bookingRepository.findBookingsByCustomerEmail(email);

        return bookingList.stream()
                .map(this::mapOrderToCustomerBookingsResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteBooking(String bookingId) {
        Booking booking = bookingRepository.findByBookingId(bookingId);

        BookingRequestDto bookingRequestDto = new BookingRequestDto();
        bookingRequestDto.setCarId(booking.getCarId());
        bookingRequestDto.setPrice(booking.getPrice());
        bookingRequestDto.setPickupDate(booking.getPickupDate());
        bookingRequestDto.setReturnDate(booking.getReturnDate());
        bookingRequestDto.setBookingDate(booking.getBookingDate());

        bookingRepository.delete(booking);
        bookingMessageProducer.sendBookingMessage(bookingRequestDto);
    }

    private BookingResponseDto mapOrderToCustomerBookingsResponseDto(Booking booking) {
        return new BookingResponseDto(
                booking.getBookingId(),
                booking.getCarId(),
                booking.getPrice(),
                booking.getBookingDate(),
                booking.getReturnDate(),
                booking.getPickupDate()
        );
    }

    private Booking bookingRequestDtoToBooking(BookingRequestDto bookingRequestDto, String email) {
        Booking booking = new Booking();
        booking.setCarId(bookingRequestDto.getCarId());
        booking.setPrice(bookingRequestDto.getPrice());
        booking.setBookingDate(bookingRequestDto.getBookingDate());
        booking.setPickupDate(bookingRequestDto.getPickupDate());
        booking.setReturnDate(bookingRequestDto.getReturnDate());
        booking.setCustomerEmail(email);

        return booking;
    }

    private String getCurrentUserEmail(String jwtToken) {
        return jwtDecoderService.getEmailFromToken(jwtToken);
    }
}
