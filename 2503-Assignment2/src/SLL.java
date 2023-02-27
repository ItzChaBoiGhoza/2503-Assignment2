package assignment2;

import java.util.Comparator;

public class SLL<T extends Comparable<T>> {

	private Node<T> head;
	private Comparator<T> comparator;

	public SLL() {
		this.head = null;
		this.comparator = null;
	}
	
	public SLL(Comparator<T> externalComp)  {
		this.head = null;
		this.comparator = externalComp;
	}
	
	// Public Methods

	/**
	 * Return the number of elements in the list.
	 * 
	 * @return int number of elements in the list.
	 */

	public void deleteItem(T data) {
		 if(head == null) {
			 return;
		 }
		 
		 if(head.getData().equals(data)) {
			 head = head.getNext();
			 return;
		 } 
		 Node<T> current = head;
		 while(current.getNext() != null) {
			 if(current.getNext().getData().equals(data)) {
				 current.setNext(current.getNext().getNext());
				 return;
			 }
			 
			 current = current.getNext();
		 }

	}
	
	public Node<T> findItem(T key) {
		
		Node<T> currentNode = head;
		
		while(currentNode != null) {
			
			if (currentNode.getData().equals(key))
				return currentNode;
			else
				currentNode = currentNode.getNext();
		}
		return null;
		
	}

	public void addInOrder(T n) {
		Node<T> newNode = new Node<>(n);
		if(head == null) {
			head = newNode;
			return;
		}
		
		if(compare(n, head.getData()) <= 0) {
			newNode.setNext(head);
			head = newNode;
			return;
		}
		
		Node<T> currentNode = head;
		while(currentNode.getNext() != null && compare(n, currentNode.getNext().getData()) > 0) {
			currentNode = currentNode.getNext();
		}
		newNode.setNext(currentNode.getNext());
		currentNode.setNext(newNode);
		
	}

	private int compare(T a1, T a2) {
		if (comparator == null)
			return a1.compareTo(a2);
		else
			return comparator.compare(a1, a2);
	}

}
