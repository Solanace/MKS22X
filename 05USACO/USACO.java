import java.util.*;
import java.io.*;
public class USACO {
    public static int bronze(String filename) {
	int ROWS, COLS, ELEVATION, COUNT_INSTRUCTIONS;
	Scanner sc = new Scanner("");
	String s = "";
	
	try {
	    sc = new Scanner(new File(filename));
	    if (sc.hasNextLine()) {
		s = sc.nextLine();
	    }
	    else {
		throw new IllegalArgumentException("First row not formatted correctly!");
	    }
	} catch(FileNotFoundException e) {
	    System.out.println(e);
	    System.exit(0);
	}
	
	String[] parameters = s.split(" ");
	if (parameters.length == 4) {
	    ROWS = Integer.parseInt(parameters[0]);
	    COLS = Integer.parseInt(parameters[1]);
	    ELEVATION = Integer.parseInt(parameters[2]);
	    COUNT_INSTRUCTIONS = Integer.parseInt(parameters[3]);
	}
	else throw new IllegalArgumentException("First row not formatted correctly!");
	//System.out.println("" + ROWS + " " + COLS + " " + ELEVATION + " " + COUNT_INSTRUCTIONS);
	
	int[][] lakeMap = new int[ROWS][COLS];
	for (int r = 0; r < ROWS; r ++) {
	    if (sc.hasNextLine()) {
		s = sc.nextLine();
		String[] lakeRow = s.split(" ");
		if (lakeRow.length == COLS) {
		    for (int c = 0; c < COLS; c ++) {
			lakeMap[r][c] = Integer.parseInt(lakeRow[c]);
		    }
		}
		else throw new IllegalArgumentException("Lake map not formatted correctly!");
	    }
	    else throw new IllegalArgumentException("Lake map not formatted correctly!");
	}
	
	/*for (int r = 0; r < lakeMap.length; r ++) {
	    for (int c = 0; c < lakeMap[r].length; c ++) {
		System.out.print(lakeMap[r][c] + " ");
	    }
	    System.out.println();
	}
	System.out.println("-------------------------");*/
	
        for (int i = 0; i < COUNT_INSTRUCTIONS; i ++) {
	    int row, col, decrease;
	    String[] dataSet;
	    
	    if (sc.hasNextLine()) {
		dataSet = sc.nextLine().split(" ");
	    }
	    else throw new IllegalArgumentException("Stomping instructions not formatted correctly!");
	    if (dataSet.length == 3) {
		// subtracting by 1 because the rows and cols are formatted starting from 1, not 0
		row = Integer.parseInt(dataSet[0]) - 1;
		col = Integer.parseInt(dataSet[1]) - 1;
		decrease = Integer.parseInt(dataSet[2]);
	    }
	    else throw new IllegalArgumentException("Stomping instructions not formatted correctly!");
	    int largest = lakeMap[row][col];
	    for (int r = row; r < row + 3; r ++) {
		for (int c = col; c < col + 3; c ++) {
		    if (largest < lakeMap[r][c]) {
			largest = lakeMap[r][c];
		    }
		}
	    }
	    int maxHeight = largest - decrease;
	    for (int r = row; r < row + 3; r ++) {
		for (int c = col; c < col + 3; c ++) {
		    if (lakeMap[r][c] > maxHeight) {
			lakeMap[r][c] = maxHeight;
		    }
		}
	    }
	}

	int finalSum = 0;
	for (int r = 0; r < lakeMap.length; r ++) {
	    for (int c = 0; c < lakeMap[r].length; c ++) {
		if (lakeMap[r][c] < ELEVATION) {
		    lakeMap[r][c] = ELEVATION - lakeMap[r][c];
		}
		else {
		    lakeMap[r][c] = 0;
		}
		finalSum += lakeMap[r][c] * 72 * 72; // 6x6 feet
	    }
	}

	/*for (int r = 0; r < lakeMap.length; r ++) {
	    for (int c = 0; c < lakeMap[r].length; c ++) {
		System.out.print(lakeMap[r][c] + " ");
	    }
	    System.out.println();
	    }*/
	
	return finalSum;
    }

