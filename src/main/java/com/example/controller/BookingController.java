package com.example.controller;

import com.example.dto.BookingRequestDto;
import com.example.model.Booking;
import com.example.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<String> createBooking(@RequestBody BookingRequestDto bookingRequestDto) {
        try {
            bookingService.bookCar(bookingRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Booking successful");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred, please try again");
        }
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<String> deleteBooking(@PathVariable("bookingId") String bookingId) {
        try {
            bookingService.deleteBooking(bookingId);
            return ResponseEntity.status(HttpStatus.OK).body("Booking deleted successfully");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred, please try again");
        }
    }

    @GetMapping
    public ResponseEntity<?> getCurrentUserBookings() {
        try {
            List<Booking> bookings = bookingService.getAllBookingsForCurrentUser();
            return ResponseEntity.status(HttpStatus.OK).body(bookings);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred please try again.");
        }
    }
}
