import java.util.*;

public class PercolationBFS extends PercolationDFSFast {
	/**
	 * initialize the state in the parent class. 
	 * @param int/size parameter
	 */
	public PercolationBFS(int n) {
		super(n);
	}
	@Override 
	/**
	 * 
	 */
	protected void dfs(int row, int col) {
		// out of bounds?
		if (! inBounds(row,col)) return;
		
		// full or NOT open, don't process
		if (isFull(row, col) || !isOpen(row, col))
			return;
		
		Queue<Integer> qu = new LinkedList<>();
        myGrid[row][col] = FULL;
        qu.add(row * myGrid.length + col);
        
        while (!qu.isEmpty()) {
        	int size = myGrid.length; 
        	int value = qu.remove();
            int ro = value / size;
            int cl = value % size;
    
            if ((inBounds(ro - 1, cl) && !isFull(ro - 1, cl) && isOpen(ro -1 ,cl))) {
                myGrid[ro - 1][cl] = FULL;
                qu.add((ro-1)* size + cl);
            }
            if ((inBounds(ro, cl - 1) && !isFull(ro, cl - 1) && isOpen(ro, cl - 1))) {
                myGrid[ro][cl - 1] = FULL;
                qu.add(ro * size + (cl-1));
            }
            if ((inBounds(ro, cl + 1) && !isFull(ro, cl + 1) && isOpen(ro, cl + 1))) {
                myGrid[ro][cl + 1] = FULL;
                qu.add(ro * size + (cl+1));
            }
            if ((inBounds(ro + 1, cl) && !isFull(ro + 1, cl) && isOpen(ro + 1, cl))) {
                myGrid[ro + 1][cl] = FULL;
                qu.add((ro+1)* size + cl);
            } 
        }
        
	}
	

}
