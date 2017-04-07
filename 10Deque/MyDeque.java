import java.util.NoSuchElementException;
public class MyDeque {
    private int front, back, size; // back is exclusive
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

    private boolean isEmpty() {
	return front == back && size == 0;
    }
    
    private boolean isFull() {
	return front == back && size == arr.length;
    }

    private void grow() {
	String[] newArr = new String[arr.length * 2];
	int count = 0;
	for (int i = front; i < arr.length; i ++) {
	    newArr[count] = arr[i];
	    count ++;
	}
	for (int i = back; i < front; i ++) {
	    newArr[count] = arr[i];
	    count ++;
	}
	front = 0;
	back = count - 1;
	arr = newArr;
    }

    public static void main(String[] args) {
	int[] a = {0, 1, 76, 34, 2};
	for (int i = 0; i < a.length; i ++) {
	    System.out.println(a[i]);
	}
    }
}
