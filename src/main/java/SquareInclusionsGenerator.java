import java.awt.*;
import java.util.*;
import java.util.List;

public class SquareInclusionsGenerator {

    public static int[][] generateMatrixWithPriorInclusion(int inclusionSize, int numberOfInclusions, int initialBoardSizeX, int initialBoardSizeY) {
        int[][] mainBoardWithInclusions = drawAllInclusions(inclusionSize, numberOfInclusions, initialBoardSizeX, initialBoardSizeY);
        int numberOfGrainsInput = Integer.parseInt(GrainGrowthFront.getInstance().getNumberOfGrainsText().getText());
        int[][] step0 = InitialStateGenerator.generateInitial(initialBoardSizeX, initialBoardSizeY, numberOfGrainsInput);

        return addSeedsToInclusionBoard(initialBoardSizeX, initialBoardSizeY, mainBoardWithInclusions, step0);
    }

    public static int[][] addSeedsToInclusionBoard(int initialBoardSizeX, int initialBoardSizeY, int[][] inclusionsBoard, int[][] seedsBoard) {
        for (int i = 0; i < initialBoardSizeX; i++) {
            for (int j = 0; j < initialBoardSizeY; j++) {
                if (inclusionsBoard[i][j] != -1) {
                    inclusionsBoard[i][j] = seedsBoard[i][j];
                }
            }
        }
        return inclusionsBoard;
    }

    public static int[][] drawAllInclusions(int inclusionSize, int numberOfInclusions, int initialBoardSizeX, int initialBoardSizeY) {
        int[][] mainBoard = new int[initialBoardSizeX][initialBoardSizeY];
        int[][] inclusionsCoordinates = generateRandomInclusionsCoordinates(numberOfInclusions, initialBoardSizeX, initialBoardSizeY, inclusionSize);
        for (int i = 0; i < inclusionsCoordinates.length; i++) {
            int[] row = inclusionsCoordinates[i];
            int x = row[0];
            int y = row[1];

            int typeOfInclusion = GrainGrowthFront.getInstance().getTypeOfInclusionsComboBox().getSelectedIndex();
            int typeOfInclusionInsert = GrainGrowthFront.getInstance().getTimeOfInclusionsInsertComboBox().getSelectedIndex();
            switch (typeOfInclusionInsert) {
                case 0:
                    switch (typeOfInclusion) {
                        case 1:
                            drawSingleSquareInclusionOnBoard(x, y, inclusionSize, mainBoard);
                            break;
                        case 2:
                            drawSingleCircularInclusionOnBoard(x, y, inclusionSize, mainBoard);
                            break;
                        default:
                    }
                    break;
                case 1:
                    System.out.println("ELO:");
            }
        }
        return mainBoard;
    }

    public static int[][] generateRandomInclusionsCoordinates(int inclusionsNumber, int initialBoardSizeX, int initialBoardSizeY, int inclusionSize) {
        int[][] inclusionsCoordinatesArray = new int[inclusionsNumber][inclusionsNumber];
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

                if (inclusionsCoordinatesArray.length != 0) {
                    if (compareAllCoordinates(row, inclusionsCoordinatesArray, inclusionSize)) {
                        inclusionsCoordinatesArray[i] = row;
                        i++;
                    }

                } else {
                    inclusionsCoordinatesArray[0] = row;
                    i++;
                }
            }
        } else {
            System.out.println("Należy zmienić parametry inclusion size lub numberOfInclusions: dodaj message");
        }
        return inclusionsCoordinatesArray;
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

    public static int[][] drawSingleCircularInclusionOnBoard(int inclusionX, int inclusionY, int inclusionSize, int[][] mainBoard) {
        int boardSizeX = mainBoard.length;
        int boardSizeY = mainBoard[0].length;

        int radius = inclusionSize;
        for (int i = inclusionX - inclusionSize + 1; i <= inclusionX + inclusionSize - 1; i++) {
            for (int j = inclusionY - inclusionSize + 1; j <= inclusionY + inclusionSize - 1; j++) {
                double decisionParameter = Math.abs(Math.sqrt(Math.pow(inclusionX - i, 2) + Math.pow(inclusionY - j, 2)));
                if (i >= 0 && i < boardSizeX && j >= 0 && j < boardSizeY)
                    if (round(decisionParameter) < inclusionSize) mainBoard[i][j] = -1;
            }
        }
        return mainBoard;
    }

    public static double round(double number) {
        double temp = number - (int) number;
        if (temp < 0.5) return (int) number;
        return (int) number + 1;
    }

    public static int[][] drawSingleSquareInclusionOnBoard(int inclusionX, int inclusionY, int inclusionSize, int[][] mainBoard) {
        for (int i = inclusionX - inclusionSize + 1; i <= inclusionX + inclusionSize - 1; i++) {
            for (int j = inclusionY - inclusionSize + 1; j <= inclusionY + inclusionSize - 1; j++) {
                mainBoard[inclusionX][inclusionY] = -1;
                if (isPointOnBoard(i, j, mainBoard))
                    mainBoard[i][j] = -1;
            }
        }
        return mainBoard;
    }

    public static boolean isPointOnBoard(int x, int y, int[][] mainBoard) {
        boolean flag = false;
        if (x >= 0 && x < mainBoard.length && y >= 0 && y < mainBoard[0].length)
            flag = true;
        return flag;
    }

    public static void main(String[] args) {
        int[][] bord = new int[20][20];
        GrainGrowth gg = new GrainGrowth();
        gg.printState(drawSingleCircularInclusionOnBoard(10, 10, 6, bord));
        gg.printState(drawSingleSquareInclusionOnBoard(10, 10, 2, bord));

        findGrainBoundaries(ColorGenerator.lastStateMatrix);
    }

    public static List<Point> findGrainBoundaries(int[][] grainGrowthMatrix){
        List<Point> availableCoordinates = new ArrayList<>();
        int sizeX = grainGrowthMatrix.length;
        int sizeY = grainGrowthMatrix[0].length;
        GrainGrowth grainGrowth = new GrainGrowth();
        for(int i = 0; i < sizeX; i++){
            for (int j = 0; j < sizeY; j++) {
            int size = grainGrowth.getGrainsBoundaries(i, j, sizeX, sizeY, grainGrowthMatrix).size();
            if(size != 1) availableCoordinates.add(new Point(i,j));
            }
        }
        return availableCoordinates;
    }

}

