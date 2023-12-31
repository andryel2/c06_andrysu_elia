package rasterize;

import model.Line;
import model.Point;
import model.Polygon;
import rasterdata.Raster;

public class PolygonRasterizer {

    private FilledLineRasterizer filledLineRasterizer;
    private DottedLineRasterizer dottedLineRasterizer;

    public PolygonRasterizer(FilledLineRasterizer filledLineRasterizer, DottedLineRasterizer dottedLineRasterizer){
        this.filledLineRasterizer = filledLineRasterizer;
        this.dottedLineRasterizer = dottedLineRasterizer;
    }

    public void rasterize(Polygon polygon) {
        if (polygon.size() < 2)
            return;

        if(polygon.size() == 2){
            Point pC = polygon.get(1);
            Point pD = polygon.get(0);
            filledLineRasterizer.rasterizePolygon(new Line(pD,pC,0xffff00));
        }
        if(polygon.size() > 2 ) {

            for (int i = 0; i < polygon.size() -1; i++) {
                int A = i;
                int B = i + 1;

                Point pA = polygon.get(A);
                Point pB = polygon.get(B);

                filledLineRasterizer.rasterizePolygon(new Line(pA, pB, 0xffff00));

            }

            if(polygon.size() >= 3) {
                Point pE = polygon.get(polygon.size() - 1);
                Point pF = polygon.get(0);

                filledLineRasterizer.rasterizePolygon(new Line(pE, pF, 0xffff00));
            }

        }
    }
    public void teckovanaRasterize(Polygon polygon, double x2, double y2, Raster raster, int color)  //co když předávám špatné parametry
            //musim ukládat do seznamu pouze konečne vrcholy
        {
            if (polygon.size() < 2) {
                return;
            }

            if(polygon.size() == 3){

               // int A = polygon.size()-2;
              //  int B = polygon.size()-1;

                Point pA = polygon.get(0);
                Point pB = polygon.get(1);
                Point pC = new Point(x2,y2);

                dottedLineRasterizer.rasterizePolygon(new Line(pA, pC, 0xff0000));
                dottedLineRasterizer.rasterizePolygon(new Line(pB,pC,0xff0000));

            }

            if(polygon.size() >= 4 ){
                    int A = polygon.size()-2;
                    int B = polygon.size()-1;

                    Point pA = polygon.get(A);
                    Point pB = polygon.get(B);
                    Point pC = new Point(x2,y2);
                    dottedLineRasterizer.rasterizePolygon(new Line(pA, pC, 0xff00ff));
                    dottedLineRasterizer.rasterizePolygon(new Line(pB,pC,0xff0000));
                }


        }


        public void obdelnik(Point p1,Point p3,int color){
        Point p2 = new Point(p3.x, p1.y);
        Point p4 = new Point(p1.x,p3.y);

       filledLineRasterizer.rasterizePolygon(new Line(p1,p2,color));
        filledLineRasterizer.rasterizePolygon(new Line(p2,p3,color));
       filledLineRasterizer.rasterizePolygon(new Line(p3,p4,color));
        filledLineRasterizer.rasterizePolygon(new Line(p4,p1,color));

        }


}
