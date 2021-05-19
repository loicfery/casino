package sample;

import games.ColorRoulette;

public class CellRoulette {

    private int originX;
    private int originY;
    private int endX;
    private int endY;
    private ColorRoulette colorRouletteCase;
    private String valueCase;

    public CellRoulette(int originX, int originY, int endX, int endY, ColorRoulette colorRouletteCase, String valueCase){
        this.originX = originX;
        this.originY = originY;
        this.endX = endX;
        this.endY = endY;
        this.colorRouletteCase = colorRouletteCase;
        this.valueCase = valueCase;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "color=" + colorRouletteCase +
                ", number=" + valueCase +
                '}';
    }

    public int getOriginX() {
        return originX;
    }

    public int getOriginY() {
        return originY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

    public ColorRoulette getColorCase() {
        return colorRouletteCase;
    }

    public String getValueCase() {
        return valueCase;
    }
}
