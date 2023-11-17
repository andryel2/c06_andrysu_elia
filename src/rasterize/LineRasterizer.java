package rasterize;

import model.Line;
import rasterdata.Raster;

import java.awt.*;

public abstract class LineRasterizer {
    Raster raster;
    Color color;
    public LineRasterizer(Raster raster) {
        this.raster = raster;
    }

    public abstract void drawLine(Raster raster, int x1, int y1, int x2, int y2, int color);

    public void rasterizePolygon(Line line){
        drawLine(raster, line.getX1(), line.getY1(), line.getX2(), line.getY2(), line.getColor());
    };
}



