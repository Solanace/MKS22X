public class QueenBoard {
    private int[][] board, solution;
    private int solutionCount;

    public QueenBoard(int size) {
	board = new int[size][size];
	solution = new int[size][size];
	solutionCount = -1;
    }

    public boolean solve() {
	solutionCount = 0;
	return solveH(0);
    }

    private boolean solveH(int col) {
	boolean solutionFound = false;
	if (col + 1 >= board.length) {
	    solutionCount += 1;
	    return true;
	}
	for (int row = 0; row < board.length; row ++) {
	    if (board[row][col] == 0) {
		addQueen(row, col);
		if (solveH(col + 1)) {
		    solutionFound = true;
		}
		removeQueen(row, col);
	    }
	}
	return solutionFound;
    }

    private void addQueen(int row, int col) {
	for (int c = 0; c < board.length; c ++) {
	    board[row][c] += 1;
	}
	for (int r = 0; r < board.length; r ++) {
	    board[r][col] += 1;
	}
    }
	

    public int getSolutionCount() {
	return solutionCount;
    }

    public String toString() {
	return "";
    }
