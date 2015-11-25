package ru.multisoft.multisofttest.constants;

import android.support.annotation.IntDef;

@IntDef({Wrap.WRAP_LINE, Wrap.WRAP_NONE, Wrap.WRAP_WORD})
public @interface Wrap {
    int WRAP_NONE = 0;
    int WRAP_LINE = 1;
    int WRAP_WORD = 2;
}
