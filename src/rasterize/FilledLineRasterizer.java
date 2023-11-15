package rasterize;

import rasterdata.Raster;

public class FilledLineRasterizer extends LineRasterizer {

    // třida pro plnou úsečku, překreslí čárkovanou
   public FilledLineRasterizer(Raster raster) {
       super(raster);
   }
    @Override
    public void drawLine(Raster raster, double x1, double y1, double x2, double y2, int color) {
        if (x2 < x1) {
            double temp = x2;
            x2 = x1;
            x1 = temp;           //triviální algoritmus pro úsečku
            // nevýhody algoritmu: požití sčítání a násobení, jiné pro jednotlivé kvadranty
            //výhody: jednoduchost implemetace
            // podminka pro fungování ve všech kvadrantech


            temp = y1;
            y1 = y2;
            y2 = temp;

            drawLine(raster, x1, y1, x2, y2, color);

        }
            double k = (y2 - y1) / (x2 - x1);
            double q = y1 - k * x1;
            if(k < 1){
                double y;
                for (int x = (int) Math.round(x1); x < x2; x++) {
                    y = (k * x + q);

                    raster.setColor(x, (int) y, color);
                }
            }else if (k>1){
                double x;
                for(int y = (int) Math.round(y1); y < y2; y++){
                    x = (y-q)/k;
                    raster.setColor((int)x,y,color);
                }
            }

          //  drawLine(raster, x1, y1, x2, y2, color);
        /*else{



                if (y2 < y1) {
                double temp = x2;
            x2 = x1;
            x2 = x1;
            x1 = temp;           //triviální algoritmus pro úsečku
            // nevýhody algoritmu: požití sčítání a násobení, jiné pro jednotlivé kvadranty
            //výhody: jednoduchost implemetace
            // podminka pro fungování ve všech kvadrantech


            temp = y1;
            y1 = y2;
            y2 = temp;

            double k = (y2 - y1) / (x2 - x1);
            double q = y1 - k * x1;
            if (k < 0) {
                double y;
                for (int x = (int) Math.round(x1); x < x2; x++) {
                    y = (k * x + q);

                    raster.setColor(x, (int) y, color);
                }
            } else {
                double x;
                for (int y = (int) Math.round(y1); y < y2; y++) {
                    x = (y - q) / k;
                    raster.setColor((int) x, y, color);
                }
            }*/


    }


}

