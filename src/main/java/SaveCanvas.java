import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class SaveCanvas {
    public static void saveCanvas(Canvas canvas) {
        BufferedImage image=new BufferedImage(canvas.getWidth(), canvas.getHeight(),BufferedImage.TYPE_INT_RGB);
        Graphics2D g2=(Graphics2D)image.getGraphics();
        canvas.paint(g2);
        try {
            ImageIO.write(image, "png", new File("GrainGrowth.png"));
        } catch (Exception e) {

        }
    }
}
