import java.util.*;

public class SquareInclusionsGenerator {
    public static void main(String[] args) {
        GrainGrowth gg = new GrainGrowth();
        int a = (int) Math.pow((4 + 1), 2) * 5;
        int b = 10 * 10;

        System.out.println("a: " + a + " b: " + b);
        gg.printState(generateRandomInclusionsStartCoordinates(2, 10, 10, 1));
        gg.printState(priorInclusionsStateMatrix(2, 10, 10, 2));

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

    public static ArrayList<int> allCoordinatesOfSingleInclusion(int x, int y, int inclusionSize, int inclusionsNumber, int initialBoardSizeX, int initialBoardSizeY) {
        int inclusionAreaSize = (int) Math.pow(inclusionSize,2);
        int[][] allCoordinatesOfSingleInclusion = new int[inclusionAreaSize][inclusionAreaSize];
        for(int i = inclusionSize; i < Math.pow(inclusionSize,2); i--){
            int[] row = new int[2];
            int newX = x + i;
            int newY = y + i;
            row[0] = newX;
            row[1] = newY;
            allCoordinatesOfSingleInclusion[i]



        }

    
    }

    public static int[][] priorInclusionsStateMatrix(int inclusionsNumber, int initialBoardSizeX, int initialBoardSizeY, int inclusionSize) {
        int[][] priorIncusionsStateMatrix = new int[initialBoardSizeX][initialBoardSizeY];
        int[][] inclusionsCoordinates = generateRandomInclusionsStartCoordinates(inclusionsNumber, initialBoardSizeX, initialBoardSizeY, inclusionSize);

        for (int i = 0; i < inclusionsCoordinates.length; i++) {
            int[] row = inclusionsCoordinates[i];
            int pubX = row[0];
            int pubY = row[1];
            priorIncusionsStateMatrix[pubX][pubY] = -1;
        }

        return priorIncusionsStateMatrix;
    }


    //czy inclusions nie moga na siebie nachodzic,jak ma sie rozmiar square do circle?
    //inclusion coordinate should be in the center of inclusion
    //before simulation
    //wez macierz zerowa sprzed wrzucenia tam grainów
    //wygeneruj n wspolrzednych gdzie beda sie znajdowac inclusions
    //*wsp. zupelnie randomowe
    //*wsp. z krawedzi
    //wez wspolrzedna i wyznacz sąsiedztwo o rozmiarze n
    //wpisz w sąsiedztwo -1


}
