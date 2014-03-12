import java.util.Arrays;

public class Fast {

	public static void main(String[] args) {
		Point[] input = readPoints(args);
		for(int i=0;i<input.length-2;i++){
			Point[] points = input.clone();
			Arrays.sort(points,i+1,points.length,points[i].SLOPE_ORDER);
			Point[] line = new Point[points.length-i];
			line[0] = points[i];
			line[1] = points[i+1];
			int count = 2;
			double slope = line[0].slopeTo(line[1]);
			for (int j=i+2;j<points.length;j++){
				if (line[0].slopeTo(points[j]) == slope){
					line[count] = points[j];
					count++;
				}
				else{
					if (count>=3)
						printToOutput(line);
					line[0] = points[i];
					line[1] = points[j];
					slope = line[0].slopeTo(line[1]);
					count=2;			
					}
			}
			if (count>=3)
				printToOutput(line);
						
		}
	}

	private static void printToOutput(Point[] subSet) {
		String output = subSet[0].toString();
		for (int i=1;i<subSet.length;i++)
			if (subSet[i]!=null)
				output += " -> "+subSet[i].toString();
		StdOut.println(output);
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