import java.util.*;
import java.io.*;
public class MazeSolver {
    /*
      '@' means correct route
      '#' means a wall
      '?' means frontier
      '.' means already covered path
      ' ' means unexplored path
    */
    private Maze m;
    private Frontier f;
    private boolean animate;
    public MazeSolver(String filename) {
	m = new Maze(filename);
    }
    
    public MazeSolver(String filename, boolean animate) {
        m = new Maze(filename);
	this.animate = animate;
    }
	
    public void solve() {
	solve(1);
    }

    public void solve(int style) {
	if (style == 0) {
	    f = new FrontierStack();
	}
	else if (style == 1) {
	    f = new FrontierQueue();
	}
	else if (style == 2) {
	    f = new FrontierPriorityQueue(); // aStar is false
	}
	else if (style == 3) {
	    f = new FrontierPriorityQueue();
	}
	f.add(m.getStart());
	Location current = f.next();
	while(current.getRow() != m.getEnd().getRow() || current.getCol() != m.getEnd().getCol()) {
	    // Debugging
	    //System.out.println(current.getAStar());
	    //System.out.println(current.getRow() + ", " + current.getCol());
	    //System.out.println(current.priority());
	    //System.out.println(current.getStart());
	    if (animate) {
		System.out.println(m.toString());
	    }
	    m.set(current.getRow(), current.getCol(), '.');
	    Location[] paths = getPaths(current, style);
	    for (int i = 0; i < paths.length; i ++) {
		f.add(paths[i]);
		m.set(paths[i].getRow(), paths[i].getCol(), '?');
	    }
	    current = f.next();
	}
	m.set(current.getRow(), current.getCol(), 'E');
	while (current.getPrevious() != null) {
	    current = current.getPrevious();
	    m.set(current.getRow(), current.getCol(), '@');
	}
	if (animate) {
	    System.out.println(m.toString());
	}
    }

    public Location[] getPaths(Location l, int style) {
	int[] rShift = {1, -1, 0, 0};
	int[] cShift = {0, 0, 1, -1};
        int r = l.getRow();
	int c = l.getCol();
	int count = 0;
	for (int i = 0; i < 4; i ++) {
	    if (isValid(r + rShift[i], c + cShift[i])) count ++;
	}
	Location[] paths = new Location[count];
	int pos = 0;
	for (int i = 0; i < 4; i ++) {
	    if (isValid(r + rShift[i], c + cShift[i])) {
		if (style == 3) {
		    paths[pos] = new Location(r + rShift[i], c + cShift[i], l, l.getStart() + 1, getManhattanDist(r + rShift[i], c + cShift[i]), true);
		}
		else {
		    paths[pos] = new Location(r + rShift[i], c + cShift[i], l, l.getStart() + 1, getManhattanDist(r + rShift[i], c + cShift[i]), false);
		}
		pos ++;
	    }
	}
	return paths;
    }

    public int getManhattanDist(int r, int c) {
	return Math.abs(m.getEnd().getRow() - r) + Math.abs(m.getEnd().getCol() - c);
    }

    private boolean isValid(int r, int c) {
	return m.get(r, c) == ' ' || m.get(r, c) == 'E';
    }
	    

    public String toString() {
	return m.toString();
    }

    public String toString(int time) {
	return m.toString(time);
    }
    
    public static void main(String[] args) {
	if (args.length < 2) {
	    System.out.println("Please format like so: java MazeSolver [filename] [method] [animate]");
	    System.out.println("For example: java MazeSolver data5.txt 2 animate");
	}
	else if (args.length == 2) {
	    MazeSolver Penn = new MazeSolver(args[0]);
	    Penn.solve(Integer.parseInt(args[1]));
	}
	else {
	    MazeSolver Penn = new MazeSolver(args[0], args[2].equals("animate"));
	    Penn.solve(Integer.parseInt(args[1]));
	}
    }
}

class Location implements Comparable<Location> {
    private boolean aStar;
    private int row, col, distToStart, distToGoal;
    private Location prev;

