import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListeners {
    public static class StartSimulation implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //todo clear canvas
            ColorGenerator colorGenerator = new ColorGenerator();
            colorGenerator.setSize(1000, 1000);
            GrainGrowthFront.showPanel.add(colorGenerator);
        }
    }

    public static class ClearAll implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}