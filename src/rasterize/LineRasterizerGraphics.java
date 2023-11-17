package rasterize;

import rasterdata.Raster;
import rasterdata.RasterBufferedImage;

import java.awt.*;

public class LineRasterizerGraphics extends LineRasterizer {


    public LineRasterizerGraphics(Raster raster) {

        super(raster);

    }

   @Override
    public void drawLine(Raster raster, int x1, int y1, int x2, int y2, int color) {
        Graphics g = ((RasterBufferedImage)raster).getImg().getGraphics();
        g.setColor(this.color);
        g.drawLine(x1, y1, x2, y2);
    }

}
