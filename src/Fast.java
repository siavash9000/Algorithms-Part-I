import java.util.Arrays;
import java.util.HashSet;
import java.util.ArrayList;

public class Fast {
	
	public static void main(String[] args) {
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		ArrayList<HashSet<Point>> lines = new ArrayList<HashSet<Point>>();
		Point[] input = readPoints(args);		
		for(int i=0;i<input.length-1;i++){
			Point[] points = input.clone();
			Arrays.sort(points,i,points.length,points[i].SLOPE_ORDER);
			Point[] line = new Point[points.length];
			line[0] = points[i];
			line[1] = points[i+1];
			int count = 2;
			double slope = line[0].slopeTo(line[1]);
			for (int j=i+2;j<points.length;j++){
				if (pointIsOnLineSegment(points[j], line, slope)){
					line[count] = points[j];
					count++;
				}
				else{
					checkIfNewLineFound(line, count,lines);
					line[1] = points[j];
					slope = line[0].slopeTo(line[1]);
					count=2;			
					}
			}
			checkIfNewLineFound(line, count,lines);						
		}
	}

	private static void checkIfNewLineFound(Point[] line, int count, ArrayList<HashSet<Point>> lines) {
		if (count < 3)
			return;
		for (int i = 0; i < lines.size(); i++) {
			if (lines.get(i).contains(line[0])&&lines.get(i).contains(line[1]))
				return;
		}
		printToOutput(line, count);
		HashSet<Point> newLine = new HashSet<Point>();
		for (int i=0;i<count;i++)
			newLine.add(line[i]);
		lines.add(newLine);
	}
	

	private static boolean pointIsOnLineSegment(Point point, Point[] line,double slope) {
		return line[0].slopeTo(point) == slope;
	}

	private static void printToOutput(Point[] points, int count) {
		Arrays.sort(points,0,count);
		String output = points[0].toString();		
		for (int i=1;i<count;i++)
			output += " -> "+points[i].toString();
		StdOut.println(output);		
		points[0].drawTo(points[count-1]);
	}

	private static Point[] readPoints(String[] args) {
		String inputFilename = args[0];
		In input = new In(inputFilename);
		Point[] points = new Point[input.readInt()];
		int[] allVallues = input.readAllInts();
		for (int i=0;i<allVallues.length;i=i+2){
			points[i/2] = new Point(allVallues[i], allVallues[i+1]);
			points[i/2].draw();
		}
		return points;
	}	
}