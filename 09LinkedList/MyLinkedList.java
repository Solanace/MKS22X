public class MyLinkedList {
    private LNode start;
    private int size;
    
    private class LNode {
	private int data;
	private LNode next;

	private LNode(int data) {
	    this.data = data;
	}
	
	private LNode(int data, LNode next) {
	    this.data = data;
	    this.next = next;
	}
    }

    public MyLinkedList() {
    }

    public boolean add(int value) {
	start = new LNode(value, start);
	size ++;
	return true;
    }

    public boolean addTemp(int value) {
	if (start == null) {
	    start = new LNode(value);
	    return true;
	}
	else {
	    LNode current = start;
	    while (current.next != null) {
		current = current.next;
	    }
	    current.next = new LNode(value);
	    return true;
	}
    }

    public int size() {
	return size;
    }

    public String toString() {
	if (start == null) {
	    return "[]";
	}
	String s = "[";
	LNode currentNode = start;
	for (int i = 0; i < size - 1; i ++) {
	    s += currentNode.data + ", ";
	    currentNode = currentNode.next;
	}
	s += currentNode.data + "]";
	return s;
    }

    public int get(int index) {
	LNode currentNode = start;
	for (int i = 0; i < index; i ++) {
	    currentNode = currentNode.next;
	}
	return currentNode.data;
    }

    public int set(int index, int newValue) {
	LNode currentNode = start;
	for (int i = 0; i < index; i ++) {
	    currentNode = currentNode.next;
	}
	int oldValue = currentNode.data;
	currentNode.data = newValue;
	return oldValue;
    }

    public static void main(String[] args) {
	MyLinkedList Penn = new MyLinkedList();
	System.out.println(Penn);
	for (int i = 0; i < 10; i ++) {
	    int rand = (int)(Math.random() * 10);
	    System.out.print("Adding " + rand + ": ");
	    System.out.println(Penn.addTemp(rand));
	    System.out.println(Penn);
	}
	System.out.println(Penn);
    }
}
