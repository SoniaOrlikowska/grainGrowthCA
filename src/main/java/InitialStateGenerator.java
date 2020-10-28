import java.util.Random;

public class InitialStateGenerator {

    int mainMatrixSizeX;
    int mainMatrixSizeY;
    int grainNumber;

    public static void main(String[] args) {
        InitialStateGenerator initialStateGenerator = new InitialStateGenerator(20, 15, 7);
    }

    public InitialStateGenerator(int mainMatrixSizeX, int mainMatrixSizeY, int grainNumber) {

        this.mainMatrixSizeX = mainMatrixSizeX;
        this.mainMatrixSizeY = mainMatrixSizeY;
        this.grainNumber = grainNumber;

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

            initialMatrix[pubX][pubY] = k + 1;
        }

        for (int l = 0; l < mainMatrixSizeX; l++) {
            for (int h = 0; h < mainMatrixSizeY; h++) {

                System.out.print(initialMatrix[l][h] + " ");

            }

            System.out.println(" ");
        }

    }
}
