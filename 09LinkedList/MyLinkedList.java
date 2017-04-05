import java.util.Iterator;
import java.util.NoSuchElementException;
public class MyLinkedList implements Iterable<Integer> {
    private LNode head, tail;
    private int size;
    
    private class LNode {
	private int data;
	private LNode next, prev;

	private LNode(int data) {
	    this.data = data;
	}
	
	private LNode(int data, LNode next) {
	    this.data = data;
	    this.next = next;
	}

	private LNode(int data, LNode next, LNode prev) {
	    this.data = data;
	    this.next = next;
	    this.prev = prev;
	}
    }

    public class LIterator implements Iterator<Integer> {
	private int index;
	private LNode current;

	public LIterator() {
	    index = 0;
	    this.current = MyLinkedList.this.head;
	}

	public LIterator(MyLinkedList a) {
	    index = 0;
	    this.current = a.head;
	}

	public boolean hasNext() {
	    return index < MyLinkedList.this.size;
	}

	public Integer next() {
	    if (hasNext()) {
		int ret = current.data;
		index ++;
		current = current.next;
		return ret;
	    }
	    throw new NoSuchElementException();
	}

	public void remove() {
	    throw new UnsupportedOperationException();
	}
    }

    public Iterator<Integer> iterator() {
	return new LIterator();
    }

    public MyLinkedList() {
	// no initialization required, just add ints
    }

    public int size() {
	return size;
    }

    private LNode getNode(int index) {
	LNode x;
	if (index < size / 2) {
	    x = head;
	    for (int i = 0; i < index; i ++) {
		x = x.next;
	    }
	    return x;
	}
	else {
	    x = tail;
	    for (int i = size - 1; i > index; i --) {
		x = x.prev;
	    }
	    return x;
	}
    }

    public boolean add(int value) {
        if (size == 0) {
	    head = new LNode(value);
	    tail = head;
	    size ++;
	    return true;
	}
	else {
	    tail.next = new LNode(value, null, tail);
	    tail = tail.next;
	    size ++;
	    return true;
	}
    }

    public void add(int index, int value) {
	if (index < 0 || index > size) {
	    throw new IndexOutOfBoundsException("Attempted to add to an invalid index!");
	}
	if (index == size) {
	    add(value);
	}
	else if (index == 0) {
	    head.prev = new LNode(value, head);
	    head = head.prev;
	    size ++;
	}
	else {
	    LNode b = getNode(index);
	    LNode a = b.prev;
	    LNode x = new LNode(value, b, a);
	    a.next = x;
	    b.prev = x;
	    size ++;
	}
    }

    public int remove(int index) {
	if (index < 0 || index >= size) {
	    throw new IndexOutOfBoundsException("Attempted to remove an invalid index!");
	}
	LNode b = getNode(index);
        int data = b.data;
	remove(b);
	return data;
    }

    private void remove(LNode b) {
	if (size == 1) {
	    head = null;
	    tail = null;
	    size --;
	}
	else if (b == head) {
	    head = head.next;
	    head.prev = null;
	    size --;
	}
	else if (b == tail) {
	    tail = tail.prev;
	    tail.next = null;
	    size --;
	}
	else {
	    LNode a = b.prev;
	    LNode c = b.next;
	    a.next = c;
	    c.prev = a;
	    size --;
	}
    }

    public int get(int index) {
	if (index < 0 || index >= size) {
	    throw new IndexOutOfBoundsException("Attempted to get an invalid index!");
	}
        return getNode(index).data;
    }

    public int set(int index, int newValue) {
	if (index < 0 || index >= size) {
	    throw new IndexOutOfBoundsException("Attempted to get an invalid index!");
	}
	LNode x = getNode(index);
	int oldValue = x.data;
	x.data = newValue;
	return oldValue;
    }

    public int indexOf(int value) {
	LNode current = head;
	for (int i = 0; i < size; i ++) {
	    if (current.data == value) {
		return i;
	    }
	    current = current.next;
	}
	return -1;
    }

    public String toString() {
	if (head == null) {
	    return "[]";
	}
	String s = "[";
	LNode current = head;
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
	a = randomArray(30);
	for (int i = 0; i < a.length; i ++) {
	    System.out.println(Penn);
	    int add = (int)(Math.random() * (Penn.size() + 1));
	    if (add == Penn.size()) {
	        System.out.println("Adding " + a[i] + " at " + add + ", shifting nothing");
	    }
	    else {
		System.out.println("Adding " + a[i] + " at " + add + ", shifting " + Penn.get(add));
	    }
	    Penn.add(add, a[i]);
	}
	System.out.println(Penn);
	System.out.println("Finished adding randomly");
	a = randomArray(20);
	for (int i = 0; i < a.length; i ++) {
	    System.out.println(Penn);
	    int replace = (int)(Math.random() * Penn.size());
	    System.out.println("Replacing " + Penn.get(replace) + " at " + replace + " with " + a[i]);
	    Penn.set(replace, a[i]);
	}
	System.out.println(Penn);
	System.out.println(Penn.size());

	a = randomArray(20);
	for (int i = 0; i < a.length; i ++) {
	    System.out.println(a[i] + " is located at " + Penn.indexOf(a[i]));
	}
	System.out.print("[");
	for (Integer i : Penn) {
	    System.out.print(i + ", ");
	}
	System.out.println("]");
	Iterator<Integer> it = Penn.iterator();
	System.out.print("[");
	while (it.hasNext()) {
	    System.out.print(it.next() + ", ");
	}
	System.out.println("]");
    }
}
