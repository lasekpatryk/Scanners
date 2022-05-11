package com.Style;

public enum ButtonColorsEnum {

    RED(10),
    GREEN(20),
    PINK(50);

    private final int scannerNumber;

    ButtonColorsEnum(int scannerNumber) {
        this.scannerNumber = scannerNumber;
    }

    public static String getColor(int scannerNumber) {
        ButtonColorsEnum color = RED;
        for (ButtonColorsEnum colorsEnum : values())
            if (colorsEnum.scannerNumber <= scannerNumber)
                color = colorsEnum;

        return color.toString();
    }
}
