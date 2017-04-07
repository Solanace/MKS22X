import java.util.LinkedList;
import java.util.NoSuchElementException;
public class MyQueue {
    private LinkedList<Integer> link;

    public MyQueue() {
	link = new LinkedList<Integer>();
    }

    public void enqueue(int item) {
	link.add(item);
    }

    public int dequeue() {
	if (isEmpty()) {
	    throw new NoSuchElementException();
	}
	else {
	    int ret = peek();
	    link.remove(0);
	    return ret;
	}
    }

    public int peek() {
	if (isEmpty()) {
	    throw new NoSuchElementException();
	}
	else {
	    return link.get(0);
	}
    }

    public int size() {
	return link.size();
    }

    public boolean isEmpty() {
	return size() == 0;
    }

    public static void main(String[] args) {
	MyQueue Penn = new MyQueue();
	System.out.println(Penn.size());
	Penn.enqueue(3);
	Penn.enqueue(6);
	System.out.println(Penn.peek());
	System.out.println(Penn.dequeue());
	System.out.println(Penn.dequeue());
	System.out.println(Penn.size());
	System.out.println(Penn.isEmpty());
	Penn.peek();
    }
}
    
