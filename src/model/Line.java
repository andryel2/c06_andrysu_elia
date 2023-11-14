package model;
public class Line {

    private int x1, x2, y1, y2;
    private int color;
    public Point start;
    public Point end;
    public Line(int x1, int y1, int x2, int y2, int color) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.color = color;

    }

    public Line(Point p1, Point p2, int color) {
        this.x1 = p1.x;
        this.y1 = p1.y;
        this.x2 = p2.x;
        this.y2 = p2.y;
        this.color = color;
    }

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public int getX2() {
        return x2;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }

    public int getColor() {
        return color;
    }

    //metody pro scan_line

    public boolean isHorizontal(){
        if((getY1() == 0)&&(getY2()==0)){
            return true;
        }
        else {
            return false;
        }
    }

   /* public Line oriented(){
        //musí být orientovaná dolů, vrátím kdyžtak přeorientovaou
      Line line = new Line();
      return line;
    }*/

    public boolean hasYIntercept(double y){ //musim přes předpis úsečky
        final double k = (y2 - y1) / (x2 - x1);
        final double q = y1 - k * x1;
        for (int c = (int) x1; c < x2; c++) {
            int r = (int) (k * c + q);
            if(r==y){
                return true;
            }
        }
        return false;
    }
  /*  public double yIntercept(double y){ //musim přes předpis úsečky
        double xIntercept = 0;
        double k = (y2 - y1) / (x2 - x1);
        double q = y1 - k * x1;
        for (int c = (int) x1; c < x2; c++) {
            int r = (int) (k * c + q);
            if(r==y){
                xIntercept = c;
                return xIntercept;
            }
        }
        //navratova hodnota
    }*/

    //metody pro ořezávání

    public Point intercept(Line other) {
        // vypočtení průsečíku s přímkou
        return null;
    }

    public boolean isInside(Point p) {   //vektory jako point, protože je tak muzeme reprezentovat
        final Point t = new Point(end.x - start.x, end.y - start.y);
        final Point n = new Point(t.y, -t.x);
        final Point v = new Point(p.x - start.x, p.y - start.y);
        final Point nNorm = new Point(n.x / n.length(), n.y / n.length()); // TODO what if length is 0?
        final Point vNorm = new Point(v.x / v.length(), v.y / v.length()); // TODO what if length is 0?
        final double cosAlpha = nNorm.x * vNorm.x + nNorm.y * vNorm.y;
        return cosAlpha < 0;
    }



}

