package fun;
//metody pro rohraní predikát, uplne nechapu
public class Predicate {

    public Predicate(){}
    public static void main(String[] args) { //definice predikátu
        java.util.function.Predicate<Integer> isEven = integer -> integer % 2 ==0; //vraci boolean jestli je podminka splněna

    }
}
