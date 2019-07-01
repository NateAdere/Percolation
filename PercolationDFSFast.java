
public class PercolationDFSFast extends PercolationDFS{
	/**
	 * calls super to initialize the state in the parent class. 
	 * @param int/size 
	 */
	public PercolationDFSFast(int n) {
		super(n);
	}
	@Override
	/**
	 *  dfs is called once if the newly open cell is in 
	 *  the top row or because it's adjacent to an already FULL cell.
	 */ 
	protected void updateOnOpen(int row, int col) {
		
		if (row == 0 || //when cell is on the top row
                (inBounds(row - 1, col) && isFull(row - 1, col)) || //when cell is adjacent to a Full cell
                (inBounds(row, col - 1) && isFull(row, col - 1)) || //when cell is adjacent to a Full cell
                (inBounds(row, col + 1) && isFull(row, col + 1)) || //when cell is adjacent to a Full cell
                (inBounds(row + 1, col) && isFull(row + 1, col))) { //when cell is adjacent to a Full cell
            dfs(row, col);
        }
	}
	
	

}
