public class KdTree {
	private Node root;
	private int size=0; 

	public boolean isEmpty() {
		return size==0;
	}

	public int size() {
		return size;
	}

	public void insert(Point2D point) {
		root = insertIntoKdTree(root, point,true);
	}
	
	private Node insertIntoKdTree(Node root, Point2D point,boolean vertical){
		if(root==null){
			root = new Node(point,null, null, vertical);
			size++;
		}
		if (root.point.equals(point))
			return root;
		if(root.vertical && Point2D.X_ORDER.compare(root.point,point)==1 || 
		  !root.vertical && Point2D.Y_ORDER.compare(root.point, point) == 1)
			root.left  = insertIntoKdTree(root.left, point, !vertical);
		else 
			root.right = insertIntoKdTree(root.right, point, !vertical);
		return root;
		
	}

	public boolean contains(Point2D point) {		
		return treeContainsPoint(null, point);
	}
	private boolean treeContainsPoint(Node root, Point2D point){
		if (root == null)
			return false;
		if (root.point.equals(point))
			return true;
		if(root.vertical && Point2D.X_ORDER.compare(root.point,point)==1 || 
		  !root.vertical && Point2D.Y_ORDER.compare(root.point, point) == 1)
			return treeContainsPoint(root.left, point);
		else 
			return treeContainsPoint(root.right, point);
	}
	public void draw() {
		drawNodeInsertionLine(null,root);
	}	
	private void drawNodeInsertionLine(Node root,Node node){
		if (node!=null) {
			node.point.draw();
			StdDraw.circle(node.point.x(), node.point.y(), 0.009);
			double lineX1=0,lineX2=0,lineY1=0,lineY2=0;
			if (node.vertical){
				StdDraw.setPenColor(StdDraw.RED);
				lineX1=node.point.x();
				lineX2=node.point.x();
				if(root==null){					
					lineY1=0;
					lineY2=1;
				}
				else if(root.point.y() > node.point.y()){
					lineY1=root.point.y();
					lineY2=0;
				}
				else {
					lineY1=root.point.y();
					lineY2=1;
				}
			}
			else {
					StdDraw.setPenColor(StdDraw.GREEN);
					lineY1=node.point.y();
					lineY2=node.point.y();
					if(root==null){					
						lineX1=0;
						lineX2=1;
					}
					else if(root.point.x() > node.point.x()){
						lineX1=root.point.x();
						lineX2=0;
					}
					else {
						lineX1=root.point.x();
						lineX2=1;
					}
			}
			StdDraw.line(lineX1, lineY1, lineX2, lineY2);
			drawNodeInsertionLine(node,node.left);
			drawNodeInsertionLine(node,node.right);
		}
	}	
	public Iterable<Point2D> range(RectHV rect){
		return null;
	}	
	public Point2D nearest(Point2D p) {
		Point2D result = null;
		return result;
	}
	private class Node {
		public Node left,right;
		public Point2D point;
		public boolean vertical;
		public Node(Point2D point, Node left, Node right, boolean vertical){
			this.point = point;
			this.vertical = vertical;
		}
	}
    /**
     * Unit tests the point data type.
     */
    public static void main(String[] args) {
        int N = 10;
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);
        StdDraw.setPenRadius(.005);
        KdTree points = new KdTree();
        points.insert(new Point2D(0.7, 0.2));
        points.insert(new Point2D(0.5, 0.4));
        points.insert(new Point2D(0.2, 0.3));
        points.insert(new Point2D(0.4, 0.7));
        points.insert(new Point2D(0.9, 0.6));
        points.draw();
       /* for (int i = 0; i < N; i++) {
            double x = StdRandom.uniform();
            double y = StdRandom.uniform();
            points.insert(new Point2D(x, y));
        }
        
        points.draw();
        
        StdDraw.setPenColor(StdDraw.RED);
        for (Point2D point: points.range(new RectHV(0, 0, 100, 100))){
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
        */
    }
}