    public Location(int r, int c, Location previous, int distanceToStart, int distanceToGoal) {
	row = r;
	col = c;
	distToStart = distanceToStart;
	distToGoal = distanceToGoal;
	prev = previous;
    }

    public Location(int r, int c, Location previous, int distanceToStart, int distanceToGoal, boolean aStar) {
	row = r;
	col = c;
	distToStart = distanceToStart;
	distToGoal = distanceToGoal;
	prev = previous;
	this.aStar = aStar;
    }

    public int compareTo(Location l) {
	if (aStar) {
	    return (distToStart + distToGoal) - (l.distToStart + l.distToGoal);
	}
	else {
	    return distToGoal - l.distToGoal;
	}
    }

    public boolean getAStar() {
	return aStar;
    }

    public int getRow() {
	return row;
    }

    public int getCol() {
	return col;
    }

    public int getStart() {
	return distToStart;
    }

    public int getGoal() {
	return distToGoal;
    }

    public Location getPrevious() {
	return prev;
    }

    public int priority() {
	if (aStar) return distToStart + distToGoal;
	else return distToGoal;
    }
}

interface Frontier {
    public void add(Location l);
    public Location next();
    public int size();
}

class FrontierStack implements Frontier {
    private Stack<Location> s;
    
    public FrontierStack() {
	s = new Stack<Location>();
    }

    public void add(Location l) {
	s.push(l);
    }

    public Location next() {
	return s.pop();
    }

    public int size() {
	return s.size();
    }
}

class FrontierQueue implements Frontier {
    private LinkedList<Location>  q;
    
    public FrontierQueue() {
	q = new LinkedList<Location>();
    }

    public void add(Location l) {
	q.add(l);
    }

    public Location next() {
	return q.remove(0);
    }

    public int size() {
	return q.size();
    }
}

class FrontierPriorityQueue implements Frontier {
    private int maxOrMin;
    private ArrayList<Location> arr;

    public FrontierPriorityQueue() {
	arr = new ArrayList<Location>();
	arr.add(null); // size 0 should not be used
	maxOrMin = -1;
    }

    private int left(int n) {
	return n * 2;
    }

    private int right(int n) {
	return n * 2 + 1;
    }

    private int parent(int n) {
	return n / 2;
    }

    private void swap(int a, int b) {
	Location temp = arr.get(a);
	arr.set(a, arr.get(b));
	arr.set(b, temp);
    }

    public void add(Location s) {
	arr.add(s);
        pushUp(arr.size() - 1);
    }

    private void pushUp(int i) {
	Location s = arr.get(i);
	Location p = arr.get(parent(i));
	while (parent(i) > 0 && s.compareTo(p) * maxOrMin > 0) {
	    swap(i, parent(i));
	    i = parent(i);
	    s = arr.get(i);
	    p = arr.get(parent(i));
	}
    }

    private void pushDown(int i) {
	if (right(i) >= arr.size()) {
	    if (left(i) >= arr.size()) {
		return;
	    }
	    Location current = arr.get(i);
	    Location l = arr.get(left(i));
	    if (l.compareTo(current) * maxOrMin > 0) {
		swap(i, left(i));
		pushDown(left(i));
	    }
	}
	else {
	    Location current = arr.get(i);
	    Location l = arr.get(left(i));
	    Location r = arr.get(right(i));
	    if (l.compareTo(r) * maxOrMin >= 0 && l.compareTo(current) * maxOrMin > 0) {
		swap(i, left(i));
		pushDown(left(i));
	    }
	    else if (r.compareTo(l) * maxOrMin >= 0 && r.compareTo(current) * maxOrMin > 0) {
		swap(i, right(i));
		pushDown(right(i));
	    }
	}
    }

    public Location next() {
	if (arr.size() == 2) {
	    return arr.remove(arr.size() - 1);
	}
	else {
	    Location top = arr.get(1);
	    Location bottom = arr.remove(arr.size() - 1);
	    arr.set(1, bottom);
	    pushDown(1);
	    return top;
	}
    }

