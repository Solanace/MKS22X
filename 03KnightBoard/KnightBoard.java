public class KnightBoard {
    private int[][] board;
    
    public KnightBoard(int startingRows, int startingCols) {
	if (startingRows <= 0 || startingCols <= 0) {
	    throw new IllegalArgumentException("Please only input positive integers");
	}
	board = new int[startingRows][startingCols];
    }

    public void solve() {
	for (int square = 0; square < board.length * board[0].length; square ++) {
	    if (solveH(square / board.length, square % board.length, 1)) {
		break; // Workaround, break statement only works for 1 loop and not nested
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
	    /*try {
		if (solveH(row - 1, col - 2, level + 1)) {
		    return true;
		}
	    } catch (ArrayIndexOutOfBoundsException e) {
	    }
	    try {
		if (solveH(row - 1, col + 2, level + 1)) {
		    return true;
		}
	    } catch (ArrayIndexOutOfBoundsException e) {
	    }
	    try {
		if (solveH(row + 1, col - 2, level + 1)) {
		    return true;
		}
	    } catch (ArrayIndexOutOfBoundsException e) {
	    }
	    try {
		if (solveH(row + 1, col + 2, level + 1)) {
		    return true;
		}
	    } catch (ArrayIndexOutOfBoundsException e) {
	    }
	    try {
		if (solveH(row - 2, col - 1, level + 1)) {
		    return true;
		}
	    } catch (ArrayIndexOutOfBoundsException e) {
	    }
	    try {
		if (solveH(row - 2, col + 1, level + 1)) {
		    return true;
		}
	    } catch (ArrayIndexOutOfBoundsException e) {
	    }
	    try {
		if (solveH(row + 2, col - 1, level + 1)) {
		    return true;
		}
	    } catch (ArrayIndexOutOfBoundsException e) {
	    }
	    try {
		if (solveH(row + 2, col + 1, level + 1)) {
		    return true;
		}
	    } catch (ArrayIndexOutOfBoundsException e) {
	    }*/
	    
	    if (solveH(row - 2, col - 1, level + 1)) {
		return true;
	    }
		
	    if (solveH(row - 2, col + 1, level + 1)) {
		return true;
	    }
		
	    if (solveH(row - 1, col - 2, level + 1)) {
		return true;
	    }
		
	    if (solveH(row - 1, col + 2, level + 1)) {
		return true;
	    }
		
	    if (solveH(row + 1, col - 2, level + 1)) {
		return true;
	    }
		
	    if (solveH(row + 1, col + 2, level + 1)) {
		return true;
	    }
		
	    if (solveH(row + 2, col - 1, level + 1)) {
		return true;
	    }
		
	    if (solveH(row + 2, col + 1, level + 1)) {
		return true;
	    }
	    board[row][col] = 0;
	}
	return false;
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
		else s += + board[row][col] + " ";
	    }
	    s += "\n";
	}
	s = s.substring(0, s.length() - 1); // strips last newline
	return s;
    }

    public static void main(String[] args) {
	KnightBoard Penn;
	for (int i = 1; i < 8; i ++) {
	    for (int j = 1; j < 8; j ++) {
	        Penn = new KnightBoard(i, j);
		Penn.solve();
		System.out.println("" + i + " x " + j);
		System.out.println(Penn);
		System.out.println("----------");
	        Penn = new KnightBoard(j, i);
		Penn.solve();
		System.out.println("" + j + " x " + i);
		System.out.println(Penn);
		System.out.println("----------");
	    }
	}
    }
}

