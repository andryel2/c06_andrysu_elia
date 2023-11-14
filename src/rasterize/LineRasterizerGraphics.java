package rasterize;

import rasterdata.Raster;
import rasterdata.RasterBufferedImage;

import java.awt.*;

public class LineRasterizerGraphics extends LineRasterizer {


    public LineRasterizerGraphics(Raster raster) {

        super(raster);

    }

   @Override
    public void drawLine(Raster raster, double x1, double y1, double x2, double y2, int color) {
        Graphics g = ((RasterBufferedImage)raster).getImg().getGraphics();
        g.setColor(this.color);
        g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
    }

}
