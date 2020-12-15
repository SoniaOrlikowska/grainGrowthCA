import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListeners {
    public static class StartSimulation implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField numberOfGrainsText = GrainGrowthFront.getInstance().getNumberOfGrainsText(); //lepiej static czy getInstance()?
            System.out.println("ELO number " + numberOfGrainsText.getText());
            JTextField mainMatrixSizeXText = GrainGrowthFront.getInstance().getxText();
            JTextField mainMatrixSizeYText = GrainGrowthFront.getInstance().getyText();
            int numberOfGrains = Integer.parseInt(numberOfGrainsText.getText());
            int mainMatrixSizeX = Integer.parseInt(mainMatrixSizeXText.getText());
            int mainMatrixSizeY = Integer.parseInt(mainMatrixSizeYText.getText());

            ColorGenerator colorGenerator = new ColorGenerator(numberOfGrains, mainMatrixSizeX, mainMatrixSizeY);

            if (GrainGrowthFront.showPanel.getComponents().length == 1) GrainGrowthFront.showPanel.remove(0);
            GrainGrowthFront.showPanel.add(colorGenerator);

            if (!numberOfGrainsText.equals("") && !mainMatrixSizeXText.equals("") && !mainMatrixSizeYText.equals("")) { //weryfikacja tego co wpisuje uzytkownik do TRIM
                colorGenerator.setSize(800, 800);

                GrainGrowthFront.getInstance().getStartSimulation().setEnabled(true);
            }
        }

    }

    public static class ClearAll implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}