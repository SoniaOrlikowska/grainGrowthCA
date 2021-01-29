/*
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PostInclusionsTest {

    @Test
    void allBoundariesCoordinatesWorksForLastColumn() {
        //last row and column coordinates are skipped due to for, for iteration
        int[][] inputMatrix = {{1, 1, 1, 2}, {1, 1, 1, 2}, {1, 1, 1, 2}, {1, 1, 1, 2}, {1, 1, 1, 2}};
        ArrayList<Point> expected = new ArrayList<>();
        expected.add(new Point(0, 2));
        expected.add(new Point(1, 2));
        expected.add(new Point(2, 2));
        expected.add(new Point(3, 2));
        ArrayList<Point> result = PostInclusions.allBoundariesCoordinates(inputMatrix);

        assertArrayEquals(expected.toArray(), result.toArray());

    }

    @Test
    void allBoundariesCoordinatesWorksForFirstRow() {
        int[][] inputMatrix = {{2, 2, 2, 2}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}};
        ArrayList<Point> expected = new ArrayList<>();
        expected.add(new Point(0, 0));
        expected.add(new Point(0, 1));
        expected.add(new Point(0, 2));
        // expected.add(new Point(0, 3));
        ArrayList<Point> result = PostInclusions.allBoundariesCoordinates(inputMatrix);

        assertArrayEquals(expected.toArray(), result.toArray());

    }

    @Test
    void drawPostInclusionCoordinates() {
        int[][] inputMatrix = {{2, 2, 2, 2, 2}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        ArrayList<Point> expected = new ArrayList<>();
        expected.add(new Point(0, 1));
        expected.add(new Point(0, 2));
        expected.add(new Point(0, 3));

        ArrayList<Point> result = PostInclusions.drawPostInclusionCoordinates(inputMatrix);
        assertArrayEquals(expected.toArray(), result.toArray());
    }
}*/
