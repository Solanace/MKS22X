import java.util.*;
import java.io.*;
public class Maze {
    private char[][] maze;
    private boolean animate;
    private int startRow, startCol;

    public Maze(String fileName) {
	Scanner sc;
	try {
	    sc = new Scanner(new File(fileName));
	    String blah;
	    while (sc.hasNext()) {
		blah = sc.next();
		System.out.println(blah);
	    }
	} catch (FileNotFoundException e) {
	    System.out.println(e);
	}
	//To be filled in
    }

    /*public void setAnimate(boolean b) {
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

        System.out.println(Penn);
    }
}
