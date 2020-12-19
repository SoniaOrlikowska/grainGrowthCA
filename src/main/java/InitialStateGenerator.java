import java.util.Random;

public class InitialStateGenerator {
    public static int[][] generateInitial(int mainMatrixSizeX, int mainMatrixSizeY, int grainNumber) {
        int[][] initialMatrix = new int[mainMatrixSizeX][mainMatrixSizeY];
        int[][] grainCoordinates = new int[grainNumber][2];

        for (int i = 0; i < grainNumber; i++) {
            Random random = new Random();
            int row[] = grainCoordinates[i];
            row[0] = random.nextInt(mainMatrixSizeX);
            row[1] = random.nextInt(mainMatrixSizeY);
        }
        for (int k = 0; k < grainCoordinates.length; k++) {
            int[] row = grainCoordinates[k];
            int pubX = row[0];
            int pubY = row[1];
            if (initialMatrix[pubX][pubY] == 0) initialMatrix[pubX][pubY] = k + 1;
        }
        return initialMatrix;
    }
}
