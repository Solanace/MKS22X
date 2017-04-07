import java.util.LinkedList;
import java.util.NoSuchElementException;
public class MyStack {
    private LinkedList<Integer> link;

    public MyStack() {
	link = new LinkedList<Integer>();
    }

    public void push(int item) {
	link.add(item);
    }

    public int pop() {
	if (isEmpty()) {
	    throw new NoSuchElementException();
	}
	else {
	    int ret = peek();
	    link.remove(link.size() - 1);
	    return ret;
	}
    }

    public int peek() {
	if (isEmpty()) {
	    throw new NoSuchElementException();
	}
	else {
	    return link.get(link.size() - 1);
	}
    }

    public int size() {
	return link.size();
    }

    public boolean isEmpty() {
	return size() == 0;
    }

    public static void main(String[] args) {
	MyStack Penn = new MyStack();
        System.out.println(Penn.size());
	Penn.push(3);
	Penn.push(6);
	System.out.println(Penn.peek());
	System.out.println(Penn.pop());
	System.out.println(Penn.pop());
	System.out.println(Penn.size());
	System.out.println(Penn.isEmpty());
	Penn.peek();
    }
}
