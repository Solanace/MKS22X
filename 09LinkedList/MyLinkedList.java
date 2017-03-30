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

    public static int[] randomArray(int size) {
	int[] ary = new int[size];
	for (int i = 0; i < ary.length; i ++) {
	    ary[i] = (int)(Math.random() * ary.length - ary.length / 2);
	}
	return ary;
    }

    public static void main(String[] args) {
	MyLinkedList Penn = new MyLinkedList();
	int[] a = randomArray(5);
	for (int i = 0; i < a.length; i ++) {
	    System.out.println(Penn);
	    System.out.println("Adding " + a[i]);
	    Penn.add(a[i]);
	    if (i % 2 == 0) {
		System.out.println(Penn);
		int remove = (int)(Math.random() * Penn.size());
		System.out.println("Removing " + Penn.remove(remove) + " at " + remove);
	    }
	}
	System.out.println(Penn);
	System.out.println("Finished adding to the back");
	a = randomArray(10);
	for (int i = 0; i < a.length; i ++) {
	    System.out.println(Penn);
	    int add = (int)(Math.random() * Penn.size());
	    System.out.println("Adding " + a[i] + " at " + add + ", replacing " + Penn.get(add));
	    Penn.add(add, a[i]);
	}
	System.out.println(Penn);
	System.out.println("Finished adding randomly");
	a = randomArray(10);
	for (int i = 0; i < a.length; i ++) {
	    System.out.println(Penn);
	    int replace = (int)(Math.random() * Penn.size());
	    System.out.println("Replacing " + Penn.get(replace) + " at " + replace + " with " + a[i]);
	    Penn.set(replace, a[i]);
	}
	System.out.println(Penn);

	a = randomArray(10);
	for (int i = 0; i < a.length; i ++) {
	    System.out.println(a[i] + " is located at " + Penn.indexOf(a[i]));
	}
    }
}
