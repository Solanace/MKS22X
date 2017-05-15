public class MazeSolver {
    public class Location implements Comparable<Location> {
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
    }
    public static void main(String[] args) {
	Location Penn = new Location(3, 3, null, 2, 15);
	Location Nnep = new Location(3, 3, null, 13, 53);
	System.out.println(Penn.compareTo(Nnep));
	System.out.println(Nnep.compareTo(Penn));
    }
}
