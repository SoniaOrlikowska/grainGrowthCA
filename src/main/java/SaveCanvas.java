import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class SaveCanvas {
    public static Canvas canvas;

    public static void setCanvas(Canvas value) {
        SaveCanvas.canvas = value;
    }

    public static void saveCanvas() {
        Container panel = GrainGrowthFront.showPanel;
        BufferedImage image = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();

        canvas.print(g2);
        try {
            ImageIO.write(image, "bmp", new File("GrainGrowth.bmp"));
            g2.dispose();
        } catch (Exception e) {
            System.out.println("Error with exporting the image file");
            System.out.println(e);
        }
    }
}
