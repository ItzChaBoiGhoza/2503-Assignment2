public class Node <T extends Comparable<T>> {
	
	/*
	 * Initialize variables
	 */
	private T data;
	private Node<T> next;

	/*
	 * Constructor for objects of class Node
	 */
	public Node(T d) {
		data = d;
		next = null;
	}
	
	/**
	 * Getter for data
	 * @return data
	 */
	public T getData() {
		return data;
	}

	/**
	 * Setter for data
	 * @param o
	 */
	public void setData(T o) {
		data = o;
	}

	/**
	 * Getter for next linked list
	 * @return next
	 */
	public Node<T> getNext() {
		return next;
	}

	/**
	 * Setter for the next linked list
	 * @param n
	 */
	public void setNext(Node<T> n) {
		next = n;
	}

	/**
	 * return size 
	 * @return size = 0
	 */
	public int size() {
		return 0;
	}
}
