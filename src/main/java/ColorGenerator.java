import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ColorGenerator extends Canvas {
    public static void main(String[] args) {
        JFrame boardFrame = new JFrame("Color Test");
        ColorGenerator colorGeneratorCanvas = new ColorGenerator();
        colorGeneratorCanvas.setSize(1000, 1000);
        colorGeneratorCanvas.setBackground(Color.BLACK);
        JPanel panel = new JPanel();
        panel.add(colorGeneratorCanvas);
        boardFrame.add(panel);
        boardFrame.setVisible(true);
        boardFrame.pack();
    }

    public void paint(Graphics g) {
        HashMap<Integer, Color> colorMap = distinctColoursGenerator(50); //zawsze jest o jeden grain mniej
        int[][] step0 = InitialStateGenerator.generateInitial(80, 80, 50);
        int[][] step1;

            for (int i = 0; i < step0.length; i++) {
                for (int j = 0; j < step0.length; j++) {
                    if (step0[i][j] == 0) {
                        g.setColor(Color.white);
                        g.fillRect(10 * i, 10 * j, 10, 10);
                    } else {
                        int grainNumber = step0[i][j];
                        if (grainNumber != 0) {
                            g.setColor(colorMap.get(grainNumber));
                            g.fillRect(10 * i, 10 * j, 10, 10);
                        }
                    }
                }
            }

            do{
                step1 = GrainGrowth.newStateMatrix(step0);
                for (int i = 0; i < step1.length; i++) {
                    for (int j = 0; j < step1.length; j++) {
                        if (step1[i][j] == 0) {
                            g.setColor(Color.white);
                            g.fillRect(10 * i, 10 * j, 10, 10);
                        } else {
                            int grainNumber = step1[i][j];
                            if (grainNumber != 0) {
                                g.setColor(colorMap.get(grainNumber));
                                g.fillRect(10 * i, 10 * j, 10, 10);
                            }
                        }
                    }
                }
                step0 = step1;
            }while (GrainGrowth.containsZeros(step0).contains(0));
    }

    public static HashMap distinctColoursGenerator(int numberOfGrains) {
        HashMap<Integer, Color> distinctColours = new HashMap<>();
        for (int i = 0; i < numberOfGrains; i++) {
            Color newColor = colourGenerator();
            if (!distinctColours.containsValue(newColor)) {
                distinctColours.put(i, newColor);
            } else {
            }
        }
        return distinctColours;
    }

    public static Color colourGenerator() {
        Color newColor;
        Random random = new Random();
        int R = random.nextInt(255);
        int G = random.nextInt(255);
        int B = random.nextInt(255);
        newColor = new Color(R, G, B);

        return newColor;
    }
}