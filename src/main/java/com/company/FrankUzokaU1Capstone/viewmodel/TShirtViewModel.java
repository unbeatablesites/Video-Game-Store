package com.company.FrankUzokaU1Capstone.viewmodel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class TShirtViewModel {

    private int TShirtId;
    @NotEmpty(message = "Please supply a value for size")
    private String size;
    @NotEmpty(message = "Please supply a value for color")
    private String color;
    @NotEmpty(message = "Please supply a value for description")
    private String description;
    @NotNull(message = "Please supply a value for price")
    private BigDecimal price;
    @NotNull(message = "Please supply a value for quantity")
    private int quantity;

    public int getTShirtId() {
        return TShirtId;
    }

    public void setTShirtId(int TShirtId) {
        this.TShirtId = TShirtId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TShirtViewModel tShirt = (TShirtViewModel) o;
        return getTShirtId() == tShirt.getTShirtId() &&
                getQuantity() == tShirt.getQuantity() &&
                Objects.equals(getSize(), tShirt.getSize()) &&
                Objects.equals(getColor(), tShirt.getColor()) &&
                Objects.equals(getDescription(), tShirt.getDescription()) &&
                Objects.equals(getPrice(), tShirt.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTShirtId(), getSize(), getColor(), getDescription(), getPrice(), getQuantity());
    }
}
