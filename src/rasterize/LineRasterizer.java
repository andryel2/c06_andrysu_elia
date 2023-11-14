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

    public abstract void drawLine(Raster raster, double x1, double y1, double x2, double y2, int color);

    public void rasterizePolygon(Line line){
        drawLine(raster, line.getX1(), line.getY1(), line.getX2(), line.getY2(), line.getColor());
    };
}