    public static int silver(String filename) {
	int ROWS, COLS, TIME, START_ROW, START_COL, END_ROW, END_COL;
	Scanner sc = new Scanner("");
	String s = "";

        try {
	    sc = new Scanner(new File(filename));
	    if (sc.hasNextLine()) {
		s = sc.nextLine();
	    }
	    else {
		throw new IllegalArgumentException("First row not formatted correctly!");
	    }
	} catch(FileNotFoundException e) {
	    System.out.println(e);
	    System.exit(0);
	}
	
	String[] parameters = s.split(" ");
	if (parameters.length == 3) {
	    ROWS = Integer.parseInt(parameters[0]);
	    COLS = Integer.parseInt(parameters[1]);
	    TIME = Integer.parseInt(parameters[2]);
	    //System.out.println("" + ROWS + " " + COLS + " " + TIME);
	}
	else throw new IllegalArgumentException("First row not formatted correctly!");

	char[][] pastureMap = new char[ROWS][COLS];
	for (int r = 0; r < ROWS; r ++) {
	    if (sc.hasNextLine()) {
		s = sc.nextLine();
		//System.out.println(s.length());
		String[] pastureRow = s.split("");
		//for (int i = 0; i < pastureRow.length; i ++) {
		//    System.out.print(pastureRow[i] + ", ");
		//}
		//System.out.println(pastureRow.length);
		if (pastureRow.length == COLS) {
		    for (int c = 0; c < COLS; c ++) {
			pastureMap[r][c] = pastureRow[c].charAt(0);
		    }
		}
		else if (pastureRow.length - 1 == COLS) {
		    for (int c = 0; c < COLS; c ++) {
			pastureMap[r][c] = pastureRow[c + 1].charAt(0);
		    }
		}
		//else throw new IllegalArgumentException("Pasture map not formatted correctly!");
	    }
	    else throw new IllegalArgumentException("Pasture map not formatted correctly!");
	}

	/*for (int r = 0; r < pastureMap.length; r ++) {
	    for (int c = 0; c < pastureMap[r].length; c ++) {
		System.out.print(pastureMap[r][c] + " ");
	    }
	    System.out.println();
	}
	System.out.println("-------------------------");*/

	s = sc.nextLine();
	parameters = s.split(" ");
	if (parameters.length == 4) {
	    START_ROW = Integer.parseInt(parameters[0]) - 1;
	    START_COL = Integer.parseInt(parameters[1]) - 1;
	    END_ROW = Integer.parseInt(parameters[2]) - 1;
	    END_COL = Integer.parseInt(parameters[3]) - 1;
	}
	else throw new IllegalArgumentException("Last row not formatted correctly!");
	//System.out.println("" + START_ROW + " " + START_COL + " " + END_ROW + " " + END_COL);
	return silverH(START_ROW, START_COL, END_ROW, END_COL, TIME, pastureMap);
    }

    private static int silverH(int r, int c, int endR, int endC, int time, char[][] pastureMap) {
	if (r > pastureMap.length - 1 || r < 0 || c > pastureMap[r].length - 1 || c < 0 || pastureMap[r][c] == '*') {
	    return 0;
	}
	if (time == 0) {
	    if (r == endR && c == endC) {
		return 1;
	    }
	    else return 0;
	}
	else return silverH(r + 1, c, endR, endC, time - 1, pastureMap) +
		 silverH(r - 1, c, endR, endC, time - 1, pastureMap) +
		 silverH(r, c + 1, endR, endC, time - 1, pastureMap) +
		 silverH(r, c - 1, endR, endC, time - 1, pastureMap);
    }
		 

    public static void main(String[] args) {
	if (args.length < 2) {
	    System.out.println("Please specify the following parameters for this USACO solver:\ncategory filename");
	    System.out.println("e.g., java USACO silver ctravel.5.in\ne.g., java USACO bronze makelake.3.in");
	}
	else {
	    USACO Penn = new USACO();
	    if (args[0].equals("bronze")) {
		System.out.println(Penn.bronze(args[1]));
	    }
	    else if (args[0].equals("silver")) {
		System.out.println(Penn.silver(args[1]));
	    }
	    else {
	        System.out.println("Please specify the following parameters for this USACO solver:\ncategory filename");
		System.out.println("e.g., java USACO silver ctravel.5.in\ne.g., java USACO bronze makelake.3.in");
	    }
	}
    }
}
