package code.filters;

public class RGBUtils {

    public static int toRange(int var) {
        return Math.min(Math.max(var,0),255);
    }
    public static double toRange(double var) {
        return Math.min(Math.max(var,0),1);
    }

    public static int redComponent(int color) {
        return (color & 0x00ff0000) >> 16;
    }
    public static int greenComponent(int color) {
        return (color & 0x0000ff00) >> 8;
    }
    public static int blueComponent(int color) {
        return (color & 0x000000ff);
    }
}
