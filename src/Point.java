/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER= new Comparator<Point>() {
    /*	
    	The SLOPE_ORDER comparator should compare points by the slopes they make with the 
    	invoking point (x0, y0). 
    	Formally, the point (x1, y1) is less than the point (x2, y2) 
    	if and only if the slope (y1 − y0) / (x1 − x0) is less than the slope (y2 − y0) / (x2 − x0). 
    	Treat horizontal, vertical, and degenerate line segments as in the slopeTo() method.
	*/
		@Override
		public int compare(Point a, Point b) {
			if (a==null||b==null)
				throw new NullPointerException();
			Double slopeA = Point.this.slopeTo(a);
			Double slopeB = Point.this.slopeTo(b);
			return slopeA.compareTo(slopeB);
		}};

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
    	if (that==null)
			throw new NullPointerException();
    	if (that.x - x == 0 && that.y - y == 0)
    		return Double.NEGATIVE_INFINITY;
    	else if (that.x - x == 0 && that.y - y != 0)
    		return Double.POSITIVE_INFINITY;
    	else if (that.x - x != 0 && that.y - y == 0)
    		return +0;
    	else
    		return ((double) (that.y - y))/((double)that.x -x);    	
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
    	if (that==null)
			throw new NullPointerException();
        if (y > that.y || (y == that.y && x > that.x))
        	return 1;
        else if (y < that.y || (y == that.y && x < that.x))
        	return -1;
        else
        	return 0;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {

		}
}
