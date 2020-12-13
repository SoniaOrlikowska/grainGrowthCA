import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ColorGenerator extends Canvas {
    int numberOfGrains;
    int mainMatrixSizeX;
    int mainMatrixSizeY;

    public ColorGenerator(int numberOfGrains, int mainMatrixSizeX, int mainMatrixSizeY) {
        this.numberOfGrains = numberOfGrains;
        System.out.println("DOSTAJE W COLORGENERATOR: " + numberOfGrains);
        this.mainMatrixSizeX = mainMatrixSizeX;
        this.mainMatrixSizeY = mainMatrixSizeY;
    }

    public void paint(Graphics g) {//jak zatrzymać to kurestwo?

        GrainGrowth gG = new GrainGrowth();
        HashMap<Integer, Color> colorMap = distinctColoursGenerator(numberOfGrains);
        int[][] step0 = InitialStateGenerator.generateInitial(mainMatrixSizeX, mainMatrixSizeY, numberOfGrains);
        int[][] step1;
        int matricesCount = -1;
        ArrayList<int[][]> listOfMatrices = new ArrayList<>();
        do {
            step1 = gG.newStateMatrix(step0);
            printState(step1, colorMap, g);
            listOfMatrices.add(step1);
            step0 = step1;
            matricesCount++;

            gG.printState(step0);
            System.out.println(" --------");
        } while (gG.containsZeros(step0).contains(0));

       // super.paint(g);

    }

    public void printState(int[][] stepMatrix, HashMap<Integer, Color> colorMap, Graphics g) {
        int p = 800 / stepMatrix.length;
        int q = 800 / stepMatrix[0].length;

        System.out.println("Dzien dobry ");
        for (int i = 0; i < stepMatrix.length; i++) {
            for (int j = 0; j < stepMatrix[0].length; j++) {

                int grainLabel = stepMatrix[j][i];
                if (grainLabel != 0) {
                    g.setColor(colorMap.get(grainLabel));
                    g.fillRect(p * i, q * j, p, q);
                }
            }
        }
    }

    public HashMap distinctColoursGenerator(int numberOfGrains) {
        HashMap<Integer, Color> distinctColours = new HashMap<>();
        for (int i = 0; i <= numberOfGrains; i++) {
            Color newColor = colourGenerator();
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