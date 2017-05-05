import java.util.ArrayList;
class MyMedian {
    private MyHeap smaller, bigger;
    private int median;

    public MyMedian() {
	smaller = new MyHeap(true);
	bigger = new MyHeap(false);
    }
}
public class MyHeap {
    private int maxOrMin;
    private ArrayList<Integer> arr;

    public MyHeap() {
	arr = new ArrayList<Integer>();
	arr.add(null); // size 0 should not be used
	maxOrMin = 1;
    }

    public MyHeap(boolean isMaxHeap) {
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
	Integer temp = arr.get(a);
	arr.set(a, arr.get(b));
	arr.set(b, temp);
    }

    public void add(Integer s) {
	arr.add(s);
        pushUp(arr.size() - 1);
    }

    private void pushUp(int i) {
	Integer s = arr.get(i);
	Integer p = arr.get(parent(i));
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
	    Integer current = arr.get(i);
	    Integer l = arr.get(left(i));
	    if (l.compareTo(current) * maxOrMin > 0) {
		swap(i, left(i));
		pushDown(left(i));
	    }
	}
	else {
	    Integer current = arr.get(i);
	    Integer l = arr.get(left(i));
	    Integer r = arr.get(right(i));
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

    public Integer remove() {
	if (arr.size() == 2) {
	    return arr.remove(arr.size() - 1);
	}
	else {
	    Integer top = arr.get(1);
	    Integer bottom = arr.remove(arr.size() - 1);
	    arr.set(1, bottom);
	    pushDown(1);
	    return top;
	}
    }

    public Integer peek() {
        if (arr.size() == 1) {
	    return null;
	}
	else {
	    return arr.get(1);
	}
    }

    public String toString() {
	return arr.toString();
    }

    public static void main(String[] args) {
	MyHeap Penn = new MyHeap(true);
	Penn.add(3);
	Penn.add(2);
	Penn.add(18);
	System.out.println(Penn);
	for (int i = 0; i < 3; i ++) {
	    System.out.println(Penn);
	    System.out.println(Penn.remove());
	}
    }
}
