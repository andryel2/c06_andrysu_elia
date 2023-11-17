package rasterize;

import rasterdata.Raster;

public class DottedLineRasterizer extends LineRasterizer{
        //třída pro čárkovanou úsečku, vykreslení při tažení myši
    public DottedLineRasterizer(Raster raster) {

        super(raster);

    }

    @Override
        public void drawLine(Raster raster, int x1, int y1, int x2, int y2, int color) {

            int pocitadlo = 0;  //Pro mezery jsem využil počitadlo, které mně řekne,
                                // které pixely zobrazím jako background
            if (x2 < x1) {
                int temp = x2;
                x2 = x1;
                x1 = temp;

                temp = y1;
                y1 = y2;
                y2 = temp;

                drawLine(raster, x1, y1, x2, y2, color);
            } else {
                double k = (y2 - y1) / (x2 - x1);
                double q = y1 - k * x1;
                double y;
                for (int x = (int) Math.round(x1); x < x2; x++) {
                    y = (k * x + q);


                        if((0 == (x % 10))){   //Když x je celodělitelné 10 tak tento a dalších 7
                                                // pixelú mají barvu jako backround
                            raster.setPixel(x, (int) y,0x000000);
                            pocitadlo = 7;
                        }


                        if(pocitadlo!=0){
                            raster.setPixel(x, (int) y,0x000000);
                            pocitadlo--;
                         }
                        else{

                            raster.setPixel(x, (int) y,color);
                        }

                }
            }


        }
    }
