import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquareInclusionsGeneratorTest {

    @Test
    void compareCoordinates() {
        int[] newCoordinate1 = {8, 8};
        int[] newCoordinate2 = {1, 1};
        int[] newCoordinate3 = {4, 5};
        int[] newCoordinate4 = {6, 1};
        int[] newCoordinate5 = {7, 7};
        int[] newCoordinate6 = {0, 0};
        int[] existingCoordinate = {1, 1};
        int size = 3;

        boolean result1 = SquareInclusionsGenerator.compareCoordinates(newCoordinate1, existingCoordinate, size);
        boolean result2 = SquareInclusionsGenerator.compareCoordinates(newCoordinate2, existingCoordinate, size);
        boolean result3 = SquareInclusionsGenerator.compareCoordinates(newCoordinate3, existingCoordinate, size);
        boolean result4 = SquareInclusionsGenerator.compareCoordinates(newCoordinate4, existingCoordinate, size);
        boolean result5 = SquareInclusionsGenerator.compareCoordinates(newCoordinate5, existingCoordinate, size);
        boolean result6 = SquareInclusionsGenerator.compareCoordinates(newCoordinate6, existingCoordinate, size);
        boolean expected1 = true;
        boolean expected2 = false;
        boolean expected3 = false;
        boolean expected4 = true;
        boolean expected5 = true;
        boolean expected6 = false;
        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
        assertEquals(expected3, result3);
        assertEquals(expected4, result4);
        assertEquals(expected5, result5);
        assertEquals(expected6, result6);
    }

    @Test
    void compareAllCoordinates() {
        int[] newCoordinate1 = {1, 1};
        int[][] existingCoordinates = {{8, 8}, {6, 1}, {7, 7}};
        int size = 3;
        boolean result = SquareInclusionsGenerator.compareAllCoordinates(newCoordinate1, existingCoordinates, size);
        boolean expected = true;
        assertEquals(expected, result);

    }
}
