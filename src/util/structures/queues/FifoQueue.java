package util.structures.queues;

public class FifoQueue<T> extends Queue<T> {

	public FifoQueue() {
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
		} else {
			QueueNode<T> node = new QueueNode<T>(element);
			tail.setNext(node);
			node.setPrev(tail);
			tail = node;
		}
		size++;
	}
	
}
