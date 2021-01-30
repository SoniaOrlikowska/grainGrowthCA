import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ColorGenerator extends Canvas {
    int numberOfGrains;
    int mainMatrixSizeX;
    int mainMatrixSizeY;
    boolean isMainGenerator;
    int[][] step0;
    int[][] sideStep;

    static int[][] step1;
    static HashMap<Integer, Color> colorMap;
    static HashMap<Integer, Color> blackAndWhiteMap;
    static boolean runOnce;

    public static void reset() {
        runOnce = false;
        colorMap = null;
    }

    public static void setGrainBorderCoordinate(ArrayList<Point> grainBorderCoordinate) {
        for (Point point : grainBorderCoordinate) {
            step1[point.x][point.y] = -1;
        }
    }

    public ColorGenerator(int numberOfGrains, int mainMatrixSizeX, int mainMatrixSizeY, boolean isMainGenerator) {
        this.isMainGenerator  = isMainGenerator;
        this.numberOfGrains = numberOfGrains;
        this.mainMatrixSizeX = mainMatrixSizeX;
        this.mainMatrixSizeY = mainMatrixSizeY;
        this.step0 = new int[this.mainMatrixSizeX][this.mainMatrixSizeY];
        step1 = new int[this.mainMatrixSizeX][this.mainMatrixSizeY];
        runOnce = false;
    }

    public void paint(Graphics g) {
        GrainGrowthFront grainGrowthFront = GrainGrowthFront.getInstance();

        int matrixSizeX = grainGrowthFront.getxSizeSlider().getValue();
        int matrixSizeY = grainGrowthFront.getySizeSlider().getValue();
        int grainNumber = Integer.parseInt(grainGrowthFront.getNumberOfGrainsText().getText());
        if (colorMap == null) {
            colorMap = distinctColoursGenerator(numberOfGrains);
            blackAndWhiteMap = cleanColoursGenerator(numberOfGrains);
        }

        int numberOfInclusions = Integer.parseInt("0".concat(grainGrowthFront.getInclusionsNumberText().getText()));
        int sizerOfInclusions = Integer.parseInt("0".concat(grainGrowthFront.getInclusionSizeText().getText()));

        // The paint method is run once when the simulation is started.
        // Later this method can be ran again when the user wants to export a text file or an image.
        // In order to export the same matrix as was created previously we have saved the matrix as an instance variable "this.step0".
        // We use a boolean variable "this.runOnce" to check if the method has already been ran.
        // Check if this instance of ColorGenerator has already been run
        if(isMainGenerator) {
            if (this.runOnce) {
                // If so, then use the previously created matrix.
                System.out.println("The paint method was already ran");
                printState(this.step1, colorMap, g);
            } else {
                if (isNoInclusionSelected()) {
                    this.step0 = InitialStateGenerator.generateInitial(matrixSizeX, matrixSizeY, grainNumber);
                    System.out.println("hello");
                    this.step0 = printAllStates(this.step0, colorMap, g);
                    step1 = this.step0;
                } else {
                    if (isPriorInclusionSelected()) {
                        this.step0 = SquareInclusionsGenerator.generateMatrixWithPriorInclusion(sizerOfInclusions, numberOfInclusions, matrixSizeX, matrixSizeY);
                        printAllStates(this.step0, colorMap, g);
                    } else if (isPostInclusionSelected()) {
                        this.step0 = InitialStateGenerator.generateInitial(matrixSizeX, matrixSizeY, grainNumber);
                        this.step0 = printAllStates(this.step0, colorMap, g);
                        PostInclusions.paintPostInclusionsOnCanvas(this.step0, g);
                        PostInclusions.addPostInclusionsToGrainsMatrix(this.step0);
                    }
                    step1 = this.step0;
                }

                this.runOnce = true;
            }
        }
        else {



        }

    }

    /*public static void paintGrainBorderOnCanvas(int[][] grainsMatrix, Graphics g, ArrayList<Point> grainBorderCoordinates) {
        int chosenSizeX = grainsMatrix.length;
        int chosenSizeY = grainsMatrix[0].length;
        int borderSize = GrainGrowthFront.getInstance().getBorderThicknessSlider().getValue();

        for (Point point : grainBorderCoordinates) {
            int p = 800 / chosenSizeX;
            int q = 800 / chosenSizeY;
            g.setColor(Color.BLACK);
            g.fillRect(p * point.x, q * point.y, borderSize, borderSize);

        }
    }*/


    public static int[][] printAllStates(int[][] step0, HashMap<Integer, Color> colorMap, Graphics g) {
        int[][] step1;
        GrainGrowth grainGrowth = new GrainGrowth();
        do {
            step1 = grainGrowth.newStateMatrix(step0);
            printState(step1, colorMap, g);
            step0 = step1;
        } while (grainGrowth.containsZeros(step0).contains(0));

        PostInclusions.setColorMap(colorMap);
        PostInclusions.setGrainsMatrix(step0);
        return step0;
    }

    public static void printState(int[][] stepMatrix, HashMap<Integer, Color> colorMap, Graphics g) {
        int p = 800 / stepMatrix.length;
        int q = 800 / stepMatrix[0].length;
        int borderSize = GrainGrowthFront.getInstance().borderThicknessSlider.getValue();
        System.out.println("border size " + borderSize);
        for (int i = 0; i < stepMatrix.length; i++) {
            for (int j = 0; j < stepMatrix[0].length; j++) {
                int grainLabel = stepMatrix[i][j];
                if (grainLabel > 0) {
                    g.setColor(colorMap.get(grainLabel));
                    g.fillRect(p * i, q * j, p, q);
                }
                if (grainLabel == -1) {
                    g.setColor(Color.BLACK);
                    g.fillRect(p * i, q * j, borderSize, borderSize);
                }
            }
        }
    }

    public static void paintOnlyBorders() {
        colorMap = blackAndWhiteMap;
    }

    public HashMap cleanColoursGenerator(int numberOfGrains) {
        HashMap<Integer, Color> distinctColours = new HashMap<>();
        distinctColours.put(-1, Color.BLACK);

        for (int i = 0; i <= numberOfGrains + 1; i++) {
            distinctColours.put(i, Color.WHITE);
        }

        return distinctColours;
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
        return flag;
    }

    public static boolean isPostInclusionSelected() {
        boolean flag = false;
        JComboBox timeOfInclusionsInsertComboBox = GrainGrowthFront.getInstance().getTimeOfInclusionsInsertComboBox();
        if (timeOfInclusionsInsertComboBox.getSelectedIndex() == 1) flag = true;
        return flag;
    }

    public static void setStep1(int[][] step1) {
        ColorGenerator.step1 = step1;
    }

    public static int[][] getStep1() {
        return step1;
    }
}
