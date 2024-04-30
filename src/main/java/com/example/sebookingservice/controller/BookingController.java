package com.example.sebookingservice.controller;

import com.example.sebookingservice.dto.BookingRequestDto;
import com.example.sebookingservice.service.BookingService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<String> bookCar(@RequestBody BookingRequestDto bookingRequestDto ) {
        try {
            bookingService.bookCar(bookingRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Booking successful");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred, please try again");
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteBooking(@PathVariable("orderId") long orderId) {
        try {
            bookingService.deleteBookingForCurrentUser(orderId);
            return ResponseEntity.status(HttpStatus.OK).body("Booking deleted successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred, please try again");
        }
    }
}
