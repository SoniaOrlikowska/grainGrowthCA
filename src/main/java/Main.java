import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        GrainGrowthFront grainGrowthFront = new GrainGrowthFront();
        InitialStateGenerator initialStateGenerator = new InitialStateGenerator();
        grainGrowthFront.setFrameLayout();

        GrainGrowth grainGrowth = new GrainGrowth();

        int[][] step0 = initialStateGenerator.generateInitial(200, 200, 25);


    }
}
