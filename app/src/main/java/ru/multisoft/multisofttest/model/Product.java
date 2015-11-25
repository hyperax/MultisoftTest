package ru.multisoft.multisofttest.model;

import android.support.annotation.NonNull;

import java.math.BigDecimal;

import ru.multisoft.multisofttest.helpers.MathUtils;
import ru.multisoft.multisofttest.helpers.NpeUtils;

public class Product {

    private Long id;

    private String name;

    private String code;

    private String description;

    private String parentCode;

    private boolean isGroup;

    private long measureUnitId;

    private String multiplicity;

    private String price;

    private int gravityMin;

    private int gravityMax;

    private boolean isRequestQuantity;

    private int sortIndex;

    private boolean isDeleted;

    public Product() {
        setId(null);
        setName("");
        setCode("");
        setDescription("");
        setParentCode("");
        setIsGroup(false);
        setMeasureUnitId(0L);
        setMultiplicity(BigDecimal.ONE);
        setPrice(BigDecimal.ZERO);
        setGravityMin(0);
        setGravityMax(0);
        setRequestQuantity(false);
        setSortIndex(0);
        setDeleted(false);
    }

    public Long getId() {
        return id;
    }

    public Product setId(Long id) {
        this.id = id;
        return this;
    }

    @NonNull
    public String getName() {
        return NpeUtils.getNonNull(name);
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    @NonNull
    public String getDescription() {
        return NpeUtils.getNonNull(description);
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    @NonNull
    public String getParentCode() {
        return NpeUtils.getNonNull(parentCode);
    }

    public Product setParentCode(String parentId) {
        this.parentCode = parentId;
        return this;
    }

    public long getMeasureUnitId() {
        return measureUnitId;
    }

    public Product setMeasureUnitId(long measureUnitId) {
        this.measureUnitId = measureUnitId;
        return this;
    }

    @NonNull
    public BigDecimal getPrice() {
        return MathUtils.getValue(price);
    }

    public Product setPrice(BigDecimal price) {
        this.price = NpeUtils.getNonNull(price).toPlainString();
        return this;
    }

    public boolean isRequestQuantity() {
        return isRequestQuantity;
    }

    public Product setRequestQuantity(boolean requestQuantity) {
        this.isRequestQuantity = requestQuantity;
        return this;
    }

    public int getSortIndex() {
        return sortIndex;
    }

    public Product setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
        return this;
    }

    @NonNull
    public String getCode() {
        return NpeUtils.getNonNull(code);
    }

    public Product setCode(String code) {
        this.code = code;
        return this;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public Product setIsGroup(boolean isGroup) {
        this.isGroup = isGroup;
        return this;
    }

    @NonNull
    public BigDecimal getMultiplicity() {
        return MathUtils.getValue(multiplicity);
    }

    public Product setMultiplicity(BigDecimal multiplicity) {
        this.multiplicity = NpeUtils.getNonNull(multiplicity).toPlainString();
        return this;
    }

    public int getGravityMin() {
        return gravityMin;
    }

    public Product setGravityMin(int gravityMin) {
        this.gravityMin = gravityMin;
        return this;
    }

    public int getGravityMax() {
        return gravityMax;
    }

    public Product setGravityMax(int gravityMax) {
        this.gravityMax = gravityMax;
        return this;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public Product setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }
}
