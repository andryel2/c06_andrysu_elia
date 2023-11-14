package rasterdata;

import java.util.Optional;

public interface Raster {

    //dodelat, aby ke každé metodě, kterou mám v rasterbufferdimage bylo matoda tady popsaná


    /**
     * Clear canvas
     */
    void clear();

    /**
     * Set clear color
     *
     * @param color
     *            clear color
     */
    void setClearColor(int color);

    /**
     * Get horizontal size
     *
     * @return width
     */
    int getWidth() ;

    /**
     * Get vertical size
     *
     * @return height
     */
    int getHeight();

    /**
     * Get pixel color at [x,y] position
     *
     * @param x
     *            horizontal coordinate
     * @param y
     *            vertical coordinate
     * @return    pixel color
     */
    int getPixel(int x, int y);

    /**
     * Set pixel color at [x,y] position
     *
     * @param x
     *            horizontal coordinate
     * @param y
     *            vertical coordinate
     * @param color
     *            pixel color
     */
    void setPixel(int x, int y, int color) ;

    int getColor();

    Optional<Integer> getColor(int c, int r);

    void setColor(int c, int r, int color);

    }
