package rasterize.fill;

import java.util.function.Predicate;

public class TestBackground implements Predicate<Integer> {

    private Integer background;

    public TestBackground(Integer background) {
        this.background = background;
    }

    @Override
    public boolean test(Integer currentPixel) {


        return currentPixel.equals(background);
    }
}
