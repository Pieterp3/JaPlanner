package util.structures.queues;

public class QueueNode<T> {
	
	private T data;
	private QueueNode<T> next;
	private QueueNode<T> prev;

	public QueueNode(T data) {
		this.data = data;
		this.next = null;
		this.prev = null;
	}

	public QueueNode(T data, QueueNode<T> next, QueueNode<T> prev) {
		this.data = data;
		this.next = next;
		this.prev = prev;
	}

	public T getData() {
		return this.data;
	}

	public QueueNode<T> getNext() {
		return this.next;
	}

	public QueueNode<T> getPrev() {
		return this.prev;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setNext(QueueNode<T> next) {
		this.next = next;
	}

	public void setPrev(QueueNode<T> prev) {
		this.prev = prev;
	}
}
