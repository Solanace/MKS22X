public class KnightBoard {
    private int[][] board, moves;
    private static int[] rowShift = {-2, -2, -1, -1,  1,  1,  2,  2};
    private static int[] colShift = {-1,  1,  -2, 2, -2,  2, -1,  1};
    
    public KnightBoard(int startingRows, int startingCols) {
	if (startingRows <= 0 || startingCols <= 0) {
	    throw new IllegalArgumentException("Please only input positive integers");
	}
	board = new int[startingRows][startingCols];
	moves = new int[startingRows][startingCols];
	for (int r = 0; r < moves.length; r ++) {
	    for (int c = 0; c < moves[r].length; c ++) {
		/* if ((r == 0 || r == moves.length - 1) &&
		    (c == 0 || c == moves[r].length - 1)) {
		    moves[r][c] = 2;
		}
		else if ((r != 1 || c != 1) &&
			 (r != board.length - 2 || c != 1) &&
			 (r != 1 || c != board[r].length - 2) &&
			 (r != board.length - 2 || c != board[r].length - 2) &&
			 (r < 2 || r > moves.length - 3) &&
			 (c < 2 || c > moves[r].length - 3)) {
		    moves[r][c] = 3;
		}
		else if ((r == 1 && c == 1) ||
			 r == 0 || r == moves.length - 1 ||
			 c == 0 || c == moves[r].length - 1 ||
			 r != c &&
			 (r == 2 || r == moves.length - 2) &&
			 (c == 2 || c == moves[r].length - 2)) {
		    moves[r][c] = 4;
		}
		else if (r == 1 || r == moves.length - 2 ||
			 c == 1 || c == moves[r].length - 2) {
		    moves[r][c] = 6;
		}
		else {
		    moves[r][c] = 8;
		    }*/ 
	    }
	}
    }

    public void solve() {
	for (int square = 0; square < board.length * board[0].length; square ++) {
	    if (solveH(square / board.length, square % board.length, 1)) {
		break; // workaround, break statement only works for 1 loop, not a nested pair
	    }
	}
    }

    public boolean solveH(int row, int col, int level) {
	if (level > board.length * board[0].length) {
	    return true;
	}
	if (row < 0 || row > board.length - 1 || col < 0 || col > board[0].length - 1) {
	    return false;
	}
	if (board[row][col] == 0) {
	    board[row][col] = level;
	    for (int i = 0; i < 8; i ++) {
		if (solveH(row + rowShift[i], col + colShift[i], level + 1)) {
		    return true;
		}
	    }
	    board[row][col] = 0;
	}
	return false;
    }

    public boolean otherSolveH(int row, int col, int level) {
	if (level > board.length * board[0].length) {
	    return true;
	}
	if (row < 0 || row > board.length - 1 || col < 0 || col > board[0].length - 1) {
	    return false;
	}
	if (board[row][col] == 0) {
	    board[row][col] = level;
	}
	return true;
    }
    
    public String toString() {
	String s = "";
	if (board[0][0] == 0) { // no solution
	    return s;
	}
        for (int row = 0; row < board.length; row ++) {
	    for (int col = 0; col < board[row].length; col ++) {
		if (board[row][col] < 10) {
		    s += " " + board[row][col] + " ";
		}
		else s += board[row][col] + " ";
	    }
	    s += "\n";
	}
	s = s.substring(0, s.length() - 1); // strips last newline
	return s;
    }

    public String otherToString() {
	String s = "";
	if (moves[0][0] == 0) { // no solution
	    return s;
	}
        for (int row = 0; row < moves.length; row ++) {
	    for (int col = 0; col < moves[row].length; col ++) {
		if (moves[row][col] < 10) {
		    s += " " + moves[row][col] + " ";
		}
		else s += moves[row][col] + " ";
	    }
	    s += "\n";
	}
	s = s.substring(0, s.length() - 1); // strips last newline
	return s;
    }

    public static void main(String[] args) {
	KnightBoard Penn = new KnightBoard(6, 8);
	Penn.solve();
	System.out.println(Penn.otherToString());
	/* for (int i = 1; i < 10; i ++) {
	    for (int j = 1; j < 10; j ++) { // starting from 1 or 2 never works
		Penn = new KnightBoard(i, j);
		Penn.solve();
		System.out.println("" + i + "x" + j);
		System.out.println(Penn.otherToString());
		System.out.println("----------");
		System.out.println(Penn);
		System.out.println("--------------------");
	    }
	    }*/ 
    }
}

