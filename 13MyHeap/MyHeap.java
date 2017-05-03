import java.util.ArrayList;
public class MyHeap {
    private int size, maxOrMin;
    private ArrayList<String> arr;
    public MyHeap() {
	arr = new ArrayList<String>();
    }

    private String leftChild(int n) {
	return arr.get(n * 2);
    }

    private String rightChild(int n) {
	return arr.get(n * 2 + 1);
    }

    private String parent(int n) {
	return arr.get(n / 2);
    }

    private void pushUp(int pos) {
        String pushMe = arr.get(pos);
	while (pushMe.compareTo(parent(pos)) * maxOrMin > 0) {
	    String temp = parent(pos);

    public void add(String s) {
	arr.add(s);
        pushUp(size);
	size ++;
    }

    public String peek() {
	return arr.get(size);
    }
