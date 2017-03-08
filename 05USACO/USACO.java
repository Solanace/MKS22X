import java.util.*;
import java.io.*;
public class USACO {
    public static int bronze(String fileName) {
	int ROWS, COLS, ELEVATION, COUNT_INSTRUCTIONS;
	Scanner sc = new Scanner("");
	String s = "";
	
	try {
	    sc = new Scanner(new File(fileName));
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
		else throw new IllegalArgumentException("Lake grid not formatted correctly!");
	    }
	    else throw new IllegalArgumentException("Lake grid not formatted correctly!");
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

    public static void main(String[] args) {
	USACO Penn = new USACO();
	System.out.println(Penn.bronze("makelake.1.in"));
    }
}
