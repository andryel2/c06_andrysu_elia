package rasterdata;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Optional;

public class RasterBufferedImage implements Raster {

    private final BufferedImage img;
    private int color;

    public BufferedImage getImg() {
        return img;
    }

    public RasterBufferedImage(int width, int height) {
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public void repaint(Graphics graphics) {
        graphics.drawImage(img, 0, 0, null);
    }

    public void draw(RasterBufferedImage raster) {
        Graphics graphics = getGraphics();
        graphics.setColor(new Color(color));
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.drawImage(raster.img, 0, 0, null);
    }

    public Optional<Integer> getColor(int c,int r){
        if(c < img.getWidth() && r < img.getHeight() && c>=0 && r>=0){
            return Optional.of(img.getRGB(c,r));
        }
        return Optional.empty();
    }

    public void setColor(int color, int c, int r) {
        if (c < img.getWidth() && r < img.getHeight() && c >= 0 && r >= 0) {
            img.setRGB(c, r, color);
        } //nechce fungovat, na cviceni boolean
    }

    public Graphics getGraphics(){
        return img.getGraphics();
    }

    @Override
    public int getPixel(int x, int y) {
        return img.getRGB(x, y);
    }

    @Override
    public void setPixel(int x, int y, int color) {
           img.setRGB(x, y, color);
    }

    @Override
    public int getColor() {
        return 0;
    }

    @Override
    public void clear() {
        Graphics g = img.getGraphics();
        g.setColor(new Color(color));
        g.clearRect(0, 0, img.getWidth() - 1, img.getHeight() - 1);
    }

    @Override
    public void setClearColor(int color) {
        this.color = color;
    }

    @Override
    public int getWidth() {
        return img.getWidth();
    }

    @Override
    public int getHeight() {
        return img.getHeight();
    }

}
