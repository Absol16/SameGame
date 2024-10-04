package com.example.samegamefx.model;

public enum ColorEnum {
    G("green", "#7cf302"),Y("yellow", "#FFEA00EF"),P("purple", "#9f0b9f"),
    R("red", "#e30000"),B("blue", "#00D0FF"),NONE(" ", " ");
    public final String representation;
    public final String fxBgColor;
    ColorEnum(String representation, String fxBgColor) {
        this.representation = representation;
        this.fxBgColor = fxBgColor;
    }
}
