package structures;

import java.util.Iterator;

public class ListIterator<T> implements Iterator<T> {

    private ListNode<T> current;

    public ListIterator(ListNode<T> head) {
        this.current = head;
    }

    public boolean hasNext() {
        return this.current != null;
    }

    public T next() {
        T data = this.current.getData();
        this.current = this.current.getNext();
        return data;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
    
}
