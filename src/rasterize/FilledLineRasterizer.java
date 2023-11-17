package rasterize;

import rasterdata.Raster;

public class FilledLineRasterizer extends LineRasterizer {

    // třida pro plnou úsečku, překreslí čárkovanou
   public FilledLineRasterizer(Raster raster) {
       super(raster);
   }
    @Override
    public void drawLine(Raster raster, int x1, int y1, int x2, int y2, int color) {
            if((Math.abs(y2-y1))<(Math.abs(x2-x1))) {
                if (x2 < x1){
                    int temp = x1;
                    x1 = x2;
                    x2 = temp;

                    temp = y1;
                    y1 = y2;
                    y2 = temp;

                    draw(raster, x1,x2,y1,y2,color,0);  //osa 0 je x

                }
            } else{
                if(y2<y1){
                        int temp = x1;
                        x1 = x2;
                        x2 = temp;

                        temp = y1;
                        y1 = y2;
                        y2 = temp;

                        draw(raster, x1,x2,y1,y2,color,1);  //osa 1 je y
                }

            }






    }

    public void draw(Raster raster,int x1,int y1, int x2, int y2, int color, int osa){
        int x = x1;
        int y = y1;
        int dy = y2 - y1;
        int dx = x2 - x1;
        int p = (2 * dy) - dx;
        int d = (2 * dx) - dy;
        int yi = 1;
        int xi = 1;

        if(osa == 0) {

            raster.setColor(x, y, color);

            if (dy < 0) {
                 yi = -1;
                 dy = -dy;
             }

            while (x < x2) {
                 x = x + 1;
                if (p < 0) {
                     p = p + 2 * dy;
                } else {
                 p = p + ( 2* (dy - dx));
                 y = y + yi;
                 }
                raster.setColor(x, y, color);
            }
        } else{
                if (dx < 0) {
                    xi = -1;
                    dx = -dx;
                }

                raster.setColor(x, y, color);

                while (y < y2) {
                    y = y + 1;
                    if (d < 0) {
                        d = d + 2 * dx;
                    } else {
                        d = d + (2 * (dx - dy));
                        x = x + xi;
                    }
                    raster.setColor(x, y, color);
                }

        }


    }


}

