package rasterize.fill;

import rasterdata.Raster;

import java.util.function.Predicate;

public interface SeedFill {

    void fill(int c, int r, int fillColor, /*Predicate<Integer> isinArea*/int backgroundColor, Raster img);
}
