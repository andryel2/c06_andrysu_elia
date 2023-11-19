package rasterize;

import rasterdata.Raster;

public class DottedLineRasterizer extends LineRasterizer{
        //třída pro čárkovanou úsečku, vykreslení při tažení myši
    public DottedLineRasterizer(Raster raster) {

        super(raster);

    }

    @Override
        public void drawLine(Raster raster, int x1, int y1, int x2, int y2, int color) {
        {
            int dx = x2 - x1;
            int dy = y2 -y1;

            if(x2 == x1){ //vertical
                dy = Math.abs(dy);
                int y = Math.max(y1,y2);

                while(dy > 0){
                    raster.setPixel(x1,y--, color);
                    --dy;
                }
            }
            else if(y2 == y1){ //horizontal

                dx = Math.abs(dx);
                int x = Math.max(x1,x2);

                while(dx > 0){

                    raster.setPixel(x--,y1, color);
                    --dx;
                }

            }else {
                //steep and shallow
                float m = dy / dx;
                float b = y1 - m* x1;
                if(Math.abs(m) > 1.0f){ //steep
                    dy = Math.abs(dy);
                    int y = Math.max(y1,y2);

                    while (dy > 0){
                        int x = (int) ((y -b)/ m);

                        raster.setPixel(x,y--,color);
                        --dy;
                    }

                }
                else{ //shalow
                    dx = Math.abs(dx);
                    int x = Math.max(x1,x2);

                    while(dx> 0){
                        int y = (int) (m * x + b);

                        raster.setPixel(x--,y,color);
                        --dx;
                    }

                }
            }


        }
    }
    }
