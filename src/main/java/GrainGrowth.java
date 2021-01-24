import java.util.*;

public class GrainGrowth {
    //generuje tylko jeden następny stan
    public int[][] newStateMatrix(int[][] initialMatrix) {
        int sizeX = initialMatrix.length;
        int sizeY = initialMatrix[0].length;
        int[][] newStateMatrix = new int[sizeX][sizeY];

        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                changeInitialState(x, y, initialMatrix, newStateMatrix);
            }
        }
        return newStateMatrix;
    }

    public int[][] changeInitialState(int x, int y, int[][] initialState, int[][] afterState) {
        int mainBoardSizeX = initialState.length;
        int mainBoardSizeY = initialState[0].length;
        if (initialState[x][y] == 0) {
            afterState[x][y] = neighbourStats(x, y, mainBoardSizeX, mainBoardSizeY, initialState);
        } else {
            afterState[x][y] = initialState[x][y];
        }
        return afterState;
    }

    public int neighbourStats(int x, int y, int mainBoardSizeX, int mainBoardSizeY, int[][] mainMatrix) {
        ArrayList<int[]> filteredCoordinates = filterCoordinates(x, y, mainBoardSizeX, mainBoardSizeY); //dac bardziej sensowna nazwe na to
        ArrayList<Integer> neighboursNames = new ArrayList<>();

        for (int[] filteredCoordinate : filteredCoordinates) {
            int coordinateX = filteredCoordinate[0];
            int coordinateY = filteredCoordinate[1];
            int cellValue = mainMatrix[coordinateX][coordinateY];

            if (cellValue != 0 && cellValue != -1) neighboursNames.add(cellValue);
        }

        if (neighboursNames.size() != 1) {
            //nameCellAs = mostCommonNeighbour(neighboursNames);
            return mostCommonNeighbour(neighboursNames);
        } else {
            return neighboursNames.get(0);
        }
    }

    public ArrayList<Integer> getGrainsBoundaries(int x, int y, int mainBoardSizeX, int mainBoardSizeY, int[][] mainMatrix) {
        ArrayList<int[]> filteredCoordinates = filterCoordinates(x, y, mainBoardSizeX, mainBoardSizeY); //dac bardziej sensowna nazwe na to
        ArrayList<Integer> neighboursNames = new ArrayList<>();

        for (int[] filteredCoordinate : filteredCoordinates) {
            int coordinateX = filteredCoordinate[0];
            int coordinateY = filteredCoordinate[1];
            int cellValue = mainMatrix[coordinateX][coordinateY];

            if (cellValue != 0 && cellValue != -1) neighboursNames.add(cellValue);
        }

        return neighboursNames;
    }

    public int[][] createNeighboursCoordinates(int x, int y) {
        int[][] coordinates = {
                {x - 1, y + 1}, {x, y + 1}, {x + 1, y + 1},
                {x - 1, y}, {x + 1, y},
                {x - 1, y - 1}, {x, y - 1}, {x + 1, y - 1}
        };

        return coordinates;
    }

    //creates an ArrayList of actual cell neighborhood with fixed InitialMatrix boundaries
    public ArrayList<int[]> filterCoordinates(int x, int y, int sizeX, int sizeY) {
        int[][] coordinates = createNeighboursCoordinates(x, y);
        ArrayList<int[]> filteredCoordinates = new ArrayList<>();
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

    //creates a HashMap with neighbours and theirs counter, finds the most frequent grain, can also draw from duplicates values.
    //todo pomijac -1!!
    public int mostCommonNeighbour(ArrayList<Integer> listOfNeighbours) {
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


    public int drawFromDuplicates(Map<Integer, Integer> map) {
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


    public void printState(int[][] state) {
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[0].length; j++) {
                System.out.print(state[i][j] + " ");
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
}
