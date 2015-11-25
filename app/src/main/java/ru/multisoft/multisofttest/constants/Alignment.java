package ru.multisoft.multisofttest.constants;

import android.support.annotation.IntDef;

@IntDef({Alignment.ALIGNMENT_CENTER, Alignment.ALIGNMENT_LEFT, Alignment.ALIGNMENT_RIGHT})
public @interface Alignment {
    int ALIGNMENT_LEFT = 0;
    int ALIGNMENT_CENTER = 1;
    int ALIGNMENT_RIGHT = 2;
}
