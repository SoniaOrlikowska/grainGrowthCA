import java.util.*;

public class SquareInclusionsGenerator {

    public static int[][] generateMatrixWithPriorInclusion(int inclusionSize, int numberOfInclusions, int initialBoardSizeX, int initialBoardSizeY) {
        int[][] mainBoard = new int[initialBoardSizeX][initialBoardSizeY];
        int[][] inclusionsCoordinates = generateRandomInclusionsStartCoordinates(numberOfInclusions, initialBoardSizeX, initialBoardSizeY, inclusionSize);

        for (int i = 0; i < inclusionsCoordinates.length; i++) {
            int[] row = inclusionsCoordinates[i];
            int x = row[0];
            int y = row[1];
            addPriorSingleInclusionToMatrix(x, y, inclusionSize, mainBoard);
        }
        int[][] step0 = InitialStateGenerator.generateInitial(initialBoardSizeX,initialBoardSizeY,Integer.parseInt(GrainGrowthFront.getInstance().getNumberOfGrainsText().getText()));
        for(int i = 0; i < initialBoardSizeX; i++){
            for(int j = 0; j < initialBoardSizeY;j++) {

                if (mainBoard[i][j] != -1) {
                    mainBoard[i][j] = step0[i][j];
                }
            }

        }
        return mainBoard;
    }

    public static int[][] addPriorSingleInclusionToMatrix(int inclusionX, int inclusionY, int inclusionSize, int[][] mainBoard) {
        for (int i = inclusionX - inclusionSize + 1; i <= inclusionX + inclusionSize - 1; i++) {
            for (int j = inclusionY - inclusionSize + 1; j <= inclusionY + inclusionSize - 1; j++) {
                mainBoard[inclusionX][inclusionY] = -1;
                if (i >= 0 && i < mainBoard.length && j >= 0 && j < mainBoard[0].length)
                    mainBoard[i][j] = -1;
            }
        }
        return mainBoard;
    }

    public static boolean compareCoordinates(int[] newCoordinate, int[] existingInclusionCoordinate, int inclusionSize) {
        boolean coordinateOk = false;

        if (existingInclusionCoordinate.length != 0) {
            int rule = 2 * inclusionSize - 1;
            int x = newCoordinate[0];
            int y = newCoordinate[1];

            if (Math.abs(x - existingInclusionCoordinate[0]) >= rule || Math.abs(y - existingInclusionCoordinate[1]) >= rule) {
                coordinateOk = true;
            }
        }
        return coordinateOk;
    }

    public static boolean compareAllCoordinates(int[] newCoordinate, int[][] inclusionsCoordinatesArray, int inclusionSize) {
        boolean allCoordinatesOK = false;
        int count = 0;
        for (int i = 0; i < inclusionsCoordinatesArray.length; i++) {
            int[] row = inclusionsCoordinatesArray[i];
            if (!compareCoordinates(newCoordinate, row, inclusionSize)) {
                count++;
            }
        }
        if (count == 0) allCoordinatesOK = true;
        return allCoordinatesOK;
    }

    public static int[][] generateRandomInclusionsStartCoordinates(int inclusionsNumber, int initialBoardSizeX, int initialBoardSizeY, int inclusionSize) {
        int[][] inclusionsStartCoordinatesArray = new int[inclusionsNumber][inclusionsNumber];

        int a = (int) Math.pow((inclusionSize + 1), 2) * inclusionsNumber;
        int b = initialBoardSizeX * initialBoardSizeY;

        if (a <= b) {
            for (int i = 0; i < inclusionsNumber; ) {
                Random random = new Random();
                int[] row = new int[2];
                int x = random.nextInt(initialBoardSizeX);
                int y = random.nextInt(initialBoardSizeY);
                row[0] = x;
                row[1] = y;

                if (inclusionsStartCoordinatesArray.length != 0) {
                    if (compareAllCoordinates(row, inclusionsStartCoordinatesArray, inclusionSize)) {
                        inclusionsStartCoordinatesArray[i] = row;
                        i++;
                    }

                } else {
                    inclusionsStartCoordinatesArray[0] = row;
                    i++;
                }
            }
        } else {
            System.out.println("Należy zmienić parametry inclusion size lub numberOfInclusions: dodaj message");
        }
        return inclusionsStartCoordinatesArray;
    }
}
