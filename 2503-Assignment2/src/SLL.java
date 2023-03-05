import java.util.Comparator;

public class SLL<T extends Comparable<T>> {

	private Node<T> head,tail;
	private int size;
	private Comparator<T> comparator;

	public SLL() {
		head = null;
		size = 0;
		comparator = null;
	}
	
	public SLL(Comparator<T> externalComp)  {
		head = null;
		size = 0;
		comparator = externalComp;
	}
	
	public int size() {
		Node<T> temp = head;
        while (temp != null) {
            temp = temp.getNext();
            size++;
        }
        return size;
	}
	
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
	
	public Node<T> findItem(T n) {
		Node<T> currentNode = head;
		while(currentNode != null) {
			if (currentNode.getData().equals(n))
				return currentNode;
			else
				currentNode = currentNode.getNext();
		}
		return null;
	}
	
	public void printList() {
		Node<T> currentNode = head;
		while (currentNode != null) {
			System.out.println(currentNode.getData().toString());
			currentNode = currentNode.getNext();
		}
	}
	
	public void addHead(T n) {
		Node<T> newNode = new Node<>(n);
		if(head == null) {
			head = tail = newNode;
		} else {
			newNode.setNext(head);
			head = newNode;
		}
	}
	
	public Node<T> getHead() {
		return head;
	}
	
	public void addTail(T n) {
		Node<T> newNode = new Node<>(n);
		if (tail == null) { 
			head = newNode;
			tail = newNode;
		} else {
			tail.setNext(newNode);
			tail = newNode;
		}
	}

//	public void addInOrder(T n) {
//			Node<T> newNode = new Node<>(n);
//			if(head == null) {
//				head = newNode;
//				return;
//			}
//			if(compare(n, head.getData()) <= 0) {
//				newNode.setNext(head);
//				head = newNode;
//				return;
//			}
//			
//			Node<T> currentNode = head;
//			while(currentNode.getNext() != null && compare(n, currentNode.getNext().getData()) > 0) {
//				currentNode = currentNode.getNext();
//			} 
//			newNode.setNext(currentNode.getNext());
//			currentNode.setNext(newNode);
//			newNode.setNext(mover.getNext());
//			mover.setNext(newNode);
//		}
//		if(compare(n, head.getData()) <= 0) {
//				newNode.setNext(head);
//				head = newNode;
//				return;
//			}
//			
//			Node<T> currentNode = head;
//			while(currentNode.getNext() != null && compare(n, currentNode.getNext().getData()) > 0) {
//				currentNode = currentNode.getNext();
//			}
//			newNode.setNext(currentNode.getNext());
//			currentNode.setNext(newNode);
//	}
	
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
	
	public void emptyList() {
		head = null;
		tail = null;
	}

	private int compare(T a1, T a2) {
		if (comparator == null)
			return a1.compareTo(a2);
		else
			return comparator.compare(a1, a2);
	}
}
