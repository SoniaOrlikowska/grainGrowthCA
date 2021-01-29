import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class PostInclusions {
    public static int[][] grainsMatrix;
    public static HashMap<Integer, Color> colorMap;

    public static ArrayList<Point> allBoundariesCoordinates(int[][] grainsMatrix) {
        PostInclusions.grainsMatrix = grainsMatrix;
        ArrayList<Point> listOfAllBoundariesCoordinates = new ArrayList<>();

        for (int i = 0; i < grainsMatrix.length - 1; i++) {
            for (int j = 0; j < grainsMatrix[0].length - 1; j++) {
                if (grainsMatrix[i][j] != grainsMatrix[i + 1][j + 1] || grainsMatrix[i][j] != grainsMatrix[i][j + 1] || grainsMatrix[i][j] != grainsMatrix[i + 1][j]) {
                    listOfAllBoundariesCoordinates.add(new Point(i, j));
                }
            }
        }
        return listOfAllBoundariesCoordinates;
    }

    public static ArrayList<Point> drawPostInclusionCoordinates(int[][] grainsMatrix) {
        //coś tu nie działa czasem
        int numberOfInclusions = Integer.parseInt(GrainGrowthFront.getInstance().getInclusionsNumberText().getText());
        ArrayList<Point> listOfAllBoundariesCoordinates = allBoundariesCoordinates(grainsMatrix);
        ArrayList<Point> listOfRandomBoundInclusionsCoordinates = new ArrayList<>();
        Random random = new Random();
        int bound = listOfAllBoundariesCoordinates.size();

        for (int i = 0; i < numberOfInclusions; i++) {
            int drawnIndex = random.nextInt(bound);
            Point point = listOfAllBoundariesCoordinates.get(drawnIndex);

            listOfRandomBoundInclusionsCoordinates.add(point);
            listOfAllBoundariesCoordinates.remove(drawnIndex);
        }
        return listOfRandomBoundInclusionsCoordinates;
    }


    public static void paintPostInclusionsOnCanvas(int[][] grainsMatrix, Graphics g) {
        int chosenSizeX = grainsMatrix.length;
        int chosenSizeY = grainsMatrix[0].length;
        ArrayList<Point> randomInclusionCoordinates = drawPostInclusionCoordinates(grainsMatrix);
        int inclusionType = GrainGrowthFront.getInstance().getTypeOfInclusionsComboBox().getSelectedIndex();
        int inclusionSize = Integer.parseInt(GrainGrowthFront.getInstance().getInclusionSizeText().getText());

        if (inclusionType == 1) {
            for (Point point : randomInclusionCoordinates) {
                int p = 800 / chosenSizeX;
                int q = 800 / chosenSizeY;
                g.setColor(Color.BLACK);
                g.fillRect(p * point.x, q * point.y, inclusionSize, inclusionSize);

            }
        } else if (inclusionType == 2) {
            for (Point point : randomInclusionCoordinates) {
                int p = 800 / chosenSizeX;
                int q = 800 / chosenSizeY;
                g.setColor(Color.BLACK);
                g.fillOval(p * point.x, q * point.y, inclusionSize, inclusionSize);

            }
        }
    }

    public static void paintBordersOnCanvas(int[][] grainsMatrix, Graphics g) {
        int chosenSizeX = grainsMatrix.length;
        int chosenSizeY = grainsMatrix[0].length;
        ArrayList<Point> bordersCoordinates = allBoundariesCoordinates(grainsMatrix);
        int borderSize = GrainGrowthFront.getInstance().getBorderThicknessSlider().getValue();

        for (Point point : bordersCoordinates) {
            int p = 800 / chosenSizeX;
            int q = 800 / chosenSizeY;
            g.setColor(Color.BLACK);
            g.fillRect(p * point.x, q * point.y, borderSize, borderSize);

        }
    }

    public static void addBordersToGrainsMatrix(int[][] grainsMatrix) {
        ArrayList<Point> bordersCoordinates = allBoundariesCoordinates(grainsMatrix);
        for (Point point : bordersCoordinates) {
            grainsMatrix[point.x][point.y] = -1;
        }
        ColorGenerator.setStep1(grainsMatrix);
    }

    public static void addPostInclusionsToGrainsMatrix(int[][] grainsMatrix) {
        ArrayList<Point> postInclusionsCoordinates = drawPostInclusionCoordinates(grainsMatrix);
        for (Point point : postInclusionsCoordinates) {
            grainsMatrix[point.x][point.y] = -1;
        }
        ColorGenerator.setStep1(grainsMatrix);
    }

    public static void setGrainsMatrix(int[][] grainsMatrix) {
        PostInclusions.grainsMatrix = grainsMatrix;
    }

    public static void setColorMap(HashMap<Integer, Color> colorMap) {
        PostInclusions.colorMap = colorMap;
    }
}
