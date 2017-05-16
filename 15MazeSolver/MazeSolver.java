import java.util.*;
import java.io.*;
public class MazeSolver {
    private Maze m;
    private Frontier f;
    public MazeSolver(String filename) {
	this(filename, false);
    }

    public MazeSolver(String filename, boolean animate) {
        m = new Maze(filename, animate);
    }
	
    public void solve() {
	solve(1);
    }

    public void solve(int style) {
	if (style == 0) f = new FrontierStack();
	else if (style == 1) f = new FrontierQueue();
	else if (style == 2) f = new FrontierStack(); // wat
	else f = new FrontierPriorityQueue();
	f.add(m.start);
    }
    public static void main(String[] args) {
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

    public int row() {
	return row;
    }

    public int col() {
	return col;
    }

    public int distToStart() {
	return distToStart;
    }

    public int distToGoal() {
	return distToGoal;
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
    private ArrayDeque<Location> q;
    
    public FrontierQueue() {
	q = new ArrayDeque<Location>();
    }

    public void add(Location l) {
	q.add(l);
    }

    public Location next() {
	return q.remove();
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
	maxOrMin = 1;
    }

    public FrontierPriorityQueue(boolean isMaxHeap) {
	this();
	if (!isMaxHeap) maxOrMin = -1;
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
    private boolean animate;
    private int maxRows, maxCols;

    private static String go(int x, int y) {
	return ("\033[" + x + ";" + y + "H");
    }
    private static String color(int foreground, int background) {
	return ("\033[0;" + foreground + ";" + background + "m");
    }
    private void wait(int millis) {
	try {
	    Thread.sleep(millis);
	}
	catch (InterruptedException e) {
	}
    }
    public void clearTerminal() {
	System.out.println(CLEAR_SCREEN + "\033[1;1H");
    }
    public Maze(String filename) {
	this(filename, false);
    }
    public Maze(String filename, boolean b) {
	animate = b;
	ArrayList<char[]> lines = new ArrayList<char[]>();
	int startr=-1, startc=-1;
	int endr=-1,endc=-1;
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
	for (int i = 0; i < maze.length; i ++) {
	    maze[i] = lines.get(i);
	}
	for (int r = 0; r < maze.length; r ++) {
	    for (int c = 0; c < maze[r].length; c ++) {
		if (maze[r][c]=='S') {
		    if (startr == -1) {
			startr=r;
			startc=c;
		    }else{
			System.out.println("Multiple 'S' found!");
			System.exit(0);
		    }
		}
		if(maze[r][c]=='E'){
		    if(endr == -1){
			endr=r;
			endc=c;
		    }else{
			System.out.println("Multiple 'E' found!");
			System.exit(0);
		    }
		}
	    }
	}
	if(startr == -1 || endr == -1){
	    System.out.println("Missing 'S' or 'E' from maze.");
	    System.exit(0);

	}
	maxRows = maze.length;
	maxCols = maze[0].length;
	//IF YOU GET HERE, there is 1 start, and 1 end. The coords are in
	//startr,startc
	//endr,endc
	//you must initialize the Location start/end now!
    }

    public String toString(){
	int maxr = maze.length;
	int maxc = maze[0].length;
	String ans = "";
	for(int i = 0; i < maxr * maxc; i++){
	    int row = i/maxc;
	    int col = i%maxc;

	    char c =  maze[row][col];
	    ans+=c;
	    if( col == maxc-1 ){
		ans += "\n";
	    }

	}
	return ans + "\n";
    }


    public static String colorize(String s){
	String ans = "";
	Scanner in = new Scanner(s);
	while(in.hasNext()){
	    String line ="";
	    for(char c : in.nextLine().toCharArray()){
		if(c == '#'){
		    line+= color(37,47)+c;
		}
		else if(c == '@'){
		    line+= color(36,40)+c;
		}
		else if(c == '?'){
		    line+= color(36,40)+c;
		}
		else if(c == '.'){
		    line+= color(32,40)+c;
		}
		else if(c == ' '){
		    line+= color(35,40)+c;
		}else{
		    line+=color(37,40)+c;
		}

	    }
	    ans += line+color(37,40)+"\n";
	}
	return ans;
    }
}
