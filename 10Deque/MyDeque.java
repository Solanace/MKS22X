import java.util.NoSuchElementException;
public class MyDeque {
    private int front, back, size; // inclusive front, exclusive back
    private String[] arr;

    public MyDeque() {
	arr = new String[10];
	size = 0;
    }

    public void addFirst(String item) {
	if (item == null) {
	    throw new NullPointerException();
	}
	else {
	    if (isFull()) {
		grow();
	    }
	    if (front == 0) {
		front = arr.length - 1;
	    }
	    else {
		front --;
	    }
	    arr[front] = item;
	    size ++;
	}
    }

    public void addLast(String item) {
	if (item == null) {
	    throw new NullPointerException();
	}
	else {
	    if (isFull()) {
		grow();
	    }
	    arr[back] = item;
	    if (back == arr.length - 1) {
		back = 0;
	    }
	    else {
		back ++;
	    }
	    size ++;
	}
    }

    public String removeFirst() {
	if (isEmpty()) {
	    throw new NullPointerException();
	}
	else {
	    String ret = arr[front];
	    if (front == arr.length - 1) {
		front = 0;
	    }
	    else {
		front ++;
	    }
	    size --;
	    return ret;
	}
    }

    public String removeLast() {
	if (isEmpty()) {
	    throw new NullPointerException();
	}
	else {
	    if (back == 0) {
		back = arr.length - 1;
	    }
	    else {
		back --;
	    }
	    size --;
	    String ret = arr[back];
	    return ret;
	}
    }

    public String getFirst() {
	if (isEmpty()) {
	    throw new NullPointerException();
	}
	else {
	    return arr[front];
	}
    }

    public String getLast() {
	if (isEmpty()) {
	    throw new NullPointerException();
	}
	else {
	    if (back == 0) {
		return arr[arr.length - 1];
	    }
	    else {
		return arr[back - 1];
	    }
	}
    }

    private boolean isEmpty() {
	return front == back && size == 0;
    }
    
    private boolean isFull() {
	return front == back && size == arr.length;
    }

    private void grow() {
	// only grows full arrays
	String[] newArr = new String[arr.length * 2];
	int count = 0;
	for (int i = front; i < arr.length; i ++) {
	    newArr[count] = arr[i];
	    count ++;
	}
	for (int i = 0; i < back; i ++) {
	    newArr[count] = arr[i];
	    count ++;
	}
	front = 0;
	back = count;
	arr = newArr;
    }

    public static void main(String[] args) {
        MyDeque Penn = new MyDeque();
        for (int i = 0; i < 100; i ++) {
	    Penn.addLast("" + i);
	}
	for (int i = 0; i < 100; i ++) {
	    System.out.println(Penn.removeLast());
	}
    }
}
