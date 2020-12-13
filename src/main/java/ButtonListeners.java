import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListeners {
    public static class StartSimulation implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //todo clear canvas before anything
           //GrainGrowthFront.showPanel.add((Component) null);

            JTextField numberOfGrainsText = GrainGrowthFront.getInstance().getNumberOfGrainsText(); //lepiej static czy getInstance()?
            System.out.println("ELO number " + numberOfGrainsText.getText());
            JTextField mainMatrixSizeXText = GrainGrowthFront.getInstance().getxText();
            JTextField mainMatrixSizeYText = GrainGrowthFront.getInstance().getyText();
            int numberOfGrains = Integer.parseInt(numberOfGrainsText.getText());
            int mainMatrixSizeX = Integer.parseInt(mainMatrixSizeXText.getText());
            int mainMatrixSizeY = Integer.parseInt(mainMatrixSizeYText.getText());

            ColorGenerator colorGenerator = new ColorGenerator(numberOfGrains, mainMatrixSizeX, mainMatrixSizeY);
           // GrainGrowthFront.showPanel.remove(0);
            GrainGrowthFront.showPanel.add(colorGenerator);

            //colorGenerator.update(colorGenerator.getGraphics());

            System.out.println("KOLOR");
            //colorGenerator.print(colorGenerator.getGraphics());
            if (!numberOfGrainsText.equals("") && !mainMatrixSizeXText.equals("") && !mainMatrixSizeYText.equals("")) {
                colorGenerator.setSize(800, 800);

                GrainGrowthFront.getInstance().getStartSimulation().setEnabled(true);
            }
            //(colorGenerator.getGraphics());
        }

    }

    public static class ClearAll implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}