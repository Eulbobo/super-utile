package superbad.idea.image;

import superbad.idea.string.StringSplit;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

import javax.imageio.ImageIO;

public class Julien {

    private static final String BASE_LINE = "Julien dit";

    private static final int BASE_Y = 55;

    private static final int BASE_Y_INCREMENT = 40;

    private List<String> lines;

    public Julien(String message) {
        this.lines = StringSplit.splitString(message, 22);
        if (lines.size() <= 2){
            lines.add(0, BASE_LINE);
        }
    }

    public BufferedImage writeImage() throws IOException {
        BufferedImage image = null;
        try (InputStream is = getClass().getClassLoader()
                .getResourceAsStream("ModeleJulienADit.png")) {
            image = ImageIO.read(is);
            Graphics2D g = (Graphics2D) image.getGraphics();

            RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g.setRenderingHints(rh);
            Font font = new Font("Comic Sans MS", Font.BOLD, 32);
            g.setFont(font);
            g.setColor(Color.BLACK);
            int y = BASE_Y;
            for (String line : lines){
                g.drawString(line, 290, y);
                y+=BASE_Y_INCREMENT;
            }
            g.dispose();
        }
        return image;
    }

    public static void dit(String message) throws IOException {
        ImageIO.write(new Julien(message).writeImage(), "png", Paths.get("C:", "/", "JulienDit.png").toFile()); ;
    }

    public static void main(String[] args) throws Exception {
        Julien.dit("Les idées de jeu, j'y pense quand je dors");
    }
}
