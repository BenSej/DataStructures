import java.util.Vector;

class DoubleHashingProbingHash<Key, Value> implements HashInterface<Key, Value> {
	
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
	int doubleFactor;
	
	int hash(Key key) {
		return ((Integer) key>>8) | (((Integer) key&0xff)<<16);
	}
	
	int hash2(Key key) {
		return 181 - (hash(key) % 181);
	}
	
	int hashIndex(Key key) {
		return hash(key) % table.size();
	}
	
	int lookUp(Key key) {
		int startIndex = hashIndex(key);
		int index = startIndex;
		int hash2 = hash2(key);
		int i = 1;
		
		while (true) {
			Record p = table.get(index);
			
			if (p == null || p.key.equals(key)) {
				return index;
			}
			collisions += 1;
			index = startIndex + (i * hash2);
			index %= table.size();
			i++;
			
			if (index == startIndex) {
				return table.size() + 1;
			}
		}
	}
	
	int nextSize() {
		return nextPrime(table.size() * 2);
	}
	
	static boolean isPrime(int n) { 
        if (n <= 1) {
        	return false;  
        }
        
        if (n <= 3) {
        	return true;  
        }
        
        if (n % 2 == 0 || n % 3 == 0) {
        	return false;  
        }
          
        for (int i = 5; i * i <= n; i = i + 6)  
            if (n % i == 0 || n % (i + 2) == 0)  
            return false;  
          
        return true;  
    }
	
    static int nextPrime(int N) {
        if (N <= 1)  {
            return 2;  
        }
      
        int prime = N;
        boolean found = false;  
        
        while (!found) {  
            prime++;  
      
            if (isPrime(prime)) {
                found = true; 
            }
        }
        return prime;  
    }
	
	DoubleHashingProbingHash(int initialSize, int doubleFactor) {
		table = new Vector<Record>();
		table.setSize(initialSize);
		this.doubleFactor = doubleFactor;
		collisions = 0;
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
