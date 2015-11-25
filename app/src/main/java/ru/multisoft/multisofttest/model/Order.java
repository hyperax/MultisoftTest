package ru.multisoft.multisofttest.model;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.math.BigDecimal;

import ru.multisoft.multisofttest.helpers.DateUtils;
import ru.multisoft.multisofttest.helpers.MathUtils;
import ru.multisoft.multisofttest.helpers.NpeUtils;

public class Order implements Serializable{

    private Long id;

    private String code;

    private long dateOpen; // in mills

    private long dateClose; // in mills

    private long shiftId;

    private long authorId;

    private long employeeId;

    private long locationId;

    private int guestsNumber;

    private boolean isClosed;

    private String total;

    public Order() {
        setId(null);
        setCode("");
        setDateOpen(0L);
        setDateClose(0L);
        setShiftId(0L);
        setAuthorId(0L);
        setEmployeeId(0L);
        setLocationId(0L);
        setGuestsNumber(0);
        setClosed(false);
        setTotal(BigDecimal.ZERO);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public Order setCode(String code) {
        this.code = code;
        return this;
    }

    public long getDateOpen() {
        return dateOpen;
    }

    public Order setDateOpen(long dateOpen) {
        this.dateOpen = dateOpen;
        return this;
    }

    public long getAuthorId() {
        return authorId;
    }

    public Order setAuthorId(long authorId) {
        this.authorId = authorId;
        return this;
    }

    public long getLocationId() {
        return locationId;
    }

    public Order setLocationId(long locationId) {
        this.locationId = locationId;
        return this;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public Order setClosed(boolean isClosed) {
        this.isClosed = isClosed;
        return this;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public Order setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public Order setTotal(BigDecimal total) {
        this.total = NpeUtils.getNonNull(total).toPlainString();
        return this;
    }

    @NonNull
    public BigDecimal getTotal() {
        return MathUtils.getValue(total);
    }

    public long getShiftId() {
        return shiftId;
    }

    public Order setShiftId(long shiftId) {
        this.shiftId = shiftId;
        return this;
    }

    public long getDateClose() {
        return dateClose;
    }

    public Order setDateClose(long dateClose) {
        this.dateClose = dateClose;
        return this;
    }

    public Order setDateClose() {
        return setDateClose(DateUtils.getDateMills());
    }

    public int getGuestsNumber() {
        return guestsNumber;
    }

    public Order setGuestsNumber(int guestsNumber) {
        this.guestsNumber = guestsNumber;
        return this;
    }

}
