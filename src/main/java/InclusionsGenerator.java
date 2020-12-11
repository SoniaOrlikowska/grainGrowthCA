import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class InclusionsGenerator {
    public InclusionsGenerator() {
    }

    public static int[][] generateRandomInclusionsCoordinates(int inclusionsNumber, int initialBoardSizeX, int initialBoardSizeY) {
        int[][] randomInclusionsCoordinates = new int[inclusionsNumber][2];
        //duplicated
        for (int i = 0; i < inclusionsNumber; i++) {
            Random random = new Random();
            int row[] = randomInclusionsCoordinates[i];
            row[0] = random.nextInt(initialBoardSizeX);
            row[1] = random.nextInt(initialBoardSizeY);
        }
        return randomInclusionsCoordinates;
    }


    //czy inclusions moga na siebie nachodzic jak ma sie rozmiar square do circle?
    //inclusion coordinate should be in the center of inclusion

    public static ArrayList<int[]> inclusionSpaceCoordinates(int x, int y, int inclusionSize) {
       /* int inclusionSpace = 1 + 2*(inclusionSize -1);
        int[][] inclusionSpaceMatrix = new int[inclusionSpace][inclusionSpace];
        size 1 = int[1 + 2*(1-1)][1] [0][0] = xy
        size 2 = int[1 + 2*(size -1)][1 + 2*1] [0][0] = [x - (size -1)][y + (size + 1)]
        size 3 = int[1 + 2*2 ][5]
        size 4 = int[1 + 2*3][7]
        //create coordinate
        //filter coordinate
        //insert coordinate to Set
        int[][] popo = GrainGrowth.createCoordinates(2,3);*/
        ArrayList<int[]> inclusionSpaceCoordinates = new ArrayList<>();
        if (inclusionSize - 1 > 0) {
           // inclusionSpaceCoordinates = GrainGrowth.filterCoordinates(x, y, new int[20][20]);
            Set<int[]> coordinatesList = new HashSet<>(inclusionSpaceCoordinates);
            inclusionSpaceCoordinates.clear();
            inclusionSpaceCoordinates.addAll(coordinatesList);

            int i = 0;
            int[] singleCoordinate = new int[2];
            singleCoordinate = inclusionSpaceCoordinates.get(i);
            int newX = singleCoordinate[0];
            int newY = singleCoordinate[1];
            i++;
            inclusionSize = inclusionSize - 1;
            inclusionSpaceCoordinates.addAll(inclusionSpaceCoordinates(newX, newY, inclusionSize));

        }

        return inclusionSpaceCoordinates;
    }


    /*public static int[][] generateRandomBorderInclusionCoordinates(int inclusionsNumber, int initialBoardSizeX, int initialBoardY){

        int[][] boarderInclusionsCoordinates = new int[inclusionsNumber][2];

        //wez maciez coordynat z krawedziami i wylosuj z nich koordynaty

        return boarderInclusionsCoordinates;

    }*/

    //before simulation
    //wez macierz zerowa sprzed wrzucenia tam grainów
    //wygeneruj n wspolrzednych gdzie beda sie znajdowac inclusions
    //*wsp. zupelnie randomowe
    //*wsp. z krawedzi
    //wez wspolrzedna i wyznacz sąsiedztwo o rozmiarze n
    //wpisz w sąsiedztwo -1


}
