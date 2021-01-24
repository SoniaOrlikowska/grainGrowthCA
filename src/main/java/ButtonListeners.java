import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MenuListener;
import javax.xml.transform.Source;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ButtonListeners {
    public static class StartSimulation implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField numberOfGrainsText = GrainGrowthFront.getInstance().getNumberOfGrainsText();
            int numberOfGrains = Integer.parseInt(numberOfGrainsText.getText());
            int mainMatrixSizeX = GrainGrowthFront.getInstance().getxSizeSlider().getValue();
            int mainMatrixSizeY = GrainGrowthFront.getInstance().getySizeSlider().getValue();

            ColorGenerator colorGenerator = new ColorGenerator(numberOfGrains, mainMatrixSizeX, mainMatrixSizeY);
            if (GrainGrowthFront.showPanel.getComponents().length == 1) GrainGrowthFront.showPanel.remove(0);

            GrainGrowthFront.showPanel.add(colorGenerator);
            // if (!numberOfGrainsText.equals("") && mainMatrixSizeX != 0 && mainMatrixSizeY != 0) { //weryfikacja tego co wpisuje uzytkownik: zrobic TRIM
            colorGenerator.setSize(800, 800);
            //GrainGrowthFront.getInstance().getStartSimulation().setEnabled(true);
            SaveCanvas.setCanvas(colorGenerator);
            // }
        }
    }

    public static class DisableInclusions implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField numberOfInclusions = GrainGrowthFront.getInstance().getInclusionsNumberText();
            JTextField sizeOfInclusions = GrainGrowthFront.getInstance().getInclusionSizeText();

            if (isInclusionSelected(e)) {
                numberOfInclusions.setEnabled(false);
               // numberOfInclusions.setText("0");//todo byc tak nie moze
                sizeOfInclusions.setEnabled(false);
               // sizeOfInclusions.setText("0");
                GrainGrowthFront.getInstance().getTimeOfInclusionsInsertComboBox().setEnabled(false);
            } else {
                numberOfInclusions.setEnabled(true);
                numberOfInclusions.setText("");
                GrainGrowthFront.getInstance().getInclusionSizeText().setEnabled(true);
                sizeOfInclusions.setText("");
                GrainGrowthFront.getInstance().getTimeOfInclusionsInsertComboBox().setEnabled(true);
            }
        }

        public boolean isInclusionSelected(ActionEvent e){
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
