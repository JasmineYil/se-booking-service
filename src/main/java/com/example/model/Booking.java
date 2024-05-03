package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Document("bookings")
public class Booking {

    @Id
    private String bookingId;

    private String carId;
    private String customerEmail;
    private BigDecimal price;
    private LocalDate bookingDate;
    private LocalDate returnDate;
    private LocalDate pickupDate;

    public Booking() {}

    public Booking(String bookingId, String carId, String customerEmail, BigDecimal price, LocalDate bookingDate, LocalDate returnDate, LocalDate pickupDate) {
        this.bookingId = bookingId;
        this.carId = carId;
        this.customerEmail = customerEmail;
        this.price = price;
        this.bookingDate = bookingDate;
        this.returnDate = returnDate;
        this.pickupDate = pickupDate;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LocalDate getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(LocalDate pickupDate) {
        this.pickupDate = pickupDate;
    }
}
