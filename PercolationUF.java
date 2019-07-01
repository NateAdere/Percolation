import java.util.*;

public class PercolationUF implements IPercolate {
	
	 boolean[][] myGrid;
	 int myOpenCount;
	 IUnionFind myFinder;
	 private final int VTOP;
	 private final int VBOTTOM;
	 
	 public PercolationUF(int size, IUnionFind finder) {
		 
		 VTOP = size * size;
	     VBOTTOM = size * size + 1;
	     myGrid = new boolean [size][size];
	     finder.initialize(size * size + 2);
	     myOpenCount = 0; 
	     for (boolean[] k : myGrid) {
	    	 Arrays.fill(k, false);
	     }
	     myFinder = finder;
	    
	 }
	 
	 @Override
	 /**
	 * Open site (row, col) if it is not already open. By convention, (0, 0)
	 * is the upper-left site
	 * 
	 * The method modifies internal state so that determining if percolation
	 * occurs could change after taking a step in the simulation.
	 * 
	 * @param row
	 * row index in range [0,N-1]
	 * @param col
	 * column index in range [0,N-1]
	 */
	public void open(int row, int col) {
		 if (! inBounds(row,col)) { //throw exceptions when the (row,col) are not in bounds. 
			 throw new IndexOutOfBoundsException(String.format("(%d,%d) out of bounds", row, col));
		 }
		 if (myGrid[row][col] == true){    //Increments by a single value and then connects after cell has been opened and connected to top and bottom.
	        	return; 
	        }
	        
		 myOpenCount += 1;
         myGrid[row][col] = true;
         int size = myGrid.length;
         
         if (row == 0) {
             myFinder.union(row * size + col, VTOP); 
         }
         
         if (row == size - 1) {
             myFinder.union(row * size + col, VBOTTOM);
         }
         
         if (inBounds(row - 1, col) && isOpen(row - 1, col)) {
             myFinder.union(row * size + col, (row-1) * size + col);
         }
         
         if (inBounds(row, col - 1) && isOpen(row, col - 1)) {
             myFinder.union(row * size + col, row * size + (col-1));
         }
         
         if (inBounds(row, col + 1) && isOpen(row, col + 1)) {
             myFinder.union(row * size + col, row * size + (col+1));
         }
         
         if (inBounds(row + 1, col) && isOpen(row + 1, col)) {
             myFinder.union(row * size + col, (row+1) * size + col);
         }
	
	 }
	 

	@Override
	/**
	 * Returns true if and only if site (row, col) is OPEN
	 * 
	 * @param row
	 *            row index in range [0,N-1]
	 * @param col
	 *            column index in range [0,N-1]
	 */	
	public boolean isOpen(int row, int col) {
		if (! inBounds(row,col)) { //throw exceptions when the (row,col) are not in bounds. 
			 throw new IndexOutOfBoundsException(String.format("(%d,%d) out of bounds", row, col));
		 }
		return myGrid[row][col] != false;
	
	}
	

	@Override
	/**
	 * Returns true if and only if site (row, col) is FULL
	 * 
	 * @param row
	 *            row index in range [0,N-1]
	 * @param col
	 *            column index in range [0,N-1]
	 */
	public boolean isFull(int row, int col) {
		if (! inBounds(row,col)) { //throw exceptions when the (row,col) are not in bounds. 
			 throw new IndexOutOfBoundsException(String.format("(%d,%d) out of bounds", row, col));
		 }
		int size = myGrid.length;
		return myFinder.connected(row * size + col, VTOP);
	
	}
	
	@Override
	/**
	 * Returns true if the simulated percolation actually percolates. What it
	 * means to percolate could depend on the system being simulated, but
	 * returning true typically means there's a connected path from
	 * top-to-bottom.
	 * 
	 * @return true iff the simulated system percolates
	 */
	public boolean percolates() {
		 return myFinder.connected(VTOP, VBOTTOM);
	
	}
	
	@Override
	/**
	 * Returns the number of distinct sites that have been opened in this
	 * simulation
	 * 
	 * @return number of open sites
	 */
	public int numberOfOpenSites() {
		return myOpenCount;
	
	}
	public boolean inBounds(int row, int col) {
        return (row >= 0 && row < myGrid.length && col >= 0 && col < myGrid.length);
    }
}

