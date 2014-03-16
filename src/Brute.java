import java.util.Arrays;

public class Brute {

	public static void main(String[] args) {
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		Point[] points = readPoints(args);
		for (Point point:points)
			point.draw();
		checkAllSetsOfSize4(points);
		//System.out.println(Arrays.toString(points));
	}

	private static void checkAllSetsOfSize4(Point[] points) {
		for(int one=0;one<points.length;one++)
			for(int two=one+1;two<points.length;two++)
				for(int three=two+1;three<points.length;three++)
					for(int four=three+1;four<points.length;four++){
						Point[] subSet = {points[one],points[two],points[three],points[four]};
							if(isColinear(subSet)) {
								printToOutput(subSet);
								drawLine(subSet);
							}
					}				
	}
	
	private static void drawLine(Point[] points) {
		Arrays.sort(points,0,points.length);
		points[0].drawTo(points[points.length-1]);
		
	}

	private static void printToOutput(Point[] points) {
		Arrays.sort(points,0,points.length);
		String output = points[0].toString()+" -> "+points[1].toString()+ " -> "
						+points[2].toString()+ " -> "+points[3].toString();   
		StdOut.println(output);
	}

	private static boolean isColinear(Point[] subSet) {
		double slopeOne = subSet[0].slopeTo(subSet[1]);
		double slopeTwo = subSet[0].slopeTo(subSet[2]);
		double slopeThree = subSet[0].slopeTo(subSet[3]);
		return slopeOne==slopeTwo && slopeOne == slopeThree;		
	}

	private static Point[] readPoints(String[] args) {
		String inputFilename = args[0];
		In input = new In(inputFilename);
		Point[] points = new Point[input.readInt()];
		int[] allVallues = input.readAllInts();
		for (int i=0;i<allVallues.length;i=i+2)
			points[i/2] = new Point(allVallues[i], allVallues[i+1]);	
		return points;
	}	
}