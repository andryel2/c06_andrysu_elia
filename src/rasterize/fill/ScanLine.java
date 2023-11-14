package rasterize.fill;

import model.Line;
import model.Polygon;
import rasterize.LineRasterizer;
import rasterdata.Raster;

import java.util.List;

public class ScanLine {
    void fill(Polygon polygon, Raster raster, int fillColor, int poylgonColor, LineRasterizer lineRasterizer){
//v parametru, take polygoner interface

        List<Line> lines = polygon.getLines();

    }

}
