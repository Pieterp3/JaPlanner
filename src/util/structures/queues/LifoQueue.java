package util.structures.queues;

public class LifoQueue<T> extends Queue<T> {

	public LifoQueue() {
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
			head.setPrev(node);
			node.setNext(head);
			head = node;
		}
		size++;
	}

}
