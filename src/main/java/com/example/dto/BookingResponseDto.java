package com.example.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookingResponseDto {

    private String bookingId;
    private String carId;
    private BigDecimal price;
    private LocalDate bookingDate;
    private LocalDate returnDate;
    private LocalDate pickupDate;

    public BookingResponseDto() {
    }

    public BookingResponseDto(
            String bookingId,
            String carId,
            BigDecimal price,
            LocalDate bookingDate,
            LocalDate returnDate,
            LocalDate pickupDate
    ) {
        this.bookingId = bookingId;
        this.carId = carId;
        this.price = price;
        this.bookingDate = bookingDate;
        this.returnDate = returnDate;
        this.pickupDate = pickupDate;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getCarId() {
        return carId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public LocalDate getPickupDate() {
        return pickupDate;
    }
}