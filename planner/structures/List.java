package structures;

public class List<T> {

    private ListNode<T> head;
    private ListNode<T> tail;
    private int size;

    public List() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public List(T data) {
        this.head = new ListNode<T>(data);
        this.tail = this.head;
        this.size = 1;
    }

    public List(T[] data) {
        this.head = new ListNode<T>(data[0]);
        this.tail = this.head;
        this.size = 1;
        for (int i = 1; i < data.length; i++) {
            this.add(data[i]);
        }
    }

    public List(List<T> list) {
        this.head = new ListNode<T>(list.get(0));
        this.tail = this.head;
        this.size = 1;
        for (int i = 1; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    public void add(T data) {
        if (this.head == null) {
            this.head = new ListNode<T>(data);
            this.tail = this.head;
        } else {
            ListNode<T> node = new ListNode<T>(data);
            this.tail.setNext(node);
            node.setPrev(this.tail);
            this.tail = node;
        }
        this.size++;
    }

    public void add(T data, int index) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            ListNode<T> node = new ListNode<T>(data);
            node.setNext(this.head);
            this.head.setPrev(node);
            this.head = node;
        } else if (index == this.size) {
            ListNode<T> node = new ListNode<T>(data);
            this.tail.setNext(node);
            node.setPrev(this.tail);
            this.tail = node;
        } else {
            ListNode<T> node = new ListNode<T>(data);
            ListNode<T> current = this.head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            node.setNext(current);
            node.setPrev(current.getPrev());
            current.getPrev().setNext(node);
            current.setPrev(node);
        }
        this.size++;
    }

    public void add(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    public void add(List<T> list, int index) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i), index + i);
        }
    }

    public void remove(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            this.head = this.head.getNext();
            this.head.setPrev(null);
        } else if (index == this.size - 1) {
            this.tail = this.tail.getPrev();
            this.tail.setNext(null);
        } else {
            ListNode<T> current = this.head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            current.getPrev().setNext(current.getNext());
            current.getNext().setPrev(current.getPrev());
        }
        this.size--;
    }

    public void remove(T data) {
        ListNode<T> current = this.head;
        for (int i = 0; i < this.size; i++) {
            if (current.getData().equals(data)) {
                this.remove(i);
                return;
            }
            current = current.getNext();
        }
    }

    public void remove(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.remove(list.get(i));
        }
    }

    public void set(T data, int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        ListNode<T> current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        current.setData(data);
    }

    public T get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        ListNode<T> current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }

    public int indexOf(T data) {
        ListNode<T> current = this.head;
        for (int i = 0; i < this.size; i++) {
            if (current.getData().equals(data)) {
                return i;
            }
            current = current.getNext();
        }
        return -1;
    }

    public boolean contains(T data) {
        return this.indexOf(data) != -1;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public List<T> subList(int start, int end) {
        if (start < 0 || start >= this.size || end < 0 || end >= this.size || start > end) {
            throw new IndexOutOfBoundsException();
        }
        List<T> list = new List<T>();
        ListNode<T> current = this.head;
        for (int i = 0; i < start; i++) {
            current = current.getNext();
        }
        for (int i = start; i <= end; i++) {
            list.add(current.getData());
            current = current.getNext();
        }
        return list;
    }

    public List<T> subList(int start) {
        return this.subList(start, this.size - 1);
    }

    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] array = (T[]) new Object[this.size];
        ListNode<T> current = this.head;
        for (int i = 0; i < this.size; i++) {
            array[i] = current.getData();
            current = current.getNext();
        }
        return array;
    }

    public void reverse() {
        ListNode<T> current = this.head;
        ListNode<T> next = null;
        ListNode<T> prev = null;
        while (current != null) {
            next = current.getNext();
            current.setNext(prev);
            current.setPrev(next);
            prev = current;
            current = next;
        }
        this.tail = this.head;
        this.head = prev;
    }

    public void swap(int index1, int index2) {
        if (index1 < 0 || index1 >= this.size || index2 < 0 || index2 >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        ListNode<T> current1 = this.head;
        ListNode<T> current2 = this.head;
        for (int i = 0; i < index1; i++) {
            current1 = current1.getNext();
        }
        for (int i = 0; i < index2; i++) {
            current2 = current2.getNext();
        }
        T temp = current1.getData();
        current1.setData(current2.getData());
        current2.setData(temp);
    }

    public void swap(T data1, T data2) {
        ListNode<T> current1 = this.head;
        ListNode<T> current2 = this.head;
        for (int i = 0; i < this.size; i++) {
            if (current1.getData().equals(data1)) {
                break;
            }
            current1 = current1.getNext();
        }
        for (int i = 0; i < this.size; i++) {
            if (current2.getData().equals(data2)) {
                break;
            }
            current2 = current2.getNext();
        }
        T temp = current1.getData();
        current1.setData(current2.getData());
        current2.setData(temp);

    }
    
}