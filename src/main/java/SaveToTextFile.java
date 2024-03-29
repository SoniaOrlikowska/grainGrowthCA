import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveToTextFile {
   // public static int[][] outputMatrix;

   /* public static void setOutputMatrix(int[][] value) {
        SaveToTextFile.outputMatrix = value;
    }*/

    public static void printToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("GrainGrowth.txt")));

            for (int i = 0; i < ColorGenerator.step1.length; i++) {
                for (int j = 0; j < ColorGenerator.step1[0].length; j++) {
                    writer.write(ColorGenerator.step1[i][j] + " ");
                }
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException e){
            System.out.println("Error creating text export");
            System.out.println(e);
        }
    }
}

