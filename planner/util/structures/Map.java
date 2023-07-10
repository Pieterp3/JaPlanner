package util.structures;

public class Map<K, V> implements Comparable<Map<K, V>> {

    private List<MapNode<K, V>> table = new List<MapNode<K, V>>();

    public Map() {
        clear();
    }

    public Map(K key, V value) {
        clear();
        put(key, value);
    }

    public Map(K[] keys, V[] values) {
        clear();
        for (int i = 0; i < keys.length; i++) {
            put(keys[i], values[i]);
        }
    }

    public Map(List<K> keys, List<V> values) {
        clear();
        for (int i = 0; i < keys.size(); i++) {
            put(keys.get(i), values.get(i));
        }
    }

    public Map(Map<K, V> map) {
        clear();
        for (int i = 0; i < map.size(); i++) {
            put(map.getKey(i), map.getValue(i));
        }
    }

    public K getKey(int index) {
        return this.table.get(index).getKey();
    }

    public V getValue(int index) {
        return this.table.get(index).getValue();
    }

    public V get(K key) {
        for (int i = 0; i < this.table.size(); i++) {
            if (this.table.get(i).getKey().equals(key)) {
                return this.table.get(i).getValue();
            }
        }
        return null;
    }

    public void put(K key, V value) {
        if (key == null) {
            return;
        }
        for (int i = 0; i < this.table.size(); i++) {
            if (this.table.get(i).getKey().equals(key)) {
                this.table.get(i).setValue(value);
                return;
            }
        }
        this.table.add(new MapNode<K, V>(key, value));
    }

    public void remove(K key) {
        for (int i = 0; i < this.table.size(); i++) {
            if (this.table.get(i).getKey().equals(key)) {
                this.table.remove(i);
                return;
            }
        }
    }

    public void clear() {
        this.table.clear();
    }

    public int size() {
        return this.table.size();
    }

    public boolean isEmpty() {
        return this.table.isEmpty();
    }

    public boolean containsKey(K key) {
        for (int i = 0; i < this.table.size(); i++) {
            if (this.table.get(i).getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(V value) {
        for (int i = 0; i < this.table.size(); i++) {
            if (this.table.get(i).getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    public List<K> getKeys() {
        List<K> keys = new List<K>();
        for (int i = 0; i < this.table.size(); i++) {
            keys.add(this.table.get(i).getKey());
        }
        return keys;
    }

    public List<V> getValues() {
        List<V> values = new List<V>();
        for (int i = 0; i < this.table.size(); i++) {
            values.add(this.table.get(i).getValue());
        }
        return values;
    }

    public List<MapNode<K, V>> getTable() {
        return this.table;
    }

    public void setTable(List<MapNode<K, V>> table) {
        this.table = table;
    }

    @Override
    public String toString() {
        String str = "{";
        for (int i = 0; i < this.table.size(); i++) {
            str += this.table.get(i).getKey() + "=" + this.table.get(i).getValue();
            if (i < this.table.size() - 1) {
                str += ", ";
            }
        }
        str += "}";
        return str;
    }

    public List<K> keySet() {
        return getKeys();
    }

    @Override
    public int compareTo(Map<K, V> o) {
        return this.table.compareTo(o.getTable());
    }

}
