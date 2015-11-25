package ru.multisoft.multisofttest.hardware;

import android.support.annotation.IntDef;

@IntDef({EcrStatusType.UNKNOWN,
        EcrStatusType.OK,
        EcrStatusType.NOT_SUPPORTED,
        EcrStatusType.NOT_INITIALIZED,
        EcrStatusType.DRIVER_ERROR})
public @interface EcrStatusType {
    int UNKNOWN = 0;
    int OK = 1;
    int NOT_SUPPORTED = 2;
    int NOT_INITIALIZED = 3;
    int DRIVER_ERROR = 4;
}
