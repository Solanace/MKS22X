import java.util.*;
import java.io.*;
public class Maze {
    private char[][] maze;
    private boolean animate;
    private int startRow, startCol;
    private int[] rowShift = {-1,  0,  1,  0};
    private int[] colShift = { 0,  1,  0, -1};
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public Maze(String fileName) {
	Scanner sc;
	int countNewlines = 0, countS = 0, countE = 0;
	String file = "";
	try {
	    sc = new Scanner(new File(fileName));
	    while (sc.hasNextLine()) {
		file += sc.nextLine() + "\n";
		countNewlines += 1;
		//System.out.println(file);
	    }
	}
	catch (FileNotFoundException e) {
	    System.out.println(e);
	    System.exit(0);
	}
	file = file.substring(0, file.length() - 1);
	//System.out.println(file);
	//System.out.println(file.length());
	//System.out.println(countNewlines);
	maze = new char[countNewlines][file.indexOf("\n")];
	//System.out.println(maze.length);
	//System.out.println(maze[0].length);

	for (int row = 0; row < maze.length; row ++) {
	    for (int col = 0; col < maze[row].length; col ++) {
		//System.out.println("Row " + row + ", Col " + col);
		char c = file.charAt(row * maze[row].length + col + row);
		if (c == 'S') {
		    startRow = row;
		    startCol = col;
		    countS += 1;
		    if (countS > 1) {
			throw new IllegalArgumentException("File has multiple starting points!");
		    }
		}
		if (c == 'E') {
		    countE += 1;
		    if (countE > 1) {
			throw new IllegalArgumentException("File has multiple ending points!");
		    }
		}
		maze[row][col] = c;
		//System.out.print(maze[row][col]);
	    }
	    //System.out.println();
	}

	if (countS == 0) {
	    throw new IllegalArgumentException("File does not have a starting point!");
	}
	if (countE == 0) {
	    throw new IllegalArgumentException("File does not have an ending point!");
	}
    }

    private void wait(int millis){
         try {
             Thread.sleep(millis);
         }
         catch (InterruptedException e) {
         }
     }

    public void setAnimate(boolean b) {
	animate = b;
    }

    public void clearTerminal() {
	System.out.println("\033[2J\033[1;1H");
    }

    public boolean solve() {
	//initialize startRow and startCol at the S
        maze[startRow][startCol] = ' ';
	return solve(startRow, startCol);
    }

    private boolean solve(int row, int col) {
	//System.out.println("Row " + row + ", Col " + col);
	if (row + 1 > maze.length || row - 1 < 0 || col + 1 > maze[0].length || col - 1 < 0) {
	    return false;
	}

	if (maze[row][col] == '*' || maze[row][col] == '.' || maze[row][col] == '#') {
	    return false;
	}
	
	if (animate) {
	    System.out.println("\033[2J\033[1;1H" + this);
            wait(30);
	}
	
	if (maze[row][col] == 'E') {
	    return true;
	}

	maze[row][col] = '*';
	for (int i = 0; i < 4; i ++) {
	    if (solve(row + rowShift[i], col + colShift[i])) {
		return true;
	    }
	}
	maze[row][col] = 'x';

	if (animate) {
	    System.out.println("\033[2J\033[1;1H" + this);
            wait(20);
	}

	return false;
    }

    public String toString() {
	String s = "";
	for (int row = 0; row < maze.length; row ++) {
	    for (int col = 0; col < maze[row].length; col ++) {
		if (maze[row][col] == '#') {
		    s += '█';
		}
		else if (maze[row][col] == 'x') {
		    s += ANSI_RED + 'x' + ANSI_RESET;
		}
		else {
		    s += maze[row][col];
		}
	    }
	    s += "\n";
	}
	return s;
    }

    public static void main(String[] args) {
	if (args.length < 1) {
	    System.out.println("Welcome to the maze solver! Please specify the following parameters for your maze:\nfilename [animate]\ne.g., data3.dat animate");
	}
	else {
	    Maze Penn = new Maze(args[0]);
	    if (args.length > 1) {
		Penn.setAnimate(args[1].equals("animate"));
	    }
	    else {
		Penn.setAnimate(false);
	    }
	    Penn.solve();
	}
    }
}
