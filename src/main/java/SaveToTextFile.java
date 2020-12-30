import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveToTextFile {
    public static void printToFile(int[][] outputMatrix) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("GrainGrowth.txt")));

        for (int i = 0; i < outputMatrix.length; i++) {
            for (int j = 0; j < outputMatrix[0].length; j++) {
                writer.write(outputMatrix[i][j] + " ");
            }
            writer.newLine();
        }
        writer.flush();
        writer.close();
    }
}

