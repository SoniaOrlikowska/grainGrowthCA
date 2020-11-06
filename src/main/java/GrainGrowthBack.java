import java.util.*;
import java.util.concurrent.TimeUnit;

public class GrainGrowthBack {
    public static void main(String[] args) throws InterruptedException {
        int[][] step0 = generateInitial(50,50,6);
        int[][] step1 = new int[step0.length][step0[0].length];
        ArrayList<Integer> zeros = new ArrayList<>();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        printState(step0);
        TimeUnit.MILLISECONDS.sleep(200);
        System.out.print("\033[H\033[2J");
        System.out.flush();
        do {
            for (int x = 0; x < step0.length; x++) {
                for (int y = 0; y < step0[0].length; y++) {
                    changeInitialState(x, y, step0, step1);
                }
            }
            printState(step1);
            step0 = step1;
            zeros = containsZeros(step0);
            TimeUnit.MILLISECONDS.sleep(900);
            System.out.print("\033[H\033[2J");
            System.out.flush();

        } while (zeros.contains(0));
        printState(step1);
    }

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
            if (initialMatrix[pubX][pubX] == 0) initialMatrix[pubX][pubY] = k + 1;
        }
        return initialMatrix;
}
    public static int[][] createCoordinates(int x, int y) {
        int[][] coordinates = {
                {x - 1, y + 1}, {x, y + 1}, {x + 1, y + 1}, {x - 1, y}, {x + 1, y}, {x - 1, y - 1}, {x, y - 1}, {x + 1, y - 1}
        };

        return coordinates;
    }

    //creates an ArrayList of actual cell neighborhood with fixed InitialMatrix boundaries
    public static ArrayList<ArrayList<Integer>> filterCoordinates(int x, int y, int[][] mainMatrix) {
        int[][] coordinates = createCoordinates(x, y);
        ArrayList<ArrayList<Integer>> filteredCoordinates = new ArrayList<>();

        int sizeX = mainMatrix.length;
        int sizeY = mainMatrix[0].length;

        for (int i = 0; i < 8; i++) {
            if (coordinates[i][0] >= 0 && coordinates[i][0] < sizeX && coordinates[i][1] >= 0 && coordinates[i][1] < sizeY) {
                ArrayList<Integer> singleCoordinate = new ArrayList<>();
                singleCoordinate.add(coordinates[i][0]);
                singleCoordinate.add(coordinates[i][1]);
                filteredCoordinates.add(singleCoordinate);
            }
        }
        return filteredCoordinates;
    }

    //creates a HashMap with neighbours and theirs counter, finds the most frequent grain, can also draw between duplicates values.
    public static int mostCommonNeighbour(ArrayList<Integer> listOfNeighbours) {
        int mostCommonNeighbour = 0;
        if (listOfNeighbours.size() != 0) {

            Map<Integer, Integer> map = new HashMap<>();

            for (Integer neighbour : listOfNeighbours) {
                Integer count = map.get(neighbour);
                map.put(neighbour, count == null ? 1 : count + 1);
            }

            int maxValueInMap = Collections.max(map.values());
            int grainDuplicatesCounter = 0;

            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() == maxValueInMap) {
                    grainDuplicatesCounter++;
                }
            }
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (grainDuplicatesCounter == 0 && entry.getValue() == maxValueInMap) {
                    mostCommonNeighbour = entry.getKey();
                }
                if (grainDuplicatesCounter != 0) mostCommonNeighbour = drawFromDuplicates(map);
            }
        }
        return mostCommonNeighbour;

    }

    public static int neighbourStats(int x, int y, int[][] mainMatrix) {
        ArrayList<ArrayList<Integer>> filteredCoordinates = filterCoordinates(x, y, mainMatrix);
        int coordinateX;
        int coordinateY;
        int nameCellAs;
        int cellValue;

        ArrayList<Integer> neighboursNames = new ArrayList<>();

        for (ArrayList<Integer> filteredCoordinate : filteredCoordinates) {
            coordinateX = filteredCoordinate.get(0);
            coordinateY = filteredCoordinate.get(1);
            cellValue = mainMatrix[coordinateX][coordinateY];

            if (cellValue != 0) neighboursNames.add(cellValue);
        }

        if (neighboursNames.size() != 1) {
            nameCellAs = mostCommonNeighbour(neighboursNames);
        } else {
            nameCellAs = neighboursNames.get(0);
        }

        return nameCellAs;
    }

    public static int drawFromDuplicates(Map<Integer, Integer> map) {
        int maxValue = Collections.max(map.values());
        int randomKey;
        ArrayList<Integer> keysForDuplicates = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == maxValue) keysForDuplicates.add(entry.getKey());
        }
        Random random = new Random();
        randomKey = keysForDuplicates.get(random.nextInt(keysForDuplicates.size()));

        return randomKey;
    }

    /*public static void changeCellName(int x, int y, int[][] initialState) {
        if (initialState[x][y] == 0) {
            initialState[x][y] = neighbourStats(x, y, initialState);
        }
    }*/

    public static int[][] changeInitialState(int x, int y, int[][] initialState, int[][] afterState) {
        if (initialState[x][y] == 0) {
            afterState[x][y] = neighbourStats(x, y, initialState);
        } else {
            afterState[x][y] = initialState[x][y];
        }
        return afterState;
    }

    public static void printState(int[][] state) {
        for (int[] ints : state) {
            for (int j = 0; j < state[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println("");
        }
    }

    public static ArrayList<Integer> containsZeros(int[][] state) {
        ArrayList<Integer> zeros = new ArrayList<>();

        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[0].length; j++) {
                zeros.add(state[i][j]);
            }
        }
        return zeros;
    }

    public static void printArrayState(ArrayList<ArrayList<Integer>> state) {
        for (ArrayList<Integer> integers : state) {
            System.out.println(integers + " ");
        }
    }
}
