package com.example.controller;

import com.example.dto.BookingRequestDto;
import com.example.dto.BookingResponseDto;
import com.example.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<String> createBooking(@RequestBody BookingRequestDto bookingRequestDto, ServerWebExchange serverWebExchange) {
        String jwtToken = Objects.requireNonNull(serverWebExchange.getRequest().getCookies().getFirst("jwtToken")).getValue();
        bookingService.bookCar(bookingRequestDto, jwtToken);
        return ResponseEntity.status(HttpStatus.CREATED).body("Booking successful");
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<String> deleteBooking(@PathVariable("bookingId") String bookingId) {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.status(HttpStatus.OK).body("Booking deleted successfully");
    }

    @GetMapping
    public ResponseEntity<List<BookingResponseDto>> getCurrentUserBookings(ServerWebExchange serverWebExchange) {
        String jwtToken = Objects.requireNonNull(serverWebExchange.getRequest().getCookies().getFirst("jwtToken")).getValue();
        List<BookingResponseDto> bookings = bookingService.getAllBookingsForCurrentUser(jwtToken);
        return ResponseEntity.status(HttpStatus.OK).body(bookings);
    }
}