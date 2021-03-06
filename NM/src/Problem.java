import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Problem {
	private static Problem problem = null;
    private int facNum;
    private int[][] flow;
    private int[] lengths;
    private double bestCost;
    
    public int getFacNum() { return facNum;}
    public double getBestCost() { return bestCost;}
    public double getFlow(int fac1, int fac2) { return flow[fac1][fac2]; }
    public double getLength(int i) { return lengths[i];}
    
    private Problem(String fileName) throws FileNotFoundException,IOException {
    	FileReader data;
		Scanner scan;

		data = new FileReader(fileName);
		scan = new Scanner(data);
		facNum = scan.nextInt();
		bestCost = scan.nextDouble();
		flow = new int[facNum][facNum];
		lengths = new int[facNum];
		//to be implemented to create new p or top 
		for (int i = 0; i < facNum; i++){
			lengths[i] = scan.nextInt();
		}
		for (int i = 0; i < facNum; i++) {
			for (int j = 0; j < facNum; j++) {
				flow[i][j] = scan.nextInt();
			}
		}
		//End of creating new p or top 
		scan.close();
    }
    
    public static Problem readProblem(String fileName) {
    	try {
    		problem = new Problem(fileName);
        	return problem;
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    	return null;
    }
    
    public static Problem get() { return problem; }
    
    public String toString() {
    	String str = "" + facNum + "\t" + bestCost + "\n\n";
    	for (int i = 0; i < lengths.length; i++) {
    		str += lengths[i] + "\t" ;
    	}
    	
    	str += "\n\n";
    	for (int i=0; i<flow.length; i++) {
    		for (int j = 0; j<flow[i].length; j++) {
    			if (j != 0) {
    				str += "\t";
    			}
    			str += flow[i][j];
    		}
    		str += "\n";
    	}
    	
    	return str;
    }
    
    public static void main(String[] args) {
    	String fileName = (new File("")).getAbsolutePath() + "/datas/01S9.txt";
    	try {
    	    Problem problem = Problem.readProblem(fileName);
    	    System.out.println(problem);
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    }
}

