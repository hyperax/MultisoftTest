package ru.multisoft.multisofttest.model;

import android.support.annotation.NonNull;

import ru.multisoft.multisofttest.helpers.NpeUtils;

public class PaymentType {

    private Long id;

    private String name;

    private String description;

    private int counterNumber;

    private boolean isModifyCashbox;

    private boolean isPredefined;

    public PaymentType() {
        setId(null);
        setName("");
        setDescription("");
        setCounterNumber(0);
        setIsModifyCashbox(false);
        setIsPredefined(false);
    }

    public Long getId() {
        return id;
    }

    public PaymentType setId(Long id) {
        this.id = id;
        return this;
    }

    public int getCounterNumber() {
        return counterNumber;
    }

    public PaymentType setCounterNumber(int counterNumber) {
        this.counterNumber = counterNumber;
        return this;
    }

    @NonNull
    public String getName() {
        return NpeUtils.getNonNull(name);
    }

    public PaymentType setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isPredefined() {
        return isPredefined;
    }

    public PaymentType setIsPredefined(boolean isPredefined) {
        this.isPredefined = isPredefined;
        return this;
    }

    @NonNull
    public String getDescription() {
        return NpeUtils.getNonNull(description);
    }

    public PaymentType setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isModifyCashbox() {
        return isModifyCashbox;
    }

    public PaymentType setIsModifyCashbox(boolean isModifyCashbox) {
        this.isModifyCashbox = isModifyCashbox;
        return this;
    }
}
