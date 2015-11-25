package ru.multisoft.multisofttest.hardware;

import java.math.BigDecimal;
import java.util.List;

import ru.multisoft.multisofttest.model.OrderItem;
import ru.multisoft.multisofttest.model.PaymentType;
import ru.multisoft.multisofttest.model.Product;

public abstract class EcrDriver {

    private EcrStatus mEcrStatus;

    public boolean prepare() {
        setStatus(new EcrStatus(EcrStatusType.NOT_INITIALIZED, "driver isn't initialized"));
        return false;
    }

    public boolean finish() {
        setStatus(new EcrStatus(EcrStatusType.OK, "destroyed by default"));
        return true;
    }

    public EcrStatus getStatus() {
        return mEcrStatus;
    }

    public boolean payment(List<OrderItem> items, List<Product> products, PaymentType payType) {
        setStatus(new EcrStatus(EcrStatusType.NOT_SUPPORTED, "'Payment' not supported"));
        return false;
    }

    public String reportX() {
        setStatus(new EcrStatus(EcrStatusType.NOT_SUPPORTED,"'Report X' not supported"));
        return null;
    }

    public String reportZ() {
        setStatus(new EcrStatus(EcrStatusType.NOT_SUPPORTED,"'Report Z' not supported"));
        return null;
    }

    public boolean cashIncome(BigDecimal amount) {
        setStatus(new EcrStatus(EcrStatusType.NOT_SUPPORTED,"'Cash income' not supported"));
        return false;
    }

    public boolean cashOutcome(BigDecimal amount) {
        setStatus(new EcrStatus(EcrStatusType.NOT_SUPPORTED,"'Cash outcome' not supported"));
        return false;
    }

    public boolean setDemoMode(boolean isDemo) {
        setStatus(new EcrStatus(EcrStatusType.NOT_SUPPORTED,"'Demo mode' not supported"));
        return false;
    }

    protected void setStatus(EcrStatus result) {
        mEcrStatus = result;
    }

    public String getInfo() {
        setStatus(new EcrStatus(EcrStatusType.NOT_SUPPORTED,"'Information' not supported"));
        return null;
    }

    public BigDecimal cashSumm() {
        setStatus(new EcrStatus(EcrStatusType.NOT_SUPPORTED,"'Cash summ' not supported"));
        return null;
    }

    public boolean printString(List<TextPrint> commands) {
        setStatus(new EcrStatus(EcrStatusType.NOT_SUPPORTED,"'Print string' not supported"));
        return false;
    }

    public boolean cut() {
        setStatus(new EcrStatus(EcrStatusType.NOT_SUPPORTED,"'Cut' not supported"));
        return false;
    }

    public boolean feed(int lines) {
        setStatus(new EcrStatus(EcrStatusType.NOT_SUPPORTED,"'Feed line' not supported"));
        return false;
    }

    public String getVersionName() {
        return "unknown version";
    }
}
