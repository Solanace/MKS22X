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
	// no initialization required, just add ints
    }

    public int size() {
	return size;
    }

    public boolean add(int value) {
        if (start == null) {
	    start = new LNode(value);
	    size ++;
	    return true;
	}
	else {
	    LNode current = start;
	    while (current.next != null) {
		current = current.next;
	    }
	    current.next = new LNode(value);
	    size ++;
	    return true;
	}
    }

    public void add(int index, int value) {
	if (index < 0 || index > size) {
	    throw new IndexOutOfBoundsException("Attempted to add to an invalid index!");
	}
	if (size == 0) {
	    start = new LNode(value);
	    size ++;
	    return;
	}
	LNode current = start;
	for (int i = 0; i < index - 1; i ++) {
	    current = current.next;
	}
	LNode nodeAtIndex = current.next;
        LNode insert = new LNode(value, nodeAtIndex);
	current.next = insert;
	size ++;
    }

    public int remove(int index) {
	if (index < 0 || index >= size) {
	    throw new IndexOutOfBoundsException("Attempted to remove an invalid index!");
	}
	if (index == 0) {
	    int ret = start.data;
	    start = start.next;
	    size --;
	    return ret;
	}
	LNode current = start;
	for (int i = 0; i < index - 1; i ++) {
	    current = current.next;
	}
	LNode nodeAtIndex = current.next;
        int removed = nodeAtIndex.data;
	current.next = current.next.next;
	size --;
	return removed;
    }

    public int get(int index) {
	if (index < 0 || index >= size) {
	    throw new IndexOutOfBoundsException("Attempted to set an invalid index!");
	}
	LNode currentNode = start;
	for (int i = 0; i < index; i ++) {
	    currentNode = currentNode.next;
	}
	return currentNode.data;
    }

    public int set(int index, int newValue) {
	if (index < 0 || index >= size) {
	    throw new IndexOutOfBoundsException("Attempted to get an invalid index!");
	}
	LNode currentNode = start;
	for (int i = 0; i < index; i ++) {
	    currentNode = currentNode.next;
	}
	int oldValue = currentNode.data;
	currentNode.data = newValue;
	return oldValue;
    }

    public int indexOf(int value) {
	LNode current = start;
	for (int i = 0; i < size; i ++) {
	    if (current.data == value) {
		return i;
	    }
	    current = current.next;
	}
	return -1;
    }

    public String toString() {
	if (start == null) {
	    return "[]";
	}
	String s = "[";
	LNode current = start;
	while (current.next != null) {
	    s += current.data + ", ";
	    current = current.next;
	}
	s += current.data + "]";
	return s;
    }

    public static void main(String[] args) {
	MyLinkedList Penn = new MyLinkedList();
	Penn.add(-1);
	System.out.println(Penn);
	System.out.println(Penn.remove(0));
	for (int i = 0; i < 10; i ++) {
	    int rand = (int)(Math.random() * 10);
	    System.out.print("Adding " + rand + ": ");
	    System.out.println(Penn.add(rand));
	    System.out.println(Penn);
	    if (i % 2 == 0) {
		System.out.print("Removing index 0: ");
		System.out.println(Penn.remove(0));
		System.out.println(Penn);
	    }
	}
	Penn.add(5, -1);
	System.out.println(Penn);
	for (int i = 0; i < Penn.size(); i ++) {
	    int rand = (int)(Math.random() * 10);
	    System.out.println("Setting " + Penn.get(i) + " to " + rand);
	    System.out.println(Penn.set(i, rand));
	    System.out.println(Penn);
	}
    }
}
