import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ButtonListeners {
    public static ColorGenerator canvas;

    public static void setColorGenerator(ColorGenerator value) {
        ButtonListeners.canvas = value;
    }


    public static class ClearSpace implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
             
        }
    }
    public static class AddBorders implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<Point> bordersCoordinates = PostInclusions.allBoundariesCoordinates(ColorGenerator.step1);
            ColorGenerator.setGrainBorderCoordinate(bordersCoordinates);
            canvas.repaint();
        }
    }
    public static class StartSimulation implements ActionListener {
        static HashMap<Integer, Point> selectedGrainsCoordinates = new HashMap<Integer, Point>();

        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField numberOfGrainsText = GrainGrowthFront.getInstance().getNumberOfGrainsText();
            int numberOfGrains = Integer.parseInt(numberOfGrainsText.getText());
            int mainMatrixSizeX = GrainGrowthFront.getInstance().getxSizeSlider().getValue();
            int mainMatrixSizeY = GrainGrowthFront.getInstance().getySizeSlider().getValue();

            ColorGenerator colorGenerator = new ColorGenerator(numberOfGrains, mainMatrixSizeX, mainMatrixSizeY);
            ButtonListeners.setColorGenerator(colorGenerator);

            if (GrainGrowthFront.showPanel.getComponents().length == 1) GrainGrowthFront.showPanel.remove(0);
            GrainGrowthFront.showPanel.add(colorGenerator);
            // if (!numberOfGrainsText.equals("") && mainMatrixSizeX != 0 && mainMatrixSizeY != 0) { //weryfikacja tego co wpisuje uzytkownik: zrobic TRIM
            colorGenerator.setSize(800, 800);
            //GrainGrowthFront.getInstance().getStartSimulation().setEnabled(true);
            SaveCanvas.setCanvas(colorGenerator);
            // }
            colorGenerator.addMouseListener(new SelectedGrainsCoordinates());

        }

    }

    public static class SelectedGrainsCoordinates extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            int x = e.getX();
            int y = e.getY();
            int[][] step1 = ColorGenerator.getStep1();
            int sizeX = step1.length;
            int sizeY = step1[0].length;
            int p = 800 / sizeX;
            int q = 800 / sizeY;
            int key = step1[x / p][y / q];
            ArrayList<Point> grainBoundsCoordinates = new ArrayList<>();
            for (int i = 1; i < step1.length - 1; i++) {
                for (int j = 1; j < step1[0].length - 1; j++) {
                    if (step1[i][j] == key && (step1[i + 1][j + 1] != key || step1[i - 1][j - 1] != key || step1[i][j - 1] != key || step1[i][j + 1] != key))
                        grainBoundsCoordinates.add(new Point(i, j));
                }
            }
            ColorGenerator.setGrainBorderCoordinate(grainBoundsCoordinates);
            canvas.repaint();
        }
    }

    public static class DisableInclusions implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField numberOfInclusions = GrainGrowthFront.getInstance().getInclusionsNumberText();
            JTextField sizeOfInclusions = GrainGrowthFront.getInstance().getInclusionSizeText();

            if (isInclusionSelected(e)) {
                numberOfInclusions.setEnabled(false);
                sizeOfInclusions.setEnabled(false);
                GrainGrowthFront.getInstance().getTimeOfInclusionsInsertComboBox().setEnabled(false);
            } else {
                numberOfInclusions.setEnabled(true);
                numberOfInclusions.setText("");
                GrainGrowthFront.getInstance().getInclusionSizeText().setEnabled(true);
                sizeOfInclusions.setText("");
                GrainGrowthFront.getInstance().getTimeOfInclusionsInsertComboBox().setEnabled(true);
            }
        }

        public boolean isInclusionSelected(ActionEvent e) {
            return e.getSource() instanceof JComboBox && GrainGrowthFront.getInstance().getTypeOfInclusionsComboBox().getSelectedIndex() == 0;
        }
    }

    public static class SaveToTxt implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            SaveToTextFile.printToFile();

            File output = new File("GrainGrowth.txt");
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.open(output);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static class SaveToBmp implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            SaveCanvas.saveCanvas();

            File output = new File("GrainGrowth.bmp");
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.open(output);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
