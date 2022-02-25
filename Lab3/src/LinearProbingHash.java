import java.util.Vector;

class LinearProbingHash<Key, Value> implements HashInterface<Key, Value> {
	
	private class Record {
		public Key key;
		public Value value;
		
		Record(Key key1, Value value1) {
			key = key1;
			value = value1;
		}
	}
	
	Vector<Record> table;
	int collisions;
	
	
	LinearProbingHash(int initialSize) {
		table = new Vector<Record>();
		table.setSize(initialSize);
		collisions = 0;
	}
	
	int hash(Key key) {
		return ((Integer) key>>8) | (((Integer) key&0xff)<<16);
	}
	
	int hashIndex(Key key) {
		int index = hash(key);
		return index % table.size();
	}
	
	int lookUp(Key key) {
		int startIndex = hashIndex(key);
		int index = startIndex;
		
		while (true) {
			Record p = table.get(index);
			
			if (p == null || p.key.equals(key)) {
				return index;
			}
			collisions += 1;
			index += 1;
			index %= table.size();
			
			if (index == startIndex) {
				return table.size() + 1;
			}
		}
	}
	
	public Value get(Key key) {
		int index = lookUp(key);
		
		if (index > table.size()) {
			return null;
		}
		
		Record p = table.get(index);
		return p != null ? p.value : null;
	}
	
	public void put(Key key, Value value) {
		int index = lookUp(key);
		
		if (index > table.size()) {
			throw new RuntimeException("Table is full");
		}
		
		Record p = table.get(index);
		if (p == null) {
			table.set(index, new Record(key, value));
		} else {
			p.value = value;
		}
	}
	
	public int getCollisions() {
		return collisions;
	}
}
