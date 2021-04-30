package sample;

public class Case {

    private int originX;
    private int originY;
    private int endX;
    private int endY;
    private String colorCase;
    private String valueCase;

    public Case(int originX, int originY, int endX, int endY, String colorCase, String valueCase){
        this.originX = originX;
        this.originY = originY;
        this.endX = endX;
        this.endY = endY;
        this.colorCase = colorCase;
        this.valueCase = valueCase;
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

    public String getColorCase() {
        return colorCase;
    }

    public String getValueCase() {
        return valueCase;
    }
}
