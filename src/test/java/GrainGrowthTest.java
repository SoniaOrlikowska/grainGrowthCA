
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GrainGrowthTest {

   @Test
   void createCoordinates() {
     //  int[][] result = GrainGrowth.createNeighboursCoordinates(3, 4);
       int[][] expected = {{2, 5}, {3, 5}, {4, 5}, {2, 4}, {4, 4}, {2, 3}, {3, 3}, {4, 3}};
       //assertArrayEquals(expected, result);
   }

   @Test
   void filterCoordinates() {
       int[][] initialMatrix = new int[3][3];
      // ArrayList<int[]> result = GrainGrowth.filterCoordinates(1, 1, initialMatrix);
       ArrayList<int[]> expected = new ArrayList<>();

       expected.add(new int[]{0, 2});
        expected.add(new int[]{1, 2});
        expected.add(new int[]{2, 2});
        expected.add(new int[]{0, 1});
        expected.add(new int[]{2, 1});
        expected.add(new int[]{0, 0});
        expected.add(new int[]{1, 0});
        expected.add(new int[]{2, 0});

       // for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < 2; j++) {
              // int[] singleResult = result.get(i);
               // int[] singleExpected = expected.get(i);
                //assertEquals(singleExpected[j], singleResult[j]);
            }
        }
    }

//}