    public int size() {
	return arr.size();
    }
}

class Maze {
    private static final String CLEAR_SCREEN = "\033[2J";
    private static final String HIDE_CURSOR = "\033[?25l";
    private static final String SHOW_CURSOR = "\033[?25h";
    Location start, end;
    private char[][] maze;
    private int maxRows, maxCols;

    public Location getStart() {
	return start;
    }
    
    public Location getEnd() {
	return end;
    }

    private static String go(int x, int y) {
	return ("\033[" + x + ";" + y + "H");
    }
    
    private static String color(int foreground, int background) {
	return ("\033[0;" + foreground + ";" + background + "m");
    }

    public void clearTerminal() {
	System.out.println(CLEAR_SCREEN + "\033[1;1H");
    }
    
    public Maze(String filename) {
	ArrayList<char[]> lines = new ArrayList<char[]>();
	int startr = -1, startc = -1;
	int endr=-1, endc=-1;
	try {
	    Scanner in = new Scanner(new File(filename));
	    while(in.hasNext()) {
		lines.add(in.nextLine().toCharArray());
	    }
	} catch(FileNotFoundException e) {
	    System.out.println("File not found: " + filename);
	    System.exit(1);
	}
	maze = new char[lines.size()][];
	for(int i = 0; i < maze.length; i ++) {
	    maze[i] = lines.get(i);
	}
	for(int r = 0; r < maze.length; r ++) {
	    for(int c = 0; c < maze[r].length; c ++) {
		if(maze[r][c] == 'S') {
		    //erase the S
		    maze[r][c] = ' ';
		    if(startr == -1) {
			startr = r;
			startc = c;
		    }
		    else {
			System.out.println("Multiple 'S' found!");
			System.exit(0);
		    }
		}

		if(maze[r][c] == 'E') {
		    if(endr == -1) {
			endr = r;
			endc = c;
		    }
		    else {
			System.out.println("Multiple 'E' found!");
			System.exit(0);
		    }
		}
	    }
	}
	if(startr == -1 || endr == -1) {
	    System.out.println("Missing 'S' or 'E' from maze.");
	    System.exit(0);
	}
	maxRows = maze.length;
	maxCols = maze[0].length;


	end = new Location(endr, endc, null, 0, 0, false);
	int d = Math.abs(endr - startr) + Math.abs(startc - endc);
	start = new Location(startr, startc, null, 0, d, false);
    }

    public String toString(int delay) {
	try {
	    Thread.sleep(delay);
	} catch(InterruptedException e) {
	    
	}
	return HIDE_CURSOR + CLEAR_SCREEN + go(1,1) + colorize(toString()) + SHOW_CURSOR;
    }
    
    public String toString() {
	int maxr = maze.length;
	int maxc = maze[0].length;
	String ans = "";
	for(int i = 0; i < maxr * maxc; i ++) {
	    int row = i / maxc;
	    int col = i % maxc;

	    char c = maze[row][col];
	    ans += c;
	    if(col == maxc - 1) {
		ans += "\n";
	    }
	}
	return ans + "\n";
    }

    public char get(int row, int col) {
	return maze[row][col];
    }
    
    public void set(int row, int col, char n) {
	maze[row][col] = n;
    }
    
    public static String colorize(String s) {
	String ans = "";
	Scanner in = new Scanner(s);
	while(in.hasNext()) {
	    String line = "";
	    for(char c : in.nextLine().toCharArray()) {
		if(c == '#') {
		    line += color(37,47) + c;
		}
		else if(c == '@') {
		    line += color(33,40) + c;
		}
		else if(c == '?') {
		    line += color(37,42) + c;
		}
		else if(c == '.') {
		    line += color(36,40) + c;
		}
		else if(c == ' ') {
		    line += color(35,40) + c;
		}
		else {
		    line += color(37,40) + c;
		}

	    }
	    ans += line+color(37,40) + "\n";
	}
	return ans;
    }
}
