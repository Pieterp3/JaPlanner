package util.structures;

public class MapNode<K, V> implements Comparable<MapNode<K, V>> {
    
        private K key;
        private V value;
        private MapNode<K, V> next;
    
        public MapNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    
        public MapNode(K key, V value, MapNode<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    
        public K getKey() {
            return this.key;
        }
    
        public V getValue() {
            return this.value;
        }
    
        public MapNode<K, V> getNext() {
            return this.next;
        }
    
        public void setKey(K key) {
            this.key = key;
        }
    
        public void setValue(V value) {
            this.value = value;
        }
    
        public void setNext(MapNode<K, V> next) {
            this.next = next;
        }

        public String toString() {
            return key + "=" + value;
        }

        @Override
        public int compareTo(MapNode<K, V> o) {
            return this.key.toString().compareTo(o.getKey().toString());
        }
    
}
