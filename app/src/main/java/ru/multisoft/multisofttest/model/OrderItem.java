package ru.multisoft.multisofttest.model;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.math.BigDecimal;

import ru.multisoft.multisofttest.helpers.MathUtils;


public class OrderItem implements Serializable{

    private Long id;

    private long orderId;

    private long productId;

    private String quantity;

    private String price;

    private String total;

    private long date;


    public OrderItem() {
        setId(null);
        setOrderId(0L);
        setProductId(0L);
        setQuantity(BigDecimal.ZERO);
        setPrice(BigDecimal.ZERO);
        setTotal(BigDecimal.ZERO);
        setDate(0L);
    }

    public Long getId() {
        return id;
    }

    public OrderItem setId(Long id) {
        this.id = id;
        return this;
    }

    public long getOrderId() {
        return orderId;
    }

    public OrderItem setOrderId(long orderId) {
        this.orderId = orderId;
        return this;
    }

    public long getProductId() {
        return productId;
    }

    public OrderItem setProductId(long productId) {
        this.productId = productId;
        return this;
    }

    @NonNull
    public BigDecimal getQuantity() {
        return MathUtils.getValue(quantity);
    }

    public OrderItem setQuantity(BigDecimal quantity) {
        this.quantity = quantity.toPlainString();
        return this;
    }

    @NonNull
    public BigDecimal getPrice() {
        return MathUtils.getValue(price);
    }

    public OrderItem setPrice(BigDecimal price) {
        this.price = price.toPlainString();
        return this;
    }

    @NonNull
    public BigDecimal getTotal() {
        return MathUtils.getValue(total);
    }

    public OrderItem setTotal(BigDecimal total) {
        this.total = total.toPlainString();
        return this;
    }

    public long getDate() {
        return date;
    }

    public OrderItem setDate(long date) {
        this.date = date;
        return this;
    }

}
