public class DLinkedList implements MyList {
	private Node head;
	private Node tail;
	private int manyNodes;

	// define any variables you want using the Encapsulation design principle.

	private class Node {
		// a private nested Node class that users cannot access.
		private Object obj;
		private Node next;
		private Node prev;
		// no argument constructor
		private Node() {
			this.next= null; 
			this.prev= null; 
		}
		// used to create the nodes in the doubly linked list
		private Node(Object obj, Node prev, Node next) {
			this.obj = obj;
			this.next = next;
			this.prev = prev;
		}

		private Node(Node prev, Node next) {
			this.prev = prev;
			this.next = next;
		}
		// rest of the code in the nested class are just getters and setters
		public Object getObject() {
			return this.obj;
		}

		public void setObject(Object item) {
			this.obj = item;
		}

		public Node getNext() {
			return this.next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public Node getPrev() {
			return this.prev;
		}

		public void setPrev(Node prev) {
			this.prev = prev;
		}
	}

	// no argument constructor
	public DLinkedList() {
		head = null;
		tail = null;
		manyNodes = 0;
	}

	// insert 'item' at 'index'. Must rearrange pointers.
	public void insert(int index, Object item) {
		// invalid index if the index is larger than the number of nodes
		if (index > manyNodes) {
			throw new IllegalArgumentException("Invalid Index");
		}
		// if the list is empty, just create the list with 1 node
		if (isEmpty()) {
			Node newNode = new Node(item, null, null);
			head = newNode;
			tail = newNode;
		} 
		else if(index == 0) // if adding to the front, create node pointing to header and assign as new header
		{
			Node newNode = new Node(item, null, head);
			head.setPrev(newNode);
			head = newNode; 	
		}
		else if(index == manyNodes){
			// this is appending to the end of the list if the index is the same as the number of nodes
			Node temp = new Node(item, tail, null); //make node.prev point to the tail
			tail.setNext(temp); // assign tail.next to temp
			tail = temp; // update tail
		}
		else{ // insert node normally
			Node temp = head;
			// need to iterate to get to the correct index
			for (int i = 0; i < index; i++) {
				temp = temp.next;
			}
			// rearrange pointers
			Node newNode = new Node(item, temp.getPrev(), temp);
			temp.getPrev().setNext(newNode);
			temp.setPrev(newNode);

		}

		manyNodes++;

	}

	// insert 'item' at the end of the list.
	// // Maintaining a tail pointer makes this easy
	public void append(Object item) {
		
		Node temp = new Node(item, tail, null); //make node.prev point to the tail
		tail.setNext(temp); // assign tail.next to temp
		tail = temp; // update tail
		manyNodes++;
		// if the list was empty, there is only one node now, and it needs to be assigned as the head
		if(manyNodes == 1){
			head = temp; 
		}
		
	}

	// clear the entire list.
	public void clear() {
		// if list is already empty, just throw an error
		if(isEmpty())
		{
			throw new IllegalArgumentException("Already cleared");
		}
		Node temp = head; 
		Node temp2 = temp.getNext();
		// if there is only 1 node (the head), then temp2 == null and the while loop does not execute
		// since head.getNext() is null 
		while(temp2 != null)
		{
			// set everything in the node to null, then assign to temp2
			temp.setNext(null);
			temp.setPrev(null);
			temp.setObject(null);
			temp = temp2; 
			temp2 = temp2.getNext(); 
		}
		// if temp2 is null, there is still one node left, so it has to be deleted still
		temp.setNext(null);
		temp.setPrev(null);
		temp.setObject(null);
		head = null; 
		tail = null; 
		manyNodes = 0; 
	}

	// return true if list is empty, false otherwise.
	public boolean isEmpty() {
		// if manyNodes == 0, then true is returned, else its false
		return (manyNodes == 0);
	}

	// return the size of the list, else -1. Must require O(1) ops.
	public int size() {
		return manyNodes;
	}

	// replaces the existing Object at 'index' with 'item'.
	// return true, if replace succeeds.
	// return false, otherwise.
	public boolean replace(int index, Object item) {
		// if there are 10 nodes, index 10 is null so it is not valid
		// only indexes 9 or less are valid in this case, so the index must be less
		//than the number of nodes
		if(index < 0){return false;} //invalid index
		if(manyNodes <= index){return false;}
		Node temp = head; 
		for(int i = 0 ; i < index; i++)
		{
			temp = temp.getNext(); 
		}
		temp.setObject(item);
		return true;
	}

	// removes the element at 'index'.
	public void remove(int index) {
		if(index < 0){
			System.out.println("invalid index"); throw new IllegalArgumentException(); 
		}
		if(manyNodes <= index){throw new IllegalArgumentException();}
		Node temp = head; 
		for(int i = 0; i < index; i++)
		{
			temp = temp.getNext(); 
		}
		// temp will point to the index we want to delete, 
		// for example index 2. so we set the node's previous to index 2's next
		// We do the same for the node after the index
		if(temp != head){
			// if temp is the head, no need to get the previous since it is null
			temp.getPrev().setNext(temp.getNext());
		}
		else{
			// need to update head if temp == head
			head = temp.getNext(); 
		}
		if(temp!=tail) {
			// same idea, only need to get next if temp != tail
			temp.getNext().setPrev(temp.getPrev());
		}
		else{
			// maintain the tail of the list
			tail = temp.getPrev();
		}
		temp.setNext(null);
		temp.setPrev(null);
		manyNodes--;
	}

	// return the element at 'index', if it exists.
	// return null otherwise.
	// Do not remove the item at 'index'.
	public Object get(int index) {
		// can't get an item if list is empty or if the index is invalid
		if (isEmpty() || manyNodes < index) {
			return null;
		}
		if(index < 0){return null; } // can't have a negative index!
		Node temp = head;
		for (int i = 0; i < index; i++) {
			temp = temp.getNext();
		}
		return temp.getObject();
	}



	public static void main(String[] args)
	{
		DLinkedList list = new DLinkedList(); 
		System.out.println(list.isEmpty());
		list.insert(0, 10);
		list.insert(1, 11);
		System.out.println("Tail: " + list.tail.getObject());
		list.insert(0, 9);

		System.out.println(list.isEmpty());
		System.out.println(list.get(0));
		//list.remove(0);
		System.out.println(list.get(0));
		System.out.println("Before: " + list.size());
		list.insert(list.size(),12);
		System.out.println((list.size()));
		System.out.println("Tail: " + list.tail.getObject());

		System.out.println(list.get(list.size()-1));
		list.append(13);	
		System.out.println("Tail: " + list.tail.getObject());

		System.out.println(list.get(list.size()-1));

		System.out.println("After removal : " + list.isEmpty());


	}
}