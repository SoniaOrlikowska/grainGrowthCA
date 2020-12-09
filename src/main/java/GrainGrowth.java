import java.util.*;

public class GrainGrowth {
    public static void main(String[] args) throws InterruptedException {
        InitialStateGenerator initialStateGenerator = new InitialStateGenerator();
        int[][] step0 = initialStateGenerator.generateInitial(20, 20, 3);
        printState(newStateGenerator(step0));
    }

    public static int[][] newStateGenerator(int[][] step0) {
        int[][] nextStepMatrix;
        ArrayList<Integer> zeros;
        do {
            nextStepMatrix = newStateMatrix(step0);
            zeros = containsZeros(step0);
            step0 = nextStepMatrix;
            printState(nextStepMatrix);
        } while (zeros.contains(0));

        return nextStepMatrix;
    }

    static int[][] createNeighboursCoordinates(int x, int y) {
        int[][] coordinates = {
                {x - 1, y + 1}, {x, y + 1}, {x + 1, y + 1}, {x - 1, y}, {x + 1, y}, {x - 1, y - 1}, {x, y - 1}, {x + 1, y - 1}
        };

        return coordinates;
    }

    //creates an ArrayList of actual cell neighborhood with fixed InitialMatrix boundaries
    public static ArrayList<int[]> filterCoordinates(int x, int y, int[][] mainMatrix) {
        int[][] coordinates = createNeighboursCoordinates(x, y);
        ArrayList<int[]> filteredCoordinates = new ArrayList<>();
        int sizeX = mainMatrix.length;
        int sizeY = mainMatrix[0].length;

        for (int i = 0; i < 8; i++) {
            if (coordinates[i][0] >= 0 && coordinates[i][0] < sizeX && coordinates[i][1] >= 0 && coordinates[i][1] < sizeY) {
                int[] singleCoordinate = new int[2];
                singleCoordinate[0] = coordinates[i][0];
                singleCoordinate[1] = coordinates[i][1];
                filteredCoordinates.add(singleCoordinate);
            }
        }
        return filteredCoordinates;
    }

    //creates a HashMap with neighbours and theirs counter, finds the most frequent grain, can also draw between duplicates values.
    //pomijac -1
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
        ArrayList<int[]> filteredCoordinates = filterCoordinates(x, y, mainMatrix);
        int coordinateX;
        int coordinateY;
        int nameCellAs;
        int cellValue;

        ArrayList<Integer> neighboursNames = new ArrayList<>();

        for (int[] filteredCoordinate : filteredCoordinates) {
            coordinateX = filteredCoordinate[0];
            coordinateY = filteredCoordinate[1];
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

    public static int[][] changeInitialState(int x, int y, int[][] initialState, int[][] afterState) {
        if (initialState[x][y] == 0) {
            afterState[x][y] = neighbourStats(x, y, initialState);
        } else {
            afterState[x][y] = initialState[x][y];
        }
        return afterState;
    }

    public static int[][] newStateMatrix(int[][] initialMatrix) {
        int[][] newStateMatrix = new int[initialMatrix.length][initialMatrix[0].length];
        ArrayList<Integer> zeros;
        for (int x = 0; x < initialMatrix.length; x++) {
            for (int y = 0; y < initialMatrix[0].length; y++) {
                changeInitialState(x, y, initialMatrix, newStateMatrix);
            }
        }
        initialMatrix = newStateMatrix;
        zeros = containsZeros(initialMatrix);

        return newStateMatrix;
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
