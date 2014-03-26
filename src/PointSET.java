import java.util.TreeSet;
import java.util.Stack;
public class PointSET {
	private TreeSet<Point2D> points;

	public PointSET() {
		points = new TreeSet<Point2D>();
	}

	public boolean isEmpty() {
		return points.isEmpty();
	}

	public int size() {
		return points.size();
	}

	public void insert(Point2D p) {
		points.add(p);
	}

	public boolean contains(Point2D p) {
		return points.contains(p);
	}

	public void draw() {
		for (Point2D point: points) 
			point.draw();
	}
	
	public Iterable<Point2D> range(RectHV rect){
		Stack<Point2D> result = new Stack<Point2D>();
		for (Point2D point: points){
			if (rect.contains(point))
				result.add(point);
		}
		return result;
	}
	
	public Point2D nearest(Point2D p) {
		Point2D result = null;
		double distance = Double.POSITIVE_INFINITY;
		for (Point2D point: points){
			double currentDistance = point.distanceTo(p);
			if (!p.equals(point) && currentDistance<distance){
				result=point;
				distance = currentDistance;
			}
		}
		return result;
	}

    /**
     * Unit tests the point data type.
     */
    public static void main(String[] args) {
        int N = 100;
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 100);
        StdDraw.setPenRadius(.005);
        PointSET points = new PointSET();
        for (int i = 0; i < N; i++) {
            int x = StdRandom.uniform(100);
            int y = StdRandom.uniform(100);
            points.insert(new Point2D(x, y));
        }
        StdDraw.setPenColor(StdDraw.GREEN);
        points.draw();
        
        StdDraw.setPenColor(StdDraw.RED);
        for (Point2D point: points.points){
        	StdDraw.setPenColor(StdDraw.RED);
        	point.draw();
        	Point2D nearest = points.nearest(point);
        	StdDraw.circle(point.x(), point.y(), point.distanceTo(nearest));
        	StdDraw.setPenColor(StdDraw.GREEN);
        	nearest.draw();
        	try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	StdDraw.setPenColor(StdDraw.GREEN);
        	StdDraw.clear();
        	points.draw();
        }
        
        StdDraw.setPenColor(StdDraw.GREEN);
        RectHV rectangle = new RectHV(10, 10, 70, 70);
        rectangle.draw();
        for (Point2D point: points.range(rectangle)){
        	point.draw();
        }
    }
}
