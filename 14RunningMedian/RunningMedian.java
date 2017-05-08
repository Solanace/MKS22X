import java.util.NoSuchElementException;
public class RunningMedian {
    private MyHeap min, max;
    private double median;

    public RunningMedian() {
	min = new MyHeap(true);
	max = new MyHeap(false);
    }

    public void add(int num) {
	if (num <= median) {
	    min.add(num);
	}
        else {
	    max.add(num);
	}

	if (min.size() - max.size() == 2) {
	    max.add(min.remove());
	    median = (min.peek() + max.peek()) / 2.0;
	}
	else if (max.size() - min.size() == 2) {
	    min.add(max.remove());
	    median = (min.peek() + max.peek()) / 2.0;
	}
	else if (max.size() == min.size()) {
	    median = (min.peek() + max.peek()) / 2.0;
	}
	else {
	    if (min.size() - max.size() == 1) {
		median = min.peek();
	    }
	    else {
		median = max.peek();
	    }
	}
    }

    public double getMedian() {
	if (min.size() == 1 && max.size() == 1) {
	    throw new NoSuchElementException("Empty set!");
	}
	else {
	    return median;
	}
    }

    public static void main(String[] args) {
	RunningMedian Penn = new RunningMedian();
	Penn.add(12);
	System.out.println(Penn.getMedian());
	Penn.add(17);
	System.out.println(Penn.getMedian());
	Penn.add(3);
	System.out.println(Penn.getMedian());
	Penn.add(14);
	System.out.println(Penn.getMedian());
	Penn.add(5);
	System.out.println(Penn.getMedian());
	Penn.add(8);
	System.out.println(Penn.getMedian());
	Penn.add(7);
	System.out.println(Penn.getMedian());
	Penn.add(15);
	System.out.println(Penn.getMedian());
    }
}
