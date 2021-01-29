/*
import java.awt.*;
import java.util.ArrayList;

public class ManageBorders {
    public static int[][] outputMatrix;
    public static Graphics g;

    public static void setOutputMatrix(int[][] value) { ManageBorders.outputMatrix = value; }

    public static void setGraphics(Graphics value) {
        ManageBorders.g = value;
    }

    public static void drawBorders() {
        int chosenSizeX = outputMatrix.length;
        int chosenSizeY = outputMatrix[0].length;

        ArrayList<Point> points = PostInclusions.allBoundariesCoordinates(outputMatrix);

        System.out.println("hello");
        System.out.println(g);

        g.setColor(Color.BLACK);
        g.fillRect(5, 5, 10, 10); // why this does not draw anything :(

        for (Point point : points) {
            int p = 800 / chosenSizeX;
            int q = 800 / chosenSizeY;

            g.setColor(Color.BLACK);
            g.fillRect(p * point.x, q * point.y, 10, 10);
        }
    }
}*/
