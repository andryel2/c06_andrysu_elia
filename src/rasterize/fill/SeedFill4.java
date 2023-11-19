package rasterize.fill;

import rasterdata.Raster;

import java.util.Optional;
import java.util.function.Predicate;
//scanline - hranice zadana objektem(polygon ma seznam bodu, ktere jsou propojene), budeme hledat pruseciky usecek s horizontalnimy
//úseckami a vyplnnujeme oblast mezi pruseciky, potrebujeme aby nakreslene usecky smerovaly dolů, najdeme si max a min y,
// budeme vyplnovat oblasti mezi sudymi a lichymi indexi x, po vyplneni nakreslime ten samotny polygon, museji se odstranit vodorovne usecky
//chceme vždy sudy počet prusečiků, specialni pripad, aby
public class SeedFill4 implements SeedFill{

    public SeedFill4() {
    }

    //seedfill pro čtyř okolí, podminka podle barvy okraje a pozadí pixelu
    @Override  //dodelat funkci pro getColor
    public void fill(int c, int r, int fillColor,/*Predicate<Integer> isInArea*/int backgroundColor,int borderColor ,Raster img)  {
        if(c < img.getWidth() && r < img.getHeight() && c>=0 && r>=0){
            //zjistim jestli je adresa pixelu validní
            //isPresent metoda z optiaonal
           int currentColor = img.getPixel(c,r); //.get();
            if(currentColor == backgroundColor) {
                if (currentColor != borderColor && currentColor != fillColor) { //kontrola pouzite barvy pozadi a jestli uz neni obarven
                    /* if(isInArea.test(currentColor))*/
                    img.setColor(c, r, fillColor);
                    fill(c + 1, r, fillColor, backgroundColor, borderColor, img);
                    fill(c - 1, r, fillColor, backgroundColor, borderColor, img);
                    fill(c, r + 1, fillColor, backgroundColor, borderColor, img);
                    fill(c, r - 1, fillColor, backgroundColor, borderColor, img);
                    //volani funkce rekurzivně pro posunuté hodnoty c r do 4 směrů
                }
            }

        }
        //na gitu napsané pomocí lambda výrazu, ve zkráceném tvaru
        // stahnout z gitlabu popřípadne dodelat
        //definovat tridu optional

    }
}

