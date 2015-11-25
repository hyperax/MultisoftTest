package ru.multisoft.multisofttest.hardware;

import ru.multisoft.multisofttest.constants.Alignment;
import ru.multisoft.multisofttest.constants.Wrap;

public class TextPrint {

    private String text;

    @Wrap
    private int wrap;

    @Alignment
    private int alignment;

    public TextPrint(String text) {
        this.text = text;
        setWrap(Wrap.WRAP_WORD);
        setAlignment(Alignment.ALIGNMENT_LEFT);
    }

    @Alignment
    public int getAlignment() {
        return alignment;
    }

    public TextPrint setAlignment(@Alignment int alignment) {
        this.alignment = alignment;
        return this;
    }

    @Wrap
    public int getWrap() {
        return wrap;
    }

    public TextPrint setWrap(@Wrap int wrap) {
        this.wrap = wrap;
        return this;
    }

    public String getText() {
        return text;
    }

    public TextPrint setText(String text) {
        this.text = text;
        return this;
    }
}