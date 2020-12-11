import java.awt.*;
import java.util.HashMap;
import java.util.Random;

public  class ColorGenerator extends Canvas {
    public void setDraw(boolean draw) {
        this.draw = draw;
    }

    boolean draw;

    public void paint(Graphics g) {
        //super.paint(g);
        GrainGrowth gG = new GrainGrowth();
        HashMap<Integer, Color> colorMap = distinctColoursGenerator(55);
        int[][] step0 = InitialStateGenerator.generateInitial(500,500,55);
        int[][] step1;

        printState(step0, colorMap, g, draw);
        do {
            step1 = gG.newStateMatrix(step0);
            printState(step1, colorMap, g, draw);
            step0 = step1;
            System.out.println("TUTAJ");
        } while (gG.containsZeros(step0).contains(0));
    }

    public  void printState(int[][] stepMatrix, HashMap<Integer, Color> colorMap, Graphics g, boolean draw) {  //todo jeśli to jest mała macierz niech to idzie wolniej
        //if (draw) {
            System.out.println("to jest tutaj: " + draw);
            int p = 1000 / stepMatrix.length;
            int q = 1000 / stepMatrix[0].length;
            for (int i = 0; i < stepMatrix.length; i++) {
                for (int j = 0; j < stepMatrix.length; j++) {
                    if (stepMatrix[i][j] == 0) {
                        g.setColor(Color.white);
                        g.fillRect(p * i, q * j, p, q);
                    } else {
                        int grainNumber = stepMatrix[i][j];
                        if (grainNumber != 0) {
                            g.setColor(colorMap.get(grainNumber));
                            g.fillRect(p * i, q * j, p, q);
                        }
                    }
                }
            }
        }
  //  }


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