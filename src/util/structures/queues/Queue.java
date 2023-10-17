package util.structures.queues;

public abstract class Queue<T> {
	
	protected QueueNode<T> head;
	protected QueueNode<T> tail;
	protected int size;

	public Queue() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	public abstract void add(T element);

	public T remove() {
		if (head == null) {
			return null;
		}
		T data = head.getData();
		head = head.getNext();
		if (head != null) {
			head.setPrev(null);
		}
		size--;
		return data;
	}

	public T peek() {
		if (head == null) {
			return null;
		}
		return head.getData();
	}
	
	public T peekLast() {
		if (tail == null) {
			return null;
		}
		return tail.getData();
	}
	
	public boolean contains(T element) {
		return indexOf(element) != -1;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return head == null;
	}
	
	public boolean remove(T element) {
		if (element == null) {
			return false;
		}
		QueueNode<T> node = head;
		while (node != null) {
			if (node.getData().equals(element)) {
				if (node.getPrev() != null) {
					node.getPrev().setNext(node.getNext());
				} else {
					head = node.getNext();
				}
				if (node.getNext() != null) {
					node.getNext().setPrev(node.getPrev());
				} else {
					tail = node.getPrev();
				}
				size--;
				return true;
			}
			node = node.getNext();
		}
		return false;
	}
	
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}
	
	public int indexOf(T element) {
		if (element == null) {
			return -1;
		}
		QueueNode<T> node = head;
		int index = 0;
		while (node != null) {
			if (node.getData().equals(element)) {
				return index;
			}
			node = node.getNext();
			index++;
		}
		return -1;
	}
	
	public T get(int index) {
		if (index < 0 || index >= size) {
			return null;
		}
		QueueNode<T> node = head;
		for (int i = 0; i < index; i++) {
			node = node.getNext();
		}
		return node.getData();
	}

	public boolean containsAll(Queue<T> queue) {
		for (int i = 0; i < queue.size(); i++) {
			if (!contains(queue.get(i))) {
				return false;
			}
		}
		return true;
	}

	public void addAll(Queue<T> queue) {
		for (int i = 0; i < queue.size(); i++) {
			add(queue.get(i));
		}
	}

	public void removeAll(Queue<T> queue) {
		for (int i = 0; i < queue.size(); i++) {
			remove(queue.get(i));
		}
	}

	public boolean containsAll(T[] array) {
		for (int i = 0; i < array.length; i++) {
			if (!contains(array[i])) {
				return false;
			}
		}
		return true;
	}

	public void addAll(T[] array) {
		for (int i = 0; i < array.length; i++) {
			add(array[i]);
		}
	}

	public void removeAll(T[] array) {
		for (int i = 0; i < array.length; i++) {
			remove(array[i]);
		}
	}
}
