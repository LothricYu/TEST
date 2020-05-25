import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public abstract class Solution implements Comparable<Solution> {

	    protected int[] p;
	    protected double[] pos;
	    protected double[][] dist;
	    protected double cost = Double.MAX_VALUE;
	    protected int lastImprove = 0;
	    static Random rand = new Random();
	    
	    

	    protected void calcuCost() {
	    	Problem problem = Problem.get();
	    	calcuDist();
	    	cost = 0;
	        for (int i = 0; i < pos.length - 1; i++) {
	        	for (int j = i+1; j < pos.length; j++) {
	        		cost += dist[i][j] * problem.getFlow(i, j);
	        	}
	        }
	    }
	    
	    protected abstract double[][] calcuDist();
	    public abstract Solution neighbor();
		public abstract Solution localSearch();
	    public abstract void save(String fileName);
	    

	    public int[] getPermutation() { return p.clone(); }
	    public int getFacility(int i) { return p[i]; }
	    public int getFacNum() { return p.length; }
	    public double getCost() { return cost; }
	    public void setLastImprove(int n) { this.lastImprove = n; }
	    public int getLastImprove() { return this.lastImprove;}
	     
	    public static void main(String[] args) {
	    	 //获取开始时间
	    	long startTime = System.currentTimeMillis();   
	    	
	    	String name = "01S9";
	    	String fileName = (new File("")).getAbsolutePath() + "/datas/" + name + ".txt";
	    	Problem problem = null;
	    	try {
	    	    problem = Problem.readProblem(fileName);
	    	    System.out.println(problem);
	    	} catch (Exception ex) {
	    		ex.printStackTrace();
	    	}
	    	Solution s = new SolutionFlag();
	    	Solution best = s;
	    	int m = 100000;
	    	while (m-- > 0) {
	    		s = new SolutionFlag();
	    		if (s.getCost() < best.getCost()) {
	    			best = s;
	    		}
	    	}
	        System.out.println(best);
	        
	        //获取结束时间
	        long endTime = System.currentTimeMillis();   
	        //输出程序运行时间
	        System.out.println("程序运行时间：" + (endTime - startTime)/1000.00 + "s");    
	     }

		@Override
		public int compareTo(Solution s) {
			if (this.cost > s.cost) {
				return 1;
			} else if (this.cost < s.cost) {
				return -1;
			} else {
			    return 0;
			}
		}
}
