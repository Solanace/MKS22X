public class QueenBoard {
    private int[][] board, solution;
    private int solutionCount;

    public QueenBoard(int size) {
	if (size <= 0) {
	    throw new IllegalArgumentException("Please only input positive integers");
	}
	board = new int[size][size];
	solution = new int[size][size];
	solution[0][0] = -1; // Used to check if solution was filled later
	solutionCount = -1;
    }

    public boolean solve() {
	solutionCount = 0;
	return solveH(0);
    }

    private boolean solveH(int col) {
	boolean solutionFound = false;
	if (col + 1 > board.length) {
	    solutionCount += 1;
	    if (solution[0][0] == -1) {
		for (int r = 0; r < board.length; r ++) {
		    for (int c = 0; c < board[r].length; c ++) {
			solution[r][c] = board[r][c];
		    }
		}
	    }
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
	for (int i = 0; i < board.length; i ++) {
	    try {
		board[row + i][col] += 1;
	    } catch (ArrayIndexOutOfBoundsException e) {
		// It's fine
	    }
	    try {
		board[row - i][col] += 1;
	    } catch (ArrayIndexOutOfBoundsException e) {
		// It's fine
	    }
	    try {
		board[row][col + i] += 1;
	    } catch (ArrayIndexOutOfBoundsException e) {
		// It's fine
	    }
	    try {
		board[row][col - i] += 1;
	    } catch (ArrayIndexOutOfBoundsException e) {
		// It's fine
	    }
	    try {
		board[row + i][col + i] += 1;
	    } catch (ArrayIndexOutOfBoundsException e) {
		// It's fine
	    }
	    try {
		board[row - i][col + i] += 1;
	    } catch (ArrayIndexOutOfBoundsException e) {
		// It's fine
	    }
	    try {
		board[row + i][col - i] += 1;
	    } catch (ArrayIndexOutOfBoundsException e) {
		// It's fine
	    }
	    try {
		board[row - i][col - i] += 1;
	    } catch (ArrayIndexOutOfBoundsException e) {
		// It's fine
	    }
	}
	board[row][col] = -1;
	/* for (int r = 0; r < board.length; r ++) {
	    for (int c = 0; c < board.length; c ++) {
		System.out.print(board[r][c] + " ");
	    }
	    System.out.println();
	}
	System.out.println("----------"); */
    }

    private void removeQueen(int row, int col) {
	for (int i = 0; i < board.length; i ++) {
	    try {
		board[row + i][col] -= 1;
	    } catch (ArrayIndexOutOfBoundsException e) {
		// It's fine
	    }
	    try {
		board[row - i][col] -= 1;
	    } catch (ArrayIndexOutOfBoundsException e) {
		// It's fine
	    }
	    try {
		board[row][col + i] -= 1;
	    } catch (ArrayIndexOutOfBoundsException e) {
		// It's fine
	    }
	    try {
		board[row][col - i] -= 1;
	    } catch (ArrayIndexOutOfBoundsException e) {
		// It's fine
	    }
	    try {
		board[row + i][col + i] -= 1;
	    } catch (ArrayIndexOutOfBoundsException e) {
		// It's fine
	    }
	    try {
		board[row - i][col + i] -= 1;
	    } catch (ArrayIndexOutOfBoundsException e) {
		// It's fine
	    }
	    try {
		board[row + i][col - i] -= 1;
	    } catch (ArrayIndexOutOfBoundsException e) {
		// It's fine
	    }
	    try {
		board[row - i][col - i] -= 1;
	    } catch (ArrayIndexOutOfBoundsException e) {
		// It's fine
	    }
	}
	board[row][col] = 0;
    }
	

    public int getSolutionCount() {
	return solutionCount;
    }

    public String toString() {
	String s = "";
	if (solutionCount > 0) {
	    for (int r = 0; r < solution.length; r ++) {
		for (int c = 0; c < solution[r].length; c ++) {
		    if (solution[r][c] == -1) {
			s += "Q";
		    }
		    else {
			s += "_";
		    }
		    s += " ";
		}
		s += "\n";
	    }
	}
	else {
	    for (int r = 0; r < solution.length; r ++) {
		for (int c = 0; c < solution[r].length; c ++) {
		    s += "_ ";
		}
		s += "\n";
	    }
	}
	return s;
    }

    public static void main(String[] args) {
        for (int i = 1; i < 11; i ++) {
	    QueenBoard Penn = new QueenBoard(i);
	    Penn.solve();
	    System.out.println(Penn.getSolutionCount());
	    System.out.println(Penn);
	}
    }
}
