package com.example.samegamefx.model;

import java.util.List;

public enum Difficulty {
    EASY(List.of(ColorEnum.B, ColorEnum.R, ColorEnum.Y)),
    MEDIUM(List.of(ColorEnum.B, ColorEnum.R, ColorEnum.Y, ColorEnum.G)),
    HARD(List.of(ColorEnum.B, ColorEnum.R, ColorEnum.Y, ColorEnum.G, ColorEnum.P));
    public final List<ColorEnum> colors;

    Difficulty(List<ColorEnum> colors) {
        this.colors = colors;
    }
}
