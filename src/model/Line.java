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
        return y1==y2;

    }

    public Line oriented(){
        //musí být orientovaná dolů, vrátím kdyžtak přeorientovaou
        Line line = null;
        if(y2< y1){
            line = new Line(x1,x2,y2,y1,getColor());
        }

      return line;
    }

    public boolean hasYIntercept(double y){
        if(y>= y1 && y< y2 ){
            return true;
        }
        return false;
    }
   public double yIntercept(double y) { //musim přes předpis úsečky
        double xIntercept = 0;
        double k = (y2 - y1) / (x2 - x1);
        double q = y1 - k * x1;
        for (int c = (int) x1; c < x2; c++) {
            int r = (int) (k * c + q);
            if (r == y) {
                xIntercept = c;
                return xIntercept;
            }
        }
        return -1;  //vraceni hodnoty, kterou x mít nemůže, bude se dále kontrolovat
    }    //metody pro ořezávání

    public Point intercept(Line other) {
        int x0;
        int y0;
        int x3 = other.getX1();
        int y3 = other.getY1();
        int x4 = other.getX2();
        int y4 = other.getY2();
        x0 = ((x1*y1 - x2*y1)*(x3 - x4) - (x3*y4 - x4*y3)*(x1 - x2)) /
                ((x1 - x2)*(y3 - y4) - (y1 - y2)*(x3 - x4));
        y0 = ((x1*y2 - x2*y1)*(y3 - y4) - (x3*y4 - x4*y3)*(y1 - y2)) /
                ((x1 - x2)*(y3 - y4) - (y1 - y2)*(x3 - x4));
        Point p0 = new Point(x0,y0);
        return p0;
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

