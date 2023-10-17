package util.structures.queues;

/**
 * Creates a queue which will return the elements from 'remove' in the order they were added.
 * Does not remove elements from the queue when they are returned.
 * Starts from the beginning whenever the end of the queue is reached.
 * 
 */
public class LoopingQueue<T> extends Queue<T> {

	private QueueNode<T> current;

	public LoopingQueue() {
		super();
	}

	@Override
	public void add(T element) {
		if (element == null) {
			return;
		}
		if (head == null) {
			head = new QueueNode<T>(element);
			tail = head;
			current = head;
		} else {
			tail.setNext(new QueueNode<T>(element));
			tail.getNext().setPrev(tail);
			tail = tail.getNext();
		}
		size += 1;
	}

	@Override
	public T remove() {
		if (current == null) {
			return null;
		}
		T data = current.getData();
		current = current.getNext();
		if (current == null) {
			current = head;
		}
		return data;
	}

	public void backToLast() {
		if (current == null) {
			return;
		}
		current = current.getPrev();
		if (current == null) {
			current = tail;
		}
	}

}
