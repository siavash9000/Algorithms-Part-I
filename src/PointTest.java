import static org.junit.Assert.*;

import org.junit.Test;


public class PointTest {

	private static final double DOUBLE_EQUALITY_THRESHOLD = 0.01;

	@Test
	public void test() {
		assertEquals(0,new Point(0, 1).compareTo(new Point(0, 1)));
		// Compare Y
		assertEquals( -1, new Point(1, 1).compareTo(new Point(1, 2)));
		assertEquals( 1, new Point(1, 2).compareTo(new Point(1, 1)));
		// Compare X
		assertEquals( -1 , new Point(1, 1).compareTo(new Point(2, 1)));
		assertEquals( 1 , new Point(2, 1).compareTo(new Point(1, 1)));

		// Vertical Line
		assertEquals( Double.POSITIVE_INFINITY , new Point(8, 5).slopeTo(new Point(8,0)), DOUBLE_EQUALITY_THRESHOLD);
		// Line Segment
		assertEquals( Double.NEGATIVE_INFINITY , new Point(5, 5).slopeTo(new Point(5,5)),DOUBLE_EQUALITY_THRESHOLD);
		// Horizontal Line
		assertEquals(0 , new Point(0, 5).slopeTo(new Point(8, 5)),DOUBLE_EQUALITY_THRESHOLD);
		assertEquals( 0 , new Point(8, 5).slopeTo(new Point(0, 5)),DOUBLE_EQUALITY_THRESHOLD);
		// Negative
		assertEquals(-2 , new Point(1, 2).slopeTo(new Point(3, -2)),DOUBLE_EQUALITY_THRESHOLD);
		// Normal
		assertEquals( 2.25 , new Point(1, 1).slopeTo(new Point(5, 10)),DOUBLE_EQUALITY_THRESHOLD);
		assertEquals( 6.0 , new Point(1, 1).slopeTo(new Point(5, 25)),DOUBLE_EQUALITY_THRESHOLD);

		Point originPoint = new Point(1, 1);

		// Same slope (straight line)
		assertEquals( 0 , originPoint.SLOPE_ORDER.compare(new Point(2, 2), new Point(5, 5)),DOUBLE_EQUALITY_THRESHOLD);

		// First slope is smaller
		assertEquals( -1 , originPoint.SLOPE_ORDER.compare(new Point(5, 10),new Point(5, 25)));

		// Second slope is smaller
		assertEquals( +1 , originPoint.SLOPE_ORDER.compare(new Point(5, 25),new Point(5, 10)));

		
		
		Point p = new Point(219, 60);
		Point q = new Point(219, 30);
		Point r = new Point(219, 154);
		assertEquals( 0 , p.SLOPE_ORDER.compare(q, r));  
	}

}
