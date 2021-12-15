package com.bomb.game.graphics;

import com.bomb.game.math.Vector2f;
import com.sun.javafx.geom.Vec2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.ArrayList;

public class Font {

    private final int TILE_SIZE = 10;
    public int w;
    public int h;
    private BufferedImage FONTSHEET = null;
    private BufferedImage[][] fontArray;
    private int wLetter;
    private int hLetter;

    public Font(String file) {
        w = TILE_SIZE;
        h = TILE_SIZE;

        System.out.println("loading: " + file + "...");
        FONTSHEET = loadFont(file);

        /*wLetter = FONTSHEET.getWidth() / w;
        hLetter = FONTSHEET.getHeight() / h;*/
        wLetter = 110 / w;
        wLetter = 120 / h;
        loadFontArray();
    }

    public Font(String file, int w, int h) {
        this.w = w;
        this.h = h;

        System.out.println("Loading:" + file + "...");
        FONTSHEET = loadFont(file);
        loadFontArray();
    }

    public static void drawArray(Graphics2D g, ArrayList<BufferedImage> img, Vector2f pos, int width, int height, int xOffset, int yOffset) {
        float x = pos.x;
        float y = pos.y;

        for (int i = 0; i < img.size(); i++) {
            if (img.get(i) != null) {
                g.drawImage(img.get(i), (int) x, (int) y, width, height, null);
            }
            x += xOffset;
            y += yOffset;
        }
    }

    public static void drawArray(Graphics2D g, Font f, String word, Vector2f pos, int width, int height, int xOffset, int yOffset) {
        float x = pos.x;
        float y = pos.y;

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != 32)
                g.drawImage(f.getFont(word.charAt(i)), (int) x, (int) y, width, height, null);
            x += xOffset;
            y += yOffset;
        }
    }

    public void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public int getWidth() {
        return w;
    }

    public void setWidth(int _w) {
        w = _w;
        wLetter = FONTSHEET.getWidth() / w;
    }

    public int getHeight() {
        return h;
    }

    public void setHeight(int _h) {
        h = _h;
        hLetter = FONTSHEET.getHeight() / h;
    }

    private BufferedImage loadFont(String file) {
        BufferedImage font = null;
        try {
            font = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
        } catch (Exception e) {
            System.out.println("ERROR: could not load file: " + file);
        }

        return font;
    }

    public void loadFontArray() {
        fontArray = new BufferedImage[wLetter][hLetter];

        for (int x = 0; x < wLetter; x++) {
            for (int y = 0; y < hLetter; y++) {
                fontArray[x][y] = getLetter(x, y);
            }
        }
    }

    public BufferedImage getFONTSHEET() {
        return FONTSHEET;
    }

    public BufferedImage getLetter(int x, int y) {
        BufferedImage img = FONTSHEET.getSubimage(x * w, y * h, w, h);
        return img;
    }

    public BufferedImage getFont(char letter) throws ArithmeticException {
        int value = letter;
        int x = value % 11;
        int y = value / 11;
        return getLetter(x, y);
    }
}