import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ColorGenerator extends Canvas {
    int numberOfGrains;
    int mainMatrixSizeX;
    int mainMatrixSizeY;
    int[][] step0;
    HashMap<Integer, Color> colorMap;
    boolean runOnce;

    public ColorGenerator(int numberOfGrains, int mainMatrixSizeX, int mainMatrixSizeY) {
        this.numberOfGrains = numberOfGrains;
        this.mainMatrixSizeX = mainMatrixSizeX;
        this.mainMatrixSizeY = mainMatrixSizeY;
        this.step0 = new int[this.mainMatrixSizeX][this.mainMatrixSizeY];
        this.runOnce = false;
    }

    public void paint(Graphics g) {
        // super.paint(g); // Not needed it seems

        GrainGrowthFront grainGrowthFront = GrainGrowthFront.getInstance();
        // GrainGrowth gg = new GrainGrowth();
        int matrixSizeX = grainGrowthFront.getxSizeSlider().getValue();
        int matrixSizeY = grainGrowthFront.getySizeSlider().getValue();
        int grainNumber = Integer.parseInt(grainGrowthFront.getNumberOfGrainsText().getText());
        //int inclusionSize = Integer.parseInt(grainGrowthFront.getInclusionSizeText().getText());
        if (this.colorMap == null) {
            this.colorMap = distinctColoursGenerator(numberOfGrains);
        }

        int numberOfInclusions = Integer.parseInt("0".concat(grainGrowthFront.getInclusionsNumberText().getText()));
        int sizerOfInclusions = Integer.parseInt("0".concat(grainGrowthFront.getInclusionSizeText().getText()));

        // The paint method is run once when the simulation is started.
        // Later this method can be ran again when the user wants to export a text file or an image.
        // In order to export the same matrix as was created previously we have saved the matrix as an instance variable "this.step0".
        // We use a boolean variable "this.runOnce" to check if the method has already been ran.

        // Check if this instance of ColorGenerator has already been run
        if (runOnce) {
            // If so, then use the previously created matrix.
            System.out.println("The paint method was already ran");
            printAllStatesButWithoutList(this.step0, colorMap, g);
        } else {
            if (isNoInclusionSelected()) {
                System.out.println("No inclusions selected");
                this.step0 = InitialStateGenerator.generateInitial(matrixSizeX, matrixSizeY, grainNumber);
                printAllStatesButWithoutList(this.step0, colorMap, g);
            } else {
                System.out.println("Inclusions are included");
                if (isPriorInclusionSelected()) {
                    // SquareInclusionsGenerator creates both square and circle
                    System.out.println("Prior inclusions selected");
                    this.step0 = SquareInclusionsGenerator.generateMatrixWithPriorInclusion(sizerOfInclusions, numberOfInclusions, matrixSizeX, matrixSizeY);
                    printAllStates(this.step0, colorMap, g);
                } else if (isPostInclusionSelected()) {
                    System.out.println("Post inclusions selected");
                    printAllStates(this.step0, colorMap, g);


                    List<Point> listOfBoundariesPoints = SquareInclusionsGenerator.findGrainBoundaries(lastStateMatrix);
                    List<Point> tempList = getPostInclusionsCoordinates(numberOfInclusions, listOfBoundariesPoints);
                    g.setColor(Color.BLACK);

                    for (Point point : tempList) {
                        g.fillRect(point.x, point.y, 1, 1);
                    }
                }
            }
        }
        this.runOnce = true;
        SaveToTextFile.setOutputMatrix(this.step0);
    }

    //conditions used
    public static boolean isNoInclusionSelected() {
        boolean flag = false;
        JComboBox typeOfInclusionComboBox = GrainGrowthFront.getInstance().getTypeOfInclusionsComboBox();
        if (typeOfInclusionComboBox.getSelectedIndex() == 0) flag = true;
        return flag;
    }

    public static boolean isPriorInclusionSelected() {
        boolean flag = false;
        JComboBox timeOfInclusionsInsertComboBox = GrainGrowthFront.getInstance().getTimeOfInclusionsInsertComboBox();
        if (timeOfInclusionsInsertComboBox.getSelectedIndex() == 0) flag = true;
        System.out.println("prior " + timeOfInclusionsInsertComboBox.getSelectedIndex());
        return flag;
    }

    public static boolean isPostInclusionSelected() {
        boolean flag = false;
        JComboBox timeOfInclusionsInsertComboBox = GrainGrowthFront.getInstance().getTimeOfInclusionsInsertComboBox();
        if (timeOfInclusionsInsertComboBox.getSelectedIndex() == 1) flag = true;
        System.out.println("post " + timeOfInclusionsInsertComboBox.getSelectedIndex());
        return flag;
    }

    public static Point getRandomBoundaryCoordinate(List<Point> list) {
        Random random = new Random();
        int temp = random.nextInt(list.size());
        Point point = list.get(temp);
        list.remove(temp);
        return point;
    }

    public static List<Point> getPostInclusionsCoordinates(int numberOfInclusions, List<Point> listOfAviablePoints) {
        List<Point> list = new ArrayList<>();

        for (int i = 0; i < numberOfInclusions; i++) {
            list.add(getRandomBoundaryCoordinate(listOfAviablePoints));
        }
        return list;
    }


    static int[][] lastStateMatrix;

    public static int[][] printAllStates(int[][] step0, HashMap<Integer, Color> colorMap, Graphics g) {
        int[][] step1;
        GrainGrowth grainGrowth = new GrainGrowth();
        ArrayList<int[][]> listOfMatrices = new ArrayList<>();
        int matricesCount = -1;
        do {
            step1 = grainGrowth.newStateMatrix(step0);
            printState(step1, colorMap, g);
            listOfMatrices.add(step1);
            step0 = step1;
            matricesCount++;
        } while (GrainGrowth.containsZeros(step0).contains(0));
        lastStateMatrix = listOfMatrices.get(matricesCount);
        return listOfMatrices.get(matricesCount);

    }

    public static int[][] printAllStatesButWithoutList(int[][] step0, HashMap<Integer, Color> colorMap, Graphics g) {
        int[][] step1;
        GrainGrowth grainGrowth = new GrainGrowth();
        do {
            step1 = grainGrowth.newStateMatrix(step0);
            printState(step1, colorMap, g);
            step0 = step1;
        } while (grainGrowth.containsZeros(step0).contains(0));

        return step0;
    }

    public static void printState(int[][] stepMatrix, HashMap<Integer, Color> colorMap, Graphics g) {
        int p = 800 / stepMatrix.length;
        int q = 800 / stepMatrix[0].length;

        for (int i = 0; i < stepMatrix.length; i++) {
            for (int j = 0; j < stepMatrix[0].length; j++) {
                int grainLabel = stepMatrix[i][j];
                if (grainLabel != 0) {
                    g.setColor(colorMap.get(grainLabel));
                    g.fillRect(p * i, q * j, p, q);
                }
            }
        }
    }

    public HashMap distinctColoursGenerator(int numberOfGrains) {
        HashMap<Integer, Color> distinctColours = new HashMap<>();
        for (int i = 0; i <= numberOfGrains + 1; i++) {
            Color newColor = colourGenerator();
            distinctColours.put(-1, Color.BLACK);
            if (!distinctColours.containsValue(newColor)) {
                distinctColours.put(i, newColor);
            } else {//co w przypadku zduplikowania koloru?
            }
        }
        return distinctColours;
    }

    public Color colourGenerator() {
        Color newColor;
        Random random = new Random();
        int R = random.nextInt(255);
        int G = random.nextInt(255);
        int B = random.nextInt(255);
        newColor = new Color(R, G, B);

        return newColor;
    }
}
