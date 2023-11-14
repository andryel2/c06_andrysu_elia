package rasterize.fill;

import rasterdata.Raster;

import java.util.function.Predicate;

public class SeedFill8 implements SeedFill{
    @Override
    public void fill(int c, int r, int fillColor, int backgroundColor, Raster img) {

    }

    /* @Override
    public void fill(int c, int r, int fillColor, Predicate<Integer> isInArea, Raster img) {
        if(img.getColor(c,r).isPresent()){
            int currentColor = img.getColor(c,r).get();

        if(isInArea.test(currentColor)){
            img.setPixel(c,r,fillColor);

            //volani funkce rekurzivně pro posunuté hodnoty c r do 8 směrů

            fill(c+1,r+1,fillColor,isInArea,img);
            fill(c-1,r,fillColor,isInArea,img);
            fill(c,r-1,fillColor,isInArea,img);
            fill(c-1,r-1,fillColor,isInArea,img);
            //dodelat neco jako c-1, r+1 atd., podle posunu na rastru
            fill(c+1,r-1,fillColor,isInArea,img);
            fill(c-1,r+1,fillColor,isInArea,img);

        }

        }
    }*/
}
