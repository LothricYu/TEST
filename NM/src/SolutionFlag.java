import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SolutionFlag extends Solution {
	
    public SolutionFlag() {
    	Problem problem = Problem.get();
    	p = new int[problem.getFacNum()+1];
    	List<Integer> list = new ArrayList<>();
    	for (int i = 0; i < p.length; i++) {
    		list.add(i);
    	}
    	Collections.shuffle(list);
   		for (int i = 0; i < p.length; i++) {
   			p[i] = list.get(i);;
   		}
   		calcuCost();
    }	

	public SolutionFlag(int[] p) {
		this.p = p.clone();
		calcuCost();
	}

	@Override
	protected double[][] calcuDist() {
    	Problem prob = Problem.get();
    	pos = new double[prob.getFacNum()];
    	int i = 0;
    	while (p[i] != pos.length) {
    		pos[p[i]] = prob.getLength(p[i])/2.0;
    		if (i != 0) {
    			pos[p[i]] += pos[p[i-1]] + prob.getLength(p[i-1])/2.0;
    		}
    		i++;
    	}
    	i++;
    	boolean first = true;
	    //to be implemented to create new p or top 
    	
    	while(i<p.length) {
    		pos[p[i]]=prob.getLength(p[i])/2.0;
    		if(!first) {
    			pos[p[i]] += pos[p[i-1]] + prob.getLength(p[i-1])/2.0;
    		}
    		first=false;
    		i++;
    	}
    	
		//End of creating new p or top 
    	
    	dist = new double[pos.length][pos.length];
        //to be implemented to create new p or top 
    	
    	for( i =0;i<pos.length;i++) {
    		for(int j=i+1;j<pos.length;j++) {
    			dist[i][j] = Math.abs(pos[i]-pos[j]);
    			dist[j][i] = dist[i][j];
    		}
    	}   
    	
		//End of creating new p or top 
    	
    	return dist;
	}

	@Override
	public Solution neighbor() {
		int[] p = this.p.clone();
	    //to be implemented to create new p or top 
		
		
		
		
		//End of creating new p or top 
		return new SolutionFlag(p);
	}

	@Override
	public Solution localSearch() {
		SolutionFlag bs = this;
		boolean improved = true;
		int[] p = this.p.clone();
		while (improved) {
	    //to be implemented to create new p or top 
			
			improved =false;
			for(int i=0;i<p.length-2;i++)
				for(int j=i+1;j<p.length-1;j++) {
					int temp = p[i];
					p[i] = p[j];
					p[j] = temp;
					SolutionFlag now = new SolutionFlag(p);
					if(now.compareTo(bs)<0) {
						bs = now;
						improved = true;
						break;
					}else {
					    p=bs.p.clone();
					}
				}	
			if(improved) break;
			
		//End of creating new p or top 
		}
		return bs;
	}

	@Override
	public void save(String fileName) {
    	try {
    		int top = 0;
    		for (int i = 0; i < p.length; i++) {
    			if (p[i] == p.length - 1) {
    				top = i;
    				break;
    			}
    		}
			PrintWriter printWriter = new PrintWriter(new FileWriter(fileName));
			printWriter.println((p.length-1)+",0,0,0,0,0,0,0,0");
			for (int i = 0; i < p.length; i++) {
				if (p[i] == p.length -1) continue;
				int f = p[i];
				printWriter.print((f+1) + ",");
				double w = Problem.get().getLength(f);
				if (i < top) {
					printWriter.print((pos[f]-w/2) + ",0,");
					printWriter.print((pos[f]+w/2) + ",0,");
					printWriter.print((pos[f]+w/2) + "," + w + ",");
					printWriter.print((pos[f]-w/2) + "," + w);
				} else {
					printWriter.print((pos[f]-w/2) + ",0,");
					printWriter.print((pos[f]+w/2) + ",0,");
					printWriter.print((pos[f]+w/2) + "," + (-w) + ",");
					printWriter.print((pos[f]-w/2) + "," + (-w));
				}
				printWriter.println();
			}
			printWriter.println(cost+",0,0,0,0,0,0,0,0");
			printWriter.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
