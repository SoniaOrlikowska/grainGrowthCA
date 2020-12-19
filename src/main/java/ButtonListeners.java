import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                GrainGrowthFront.getInstance().getStartSimulation().setEnabled(true);
           // }
        }
    }

    public static class ClearAll implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }

        public static class DisableIncluisons implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {

                if(GrainGrowthFront.getInstance().getTypeOfInclusionsComboBox().getSelectedIndex() == 0);


            }
        }
    }
}
