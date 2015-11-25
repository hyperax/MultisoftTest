package ru.multisoft.multisofttest.hardware;

import android.support.annotation.NonNull;

import ru.multisoft.multisofttest.helpers.NpeUtils;

public class EcrStatus {

    @EcrStatusType
    private int type;
    private String message;

    public EcrStatus() {
        setType(EcrStatusType.UNKNOWN);
        setMessage(null);
    }

    public EcrStatus(@EcrStatusType int type, String message) {
        setType(type);
        setMessage(message);
    }

    @EcrStatusType
    public int getType() {
        return type;
    }

    public EcrStatus setType(@EcrStatusType int type) {
        this.type = type;
        return this;
    }

    @NonNull
    public String getMessage() {
        return NpeUtils.getNonNull(message);
    }

    public EcrStatus setMessage(String message) {
        this.message = message;
        return this;
    }
}
