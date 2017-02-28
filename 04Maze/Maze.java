import java.util.*;
import java.io.*;
public class Maze {
    private char[][] maze;
    private boolean animate;
    private int startRow, startCol;

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
	//System.out.println(count);
	maze = new char[file.indexOf("\n")][countNewlines];
	//System.out.println(maze.length);
	//System.out.println(maze[0].length);

	for (int row = 0; row < maze.length; row ++) {
	    for (int col = 0; col < maze[row].length; col ++) {
		char c = file.charAt(row * maze[row].length + col + row);
		if (c == 'S') {
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
		maze[row][col] = c; // extra row accounts for newlines
		System.out.print(maze[row][col]);
	    }
	    System.out.println();
	}

	if (countS == 0) {
	    throw new IllegalArgumentException("File does not have a starting point!");
	}
	if (countE == 0) {
	    throw new IllegalArgumentException("File does not have an ending point!");
	}
    }

    private void wait(int millis){ //ADDED SORRY!
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

    /*public boolean solve() {
	//initialize startRow and startCol at the S
        maze[startRow][startCol] = ' ';
	return solve(startRow, startCol);
    }

    private boolean solve(int row, int col) {
	if (animate) {
	    System.out.println("\033[2J\033[1;1H"+this);
            wait(20);
        }

	// Solve thingy

	return false;
	}*/

    public static void main(String[] args) {
	Maze Penn = new Maze("data1.dat");//true animates the maze.
        
        /*Penn.setAnimate(true);
	  Penn.solve();*/

        //System.out.println(Penn);
    }
}
