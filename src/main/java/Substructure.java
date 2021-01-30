import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Substructure {
    public static ArrayList<Point> substructureCoordinates;

    public static ArrayList<Point> findSubstructureSeeds() {
        int numberOfDualPhaseNucleation = 7;
        Random random = new Random();
        ArrayList<Point> dualNucleationInitCoordinates = new ArrayList<>();

        for (int i = 0; i < numberOfDualPhaseNucleation; i++) {
            System.out.println("size" + substructureCoordinates.size());
            Point point = substructureCoordinates.get(random.nextInt(substructureCoordinates.size()));
            dualNucleationInitCoordinates.add(point);
        }
        return dualNucleationInitCoordinates;
    }

    public static int[][] subStateInitialMatrix(ArrayList<Point> seeds){
        int sizeX = GrainGrowthFront.getInstance().getxSizeSlider().getValue();
        int sizeY = GrainGrowthFront.getInstance().getySizeSlider().getValue();
        int[][] subStateMatrix =  new int[sizeX][sizeY];
        int count = 1;
        for (Point point:seeds) {
            subStateMatrix[point.x][point.y] = count;
            count++;
        }
        return subStateMatrix;
    }

    public static void setSubstructureCoordinates(ArrayList<Point> substructureCoordinates) {
        Substructure.substructureCoordinates = substructureCoordinates;
    }

    public static int[][] combineSubstructureMatrices(int[][] step0, int[][] sideStep){
        for (Point point : substructureCoordinates) {
            step0[point.x][point.y] = sideStep[point.x][point.y];
        }
        return step0;
    }

    public static int[][] printSideStates(int[][] step0){
        int[][] step1;
        GrainGrowth grainGrowth = new GrainGrowth();
        do {
            step1 = grainGrowth.newStateMatrix(step0);
            step0 = step1;
        } while (grainGrowth.containsZeros(step0).contains(0));

        return step0;

    }


}
