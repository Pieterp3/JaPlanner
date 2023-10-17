package util.structures.lists;

public class ListNode<T> {

    private T data;
    private ListNode<T> next;
    private ListNode<T> prev;

    public ListNode(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public ListNode(T data, ListNode<T> next, ListNode<T> prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    public T getData() {
        return this.data;
    }

    public ListNode<T> getNext() {
        return this.next;
    }

    public ListNode<T> getPrev() {
        return this.prev;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setNext(ListNode<T> next) {
        this.next = next;
    }

    public void setPrev(ListNode<T> prev) {
        this.prev = prev;
    }
}
