import static org.junit.Assert.*;

import org.junit.Test;

public class NearestNeighborTest {

	@Test
	public void test() {
		// initialize the two data structures with point from standard input
		PointSET brute = new PointSET();
		KdTree kdtree = new KdTree();

		double x = 0;
		double y = 0;
		for (double i = 0; i <= 100000; i++) {
			// the location (x, y) of the mouse
			x = StdRandom.uniform();
			y = StdRandom.uniform();
			Point2D p = new Point2D(x, y);
			kdtree.insert(p);
			brute.insert(p);
		}

		x = 0;
		y = 0;
		for (double i = 0; i <= 3000; i++) {
			// the location (x, y) of the mouse
			x = StdRandom.uniform();
			y = StdRandom.uniform();
			Point2D query = new Point2D(x, y);
			Point2D bruteNearest = brute.nearest(query);
			Point2D treeNearest = kdtree.nearest(query);
			assertEquals(bruteNearest, treeNearest);
		}
	}
}
