import java.util.Arrays;
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
		if ((r == 0 || r == moves.length - 1) &&
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
		else if (r == 0 || r == moves.length - 1 ||
			 c == 0 || c == moves[r].length - 1 ||
			 ((r == 1 || r == moves.length - 2) && (c == 1 || c == moves[r].length - 2))) {
		    moves[r][c] = 4;
		}
		else if (r == 1 || r == moves.length - 2 ||
			 c == 1 || c == moves[r].length - 2) {
		    moves[r][c] = 6;
		}
		else {
		    moves[r][c] = 8;
		}
	    }
	}
    }
    
    public void solve() {
	if (board.length < 5 || board[0].length < 5) {
	    for (int square = 0; square < board.length * board[0].length; square ++) {
		if (solveH(square / board.length, square % board.length, 1)) {
		    break; // workaround, break statement only works for 1 loop, not a nested pair
		}
	    }
	}
	else {
	    for (int square = 0; square < board.length * board[0].length; square ++) {
		if (otherSolveH(square / board.length, square % board.length, 1)) {
		    break; // workaround, break statement only works for 1 loop, not a nested pair
		}
	    }
	}
    }

    private boolean solveH(int row, int col, int level) {
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

    private boolean otherSolveH(int row, int col, int level) {
        /*try {
	    Thread.sleep(0);
	} catch (InterruptedException e) {
	}*/
	moveKnight(row, col, level);
	if (level == board.length * board[0].length) {
	    return true;
	}
	if (level > 48 * 49 + 40) {
	    System.out.println("Level " + level);
	    System.out.println("Row " + row + ", Col " + col);
	    System.out.println(toString());
	    System.exit(0);
	}
	int[] priority = sortMoves(row, col);
	for (int i = 0; i < priority.length; i ++) {
	    if (otherSolveH(row + rowShift[priority[i] % 10],
			    col + colShift[priority[i] % 10],
			    level + 1)) {
		return true;
	    }
	}
	removeKnight(row, col);
	System.out.println("Level " + level);
	System.out.println("Row " + row + ", Col " + col);
	System.out.println(toString());
	System.exit(0);
	System.exit(0);
	return false;
    }

    private void moveKnight(int row, int col, int level) {
	for (int i = 0; i < 8; i ++) {
	    try {
		moves[row + rowShift[i]][col + colShift[i]] --;
	    } catch (ArrayIndexOutOfBoundsException e) {
		//It's fine
	    }
	}
	board[row][col] = level;
    }

    private void removeKnight(int row, int col) {
	board[row][col] = 0;
	for (int i = 0; i < 8; i ++) {
	    try {
		moves[row + rowShift[i]][col + colShift[i]] ++;
	    } catch (ArrayIndexOutOfBoundsException e) {
		//It's fine
	    }
	}
    }

    private int[] sortMoves(int row, int col) { // SOMEHOW BECOMES SIZELESS WHEN A SOLUTION SHOULD EXIST
	//System.out.println("Row " + row + " Col " + col);
	int[] temp = new int[8];
	// formatted as a 2-digit number, with tens place being numOfMoves and ones place being the index of rowShift/colShift
	for (int i = 0; i < 8; i ++) {
	    try {
		if (board[row + rowShift[i]][col + colShift[i]] > 0) {
		    //System.out.println("Space " + (row + rowShift[i]) + ", " + (col + colShift[i]) + " is occupied");
		    temp[i] = -1;
		    }
		else {
		    if (moves[row + rowShift[i]][col + colShift[i]] > 0) {
			temp[i] = moves[row + rowShift[i]][col + colShift[i]] * 10 + i;
		    }
		    else temp[i] = 10 + i;
		    //System.out.println("Space " + (row + rowShift[i]) + ", " + (col + colShift[i] + " WORKS"))
		}
	    } catch (ArrayIndexOutOfBoundsException e) {
		//System.out.println("Space " + (row + rowShift[i]) + ", " + (col + colShift[i]) + " DNE");
		temp[i] = -1;
	    }
	}

	/*for (int i = 0; i < temp.length; i ++) {
	       System.out.println(temp[i]);
	       }*/
	
	int count = 0;
	for (int i = 0; i < 8; i ++) {
	    if (temp[i] / 10 > 0) {
		count += 1;
	    }
	}
	
	if (count > 0) {
	    int[] priority = new int[count];
	    int loop = 0;
	    for (int i = 0; i < 8 && loop < count; i ++) {
		if (temp[i] > 0) {
		    priority[loop] = temp[i];
		    loop ++;
		}
	    }
	    Arrays.sort(priority);
	    return priority;
	    
	    /* for (int i = 0; i < temp.length; i ++) {
	       System.out.println(temp[i]);
	       }
	       System.out.println("------------------");
	       for (int i = 0; i < priority.length; i ++) {
	       System.out.println(priority[i]);
	       }
	       System.out.println("------------------");*/
	}
	return new int[0];
	    
	}
    
    public String toString() {
	String s = "";
	/*if (board[0][0] == 0) { // no solution
	    return s;
	    }*/
        for (int row = 0; row < board.length; row ++) {
	    for (int col = 0; col < board[row].length; col ++) {
		if (board[row][col] < 10) {
		    s += "  " + board[row][col] + " ";
		}
		else if (board[row][col] < 100) {
		    s += " " + board[row][col] + " ";
		}
		else if (board[row][col] < 1000) {
		    s += "" + board[row][col] + " ";
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
	KnightBoard Penn = new KnightBoard(49, 49);
	Penn.solve();
	System.out.println(Penn);
	/*for (int i = 3; i < 60; i ++) {
	    Penn = new KnightBoard(i, i);
	    Penn.solve();
	    System.out.println("" + i + "x" + i);
	    System.out.println(Penn);
	    System.out.println("--------------------");
	    }*/
	
    }
}
