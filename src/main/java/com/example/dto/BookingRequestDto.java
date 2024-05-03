package com.example.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookingRequestDto {

    private String cardId;
    private LocalDate pickupDate;
    private LocalDate returnDate;
    private LocalDate bookingDate;
    private BigDecimal price;

    public BookingRequestDto() {}

    public BookingRequestDto(String cardId, LocalDate pickupDate, LocalDate returnDate, LocalDate bookingDate, BigDecimal price) {
        this.cardId = cardId;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
        this.bookingDate = bookingDate;
        this.price = price;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public LocalDate getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(LocalDate pickupDate) {
        this.pickupDate = pickupDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
